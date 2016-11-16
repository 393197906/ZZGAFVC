/*
 * Copyright (c) MASSCLOUDS 2013 All Rights Reserved
 */
package com.massclouds.ns.service.impl;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.From;
import javax.resource.spi.AdministeredObject;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tempuri1.FRSServiceStub.FaceLibraryInfo;

import com.alibaba.fastjson.JSON;
import com.massclouds.ns.dao.BaseDao;
import com.massclouds.ns.model.CameraModel;
import com.massclouds.ns.model.CameraPlaceModel;
import com.massclouds.ns.model.CameraToUserModel;
import com.massclouds.ns.model.UserGroupModel;
import com.massclouds.ns.model.UserLoginModel;
import com.massclouds.ns.model.UserModel;
import com.massclouds.ns.service.BaseService;
import com.massclouds.ns.service.CameraPlaceService;
import com.massclouds.ns.service.CameraService;
import com.massclouds.ns.service.CameraToUserService;
import com.massclouds.ns.service.UserGroupService;
import com.massclouds.ns.service.UserService;
import com.massclouds.ns.util.DateUtil;
import com.massclouds.ns.util.JsonHelper;
import com.massclouds.ns.util.WebServiceServe;

/**
 * <p>
 * 用户服务实现类
 * <p>
 * 
 * 创建日期 2016年5月27日<br>
 * 
 * @author xielipeng<br>
 */

