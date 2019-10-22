package com.dimine.taglib.entity;

public class ComboEntity {
	//主键
	private String comboid;
	//名称
	private String combotext;
	//表名
	private String tablename;
	//参数值
	private String parameter;
	//查询的functioncode
	private String comcode;
	
	
	public String getComboid() {
		return comboid;
	}
	public void setComboid(String comboid) {
		this.comboid = comboid;
	}
	public String getCombotext() {
		return combotext;
	}
	public void setCombotext(String combotext) {
		this.combotext = combotext;
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
	public String getComcode() {
		return comcode;
	}
	public void setComcode(String comcode) {
		this.comcode = comcode;
	}
	
	
}
