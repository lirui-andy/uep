package com.yichang.uep.controller;

import java.io.Serializable;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class EventVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	MultipartFile[] file;
	Integer eventId;

	private String briefInfo;
	private String coOrgContact;
	private String coOrgName;
	private String coOrgTel;
	private String detailInfo;
	private Date eventTime;
	private String eventType;
	private String gender;
	private String handler;
	private String idNum;
	private int inputOrgId;
	private String inputOrgName;
	private String inputRealName;
	private Date inputTime;
	private int inputUserId;
	private String inputUserName;
	private String name;
	private int orgId;
	private String reportorName;
	private String reportorTel;
	private String reviewerName;

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
	public String getHandler() {
		return handler;
	}
	public void setHandler(String handler) {
		this.handler = handler;
	}
	public String getBriefInfo() {
		return briefInfo;
	}
	public void setBriefInfo(String briefInfo) {
		this.briefInfo = briefInfo;
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
	public String getDetailInfo() {
		return detailInfo;
	}
	public void setDetailInfo(String detailInfo) {
		this.detailInfo = detailInfo;
	}
	public Date getEventTime() {
		return eventTime;
	}
	public void setEventTime(Date eventTime) {
		this.eventTime = eventTime;
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
	public int getInputOrgId() {
		return inputOrgId;
	}
	public void setInputOrgId(int inputOrgId) {
		this.inputOrgId = inputOrgId;
	}
	public String getInputOrgName() {
		return inputOrgName;
	}
	public void setInputOrgName(String inputOrgName) {
		this.inputOrgName = inputOrgName;
	}
	public String getInputRealName() {
		return inputRealName;
	}
	public void setInputRealName(String inputRealName) {
		this.inputRealName = inputRealName;
	}
	public Date getInputTime() {
		return inputTime;
	}
	public void setInputTime(Date inputTime) {
		this.inputTime = inputTime;
	}
	public int getInputUserId() {
		return inputUserId;
	}
	public void setInputUserId(int inputUserId) {
		this.inputUserId = inputUserId;
	}
	public String getInputUserName() {
		return inputUserName;
	}
	public void setInputUserName(String inputUserName) {
		this.inputUserName = inputUserName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getOrgId() {
		return orgId;
	}
	public void setOrgId(int orgId) {
		this.orgId = orgId;
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
	public String getReviewerName() {
		return reviewerName;
	}
	public void setReviewerName(String reviewerName) {
		this.reviewerName = reviewerName;
	}
	
	
}
