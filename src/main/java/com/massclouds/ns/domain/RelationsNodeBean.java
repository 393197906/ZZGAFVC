package com.massclouds.ns.domain;

import java.io.Serializable;

/**
 * 
 * <p>关系节点数据。<p>
 *
 * 创建日期 2016年4月12日<br>
 * @author  wang_qiang<br>
 */
public class RelationsNodeBean implements Serializable{

	private static final long serialVersionUID = 1L;
	//节点id
	private  String id = "";
	//节点名称
	private String name = "";
	//节点的值
	private String value = "";
	//节点的描述
	private String description = "";
	//节点图片地址
	private String imgUrl = "";
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the imgUrl
	 */
	public String getImgUrl() {
		return imgUrl;
	}
	/**
	 * @param imgUrl the imgUrl to set
	 */
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	


}
