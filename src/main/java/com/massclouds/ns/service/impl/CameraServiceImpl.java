/*
 * Copyright (c) MASSCLOUDS 2013 All Rights Reserved
 */
package com.massclouds.ns.service.impl;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.massclouds.ns.common.Constants;
import com.massclouds.ns.model.CameraModel;
import com.massclouds.ns.model.CameraToUserModel;
import com.massclouds.ns.model.UserModel;
import com.massclouds.ns.service.CameraPlaceService;
import com.massclouds.ns.service.CameraService;
import com.massclouds.ns.service.CameraToUserService;
import com.massclouds.ns.service.UserService;
import com.massclouds.ns.util.DateUtil;
import com.massclouds.ns.util.JsonHelper;
import com.massclouds.ns.util.WebServiceServe;

/**
 * <p>
 * 摄像机服务实现类
 * <p>
 * 
 * 创建日期 2016年5月5日<br>
 * 
 * @author xielipeng<br>
 */

@Service("CameraService")
public class CameraServiceImpl extends BaseServiceImpl<CameraModel> implements
		CameraService {

	// 用户摄像机映射关系表服务
	@Autowired
	private CameraToUserService ctu;
	// 用户表服务
	@Autowired
	private UserService userBase;

	// 获取单个摄像机信息
	@Override
	public String getCameraInfo(Integer id) {
		String hql = "from CameraModel where CameraId = " + id;
		CameraModel camera = this.getByHql(hql);
		return JSON.toJSONString(camera, true);
	}

	// 获取单个摄像机信息
	@Override
	public CameraModel getCameraInfo(String sourceid) {
		String hql = "from CameraModel where CameraSourceId ='" + sourceid
				+ "'";
		CameraModel camera = this.getByHql(hql);
		return camera;
	}

	// 修改摄像机信息
	@Override
	public String updateCamera(CameraModel cm, String user) {
		if (!hasPermission(user)) {
			return "{\"statu\":\"error\",\"msg\":\"你没有权限这么做\"}";
		}
		this.update(cm);
		return "{\"statu\":\"success\",\"msg\":\"修改成功\"}";
	}

	// 添加摄像机信息
	@Override
	public String saveCamera(CameraModel cm, String user) {
		if (!hasPermission(user)) {
			return "{\"statu\":\"error\",\"msg\":\"你没有权限这么做\"}";
		}
		try {
			// 判断摄像机是否已存在
			CameraModel isHaveCamera = this
					.getByHql("from CameraModel where CameraSourceId = '"
							+ cm.getCameraSourceId() + "'");
			if (isHaveCamera != null) {
				return "{\"statu\":\"error\",\"msg\": \"设备添加失败,sourceId已存在\"}";
			}

			int statu = WebServiceServe.getObj().AddCamerasOfUser(user,
					cm.getCameraSourceId());
			this.save(cm);
			return "{\"statu\":\"success\",\"msg\": \"设备添加成功\"}";
		} catch (Exception e) {
			e.printStackTrace();
			return "{\"statu\":\"error\",\"msg\": \"设备添加失败\"}";
		}

	}

	// 删除摄像机信息
	@Override
	public String deleteCamera(Integer cameraId, String user) {
		if (!hasPermission(user)) {
			return "{\"statu\":\"error\",\"msg\":\"你没有权限这么做\"}";
		}
		int cou = deleteCameraAllInfo(cameraId);
		if (cou == 0) {
			return "{\"statu\":\"success\",\"msg\": \"设备删除成功\"}";
		} else {
			return "{\"statu\":\"error\",\"msg\": \"设备删除失败\"}";
		}
	}

	// 删除摄像机相关的所有信息（包含用户对应）
	public int deleteCameraAllInfo(Integer cameraId) {
		try {

			// 根据id获取camera实体对象
			CameraModel cm = this.getByHql("from CameraModel where CameraId = "
					+ cameraId);
			int statuAdmin = WebServiceServe.getObj().DeleteCamerasOfUser(
					"admin", cm.getCameraSourceId());
			this.delete(cm);// 删除数据库摄像机信息

			// 删除webservice端所有信息
			String hql = "from CameraToUserModel where cameraSourceId = '"
					+ cm.getCameraSourceId() + "'";
			List<CameraToUserModel> ctuList = ctu.find(hql);
			String[] usernameArr = new String[ctuList.size()]; // 用户名数组
			for (int i = 0; i < ctuList.size(); i++) {
				String uhql = "from UserModel where id ="
						+ ctuList.get(i).getUserId();
				UserModel userm = userBase.getByHql(uhql);
				usernameArr[i] = userm.getUsername();
			}

			int cou = 0;
			for (int j = 0; j < usernameArr.length; j++) {
				System.out.println(usernameArr[j] + "@");
				int statu = WebServiceServe.getObj().DeleteCamerasOfUser(
						usernameArr[j], cm.getCameraSourceId());
				cou += statu;
			}
			cou += statuAdmin;
			// 删除用户=》摄像机映射表的所有数据
			String sql = "delete from face_camera_to_user where cameraSourceId = '"
					+ cm.getCameraSourceId() + "' or cameraSourceId = '-1'";
			this.executeSql(sql);
			return cou;

		} catch (Exception e) {
			e.printStackTrace();
			return -100;
		}
	}

	// 获取摄像机三天内的报警数量信息
	@Override
	public String getThreeDayAlertTotal(String user) {
		Date date = new Date();
		String todayTo = new SimpleDateFormat(Constants.FORMAT_DATE_YMDHMS)
				.format(date);
		String todayFrom = new SimpleDateFormat("yyyy-MM-dd").format(date)
				+ " 00:00:00";
		String timeString = DateUtil.date2TimeStamp(todayFrom,
				Constants.FORMAT_DATE_YMDHMS);
		String yesterdayFrom = DateUtil.timeStamp2Date(
				(Long.parseLong(timeString) - 24 * 3600) + "",
				Constants.FORMAT_DATE_YMDHMS);
		String threedayFrom = DateUtil.timeStamp2Date(
				(Long.parseLong(timeString) - 2 * 24 * 3600) + "",
				Constants.FORMAT_DATE_YMDHMS);

		// 取得三天数据 json字符串
		String today = toCameraNameJson(WebServiceServe.getObj()
				.GetTotalAlertOfCamera(user, todayFrom, todayTo));
		String yesterday = toCameraNameJson(WebServiceServe.getObj()
				.GetTotalAlertOfCamera(user, yesterdayFrom, todayFrom));
		String beforeYesterday = toCameraNameJson(WebServiceServe.getObj()
				.GetTotalAlertOfCamera(user, threedayFrom, yesterdayFrom));
		return "{\"today\":" + today + ",\"yesterday\":" + yesterday
				+ ",\"beforeYesterday\":" + beforeYesterday + "}";
	}

	// 获取摄像机三天内的报警数量信息
	@Override
	public String getThreeDayCaptureTotal(String user) {
		Date date = new Date();
		String todayTo = new SimpleDateFormat(Constants.FORMAT_DATE_YMDHMS)
				.format(date);
		String todayFrom = new SimpleDateFormat("yyyy-MM-dd").format(date)
				+ " 00:00:00";
		String timeString = DateUtil.date2TimeStamp(todayFrom,
				Constants.FORMAT_DATE_YMDHMS);
		String yesterdayFrom = DateUtil.timeStamp2Date(
				(Long.parseLong(timeString) - 24 * 3600) + "",
				Constants.FORMAT_DATE_YMDHMS);
		String threedayFrom = DateUtil.timeStamp2Date(
				(Long.parseLong(timeString) - 2 * 24 * 3600) + "",
				Constants.FORMAT_DATE_YMDHMS);
		// 取得三天数据 json字符串
		String today = toCameraNameJson(WebServiceServe.getObj()
				.GetTotalCaptureOfUser(user, todayFrom, todayTo));
		String yesterday = toCameraNameJson(WebServiceServe.getObj()
				.GetTotalCaptureOfUser(user, yesterdayFrom, todayFrom));
		String beforeYesterday = toCameraNameJson(WebServiceServe.getObj()
				.GetTotalCaptureOfUser(user, threedayFrom, yesterdayFrom));
		return "{\"today\":" + today + ",\"yesterday\":" + yesterday
				+ ",\"beforeYesterday\":" + beforeYesterday + "}";
	}

	// 转换成cameraName json
	private String toCameraNameJson(String string) {
		try {
			if (string.equals("{}")) {
				return "[]";
			}
			Map<String, String> map = JsonHelper.toMap(string);
			String str = "[";
			for (Map.Entry<String, String> entry : map.entrySet()) {
				str += "{";
				try {
					CameraModel camera = getCameraInfo(entry.getKey());
					str += "\"cameraName\":\"" + camera.getCameraName() + "\",";
				} catch (Exception e) {
					str += "\"cameraName\":\"未知\",";
				}
				str += "\"count\":\"" + entry.getValue() + "\"";
				str += "},";
			}
			str = str.substring(0, str.length() - 1);
			str += "]";
			return str;
		} catch (Exception e) {
			// e.printStackTrace();
			return "[]";
		}
	}

}
