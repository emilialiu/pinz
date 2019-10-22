package com.dimine.sc.entity;

import com.dimine.base.entity.BaseEntity;

/**
 * 业务参数配置信息类
 * 
 * @author dimine 2016-07-04 09:45:39
 * 
 */
public class T_sc_weightparameterEntity extends BaseEntity {

	/** 参数ID */
	private String paraid = "";
	/** 所属矿山 */
	private String tdeptid = "";
	/** 参数编码 */
	private String paracode = "";
	/** 参数名称：岩石体重、矿石体重、松散系数 */
	private String paraname = "";
	/** 单位 */
	private String unit = "";
	/** 参数值 */
	private String paravalue = "";
	/** 是否删除，0,未删除;1表示删除 */
	private String isdel = "";
	/** 创建人 */
	private String createid = "";
	/** 创建日期 */
	private String createdate = "";
	/** 修改人 */
	private String modifyid = "";
	/** 修改日期 */
	private String modifydate = "";
	/** 备注 */
	private String demo = "";

	public String getParaid() {
		return paraid;
	}
	public void setParaid(String paraid) {
		this.paraid = paraid;
	}
	public String getTdeptid() {
		return tdeptid;
	}
	public void setTdeptid(String tdeptid) {
		this.tdeptid = tdeptid;
	}
	public String getParacode() {
		return paracode;
	}
	public void setParacode(String paracode) {
		this.paracode = paracode;
	}
	public String getParaname() {
		return paraname;
	}
	public void setParaname(String paraname) {
		this.paraname = paraname;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getParavalue() {
		return paravalue;
	}
	public void setParavalue(String paravalue) {
		this.paravalue = paravalue;
	}
	public String getIsdel() {
		return isdel;
	}
	public void setIsdel(String isdel) {
		this.isdel = isdel;
	}
	public String getCreateid() {
		return createid;
	}
	public void setCreateid(String createid) {
		this.createid = createid;
	}
	public String getCreatedate() {
		return createdate;
	}
	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	public String getModifyid() {
		return modifyid;
	}
	public void setModifyid(String modifyid) {
		this.modifyid = modifyid;
	}
	public String getModifydate() {
		return modifydate;
	}
	public void setModifydate(String modifydate) {
		this.modifydate = modifydate;
	}
	public String getDemo() {
		return demo;
	}
	public void setDemo(String demo) {
		this.demo = demo;
	}

}
