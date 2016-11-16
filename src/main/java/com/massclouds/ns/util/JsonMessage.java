package com.massclouds.ns.util;

import java.io.Serializable;

/**
 * JSON数据模型
 * 用户后台向前台返回的JSON对象
 * @author wang_qiang
 * @date 2016年04月11日
 */
public class JsonMessage implements Serializable {

	private static final long serialVersionUID = 1L;
	//处理结果
	private String result = "OK";
	//处理结果信息
	private String msg = null;
	//其他内容(节点)
	private Object node = null;
	//其他内容(连线)
	private Object line = null;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getNode() {
		return node;
	}

	public void setNode(Object node) {
		this.node = node;
	}

	public Object getLine() {
		return line;
	}

	public void setLine(Object line) {
		this.line = line;
	}

}
