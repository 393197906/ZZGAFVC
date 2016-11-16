/*
 * Copyright (c) MASSCLOUDS 2013 All Rights Reserved
 */
package com.massclouds.ns.service;

import java.util.List;

import com.massclouds.ns.model.CameraToUserModel;
import com.massclouds.ns.model.UserGroupModel;
import com.massclouds.ns.model.UserModel;

/**
 * <p>
 * 用户服务接口
 * <p>
 * 
 * 创建日期 2016年5月27日<br>
 * 
 * @author xielipeng<br>
 */
public interface CameraToUserService extends BaseService<CameraToUserModel> {
	public List<CameraToUserModel> getCamerasByUserId(Integer id);
	public List<CameraToUserModel> getUsersByCameraId(Integer id);
}
