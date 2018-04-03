package com.yichang.uep.dto;

import java.io.Serializable;

public class CommentVO implements Serializable{
	private static final long serialVersionUID = 1L;

	String commentType;
	String commentValue;
	Integer eventId;
	public String getCommentType() {
		return commentType;
	}
	public void setCommentType(String commentType) {
		this.commentType = commentType;
	}
	public String getCommentValue() {
		return commentValue;
	}
	public void setCommentValue(String commentValue) {
		this.commentValue = commentValue;
	}
	public Integer getEventId() {
		return eventId;
	}
	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}
	
	
}
