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
 * <p>公安单位实体类。<p>
 * 
 * 创建日期 2016年06月29日<br>
 * @author xielipeng<br>
 */
@Entity
@Table(name = "face_police_company")
public class PoliceCompanyModel extends AbstractModel {

	private static final long serialVersionUID = 6274392309460978865L;

	//ID
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "name")
	private String name;

	@Column(name = "parentId")
	private Integer parentId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	
	

}
