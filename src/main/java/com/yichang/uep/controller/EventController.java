package com.yichang.uep.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StreamUtils;
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
import com.yichang.uep.model.YUser;
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
	public CommonOperResult<?> save(EventVO event, final HttpServletRequest request){
		final Date now = new Date();
		final YUser currentUser = currentUser();
		
		
		YEvent eventModel = new YEvent();
		BeanUtils.copyProperties(event, eventModel,"eventId", "inputTime");
		eventModel.setInputTime(now);
		eventModel.setInputOrgId(currentUser.getOrgId());
		eventModel.setInputOrgName(currentUser.getOrgName());
		eventModel.setInputUserId(currentUser.getUserId());
		eventModel.setInputUserName(currentUser.getUserName());
		
		eventModel = eventRepo.save(eventModel);
		
		final int eventId = eventModel.getEventId();
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
				att.setUpUser(currentUser.getUserName());
				att.setAttUri(eventId+"_"+FileUtils.genId());
				att.setFileType(FileUtils.guessFileType(f.getOriginalFilename()));
				FileUtils.saveFile(f.getInputStream(), att.getAttUri());
				attachmentRepo.save(att);
				logger.info(name+"  ,size="+size);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		});
		
		try{
			//录入单位自动签收
			eventManage.receiveEvent(currentUser, eventId, currentUser.getRealName());
		}catch(Exception e){
			logger.error("录入单位自动签收出错");
			logger.error(e.getMessage(), e);
		}
		
		//备注属性
		saveComments(eventId, currentUser, request);
		
		return CommonOperResult.success(eventModel);
	}
	

	@PostMapping("/update")
	@ResponseBody
	public CommonOperResult<?> update(EventVO event, final HttpServletRequest request){
		final YUser currentUser = currentUser();
		//备注属性
		saveComments(event.getEventId(), currentUser, request);
		
		return CommonOperResult.success();
	}

	//保存备注
	private void saveComments(final int eventId,  final YUser currentUser,
			final HttpServletRequest request) {
		final Date now = new Date();
		String[] commentCodes = request.getParameterValues("commentKey");
		if(commentCodes != null){
			Stream.of(commentCodes).forEach(code -> {
				String val = request.getParameter("commentVal_"+code);
				if(val != null){
					YEventComment cmt = new YEventComment();
					cmt.setCommentType(code);
					cmt.setCommentValue(val);
					cmt.setEventId(eventId);
					cmt.setOperTime(now);
					cmt.setOperUser(currentUser.getUserName());
					eventCommentRepo.save(cmt);
				}
			});
		}
	}
	
	@GetMapping("/{eventId}")
	public String view(@PathVariable("eventId") Integer eventId, Model model){
		YEventReceipt rcpt = eventReceiptRepo.findTop1ByEventIdAndOrgId(eventId, currentUser().getOrgId());
		boolean signed = rcpt != null;
		if(!signed){
			model.addAttribute("eventId", eventId);
			model.addAttribute("needSign", true);
		}else{
			Optional<YEvent> event = eventRepo.findById(eventId);
			if(event.isPresent()){
				model.addAttribute("event", event.get());
				
				//签收列表
				List<YEventReceipt> receipts = eventReceiptRepo.findByEventIdOrderByReceiptTimeDesc(eventId);
				model.addAttribute("receipts", receipts);
				//附件列表
				List<YAttachment> attachs = attachmentRepo.findByEventIdOrderByAttIdDesc(eventId);
				model.addAttribute("attachs", attachs);
				//备注信息
				List<YEventComment> commentlist = eventCommentRepo.findLatestByEventId(eventId);
				final Map<String, String> comments = new HashMap<>();
				Stream.of(commentlist.toArray(new YEventComment[]{})).forEach( cmt -> {
					comments.put(cmt.getCommentType(), cmt.getCommentValue());
				});
				model.addAttribute("comments", comments);
			}
		}
		return "view";
	}
	
	@GetMapping(path="/attach/{uuid}")
	public void viewAttach(@PathVariable("uuid") String uuid, HttpServletResponse response) throws IOException{
		Optional<YAttachment>  att = attachmentRepo.findTop1ByAttUri(uuid);
		if(att.isPresent()){
			String fileType = att.get().getFileType();
			try(InputStream in = FileUtils.readFile(uuid)){
				response.setContentType("image/"+fileType);
				StreamUtils.copy(in, response.getOutputStream());
				response.flushBuffer();
			} catch(FileNotFoundException e){
				response.sendError(404, "图片内容未找到");
			}
			
		}else{
			response.sendError(404, "图片记录未找到");
		}
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
	@PostMapping("/receive")
	public String doReceipt(@RequestParam("eventId") Integer eventId, 
			@RequestParam("user") String user){
		if(eventId == null ){
			return "redirect:/404.html";
		}
		eventManage.receiveEvent(currentUser(), eventId, user);
		return "redirect:/event/"+eventId;
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
