/*
 * Copyright (c) MASSCLOUDS 2013 All Rights Reserved
 */
package com.massclouds.ns.service.impl;

import java.io.Serializable;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.massclouds.ns.dao.BaseDao;
import com.massclouds.ns.model.CameraModel;
import com.massclouds.ns.model.CameraPlaceModel;
import com.massclouds.ns.model.CameraToUserModel;
import com.massclouds.ns.model.UserGroupModel;
import com.massclouds.ns.model.UserModel;
import com.massclouds.ns.service.BaseService;
import com.massclouds.ns.service.CameraService;
import com.massclouds.ns.service.CameraToUserService;
import com.massclouds.ns.service.UserGroupService;
import com.massclouds.ns.service.UserService;

/**
 * <p>
 * 用户服务实现类
 * <p>
 * 
 * 创建日期 2016年5月27日<br>
 * 
 * @author xielipeng<br>
 */

@Service("CameraToUserService")
public class CameraToUserServiceImpl extends BaseServiceImpl<CameraToUserModel>
		implements CameraToUserService {
	
	//取用户对应的摄像机
	@Override
	public List<CameraToUserModel> getCamerasByUserId(Integer id) {
		try {
		String hql = "from CameraToUserModel where userId="+id;
		List<CameraToUserModel> list = this.find(hql);
		return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//取摄像机对应的用户
	@Override
	public List<CameraToUserModel> getUsersByCameraId(Integer id) {
		String hql = "from CameraToUserModel where cameraId="+id;
		List<CameraToUserModel> list = this.find(hql);
		return list;
	}

 }
