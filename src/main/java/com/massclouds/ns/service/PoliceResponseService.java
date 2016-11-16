/*
 * Copyright (c) MASSCLOUDS 2013 All Rights Reserved
 */
package com.massclouds.ns.service;

import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONObject;
import com.massclouds.ns.model.PoliceCompanyModel;
import com.massclouds.ns.model.PoliceResponseModel;

/**
 * <p>
 * 出警审核单服务接口
 * <p>
 * 
 * 创建日期 2016年6月29日<br>
 * 
 * @author xielipeng<br>
 */
public interface PoliceResponseService extends BaseService<PoliceResponseModel> {

	// add
	public String savePoliceResponse(PoliceResponseModel prm);
	//查询(byhitId);
	public String getPoliceResponseByHitId(Integer hid);
	//查询时间区间内审核总量和未审核总量
	public JSONObject getAuditTotalAndNotAuditTotalByTimeZones(String timeFrom, String timeTo);

}
