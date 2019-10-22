package com.dimine.taglib.entity;

public class ZtreeEntity {
	//节点id
	private String ztreeid;
	//名称
	private String ztreetext;
	//表名
	private String tablename;
	//参数值
	private String parameter;
	//父节点
	private String parentid;
	//子节点个数
	private String childcount;
	//父节点值
	private String parent;
	//节点id值
	private String id;
	//节点name值
	private String name;
	
	public String getZtreeid() {
		return ztreeid;
	}
	public void setZtreeid(String ztreeid) {
		this.ztreeid = ztreeid;
	}
	public String getZtreetext() {
		return ztreetext;
	}
	public void setZtreetext(String ztreetext) {
		this.ztreetext = ztreetext;
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
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
