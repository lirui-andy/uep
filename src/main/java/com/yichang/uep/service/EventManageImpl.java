package com.yichang.uep.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yichang.uep.controller.EventVO;
import com.yichang.uep.repo.EventRepo;

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

}
