package com.dimine.sys.entity;

import com.dimine.base.entity.BaseEntity;

/**
 * EXCEL导入属性对应关系表信息类
 * 
 * @author dimine 2014-12-18 15:53:05
 * 
 */
public class Sys_modelmappEntity extends BaseEntity {

	/** 映射关系ID */
	private String mid ;
	/** 模版ID */
	private String modelid ;
	/** excel列名称 */
	private String ecolname ;
	/** excel列序号 */
	private String ecolindex ;
	/** 映射字段 */
	private String mfield ;
	/** 是否为空 0不为空 1空 */
	private String isnull ;
	/** 是否转码 0不转码 1 转码 */
	private String isscode ;
	/** 转码列的父类 */
	private String dicttypeid ;
	/** 数据类型（字典：数字，字符串等） */
	private String dtype ;

	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getModelid() {
		return modelid;
	}
	public void setModelid(String modelid) {
		this.modelid = modelid;
	}
	public String getEcolname() {
		return ecolname;
	}
	public void setEcolname(String ecolname) {
		this.ecolname = ecolname;
	}
	public String getEcolindex() {
		return ecolindex;
	}
	public void setEcolindex(String ecolindex) {
		this.ecolindex = ecolindex;
	}
	public String getMfield() {
		return mfield;
	}
	public void setMfield(String mfield) {
		this.mfield = mfield;
	}
	public String getIsnull() {
		return isnull;
	}
	public void setIsnull(String isnull) {
		this.isnull = isnull;
	}
	public String getIsscode() {
		return isscode;
	}
	public void setIsscode(String isscode) {
		this.isscode = isscode;
	}
	public String getDicttypeid() {
		return dicttypeid;
	}
	public void setDicttypeid(String dicttypeid) {
		this.dicttypeid = dicttypeid;
	}
	public String getDtype() {
		return dtype;
	}
	public void setDtype(String dtype) {
		this.dtype = dtype;
	}

}
