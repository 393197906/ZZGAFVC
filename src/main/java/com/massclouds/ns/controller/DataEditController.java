package com.massclouds.ns.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.massclouds.ns.domain.ChildrenBean;
import com.massclouds.ns.domain.NodeBean;
import com.massclouds.ns.domain.RelationsLineBean;
import com.massclouds.ns.domain.RelationsNodeBean;
import com.massclouds.ns.model.UserModel;
import com.massclouds.ns.util.JsonMessage;

/**
 * @author wang_qiang
 * @date 2016年04月11日
 */

@Controller
@RequestMapping("/demo")
public class DataEditController  {
	
//	@Autowired
//	private UserService userservice;
	
	//用来处理前台的login请求
	@RequestMapping("/login") 
	private @ResponseBody String hello(
			@RequestParam(value = "username", required = false)String username,
			@RequestParam(value = "password", required = false)String password
			){
		return "Hello "+username+",Your password is: "+password;
		
	}
	
	//获取前台展示的人员关系数据
	@RequestMapping(value = "/getRelationsData",method = RequestMethod.GET,produces = "application/json; charset=utf-8")
	@ResponseBody
	private String getData(){
		JsonMessage message = new JsonMessage();
		RelationsNodeBean nodebean = new RelationsNodeBean();
		RelationsLineBean linebean = new RelationsLineBean();
		List<RelationsNodeBean> nodeList = new ArrayList<RelationsNodeBean>();
		List<RelationsLineBean> lineList = new ArrayList<RelationsLineBean>();
		nodebean.setName("taofan");
		nodebean.setId("0000");
		nodebean.setValue("程东林");
		nodebean.setImgUrl("http://hiphotos.baidu.com/doc/pic/item/f9198618367adab48f0e69dd89d4b31c8601e456.jpg");
		nodebean.setDescription("逃犯本人");
		nodeList.add(nodebean);
		nodebean = new RelationsNodeBean();
		nodebean.setName("jiashu1");
		nodebean.setId("0001");
		nodebean.setValue("叶云兰");
		nodebean.setImgUrl("http://imgcp.yxlady.com/newtag.php?cut=1&width=200&height=200&url=http://img8.yxlady.com/yl/uploadfiles_5361/20150719/20150719215159810.jpg");
		nodebean.setDescription("逃犯的配偶");
		nodeList.add(nodebean);
		nodebean = new RelationsNodeBean();
		nodebean.setName("jiashu2");
		nodebean.setId("0002");
		nodebean.setValue("程冬林");
		nodebean.setImgUrl("http://www.qq1234.org/uploads/allimg/150326/8_150326183303_2.jpg");
		nodebean.setDescription("逃犯的次子");
		nodeList.add(nodebean);
		nodebean = new RelationsNodeBean();
		nodebean.setName("jiashu3");
		nodebean.setId("0003");
		nodebean.setValue("程浩飞");
		nodebean.setImgUrl("http://p.7755.com/cms/1208/201208171605035755.jpg");
		nodebean.setDescription("逃犯的孙子");
		nodeList.add(nodebean);
		nodebean = new RelationsNodeBean();
		nodebean.setName("jiashu4");
		nodebean.setId("0004");
		nodebean.setValue("程浩天");
		nodebean.setImgUrl("http://p.7755.com/cms/1208/201208171605035755.jpg");
		nodebean.setDescription("逃犯的孙子");
		nodeList.add(nodebean);
		nodebean = new RelationsNodeBean();
		nodebean.setName("jiashu5");
		nodebean.setId("0005");
		nodebean.setValue("程浩宇");
		nodebean.setImgUrl("http://p.7755.com/cms/1208/201208171605035755.jpg");
		nodebean.setDescription("逃犯的孙子");
		nodeList.add(nodebean);
		nodebean = new RelationsNodeBean();
		nodebean.setName("jiashu6");
		nodebean.setId("0006");
		nodebean.setValue("程吉");
		nodebean.setImgUrl("http://up.qqjia.com/z/14/tu17239_15.jpg");
		nodebean.setDescription("逃犯的孙女");
		nodeList.add(nodebean);
		linebean.setRelation("配偶");
		linebean.setSource("taofan");
		linebean.setTarget("jiashu1");
		lineList.add(linebean);
		linebean = new RelationsLineBean();
		linebean.setRelation("次子");
		linebean.setSource("taofan");
		linebean.setTarget("jiashu2");
		lineList.add(linebean);		
		linebean = new RelationsLineBean();
		linebean.setRelation("孙子");
		linebean.setSource("taofan");
		linebean.setTarget("jiashu3");
		lineList.add(linebean);	
		linebean = new RelationsLineBean();
		linebean.setRelation("孙子");
		linebean.setSource("taofan");
		linebean.setTarget("jiashu4");
		lineList.add(linebean);	
		linebean = new RelationsLineBean();
		linebean.setRelation("孙子");
		linebean.setSource("taofan");
		linebean.setTarget("jiashu5");
		lineList.add(linebean);	
		linebean = new RelationsLineBean();
		linebean.setRelation("孙女");
		linebean.setSource("taofan");
		linebean.setTarget("jiashu6");
		lineList.add(linebean);	
		message.setNode(nodeList);
		message.setLine(lineList);
		String jsonString = JSON.toJSONString(message, true);
		return  jsonString;
	}

	

	
}
