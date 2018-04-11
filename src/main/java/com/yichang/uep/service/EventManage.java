package com.yichang.uep.service;

import org.springframework.data.domain.Page;

import com.yichang.uep.dto.EventListVO;
import com.yichang.uep.dto.EventQueryVO;
import com.yichang.uep.dto.EventVO;
import com.yichang.uep.model.YEvent;
import com.yichang.uep.model.YUser;

public interface EventManage {

	public void addEvent(EventVO event);
	
	public long findNeweventCount(int orgId);
	
	public Page<YEvent> findNewEvent(EventQueryVO event,Integer orgId, Integer pageNum);
	
	public Page<YEvent> findEvent(EventQueryVO event, Integer pageNum);
	
	public void receiveEvent(YUser rcvUser, int eventId, String rcvUserRealName);

	public Page<EventListVO> findSignedInfo(Page<YEvent> page, int orgId);

}