@Service("UserService")
public class UserServiceImpl extends BaseServiceImpl<UserModel> implements
		UserService {

	@Autowired
	UserGroupService UserGroupBase;

	@Autowired
	CameraToUserService ctouBase;

	@Autowired
	CameraPlaceService CameraPlaceBase;

	@Autowired
	CameraService CameraBase;

	@Autowired
	BaseDao<UserLoginModel> UserLoginBase;

	// 登陆
	@Override
	public Boolean login(String username, String password) {
		String hql = "select count(id) from UserModel where username = '"
				+ username + "' and password = '" + password + "'";
		long cou = this.count(hql);
		if (cou > 0) {
			// 写入登陆信息
			UserLoginModel ul = new UserLoginModel();
			ul.setUsername(username);
			System.out.println("login" + Long.parseLong(DateUtil.timeStamp()));
			ul.setLoginTime(Long.parseLong(DateUtil.timeStamp()));
			String todayFrom = new SimpleDateFormat("yyyy-MM-dd")
					.format(new Date()) + " 00:00:00";
			System.out.println(todayFrom);
			String timeString = DateUtil.date2TimeStamp(todayFrom,
					"yyyy-MM-dd HH:mm:ss");
			ul.setLoginDay(Long.parseLong(timeString) + 24 * 3600);
			UserLoginBase.save(ul);
			return true;
		}

		return false;

	}

	// 用户列表
	@Override
	public String userList() {
		List<UserGroupModel> ugList = UserGroupBase.find();
		String str = "[{\"id\":0,\"text\":\"admin\",\"iconCls\":\"icon-user_gray\",\"children\":[";
		for (int i = 0; i < ugList.size(); i++) {
			// 拼接父字符串
			str += "{";
			str += "\"id\":" + ugList.get(i).getGroupId() + ",";
			str += "\"text\":\"" + ugList.get(i).getGroupName() + "\",";
			str += "\"iconCls\":\"icon-group\","; // 图标
			str += "\"children\":[";

			// 拼接子字符串
			String hql = "from UserModel where ofGroupId = "
					+ ugList.get(i).getGroupId();
			List<UserModel> uList = this.find(hql);
			for (int j = 0; j < uList.size(); j++) {
				str += "{";
				str += "\"id\":" + uList.get(j).getId() + ",";
				str += "\"text\":\"" + uList.get(j).getUsername() + "\",";
				str += "\"iconCls\":\"icon-user\","; // 图标
				str += "\"attributes\":{\"zw\":\"zw\"}"; // 图标
				if (j == uList.size() - 1) {
					str += "}";
				} else {
					str += "},";
				}
			}
			if (i == ugList.size() - 1) {
				str += "]}";
			} else {
				str += "]},";
			}
		}
		str += "]}]";

		return str;
	}

	// 单个用户信息byId
	@Override
	public String userInfo(Integer id) {
		String hql = "from UserModel where id = " + id;
		UserModel user = this.getByHql(hql);
		return JSON.toJSONString(user, true);
	}

	// 编辑用户
	@Override
	public String updateUser(UserModel um, String user) {
		if (!hasPermission(user)) {
			return "{\"statu\":\"error\",\"msg\":\"你没有权限这么做\"}";
		}
		this.update(um);
		return "{\"statu\":\"success\",\"msg\":\"修改成功\"}";
	}

	// 添加用户
	@Override
	public String addUser(UserModel um, String user) {
		if (!hasPermission(user)) {
			return "{\"statu\":\"error\",\"msg\":\"你没有权限这么做\"}";
		}
		UserModel hasUser = this.getByHql("from UserModel where username='"
				+ um.getUsername() + "'");
		if (hasUser != null) {
			return "{\"statu\":\"error\",\"msg\": \"新用户添加失败,用户已存在\"}";
		}
		Integer st = (Integer) this.save(um);
		if (st > 0) {
			return "{\"statu\":\"success\",\"msg\": \"新用户添加成功\"}";
		} else {
			return "{\"statu\":\"error\",\"msg\": \"新用户添加失败\"}";
		}

	}

	// 删除用户
	@Override
	public String deleteUser(UserModel um, String user) {
		if (!hasPermission(user)) {
			return "{\"statu\":\"error\",\"msg\":\"你没有权限这么做\"}";
		}
		try {
			UserModel duser = this.getByHql("from UserModel where id = "
					+ um.getId());
			int result = WebServiceServe.getObj().DeleteUserIncCamsLibs(
					duser.getUsername());
			if (result == 0) {
				this.delete(duser);
				String sql = "delete from face_camera_to_user where userId = "
						+ duser.getId();
				ctouBase.executeSql(sql);
				return "{\"statu\":\"success\",\"msg\": \"用户删除成功\"}";
			} else {
				return "{\"statu\":\"error\",\"msg\": \"用户删除失败\"}";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "{\"statu\":\"error\",\"msg\": \"用户删除失败\"}";
		}
	}

	// 管理员管理用户权限 人像库列表
	@Override
	public String getPeopleBasesPermissionList(String user) {
		FaceLibraryInfo[] array = WebServiceServe.getObj().GetFaceLibraries(
				"admin");
		if (array == null) {
			return null;
		}
		FaceLibraryInfo[] checkArr = WebServiceServe.getObj().GetFaceLibraries(
				user);

		int length = array.length;
		String str = "[{\"id\":0,\"text\":\"人像库列表\",\"iconCls\":\"icon-application_view_list\",\"children\":[";
		for (int i = 0; i < length; i++) {
			str += "{\"id\":" + (i + 1) + ",\"text\":";
			str += "\"" + array[i].getName() + "\",";
			if (checkArr != null) {
				for (int j = 0; j < checkArr.length; j++) {
					if (checkArr[j].getName().equals(array[i].getName())) {
						str += "\"checked\":\"true\",";
						break;
					}
				}
			}
			str += "\"iconCls\":\"icon-database\"}"; // 图标
			if (i != length - 1) {
				str += ",";
			}
		}
		str += "]}]";
		return str;
	}

	// 管理员管理用户权限 摄像机列表
	@Override
	public String getCamerasPermissionList(Integer id) {
		List<CameraToUserModel> numList = ctouBase.getCamerasByUserId(id);

		List<CameraPlaceModel> placeList = CameraPlaceBase.find();

		String str = "[{\"id\":0,\"text\":\"设备列表\",\"iconCls\":\"icon-application_view_list\",\"children\":[";
		for (int i = 0; i < placeList.size(); i++) {
			// 拼接父字符串
			str += "{";
			str += "\"id\":" + placeList.get(i).getPlaceId() + ",";
			str += "\"text\":\"" + placeList.get(i).getPlaceName() + "\",";
			str += "\"iconCls\":\"icon-bullet_home\","; // 图标
			str += "\"children\":[";

			// 拼接子字符串
			String hql = "from CameraModel where CameraPlaceId = "
					+ placeList.get(i).getPlaceId();
			List<CameraModel> cameraList = CameraBase.find(hql);
			for (int j = 0; j < cameraList.size(); j++) {
				str += "{";
				str += "\"id\":" + cameraList.get(j).getCameraId() + ",";
				str += "\"text\":\"" + cameraList.get(j).getCameraName()
						+ "\",";
				str += "\"iconCls\":\"icon-camera\","; // 图标
				// 判断是否被用户选取
				for (int k = 0; k < numList.size(); k++) {
					if (cameraList.get(j).getCameraSourceId()
							.equals(numList.get(k).getCameraSourceId())) {
						str += "\"checked\":\"true\","; // 勾选
						break;
					}
				}
				//
				str += "\"attributes\":{\"sourceId\":\""
						+ cameraList.get(j).getCameraSourceId()
						+ "\",\"ip\":\""
						+ cameraList.get(j).getCameraIPAddress()
						+ "\",\"user\":\"" + cameraList.get(j).getCameraUser()
						+ "\",\"password\":\""
						+ cameraList.get(j).getCameraPassword() + "\"}";

				if (j == cameraList.size() - 1) {
					str += "}";
				} else {
					str += "},";
				}
			}
			if (i == placeList.size() - 1) {
				str += "]}";
			} else {
				str += "]},";
			}
		}
		str += "]}]";

		return str;

	}

	// 添加摄像机--用户映射关系
	@Override
	public String changeCameraToUser(String[] jianArray, String[] jiaArray,
			Integer userId) {
		String hql = "from UserModel where id = " + userId;
		UserModel user = this.getByHql(hql);

		int statu = 0;
		// 取消共享
		for (int i = 0; i < jianArray.length; i++) {
			if (jianArray[i].equals("-1")) {
				continue;
			}
			int result = WebServiceServe.getObj().DeleteCamerasOfUser(
					user.getUsername(), jianArray[i]);
			ctouBase.executeSql("delete from face_camera_to_user where userId="
					+ userId + " and cameraSourceId='" + jianArray[i] + "'");
			statu += result;
		}
		// 添加共享
		for (int i = 0; i < jiaArray.length; i++) {
			if (jiaArray[i].equals("-1")) {
				continue;
			}
			int result = WebServiceServe.getObj().AddCamerasOfUser(
					user.getUsername(), jiaArray[i]);
			CameraToUserModel ctum = new CameraToUserModel();
			ctum.setCameraSourceId(jiaArray[i]);
			ctum.setUserId(userId);
			ctouBase.save(ctum);
			statu += result;
		}
		if (statu == 0) {
			return "{\"statu\":\"success\",\"msg\": \"映射关系更新成功\"}";
		} else {
			return "{\"statu\":\"error\",\"msg\": \"映射关系更新失败\"}";
		}
	}

	// 添加人像库--用户映射关系
	@Override
	public String changeLibraryToUser(String[] jianArray, String[] jiaArray,
			Integer userId, String currentUser) {
		String hql = "from UserModel where id = " + userId;
		UserModel user = this.getByHql(hql);

		int statu = 0;
		// 取消共享
		for (int i = 0; i < jianArray.length; i++) {
			if (jianArray[i].equals("-1")) {
				continue;
			}
			int result = WebServiceServe.getObj().CancelShareToUser(
					currentUser, jianArray[i], user.getUsername());
			statu += result;
		}
		// 添加共享
		for (int i = 0; i < jiaArray.length; i++) {
			if (jiaArray[i].equals("-1")) {
				continue;
			}
			int result = WebServiceServe.getObj().ShareLibrariesToUser(
					currentUser, jiaArray[i], user.getUsername());
			System.out.println(result);
			statu += result;
		}
		if (statu == 0) {
			return "{\"statu\":\"success\",\"msg\": \"映射关系更新成功\"}";
		} else {
			return "{\"statu\":\"error\",\"msg\": \"映射关系更新失败\"}";
		}
	}

	// 获取服务状态
	@Override
	public String getServiceStatus(String username) {
		try {

			String result = WebServiceServe.getObj().GetServiceStatus(username);
			// 定义状态map
			Map<String, String> statuMap = new HashMap<String, String>();
			statuMap.put("IDLE", "空闲");
			statuMap.put("PROCESSING", "运行");
			statuMap.put("FAULT", "错误");
			statuMap.put("STOPPED", "停止");
			// 取值
			Map<String, String> map = JsonHelper.toMap(result);
			Map<String, String> newMap = new HashMap<String, String>();
			for (Map.Entry<String, String> entry : map.entrySet()) {
				CameraModel camera = CameraBase.getCameraInfo(entry.getKey());
				newMap.put(camera.getCameraName(),
						statuMap.get(entry.getValue()));
			}
			String str = "";

			str += "[";
			str += JSON.toJSONString(newMap);
			str += "]";

			return str;
		} catch (Exception e) {
			e.printStackTrace();
			return "[]";
		}
	}

	// 获取用户登陆时间
	@Override
	public String getLoginTime(String user) {
		String startTime = new Long(
				Long.parseLong(DateUtil.timeStamp()) - 3 * 24 * 3600)
				.toString();
		String hql;
		if (user.equals("admin")) {
			hql = "from UserLoginModel where loginTime > " + startTime
					+ "order by loginTime desc";
		} else {
			hql = "from UserLoginModel where username = '" + user
					+ "' and loginTime > " + startTime
					+ "order by loginTime desc";
		}
		List<UserLoginModel> list = UserLoginBase.find(hql);

		// 拼接json
		String str = "[";
		for (int i = 0; i < list.size(); i++) {
			str += "{";
			str += "\"user\":\"" + list.get(i).getUsername() + "\",";
			str += "\"loginTime\":\""
					+ DateUtil.timeStamp2Date(list.get(i).getLoginTime()
							.toString(), "yyyy-MM-dd HH:mm:ss") + "\"";
			str += "}";
			if (i != list.size() - 1) {
				str += ",";
			}
		}
		str += "]";

		return str;
	}
}
