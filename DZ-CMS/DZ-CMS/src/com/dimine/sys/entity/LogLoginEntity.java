// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   LogLoginEntity.java
package com.dimine.sys.entity;
import com.dimine.base.entity.BaseEntity;

public class LogLoginEntity extends BaseEntity
{

	public static final String IBATIS_KEY_EXPORT_LIST = "LogLoginEntity.export.list";
	public static final String IBATIS_KEY_DELETE = "LogLoginEntity.delete";
	public static final String IBATIS_KEY_DELETE_ALL = "LogLoginEntity.deleteAll";
	private String logid;
	private String userid;
	private String loginname;
	private String loginip;
	private String logintype;
	private String loginflag;
	private String deptid;
	private String loginmessage;
	private String startTime;
	private String endTime;
	private String logintime;
	private String tongpeifu;
	private String deptname;
	
	// 参数
		private String param = "";
		// 高级查询
		private String query = "";

	public LogLoginEntity()
	{
		logid = "";
		userid = "";
		loginname = "";
		loginip = "";
		logintype = "";
		loginflag = "";
		deptid = "";
		loginmessage = "";
		startTime = "";
		endTime = "";
		tongpeifu = "";
		param = "";
		query = "";
	}

	public String getLogid() {
		return logid;
	}

	public void setLogid(String logid) {
		this.logid = logid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getLoginip() {
		return loginip;
	}

	public void setLoginip(String loginip) {
		this.loginip = loginip;
	}

	public String getLogintype() {
		return logintype;
	}

	public void setLogintype(String logintype) {
		this.logintype = logintype;
	}

	public String getLoginflag() {
		return loginflag;
	}

	public void setLoginflag(String loginflag) {
		this.loginflag = loginflag;
	}

	public String getDeptid() {
		return deptid;
	}

	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}

	public String getLoginmessage() {
		return loginmessage;
	}

	public void setLoginmessage(String loginmessage) {
		this.loginmessage = loginmessage;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getLogintime() {
		return logintime;
	}

	public void setLogintime(String logintime) {
		this.logintime = logintime;
	}

	public String getTongpeifu() {
		return tongpeifu;
	}

	public void setTongpeifu(String tongpeifu) {
		this.tongpeifu = tongpeifu;
	}

	public String getDeptname() {
		return deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}
   
	
}
