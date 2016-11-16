/*
 * Copyright (c) MASSCLOUDS 2013 All Rights Reserved
 */
package com.massclouds.ns.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.enterprise.inject.New;
import javax.ws.rs.PUT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tempuri1.FRSServiceStub.ArrayOfHitDetail;
import org.tempuri1.FRSServiceStub.FaceLibraryInfo;
import org.tempuri1.FRSServiceStub.HitDetail;
import org.tempuri1.FRSServiceStub.HitRecordExtend;
import org.tempuri1.FRSServiceStub.NotHitRecordExtend;
import org.tempuri1.FRSServiceStub.SimilarFace;
import org.tempuri1.FRSServiceStub.UserBaseExtend;
import org.tempuri1.FRSServiceStub.UserIdArray;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.massclouds.ns.dao.BaseDao;
import com.massclouds.ns.model.CameraModel;
import com.massclouds.ns.model.PeopleBaseModel;
import com.massclouds.ns.service.PeopleBaseService;
import com.massclouds.ns.socket.SocketThread;
import com.massclouds.ns.util.JsonHelper;
import com.massclouds.ns.util.WebServiceServe;
import com.sun.tools.xjc.generator.bean.ImplStructureStrategy.Result;

/**
 * <p>
 * 人口库实现类
 * <p>
 * 
 * 创建日期 2016年5月1日<br>
 * 
 * @author xielipeng<br>
 */

