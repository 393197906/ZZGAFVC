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
import com.massclouds.ns.socket.PolicyServer;
import com.massclouds.ns.socket.SocketServer;
import com.massclouds.ns.util.WebServiceServe;

/**
 * socket相关控制器
 * 
 * @author xielipeng
 * @date 2016年06月06日
 */

@Controller
@RequestMapping("/socket")
public class SocketController {

	//开启监听
	@RequestMapping(value = "/start", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String start() {
		//开启socket监听
		
    	return null;
	}

	

}
