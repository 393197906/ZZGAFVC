package com.massclouds.ns.controller;

import java.io.InputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.tempuri1.FRSServiceStub.HitRecordExtend;
import org.tempuri1.FRSServiceStub.NotHitRecordExtend;
import org.tempuri1.FRSServiceStub.UserBaseExtend;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.massclouds.ns.service.CameraPlaceService;
import com.massclouds.ns.service.PeopleBaseService;
import com.massclouds.ns.service.PoliceCompanyService;
import com.massclouds.ns.util.WebServiceServe;

/**
 * 人口库相关控制器
 * 
 * @author xielipeng
 * @date 2016年04月19日
 */

@Controller
@RequestMapping("/peopleBase")
public class PeopleBaseController {

	@Autowired
	private PeopleBaseService peopleBase;

	@Autowired
	private CameraPlaceService CameraPlaceBase;

	// 用来处理前台的login请求
	@RequestMapping("/login")
	private @ResponseBody String hello(
			@RequestParam(value = "username", required = false) String username,
			@RequestParam(value = "password", required = false) String password) {
		return "Hello " + username + ",Your password is: " + password;

	}

	// 添加人口库
	@RequestMapping(value = "/addPeopleBase", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String addPeopleBase(@RequestParam String name,
			@RequestParam String user) {
		String statu = peopleBase.savePeopleBase(name, user);
		return statu;
	}

	// 删除人口库
	@RequestMapping(value = "/deletePeopleBase", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String deletePeopleBase(@RequestParam String name,
			@RequestParam String user) {
		String statu = peopleBase.deletePeopleBase(name, user);
		return statu;
	}

	// 获取人口库列表（tree 菜单）
	@RequestMapping(value = "/getPeopleBases", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getPeopleBases(@RequestParam String user) {
		String statu = peopleBase.getPeopleBases(user);
		return statu;
	}

	// 获取人口库列表（tree菜单 显示库容）
	@RequestMapping(value = "/getPeopleBasesIndex", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getPeopleBasesIndex(@RequestParam String user) {
		String statu = peopleBase.getPeopleBasesIndex(user);
		return statu;
	}

	// 获取人口库列表（select）
	@RequestMapping(value = "/getPeopleBasesSelect", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getPeopleBasesSelect(@RequestParam String user) {
		String statu = peopleBase.getPeopleBasesSelect(user);
		return statu;
	}

	// 单例人口注册
	@RequestMapping(value = "/enrollUser", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String enrollUser(HttpServletRequest request) {
		Map<String, String[]> map = request.getParameterMap();
		if (map.toString().equals("{}")) {
			return "{\"statu\":\"error\",\"msg\":\"注册失败,图片过大\"}";
		}
		int statu = WebServiceServe.getObj().EnrollUser(map);
		if (statu == 0 || statu == 1) {
			return "{\"statu\":\"success\",\"msg\":\"注册成功\"}";
		} else {
			return "{\"statu\":\"error\",\"msg\":\"注册失败，错误码：" + statu + "\"}";
		}

	}

	// 单例人口反向注册（删除）
	@RequestMapping(value = "/deleteUser", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String deleteUser(@RequestParam String userId) {
		String str = peopleBase.deleteUser(Integer.parseInt(userId));
		return str;
	}

	// 最近注册人脸记录(总数 按库)
	@RequestMapping(value = "/getLatestEnrolledUsersInfoCount", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getLatestEnrolledUsersInfoCount(@RequestParam String lib) {
		String str = peopleBase.getLatestEnrolledUsersInfoCount(lib, 100);
		return str;
	}

	// 最近注册人脸记录(按库)
	@RequestMapping(value = "/getLatestEnrolledUsersInfo", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getLatestEnrolledUsersInfo(@RequestParam String lib,
			@RequestParam int pageNum, @RequestParam int pageSize) {
		String str = peopleBase.getLatestEnrolledUsersInfo(lib, pageNum,
				pageSize, 100);
		return str;

	}

	// 单个人脸记录 (按id)
	@RequestMapping(value = "/getUsersInfoById", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getUsersInfoById(@RequestParam String id) {
		String str = peopleBase.getUsersInfoById(id);
		return str;
	}

	// 单个人脸记录 (按姓名身份证号)
	@RequestMapping(value = "/getUsersInfoByName", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getUsersInfoByName(@RequestParam String name,
			@RequestParam String passportNumber) {
		String str = peopleBase.getUsersInfoByName(name, passportNumber);
		return str;
	}

	// 根据id查询比中记录详细信息
	@RequestMapping(value = "/getHitRecordByID", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getHitRecordByID(@RequestParam Integer id) {
		String str = peopleBase.getHitRecordByID(id);
		return str;
	}

	// 查询符合条件的比中记录(条件检索)
	@RequestMapping(value = "/getHitRecord", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getHitRecord(@RequestParam String userType,
			@RequestParam int nPageSize, @RequestParam int nPage,
			@RequestParam String dtFrom, @RequestParam String dtTo,
			@RequestParam String strSourceId, @RequestParam String username) {

		String str = peopleBase.getHitRecord(userType, nPageSize, nPage,
				dtFrom, dtTo, strSourceId, username);
		return str;
	}

	// 查询符合条件的比中记录(条件检索)(报警研判)
	@RequestMapping(value = "/getHitRecordForResponse", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getHitRecordForResponse(@RequestParam String userType,
			@RequestParam int nPageSize, @RequestParam int nPage,
			@RequestParam String dtFrom, @RequestParam String dtTo,
			@RequestParam String strSourceId, @RequestParam String username) {

		String str = peopleBase.getHitRecordForResponse(userType, nPageSize,
				nPage, dtFrom, dtTo, strSourceId, username);
		return str;
	}

	// 查询符合条件的比中记录总条数(条件检索)
	@RequestMapping(value = "/getHitRecordTotal", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getHitRecordTotal(String userType, String dtFrom,
			String dtTo, String strSourceId, String username) {
		String str = peopleBase.getHitRecordTotal(userType, dtFrom, dtTo,
				strSourceId, username);
		return str;
	}

	// 查询符合条件的未比中记录(条件检索)
	@RequestMapping(value = "/getNotHitRecord", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getNotHitRecord(@RequestParam String userType,
			@RequestParam int nPageSize, @RequestParam int nPage,
			@RequestParam String dtFrom, @RequestParam String dtTo,
			@RequestParam String strSourceId) {

		String str = peopleBase.getNotHitRecord(userType, nPageSize, nPage,
				dtFrom, dtTo, strSourceId);
		return str;
	}

	// 查询符合条件的未比中记录总条数(条件检索)
	@RequestMapping(value = "/getNotHitRecordTotal", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getNotHitRecordTotal(@RequestParam String userType,
			@RequestParam String dtFrom, @RequestParam String dtTo,
			@RequestParam String strSourceId) {
		String str = peopleBase.getNotHitRecordTotal(userType, dtFrom, dtTo,
				strSourceId);
		return str;
	}
	
	// 图片检索(取总量)
		@RequestMapping(value = "/searchFaceImageCount", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
		@ResponseBody
		public String searchFaceImageCount(HttpServletRequest request) {
			Map<String, String[]> map = request.getParameterMap();
			String count = peopleBase.searchFaceImageCount(map);
			return count;

		}
		
	// 图片检索
	@RequestMapping(value = "/searchFaceImage", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String searchFaceImage(HttpServletRequest request) {
		Map<String, String[]> map = request.getParameterMap();
		String str = peopleBase.searchFaceImage(map);
		return str;

	}

	// 根据id字符串取用户信息(查询检索使用)
	@RequestMapping(value = "/getUserBaseByIdStr", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getUserBaseByIdStr(@RequestParam String info) {
		String[] sourceStrArray = info.split(",");
		JSONArray jsonArray = new JSONArray();
		try {

			for (int i = 0; i < sourceStrArray.length; i++) {
				UserBaseExtend ube = WebServiceServe.getObj().GetUserBase(
						Integer.parseInt(sourceStrArray[i]));
				JSONObject jb = (JSONObject) JSONObject.toJSON(ube);
				jsonArray.add(jb);
			}
			return jsonArray.toJSONString();
		} catch (Exception e) {
			return "[]";
		}
	}


	// 获取上传图片的 base64字符串
	@RequestMapping(value = "/getBase64", method = RequestMethod.POST, produces = "text/html; charset=utf-8")
	@ResponseBody
	public String getBase64(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
			// 获得文件：
			MultipartFile file = mRequest.getFile("faceImg");
			// 获得文件名：
			String filename = file.getOriginalFilename();

			// 获得输入流：
			InputStream input = file.getInputStream();
			String image = WebServiceServe.GetBase64FromImage(input);
			return image;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 获取上传图片的 base64字符串(1:1验证 两张图片)
	@RequestMapping(value = "/getBase64ByFaceVerify", method = RequestMethod.POST, produces = "text/html; charset=utf-8")
	@ResponseBody
	public String getBase64ByFaceVerify(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
			// 获得文件：
			MultipartFile file1 = mRequest.getFile("faceImg1");
			// 获得文件名：
			String filename1 = file1.getOriginalFilename();

			// 获得输入流：
			InputStream input1 = file1.getInputStream();
			String image1 = WebServiceServe.GetBase64FromImage(input1);
			// 获得文件：
			MultipartFile file2 = mRequest.getFile("faceImg2");
			// 获得文件名：
			String filename2 = file2.getOriginalFilename();

			// 获得输入流：
			InputStream input2 = file2.getInputStream();
			String image2 = WebServiceServe.GetBase64FromImage(input2);
			return image1 + "@biao@ji@" + image2;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 1:1验证控制器方法
	@RequestMapping(value = "/faceVerify", method = RequestMethod.POST, produces = "text/html; charset=utf-8")
	@ResponseBody
	public String faceVerify(@RequestParam String img1,
			@RequestParam String img2, @RequestParam float threshold) {
		String result = peopleBase.faceVerify(img1, img2, threshold);
		return result;
	}

	// 报警记录测试接口
	@RequestMapping(value = "/glhr", method = RequestMethod.POST, produces = "text/html; charset=utf-8")
	@ResponseBody
	public String glhr(@RequestParam String user, @RequestParam Integer time) {
		HitRecordExtend[] result = WebServiceServe.getObj().GetLatestHitRecord(
				user, time);
		JSONArray array = (JSONArray) JSONArray.toJSON(result);
		try {
			return array.toJSONString();
		} catch (Exception e) {
			return "null";
		}

	}

	// 抓拍记录测试接口
	@RequestMapping(value = "/glcr", method = RequestMethod.POST, produces = "text/html; charset=utf-8")
	@ResponseBody
	public String glcr(@RequestParam String user, @RequestParam Integer time) {
		NotHitRecordExtend[] result = WebServiceServe.getObj()
				.GetLatestCapturedFaces(user, time);
		JSONArray array = (JSONArray) JSONArray.toJSON(result);
		try {
			return array.toJSONString();
		} catch (Exception e) {
			return "null";
		}
	}

}
