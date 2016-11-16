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
 * <p>共通字典实体类。<p>
 * 
 * 创建日期 2015年10月26日<br>
 * @author wangqiang<br>
 */
@Entity
@Table(name = "face_camera_to_user")
public class CameraToUserModel extends AbstractModel {

	private static final long serialVersionUID = 6274392309460978865L;

	//ID
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "cameraSourceId")
	private String cameraSourceId;

	@Column(name = "userId")
	private Integer userId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCameraSourceId() {
		return cameraSourceId;
	}

	public void setCameraSourceId(String cameraSourceId) {
		this.cameraSourceId = cameraSourceId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	

}
