// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   UserEntity.java

package com.dimine.sys.entity;

import java.util.*;

import com.dimine.base.entity.BaseEntity;

public class UserEntity extends BaseEntity {

	public static final String IBATIS_KEY_LOGINNAME_EXIST = "UserEntity.loginnameexist";
	public static final String IBATIS_KEY_UPDATE_PERSONAL_INFO = "UserEntity.update.personalinfo";
	public static final String IBATIS_KEY_GETNAME_BY_ID = "UserEntity.getnamebyid";
	public static final String IBATIS_KEY_ADMIN_LIST = "UserEntity.admin.list";
	public static final String IBATIS_KEY_UPDATE_FROM_DEPT = "UserEntity.updatefromdept";
	public static final String IBATIS_KEY_USERINFO_UPDATEPASSWORD = "UserEntity.updatepassword";
	public static final String IBATIS_KEY_VALIDATECODE_LOGINNAME = "UserEntity.validatecode.loginname";
	private String userid;
	private String username;
	private String sex;
	private String tel;
	private String email;
	private String delflag;
	private Date createdate;
	private String createman;
	private Date modifydate;
	private String modifyman;
	private String loginname;
	private String loginpwd;
	private String userType;
	private String deptid;
	private String deptCode;
	private String address;
	private String tongpeifu;
	private String deptname;
	private String rolename;
	private String query;
	private String memo;

	public UserEntity() {
		userid = "";
		username = "";
		sex = "0";
		tel = "";
		email = "";
		delflag = "0";
		createman = "";
		modifyman = "";
		loginname = "";
		loginpwd = "";
		userType = "1";
		deptid = "";
		deptCode = "";
		address = "";
		tongpeifu = "";
		deptname = "";
		rolename = "";
		query = "";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDelflag() {
		return delflag;
	}

	public void setDelflag(String delflag) {
		this.delflag = delflag;
	}

	public String getCreateman() {
		return createman;
	}

	public void setCreateman(String createman) {
		this.createman = createman;
	}

	public String getModifyman() {
		return modifyman;
	}

	public void setModifyman(String modifyman) {
		this.modifyman = modifyman;
	}

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getLoginpwd() {
		return loginpwd;
	}

	public void setLoginpwd(String loginpwd) {
		this.loginpwd = loginpwd;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getDeptid() {
		return deptid;
	}

	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public Date getModifydate() {
		return modifydate;
	}

	public void setModifydate(Date modifydate) {
		this.modifydate = modifydate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getDeptname() {
		return deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public String getTongpeifu() {
		return tongpeifu;
	}

	public void setTongpeifu(String tongpeifu) {
		this.tongpeifu = tongpeifu;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}
