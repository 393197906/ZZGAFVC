/*
 * Copyright (c) MASSCLOUDS 2013 All Rights Reserved
 */
package com.massclouds.ns.pagination;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

import javax.persistence.Id;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.massclouds.ns.common.util.KeySynchronizer;

/**
 * <p>分页工具类。<p>
 * 
 * 创建日期 2013-7-27<br>
 * @author li_ming<br>
 */
public class PageUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(PageUtil.class);

	// 获取主键时缓存
	private static Map<Class<?>, Field> classPKMap = new WeakHashMap<Class<?>, Field>();

	/**
	 * 计算分页获取数据时游标的起始位置
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public static int getPageStart(int pageNumber, int pageSize) {
		return (pageNumber - 1) * pageSize;
	}

	/**
	 * 计算分页获取数据时游标的起始位置
	 * @param totalCount 所有记录总和
	 * @param pageNumber 页码,从1开始
	 * @return 分页获取数据时游标的起始位置
	 */
	public static int getPageStart(int totalCount, int pageNumber, int pageSize) {
		int start = (pageNumber - 1) * pageSize;
		if (start >= totalCount) {
			start = 0;
		}
		return start;
	}

	/**
	 * 计算分页获取数据时游标的结束位置
	 * @param totalCount 所有记录总和
	 * @param pageNumber 页码,从1开始
	 * @return 分页获取数据时游标的结束位置
	 */
	public static int getPageEnd(int totalCount, int pageNumber, int pageSize) {
		int pageEnd = pageNumber * pageSize - 1;
		if (pageEnd > totalCount - 1) {
			pageEnd = totalCount - 1;
		}
		return pageEnd;
	}

	/**
	 * 构造分页对象
	 * @param totalCount 满足条件的所有记录总和
	 * @param pageNumber 本次分页的页码
	 * @param items
	 * @return
	 */
	public static <E> Page<E> getPage(int totalCount, int pageNumber, List<E> items, int pageSize) {
		IPageContext<E> pageContext = new QuickPageContext<E>(totalCount, pageSize, items);
		return pageContext.getPage(pageNumber);
	}

	/**
	 * 获取主键
	 * @param cls
	 * @return
	 */
	public static Field getPkField(Class<?> cls) {
		Field pkField = classPKMap.get(cls);
		if (pkField == null) {
			synchronized (KeySynchronizer.acquire(cls)) {
				Field[] fields = cls.getDeclaredFields();
				for (Field field : fields) {
					if (field.isAnnotationPresent(Id.class)) {
						pkField = field;
						pkField.setAccessible(true);
						classPKMap.put(cls, pkField);
					}
				}
			}
		}
		if (pkField == null) {
			LOGGER.error("page error,{} : pk null", cls);
		}
		return pkField;
	}

	/**
	 * 获取id
	 * @param obj
	 * @return
	 */
	public static <T> String getIdValue(T obj) {
		if (obj == null) {
			return "";
		}
		String retVal = "";
		Field pkField = getPkField(obj.getClass());
		try {
			retVal = pkField.get(obj).toString();
		} catch (Exception e) {
			LOGGER.error("page error,{} : get id value", obj);
		}
		return retVal;
	}

	/**
	 * 获取id名称
	 * @param obj
	 * @return
	 */
	public static <T> String getIdName(T obj) {
		if (obj == null) {
			return "";
		}
		String retVal = "";
		Field pkField = getPkField(obj.getClass());
		try {
			retVal = pkField.getName();
		} catch (Exception e) {
			LOGGER.error("page error,{} : get id name", obj);
		}
		return retVal;
	}
}
