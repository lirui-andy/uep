package com.yichang.uep.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.yichang.uep.dto.EventListVO;
import com.yichang.uep.dto.EventQueryVO;
import com.yichang.uep.dto.EventVO;
import com.yichang.uep.model.YEvent;
import com.yichang.uep.model.YEventComment;
import com.yichang.uep.model.YEventReceipt;
import com.yichang.uep.model.YUser;
import com.yichang.uep.repo.EventReceiptRepo;
import com.yichang.uep.repo.EventRepo;
import com.yichang.uep.utils.DateUtils;
import com.yichang.uep.utils.StringUtils;

@Component
public class EventManageImpl implements EventManage {

	@Autowired
	EventRepo eventRepo;
	@Autowired
	EventReceiptRepo eventReceiptRepo;
	
	@Override
	@Transactional
	public void addEvent(EventVO event) {
//		constRepo.save(new YConst((short)1, "A","3", "3"));
//		constRepo.save(new YConst((short)1, "B","3", "3"));
		
	}

	//组装查询条件数组
	private List<Predicate> commenSepc(final EventQueryVO event, Root<YEvent> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicates = new ArrayList<>();
		if(event == null) return predicates;
		//事件类别 
		if( !StringUtils.isBlank( event.getEventType()))
			predicates.add(root.get("eventType").in(event.getEventType()));
		//性别
		if(!StringUtils.isBlank( event.getGender()))
			predicates.add(root.get("gender").in(event.getGender()));
		//身份证号
		if(!StringUtils.isBlank( event.getIdNum()))
			predicates.add( cb.like(root.get("idNum"), 
					"%"+StringUtils.trimAllWhitespace(event.getIdNum())+"%") );
		//姓名
		if(!StringUtils.isBlank( event.getName()))
			predicates.add(cb.like(root.get("name"), 
					"%"+StringUtils.trimAllWhitespace(event.getName())+"%" ));
		//时间范围
		if(!StringUtils.isBlank(event.getTimeRange())) {
			String[] s = StringUtils.trimAllWhitespace(event.getTimeRange()).split("至");
			predicates.add(cb.between(root.get("eventTime"), 
					DateUtils.parse(s[0]), DateUtils.addDay(DateUtils.parse(s[1]), 1) ));
		}
		//关键字
		if(!StringUtils.isBlank(event.getKeyword())){
			predicates.add(cb.or(buildKeywordClause(event.getKeyword(), root, query, cb)));
		}
		return predicates;
	}
	
	private Predicate[] buildKeywordClause(String keyword,Root<YEvent> root, CriteriaQuery<?> query, CriteriaBuilder cb){
		String[] keywords = StringUtils.trimAllWhitespace(keyword).split("[\\,，]");
		
		List<Predicate> list = Stream.of(keywords)
				.limit(3) //只接受前3个关键字
				.flatMap( k -> {
			String likeword = "%"+k+"%";
			Subquery<YEventComment> subquery = query.subquery(YEventComment.class);
			Root<YEventComment> subroot = subquery.from(YEventComment.class);
			subquery.select(subroot);
			subquery.where(
					cb.equal(root.get("eventId"), subroot.get("eventId")),
					cb.like(subroot.get("commentValue"), likeword ));
			
			return Stream.of(
					cb.like(root.get("briefInfo"), likeword),
					cb.like(root.get("detailInfo"), likeword),
					cb.exists(subquery)
					);
		}).collect(Collectors.toList());
		
		return list.toArray(new Predicate[]{});
	}
	
	//拼装未签收查询Specification
	private Specification<YEvent> unreadEventSpec(final EventQueryVO event, final Integer orgId){
		Specification<YEvent> spec = new Specification<YEvent>() {
			private static final long serialVersionUID = 1L;
			@Override
			public Predicate toPredicate(Root<YEvent> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = commenSepc(event, root, query, cb);
				
				Subquery<YEventReceipt> subquery = query.subquery(YEventReceipt.class);
				Root<YEventReceipt> subroot = subquery.from(YEventReceipt.class);
				subquery.select(subroot);
				subquery.where(
						cb.equal(root.get("eventId"), subroot.get("eventId")),
						cb.equal(subroot.get("orgId"), orgId));
				
				predicates.add(cb.not(cb.exists(subquery)));
				
				return query.where(predicates.toArray(new Predicate[]{} )).getRestriction();
			}

		};
		return spec;
	}
	
	//拼装普通查询Specification
	private Specification<YEvent> eventSpec(final EventQueryVO event){
		Specification<YEvent> spec = new Specification<YEvent>() {
			private static final long serialVersionUID = 1L;
			@Override
			public Predicate toPredicate(Root<YEvent> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = commenSepc(event, root, query, cb);
				return query.where(predicates.toArray(new Predicate[]{} )).getRestriction();
			}

		};
		return spec;
	}
	
	private PageRequest pageRequest(Integer pageNum){
		if(pageNum == null ) pageNum = 0;
		return PageRequest.of(pageNum, 20, Direction.DESC, "eventTime");
	}
	
	
	//查询单位所有未签收事件数量
	@Override
	public long findNeweventCount(int orgId) {
		return eventRepo.count(unreadEventSpec(null, orgId));
	}

	//查询单位未签收事件
	@Override
	public Page<YEvent> findNewEvent(EventQueryVO event, Integer orgId, Integer pageNum) {
		event.setEventType(null);
		return eventRepo.findAll( unreadEventSpec(event, orgId), pageRequest(pageNum));
	}

	//按条件查询事件
	@Override
	public Page<YEvent> findEvent(EventQueryVO event,Integer pageNum) {	
		return eventRepo.findAll( eventSpec(event), pageRequest(pageNum));
	}

	//签收事件
	@Override
	public void receiveEvent(YUser rcvUser, int eventId, String rcvUserRealName) {
		YEventReceipt recpt = new YEventReceipt();
		recpt.setEventId(eventId);
		recpt.setReceiptTime(new Date());
		recpt.setReceiptUser(rcvUserRealName);
		recpt.setUserId(rcvUser.getUserId());
		recpt.setOrgId(rcvUser.getOrgId());
		recpt.setReceiptOrgName(rcvUser.getOrgName());
		eventReceiptRepo.save(recpt);
		
	}

	@Override
	public Page<EventListVO> findSignedInfo(Page<YEvent> page, int orgId) {
		
		//事件ID列表
		List<Integer> eventIds = page.getContent().stream()
		.flatMap( f-> Stream.of(f.getEventId()) )
		.collect(Collectors.toList());
		Set<Integer> received = new HashSet<>();
		
		if(eventIds.size() > 0){
			//找出给定的事件中，已经签收的事件
			List<YEventReceipt> receipts = eventReceiptRepo.findAllByEventIdAndOrgId(eventIds, orgId);
			received .addAll( receipts.stream()
				.flatMap(r -> Stream.of(r.getEventId()))
				.collect(Collectors.toSet()) );
		}
		
		//转换成EventListVO列表
		List<EventListVO> list = page.getContent().stream().flatMap(e -> {
			EventListVO vo = new EventListVO();
			BeanUtils.copyProperties(e, vo);
			vo.setSigned(received.contains(e.getEventId()));
			return Stream.of(vo);
		}).collect(Collectors.toList());
		
		Page<EventListVO> newpage = new PageImpl<EventListVO>(list, 
				page.getPageable(), 
				page.getTotalElements());
		return newpage;
	}

}
