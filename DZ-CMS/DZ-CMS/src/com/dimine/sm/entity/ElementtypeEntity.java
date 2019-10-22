package com.dimine.sm.entity;

import com.dimine.base.entity.BaseEntity;

/**
 * 要素分类表信息类
 * 
 * @author dimine 2015-11-09 11:23:34
 * 
 */
public class ElementtypeEntity extends BaseEntity {

	/** 要素分类ID */
	private String eletypeid ;
	/** 要素分类类型：井巷工程，采场 */
	private String eletypekind ;
	/** 要素名称 */
	private String eletypename ;
	/** 所属项目部 */
	private String deptid ;
	/** 创建人 */
	private String createid ;
	/** 创建时间 */
	private String createdate ;
	/** 修改人 */
	private String modifyid ;
	/** 修改时间 */
	private String modifydate ;
	/** 备注 */
	private String memo ;

	public String getEletypeid() {
		return eletypeid;
	}
	public void setEletypeid(String eletypeid) {
		this.eletypeid = eletypeid;
	}
	public String getEletypekind() {
		return eletypekind;
	}
	public void setEletypekind(String eletypekind) {
		this.eletypekind = eletypekind;
	}
	public String getEletypename() {
		return eletypename;
	}
	public void setEletypename(String eletypename) {
		this.eletypename = eletypename;
	}
	public String getDeptid() {
		return deptid;
	}
	public void setDeptid(String deptid) {
		this.deptid = deptid;
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

}
