package com.yichang.uep.controller;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yichang.uep.model.YEvent;
import com.yichang.uep.repo.EventRepo;

@Controller
@RequestMapping("event")
public class EventController {
	Logger logger = LoggerFactory.getLogger(EventController.class);
	@Autowired
	EventRepo eventRepo;
	
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
		BeanUtils.copyProperties(event, eventModel,"eventId", "eventTime","inputTime");
		eventRepo.save(eventModel);
		
		return "success";
	}
}
