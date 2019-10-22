package com.dimine.activity.entity;

import com.dimine.base.entity.BaseEntity;

public class BuinessProcessEntity extends BaseEntity {
	//业务id
	private String bussinesskey;
	//流程实例id
	private String processinstanceid;
	//链接地址
	private String uri;
	public String getBussinesskey() {
		return bussinesskey;
	}
	public void setBussinesskey(String bussinesskey) {
		this.bussinesskey = bussinesskey;
	}
	public String getProcessinstanceid() {
		return processinstanceid;
	}
	public void setProcessinstanceid(String processinstanceid) {
		this.processinstanceid = processinstanceid;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	
}
