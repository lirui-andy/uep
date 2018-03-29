package com.yichang.uep.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the y_const database table.
 * 
 */
@Entity
@Table(name="y_const")
@NamedQuery(name="YConst.findAll", query="SELECT y FROM YConst y")
public class YConst implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="const_id")
	private int constId;

	@Column(name="active_flag")
	private short activeFlag;

	@Column(name="const_code")
	private String constCode;

	@Column(name="const_comment")
	private String constComment;

	@Column(name="const_group")
	private String constGroup;

	@Column(name="const_name")
	private String constName;

	public YConst(short activeFlag, String constGroup, String constCode, String constName) {
		super();
		this.activeFlag = activeFlag;
		this.constCode = constCode;
		this.constGroup = constGroup;
		this.constName = constName;
	}

	public YConst() {
	}

	public int getConstId() {
		return this.constId;
	}

	public void setConstId(int constId) {
		this.constId = constId;
	}

	public short getActiveFlag() {
		return this.activeFlag;
	}

	public void setActiveFlag(short activeFlag) {
		this.activeFlag = activeFlag;
	}

	public String getConstCode() {
		return this.constCode;
	}

	public void setConstCode(String constCode) {
		this.constCode = constCode;
	}

	public String getConstComment() {
		return this.constComment;
	}

	public void setConstComment(String constComment) {
		this.constComment = constComment;
	}

	public String getConstGroup() {
		return this.constGroup;
	}

	public void setConstGroup(String constGroup) {
		this.constGroup = constGroup;
	}

	public String getConstName() {
		return this.constName;
	}

	public void setConstName(String constName) {
		this.constName = constName;
	}

}