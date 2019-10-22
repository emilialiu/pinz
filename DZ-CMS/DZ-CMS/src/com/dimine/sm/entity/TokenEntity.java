package com.dimine.sm.entity;

import com.dimine.base.entity.BaseEntity;

public class TokenEntity extends BaseEntity {
	/** 令牌ID */
	private String tokenid;
	/** 用户ID */
	private String userid;
	/** 部门ID */
	private String deptid;
	/** 登录帐号 */
	private String loginname;
	/** 登录密码 */
	private String loginpwd;
	/** 登录时间 */
	private String logintime;
	/** 创建人 */
	private String createid;
	/** 创建时间 */
	private String createdate;
	/** 备注 */
	private String memo;

	public String getTokenid() {
		return tokenid;
	}

	public void setTokenid(String tokenid) {
		this.tokenid = tokenid;
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

	public String getLogintime() {
		return logintime;
	}

	public void setLogintime(String logintime) {
		this.logintime = logintime;
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

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}
