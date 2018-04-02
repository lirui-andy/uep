package com.yichang.uep.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the y_event_receipt database table.
 * 
 */
@Entity
@Table(name="y_event_receipt")
@NamedQuery(name="YEventReceipt.findAll", query="SELECT y FROM YEventReceipt y")
public class YEventReceipt implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="receipt_id")
	private int receiptId;

	@Column(name="org_id")
	private int orgId;

	@Column(name="receipt_org_name")
	private String receiptOrgName;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="receipt_time")
	private Date receiptTime;

	@Column(name="receipt_user")
	private String receiptUser;

	@Column(name="user_id")
	private int userId;

	//bi-directional many-to-one association to YEvent
	@ManyToOne
	@JoinColumn(name="event_id")
	private YEvent YEvent;

	public YEventReceipt() {
	}

	public int getReceiptId() {
		return this.receiptId;
	}

	public void setReceiptId(int receiptId) {
		this.receiptId = receiptId;
	}

	public int getOrgId() {
		return this.orgId;
	}

	public void setOrgId(int orgId) {
		this.orgId = orgId;
	}

	public String getReceiptOrgName() {
		return this.receiptOrgName;
	}

	public void setReceiptOrgName(String receiptOrgName) {
		this.receiptOrgName = receiptOrgName;
	}

	public Date getReceiptTime() {
		return this.receiptTime;
	}

	public void setReceiptTime(Date receiptTime) {
		this.receiptTime = receiptTime;
	}

	public String getReceiptUser() {
		return this.receiptUser;
	}

	public void setReceiptUser(String receiptUser) {
		this.receiptUser = receiptUser;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public YEvent getYEvent() {
		return this.YEvent;
	}

	public void setYEvent(YEvent YEvent) {
		this.YEvent = YEvent;
	}

}