package com.yichang.uep.service;

import org.springframework.data.domain.Page;

import com.yichang.uep.dto.EventVO;
import com.yichang.uep.model.YEvent;
import com.yichang.uep.model.YUser;

public interface EventManage {

	public void addEvent(EventVO event);
	
	public Page<YEvent> findNewEvent(EventVO event,Integer orgId, Integer pageNum);
	public Page<YEvent> findEvent(EventVO event, Integer pageNum);
	
	public void receiveEvent(YUser rcvUser, int eventId, String rcvUserRealName);

	public long findNeweventCount(int orgId);
}
