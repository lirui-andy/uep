package com.yichang.uep.controller;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("event")
public class EventController {
	Logger logger = LoggerFactory.getLogger(EventController.class);

	@PostMapping("/save")
	@ResponseBody
	public String save(EventVO event){
		Stream.of(event.getFile())
		.filter(f -> (f != null && f.getSize() > 0 ) )
		.forEach(f -> {
			long size = f.getSize();
			String name = f.getOriginalFilename();
			logger.info(name+"  ,size="+size);
		});
		logger.info("EventType="+event.getEventType());
		return "success";
	}
}
