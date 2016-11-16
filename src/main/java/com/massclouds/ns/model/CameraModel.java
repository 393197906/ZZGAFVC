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
 * <p>摄像机实体类。<p>
 * 
 * 创建日期 2016年5月5日<br>
 * @author xielipeng<br>
 */
@Entity
@Table(name = "face_camera")
public class CameraModel extends AbstractModel {

	
	private static final long serialVersionUID = 1850501124784229133L;

	//ID
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name = "CameraId")
	private Integer CameraId;

	@Column(name = "CameraName")
	private String CameraName;
	
	@Column(name = "CameraIPAddress")
	private String CameraIPAddress;
	
	@Column(name = "CameraPlaceId")
	private Integer CameraPlaceId;
	
	@Column(name = "PersonType")
	private String PersonType;
	
	@Column(name = "CameraUser")
	private String CameraUser;
	
	@Column(name = "CameraPassword")
	private String CameraPassword;
	
	@Column(name = "CameraSourceId")
	private String CameraSourceId;
	
	@Column(name = "CameraUrl")
	private String CameraUrl;

	public Integer getCameraId() {
		return CameraId;
	}

	public void setCameraId(Integer cameraId) {
		CameraId = cameraId;
	}

	public String getCameraName() {
		return CameraName;
	}

	public void setCameraName(String cameraName) {
		CameraName = cameraName;
	}

	public String getCameraIPAddress() {
		return CameraIPAddress;
	}

	public void setCameraIPAddress(String cameraIPAddress) {
		CameraIPAddress = cameraIPAddress;
	}

	public Integer getCameraPlaceId() {
		return CameraPlaceId;
	}

	public void setCameraPlaceId(Integer cameraPlaceId) {
		CameraPlaceId = cameraPlaceId;
	}

	public String getPersonType() {
		return PersonType;
	}

	public void setPersonType(String personType) {
		PersonType = personType;
	}

	public String getCameraUser() {
		return CameraUser;
	}

	public void setCameraUser(String cameraUser) {
		CameraUser = cameraUser;
	}

	public String getCameraPassword() {
		return CameraPassword;
	}

	public void setCameraPassword(String cameraPassword) {
		CameraPassword = cameraPassword;
	}

	public String getCameraSourceId() {
		return CameraSourceId;
	}

	public void setCameraSourceId(String cameraSourceId) {
		CameraSourceId = cameraSourceId;
	}

	public String getCameraUrl() {
		return CameraUrl;
	}

	public void setCameraUrl(String cameraUrl) {
		CameraUrl = cameraUrl;
	}
	
	

}
