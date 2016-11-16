/*
 * Copyright (c) MASSCLOUDS 2013 All Rights Reserved
 */
package com.massclouds.ns.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p>
 * 共通字典实体类。
 * <p>
 * 
 * 创建日期 2015年10月26日<br>
 * 
 * @author wangqiang<br>
 */
@Entity
@Table(name = "face_user")
public class UserModel extends AbstractModel {

	private static final long serialVersionUID = 6274392309460978865L;

	// ID
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	@Column(name = "permission")
	private Integer permission;

	@Column(name = "ofGroupId")
	private Integer ofGroupId;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getPermission() {
		return permission;
	}

	public void setPermission(Integer permission) {
		this.permission = permission;
	}

	public Integer getOfGroupId() {
		return ofGroupId;
	}

	public void setOfGroupId(Integer ofGroupId) {
		this.ofGroupId = ofGroupId;
	}


}
