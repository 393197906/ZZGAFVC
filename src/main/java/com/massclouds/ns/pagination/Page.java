/*
 * Copyright (c) MASSCLOUDS 2013 All Rights Reserved
 */
package com.massclouds.ns.pagination;

import java.util.Collections;
import java.util.List;

/**
 * <p>表示分页中的一页。<p>
 * 
 * 创建日期 2013-7-27<br>
 * @author li_ming<br>
 */
public class Page<E> {
	// 是否首页
	private boolean hasPre;
	// 是否尾页
	private boolean hasNext;
	// 当前页包含的记录列表
	private List<E> items;
	// 当前页页码(起始为1)
	private int index;
	// 分页上下文
	private IPageContext<E> context;

	public IPageContext<E> getContext() {
		return this.context;
	}

	public void setContext(IPageContext<E> context) {
		this.context = context;
	}

	public int getIndex() {
		return this.index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public boolean isHasPre() {
		return this.hasPre;
	}

	public void setHasPre(boolean hasPre) {
		this.hasPre = hasPre;
	}

	public boolean isHasNext() {
		return this.hasNext;
	}

	public void setHasNext(boolean hasNext) {
		this.hasNext = hasNext;
	}

	public List<E> getItems() {
		return this.items == null ? Collections.<E> emptyList() : this.items;
	}

	public void setItems(List<E> items) {
		this.items = items;
	}
}
