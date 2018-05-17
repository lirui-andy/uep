package com.yichang.uep.dto;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonFormat;

public class EventVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	MultipartFile[] file;
	Integer eventId;
	private String eventType;
	
	private String checkedClear;//是否查清
	private String rcvOrgCode;//接警单位编码
	private String rcvOrgName;//接警单位名称

	private String handlerName;//出警民警
	private String reportorName;//报警人
	private String reportorTel;//报警电话

	private String coOrgContact;//协查单位联系人
	private String coOrgName;//协查单位
	private String coOrgTel;//协查单位电话

	private String name;
	private String gender;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm", timezone="GMT+8")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	private Date eventTime;
	
	private String idNum;
	
	private String briefInfo;
	private String detailInfo;
	private String reviewerName;
	private String inputRealName;
	
	private String inputOrgName;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm", timezone="GMT+8")
	private Date inputTime;
	private String inputUserName;
	

	private String updateReviewerName;
	private String updateRealName;
	
	public MultipartFile[] getFile() {
		return file;
	}
	public void setFile(MultipartFile[] file) {
		this.file = file;
	}
	public Integer getEventId() {
		return eventId;
	}
	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}
	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	public String getCheckedClear() {
		return checkedClear;
	}
	public void setCheckedClear(String checkedClear) {
		this.checkedClear = checkedClear;
	}
	public String getRcvOrgCode() {
		return rcvOrgCode;
	}
	public void setRcvOrgCode(String rcvOrgCode) {
		this.rcvOrgCode = rcvOrgCode;
	}
	public String getRcvOrgName() {
		return rcvOrgName;
	}
	public void setRcvOrgName(String rcvOrgName) {
		this.rcvOrgName = rcvOrgName;
	}
	public String getHandlerName() {
		return handlerName;
	}
	public void setHandlerName(String handlerName) {
		this.handlerName = handlerName;
	}
	public String getReportorName() {
		return reportorName;
	}
	public void setReportorName(String reportorName) {
		this.reportorName = reportorName;
	}
	public String getReportorTel() {
		return reportorTel;
	}
	public void setReportorTel(String reportorTel) {
		this.reportorTel = reportorTel;
	}
	public String getCoOrgContact() {
		return coOrgContact;
	}
	public void setCoOrgContact(String coOrgContact) {
		this.coOrgContact = coOrgContact;
	}
	public String getCoOrgName() {
		return coOrgName;
	}
	public void setCoOrgName(String coOrgName) {
		this.coOrgName = coOrgName;
	}
	public String getCoOrgTel() {
		return coOrgTel;
	}
	public void setCoOrgTel(String coOrgTel) {
		this.coOrgTel = coOrgTel;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getIdNum() {
		return idNum;
	}
	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}
	public String getBriefInfo() {
		return briefInfo;
	}
	public void setBriefInfo(String briefInfo) {
		this.briefInfo = briefInfo;
	}
	public String getDetailInfo() {
		return detailInfo;
	}
	public void setDetailInfo(String detailInfo) {
		this.detailInfo = detailInfo;
	}
	public String getReviewerName() {
		return reviewerName;
	}
	public void setReviewerName(String reviewerName) {
		this.reviewerName = reviewerName;
	}
	public String getInputRealName() {
		return inputRealName;
	}
	public void setInputRealName(String inputRealName) {
		this.inputRealName = inputRealName;
	}
	public String getInputOrgName() {
		return inputOrgName;
	}
	public void setInputOrgName(String inputOrgName) {
		this.inputOrgName = inputOrgName;
	}
	public Date getInputTime() {
		return inputTime;
	}
	public void setInputTime(Date inputTime) {
		this.inputTime = inputTime;
	}
	public String getInputUserName() {
		return inputUserName;
	}
	public void setInputUserName(String inputUserName) {
		this.inputUserName = inputUserName;
	}
	public Date getEventTime() {
		return eventTime;
	}
	public void setEventTime(Date eventTime) {
		this.eventTime = eventTime;
	}
	public String getUpdateReviewerName() {
		return updateReviewerName;
	}
	public void setUpdateReviewerName(String updateReviewerName) {
		this.updateReviewerName = updateReviewerName;
	}
	public String getUpdateRealName() {
		return updateRealName;
	}
	public void setUpdateRealName(String updateRealName) {
		this.updateRealName = updateRealName;
	}

}
