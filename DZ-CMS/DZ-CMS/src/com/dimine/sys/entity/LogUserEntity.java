// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   LogUserEntity.java
package com.dimine.sys.entity;
import com.dimine.base.entity.BaseEntity;
public class LogUserEntity extends BaseEntity
{

	public static final String IBATIS_KEY_EXPORT_LIST = "LogUserEntity.export.list";
	public static final String IBATIS_KEY_INSERT = "LogUserEntity.insert";
	private String logid;
	private String userid;
	private String useip;
	private String userip;
	private String usetime;
	private String usemodule;
	private String useoperation;
	private String deptid;
	private String startTime;
	private String endTime;
	private String username;
	private String tongpeifu;
	private String deptname;
	private String usedescrible;
	
	// 参数
	private String param = "";
	// 高级查询
	private String query = "";


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

	public String getUserip() {
		return userip;
	}

	public void setUserip(String userip) {
		this.userip = userip;
	}

	public String getTongpeifu()
	{
		return tongpeifu;
	}

	public void setTongpeifu(String tongpeifu)
	{
		this.tongpeifu = tongpeifu;
	}

	public LogUserEntity()
	{
		logid = "";
		userid = "";
		useip = "";
		usemodule = "";
		useoperation = "";
		deptid = "";
		startTime = "";
		endTime = "";
		username = "";
		tongpeifu = "";
		username="";
	}

	
	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getStartTime()
	{
		return startTime;
	}

	public void setStartTime(String startTime)
	{
		this.startTime = startTime;
	}

	public String getEndTime()
	{
		return endTime;
	}

	public void setEndTime(String endTime)
	{
		this.endTime = endTime;
	}

	public String getLogid()
	{
		return logid;
	}

	public void setLogid(String logid)
	{
		this.logid = logid;
	}

	
	public String getUserid()
	{
		return userid;
	}

	public void setUserid(String userid)
	{
		this.userid = userid;
	}

	 
	public String getUseip() {
		return useip;
	}

	public void setUseip(String useip) {
		this.useip = useip;
	}

	public String getUsetime() {
		return usetime;
	}

	public void setUsetime(String usetime) {
		this.usetime = usetime;
	}

	public String getUsemodule() {
		return usemodule;
	}

	public void setUsemodule(String usemodule) {
		this.usemodule = usemodule;
	}

	public String getUseoperation() {
		return useoperation;
	}

	public void setUseoperation(String useoperation) {
		this.useoperation = useoperation;
	}

	public String getDeptid() {
		return deptid;
	}

	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}

	public String getDeptname() {
		return deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	public String getUsedescrible() {
		return usedescrible;
	}

	public void setUsedescrible(String usedescrible) {
		this.usedescrible = usedescrible;
	}
	
	

	
}
