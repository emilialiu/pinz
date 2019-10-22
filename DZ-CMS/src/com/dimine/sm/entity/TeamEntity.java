package com.dimine.sm.entity;

import com.dimine.base.entity.BaseEntity;

/**
 * 班组信息类
 * 
 * @author dimine 2015-06-16 17:53:00
 * 
 */
public class TeamEntity extends BaseEntity {

	/** 班组ID */
	private String teamid;
	/** 所属项目部 */
	private String tdeptid;
	/** 组织机构ID */
	private String deptid;
	private String deptname;
	/** 班组编号 */
	private String teamcode;
	/** 班组类型：掘进班组、采矿班组、支护班组 */
	private String teamtype;
	private String teamtypename;
	/** 班组名称 */
	private String teamname;
	/** 班组责任人 */
	private String dutyman;
	private String dutymanname;
	/** 人数 */
	private String peoplenum;
	/** 联系电话 */
	private String phone;
	/** 删除标识 */
	private String delflag;
	/** 创建人 */
	private String createid;
	/** 创建日期 */
	private String createdate;
	/** 修改人 */
	private String modifyid;
	/** 修改日期 */
	private String modifydate;
	/** 备注 */
	private String memo;
	private String bistype;//业务类型
	private String procid;//工序ID
	public String getTeamid() {
		return teamid;
	}

	public void setTeamid(String teamid) {
		this.teamid = teamid;
	}

	public String getTdeptid() {
		return tdeptid;
	}

	public void setTdeptid(String tdeptid) {
		this.tdeptid = tdeptid;
	}

	public String getDeptid() {
		return deptid;
	}

	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}

	public String getTeamcode() {
		return teamcode;
	}

	public void setTeamcode(String teamcode) {
		this.teamcode = teamcode;
	}

	public String getTeamtype() {
		return teamtype;
	}

	public void setTeamtype(String teamtype) {
		this.teamtype = teamtype;
	}

	public String getTeamname() {
		return teamname;
	}

	public void setTeamname(String teamname) {
		this.teamname = teamname;
	}

	public String getDutyman() {
		return dutyman;
	}

	public void setDutyman(String dutyman) {
		this.dutyman = dutyman;
	}

	public String getPeoplenum() {
		return peoplenum;
	}

	public void setPeoplenum(String peoplenum) {
		this.peoplenum = peoplenum;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDelflag() {
		return delflag;
	}

	public void setDelflag(String delflag) {
		this.delflag = delflag;
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

	public String getDeptname() {
		return deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	public String getTeamtypename() {
		return teamtypename;
	}

	public void setTeamtypename(String teamtypename) {
		this.teamtypename = teamtypename;
	}

	public String getDutymanname() {
		return dutymanname;
	}

	public void setDutymanname(String dutymanname) {
		this.dutymanname = dutymanname;
	}

	public String getBistype() {
		return bistype;
	}

	public void setBistype(String bistype) {
		this.bistype = bistype;
	}

	public String getProcid() {
		return procid;
	}

	public void setProcid(String procid) {
		this.procid = procid;
	}

}