@Service("PeopleBaseService")
public class PeopleBaseServiceImpl extends BaseServiceImpl<PeopleBaseModel>
		implements PeopleBaseService {

	@Autowired
	private BaseDao<PeopleBaseModel> peopleBaseDao;
	@Autowired
	private BaseDao<CameraModel> CameraBaseDao;

	// 添加人像库
	@Override
	public String savePeopleBase(String name, String user) {
		int statu = WebServiceServe.getObj().addFaceLibrary(name, user);
		if (statu == 0) {
			return "{\"statu\":\"success\",\"message\":\"人像库添加成功\"}";
		} else {
			return "{\"statu\":\"error\",\"message\":\"人像库添加失败\"}";
		}
	}

	// 删除人像库
	@Override
	public String deletePeopleBase(String name, String user) {
		int statu = WebServiceServe.getObj().deleteFaceLibrary(name, user);
		if (statu == 0) {
			return "{\"statu\":\"success\",\"message\":\"人像库删除成功\"}";
		} else {
			return "{\"statu\":\"error\",\"message\":\"人像库删除失败\"}";
		}
	}

	// 查询人像库
	@Override
	public String getPeopleBases(String user) {
		FaceLibraryInfo[] array = WebServiceServe.getObj().GetFaceLibraries(
				user);
		if (array == null) {
			return null;
		}
		int length = array.length;
		String str = "[{\"id\":0,\"text\":\"人像库清单\",\"iconCls\":\"icon-application_view_list\",\"children\":[";
		for (int i = 0; i < length; i++) {
			str += "{\"id\":" + (i + 1) + ",\"text\":";
			str += "\"" + array[i].getName() + "\",";
			str += "\"iconCls\":\"icon-database\"}"; // 图标
			if (i != length - 1) {
				str += ",";
			}
		}
		str += "]}]";
		return str;
	}

	// select
	@Override
	public String getPeopleBasesSelect(String user) {
		FaceLibraryInfo[] array = WebServiceServe.getObj().GetFaceLibraries(
				user);
		if (array == null) {
			return null;
		}
		int length = array.length;
		String str = "[";
		for (int i = 0; i < length; i++) {
			str += "{\"id\":" + (i + 1) + ",\"text\":";
			str += "\"" + array[i].getName() + "\",";
			str += "\"iconCls\":\"icon-database\"}"; // 图标
			if (i != length - 1) {
				str += ",";
			}
		}
		str += "]";
		return str;
	}

	// 获取人口库列表（tree菜单 显示库容）
	@Override
	public String getPeopleBasesIndex(String user) {
		try {

			String jsonString = WebServiceServe.getObj()
					.GetTotalOfLibrary(user);
			Map<String, String> map = JsonHelper.toMap(jsonString);

			JSONArray ja = new JSONArray();
			int i = 0;
			for (Map.Entry<String, String> entry : map.entrySet()) {
				JSONObject jb = new JSONObject();
				jb.put("id", ++i);
				jb.put("text", entry.getKey());
				jb.put("iconCls", "icon-my_facel");
				JSONObject jl = new JSONObject();
				jl.put("cou", entry.getValue());
				jb.put("attributes", jl);
				ja.add(jb);
			}

			JSONArray jresult = new JSONArray();
			JSONObject jparent = new JSONObject();
			jparent.put("id", 0);
			jparent.put("text", "人像库清单");
			jparent.put("iconCls", "icon-application_view_list");
			jparent.put("children", ja);
			jresult.add(jparent);

			return jresult.toJSONString();
		} catch (Exception e) {
//			e.printStackTrace();
			return "[]";
		}

	}

	// 获取最近注册人脸(总数)
	public String getLatestEnrolledUsersInfoCount(String lib, int n) {
		UserIdArray[] arr = WebServiceServe.getObj().GetLatestEnrolledUsers(
				lib, n);
		if (arr == null) {
			return "{\"statu\":\"success\",\"num\":0}";
		}
		return "{\"statu\":\"success\",\"num\":\"" + arr.length + "\"}";
	}

	// 获取最近注册人脸(增加分页) TODO 算法待优化
	public String getLatestEnrolledUsersInfo(String lib, int pageNum,
			int pageSize, int n) {
		UserIdArray[] arr = WebServiceServe.getObj().GetLatestEnrolledUsers(
				lib, n);
		if (arr == null) {
			return "[]";
		}
		List<UserBaseExtend> list = new ArrayList<UserBaseExtend>();
		for (int i = (pageNum - 1) * pageSize; i < pageNum * pageSize; i++) { // 组织UserBaseExtend
			if (i >= arr.length) { // 越界跳出
				break;
			}
			Integer id = new Integer(arr[i].getUser_id());
			UserBaseExtend ube = WebServiceServe.getObj().GetUserBase(id);
			if (ube != null) {
				list.add(ube);
			}
		}
		// 拼接json
		String str = "[";
		for (int i = 0; i < list.size(); i++) {
			str += "{";
			str += "\"id\":\"" + list.get(i).getId() + "\",";
			str += "\"name\":\"" + list.get(i).getName() + "\",";
			str += "\"passportNumber\":\"" + list.get(i).getPassportNumber()
					+ "\",";
			str += "\"gender\":\"" + list.get(i).getGender() + "\",";
			str += "\"comment\":\"" + list.get(i).getComment() + "\",";
			str += "\"nation\":\"" + list.get(i).getNation() + "\",";
			str += "\"identityType\":\"" + list.get(i).getIdentityType()
					+ "\",";
			str += "\"bornedDate\":\"" + list.get(i).getBornedDate() + "\",";
			str += "\"fullImagePath\":\"" + list.get(i).getFullImagePath()
					+ "\",";
			str += "\"faceImage\":\"" + list.get(i).getFaceImage() + "\"";
			str += "}";
			if (i != list.size() - 1) {
				str += ",";
			}
		}
		str += "]";
		return str;
	}

	// 单个人脸记录
	@Override
	public String getUsersInfoById(String id) {
		Integer iid = new Integer(id);
		UserBaseExtend ube = WebServiceServe.getObj().GetUserBase(iid);
		if (ube == null) {
			return null;
		}
		String str = "{";
		str += "\"id\":\"" + ube.getId() + "\",";
		str += "\"name\":\"" + ube.getName() + "\",";
		str += "\"passportNumber\":\"" + ube.getPassportNumber() + "\",";
		str += "\"gender\":\"" + ube.getGender() + "\",";
		str += "\"comment\":\"" + ube.getComment() + "\",";
		str += "\"mac\":\"" + ube.getMacAddr() + "\",";
		str += "\"nation\":\"" + ube.getNation() + "\",";
		str += "\"identityType\":\"" + ube.getIdentityType() + "\",";
		str += "\"bornedDate\":\"" + ube.getBornedDate() + "\",";
		str += "\"fullImagePath\":\"" + ube.getFullImagePath() + "\",";
		str += "\"faceImage\":\"" + ube.getFaceImage() + "\"";
		str += "}";

		return str;

	}

	// 人脸反向注册
	@Override
	public String deleteUser(int userId) {
		int statu = WebServiceServe.getObj().DeEnroll(userId);
		if (statu == 0) {
			return "{\"statu\":\"success\",\"msg\":\"删除成功\"}";
		} else {
			return "{\"statu\":\"error\",\"msg\":\"删除失败\"}";
		}
	}

	// 单个人脸记录(按姓名、身份证)
	@Override
	public String getUsersInfoByName(String name, String passportNumber) {
		UserBaseExtend ube = WebServiceServe.getObj().GetUser(name,
				passportNumber);
		if (ube == null) {
			return "null";
		}
		String str = "{";
		str += "\"id\":\"" + ube.getId() + "\",";
		str += "\"name\":\"" + ube.getName() + "\",";
		str += "\"passportNumber\":\"" + ube.getPassportNumber() + "\",";
		str += "\"gender\":\"" + ube.getGender() + "\",";
		str += "\"comment\":\"" + ube.getComment() + "\",";
		str += "\"nation\":\"" + ube.getNation() + "\",";
		str += "\"mac\":\"" + ube.getMacAddr() + "\",";
		str += "\"identityType\":\"" + ube.getIdentityType() + "\",";
		str += "\"bornedDate\":\"" + ube.getBornedDate() + "\",";
		str += "\"fullImagePath\":\"" + ube.getFullImagePath() + "\",";
		str += "\"faceImage\":\"" + ube.getFaceImage() + "\"";
		str += "}";

		return str;

	}

	// 查询符合条件的比中记录总条数(条件检索 )(报警研判)(一比一比对)
	@Override
	public String getHitRecordForResponseOTO(String userType, int nPageSize,
			int nPage, String dtFrom, String dtTo, String strSourceId,
			String username) {
		HitRecordExtend[] array = WebServiceServe.getObj()
				.GetHitRecord(userType, nPageSize, nPage, dtFrom, dtTo,
						strSourceId, username);
		if (array == null) {
			return "null";
		}

		JSONArray jArray = new JSONArray();
		for (int i = 0; i < array.length; i++) {
			HitDetail[] harr = array[i].getDetails().getHitDetail();
			for (int j = 0; j < harr.length; j++) {
				JSONObject jb = (JSONObject) JSONObject.toJSON(array[i]);
				UserBaseExtend ube = WebServiceServe.getObj().GetUserBase(
						harr[j].getUserID());

				JSONObject ubjb = (JSONObject) JSONObject.toJSON(ube);
				jb.put("hitInfo", ubjb);
				jb.put("score", harr[j].getScore());
				jArray.add(jb);
			}
		}

		return jArray.toJSONString();
	}

	// // 查询符合条件的比中记录(条件检索)
	// @Override
	// public String getHitRecord(String userType, int nPageSize, int nPage,
	// String dtFrom, String dtTo, String strSourceId, String username) {
	// HitRecordExtend[] array = WebServiceServe.getObj()
	// .GetHitRecord(userType, nPageSize, nPage, dtFrom, dtTo,
	// strSourceId, username);
	// if (array == null) {
	// return null;
	// }
	// // 拼接json
	// String str = "[";
	// for (int i = 0; i < array.length; i++) {
	// str += "{";
	// str += "\"id\":\"" + array[i].getId() + "\",";
	// str += "\"sourceId\":\"" + array[i].getSourceId() + "\",";
	// str += "\"queryImage\":\"" + array[i].getQueryImage() + "\",";
	// str += "\"createTime\":\"" + array[i].getCreateTime() + "\",";
	// // 取详情信息
	// ArrayOfHitDetail aohd = array[i].getDetails();
	// HitDetail[] harr = aohd.getHitDetail();
	// if (harr != null) {
	// str += "\"hitDetail\":[";// 对象数组
	// for (int j = 0, k = 0; j < harr.length; j++, k++) {
	// if (k >= 3) {
	// break;
	// }
	// UserBaseExtend ube = WebServiceServe.getObj().GetUserBase(
	// harr[j].getUserID());
	// if (ube != null) {
	// str += "{";
	// str += "\"id\":\"" + ube.getId() + "\","; // id
	// str += "\"hitFace\":\"" + ube.getFaceImage() + "\","; // 人脸base64
	// str += "\"name\":\"" + ube.getName() + "\","; // 姓名
	// str += "\"passportNumber\":\""
	// + ube.getPassportNumber() + "\","; // 身份证号码
	// str += "\"gender\":\"" + ube.getGender() + "\","; // 性别
	// str += "\"comment\":\"" + ube.getComment() + "\","; // 备注
	// str += "\"Nation\":\"" + ube.getNation() + "\","; // 籍贯
	// str += "\"identityType\":\"" + ube.getIdentityType()
	// + "\","; // 类型
	// str += "\"bornedDate\":\"" + ube.getBornedDate()
	// + "\","; // 出生日期
	// str += "\"score\":\"" + harr[j].getScore() + "\""; // 比分
	// str += "}";
	// if (j != 2 && j != harr.length - 1) {
	// str += ",";
	// }
	// }
	//
	// }
	// str += "],";
	// }
	// try {
	// // 取得摄像机信息
	// CameraModel camera = CameraBaseDao
	// .getByHql("from CameraModel where CameraSourceId ='"
	// + array[i].getSourceId() + "'");
	// str += "\"cameraName\":\"" + camera.getCameraName() + "\"";
	// } catch (Exception e) {
	// str += "\"cameraName\":\"未知\"";
	// }
	// str += "}";
	// if (i != array.length - 1) {
	// str += ",";
	// }
	// }
	// str += "]";
	//
	// return str;
	// }

	// 查询符合条件的比中记录(条件检索)
	@Override
	public String getHitRecord(String userType, int nPageSize, int nPage,
			String dtFrom, String dtTo, String strSourceId, String username) {
		HitRecordExtend[] array = WebServiceServe.getObj()
				.GetHitRecord(userType, nPageSize, nPage, dtFrom, dtTo,
						strSourceId, username);
		if (array == null) {
			return null;
		}
		JSONArray jarr = new JSONArray();
		for (int i = 0; i < array.length; i++) {
			JSONObject jb = (JSONObject) JSONObject.toJSON(array[i]);
			// 设置摄像机名称
			try {
				CameraModel camera = CameraBaseDao
						.getByHql("from CameraModel where CameraSourceId ='"
								+ array[i].getSourceId() + "'");
				jb.put("cameraName", camera.getCameraName());
			} catch (Exception e) {
				jb.put("cameraName", "未知摄像机");
			}

			HitDetail[] hdarr = array[i].getDetails().getHitDetail();
			JSONArray jarrD = new JSONArray();
			for (int j = 0, k = 0; j < hdarr.length; j++, k++) {
				if (k >= 3) {
					break;
				}
				UserBaseExtend ube = WebServiceServe.getObj().GetUserBase(
						hdarr[j].getUserID());
				JSONObject ubej = (JSONObject) JSONObject.toJSON(ube);
				try {
					ubej.put("score", hdarr[j].getScore());
					int level = SocketThread.getLevel(hdarr[j].getScore(),
							ube.getMacAddr(), 10, "1001");
					ubej.put("level", level);
				} catch (Exception e) {
					e.printStackTrace();
				}
				jarrD.add(ubej);
			}
			jb.put("hitDetail", jarrD);

			jarr.add(jb);
		}

		return jarr.toJSONString();

	}

	// 根据id查询比中记录详细信息
	@Override
	public String getHitRecordByID(Integer id) {
		try {
			HitRecordExtend result = WebServiceServe.getObj().GetHitRecordByID(
					id);
			JSONObject jb = (JSONObject) JSONObject.toJSON(result);
			String cn;
			try {
				CameraModel camera = CameraBaseDao
						.getByHql("From CameraModel where CameraSourceId = '"
								+ result.getSourceId() + "'");
				cn = camera.getCameraName();
			} catch (Exception e) {
				e.printStackTrace();
				cn = "未知摄像机";
			}
			jb.put("cameraName", cn);

			return jb.toJSONString();

		} catch (Exception e) {
			e.printStackTrace();
			return "null";
		}

	}

	// 查询符合条件的比中记录总条数(条件检索)
	@Override
	public String getHitRecordTotal(String userType, String dtFrom,
			String dtTo, String strSourceId, String username) {
		Integer count = WebServiceServe.getObj().GetHitRecordTotal(userType,
				dtFrom, dtTo, strSourceId, username);
		return count.toString();
	}

	// 查询符合条件的未比中记录(条件检索)
	@Override
	public String getNotHitRecord(String userType, int nPageSize, int nPage,
			String dtFrom, String dtTo, String strSourceId) {
		NotHitRecordExtend[] array = WebServiceServe.getObj().GetNotHitRecord(
				userType, nPageSize, nPage, dtFrom, dtTo, strSourceId);
		if (array == null) {
			return null;
		}
		// 拼接json
		String str = "[";
		for (int i = 0; i < array.length; i++) {
			str += "{";
			str += "\"id\":\"" + array[i].getId() + "\",";
			str += "\"sourceId\":\"" + array[i].getSourceID() + "\",";
			str += "\"queryImage\":\"" + array[i].getFaceStr() + "\",";
			str += "\"createTime\":\"" + array[i].getCreateTime() + "\",";
			// 取得摄像机信息
			try {
				// 取得摄像机信息
				CameraModel camera = CameraBaseDao
						.getByHql("from CameraModel where CameraSourceId ='"
								+ array[i].getSourceID() + "'");
				str += "\"cameraName\":\"" + camera.getCameraName() + "\"";
			} catch (Exception e) {
				str += "\"cameraName\":\"未知摄像机\"";
			}
			str += "}";
			if (i != array.length - 1) {
				str += ",";
			}
		}
		str += "]";

		return str;
	}

	// 查询符合条件的未比中记录总条数(条件检索)
	@Override
	public String getNotHitRecordTotal(String userType, String dtFrom,
			String dtTo, String strSourceId) {
		Integer count = WebServiceServe.getObj().GetNotHitRecordTotal(userType,
				dtFrom, dtTo, strSourceId);
		return count.toString();
	}

	// 条件查询相似图像
	@Override
	public String searchFaceImage(Map<String, String[]> map) {
		int pageSize = Integer.parseInt(map.get("pageSize")[0]); // 分页参数
		int pageNum = Integer.parseInt(map.get("pageNum")[0]);
		// 参数
		String imgStr = map.get("image")[0];
		String type = map.get("type")[0];
		float threshold = Float.parseFloat(map.get("threshold")[0]);
		int topN = Integer.parseInt(map.get("topN")[0]);
		String model = map.get("model")[0];
		try {
			SimilarFace[] array = WebServiceServe.getObj().SearchFaceImage(
					imgStr, type, threshold, topN);

			String str = "[";
			for (int i = (pageNum - 1) * pageSize; i < pageNum * pageSize; i++) {
				if (i >= array.length) { // 越界跳出
					break;
				}
				UserBaseExtend ube = WebServiceServe.getObj().GetUserBase(
						Integer.parseInt(array[i].getUserID()));
				if (ube != null) {
					str += "{";
					str += "\"id\":\"" + ube.getId() + "\","; // id
					str += "\"hitFace\":\"" + ube.getFaceImage() + "\","; // 人脸base64
					str += "\"name\":\"" + ube.getName() + "\","; // 姓名
					str += "\"passportNumber\":\"" + ube.getPassportNumber()
							+ "\","; // 身份证号码
					str += "\"gender\":\"" + ube.getGender() + "\","; // 性别
					str += "\"comment\":\"" + ube.getComment() + "\","; // 备注
					str += "\"nation\":\"" + ube.getNation() + "\","; // 籍贯
					str += "\"identityType\":\"" + ube.getIdentityType()
							+ "\","; // 类型
					str += "\"bornedDate\":\"" + ube.getBornedDate() + "\","; // 出生日期
					str += "\"score\":\"" + array[i].getScore() + "\""; // 比分
					str += "}";
					if (i != pageNum * pageSize - 1 && i != array.length - 1) {
						str += ",";
					}
				}
			}
			str += "]";
			return str;
		} catch (Exception e) {
			e.printStackTrace();
			return "null";
		}
	}

	// 条件查询相似图像 （取总量）
	@Override
	public String searchFaceImageCount(Map<String, String[]> map) {
		try {
			// 参数
			String imgStr = map.get("image")[0];
			String type = map.get("type")[0];
			float threshold = Float.parseFloat(map.get("threshold")[0]);
			int topN = Integer.parseInt(map.get("topN")[0]);
			SimilarFace[] array = WebServiceServe.getObj().SearchFaceImage(
					imgStr, type, threshold, topN);
			System.out.println(array.length);
			String count = new Integer(array.length).toString();
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			return "0";
		}
	}

	// 1:1 验证
	@Override
	public String faceVerify(String img1, String img2, float threshold) {
		float resultNum = WebServiceServe.getObj().FaceVerify(img1, img2);
		if (resultNum < 0) {
			if (resultNum == -1) {
				return "{\"statu\":\"error\",\"msg\":\"错误:请选择有人脸的图片上传比对\"}";
			}
			return "{\"statu\":\"error\",\"msg\":\"系统错误,文件过大\"}";
		}

		String colorStatu;
		if (resultNum < threshold) {
			colorStatu = "<";
		} else if (resultNum > threshold) {
			colorStatu = ">";
		} else {
			colorStatu = "=";
		}
		return "{\"statu\":\"success\",\"msg\":\"成功\",\"data\":{\"result\":\""
				+ resultNum + "\",\"colorStatu\":\"" + colorStatu + "\"}}";

	}

	@Override
	public String getHitRecordForResponse(String userType, int nPageSize,
			int nPage, String dtFrom, String dtTo, String strSourceId,
			String username) {
		// TODO Auto-generated method stub
		return null;
	}

	// public static void main(String[] args) {
	// System.out.println(new
	// PeopleBaseServiceImpl().GetLatestEnrolledUsers("o",100));
	//
	// }

}
