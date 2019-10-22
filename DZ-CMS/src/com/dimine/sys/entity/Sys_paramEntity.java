package com.dimine.sys.entity;

import com.dimine.base.entity.BaseEntity;

/**
 * 系统参数表信息类
 * 
 * @author dimine 2014-12-18 12:01:02
 * 
 */
public class Sys_paramEntity extends BaseEntity {

	/** 参数编号 */
	private String paramcode ;
	/** 参数名称 */
	private String paramname ;
	/** 参数值 */
	private String paramvalue ;
	/** 部门id */
	private String deptid ;
	/** 备注 */
	private String memo ;
	// 参数
	private String param = "";
	// 高级查询
	private String query = "";

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
	public String getParamcode() {
		return paramcode;
	}
	public void setParamcode(String paramcode) {
		this.paramcode = paramcode;
	}
	public String getParamname() {
		return paramname;
	}
	public void setParamname(String paramname) {
		this.paramname = paramname;
	}
	public String getParamvalue() {
		return paramvalue;
	}
	public void setParamvalue(String paramvalue) {
		this.paramvalue = paramvalue;
	}
	public String getDeptid() {
		return deptid;
	}
	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}

}
