package com.dimine.pub.entity;

import com.dimine.base.entity.BaseEntity;

/**
 * 待办信息实体
 * 
 * @author LCF
 * 
 */
public class TomatterEntity extends BaseEntity {
	/** 事项ID */
	private String matterid;
	/** 事项名称 */
	private String mattername;
	/** 发布时间 */
	private String addtime;
	/** 处理状态 */
	private String mattertype;
	/** 内容 */
	private String memo;
	/** 跳转地址 */
	private String linkurl;
	/** 处理时间 */
	private String handletime;
	/** 处理人员 */
	private String handlename;
	// 参数
	private String param = "";
	// 高级查询
	private String query = "";

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

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

}
