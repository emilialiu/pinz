package com.dimine.sys.entity;

import com.dimine.base.entity.BaseEntity;

public class ParamEntity extends BaseEntity {

	private String parameterid;
	
	private String loginlogo;
	
	private String loginname;
	
	private String firstlogo;
	
	private String firstname;
	
	private String iscaptcha;

	public String getParameterid() {
		return parameterid;
	}

	public void setParameterid(String parameterid) {
		this.parameterid = parameterid;
	}

	public String getLoginlogo() {
		return loginlogo;
	}

	public void setLoginlogo(String loginlogo) {
		this.loginlogo = loginlogo;
	}

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getFirstlogo() {
		return firstlogo;
	}

	public void setFirstlogo(String firstlogo) {
		this.firstlogo = firstlogo;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getIscaptcha() {
		return iscaptcha;
	}

	public void setIscaptcha(String iscaptcha) {
		this.iscaptcha = iscaptcha;
	}
	
	
}
