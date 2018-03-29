package com.yichang.uep.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the y_org database table.
 * 
 */
@Entity
@Table(name="y_org")
@NamedQuery(name="YOrg.findAll", query="SELECT y FROM YOrg y")
public class YOrg implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="org_id")
	private int orgId;

	@Column(name="org_name")
	private String orgName;

	@Column(name="org_parent_id")
	private int orgParentId;

	public YOrg() {
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

	public int getOrgParentId() {
		return this.orgParentId;
	}

	public void setOrgParentId(int orgParentId) {
		this.orgParentId = orgParentId;
	}

}