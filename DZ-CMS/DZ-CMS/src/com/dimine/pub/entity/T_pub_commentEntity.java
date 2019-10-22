package com.dimine.pub.entity;

import com.dimine.base.entity.BaseEntity;

/**
 * 评论信息（T_PUB_COMMENT）信息类
 * 
 * @author dimine 2016-09-28 10:53:47
 * 
 */
public class T_pub_commentEntity extends BaseEntity {

	/** 评论id */
	private String commid = "";
	/** 所属矿山 */
	private String deptid = "";
	/** 评论内容 */
	private String commmemo = "";
	/** 1，年计划；2，月计划，3，周计划，4，调度台账，5，验收 */
	private String commtype = "";
	/** 业务id */
	private String busiid = "";
	/** 评论时间 */
	private String commtime = "";
	/** 评论人 */
	private String commman = "";
	//评论人名字
	private String username="";

	public String getCommid() {
		return commid;
	}
	public void setCommid(String commid) {
		this.commid = commid;
	}
	public String getDeptid() {
		return deptid;
	}
	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}
	public String getCommmemo() {
		return commmemo;
	}
	public void setCommmemo(String commmemo) {
		this.commmemo = commmemo;
	}
	public String getCommtype() {
		return commtype;
	}
	public void setCommtype(String commtype) {
		this.commtype = commtype;
	}
	public String getBusiid() {
		return busiid;
	}
	public void setBusiid(String busiid) {
		this.busiid = busiid;
	}
	public String getCommtime() {
		return commtime;
	}
	public void setCommtime(String commtime) {
		this.commtime = commtime;
	}
	public String getCommman() {
		return commman;
	}
	public void setCommman(String commman) {
		this.commman = commman;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
}
