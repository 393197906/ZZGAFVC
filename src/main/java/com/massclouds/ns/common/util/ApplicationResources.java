/*
 * Copyright (c) MASSCLOUDS 2013 All Rights Reserved
 */
package com.massclouds.ns.common.util;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * <p>国际化资源和获取方法。<p>
 * 
 * 创建日期 2013年8月2日<br>
 * @author li_ming<br>
 */
public class ApplicationResources {
	private static ResourceBundle resourceBundle;
	static {
		Locale locale = new Locale("zh", "CN");
		resourceBundle = ResourceBundle.getBundle("Application", locale);
	}

	/**
	 * 获取国际化信息
	 * @param key
	 * @return
	 */
	public static String getMessage(String key) {
		return resourceBundle.getString(key);
	}

	/**
	 * 获取国际化信息
	 * @param key
	 * @param values
	 * @return
	 */
	public static String getMessage(String key, Object... values) {
		String msg = resourceBundle.getString(key);
		return MessageFormat.format(msg, values);
	}
/*  public static void main(String[] args) {
	  System.out.println(getMessage("order_success_message","小王","户籍办理"));
	}*/
}
