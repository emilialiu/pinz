package com.dimine.sc.entity;

import com.dimine.base.entity.BaseEntity;

/**
 * 矿种表信息类
 * 
 * @author dimine 2017-09-05 10:02:05
 * 
 */
public class T_sc_oretypeEntity extends BaseEntity {

	/** 矿种id */
	private String oretypeid = "";
	/** 矿种(字典) */
	private String oretype = "";
	/** 部门(矿山) */
	private String deptid = "";
	/** 单位(字典) */
	private String unit = "";
	/** 序号 */
	private String indexno = "";
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

	public String getOretypeid() {
		return oretypeid;
	}
	public void setOretypeid(String oretypeid) {
		this.oretypeid = oretypeid;
	}
	public String getOretype() {
		return oretype;
	}
	public void setOretype(String oretype) {
		this.oretype = oretype;
	}
	public String getDeptid() {
		return deptid;
	}
	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getIndexno() {
		return indexno;
	}
	public void setIndexno(String indexno) {
		this.indexno = indexno;
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
