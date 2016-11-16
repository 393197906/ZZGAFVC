package com.massclouds.ns.controller;

import java.rmi.RemoteException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.massclouds.ns.common.util.StringUtil;
import com.massclouds.ns.model.CameraModel;
import com.massclouds.ns.model.CameraPlaceModel;
import com.massclouds.ns.model.PeopleBaseModel;
import com.massclouds.ns.model.UserGroupModel;
import com.massclouds.ns.model.UserModel;
import com.massclouds.ns.service.CameraPlaceService;
import com.massclouds.ns.service.CameraService;
import com.massclouds.ns.service.PeopleBaseService;
import com.massclouds.ns.service.UserGroupService;
import com.massclouds.ns.service.UserService;
import com.massclouds.ns.util.WebServiceServe;

/**
 * 摄像机相关控制器
 * 
 * @author xielipeng
 * @date 2016年05月5日
 */

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService UserBase;
	@Autowired
	private UserGroupService UserGroupBase;

	// md5 字符串
	@RequestMapping(value = "/md5", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String md5(@RequestParam String str) {
		return StringUtil.MD5(str);
	}

	// 登陆
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String login(@RequestParam String username,
			@RequestParam String password) {
		boolean statu = UserBase.login(username, StringUtil.MD5(password));
		if (statu) {
			return "{\"statu\":\"success\",\"msg\":\"登陆成功\"}";
		}
		return "{\"statu\":\"error\",\"msg\":\"登陆失败，请检查您的账户信息\"}";
	}

	// 用户列表
	@RequestMapping(value = "/userList", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String userList() {
		String str = UserBase.userList();
		return str;
	}

	// 用户分组列表
	@RequestMapping(value = "/userGroupList", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String userGroupList() {
		String str = UserGroupBase.userGroupList();
		return str;
	}

	// 单个用户信息 byId
	@RequestMapping(value = "/userInfo", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String userInfo(@RequestParam Integer id) {
		String str = UserBase.userInfo(id);
		return str;
	}

	// 编辑用户
	@RequestMapping(value = "/editUser", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String editUser(@RequestParam Integer id,
			@RequestParam String username, @RequestParam String password,
			@RequestParam Integer permission, @RequestParam Integer ofGroupId,
			@RequestParam String user) {
		UserModel um = new UserModel();
		um.setId(id);
		um.setUsername(username);
		um.setPassword(password);
		um.setPermission(permission);
		um.setOfGroupId(ofGroupId);
		String str = UserBase.updateUser(um, user);
		return str;
	}

	// 添加用户
	@RequestMapping(value = "/addUser", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String addUser(@RequestParam String username,
			@RequestParam String password, @RequestParam Integer permission,
			@RequestParam Integer ofGroupId, @RequestParam String user) {
		UserModel um = new UserModel();
		um.setUsername(username);
		um.setPassword(StringUtil.MD5(password));
		um.setPermission(permission);
		um.setOfGroupId(ofGroupId);
		String str = UserBase.addUser(um, user);
		return str;
	}

	// 删除用户
	@RequestMapping(value = "/deleteUser", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String deleteUser(@RequestParam Integer id, @RequestParam String user) {
		UserModel um = new UserModel();
		um.setId(id);
		String str = UserBase.deleteUser(um, user);
		return str;
	}

	// 获取用户拥有权限的摄像机列表
	@RequestMapping(value = "/getCamerasPermissionList", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getCamerasPermissionList(@RequestParam Integer id) {

		String str = UserBase.getCamerasPermissionList(id);
		return str;
	}

	// 获取用户拥有权限的人像库列表
	@RequestMapping(value = "/getPeopleBasesPermissionList", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getPeopleBasesPermissionList(@RequestParam String user) {
		String statu = UserBase.getPeopleBasesPermissionList(user);
		return statu;
	}

	// 单个用户分组信息 byId
	@RequestMapping(value = "/userGroupInfo", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String userGroupInfo(@RequestParam Integer id) {
		String str = UserGroupBase.userGroupInfo(id);
		return str;
	}

	// 添加用户分组
	@RequestMapping(value = "/addUserGroup", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String addUserGroup(@RequestParam String groupName,
			@RequestParam Integer parentId, @RequestParam String user) {
		UserGroupModel ugm = new UserGroupModel();
		ugm.setGroupName(groupName);
		ugm.setParentId(parentId);
		;
		String str = UserGroupBase.addUserGroup(ugm, user);
		return str;
	}

	// 编辑用户分组
	@RequestMapping(value = "/editUserGroup", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String editUserGroup(@RequestParam Integer groupId,
			@RequestParam String groupName, @RequestParam String user) {
		UserGroupModel ugm = new UserGroupModel();
		ugm.setGroupId(groupId);
		ugm.setGroupName(groupName);
		String str = UserGroupBase.updateUserGroup(ugm, user);
		return str;
	}

	// 删除用户分组
	@RequestMapping(value = "/deleteUserGroup", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String deleteUserGroup(@RequestParam Integer id,
			@RequestParam String user) {
		UserGroupModel ugm = new UserGroupModel();
		ugm.setGroupId(id);
		String str = UserGroupBase.deleteUserGroup(ugm, user);
		return str;
	}

	// 设置用户摄像机映射关系
	@RequestMapping(value = "/changeCameraToUser", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String changeCameraToUser(
			@RequestParam(value = "jianArray[]") String[] jianArray,
			@RequestParam(value = "jiaArray[]") String[] jiaArray,
			@RequestParam Integer userId) {
		String str = UserBase.changeCameraToUser(jianArray, jiaArray, userId);
		return str;
	}

	// 设置用户摄像机映射关系
	@RequestMapping(value = "/changeLibraryToUser", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String changeLibraryToUser(
			@RequestParam("jianArray[]") String[] jianArray,
			@RequestParam("jiaArray[]") String[] jiaArray,
			@RequestParam Integer userId, @RequestParam String currentUser) {
		String str = UserBase.changeLibraryToUser(jianArray, jiaArray, userId,
				currentUser);
		return str;
	}

	// 获取服务状态
	@RequestMapping(value = "/getServiceStatus", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getServiceStatus(@RequestParam String user) {
		String str = UserBase.getServiceStatus(user);
		return str;
	}

	// 根据用户 获取用户登陆时间
	@RequestMapping(value = "/getLoginTime", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getLoginTime(@RequestParam String user) {
		String str = UserBase.getLoginTime(user);
		return str;
	}

	// test
	// 根据用户 获取用户登陆时间
	@RequestMapping(value = "/tests", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String tests() {
		String str = WebServiceServe.getObj().TestVelocity() + "";
		return str;
	}

}
