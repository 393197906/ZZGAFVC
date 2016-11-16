/*
 * Copyright (c) MASSCLOUDS 2013 All Rights Reserved
 */
package com.massclouds.ns.service;

import com.massclouds.ns.model.UserGroupModel;
import com.massclouds.ns.model.UserModel;

/**
 * <p>
 * 用户分组服务接口
 * <p>
 * 
 * 创建日期 2016年5月27日<br>
 * 
 * @author xielipeng<br>
 */
public interface UserGroupService extends BaseService<UserGroupModel> {
	
	//用户分组列表
	public String userGroupList();
	//单个用户分组信息byId
	public String userGroupInfo(Integer id);
	//编辑用户分组
	public String updateUserGroup(UserGroupModel ugm, String user);
	// 添加用户分组
	public String addUserGroup(UserGroupModel ugm, String user);

	// 删除用户分组
	public String deleteUserGroup(UserGroupModel ugm, String user);

}
