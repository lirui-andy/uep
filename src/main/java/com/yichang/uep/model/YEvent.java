package com.yichang.uep.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonManagedReference;


/**
 * The persistent class for the y_event database table.
 * 
 */
@Entity
@Table(name="y_event")
@NamedQuery(name="YEvent.findAll", query="SELECT y FROM YEvent y")
public class YEvent implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="event_id")
	private int eventId;

	@Column(name="brief_info")
	private String briefInfo;

	@Column(name="co_org_contact")
	private String coOrgContact;

	@Column(name="co_org_name")
	private String coOrgName;

	@Column(name="co_org_tel")
	private String coOrgTel;

	@Column(name="detail_info")
	private String detailInfo;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="event_time")
	private Date eventTime;

	@Column(name="event_type")
	private String eventType;

	private String gender;

	@Column(name="handler_name")
	private String handlerName;

	@Column(name="id_num")
	private String idNum;

	@Column(name="input_org_id")
	private int inputOrgId;

	@Column(name="input_org_name")
	private String inputOrgName;

	@Column(name="input_real_name")
	private String inputRealName;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="input_time")
	private Date inputTime;

	@Column(name="input_user_id")
	private int inputUserId;

	@Column(name="input_user_name")
	private String inputUserName;

	private String name;

	@Column(name="rcv_org_code")
	private String rcvOrgCode;

	@Column(name="rcv_org_name")
	private String rcvOrgName;

	@Column(name="reportor_name")
	private String reportorName;

	@Column(name="reportor_tel")
	private String reportorTel;

	@Column(name="reviewer_name")
	private String reviewerName;



	@Column(name="update_org_id")
	private Integer updateOrgId;

	@Column(name="update_org_name")
	private String updateOrgName;

	@Column(name="update_user_id")
	private Integer updateUserId;
	
	@Column(name="update_user_name")
	private String updateUserName;
	
	@Column(name="update_real_name")
	private String updateRealName;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="update_time")
	private Date updateTime;

	@Column(name="update_reviewer_name")
	private String updateReviewerName;
	
	@Column(name="active")
	private Boolean active;

	
	public YEvent() {
	}

	public int getEventId() {
		return this.eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public String getBriefInfo() {
		return this.briefInfo;
	}

	public void setBriefInfo(String briefInfo) {
		this.briefInfo = briefInfo;
	}

	public String getCoOrgContact() {
		return this.coOrgContact;
	}

	public void setCoOrgContact(String coOrgContact) {
		this.coOrgContact = coOrgContact;
	}

	public String getCoOrgName() {
		return this.coOrgName;
	}

	public void setCoOrgName(String coOrgName) {
		this.coOrgName = coOrgName;
	}

	public String getCoOrgTel() {
		return this.coOrgTel;
	}

	public void setCoOrgTel(String coOrgTel) {
		this.coOrgTel = coOrgTel;
	}

	public String getDetailInfo() {
		return this.detailInfo;
	}

	public void setDetailInfo(String detailInfo) {
		this.detailInfo = detailInfo;
	}

	public Date getEventTime() {
		return this.eventTime;
	}

	public void setEventTime(Date eventTime) {
		this.eventTime = eventTime;
	}

	public String getEventType() {
		return this.eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getHandlerName() {
		return this.handlerName;
	}

	public void setHandlerName(String handlerName) {
		this.handlerName = handlerName;
	}

	public String getIdNum() {
		return this.idNum;
	}

	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}

	public int getInputOrgId() {
		return this.inputOrgId;
	}

	public void setInputOrgId(int inputOrgId) {
		this.inputOrgId = inputOrgId;
	}

	public String getInputOrgName() {
		return this.inputOrgName;
	}

	public void setInputOrgName(String inputOrgName) {
		this.inputOrgName = inputOrgName;
	}

	public String getInputRealName() {
		return this.inputRealName;
	}

	public void setInputRealName(String inputRealName) {
		this.inputRealName = inputRealName;
	}

	public Date getInputTime() {
		return this.inputTime;
	}

	public void setInputTime(Date inputTime) {
		this.inputTime = inputTime;
	}

	public int getInputUserId() {
		return this.inputUserId;
	}

	public void setInputUserId(int inputUserId) {
		this.inputUserId = inputUserId;
	}

	public String getInputUserName() {
		return this.inputUserName;
	}

	public void setInputUserName(String inputUserName) {
		this.inputUserName = inputUserName;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRcvOrgCode() {
		return this.rcvOrgCode;
	}

	public void setRcvOrgCode(String rcvOrgCode) {
		this.rcvOrgCode = rcvOrgCode;
	}

	public String getRcvOrgName() {
		return this.rcvOrgName;
	}

	public void setRcvOrgName(String rcvOrgName) {
		this.rcvOrgName = rcvOrgName;
	}

	public String getReportorName() {
		return this.reportorName;
	}

	public void setReportorName(String reportorName) {
		this.reportorName = reportorName;
	}

	public String getReportorTel() {
		return this.reportorTel;
	}

	public void setReportorTel(String reportorTel) {
		this.reportorTel = reportorTel;
	}

	public String getReviewerName() {
		return this.reviewerName;
	}

	public void setReviewerName(String reviewerName) {
		this.reviewerName = reviewerName;
	}

	public Integer getUpdateOrgId() {
		return updateOrgId;
	}

	public void setUpdateOrgId(Integer updateOrgId) {
		this.updateOrgId = updateOrgId;
	}

	public String getUpdateOrgName() {
		return updateOrgName;
	}

	public void setUpdateOrgName(String updateOrgName) {
		this.updateOrgName = updateOrgName;
	}

	public Integer getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(Integer updateUserId) {
		this.updateUserId = updateUserId;
	}

	public String getUpdateUserName() {
		return updateUserName;
	}

	public void setUpdateUserName(String updateUserName) {
		this.updateUserName = updateUserName;
	}

	public String getUpdateRealName() {
		return updateRealName;
	}

	public void setUpdateRealName(String updateRealName) {
		this.updateRealName = updateRealName;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateReviewerName() {
		return updateReviewerName;
	}

	public void setUpdateReviewerName(String updateReviewerName) {
		this.updateReviewerName = updateReviewerName;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}


}