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
 * <p>摄像机地点实体类。<p>
 * 
 * 创建日期 2016年5月5日<br>
 * @author xielipeng<br>
 */


@Entity
@Table(name = "face_camera_place")
public class CameraPlaceModel extends AbstractModel {

	private static final long serialVersionUID = 6274392309460978865L;

	//ID
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name = "placeId")
	private Integer placeId;

	@Column(name = "placeName")
	private String placeName;
	
	@Column(name = "parentId")
	private Integer parentId;

	public Integer getPlaceId() {
		return placeId;
	}

	public void setPlaceId(Integer placeId) {
		this.placeId = placeId;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	
	

}
