package com.yichang.uep.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the y_user database table.
 * 
 */
@Entity
@Table(name="y_user")
@NamedQuery(name="YUser.findAll", query="SELECT y FROM YUser y")
public class YUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_id")
	private int userId;

	@Column(name="org_id")
	private int orgId;

	@Column(name="org_name")
	private String orgName;

	private String pwd;

	@Column(name="real_name")
	private String realName;

	@Column(name="user_name")
	private String userName;

	public YUser() {
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getOrgId() {
		return this.orgId;
	}

	public void setOrgId(int orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return this.orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getPwd() {
		return this.pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getRealName() {
		return this.realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}