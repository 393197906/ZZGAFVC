package com.massclouds.ns.test;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.ServiceException;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sun.javafx.collections.MappingChange.Map;

public class MessageUtil {
//
//	/*	*//**
//	 * 发送短信
//	 * 
//	 * @return 发送结果
//	 */
//	/*
//	 * public static String sendMessgae(String tel, String code ,String
//	 * flg,String serviceName,String name,String message) throws
//	 * MalformedURLException { String endpoint = Constants.ENDPOINT; String
//	 * result = ""; Service service = new Service(); Call call; Object[] object
//	 * = new Object[5]; object[0] = Constants.USER;// 账号 object[1] =
//	 * Constants.PASSWORD;// 密码 object[2] = tel;// 手机号 // 短信内容编辑 //注册
//	 * if("1".equals(flg)){ //生成四位验证码 object[3] =
//	 * ApplicationResources.getMessage("register_message",code); }else
//	 * if("2".equals(flg)){ //密码重置 object[3] =
//	 * ApplicationResources.getMessage("resetpassword_message",code); }else
//	 * if("3".equals(flg)){ //通知 object[3] =
//	 * ApplicationResources.getMessage("order_success_message"
//	 * ,name,serviceName); }else if("4".equals(flg)){ //密碼重置 object[3] =
//	 * ApplicationResources.getMessage("resetpassword_result",code); }else
//	 * if("5".equals(flg)){ //暂缓办理通知 object[3] =
//	 * ApplicationResources.getMessage(
//	 * "order_failure_message",name,serviceName,message); } object[4] =
//	 * Constants.LONGNUM;// 上行码 try { call = (Call) service.createCall();
//	 * call.setTargetEndpointAddress(new java.net.URL(endpoint));// 远程调用路径
//	 * call.setOperationName(new QName("http://tempuri.org/", "SendSms"));//
//	 * 调用的方法名
//	 * 
//	 * // 设置参数名: call.addParameter(new QName("http://tempuri.org/", "username"),
//	 * // 参数名 XMLType.XSD_STRING,// 参数类型:String ParameterMode.IN);// 参数模式：'IN'
//	 * or 'OUT' call.addParameter(new QName("http://tempuri.org/", "password"),
//	 * // 参数名 XMLType.XSD_STRING,// 参数类型:String ParameterMode.IN);// 参数模式：'IN'
//	 * or 'OUT' call.addParameter(new QName("http://tempuri.org/", "phonelist"),
//	 * // 参数名 XMLType.XSD_STRING,// 参数类型:String ParameterMode.IN);// 参数模式：'IN'
//	 * or 'OUT' call.addParameter(new QName("http://tempuri.org/", "msg"), //
//	 * 参数名 XMLType.XSD_STRING,// 参数类型:String ParameterMode.IN);// 参数模式：'IN' or
//	 * 'OUT' call.addParameter(new QName("http://tempuri.org/", "longnum"), //
//	 * 参数名 XMLType.XSD_STRING,// 参数类型:String ParameterMode.IN);// 参数模式：'IN' or
//	 * 'OUT'
//	 * 
//	 * call.setSOAPActionURI("http://tempuri.org/SendSms"); // 设置返回值类型：
//	 * call.setReturnType(XMLType.XSD_STRING);// 返回值类型：String
//	 * 
//	 * result = (String) call.invoke(object);// 远程调用 System.out.println(result);
//	 * } catch (ServiceException e) { e.printStackTrace(); } catch
//	 * (RemoteException e) { e.printStackTrace(); } return result; }
//	 */
//
//	// common url
//	public static String endpoint = "http://192.168.5.140:5657/FRSService.asmx";
//
//	public static CameraStructBase GetWebService() throws MalformedURLException {
//		Service service = new Service();
//		Call call;
//		CameraStructBase result = new CameraStructBase();
//		Object[] object = new Object[1];
//		object[0] = "1212";// 账号
//		try {
//			call = (Call) service.createCall();
//			// 设置调用参数类型
//			 QName qn = new QName("http://tempuri.org/", "GetCameraList");
//			 call.registerTypeMapping(CameraStructBase.class, qn, new
//			 BeanSerializerFactory(CameraStructBase.class, qn), new
//			 BeanDeserializerFactory(CameraStructBase.class, qn));
//			call.setTargetEndpointAddress(new java.net.URL(endpoint));// 远程调用路径
//			call.setOperationName(new QName("http://tempuri.org/", "GetCameraList"));//
//			// 调用的方法名
//			call.setSOAPActionURI("http://tempuri.org/GetCameraList");
//			call.addParameter(new QName("http://tempuri.org/", "type"), // 参数名
//					XMLType.XSD_STRING,// 参数类型:String
//					ParameterMode.IN);// 参数模式：'IN' or 'OUT'
//			// 设置返回值类型：
////			call.setReturnType(XMLType.XSD_STRING);// 返回值类型：String
//			call.setReturnType(qn, CameraStructBase.class);
//			result = (CameraStructBase) call.invoke(object);// 远程调用
//			System.out.println(result);
//		} catch (ServiceException e) {
//			e.printStackTrace();
//		} catch (RemoteException e) {
//			e.printStackTrace();
//		}
//		return result;
//	}
//
//	// public static String GetWebService() throws MalformedURLException {
//	// Service service = new Service();
//	// Call call;
//	// String result = "";
//	// Object[] object = new Object[1];
//	// object[0] = "1212";// 账号
//	// try {
//	// call = (Call) service.createCall();
//	// // 设置调用参数类型
//	// // QName qn = new QName("http://tempuri.org/", "Camera");
//	// // call.registerTypeMapping(Camera.class, qn, new
//	// BeanSerializerFactory(Camera.class, qn), new
//	// BeanDeserializerFactory(Camera.class, qn));
//	// call.setTargetEndpointAddress(new java.net.URL(endpoint));// 远程调用路径
//	// call.setOperationName(new QName("http://tempuri.org/", "GetPlaces"));//
//	// // 调用的方法名
//	// call.setSOAPActionURI("http://tempuri.org/GetPlaces");
//	// call.addParameter(new QName("http://tempuri.org/", "type"), // 参数名
//	// XMLType.XSD_STRING,// 参数类型:String
//	// ParameterMode.IN);// 参数模式：'IN' or 'OUT'
//	// // 设置返回值类型：
//	// call.setReturnType(XMLType.XSD_STRING);// 返回值类型：String
//	// result = (String) call.invoke(object);// 远程调用
//	// System.out.println(result );
//	// } catch (ServiceException e) {
//	// e.printStackTrace();
//	// } catch (RemoteException e) {
//	// e.printStackTrace();
//	// }
//	// return result;
//	// }
//
//	// public static String GetWebService(String method,
//	// HashMap<String, String> params) throws MalformedURLException {
//	// String result = "";
//	// Service service = new Service();
//	// Call call;
//	// Object[] object = new Object[params.size()];
//	// try {
//	// call = (Call) service.createCall();
//	// call.setTargetEndpointAddress(new java.net.URL(endpoint));// 远程调用路径
//	// call.setOperationName(new QName("http://tempuri.org/", method));// 调用的方法名
//	// call.setSOAPActionURI("http://tempuri.org/" + method);
//	// if (params != null) {
//	// int i = 0;
//	// for (Entry<String, String> entry : params.entrySet()) {
//	// call.addParameter(
//	// new QName("http://tempuri.org/","type"), // 参数名
//	// XMLType.XSD_STRING,// 参数类型:String
//	// ParameterMode.IN);// 参数模式：'IN' or 'OUT'
//	// object[i] = entry.getValue();
//	// i++;
//	// }
//	// }
//	// call.setReturnType(XMLType.XSD_STRING);// 返回值类型：String
//	// result = (String) call.invoke(object);// 远程调用
//	// // 设置返回值类型：
//	// System.out.println(result);
//	// } catch (ServiceException e) {
//	// e.printStackTrace();
//	// } catch (RemoteException e) {
//	// e.printStackTrace();
//	// }
//	// return result;
//	// }
//
//	public static void main(String[] args) throws MalformedURLException {
//		// MessageUtil me = new MessageUtil();
//		HashMap<String, String> map = new HashMap<String, String>();
//		map.put("type", "123");
//		MessageUtil.GetWebService();
//		// Camera cam = new Camera();
//		// System.out.println(cam.getCameraIPAddress());
//		// me.sendMessgae("13645415341", "register_message", "1", null, null,
//		// null);
//	}
	
	//test
	
	public static void main(String[] args) {
		String str =null;
		String str2 = "123";
		System.out.println(str + str2);
	}

}
