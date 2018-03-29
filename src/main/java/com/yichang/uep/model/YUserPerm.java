package com.yichang.uep.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the y_user_perm database table.
 * 
 */
@Entity
@Table(name="y_user_perm")
@NamedQuery(name="YUserPerm.findAll", query="SELECT y FROM YUserPerm y")
public class YUserPerm implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="perm_id")
	private int permId;

	@Column(name="perm_comment")
	private String permComment;

	private String permcode;

	@Column(name="user_id")
	private int userId;

	public YUserPerm() {
	}

	public int getPermId() {
		return this.permId;
	}

	public void setPermId(int permId) {
		this.permId = permId;
	}

	public String getPermComment() {
		return this.permComment;
	}

	public void setPermComment(String permComment) {
		this.permComment = permComment;
	}

	public String getPermcode() {
		return this.permcode;
	}

	public void setPermcode(String permcode) {
		this.permcode = permcode;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}