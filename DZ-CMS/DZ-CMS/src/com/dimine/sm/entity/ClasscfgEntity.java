package com.dimine.sm.entity;

import com.dimine.base.entity.BaseEntity;

/**
 * 班次配置表信息类
 * 
 * @author dimine 2015-07-08 16:55:30
 * 
 */
public class ClasscfgEntity extends BaseEntity {

	/** 班次配置ID */
	private String classcfgid;
	/** 所属项目部 */
	private String deptid;
	/** 班次 */
	private String classtype;
	private String classtypename;
	/** 开始时间 */
	private String starttime;
	/** 结束时间 */
	private String endtime;
	/** 创建人 */
	private String createid;
	/** 创建时间 */
	private String createdate;
	/** 修改人 */
	private String modifyid;
	/** 修改时间 */
	private String modifydate;
	/** 备注 */
	private String demo;
	private String value;
	private String text;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getClasscfgid() {
		return classcfgid;
	}

	public void setClasscfgid(String classcfgid) {
		this.classcfgid = classcfgid;
	}

	public String getDeptid() {
		return deptid;
	}

	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}

	public String getClasstype() {
		return classtype;
	}

	public void setClasstype(String classtype) {
		this.classtype = classtype;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
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

	public String getClasstypename() {
		return classtypename;
	}

	public void setClasstypename(String classtypename) {
		this.classtypename = classtypename;
	}

}
