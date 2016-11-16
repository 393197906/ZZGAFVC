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
 * 用户登陆实体类。
 * <p>
 * 
 * 创建日期 2016年6月2日<br>
 * 
 * @author xielipeng<br>
 */
@Entity
@Table(name = "face_user_login")
public class UserLoginModel extends AbstractModel {

	private static final long serialVersionUID = 6274392309460978865L;

	// ID
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "username")
	private String username;

	@Column(name = "loginTime")
	private Long loginTime;
	
	@Column(name = "loginDay")
	private Long loginDay;

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

	public Long getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Long loginTime) {
		this.loginTime = loginTime;
	}

	public Long getLoginDay() {
		return loginDay;
	}

	public void setLoginDay(Long loginDay) {
		this.loginDay = loginDay;
	}

	


}
