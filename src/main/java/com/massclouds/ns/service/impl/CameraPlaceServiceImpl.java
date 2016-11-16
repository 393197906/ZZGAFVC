/*
 * Copyright (c) MASSCLOUDS 2013 All Rights Reserved
 */
package com.massclouds.ns.service.impl;

import java.util.List;
import java.util.Map;

import org.aspectj.weaver.ast.Var;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.massclouds.ns.dao.BaseDao;
import com.massclouds.ns.model.CameraModel;
import com.massclouds.ns.model.CameraPlaceModel;
import com.massclouds.ns.model.CameraToUserModel;
import com.massclouds.ns.model.UserModel;
import com.massclouds.ns.service.CameraPlaceService;
import com.massclouds.ns.service.CameraService;
import com.massclouds.ns.service.CameraToUserService;
import com.massclouds.ns.service.UserService;
import com.massclouds.ns.util.WebServiceServe;

/**
 * <p>
 * 摄像机服务实现类
 * <p>
 * 创建日期 2016年5月5日<br>
 * 
 * @author xielipeng<br>
 */

@Service("CameraPlaceService")
public class CameraPlaceServiceImpl extends BaseServiceImpl<CameraPlaceModel>
		implements CameraPlaceService {
	@Autowired
	CameraService cameraModel;

	@Autowired
	UserService userBase;

	@Autowired
	CameraToUserService ctuBase;

	@Override
	// 获取地点及摄像机列表
	public String getCameraList(String type) {
		String title = null;
		switch (type) {
		case "list":
			title = "设备列表";
			break;
		case "chose":
			title = "全部设备";
			break;
		}
		try {
			List<CameraPlaceModel> placeList = this.find(); // 地点list
			// 声明根json数组
			JSONArray rootArray = new JSONArray();
			// 声明根对象
			JSONObject rootObject = new JSONObject();
			rootObject.put("id", 0);
			rootObject.put("text", title);
			rootObject.put("iconCls", "icon-application_view_list");

			// 声明地点json数组
			JSONArray placeArray = new JSONArray();
			for (int i = 0; i < placeList.size(); i++) {
				JSONObject placeObject = new JSONObject();
				
				// 获取地点下携摄像机list
				String hql2 = "from CameraModel where CameraPlaceId = "
						+ placeList.get(i).getPlaceId();
				List<CameraModel> cameraList = cameraModel.find(hql2);
				
				//判断显示模式  list 全部显示 包括地点 ，size>0 显示有摄像头的地点及下属摄像机（chose模式）
				if (type.equals("list") || cameraList.size()>0) {
					placeObject.put("id", placeList.get(i).getPlaceId());
					placeObject.put("text", placeList.get(i).getPlaceName());
					placeObject.put("iconCls", "icon-bullet_home");
				}  else{
					continue;
				}

				JSONArray cameraArray = new JSONArray();
				// 匹配用户下携列表
				for (int j = 0; j < cameraList.size(); j++) {
					JSONObject cameraObject = new JSONObject();
					cameraObject.put("id", cameraList.get(j).getCameraId());
					cameraObject.put("text", cameraList.get(j).getCameraName());
					cameraObject.put("iconCls", "icon-my_camera1");

					JSONObject attrObject = new JSONObject();
					attrObject.put("sourceId", cameraList.get(j)
							.getCameraSourceId());
					attrObject
							.put("ip", cameraList.get(j).getCameraIPAddress());
					attrObject
							.put("ip", cameraList.get(j).getCameraIPAddress());
					attrObject.put("url", cameraList.get(j).getCameraUrl());
					attrObject.put("user", cameraList.get(j).getCameraUser());
					attrObject.put("password", cameraList.get(j)
							.getCameraPassword());
					cameraObject.put("attributes", attrObject);

					cameraArray.add(cameraObject);

				}

				placeObject.put("children", cameraArray);
				placeArray.add(placeObject);

			}

			rootObject.put("children", placeArray);

			rootArray.add(rootObject);

			return rootArray.toJSONString();

		} catch (Exception e) {
			e.printStackTrace();
			try {
				// 声明根json数组
				JSONArray rootArray = new JSONArray();
				// 声明根对象
				JSONObject rootObject = new JSONObject();
				rootObject.put("id", 0);
				rootObject.put("text", title);
				rootObject.put("iconCls", "icon-application_view_list");
				JSONArray placeArray = new JSONArray();
				rootObject.put("children", placeArray);
				return rootArray.toJSONString();
			} catch (Exception e2) {
				e2.printStackTrace();
				return null;
			}

		}

	}

	// 获取地点及摄像机列表（按用户）
	@Override
	public String getCameraListByUser(String username, String type) {
		String title = null;
		switch (type) {
		case "list":
			title = "设备列表";
			break;
		case "chose":
			title = "全部设备";
			break;
		}
		try {
			String hql = "from UserModel where username='" + username + "'";
			UserModel user = userBase.getByHql(hql);
			List<CameraToUserModel> idList = ctuBase.getCamerasByUserId(user
					.getId()); // 用户对应摄像机

			List<CameraPlaceModel> placeList = this.find(); // 地点list

			// 声明根json数组
			JSONArray rootArray = new JSONArray();
			// 声明根对象
			JSONObject rootObject = new JSONObject();
			rootObject.put("id", 0);
			rootObject.put("text", title);
			rootObject.put("iconCls", "icon-application_view_list");

			// 声明地点json数组
			JSONArray placeArray = new JSONArray();
			for (int i = 0; i < placeList.size(); i++) {
				// 获取地点下携摄像机list
				String hql2 = "from CameraModel where CameraPlaceId = "
						+ placeList.get(i).getPlaceId();
				List<CameraModel> cameraList = cameraModel.find(hql2);

				JSONObject placeObject = new JSONObject();

//				if (type.equals("list")) {
//					placeObject.put("id", placeList.get(i).getPlaceId());
//					placeObject.put("text", placeList.get(i).getPlaceName());
//					placeObject.put("iconCls", "icon-bullet_home");
//				} else
				if (cameraList.size() > 0) { // 判断地点下有摄像机数据
					// 判断是否拥有权限
					int a = 0 ; //状态
					for (int j = 0; j < cameraList.size(); j++) {
						if(a!=0){
							break;
						}
						for (int k = 0; k < idList.size(); k++) {
							if (idList.get(k).getCameraSourceId()
									.equals(cameraList.get(j).getCameraSourceId())) {
								placeObject.put("id", placeList.get(i).getPlaceId());
								placeObject.put("text", placeList.get(i).getPlaceName());
								placeObject.put("iconCls", "icon-bullet_home");
								a++;
								break;
							}	
						}
					}
					if(a == 0){
						continue;
					}
				} else {
					continue;
				}

				JSONArray cameraArray = new JSONArray();
				// 匹配用户下携列表
				for (int j = 0; j < cameraList.size(); j++) {
					for (int k = 0; k < idList.size(); k++) {
						// sourceId 相等
						if (idList.get(k).getCameraSourceId()
								.equals(cameraList.get(j).getCameraSourceId())) {
							JSONObject cameraObject = new JSONObject();
							cameraObject.put("id", cameraList.get(j)
									.getCameraId());
							cameraObject.put("text", cameraList.get(j)
									.getCameraName());
							cameraObject.put("iconCls", "icon-my_camera1");

							JSONObject attrObject = new JSONObject();
							attrObject.put("sourceId", cameraList.get(j)
									.getCameraSourceId());
							attrObject.put("ip", cameraList.get(j)
									.getCameraIPAddress());
							attrObject.put("ip", cameraList.get(j)
									.getCameraIPAddress());
							attrObject.put("url", cameraList.get(j)
									.getCameraUrl());
							attrObject.put("user", cameraList.get(j)
									.getCameraUser());
							attrObject.put("password", cameraList.get(j)
									.getCameraPassword());
							cameraObject.put("attributes", attrObject);

							cameraArray.add(cameraObject);
							idList.remove(k);

						}
					}
				}

				placeObject.put("children", cameraArray);

				placeArray.add(placeObject);
			}

			rootObject.put("children", placeArray);

			rootArray.add(rootObject);

			return rootArray.toJSONString();

		} catch (Exception e) {
			e.printStackTrace();
			try {
				// 声明根json数组
				JSONArray rootArray = new JSONArray();
				// 声明根对象
				JSONObject rootObject = new JSONObject();
				rootObject.put("id", 0);
				rootObject.put("text", title);
				rootObject.put("iconCls", "icon-application_view_list");
				JSONArray placeArray = new JSONArray();
				rootObject.put("children", placeArray);
				return rootArray.toJSONString();
			} catch (Exception e2) {
				e2.printStackTrace();
				return null;
			}

		}
	}

	// 获取地点列表
	public String getCameraPlaceList() {
		List<CameraPlaceModel> list = this.find();
		return JSON.toJSONString(list, true);

	}

	// 获取单个地点信息
	public String getCameraPlaceInfo(Integer id) {
		String hql = "from CameraPlaceModel where placeId = " + id;
		CameraPlaceModel place = this.getByHql(hql);
		return JSON.toJSONString(place, true);
	}

	// 修改地点信息
	public String updateCameraPlace(CameraPlaceModel cp, String user) {
		try {
			if (!hasPermission(user)) {
				return "{\"statu\":\"error\",\"msg\":\"你没有权限这么做\"}";
			}
			this.update(cp);
			return "{\"statu\":\"success\",\"msg\":\"修改成功\"}";
		} catch (Exception e) {
			e.printStackTrace();
			return "{\"statu\":\"error\",\"msg\":\"修改失败\"}";
		}

	}

	// 添加地点信息
	public String saveCameraPlace(CameraPlaceModel cp, String user) {
		try {
			if (!hasPermission(user)) {
				return "{\"statu\":\"error\",\"msg\":\"你没有权限这么做\"}";
			}
			this.save(cp);
			return "{\"statu\":\"success\",\"msg\": \"新地点添加成功\"}";
		} catch (Exception e) {
			e.printStackTrace();
			return "{\"statu\":\"error\",\"msg\": \"新地点添加失败\"}";
		}
	}

	// 删除地点信息
	@Override
	public String deleteCameraPlace(CameraPlaceModel cp, String user) {
		try {

			if (!hasPermission(user)) {
				return "{\"statu\":\"error\",\"msg\":\"你没有权限这么做\"}";
			}
			this.delete(cp); // 删除数据库地点信息
			// 获取地点分支下的所有摄像机信息
			String hql = "from CameraModel where CameraPlaceId = "
					+ cp.getPlaceId();
			List<CameraModel> cList = cameraModel.find(hql);

			int cou = 0;
			for (int i = 0; i < cList.size(); i++) {
				cou += cameraModel.deleteCameraAllInfo(cList.get(i)
						.getCameraId());
			}

			if (cou == 0) {
				return "{\"statu\":\"success\",\"msg\": \"地点删除成功\"}";
			} else {
				return "{\"statu\":\"error\",\"msg\": \"未完整删除\"}";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "{\"statu\":\"error\",\"msg\": \"删除失败，系统错误\"}";
		}

	}

}
