/*
 * Copyright (c) MASSCLOUDS 2013 All Rights Reserved
 */
package com.massclouds.ns.common.util;

import java.text.NumberFormat;

/**
 * <p>数学计算工具类。<p>
 * 
 * 创建日期 2013年8月8日<br>
 * @author li_ming<br>
 */
public class NumberUtil {
	/**
	 * 求百分比
	 * @param numerator
	 * @param denominator
	 * @return
	 */
	public static Integer percent(Long numerator, Long denominator) {
		int a = (int) ((float)numerator/(float)denominator*100);
		return a;
	}
}
