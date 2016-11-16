package com.massclouds.ns.common.util;


/**
 * 
 * <p>工具类。<p>
 *
 * 创建日期2015-2-13<br>
 * @author  wangrhuan<br>
 */
public class CommandUtil {
	/**
	 * 是否为空（null、空格、长度0情况下返回true）
	 * @param param
	 * @return
	 */
	public static boolean isEmpty(String str) {
		if(str!=null){
			str = str.trim();
		}
        return str == null || str.length() == 0;
	}
	/**
	 * 去除字符串空格，如果为null返回"" 空字符串
	 * @param str
	 * @return
	 */
	public static String strTirm(String str){
		return str==null?"":str.trim();
	}
	/**
	 * 转字符串到int 如果str为空或null返回0
	 * @param str
	 * @return
	 */
	public static int parseStrToInt(String str){
		if(isEmpty(str)){
			str="0";
		}
		return Integer.parseInt(str);
	}
	
}
