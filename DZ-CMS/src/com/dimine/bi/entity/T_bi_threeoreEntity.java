package com.dimine.bi.entity;

import com.dimine.base.entity.BaseEntity;

/**
 * 三级矿量信息表信息类
 * 
 * @author dimine 2017-08-15 13:43:48
 * 
 */
public class T_bi_threeoreEntity extends BaseEntity {

	/** 三级矿量信息ID */
	private String threeoreid = "";
	/** 矿山ID */
	private String mineid = "";
	/** 所属单元ID */
	private String mineunitid = "";
	/** 年份 */
	private String yearvalue = "";
	/** 金属元素KZ */
	private String element = "";
	/** 保有量（t） */
	private String reclamount = "";
	/** 采准量（t） */
	private String expamount = "";
	/** 可采量（t） */
	private String extamount = "";
	/** 保有期限（月） */
	private String byqx ;
	/** 备注 */
	private String memo = "";
	/** 创建人 */
	private String createid = "";
	/** 创建时间 */
	private String createdate = "";
	/** 修改人 */
	private String modifyid = "";
	/** 修改时间 */
	private String modifydate = "";

	public String getThreeoreid() {
		return threeoreid;
	}
	public void setThreeoreid(String threeoreid) {
		this.threeoreid = threeoreid;
	}
	public String getMineid() {
		return mineid;
	}
	public void setMineid(String mineid) {
		this.mineid = mineid;
	}
	public String getMineunitid() {
		return mineunitid;
	}
	public void setMineunitid(String mineunitid) {
		this.mineunitid = mineunitid;
	}
	public String getYearvalue() {
		return yearvalue;
	}
	public void setYearvalue(String yearvalue) {
		this.yearvalue = yearvalue;
	}
	public String getElement() {
		return element;
	}
	public void setElement(String element) {
		this.element = element;
	}
	public String getReclamount() {
		return reclamount;
	}
	public void setReclamount(String reclamount) {
		this.reclamount = reclamount;
	}
	public String getExpamount() {
		return expamount;
	}
	public void setExpamount(String expamount) {
		this.expamount = expamount;
	}
	public String getExtamount() {
		return extamount;
	}
	public void setExtamount(String extamount) {
		this.extamount = extamount;
	}
	public String getByqx() {
		return byqx;
	}
	public void setByqx(String byqx) {
		this.byqx = byqx;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
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

}
