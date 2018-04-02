package com.yichang.uep.service;

import org.springframework.data.domain.Page;

import com.yichang.uep.controller.EventVO;
import com.yichang.uep.model.YEvent;

public interface EventManage {

	public void addEvent(EventVO event);
	
	public Page<YEvent> findNewEvent(EventVO event,Integer orgId, Integer pageNum);
	public Page<YEvent> findEvent(EventVO event, Integer pageNum);
}
