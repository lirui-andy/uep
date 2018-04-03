package com.yichang.uep.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yichang.uep.dto.CommentVO;
import com.yichang.uep.dto.CommonOperResult;
import com.yichang.uep.dto.EventVO;
import com.yichang.uep.dto.datatables.PageAdapter;
import com.yichang.uep.dto.datatables.RequestAdapter;
import com.yichang.uep.model.YAttachment;
import com.yichang.uep.model.YEvent;
import com.yichang.uep.model.YEventComment;
import com.yichang.uep.model.YEventReceipt;
import com.yichang.uep.repo.AttachmentRepo;
import com.yichang.uep.repo.EventCommentRepo;
import com.yichang.uep.repo.EventReceiptRepo;
import com.yichang.uep.repo.EventRepo;
import com.yichang.uep.service.EventManage;
import com.yichang.uep.utils.FileUtils;
import com.yichang.uep.utils.StringUtils;

@Controller
@RequestMapping("event")
public class EventController extends BaseController{
	Logger logger = LoggerFactory.getLogger(EventController.class);
	@Autowired
	EventRepo eventRepo;
	@Autowired
	EventReceiptRepo eventReceiptRepo;
	@Autowired
	EventCommentRepo eventCommentRepo;
	@Autowired
	AttachmentRepo attachmentRepo;
	
	@Autowired
	EventManage eventManage;
	
	/**
	 * 查询事件列表
	 * @param eventSearch
	 * @return
	 */
	@PostMapping(path="/list", consumes={"text/plain", "application/json"})
	@ResponseBody
	public PageAdapter<YEvent> list(
			@RequestBody RequestAdapter<EventVO> eventSearch){
		int orgId = 1;
		Page<YEvent> page  = null;
		if(eventSearch.getCondition() == null || StringUtils.isBlank(eventSearch.getCondition().getEventType())){
			page = eventManage.findNewEvent(eventSearch.getCondition(), 
					orgId, eventSearch.getPage());
		}
		else{
			page = eventManage.findEvent(eventSearch.getCondition(), 
					eventSearch.getPage());
		}
		return PageAdapter.create(page, eventSearch.getDraw());
	}
	
	/**
	 * 保存新事件
	 * @param event
	 * @return
	 */
	@PostMapping("/save")
	@ResponseBody
	public CommonOperResult<?> save(EventVO event){
		
		YEvent eventModel = new YEvent();
		BeanUtils.copyProperties(event, eventModel,"eventId", "inputTime");
		
		eventModel = eventRepo.save(eventModel);
		final int eventId = eventModel.getEventId();
		final Date now = new Date();
		final String user = currentUser().getUserName();
		//file
		Stream.of(event.getFile())
		.filter(f -> (f != null && f.getSize() > 0 ) )
		.forEach(f -> {
			try {
				long size = f.getSize();
				String name = f.getOriginalFilename();
				YAttachment att = new YAttachment();
				att.setEventId(eventId);
				att.setFileName(f.getOriginalFilename());
				att.setFileSize(f.getSize());
				att.setUpTime(now);
				att.setUpUser(user);
				att.setAttUri(eventId+"_"+FileUtils.genId());
				FileUtils.saveFile(f.getInputStream(), att.getAttUri());
				attachmentRepo.save(att);
				logger.info(name+"  ,size="+size);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		});
		logger.info("EventType="+event.getEventType());
		
		return CommonOperResult.success();
	}
	
	@GetMapping("/{eventId}")
	public String view(@PathVariable("eventId") Integer eventId, Model model){
		YEventReceipt rcpt = eventReceiptRepo.findTop1ByEventIdAndOrgId(eventId, currentUser().getOrgId());
		boolean signed = rcpt != null;
		signed = true;
		if(!signed)
			return "sign";
		else{
			Optional<YEvent> event = eventRepo.findById(eventId);
			if(event.isPresent()){
				model.addAttribute("event", event.get());
				
				//签收列表
				List<YEventReceipt> receipts = eventReceiptRepo.findByEventIdOrderByReceiptTimeDesc(eventId);
				model.addAttribute("receipts", receipts);
				//附件列表
				List<YAttachment> attachs = attachmentRepo.findByEventIdOrderByAttIdDesc(eventId);
				model.addAttribute("attachs", attachs);
			}
		}
		return "view";
	}
	
	/**
	 * 判断当前登陆用户对指定的事件是否已签收
	 * @param eventId
	 * @return
	 */
	@GetMapping("/hasReceipt/{eventId}")
	@ResponseBody
	public CommonOperResult<?> hasReceipt(@PathVariable Integer eventId){
		YEventReceipt rcpt = eventReceiptRepo.findTop1ByEventIdAndOrgId(eventId, currentUser().getOrgId());
		return CommonOperResult.success( rcpt != null );
	}
	
	/**
	 * 签收事件
	 * @param eventId
	 * @param user
	 */
	@PostMapping("/receipt/{eventId}")
	@ResponseBody
	public CommonOperResult<?> doReceipt(@PathVariable Integer eventId, 
			@RequestParam("user") String user){
		if(eventId == null ){
			return CommonOperResult.fail("-1", "eventId invalid");
		}
		YEventReceipt recpt = new YEventReceipt();
		recpt.setEventId(eventId);
		recpt.setReceiptTime(new Date());
		recpt.setReceiptUser(user);
		recpt.setUserId(currentUser().getUserId());
		recpt.setOrgId(currentUser().getOrgId());
		recpt.setReceiptOrgName(currentUser().getOrgName());
		eventReceiptRepo.save(recpt);
		return CommonOperResult.success();
	}
	
	/**
	 * 查询事件的签收列表
	 * @param eventId
	 * @return
	 */
	@GetMapping("/receipt/{eventId}")
	@ResponseBody
	public CommonOperResult<List<YEventReceipt>> listReceipt(
			@PathVariable("eventId") Integer eventId){
		if(eventId != null){
			List<YEventReceipt> data = eventReceiptRepo.findByEventIdOrderByReceiptTimeDesc(eventId);
			return CommonOperResult.success(data);
		}
		else return CommonOperResult.success();
	}
	
	/**
	 * 添加事件备注
	 * @return
	 */
	@PostMapping(path="/comment", 
			consumes={"text/plain", "application/json"})
	@ResponseBody
	public CommonOperResult<?> saveComment(
			@RequestBody CommentVO commentVO){
		YEventComment cmt = new YEventComment();
		BeanUtils.copyProperties(commentVO, cmt);
		cmt.setOperTime(new Date());
		cmt.setOperUser(currentUser().getUserName());
		
		eventCommentRepo.save(cmt);
		return CommonOperResult.success();
	}
}
