/*
 * Copyright (c) MASSCLOUDS 2013 All Rights Reserved
 */
package com.massclouds.ns.service;

import org.springframework.web.bind.annotation.RequestParam;

import com.massclouds.ns.model.PoliceCompanyModel;

/**
 * <p>
 * 公安单位服务接口
 * <p>
 * 
 * 创建日期 2016年6月29日<br>
 * 
 * @author xielipeng<br>
 */
public interface PoliceCompanyService extends BaseService<PoliceCompanyModel> {
	//单位列表 （递归）
	public String companyList();
	
	public String policemanListByCompany(Integer cid);

}
