package com.massclouds.ns.controller;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.enterprise.inject.New;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.massclouds.ns.model.CameraModel;
import com.massclouds.ns.model.CameraPlaceModel;
import com.massclouds.ns.model.PeopleBaseModel;
import com.massclouds.ns.service.CameraPlaceService;
import com.massclouds.ns.service.CameraService;
import com.massclouds.ns.service.PeopleBaseService;
import com.massclouds.ns.socket.PolicyServer;
import com.massclouds.ns.socket.SocketServer;
import com.massclouds.ns.util.DateUtil;
import com.massclouds.ns.util.WebServiceServe;

/**
 * 摄像机相关控制器
 * @author xielipeng
 * @date 2016年05月5日
 */

@Controller
@RequestMapping("/camera")
public class CameraController {
	@Autowired
	private CameraPlaceService CameraPlaceBase;
	@Autowired
	private CameraService CameraBase;

	// 摄像机列表
	@RequestMapping(value = "/cameraList", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String cameraList() {
		String str = CameraPlaceBase.getCameraList("list");
		return str;
	}

	// 摄像机列表(按用户)
	@RequestMapping(value = "/cameraListByUser", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String cameraListByUser(@RequestParam String username) {
		String str;
		if (username.equals("admin")) {
			str = CameraPlaceBase.getCameraList("list");
		} else {
			str = CameraPlaceBase.getCameraListByUser(username,"list");
		}
		return str;
	}
	

	// 摄像机列表(按用户 实例化socket)
	@RequestMapping(value = "/cameraListByUserMonitor", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String cameraListByUserMonitor(@RequestParam String username) {
		String str;
		if (username.equals("admin")) {
			str = CameraPlaceBase.getCameraList("list");
		} else {
			str = CameraPlaceBase.getCameraListByUser(username,"list");
		}
		//开启监听
		SocketServer.getObj();
		return str;
	}
	
	// 摄像机列表 chose(按用户)
	@RequestMapping(value = "/cameraListChoseByUser", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String cameraListChoseByUser(@RequestParam String username) {
		String str;
		if (username.equals("admin")) {
			str = CameraPlaceBase.getCameraList("chose");
		} else {
			str = CameraPlaceBase.getCameraListByUser(username,"chose");
		}
		return str;
	}

	// 单个摄像机信息
	@RequestMapping(value = "/cameraInfo", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String cameraInfo(@RequestParam Integer id) {
		String str = CameraBase.getCameraInfo(id);
		return str;
	}

	// 地点列表
	@RequestMapping(value = "/cameraPlaceList", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String cameraPlaceList() {
		String str = CameraPlaceBase.getCameraPlaceList();
		return str;
	}

	// 单个摄像机地点
	@RequestMapping(value = "/cameraPlaceInfo", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String cameraplaceInfo(@RequestParam Integer id) {
		String str = CameraPlaceBase.getCameraPlaceInfo(id);
		return str;
	}

	// 修改摄像机地点信息
	@RequestMapping(value = "/editCameraPlace", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String editCameraPlace(@RequestParam Integer placeId,
			@RequestParam String placeName, @RequestParam String user) {
		CameraPlaceModel cp = new CameraPlaceModel();
		cp.setPlaceId(placeId);
		cp.setPlaceName(placeName);
		String statu = CameraPlaceBase.updateCameraPlace(cp, user);
		return statu;
	}

	// 添加摄像机地点信息
	@RequestMapping(value = "/addCameraPlace", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String addCameraPlace(@RequestParam String placeName,
			@RequestParam Integer parentId, @RequestParam String user) {
		System.out.println(placeName);
		CameraPlaceModel cp = new CameraPlaceModel();
		cp.setPlaceName(placeName);
		cp.setParentId(parentId);
		String statu = CameraPlaceBase.saveCameraPlace(cp, user);
		return statu;
	}

	// 修改摄像机信息
	@RequestMapping(value = "/editCamera", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String editCamera(@RequestParam Integer cameraId,
			@RequestParam String cameraName,
			@RequestParam String cameraSourceId,
			@RequestParam Integer cameraPlaceId,
			@RequestParam String cameraIPAddress,
			@RequestParam String cameraUser,
			@RequestParam String cameraPassword, @RequestParam String cameraUrl,@RequestParam String user) {
		CameraModel cm = new CameraModel();
		cm.setCameraId(cameraId);
		cm.setCameraName(cameraName);
		cm.setCameraSourceId(cameraSourceId);
		cm.setCameraIPAddress(cameraIPAddress);
		cm.setCameraPlaceId(cameraPlaceId);
		cm.setCameraUser(cameraUser);
		cm.setCameraPassword(cameraPassword);
		cm.setCameraUrl(cameraUrl);
		String statu = CameraBase.updateCamera(cm, user);
		return statu;
	}

	// 添加摄像机信息
	@RequestMapping(value = "/addCamera", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String addCamera(@RequestParam String cameraName,
			@RequestParam String cameraSourceId,
			@RequestParam Integer cameraPlaceId,
			@RequestParam String cameraIPAddress,
			@RequestParam String cameraUser,
			@RequestParam String cameraPassword,@RequestParam String cameraUrl, @RequestParam String user) {
		CameraModel cm = new CameraModel();
		cm.setCameraName(cameraName);
		cm.setCameraSourceId(cameraSourceId);
		cm.setCameraIPAddress(cameraIPAddress);
		cm.setCameraPlaceId(cameraPlaceId);
		cm.setCameraUser(cameraUser);
		cm.setCameraPassword(cameraPassword);
		cm.setCameraUrl(cameraUrl);
		String statu = CameraBase.saveCamera(cm, user);
		return statu;
	}

	// 删除地点信息
	@RequestMapping(value = "/deleteCameraPlace", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String deleteCameraPlace(@RequestParam Integer id,
			@RequestParam String user) {
		CameraPlaceModel cp = new CameraPlaceModel();
		cp.setPlaceId(id);
		String str = CameraPlaceBase.deleteCameraPlace(cp, user);
		return str;
	}

	// 删除摄像机信息
	@RequestMapping(value = "/deleteCamera", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String deleteCamera(@RequestParam Integer id,
			@RequestParam String user) {
		String str = CameraBase.deleteCamera(id, user);
		return str;
	}

	// 获取最近三天报警记录数据
	@RequestMapping(value = "/getThreeDayAlertTotal", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getThreeDayAlertTotal(@RequestParam String user) {
		String str = CameraBase.getThreeDayAlertTotal(user);
		return str;
	}

	// 获取最近三天抓拍记录数据
	@RequestMapping(value = "/getThreeDayCaptureTotal", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getThreeDayCaptureTotal(@RequestParam String user) {
		String str = CameraBase.getThreeDayCaptureTotal(user);
		return str;
	}

}
