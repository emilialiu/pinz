package com.dimine.taglib.entity;

public class CheckboxEntity {
	//主键
	private String checkboxid;
	//名称
	private String checkboxtext;
	//表名
	private String tablename;
	//参数值
	private String parameter;
	public String getCheckboxid() {
		return checkboxid;
	}
	public void setCheckboxid(String checkboxid) {
		this.checkboxid = checkboxid;
	}
	public String getCheckboxtext() {
		return checkboxtext;
	}
	public void setCheckboxtext(String checkboxtext) {
		this.checkboxtext = checkboxtext;
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
