package com.dimine.taglib.entity;

public class AutocompleteEntity {
	//主键
	private String autoid;
	//名称
	private String autotext;
	//表名
	private String tablename;
	//参数
	private String parameter;
	//模糊查询条件
	private String controlval;
	//
	private String maxsize;
	
	public String getAutoid() {
		return autoid;
	}
	public void setAutoid(String autoid) {
		this.autoid = autoid;
	}
	public String getAutotext() {
		return autotext;
	}
	public void setAutotext(String autotext) {
		this.autotext = autotext;
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
	public String getControlval() {
		return controlval;
	}
	public void setControlval(String controlval) {
		this.controlval = controlval;
	}
	public String getMaxsize() {
		return maxsize;
	}
	public void setMaxsize(String maxsize) {
		this.maxsize = maxsize;
	}
	
}
