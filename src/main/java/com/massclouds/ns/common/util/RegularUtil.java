package com.massclouds.ns.common.util;

/*
 * Copyright (c) MASSCLOUDS 2013 All Rights Reserved
 */

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>常用正则表达式验证。<p>
 * 
 * 创建日期 2013年9月5日<br>
 * @author wu_rui<br>
 */
public class RegularUtil {
	// ip规则
	private final static String ipRegular = "\\b((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\b";

	// 是否符合ip规则
	public static boolean isMatchIp(String ipAddress) {
		Pattern pattern = Pattern.compile(ipRegular);
		Matcher matcher = pattern.matcher(ipAddress);
		return matcher.matches();
	}
	
	//判断字符串是否符合表达式
	public static boolean patternValidate(String s,String regEx){
		boolean rs = false;
		Pattern p1 = Pattern.compile(regEx); 
        Matcher m1 = p1.matcher(s); 
        rs = m1.matches(); 
		return rs;
	}
}
