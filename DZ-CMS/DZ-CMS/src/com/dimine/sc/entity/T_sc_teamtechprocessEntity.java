package com.dimine.sc.entity;

import com.dimine.base.entity.BaseEntity;

/**
 * 工序对应班组信息类
 * 
 * @author dimine 2016-08-11 10:54:09
 * 
 */
public class T_sc_teamtechprocessEntity extends BaseEntity {

	/** 班组ID */
	private String teamid = "";
	/** 工序ID */
	private String procid = "";
	/** 所属矿山 */
	private String tdeptid = "";
	/** 班组名字 */
	private String teamname = "";
	/** 班组类型名 */
	private String teamtypename = "";

	public String getTeamid() {
		return teamid;
	}
	public void setTeamid(String teamid) {
		this.teamid = teamid;
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
	public String getTeamname() {
		return teamname;
	}
	public void setTeamname(String teamname) {
		this.teamname = teamname;
	}
	public String getTeamtypename() {
		return teamtypename;
	}
	public void setTeamtypename(String teamtypename) {
		this.teamtypename = teamtypename;
	}
	
}
