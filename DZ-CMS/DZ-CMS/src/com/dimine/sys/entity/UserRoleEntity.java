package com.dimine.sys.entity;

import com.dimine.base.entity.BaseEntity;

/**
 * 用户角色类
 * 
 * @author LCF
 */
public class UserRoleEntity extends BaseEntity {
	/**
	 * UserRoleEntity.acgilistbyuserid
	 */
	public static final String IBAITSI_KEY_USERROLE_ACGI_LIST_BY_USERID = "UserRoleEntity.acgilistbyuserid";

	/**
	 * UserRoleEntity.listbyuserid
	 */
	public static final String IBAITSI_KEY_USERROLE_LIST_BY_USERID = "UserRoleEntity.listbyuserid";

	/**
	 * UserRoleEntity.deletebyuserid
	 */
	public static final String IBAITSI_KEY_USERROLE_DELETE_ALL_BY_USERID = "UserRoleEntity.deletebyuserid";

	/**
	 * UserRoleEntity.rolelistbyroleid
	 */
	public static final String IBAITSI_KEY_USERROLE_ROLE_LIST_BY_ROLEID = "UserRoleEntity.rolelistbyroleid";

	/**
	 * UserRoleEntity.delete
	 */
	public static final String IBAITSI_KEY_USERROLE_DELETE = "UserRoleEntity.delete";

	// 用户id
	private String userid = "";

	// 角色编号
	private String roleid = "";

	// 角色名称
	private String rolename = "";

	// 部门ID
	private String deptid = "";
	
	private String query = "";

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

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

	public String getDeptid() {
		return deptid;
	}

	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}
	
}
