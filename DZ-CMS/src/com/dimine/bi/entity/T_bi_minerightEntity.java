package com.dimine.bi.entity;

import com.dimine.base.entity.BaseEntity;

/**
 * 采矿权信息表信息类
 * 
 * @author dimine 2017-08-15 10:39:07
 * 
 */
public class T_bi_minerightEntity extends BaseEntity {

	/** 矿业权ID */
	private String rightid = "";
	/** 合作单位 */
	private String cooperateent = "";
	/** 设计规模 */
	private String planscale = "";
	/** 投资额 */
	private String involume = "";
	/** 矿区范围 */
	private String arearange = "";
	/** 矿权面积(km2) */
	private String mrarea = "";
	/** 矿种 */
	private String mineraltype = "";
	/** 共伴生类型 */
	private String catype = "";
	/** 探明的地质储量 */
	private String verifyreserve = "";
	/** 开采方式 */
	private String mineway = "";
	/** 采矿方法 */
	private String miningmethod = "";
	/** 选矿方法 */
	private String benemethod = "";
	/** 采深上限 */
	private String depthupper = "";
	/** 采深下限 */
	private String depthlower = "";
	/** 采矿权获取方式 */
	private String minegetway = "";
	/** 备注 */
	private String remark = "";

	public String getRightid() {
		return rightid;
	}
	public void setRightid(String rightid) {
		this.rightid = rightid;
	}
	public String getCooperateent() {
		return cooperateent;
	}
	public void setCooperateent(String cooperateent) {
		this.cooperateent = cooperateent;
	}
	public String getPlanscale() {
		return planscale;
	}
	public void setPlanscale(String planscale) {
		this.planscale = planscale;
	}
	public String getInvolume() {
		return involume;
	}
	public void setInvolume(String involume) {
		this.involume = involume;
	}
	public String getArearange() {
		return arearange;
	}
	public void setArearange(String arearange) {
		this.arearange = arearange;
	}
	public String getMrarea() {
		return mrarea;
	}
	public void setMrarea(String mrarea) {
		this.mrarea = mrarea;
	}
	public String getMineraltype() {
		return mineraltype;
	}
	public void setMineraltype(String mineraltype) {
		this.mineraltype = mineraltype;
	}
	public String getCatype() {
		return catype;
	}
	public void setCatype(String catype) {
		this.catype = catype;
	}
	public String getVerifyreserve() {
		return verifyreserve;
	}
	public void setVerifyreserve(String verifyreserve) {
		this.verifyreserve = verifyreserve;
	}
	public String getMineway() {
		return mineway;
	}
	public void setMineway(String mineway) {
		this.mineway = mineway;
	}
	public String getMiningmethod() {
		return miningmethod;
	}
	public void setMiningmethod(String miningmethod) {
		this.miningmethod = miningmethod;
	}
	public String getBenemethod() {
		return benemethod;
	}
	public void setBenemethod(String benemethod) {
		this.benemethod = benemethod;
	}
	public String getDepthupper() {
		return depthupper;
	}
	public void setDepthupper(String depthupper) {
		this.depthupper = depthupper;
	}
	public String getDepthlower() {
		return depthlower;
	}
	public void setDepthlower(String depthlower) {
		this.depthlower = depthlower;
	}
	public String getMinegetway() {
		return minegetway;
	}
	public void setMinegetway(String minegetway) {
		this.minegetway = minegetway;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

}
