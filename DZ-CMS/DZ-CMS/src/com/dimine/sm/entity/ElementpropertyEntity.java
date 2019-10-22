package com.dimine.sm.entity;

import com.dimine.base.entity.BaseEntity;

/**
 * 要素属性表信息类
 * 
 * @author dimine 2015-11-09 11:23:11
 * 
 */
public class ElementpropertyEntity extends BaseEntity {

	/** 要素属性ID */
	private String eleproid;
	/** 要素分类ID */
	private String eletypeid;
	/** 要素属性编码 */
	private String elecode;
	/** 要素序号 */
	private String eleno;
	/** 要素属性名称 */
	private String elename;
	/** 上级要素属性ID */
	private String parenteleid;
	/** 要素属性类型 */
	private String eletype;
	/** 取值SQL */
	private String valuesql;
	/** 对应服务名 */
	private String servicename;
	/** 能否编辑 */
	private String isedit;
	/** 对应内部属性名 */
	private String iproname;
	/** 是否联合主键 */
	private String isprimary;
	/** 创建人 */
	private String createid;
	/** 创建时间 */
	private String createdate;
	/** 修改人 */
	private String modifyid;
	/** 修改时间 */
	private String modifydate;
	/** 备注 */
	private String memo;

	/** 工程级别 */
	private String projectlevel;
	/** 是否隐藏 */
	private String ishide;
	/** 是否为空 */
	private String isnull;

	public String getProjectlevel() {
		return projectlevel;
	}

	public void setProjectlevel(String projectlevel) {
		this.projectlevel = projectlevel;
	}

	public String getIshide() {
		return ishide;
	}

	public void setIshide(String ishide) {
		this.ishide = ishide;
	}

	public String getEleno() {
		return eleno;
	}

	public void setEleno(String eleno) {
		this.eleno = eleno;
	}

	public String getEleproid() {
		return eleproid;
	}

	public void setEleproid(String eleproid) {
		this.eleproid = eleproid;
	}

	public String getEletypeid() {
		return eletypeid;
	}

	public void setEletypeid(String eletypeid) {
		this.eletypeid = eletypeid;
	}

	public String getElecode() {
		return elecode;
	}

	public void setElecode(String elecode) {
		this.elecode = elecode;
	}

	public String getElename() {
		return elename;
	}

	public void setElename(String elename) {
		this.elename = elename;
	}

	public String getParenteleid() {
		return parenteleid;
	}

	public void setParenteleid(String parenteleid) {
		this.parenteleid = parenteleid;
	}

	public String getEletype() {
		return eletype;
	}

	public void setEletype(String eletype) {
		this.eletype = eletype;
	}

	public String getValuesql() {
		return valuesql;
	}

	public void setValuesql(String valuesql) {
		this.valuesql = valuesql;
	}

	public String getServicename() {
		return servicename;
	}

	public void setServicename(String servicename) {
		this.servicename = servicename;
	}

	public String getIsedit() {
		return isedit;
	}

	public void setIsedit(String isedit) {
		this.isedit = isedit;
	}

	public String getIproname() {
		return iproname;
	}

	public void setIproname(String iproname) {
		this.iproname = iproname;
	}

	public String getIsprimary() {
		return isprimary;
	}

	public void setIsprimary(String isprimary) {
		this.isprimary = isprimary;
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

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getIsnull() {
		return isnull;
	}

	public void setIsnull(String isnull) {
		this.isnull = isnull;
	}

}
