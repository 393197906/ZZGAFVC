/*
 * Copyright (c) MASSCLOUDS 2013 All Rights Reserved
 */
package com.massclouds.ns.pagination;

/**
 * <p>分页上下文环境。用于计算Page。<p>
 * 
 * 创建日期 2013-7-27<br>
 * @author li_ming<br>
 */
public interface IPageContext<E> {
	/**
	 * 计算总页数.
	 * @return
	 */
	public int getPageCount();

	/**
	 * 返回 Page 对象.
	 * @param index
	 * @return
	 */
	public Page<E> getPage(int index);

	/**
	 * 每页显示的记录数量
	 * @return
	 */
	public int getPageSize();

	/**
	 * 计算总记录数
	 * @return
	 */
	public int getTotal();

}
