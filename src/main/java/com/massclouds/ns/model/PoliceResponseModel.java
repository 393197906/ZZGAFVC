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
 * 警情处理实体类。
 * <p>
 * 
 * 创建日期 2016年06月29日<br>
 * 
 * @author xielipeng<br>
 */
@Entity
@Table(name = "face_police_response")
public class PoliceResponseModel extends AbstractModel {

	private static final long serialVersionUID = 6274392309460978865L;

	// ID
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	// 审核民警id
	@Column(name = "auditPolicemanId")
	private Integer auditPolicemanId;

	// 审核时间
	@Column(name = "auditTime")
	private String auditTime;

	// 出警民警id
	@Column(name = "responsePolicemanId")
	private Integer responsePolicemanId;

	// 出警时间
	@Column(name = "responseTime")
	private String responseTime;

	// 出警单位id
	@Column(name = "responseCompanyId")
	private Integer responseCompanyId;
	
	// 出警事由
	@Column(name = "responseReason")
	private String responseReason;
	
	// 出警结果
	@Column(name = "responseResult")
	private String responseResult;
	
	// 报警信息id(外键)
	@Column(name = "hitId")
	private Integer hitId;

	//报警时间
	@Column(name = "hitTime")
	private Long hitTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAuditPolicemanId() {
		return auditPolicemanId;
	}

	public void setAuditPolicemanId(Integer auditPolicemanId) {
		this.auditPolicemanId = auditPolicemanId;
	}

	public String getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(String auditTime) {
		this.auditTime = auditTime;
	}

	public Integer getResponsePolicemanId() {
		return responsePolicemanId;
	}

	public void setResponsePolicemanId(Integer responsePolicemanId) {
		this.responsePolicemanId = responsePolicemanId;
	}

	public String getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(String responseTime) {
		this.responseTime = responseTime;
	}

	public Integer getResponseCompanyId() {
		return responseCompanyId;
	}

	public void setResponseCompanyId(Integer responseCompanyId) {
		this.responseCompanyId = responseCompanyId;
	}

	public String getResponseReason() {
		return responseReason;
	}

	public void setResponseReason(String responseReason) {
		this.responseReason = responseReason;
	}

	public String getResponseResult() {
		return responseResult;
	}

	public void setResponseResult(String responseResult) {
		this.responseResult = responseResult;
	}

	public Integer getHitId() {
		return hitId;
	}

	public void setHitId(Integer hitId) {
		this.hitId = hitId;
	}

	public Long getHitTime() {
		return hitTime;
	}

	public void setHitTime(Long hitTime) {
		this.hitTime = hitTime;
	}
	
	
	
}
