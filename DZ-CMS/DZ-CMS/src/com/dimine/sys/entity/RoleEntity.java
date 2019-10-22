package com.dimine.sys.entity;

import com.dimine.base.entity.BaseEntity;

/**
 * 角色信息类
 * 
 * @author 徐飞雄（dominic）-多米
 * 
 */
public class RoleEntity extends BaseEntity {

	// 角色id
	private String roleid = "";
	// 角色名称
	private String rolename = "";
	// 描述
	private String memo = "";
	// acgi安全机制的
	private String acginame = "";
	// 用户ID
	private String userid = "";
	// 机构ID
	private String deptid = "";
	// 参数
	private String param = "";
	// 高级查询
	private String query = "";


	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getAcginame() {
		return acginame;
	}

	public void setAcginame(String acginame) {
		this.acginame = acginame;
	}

	public String getDeptid() {
		return deptid;
	}

	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
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
