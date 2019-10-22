package com.dimine.bi.entity;

import com.dimine.base.entity.BaseEntity;

/**
 * 矿业权基本信息表信息类
 * 
 * @author dimine 2017-08-15 10:34:46
 * 
 */
public class T_bi_mrbaseinfoEntity extends BaseEntity {

	/** 矿业权ID */
	private String rightid = "";
	/** 矿山ID */
	private String mineid = "";
	/** 矿权类别 */
	private String mrcategory = "";
	/** 项目名称 */
	private String projectname = "";
	/** 项目性质 */
	private String projectquality = "";
	/** 矿业权状态 */
	private String mrstate = "";
	/** 拥有方式 */
	private String ownway = "";
	/** 经济类型 */
	private String economictype = "";
	/** 公司名称 */
	private String companyname = "";
	/** 公司地址 */
	private String companyaddr = "";
	/** 公司邮编 */
	private String companypost = "";
	/** 注册资金 */
	private String registeredfund = "";
	/** 法定代表人 */
	private String legalrepresentative = "";
	/** 帐号 */
	private String accountnum = "";
	/** 开户银行 */
	private String bank = "";
	/** 矿业权资格证号 */
	private String mrcardid = "";
	/** 东经起 */
	private String eaststart = "";
	/** 东经止 */
	private String eastend = "";
	/** 北纬起 */
	private String northstart = "";
	/** 北纬止 */
	private String northend = "";
	/** 地理位置 */
	private String geoposition = "";
	/** 矿权面积(km2) */
	private String mrarea = "";
	/** 有效期开始时间 */
	private String expstartdate = "";
	/** 有效期结束时间 */
	private String expenddate = "";
	/** 责任人 */
	private String dutyman = "";
	/** 创建日期 */
	private String createdate = "";
	/** 创建人 */
	private String createid = "";
	/** 修改人 */
	private String modifyid = "";
	/** 修改日期 */
	private String modifydate = "";
	/** 备注 */
	private String remark = "";
	
	private String mrcategoryname = "";
	private String projectqualityname = "";
	private String mrstatename = "";
	private String ownwayname = "";
	private String economictypename = "";
	
	private String deptname = "";

	public String getRightid() {
		return rightid;
	}
	public void setRightid(String rightid) {
		this.rightid = rightid;
	}
	public String getMineid() {
		return mineid;
	}
	public void setMineid(String mineid) {
		this.mineid = mineid;
	}
	public String getMrcategory() {
		return mrcategory;
	}
	public void setMrcategory(String mrcategory) {
		this.mrcategory = mrcategory;
	}
	public String getProjectname() {
		return projectname;
	}
	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}
	public String getProjectquality() {
		return projectquality;
	}
	public void setProjectquality(String projectquality) {
		this.projectquality = projectquality;
	}
	public String getMrstate() {
		return mrstate;
	}
	public void setMrstate(String mrstate) {
		this.mrstate = mrstate;
	}
	public String getOwnway() {
		return ownway;
	}
	public void setOwnway(String ownway) {
		this.ownway = ownway;
	}
	public String getEconomictype() {
		return economictype;
	}
	public void setEconomictype(String economictype) {
		this.economictype = economictype;
	}
	public String getCompanyname() {
		return companyname;
	}
	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}
	public String getCompanyaddr() {
		return companyaddr;
	}
	public void setCompanyaddr(String companyaddr) {
		this.companyaddr = companyaddr;
	}
	public String getCompanypost() {
		return companypost;
	}
	public void setCompanypost(String companypost) {
		this.companypost = companypost;
	}
	public String getRegisteredfund() {
		return registeredfund;
	}
	public void setRegisteredfund(String registeredfund) {
		this.registeredfund = registeredfund;
	}
	public String getLegalrepresentative() {
		return legalrepresentative;
	}
	public void setLegalrepresentative(String legalrepresentative) {
		this.legalrepresentative = legalrepresentative;
	}
	public String getAccountnum() {
		return accountnum;
	}
	public void setAccountnum(String accountnum) {
		this.accountnum = accountnum;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getMrcardid() {
		return mrcardid;
	}
	public void setMrcardid(String mrcardid) {
		this.mrcardid = mrcardid;
	}
	public String getEaststart() {
		return eaststart;
	}
	public void setEaststart(String eaststart) {
		this.eaststart = eaststart;
	}
	public String getEastend() {
		return eastend;
	}
	public void setEastend(String eastend) {
		this.eastend = eastend;
	}
	public String getNorthstart() {
		return northstart;
	}
	public void setNorthstart(String northstart) {
		this.northstart = northstart;
	}
	public String getNorthend() {
		return northend;
	}
	public void setNorthend(String northend) {
		this.northend = northend;
	}
	public String getGeoposition() {
		return geoposition;
	}
	public void setGeoposition(String geoposition) {
		this.geoposition = geoposition;
	}
	public String getMrarea() {
		return mrarea;
	}
	public void setMrarea(String mrarea) {
		this.mrarea = mrarea;
	}
	public String getExpstartdate() {
		return expstartdate;
	}
	public void setExpstartdate(String expstartdate) {
		this.expstartdate = expstartdate;
	}
	public String getExpenddate() {
		return expenddate;
	}
	public void setExpenddate(String expenddate) {
		this.expenddate = expenddate;
	}
	public String getDutyman() {
		return dutyman;
	}
	public void setDutyman(String dutyman) {
		this.dutyman = dutyman;
	}
	public String getCreatedate() {
		return createdate;
	}
	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	public String getCreateid() {
		return createid;
	}
	public void setCreateid(String createid) {
		this.createid = createid;
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getMrcategoryname() {
		return mrcategoryname;
	}
	public void setMrcategoryname(String mrcategoryname) {
		this.mrcategoryname = mrcategoryname;
	}
	public String getProjectqualityname() {
		return projectqualityname;
	}
	public void setProjectqualityname(String projectqualityname) {
		this.projectqualityname = projectqualityname;
	}
	public String getMrstatename() {
		return mrstatename;
	}
	public void setMrstatename(String mrstatename) {
		this.mrstatename = mrstatename;
	}
	public String getOwnwayname() {
		return ownwayname;
	}
	public void setOwnwayname(String ownwayname) {
		this.ownwayname = ownwayname;
	}
	public String getEconomictypename() {
		return economictypename;
	}
	public void setEconomictypename(String economictypename) {
		this.economictypename = economictypename;
	}
	public String getDeptname() {
		return deptname;
	}
	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

}
