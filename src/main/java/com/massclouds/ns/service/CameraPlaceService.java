/*
 * Copyright (c) MASSCLOUDS 2013 All Rights Reserved
 */
package com.massclouds.ns.service;

import org.springframework.web.bind.annotation.RequestParam;

import com.massclouds.ns.model.CameraPlaceModel;

/**
 * <p>
 * 摄像机服务
 * <p>
 * 
 * 创建日期 2016年5月5日<br>
 * 
 * @author xielipeng<br>
 */
public interface CameraPlaceService extends BaseService<CameraPlaceModel> {
	//获取地点及摄像机列表
	public String getCameraList(String type);
	//获取地点列表
	public String getCameraPlaceList();
	//获取单个地点信息
	public String getCameraPlaceInfo(Integer id);
	//修改地点信息
	public String updateCameraPlace(CameraPlaceModel cp,String user);
	//添加地点信息
	public String saveCameraPlace(CameraPlaceModel cp,String user);
	//删除地点信息
	public String deleteCameraPlace(CameraPlaceModel cp,String user);
	//获取地点及摄像机列表(按用户)
	public String getCameraListByUser(String username,String type);
	
}
