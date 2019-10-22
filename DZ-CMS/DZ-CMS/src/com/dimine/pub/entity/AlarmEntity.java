package com.dimine.pub.entity;

import com.dimine.base.entity.BaseEntity;

/**
 * 告警信息实体
 * 
 * @author LCF
 * 
 */
public class AlarmEntity extends BaseEntity {
	/** 告警信息ID */
	private String alarmid;
	/** 告警信息名称 */
	private String alarmtitle;
	/** 告警类别 */
	private String alarmtype;
	/** 告警等级 */
	private String alarmlevel;
	/** 发布时间 */
	private String addtime;
	/** 信息内容 */
	private String memo;
	/** 处理状态 */
	private String handletype;
	/** 处理时间 */
	private String handletime;
	/** 处理人员 */
	private String handlename;
	/** 处理说明 */
	private String handlecontent;
	/** 连接地址 */
	private String handleaddres;
	private String userid;//当前用户
	private String alarmtypename;
	


	public String getAlarmtypename() {
		return alarmtypename;
	}

	public void setAlarmtypename(String alarmtypename) {
		this.alarmtypename = alarmtypename;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
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
