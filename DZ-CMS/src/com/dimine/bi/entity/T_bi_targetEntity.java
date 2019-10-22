package com.dimine.bi.entity;

import com.dimine.base.entity.BaseEntity;

/**
 * 指标信息信息类
 * 
 * @author dimine 2016-07-04 11:23:07
 * 
 */
public class T_bi_targetEntity extends BaseEntity {

	/** 指标ID */
	private String targetid = "";
	/** 工序ID */
	private String procid = "";
	/**工序名称*/
	private String proctname = "";
	/**上级工序ID*/
	private String parentid = "";
	/**上级工序名称*/
	private String parentname = "";
	/** 指标名称 */
	private String targetname = "";
	/**指标英文名称*/
	private String targetnameen = "";
	/** 简称 */
	private String shortname = "";
	/** 指标单位 */
	private String targetunit = "";
	/** 指标单位 名称*/
	private String targetunitname = "";
	/** 是否启用，0,未启用;1表示启用 */
	private String isused = "";
	/** 是否删除，0,未删除;1表示删除 */
	private String isdel = "";
	/** 是否可编辑,0,不可编辑;1表示能编辑 */
	private String isedit = "";
	/** 是否汇总，0,不能汇总;1表示能汇总 */
	private String issum = "";
	/** 是否必填，0,不必填;1表示必填 */
	private String ismust = "";
	/** 数据类型 */
	private String datatype = "";
	/** 年计划阶段使用：0表示不使用，1表示使用 */
	private String isyplan = "";
	/** 月计划阶段使用：0表示不使用，1表示使用 */
	private String ismplan = "";
	/** 生产台账阶段使用：0表示不使用，1表示使用 */
	private String isproduce = "";
	/** 验收阶段使用：0表示不使用，1表示使用 */
	private String isaccept = "";
	//公式
	private String talgorithm="";
	//公式中文描述
	private String tal_desc="";
	/** 备注 */
	private String memo = "";
	/** 创建人 */
	private String createid = "";
	/** 创建日期 */
	private String createdate = "";
	/** 修改人 */
	private String modifyid = "";
	/** 修改日期 */
	private String modifydate = "";
	/** 序号 */
	private String serialno = "";
	/** 是否显示 */
	private String isdisplay = "";
	/** 获取数据类型 */
	private String hqdatatype = "";
	/** 是否触发事件 */
	private String isevent = "";
	/** 是否主指标 */
	private String ismaintarget = "";
	private String stageid = "";

	public String getTargetid() {
		return targetid;
	}
	public void setTargetid(String targetid) {
		this.targetid = targetid;
	}
	public String getProcid() {
		return procid;
	}
	public void setProcid(String procid) {
		this.procid = procid;
	}
	public String getTargetname() {
		return targetname;
	}
	public void setTargetname(String targetname) {
		this.targetname = targetname;
	}
	public String getTargetnameen() {
		return targetnameen;
	}
	public void setTargetnameen(String targetnameen) {
		this.targetnameen = targetnameen;
	}
	public String getShortname() {
		return shortname;
	}
	public void setShortname(String shortname) {
		this.shortname = shortname;
	}
	public String getTargetunit() {
		return targetunit;
	}
	public void setTargetunit(String targetunit) {
		this.targetunit = targetunit;
	}
	public String getIsused() {
		return isused;
	}
	public void setIsused(String isused) {
		this.isused = isused;
	}
	public String getIsdel() {
		return isdel;
	}
	public void setIsdel(String isdel) {
		this.isdel = isdel;
	}
	public String getIsedit() {
		return isedit;
	}
	public void setIsedit(String isedit) {
		this.isedit = isedit;
	}
	public String getIssum() {
		return issum;
	}
	public void setIssum(String issum) {
		this.issum = issum;
	}
	public String getIsmust() {
		return ismust;
	}
	public void setIsmust(String ismust) {
		this.ismust = ismust;
	}
	public String getDatatype() {
		return datatype;
	}
	public void setDatatype(String datatype) {
		this.datatype = datatype;
	}
	public String getIsyplan() {
		return isyplan;
	}
	public void setIsyplan(String isyplan) {
		this.isyplan = isyplan;
	}
	public String getIsmplan() {
		return ismplan;
	}
	public void setIsmplan(String ismplan) {
		this.ismplan = ismplan;
	}
	public String getIsproduce() {
		return isproduce;
	}
	public void setIsproduce(String isproduce) {
		this.isproduce = isproduce;
	}
	public String getIsaccept() {
		return isaccept;
	}
	public void setIsaccept(String isaccept) {
		this.isaccept = isaccept;
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
	public String getProctname() {
		return proctname;
	}
	public void setProctname(String proctname) {
		this.proctname = proctname;
	}
	public String getParentid() {
		return parentid;
	}
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	public String getParentname() {
		return parentname;
	}
	public void setParentname(String parentname) {
		this.parentname = parentname;
	}
	public String getTargetunitname() {
		return targetunitname;
	}
	public void setTargetunitname(String targetunitname) {
		this.targetunitname = targetunitname;
	}
	public String getSerialno() {
		return serialno;
	}
	public void setSerialno(String serialno) {
		this.serialno = serialno;
	}
	public String getIsdisplay() {
		return isdisplay;
	}
	public void setIsdisplay(String isdisplay) {
		this.isdisplay = isdisplay;
	}
	public String getHqdatatype() {
		return hqdatatype;
	}
	public void setHqdatatype(String hqdatatype) {
		this.hqdatatype = hqdatatype;
	}
	public String getIsevent() {
		return isevent;
	}
	public void setIsevent(String isevent) {
		this.isevent = isevent;
	}
	public String getTalgorithm() {
		return talgorithm;
	}
	public void setTalgorithm(String talgorithm) {
		this.talgorithm = talgorithm;
	}
	public String getTal_desc() {
		return tal_desc;
	}
	public void setTal_desc(String tal_desc) {
		this.tal_desc = tal_desc;
	}
	public String getIsmaintarget() {
		return ismaintarget;
	}
	public void setIsmaintarget(String ismaintarget) {
		this.ismaintarget = ismaintarget;
	}
	public String getStageid() {
		return stageid;
	}
	public void setStageid(String stageid) {
		this.stageid = stageid;
	}
	
}
