package com.dimine.pub.entity;

import com.dimine.base.entity.BaseEntity;

/**
 * 通知公告信息类
 * @author lijun
 * 
 */
public class MessageEntity extends BaseEntity {
	private String messageid="";
	private String messagetitle="";
	private String shorttile="";
	private String mestype="";
	private String mescontent="";
	private String mesfile="";
	private String mestime="";
	private String istop="";
	private String isdel="";
	private String adddate="";
	private String addname="";
	private String updatedate="";
	private String updatename="";
	private String fileurl="";
	private String messagestate;
	private String messagestatename;
	private String userid="";
	private String bizid="";
	private String mid="";

 	
	public String getMessagestate() {
		return messagestate;
	}
	public void setMessagestate(String messagestate) {
		this.messagestate = messagestate;
	}
	public String getMessagestatename() {
		return messagestatename;
	}
	public void setMessagestatename(String messagestatename) {
		this.messagestatename = messagestatename;
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
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getMessageid() {
		return messageid;
	}
	public void setMessageid(String messageid) {
		this.messageid = messageid;
	}
	public String getMessagetitle() {
		return messagetitle;
	}
	public void setMessagetitle(String messagetitle) {
		this.messagetitle = messagetitle;
	}
	public String getShorttile() {
		return shorttile;
	}
	public void setShorttile(String shorttile) {
		this.shorttile = shorttile;
	}
	public String getMestype() {
		return mestype;
	}
	public void setMestype(String mestype) {
		this.mestype = mestype;
	}
	public String getMescontent() {
		return mescontent;
	}
	public void setMescontent(String mescontent) {
		this.mescontent = mescontent;
	}
	public String getMesfile() {
		return mesfile;
	}
	public void setMesfile(String mesfile) {
		this.mesfile = mesfile;
	}
	public String getMestime() {
		return mestime;
	}
	public void setMestime(String mestime) {
		this.mestime = mestime;
	}
	public String getIstop() {
		return istop;
	}
	public void setIstop(String istop) {
		this.istop = istop;
	}
	public String getIsdel() {
		return isdel;
	}
	public void setIsdel(String isdel) {
		this.isdel = isdel;
	}
	public String getAdddate() {
		return adddate;
	}
	public void setAdddate(String adddate) {
		this.adddate = adddate;
	}
	public String getAddname() {
		return addname;
	}
	public void setAddname(String addname) {
		this.addname = addname;
	}
	public String getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(String updatedate) {
		this.updatedate = updatedate;
	}
	public String getUpdatename() {
		return updatename;
	}
	public void setUpdatename(String updatename) {
		this.updatename = updatename;
	}
	public String getFileurl() {
		return fileurl;
	}
	public void setFileurl(String fileurl) {
		this.fileurl = fileurl;
	}
}
