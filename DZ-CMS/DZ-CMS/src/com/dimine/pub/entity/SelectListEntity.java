package com.dimine.pub.entity;

import com.dimine.base.entity.BaseEntity;

/**
 * 待办信息实体
 * 
 * @author LCF
 * 
 */
public class SelectListEntity extends BaseEntity {
	/** 实际值 */
	private String value;
	/** 显示值 */
	private String text;
	/**	业务类型*/
	private String dicttypeid;
	/**	单位业务类型*/
	private String businesstype;
	/**	父id*/
	private String parentid;
	/**	限制初始位置*/
	private int startposition;
	/**	限制数量*/
	private int limitnum;
	
	
	
	/**	分段*/
	private String fenduanid;
	/**	中断*/
	private String zhongduanid;
	/**	合同id*/
	private String contractid;
	/**	当前页面传入的项目部id*/
	private String deptid;
	/**工序id**/
	private String procid;
	/**班组id**/
	private String teamid;

	
	
	

	public SelectListEntity() {
	}
	public SelectListEntity(String value, String text) {
		this.value = value;
		this.text = text;
	}
	public String getDeptid() {
		return deptid;
	}
	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}
	public String getParentid() {
		return parentid;
	}
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
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

	public String getDicttypeid() {
		return dicttypeid;
	}
	public void setDicttypeid(String dicttypeid) {
		this.dicttypeid = dicttypeid;
	}
	public String getBusinesstype() {
		return businesstype;
	}
	public void setBusinesstype(String businesstype) {
		this.businesstype = businesstype;
	}
	public int getStartposition() {
		return startposition;
	}
	public void setStartposition(int startposition) {
		this.startposition = startposition;
	}
	public int getLimitnum() {
		return limitnum;
	}
	public void setLimitnum(int limitnum) {
		this.limitnum = limitnum;
	}
	public String getFenduanid() {
		return fenduanid;
	}
	public void setFenduanid(String fenduanid) {
		this.fenduanid = fenduanid;
	}
	public String getZhongduanid() {
		return zhongduanid;
	}
	public void setZhongduanid(String zhongduanid) {
		this.zhongduanid = zhongduanid;
	}
	public String getContractid() {
		return contractid;
	}
	public void setContractid(String contractid) {
		this.contractid = contractid;
	}
	public String getProcid() {
		return procid;
	}
	public void setProcid(String procid) {
		this.procid = procid;
	}
	public String getTeamid() {
		return teamid;
	}
	public void setTeamid(String teamid) {
		this.teamid = teamid;
	}
	
	
}
