package com.dimine.sc.entity;

import com.dimine.base.entity.BaseEntity;

/**
 * 工艺对应工序信息类
 * 
 * @author dimine 2016-07-13 16:04:47
 * 
 */
public class T_sc_techprocessEntity extends BaseEntity {

	/** 工艺ID */
	private String techid = "";
	/** 工序ID */
	private String procid = "";
	/** 所属矿山 */
	private String tdeptid = "";
	/** 序号 */
	private String serialno = "";
	/** 计划阶段使用：0表示不使用，1表示使用 */
	private String isplan = "";
	/** 验收阶段使用：0表示不使用，1表示使用 */
	private String isaccept = "";
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
	/** 工序名称 */
	private String processname = "";
	/** 上级工序 */
	private String parentprocess = "";
	
	public String getTechid() {
		return techid;
	}
	public void setTechid(String techid) {
		this.techid = techid;
	}
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
	public String getSerialno() {
		return serialno;
	}
	public void setSerialno(String serialno) {
		this.serialno = serialno;
	}
	public String getIsplan() {
		return isplan;
	}
	public void setIsplan(String isplan) {
		this.isplan = isplan;
	}
	public String getIsaccept() {
		return isaccept;
	}
	public void setIsaccept(String isaccept) {
		this.isaccept = isaccept;
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
	public String getProcessname() {
		return processname;
	}
	public void setProcessname(String processname) {
		this.processname = processname;
	}
	public String getParentprocess() {
		return parentprocess;
	}
	public void setParentprocess(String parentprocess) {
		this.parentprocess = parentprocess;
	}
	
}
