package com.yichang.uep.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yichang.uep.model.YEvent;
import com.yichang.uep.repo.EventRepo;
import com.yichang.uep.service.EventManage;

@Controller
@RequestMapping("event")
public class EventController {
	Logger logger = LoggerFactory.getLogger(EventController.class);
	@Autowired
	EventRepo eventRepo;
	@Autowired
	EventManage eventManage;
	
	@RequestMapping("/")
	@ResponseBody
	public Page<YEvent> list(EventVO event){
		Page<YEvent> page = eventManage.findNewEvent(event, 1, 0);
		return page;
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
