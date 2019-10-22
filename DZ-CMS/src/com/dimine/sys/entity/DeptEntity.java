package com.dimine.sys.entity;

import java.util.Date;

import com.dimine.base.entity.BaseEntity;

public class DeptEntity extends BaseEntity {
	public static final String IBATIS_QUERY_STRING_SAMENODE = "DeptEntity.samenode";
	public static final String IBATIS_QUERY_STRING_DEPT_ALL = "DeptEntity.all";
	public static final String IBATIS_KEY_GETNAME_BY_ID = "DeptEntity.getnamebyid";
	public static final String IBATIS_KEY_COUNT_CHILDNODES = "DeptEntity.count.childnode";
	public static final String IBATIS_KEY_CODE_EXIST = "DeptEntity.codeexist";
	public static final String IBATIS_KEY_DEPT_ORG_PARENT = "DeptEntity.org.parent";
	public static final String IBATIS_KEY_SELECT_NOTORGID_CHILDS = "DeptEntity.isnotorg.child";
	public static final String IBATIS_KEY_UPDATE_RGID_CHILDS = "DeptEntity.update.orgid.childs";
	private String deptid;
	private String deptCode;
	private String deptname;
	private String deptType;
	private String parentdeptid;
	private String managerid;
	private String tel;
	private String fax;
	private String address;
	private String email;
	private String memo;
	private String delflag;
	private Date createdate;
	private String createman;
	private Date modifydate;
	private String modifyman;
	private String orderNo;
	private String isOrg;
	private String orgID;
	private String childCount;
	private String parentdeptname;
	/** 组织机构级别：1,2,3,4,5,6,7 */
	private String orglevel;
	/** 组织机构类型：公司、分公司、项目部、部门、工区、班组 */
	private String orgtype;

	public DeptEntity() {
		deptid = "";
		deptCode = "";
		deptname = "";
		deptType = "1";
		parentdeptid = "";
		managerid = "";
		tel = "";
		fax = "";
		address = "";
		email = "";
		memo = "";
		delflag = "0";
		createman = "";
		modifyman = "";
		orderNo = "0";
		isOrg = "0";
		orgID = "";
		childCount = "0";
		parentdeptname = "";
	}

	public String getDeptid() {
		return deptid;
	}

	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getDeptname() {
		return deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	public String getDeptType() {
		return deptType;
	}

	public void setDeptType(String deptType) {
		this.deptType = deptType;
	}

	public String getParentdeptid() {
		return parentdeptid;
	}

	public void setParentdeptid(String parentdeptid) {
		this.parentdeptid = parentdeptid;
	}

	public String getManagerid() {
		return managerid;
	}

	public void setManagerid(String managerid) {
		this.managerid = managerid;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getDelflag() {
		return delflag;
	}

	public void setDelflag(String delflag) {
		this.delflag = delflag;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public String getCreateman() {
		return createman;
	}

	public void setCreateman(String createman) {
		this.createman = createman;
	}

	public Date getModifydate() {
		return modifydate;
	}

	public void setModifydate(Date modifydate) {
		this.modifydate = modifydate;
	}

	public String getModifyman() {
		return modifyman;
	}

	public void setModifyman(String modifyman) {
		this.modifyman = modifyman;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getIsOrg() {
		return isOrg;
	}

	public void setIsOrg(String isOrg) {
		this.isOrg = isOrg;
	}

	public String getOrgID() {
		return orgID;
	}

	public void setOrgID(String orgID) {
		this.orgID = orgID;
	}

	public String getOrglevel() {
		return orglevel;
	}

	public void setOrglevel(String orglevel) {
		this.orglevel = orglevel;
	}

	public String getOrgtype() {
		return orgtype;
	}

	public void setOrgtype(String orgtype) {
		this.orgtype = orgtype;
	}

	public String getChildCount() {
		return childCount;
	}

	public void setChildCount(String childCount) {
		this.childCount = childCount;
	}

	public static String getIbatisQueryStringSamenode() {
		return IBATIS_QUERY_STRING_SAMENODE;
	}

	public static String getIbatisQueryStringDeptAll() {
		return IBATIS_QUERY_STRING_DEPT_ALL;
	}

	public static String getIbatisKeyGetnameById() {
		return IBATIS_KEY_GETNAME_BY_ID;
	}

	public static String getIbatisKeyCountChildnodes() {
		return IBATIS_KEY_COUNT_CHILDNODES;
	}

	public static String getIbatisKeyCodeExist() {
		return IBATIS_KEY_CODE_EXIST;
	}

	public static String getIbatisKeyDeptOrgParent() {
		return IBATIS_KEY_DEPT_ORG_PARENT;
	}

	public static String getIbatisKeySelectNotorgidChilds() {
		return IBATIS_KEY_SELECT_NOTORGID_CHILDS;
	}

	public static String getIbatisKeyUpdateRgidChilds() {
		return IBATIS_KEY_UPDATE_RGID_CHILDS;
	}

	public String getParentdeptname() {
		return parentdeptname;
	}

	public void setParentdeptname(String parentdeptname) {
		this.parentdeptname = parentdeptname;
	}

}
