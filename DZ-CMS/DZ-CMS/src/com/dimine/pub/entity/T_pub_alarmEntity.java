package com.dimine.pub.entity;

import java.util.List;

import com.dimine.base.entity.BaseEntity;

/**
 * 告警信息管理信息类
 * 
 * @author dimine 2017-10-26 14:27:39
 * 
 */
public class T_pub_alarmEntity extends BaseEntity {

	/** id */
	private String alarmid = "";
	/** 标题 */
	private String alarmtitle = "";
	/** 类别 */
	private String alarmtype = "";
	/** 等级 */
	private String alarmlevel = "";
	/** 时间 */
	private String addtime = "";
	/** 备注 */
	private String memo = "";
	/** 处理状态 */
	private String handletype = "";
	/** 处理时间 */
	private String handletime = "";
	/** 处理人 */
	private String handlename = "";
	/** 处理说明 */
	private String handlecontent = "";
	/** 链接地址 */
	private String handleaddres = "";
	private String userid="";
	private String bizid="";
	private String mid="";
	private String matterid="";
	
	
	private String ddzg="";
	private String ckgcs="";
	private String ddgly="";
	private String ckzr="";
	private String ksfccz="";

	public String getMatterid() {
		return matterid;
	}
	public void setMatterid(String matterid) {
		this.matterid = matterid;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getDdzg() {
		return ddzg;
	}
	public void setDdzg(String ddzg) {
		this.ddzg = ddzg;
	}
	public String getCkgcs() {
		return ckgcs;
	}
	public void setCkgcs(String ckgcs) {
		this.ckgcs = ckgcs;
	}
	public String getDdgly() {
		return ddgly;
	}
	public void setDdgly(String ddgly) {
		this.ddgly = ddgly;
	}
	public String getCkzr() {
		return ckzr;
	}
	public void setCkzr(String ckzr) {
		this.ckzr = ckzr;
	}
	public String getKsfccz() {
		return ksfccz;
	}
	public void setKsfccz(String ksfccz) {
		this.ksfccz = ksfccz;
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
	public String getAlarmid() {
		return alarmid;
	}
	public void setAlarmid(String alarmid) {
		this.alarmid = alarmid;
	}
	public String getAlarmtitle() {
		return alarmtitle;
	}
	public void setAlarmtitle(String alarmtitle) {
		this.alarmtitle = alarmtitle;
	}
	public String getAlarmtype() {
		return alarmtype;
	}
	public void setAlarmtype(String alarmtype) {
		this.alarmtype = alarmtype;
	}
	public String getAlarmlevel() {
		return alarmlevel;
	}
	public void setAlarmlevel(String alarmlevel) {
		this.alarmlevel = alarmlevel;
	}
	public String getAddtime() {
		return addtime;
	}
	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getHandletype() {
		return handletype;
	}
	public void setHandletype(String handletype) {
		this.handletype = handletype;
	}
	public String getHandletime() {
		return handletime;
	}
	public void setHandletime(String handletime) {
		this.handletime = handletime;
	}
	public String getHandlename() {
		return handlename;
	}
	public void setHandlename(String handlename) {
		this.handlename = handlename;
	}
	public String getHandlecontent() {
		return handlecontent;
	}
	public void setHandlecontent(String handlecontent) {
		this.handlecontent = handlecontent;
	}
	public String getHandleaddres() {
		return handleaddres;
	}
	public void setHandleaddres(String handleaddres) {
		this.handleaddres = handleaddres;
	}

}
