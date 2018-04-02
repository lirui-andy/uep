package com.yichang.uep.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yichang.uep.dto.datatables.PageAdapter;
import com.yichang.uep.dto.datatables.RequestAdapter;
import com.yichang.uep.model.YEvent;
import com.yichang.uep.repo.EventRepo;
import com.yichang.uep.service.EventManage;
import com.yichang.uep.utils.StringUtils;

@Controller
@RequestMapping("event")
public class EventController {
	Logger logger = LoggerFactory.getLogger(EventController.class);
	@Autowired
	EventRepo eventRepo;
	@Autowired
	EventManage eventManage;
	
	@PostMapping(path="/list", consumes={"text/plain", "application/json"})
	@ResponseBody
	public PageAdapter<YEvent> list(@RequestBody RequestAdapter<EventVO> eventSearch){
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
	
	@PostMapping("/save")
	@ResponseBody
	public String save(EventVO event){
		/*
		Stream.of(event.getFile())
		.filter(f -> (f != null && f.getSize() > 0 ) )
		.forEach(f -> {
			long size = f.getSize();
			String name = f.getOriginalFilename();
			logger.info(name+"  ,size="+size);
		});
		*/
		logger.info("EventType="+event.getEventType());
		
		YEvent eventModel = new YEvent();
		BeanUtils.copyProperties(event, eventModel,"eventId", "inputTime");
		eventRepo.save(eventModel);
		
		return "success";
	}
}
