/*
 * Copyright (c) MASSCLOUDS 2013 All Rights Reserved
 */
package com.massclouds.ns.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.massclouds.ns.common.Constants;
import com.massclouds.ns.dao.BaseDao;
import com.massclouds.ns.model.PoliceCompanyModel;
import com.massclouds.ns.model.PoliceResponseModel;
import com.massclouds.ns.model.PolicemanModel;
import com.massclouds.ns.service.PoliceCompanyService;
import com.massclouds.ns.service.PoliceResponseService;
import com.massclouds.ns.util.DateUtil;
import com.massclouds.ns.util.WebServiceServe;

/**
 * <p>
 * 出警审核单服务实现类
 * <p>
 * 
 * 创建日期 2016年6月29日<br>
 * 
 * @author xielipeng<br>
 */

@Service("PoliceResponseService")
public class PoliceResponseServiceImpl extends
		BaseServiceImpl<PoliceResponseModel> implements PoliceResponseService {
	
	@Autowired
	PoliceCompanyService policeCompanyBase;
	
	@Autowired
	BaseDao<PolicemanModel> policemanBase;
	
	//添加
	@Override
	public String savePoliceResponse(PoliceResponseModel prm) {
		try {
			this.save(prm);
			return "{\"statu\":\"success\",\"msg\": \"成功\"}";
		} catch (Exception e) {
			return "{\"statu\":\"error\",\"msg\": \"失败\"}";
		}

	}
	
	//查询(byhitId);
	public String getPoliceResponseByHitId(Integer hid){
		String hql = "from PoliceResponseModel where hitId = "+hid;
		PoliceResponseModel prm = this.getByHql(hql);
		JSONObject jb  = (JSONObject) JSONObject.toJSON(prm);
		if(prm != null){
			Integer cid = prm.getResponseCompanyId();
			Integer pid = prm.getResponsePolicemanId();
			Integer aid = prm.getAuditPolicemanId();
			try {
				if(cid!=null && pid!=null){
					PolicemanModel pmp = policemanBase.getByHql("from PolicemanModel where id="+pid);
					jb.put("responsePolicemanName", pmp.getName());
					
					PoliceCompanyModel pcm = policeCompanyBase.getByHql("from PoliceCompanyModel where id="+cid);
					jb.put("responseCompanyName",pcm.getName());
				}
				PolicemanModel pma = policemanBase.getByHql("from PolicemanModel where id="+aid);
				jb.put("auditPolicemanName", pma.getName());
			} catch (Exception e) {
				e.printStackTrace();	
				return jb.toJSONString();
			}
			
		}else{
			return JSON.toJSONString(prm);
		}
		return jb.toJSONString();
		
	}
	
	
	//查询时间区间内审核总量和未审核总量
	@Override
	public JSONObject getAuditTotalAndNotAuditTotalByTimeZones(String timeFrom,
			String timeTo) {
		try {
			//数据总量
			Integer hitTotal = WebServiceServe.getObj().GetHitRecordTotal("admin", timeFrom, timeTo, "", "");
			//审核总量
			String hql  = "select count(*) From PoliceResponseModel where hitTime  between "+DateUtil.date2TimeStamp(timeFrom,"yyyy-MM-dd HH:mm:ss")+" and "+DateUtil.date2TimeStamp(timeTo,"yyyy-MM-dd HH:mm:ss");
			Long audiTotal = this.count(hql);
			
			JSONObject jb = new JSONObject();
			jb.put("hitTotal",hitTotal);
			jb.put("audiTotal",audiTotal);
			jb.put("notAudiTotal",hitTotal-audiTotal);
			
			return jb;
			
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject jb = new JSONObject();
			jb.put("hitTotal",0);
			jb.put("audiTotal",0);
			jb.put("notAudiTotal",0);
			return jb;
			
		}
		
	}

}
