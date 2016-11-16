/*
 * Copyright (c) MASSCLOUDS 2013 All Rights Reserved
 */
package com.massclouds.ns.service;

import java.util.List;
import java.util.Map;

import com.massclouds.ns.model.PeopleBaseModel;

/**
 * <p>
 * 人口库服务接口
 * <p>
 * 创建日期 2016年5月1日<br>
 * 
 * @author xielipeng<br>
 */
public interface PeopleBaseService extends BaseService<PeopleBaseModel> {
	// 添加人像库
	public String savePeopleBase(String name, String user);

	// 删除人像库
	public String deletePeopleBase(String name, String user);

	// 查询人像库
	public String getPeopleBases(String user);

	// 查询人像库(select)
	public String getPeopleBasesSelect(String user);

	// 首页人像库列表（显示库容）
	public String getPeopleBasesIndex(String user);

	// 查询最近注册人脸记录(总数)
	public String getLatestEnrolledUsersInfoCount(String lib, int n);

	// 查询最近注册人脸记录
	public String getLatestEnrolledUsersInfo(String lib, int pageNum,
			int pageSize, int n);

	// 单个人脸记录
	public String getUsersInfoById(String id);

	// 单个人脸记录(按姓名、身份证)
	public String getUsersInfoByName(String name, String passportNumber);

	// 人脸反向注册（删除）
	public String deleteUser(int userId);

	// 查询符合条件的比中记录(条件检索)
	public String getHitRecord(String userType, int nPageSize, int nPage,
			String dtFrom, String dtTo, String strSourceId, String username);

	// 查询符合条件的比中记录总条数(条件检索 )
	public String getHitRecordTotal(String userType, String dtFrom,
			String dtTo, String strSourceId, String username);

	// 查询符合条件的未比中记录(条件检索)
	public String getNotHitRecord(String userType, int nPageSize, int nPage,
			String dtFrom, String dtTo, String strSourceId);

	// 查询符合条件的未比中记录总条数(条件检索)
	public String getNotHitRecordTotal(String userType, String dtFrom,
			String dtTo, String strSourceId);

	// 条件查询相似图像
	public String searchFaceImage(Map<String, String[]> map);

	// 条件查询相似图像(取总量)
	public String searchFaceImageCount(Map<String, String[]> map);

	// 1:1 验证
	public String faceVerify(String img1, String img2, float threshold);

	// 查询符合条件的比中记录总条数(条件检索 )(报警研判)
	public String getHitRecordForResponse(String userType, int nPageSize,
			int nPage, String dtFrom, String dtTo, String strSourceId,
			String username);

	// 查询符合条件的比中记录总条数(条件检索 )(报警研判)(一比一比对)
	String getHitRecordForResponseOTO(String userType, int nPageSize,
			int nPage, String dtFrom, String dtTo, String strSourceId,
			String username);

	// 根据id查询比中记录详细信息
	public String getHitRecordByID(Integer id);

}
