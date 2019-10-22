package com.dimine.bogen.model;

import com.dimine.base.entity.BaseEntity;

public class BeyondCAEntity extends BaseEntity{
	private String ip; //数据库服务器地址
	private String sid; //数据库服务器SID
	private String username; //用户名
	private String password; //密码
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}

}
