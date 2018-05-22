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

import org.hibernate.validator.constraints.URL;
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
import org.springframework.web.multipart.MultipartFile;

import com.yichang.uep.dto.CommentVO;
import com.yichang.uep.dto.CommonOperResult;
import com.yichang.uep.dto.EventListVO;
import com.yichang.uep.dto.EventQueryVO;
import com.yichang.uep.dto.EventVO;
import com.yichang.uep.dto.datatables.PageAdapter;
import com.yichang.uep.dto.datatables.RequestAdapter;
import com.yichang.uep.model.YAttachment;
import com.yichang.uep.model.YEvent;
import com.yichang.uep.model.YEventComment;
import com.yichang.uep.model.YEventHis;
import com.yichang.uep.model.YEventReceipt;
import com.yichang.uep.model.YUser;
import com.yichang.uep.repo.AttachmentRepo;
import com.yichang.uep.repo.EventCommentRepo;
import com.yichang.uep.repo.EventHisRepo;
import com.yichang.uep.repo.EventReceiptRepo;
import com.yichang.uep.repo.EventRepo;
import com.yichang.uep.service.CommentManage;
import com.yichang.uep.service.EventManage;
import com.yichang.uep.utils.FileUtils;
import com.yichang.uep.utils.StringUtils;

@Controller
@RequestMapping("manage")
public class EventManageController extends BaseController{
	Logger logger = LoggerFactory.getLogger(EventManageController.class);
	@Autowired
	EventRepo eventRepo;
	@Autowired
	EventHisRepo eventHisRepo;
	@Autowired
	EventReceiptRepo eventReceiptRepo;
	@Autowired
	EventCommentRepo eventCommentRepo;
	@Autowired
	AttachmentRepo attachmentRepo;

	@Autowired
	EventManage eventManage;

	@Autowired
	CommentManage commentManage;

	@PostMapping("/delete")
	@ResponseBody
	public CommonOperResult delete(@RequestParam("eventIds[]") List<String> eventIds, final HttpServletRequest request){
		final Date now = new Date();
		final YUser currentUser = currentUser();
		logger.info("Deleting event data... Operating by:"+currentUser.getUserName());
		Stream.of(eventIds.toArray(new String[]{})).forEach(id -> {
			Optional<YEvent> eventOpt = eventRepo.findById(Integer.parseInt(id));
			if(eventOpt.isPresent()){
				logger.info("Deleting event: "+id);
				//更新事件表
				YEvent eventModel = eventOpt.get();

				//备份事件记录
				YEventHis his = new YEventHis();
				BeanUtils.copyProperties(eventModel, his);
				eventHisRepo.save(his);

				//更新事件
				eventModel.setActive(Boolean.FALSE);//置为删除状态
				eventModel.setUpdateOrgId(currentUser.getOrgId());
				eventModel.setUpdateOrgName(currentUser.getOrgName());
				eventModel.setUpdateUserId(currentUser.getUserId());
				eventModel.setUpdateUserName(currentUser.getUserName());
				eventModel.setUpdateTime(now);
				eventModel.setUpdateRealName(currentUser.getRealName());
				eventModel.setUpdateReviewerName(currentUser.getRealName());
				eventModel = eventRepo.save(eventModel);
			}
		});

		return CommonOperResult.success();
	}

}
