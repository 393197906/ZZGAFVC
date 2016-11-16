/*
 * Copyright (c) MASSCLOUDS 2013 All Rights Reserved
 */
package com.massclouds.ns.service;

import org.springframework.web.bind.annotation.RequestParam;

import com.massclouds.ns.model.CameraModel;

/**
 * <p>
 * 摄像机服务
 * <p>
 * 
 * 创建日期 2016年5月5日<br>
 * 
 * @author xielipeng<br>
 */
public interface CameraService extends BaseService<CameraModel> {
	//获取单个摄像机信息
	public String getCameraInfo(Integer id);
	//获取单个摄像机信息
	public CameraModel getCameraInfo(String sourceid);
	//修改摄像机信息
	public String updateCamera(CameraModel cm,String user);
	//添加摄像机信息
	public String saveCamera(CameraModel cm,String user);
	//删除摄像机信息
	public String deleteCamera(Integer cameraId,String user);
	
	//获取摄像机三天内的报警数量信息
	public String getThreeDayAlertTotal(String user);
	//获取摄像机三天内的抓拍数量信息
	public String getThreeDayCaptureTotal(String user);
	// 删除摄像机相关的所有信息（包含用户对应）
	public int deleteCameraAllInfo(Integer cameraId);

}
