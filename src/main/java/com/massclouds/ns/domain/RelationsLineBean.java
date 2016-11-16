package com.massclouds.ns.domain;

import java.io.Serializable;

/**
 * 
 * <p>关系图连线数据<p>
 *
 * 创建日期 2016年4月12日<br>
 * @author  wang_qiang<br>
 */
public class RelationsLineBean implements Serializable{

	private static final long serialVersionUID = 1L;
	//关联节点name属性
	private  String source;
	//自身target属性
	private String target;
	//自身target属性
	private String relation;
	
	/**
	 * @return the source
	 */
	public String getSource() {
		return source;
	}
	/**
	 * @param source the source to set
	 */
	public void setSource(String source) {
		this.source = source;
	}
	/**
	 * @return the target
	 */
	public String getTarget() {
		return target;
	}
	/**
	 * @param target the target to set
	 */
	public void setTarget(String target) {
		this.target = target;
	}
	/**
	 * @return the relation
	 */
	public String getRelation() {
		return relation;
	}
	/**
	 * @param relation the relation to set
	 */
	public void setRelation(String relation) {
		this.relation = relation;
	}
	

	
	}
