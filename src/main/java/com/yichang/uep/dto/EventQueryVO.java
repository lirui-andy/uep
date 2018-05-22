package com.yichang.uep.dto;

public class EventQueryVO {
	private String eventType;
	private String name;
	private String idNum;
	private String gender;
	private String timeRange;
	private String keyword;
	private String checkedClear;
	private String activeFlag; //"1"-未删除 "0"-已删除 "A"-全部
	
	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIdNum() {
		return idNum;
	}
	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getTimeRange() {
		return timeRange;
	}
	public void setTimeRange(String timeRange) {
		this.timeRange = timeRange;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getCheckedClear() {
		return checkedClear;
	}
	public void setCheckedClear(String checkedClear) {
		this.checkedClear = checkedClear;
	}
	public String getActiveFlag() {
		return activeFlag;
	}
	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}

}
