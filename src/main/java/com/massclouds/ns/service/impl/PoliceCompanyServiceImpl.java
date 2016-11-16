/*
 * Copyright (c) MASSCLOUDS 2013 All Rights Reserved
 */
package com.massclouds.ns.service.impl;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.enterprise.inject.New;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.massclouds.ns.dao.BaseDao;
import com.massclouds.ns.model.PoliceCompanyModel;
import com.massclouds.ns.model.PolicemanModel;
import com.massclouds.ns.service.PoliceCompanyService;

/**
 * <p>
 * 公安单位服务实现类
 * <p>
 * 
 * 创建日期 2016年6月29日<br>
 * 
 * @author xielipeng<br>
 */

@Service("PoliceCompanyService")
public class PoliceCompanyServiceImpl extends BaseServiceImpl<PoliceCompanyModel> implements
PoliceCompanyService {
	
	@Autowired
	BaseDao<PolicemanModel> policemanBase;
 	
	//单位列表
	@Override
	public String companyList() {
		JSONArray array = this.recursionCompany(0);
		return JSON.toJSONString(array,true);
	}
	
 	private JSONArray recursionCompany(Integer pid){
		String hql  = "from PoliceCompanyModel where parentId = "+pid;
		List<PoliceCompanyModel> list = this.find(hql);
		
		JSONArray joa = new JSONArray();
		for(int i = 0 ; i <list.size();i++){
			JSONObject jo = new JSONObject();
			jo.put("id", list.get(i).getId());
			jo.put("text", list.get(i).getName());
			jo.put("iconCls","icon-bullet_home");
			jo.put("children",recursionCompany(list.get(i).getId()));
			joa.add(jo);
		}
		return joa;
	}
 	
 	
 	//取得警员列表
	@Override
	public String policemanListByCompany(Integer cid) {
		String hql = " from PolicemanModel where companyId = "+cid;
		List<PolicemanModel> list = policemanBase.find(hql);
		JSONArray array  = new JSONArray();
		for(int i =0;i<list.size();i++){
			JSONObject jo = new JSONObject();
			jo.put("id",list.get(i).getId());
			jo.put("text",list.get(i).getName());
			array.add(jo);
		}
		return JSON.toJSONString(array,true);
	}
 	
 	
	

}
