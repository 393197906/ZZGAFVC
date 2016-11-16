/*
 * Copyright (c) MASSCLOUDS 2013 All Rights Reserved
 */
package com.massclouds.ns.pagination;

import java.util.List;

/**
 * <p>动态分页实现.每次查询返回一页记录的情况下使用。<p>
 * 
 * 创建日期 2013-7-27<br>
 * @author li_ming<br>
 */
public class QuickPageContext<E> implements IPageContext<E> {
	// 每页的数据
	private List<E> items;
	// 总记录数
	private int totalCount;
	// 每页显示记录数
	private int pageSize;

	public QuickPageContext(int totalCount, int pageSize, List<E> items) {
		this.totalCount = totalCount;
		this.pageSize = pageSize == 0 ? 10 : pageSize;
		if (items != null) {
			this.items = items;
		}
	}

	/*
	 * @see com.massclouds.capp.center.dal.pagination.IPageContext#getPage(int)
	 */
	public Page<E> getPage(int index) {
		Page<E> page = new Page<E>();
		page.setContext(this);
		int index2 = index > getPageCount() ? 1 : index;
		page.setHasNext(index2 < getPageCount());
		page.setHasPre(index2 > 1);
		page.setIndex(index2);
		page.setItems(items);
		return page;
	}

	/*
	 * @see com.massclouds.capp.center.dal.pagination.IPageContext#getPageCount()
	 */
	public int getPageCount() {
		int div = totalCount / pageSize;
		int result = (totalCount % pageSize == 0) ? div : div + 1;

		return result;
	}

	/*
	 * @see com.massclouds.capp.center.dal.pagination.IPageContext#getTotal()
	 */
	public int getTotal() {
		return this.totalCount;
	}

	/*
	 * @see com.massclouds.capp.center.dal.pagination.IPageContext#getPageSize()
	 */
	public int getPageSize() {
		return this.pageSize;
	}
}
