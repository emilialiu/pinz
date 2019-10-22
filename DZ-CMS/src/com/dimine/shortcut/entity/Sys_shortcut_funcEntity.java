package com.dimine.shortcut.entity;

import com.dimine.base.entity.BaseEntity;

/**
 * 人员快捷功能信息类
 * 
 * @author dimine 2016-09-09 17:39:35
 * 
 */
public class Sys_shortcut_funcEntity extends BaseEntity {

	/** 用户ID */
	private String userid = "";
	/** 功能 */
	private String funccode = "";
	private String orderno= "";
	private String funcname = "";
	private String funcnameen = "";
	private String url = "";

	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getFunccode() {
		return funccode;
	}
	public void setFunccode(String funccode) {
		this.funccode = funccode;
	}
	public String getFuncname() {
		return funcname;
	}
	public void setFuncname(String funcname) {
		this.funcname = funcname;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getFuncnameen() {
		return funcnameen;
	}
	public void setFuncnameen(String funcnameen) {
		this.funcnameen = funcnameen;
	}
	public String getOrderno() {
		return orderno;
	}
	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}

}
