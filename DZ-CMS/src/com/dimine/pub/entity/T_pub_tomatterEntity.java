package com.dimine.pub.entity;

import com.dimine.base.entity.BaseEntity;

/**
 * 待办事项管理信息类
 * 
 * @author dimine 2017-11-16 15:35:42
 * 
 */
public class T_pub_tomatterEntity extends BaseEntity {

	/** id */
	private String matterid = "";
	/** 事项名称 */
	private String mattername = "";
	/** 发起时间 */
	private String addtime = "";
	/** 事项状态 */
	private String mattertype = "";
	/** 备注 */
	private String memo = "";
	/** 链接地址 */
	private String linkurl = "";
	/** 处理时间 */
	private String handletime = "";
	/** 处理人 */
	private String handlename = "";
	private String mid ="";
	private String userid="";
	private String mattertypename="";
	
	public String getMattertypename() {
		return mattertypename;
	}
	public void setMattertypename(String mattertypename) {
		this.mattertypename = mattertypename;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getMatterid() {
		return matterid;
	}
	public void setMatterid(String matterid) {
		this.matterid = matterid;
	}
	public String getMattername() {
		return mattername;
	}
	public void setMattername(String mattername) {
		this.mattername = mattername;
	}
	public String getAddtime() {
		return addtime;
	}
	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}
	public String getMattertype() {
		return mattertype;
	}
	public void setMattertype(String mattertype) {
		this.mattertype = mattertype;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getLinkurl() {
		return linkurl;
	}
	public void setLinkurl(String linkurl) {
		this.linkurl = linkurl;
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

}
