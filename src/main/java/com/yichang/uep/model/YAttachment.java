package com.yichang.uep.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the y_attachment database table.
 * 
 */
@Entity
@Table(name="y_attachment")
@NamedQuery(name="YAttachment.findAll", query="SELECT y FROM YAttachment y")
public class YAttachment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="att_id")
	private int attId;

	@Column(name="att_uri")
	private String attUri;

	@Column(name="event_id")
	private int eventId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="up_time")
	private Date upTime;

	@Column(name="up_user")
	private String upUser;

	public YAttachment() {
	}

	public int getAttId() {
		return this.attId;
	}

	public void setAttId(int attId) {
		this.attId = attId;
	}

	public String getAttUri() {
		return this.attUri;
	}

	public void setAttUri(String attUri) {
		this.attUri = attUri;
	}

	public int getEventId() {
		return this.eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public Date getUpTime() {
		return this.upTime;
	}

	public void setUpTime(Date upTime) {
		this.upTime = upTime;
	}

	public String getUpUser() {
		return this.upUser;
	}

	public void setUpUser(String upUser) {
		this.upUser = upUser;
	}

}