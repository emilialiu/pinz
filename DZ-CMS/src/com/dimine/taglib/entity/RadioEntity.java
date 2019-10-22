package com.dimine.taglib.entity;
/**
 * 单选框的实体
 * @author Administrator
 *
 */
public class RadioEntity {
	//主键
	private String radioid;
	//名称
	private String radiotext;
	//表名
	private String tablename;
	//参数值
	private String parameter;
	public String getRadioid() {
		return radioid;
	}
	public void setRadioid(String radioid) {
		this.radioid = radioid;
	}
	public String getRadiotext() {
		return radiotext;
	}
	public void setRadiotext(String radiotext) {
		this.radiotext = radiotext;
	}
	public String getTablename() {
		return tablename;
	}
	public void setTablename(String tablename) {
		this.tablename = tablename;
	}
	public String getParameter() {
		return parameter;
	}
	public void setParameter(String parameter) {
		this.parameter = parameter;
	}
	
}
