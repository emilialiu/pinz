package com.dimine.sc.entity;

import com.dimine.base.entity.BaseEntity;

/**
 * 工序信息信息类
 * 
 * @author dimine 2016-07-04 11:14:52
 * 
 */
public class T_sc_processEntity extends BaseEntity {

	/** 工序ID */
	private String procid = "";
	/** 所属矿山 */
	private String tdeptid = "";
	/** 工序名称 */
	private String proctname = "";
	/** 工序英文名称 */
	private String proctnameen = "";
	/** 工序编码 */
	private String proccode = "";
	/** 简称 */
	private String shortname = "";
	/** 上级工序 */
	private String parentid = "";
	/** 是否删除，0,未删除;1表示删除 */
	private String isdel = "";
	/** 是否叶子节点 */
	private String isleaf = "";
	/** 上级编码拼串 */
	private String parentstr = "";
	/** 序号 */
	private String serialno = "";
	/** 创建人 */
	private String createid = "";
	/** 创建日期 */
	private String createdate = "";
	/** 修改人 */
	private String modifyid = "";
	/** 修改日期 */
	private String modifydate = "";
	/** 备注 */
	private String memo = "";
	/** 儿子数量*/
	private String childCount="";
	/** 上级工序名称*/
	private String parentname="";
	//班组id
	private String teamid="";
	private String techid="";


	public String getProcid() {
		return procid;
	}
	public void setProcid(String procid) {
		this.procid = procid;
	}
	public String getTdeptid() {
		return tdeptid;
	}
	public void setTdeptid(String tdeptid) {
		this.tdeptid = tdeptid;
	}
	public String getProctname() {
		return proctname;
	}
	public void setProctname(String proctname) {
		this.proctname = proctname;
	}
	public String getProctnameen() {
		return proctnameen;
	}
	public void setProctnameen(String proctnameen) {
		this.proctnameen = proctnameen;
	}
	public String getProccode() {
		return proccode;
	}
	public void setProccode(String proccode) {
		this.proccode = proccode;
	}
	public String getShortname() {
		return shortname;
	}
	public void setShortname(String shortname) {
		this.shortname = shortname;
	}
	public String getParentid() {
		return parentid;
	}
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	public String getIsdel() {
		return isdel;
	}
	public void setIsdel(String isdel) {
		this.isdel = isdel;
	}
	public String getIsleaf() {
		return isleaf;
	}
	public void setIsleaf(String isleaf) {
		this.isleaf = isleaf;
	}
	public String getParentstr() {
		return parentstr;
	}
	public void setParentstr(String parentstr) {
		this.parentstr = parentstr;
	}
	public String getSerialno() {
		return serialno;
	}
	public void setSerialno(String serialno) {
		this.serialno = serialno;
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
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getChildCount() {
		return childCount;
	}
	public void setChildCount(String childCount) {
		this.childCount = childCount;
	}
	public String getParentname() {
		return parentname;
	}
	public void setParentname(String parentname) {
		this.parentname = parentname;
	}
	public String getTechid() {
		return techid;
	}
	public void setTechid(String techid) {
		this.techid = techid;
	}
	public String getTeamid() {
		return teamid;
	}
	public void setTeamid(String teamid) {
		this.teamid = teamid;
	}
	
}
