/*
 * Copyright (c) MASSCLOUDS 2013 All Rights Reserved
 */
package com.massclouds.ns.service;

import org.springframework.web.bind.annotation.RequestParam;

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
public interface UserService extends BaseService<UserModel> {
	// 登陆
	public Boolean login(String username, String password);

	// 用户列表
	public String userList();

	// 单个用户信息byId
	public String userInfo(Integer id);

	// 编辑用户
	public String updateUser(UserModel um, String user);

	// 添加用户
	public String addUser(UserModel um, String user);

	// 删除用户
	public String deleteUser(UserModel um, String user);

	// 获取用户拥有权限的摄像机列表
	public String getCamerasPermissionList(Integer id);

	// 摄像机--用户映射关系
	public String changeCameraToUser(String[] jianArray,String[] jiaArray,Integer userId);

	// 人像库--用户映射关系
	public String changeLibraryToUser(String[] jianArray,String[] jiaArray,Integer userId,String currentUser);

	String getPeopleBasesPermissionList(String user);
	//获取服务状态
	public String getServiceStatus(String username);
	
	//获取用户登陆时间
	public String getLoginTime(String user);

}
