package com.dimine.pub.entity;

import com.dimine.base.entity.BaseEntity;

/**
 * 待办接收人管理信息类
 * 
 * @author dimine 2017-11-16 15:35:42
 * 
 */
public class T_pub_messagevistEntity extends BaseEntity {

	/** 主键mid */
	private String mid = "";
	/** 待办接收人id */
	private String userid = "";
	/** 业务id */
	private String bizid = "";
	/** 查看日期 */
	private String visiterdate = "";
	/** 消息状态 */
	private String messagestate = "";
	/** 查看次数 */
	private String visitertime = "";

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getBizid() {
		return bizid;
	}

	public void setBizid(String bizid) {
		this.bizid = bizid;
	}

	public String getVisiterdate() {
		return visiterdate;
	}

	public void setVisiterdate(String visiterdate) {
		this.visiterdate = visiterdate;
	}

	public String getMessagestate() {
		return messagestate;
	}

	public void setMessagestate(String messagestate) {
		this.messagestate = messagestate;
	}

	public String getVisitertime() {
		return visitertime;
	}

	public void setVisitertime(String visitertime) {
		this.visitertime = visitertime;
	}

}
