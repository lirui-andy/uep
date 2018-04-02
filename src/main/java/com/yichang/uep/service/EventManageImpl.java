package com.yichang.uep.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.yichang.uep.controller.EventVO;
import com.yichang.uep.model.YEvent;
import com.yichang.uep.model.YEventReceipt;
import com.yichang.uep.repo.EventRepo;
import com.yichang.uep.utils.DateUtils;
import com.yichang.uep.utils.StringUtils;

@Component
public class EventManageImpl implements EventManage {

	@Autowired
	EventRepo eventRepo;
	
	@Override
	@Transactional
	public void addEvent(EventVO event) {
//		constRepo.save(new YConst((short)1, "A","3", "3"));
//		constRepo.save(new YConst((short)1, "B","3", "3"));
		
	}

	//组装查询条件数组
	private List<Predicate> commenSepc(final EventVO event, Root<YEvent> root, CriteriaBuilder cb) {
		List<Predicate> predicates = new ArrayList<>();
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
		return predicates;
	}
	
	//拼装未签收查询Specification
	private Specification<YEvent> unreadEventSpec(final EventVO event){
		Specification<YEvent> spec = new Specification<YEvent>() {
			private static final long serialVersionUID = 1L;
			@Override
			public Predicate toPredicate(Root<YEvent> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = commenSepc(event, root, cb);
				
				Subquery<YEventReceipt> subquery = query.subquery(YEventReceipt.class);
				Root<YEventReceipt> subroot = subquery.from(YEventReceipt.class);
				subquery.select(subroot);
				subquery.where(
						cb.equal(root.get("eventId"), subroot.get("YEvent").get("eventId")),
						cb.equal(subroot.get("orgId"), 1));
				
				predicates.add(cb.not(cb.exists(subquery)));
				
				return query.where(predicates.toArray(new Predicate[]{} )).getRestriction();
			}

		};
		return spec;
	}
	
	//拼装普通查询Specification
	private Specification<YEvent> eventSpec(final EventVO event){
		Specification<YEvent> spec = new Specification<YEvent>() {
			private static final long serialVersionUID = 1L;
			@Override
			public Predicate toPredicate(Root<YEvent> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = commenSepc(event, root, cb);
				return query.where(predicates.toArray(new Predicate[]{} )).getRestriction();
			}

		};
		return spec;
	}
	
	
	@Override
	public Page<YEvent> findNewEvent(final EventVO event, int orgId, int pageNum) {
		return eventRepo.findAll(unreadEventSpec(event), PageRequest.of(pageNum, 20, Direction.DESC, "eventTime"));
	}

	@Override
	public Page<YEvent> findEvent(EventVO event,int pageNum) {	
		return eventRepo.findAll(eventSpec(event), PageRequest.of(pageNum, 20, Direction.DESC, "eventTime"));
	}

}
