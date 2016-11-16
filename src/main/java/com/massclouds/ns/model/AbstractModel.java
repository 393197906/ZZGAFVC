/*
 * Copyright (c) MASSCLOUDS 2013 All Rights Reserved
 */
package com.massclouds.ns.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * <p>数据对象的基类。<p>
 * 
 * 创建日期 2013-7-27<br>
 * @author li_ming<br>
 */
public abstract class AbstractModel implements java.io.Serializable {

	private static final long serialVersionUID = -187509355561339309L;

	/*
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
