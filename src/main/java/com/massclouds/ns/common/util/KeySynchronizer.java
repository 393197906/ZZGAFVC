/*
 * Copyright (c) MASSCLOUDS 2013 All Rights Reserved
 */
package com.massclouds.ns.common.util;

import java.util.WeakHashMap;

/**
 * <p>同步器。<p>
 * 
 * 创建日期 2013-7-27<br>
 * @author li_ming<br>
 */
public class KeySynchronizer {
	// 锁对象
	private static final WeakHashMap<Object, Locker> LOCK_MAP = new WeakHashMap<Object, Locker>();

	/**
	 * <p>锁定。<p>
	 * 
	 * 创建日期 2013-7-27<br>
	 * @author li_ming<br>
	 */
	private static class Locker {
		private Locker() {

		}
	}

	/**
	 * 获得锁
	 * @param key
	 * @return
	 */
	public static synchronized Object acquire(Object key) {
		Locker locker = LOCK_MAP.get(key);
		if (locker == null) {
			locker = new Locker();
			LOCK_MAP.put(key, locker);
		}
		return locker;
	}
}
