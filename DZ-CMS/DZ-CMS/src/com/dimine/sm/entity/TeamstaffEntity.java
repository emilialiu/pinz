package com.dimine.sm.entity;

import com.dimine.base.entity.BaseEntity;

/**
 * 班组对应人员信息类
 * 
 * @author dimine 2015-06-16 17:53:39
 * 
 */
public class TeamstaffEntity extends BaseEntity {

	/** 班组ID */
	private String teamid;
	/** 员工ID */
	private String staffid;
	private String staffname;
	private String deptid;

	public String getTeamid() {
		return teamid;
	}

	public void setTeamid(String teamid) {
		this.teamid = teamid;
	}

	public String getStaffid() {
		return staffid;
	}

	public void setStaffid(String staffid) {
		this.staffid = staffid;
	}

	public String getStaffname() {
		return staffname;
	}

	public void setStaffname(String staffname) {
		this.staffname = staffname;
	}

	public String getDeptid() {
		return deptid;
	}

	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}

}
