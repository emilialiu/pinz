package com.dimine.bi.entity;

import com.dimine.base.entity.BaseEntity;

/**
 * 工区对应工程信息信息类
 * 
 * @author dimine 2016-08-23 09:31:47
 * 
 */
public class TechnologyProjectEntity extends BaseEntity {
	/** 工艺对应工程ID */
	private String apid;
	/** 所属矿山 */
	private String tdeptid;
	/** 工程ID */
	private String projectid;
	/** 工艺ID */
	private String techid;

	public String getApid() {
		return apid;
	}

	public void setApid(String apid) {
		this.apid = apid;
	}

	public String getTdeptid() {
		return tdeptid;
	}

	public void setTdeptid(String tdeptid) {
		this.tdeptid = tdeptid;
	}

	public String getProjectid() {
		return projectid;
	}

	public void setProjectid(String projectid) {
		this.projectid = projectid;
	}

	public String getTechid() {
		return techid;
	}

	public void setTechid(String techid) {
		this.techid = techid;
	}

}
