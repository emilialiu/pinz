package com.dimine.taglib.entity;

public class SelectmemuEntity {

	//id
	private String checkboxid;
	//名称
	private String checkboxtext;
	//表名
	private String tablename;
	//参数值
	private String parameter;
	//父节点
	private String parentid;
	//子节点个数
	private String childcount;
		
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
	public String getParentid() {
		return parentid;
	}
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	public String getChildcount() {
		return childcount;
	}
	public void setChildcount(String childcount) {
		this.childcount = childcount;
	}
}
