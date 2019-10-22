package com.dimine.sm.entity;

import com.dimine.base.entity.BaseEntity;

/**
 * 配置组织机构类型是属于组织机构还是部门。信息类
 * 
 * @author dimine 2015-06-23 11:20:50
 * 
 */
public class OrgcfgEntity extends BaseEntity {

	/** 组织机构配置ID */
	private String orgcfgid;
	/** 组织机构类型：公司、分公司、项目部、部门、工区 */
	private String orgtype;
	private String orgtypename;
	/** 是否组织机构：1表示是组织机构，0表示部门 */
	private String isorg;
	private String isorgname;
	/** 创建人 */
	private String createid;
	/** 创建日期 */
	private String createdate;
	/** 修改人 */
	private String modifyid;
	/** 修改日期 */
	private String modifydate;
	/** 备注 */
	private String demo;

	public String getOrgcfgid() {
		return orgcfgid;
	}

	public void setOrgcfgid(String orgcfgid) {
		this.orgcfgid = orgcfgid;
	}

	public String getOrgtype() {
		return orgtype;
	}

	public void setOrgtype(String orgtype) {
		this.orgtype = orgtype;
	}

	public String getIsorg() {
		return isorg;
	}

	public void setIsorg(String isorg) {
		this.isorg = isorg;
	}

	public String getCreateid() {
		return createid;
	}

	public void setCreateid(String createid) {
		this.createid = createid;
	}

	public String getCreatedate() {
		return createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}

	public String getModifyid() {
		return modifyid;
	}

	public void setModifyid(String modifyid) {
		this.modifyid = modifyid;
	}

	public String getModifydate() {
		return modifydate;
	}

	public void setModifydate(String modifydate) {
		this.modifydate = modifydate;
	}

	public String getDemo() {
		return demo;
	}

	public void setDemo(String demo) {
		this.demo = demo;
	}

	public String getOrgtypename() {
		return orgtypename;
	}

	public void setOrgtypename(String orgtypename) {
		this.orgtypename = orgtypename;
	}

	public String getIsorgname() {
		return isorgname;
	}

	public void setIsorgname(String isorgname) {
		this.isorgname = isorgname;
	}

}
