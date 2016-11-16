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
import com.massclouds.ns.model.UserGroupModel;
import com.massclouds.ns.model.UserModel;
import com.massclouds.ns.service.BaseService;
import com.massclouds.ns.service.CameraService;
import com.massclouds.ns.service.UserGroupService;
import com.massclouds.ns.service.UserService;

/**
 * <p>
 * 用户分组服务实现类
 * <p>
 * 
 * 创建日期 2016年5月27日<br>
 * 
 * @author xielipeng<br>
 */

@Service("UserGroupService")
public class UserGroupServiceImpl extends BaseServiceImpl<UserGroupModel> implements
		UserGroupService {
	
	//用户分组列表
	@Override
	public String userGroupList() {
		List<UserGroupModel> list = this.find();
		String str = "[";
		for(int i = 0;i<list.size();i++){
			str+="{";
			str+="\"groupId\":\""+list.get(i).getGroupId()+"\",";
			str+="\"groupName\":\""+list.get(i).getGroupName()+"\"";
			str+="}";
			if(i!=list.size()-1){
				str+=",";
			}		
		}
		str+="]";
		
		return str;
	}
	
	//单个用户分组信息byId
	@Override
	public String userGroupInfo(Integer id) {
		String hql = "from UserGroupModel where groupId = "+id;
		UserGroupModel group = this.getByHql(hql);
		return JSON.toJSONString(group,true);
	}
	
	//编辑用户分组信息
	@Override
	public String updateUserGroup(UserGroupModel ugm, String user) {
		if(!hasPermission(user)){
			return "{\"statu\":\"error\",\"msg\":\"你没有权限这么做\"}";
		}
		this.update(ugm);
		return "{\"statu\":\"success\",\"msg\":\"修改成功\"}";
	}
	
	//添加用户分组
	@Override
	public String addUserGroup(UserGroupModel ugm, String user) {
		if(!hasPermission(user)){
			return "{\"statu\":\"error\",\"msg\":\"你没有权限这么做\"}";
		}
		UserGroupModel hasUserG = this.getByHql("from UserGroupModel where groupName='"+ugm.getGroupName()+"'");
		if(hasUserG!=null){
			return "{\"statu\":\"error\",\"msg\": \"新分组添加失败,分组已存在\"}";
		}
		this.save(ugm);
		return "{\"statu\":\"success\",\"msg\": \"新用户分组添加成功\"}";  
	}
	//删除用户分组
	@Override
	public String deleteUserGroup(UserGroupModel ugm, String user) {
		if(!hasPermission(user)){
			return "{\"statu\":\"error\",\"msg\":\"你没有权限这么做\"}";
		}
		this.delete(ugm);
		String sql = "delete from face_user where ofGroupId = "+ugm.getGroupId();
		this.executeSql(sql);
		return "{\"statu\":\"success\",\"msg\": \"用户分组删除成功\"}";
	}
	
}
