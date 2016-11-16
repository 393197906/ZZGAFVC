package com.massclouds.ns.domain;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * <p>关系节点数据。<p>
 *
 * 创建日期 2016年4月12日<br>
 * @author  wang_qiang<br>
 */
public class NodeBean implements Serializable{

	private static final long serialVersionUID = 1L;
	//节点id
	private  String id = "";
	//节点名称
	private String name = "";
	//节点的值
	private String value = "";
	//节点图片地址
	private String image = "";
	//自己点数据
	private List<ChildrenBean> children = null;
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
	 * @return the image
	 */
	public String getImage() {
		return image;
	}
	/**
	 * @param image the image to set
	 */
	public void setImage(String image) {
		this.image = image;
	}
	/**
	 * @return the children
	 */
	public List<ChildrenBean> getChildren() {
		return children;
	}
	/**
	 * @param children the children to set
	 */
	public void setChildren(List<ChildrenBean> children) {
		this.children = children;
	}

	

}
