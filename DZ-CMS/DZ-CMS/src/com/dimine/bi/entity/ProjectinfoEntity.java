package com.dimine.bi.entity;

import java.util.ArrayList;
import java.util.List;

import com.dimine.base.entity.BaseEntity;

/**
 * 作业地点信息类
 * 
 * @author dimine 2015-08-26 17:26:56
 * 
 */
public class ProjectinfoEntity extends BaseEntity {
	private String businesstype;
	/** 父工程ID集合 */
	private List<String> parentids = new ArrayList<String>();
	/** 工程ID */
	private String projectid;
	/** 所属项目部ID */
	private String deptid;
	/** 父工程ID */
	private String parentitemid;
	/** 工程名称 */
	private String projectname;
	/** 工程编码 */
	private String projectcode;
	/** 工程类型：巷道、采场、安装工程、反井工程、其他工程 */
	private String projecttype;
	/** 工程级别：中段、分段、作业地点 */
	private String projectlevel;
	/** 作业地点状态 */
	private String projectstatus;
	/** 级别序号 */
	private String levelno;
	/** 是否删除 */
	private String isdel;
	/** 序号 */
	private String indexno;
	/** 是否叶子节点 */
	private String isleaf;
	/** 创建人 */
	private String createid;
	/** 创建日期 */
	private String createdate;
	/** 修改人 */
	private String modifyid;
	/** 修改日期 */
	private String modifydate;
	/** 备注 */
	private String memo;
	private String childCount;
	private String parentname;
	private String qdid;// 区队id
	private String techid;// 工艺id
	/** 父工程ID */
	private String aparentitemid;
	/** 父工程名称 */
	private String aparentitemname;
	//'巷道类别',
	private String rdtype;
	//'掘进类型',
	private String jjtype;
	//'矿岩硬度',
	private String rockHardness;
	//'掘进断面',
	private String section;
	//'长度',
	private String designLen;
	//'方量'
	private String rdDia;
	private String mineMethod;
	private String oreType;
	private String rockHardness1;
	private String mineReserve;
	private String	lostRatio;
	private String	rateDilution;
	private String	projectAmount;
	private String	units;
	private String	procid;
	private String	installtype;
	private String	installcontent;
	private String	demo;
	private String	units3;
	private String	projectamount3;
	
	private String isupload;

	public String getProjectid() {
		return projectid;
	}

	public void setProjectid(String projectid) {
		this.projectid = projectid;
	}

	public String getDeptid() {
		return deptid;
	}

	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}

	public String getParentitemid() {
		return parentitemid;
	}

	public void setParentitemid(String parentitemid) {
		this.parentitemid = parentitemid;
	}

	public String getProjectname() {
		return projectname;
	}

	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}

	public String getProjectcode() {
		return projectcode;
	}

	public void setProjectcode(String projectcode) {
		this.projectcode = projectcode;
	}

	public String getProjecttype() {
		return projecttype;
	}

	public void setProjecttype(String projecttype) {
		this.projecttype = projecttype;
	}

	public String getProjectlevel() {
		return projectlevel;
	}

	public void setProjectlevel(String projectlevel) {
		this.projectlevel = projectlevel;
	}

	public String getIsdel() {
		return isdel;
	}

	public void setIsdel(String isdel) {
		this.isdel = isdel;
	}

	public String getIndexno() {
		return indexno;
	}

	public void setIndexno(String indexno) {
		this.indexno = indexno;
	}

	public String getIsleaf() {
		return isleaf;
	}

	public void setIsleaf(String isleaf) {
		this.isleaf = isleaf;
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

	public String getChildCount() {
		return childCount;
	}

	public void setChildCount(String childCount) {
		this.childCount = childCount;
	}

	public String getParentname() {
		return parentname;
	}

	public void setParentname(String parentname) {
		this.parentname = parentname;
	}

	public String getQdid() {
		return qdid;
	}

	public void setQdid(String qdid) {
		this.qdid = qdid;
	}

	public List<String> getParentids() {
		return parentids;
	}

	public void setParentids(List<String> parentids) {
		this.parentids = parentids;
	}

	public String getBusinesstype() {
		return businesstype;
	}

	public void setBusinesstype(String businesstype) {
		this.businesstype = businesstype;
	}

	public String getAparentitemid() {
		return aparentitemid;
	}

	public void setAparentitemid(String aparentitemid) {
		this.aparentitemid = aparentitemid;
	}

	public String getProjectstatus() {
		return projectstatus;
	}

	public void setProjectstatus(String projectstatus) {
		this.projectstatus = projectstatus;
	}

	public String getLevelno() {
		return levelno;
	}

	public void setLevelno(String levelno) {
		this.levelno = levelno;
	}

	public String getTechid() {
		return techid;
	}

	public void setTechid(String techid) {
		this.techid = techid;
	}

	public String getRdtype() {
		return rdtype;
	}

	public void setRdtype(String rdtype) {
		this.rdtype = rdtype;
	}

	public String getJjtype() {
		return jjtype;
	}

	public void setJjtype(String jjtype) {
		this.jjtype = jjtype;
	}

	public String getRockHardness() {
		return rockHardness;
	}

	public void setRockHardness(String rockHardness) {
		this.rockHardness = rockHardness;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String getDesignLen() {
		return designLen;
	}

	public void setDesignLen(String designLen) {
		this.designLen = designLen;
	}

	public String getRdDia() {
		return rdDia;
	}

	public void setRdDia(String rdDia) {
		this.rdDia = rdDia;
	}

	public String getMineMethod() {
		return mineMethod;
	}

	public void setMineMethod(String mineMethod) {
		this.mineMethod = mineMethod;
	}

	public String getOreType() {
		return oreType;
	}

	public void setOreType(String oreType) {
		this.oreType = oreType;
	}

	public String getRockHardness1() {
		return rockHardness1;
	}

	public void setRockHardness1(String rockHardness1) {
		this.rockHardness1 = rockHardness1;
	}

	public String getMineReserve() {
		return mineReserve;
	}

	public void setMineReserve(String mineReserve) {
		this.mineReserve = mineReserve;
	}

	public String getLostRatio() {
		return lostRatio;
	}

	public void setLostRatio(String lostRatio) {
		this.lostRatio = lostRatio;
	}

	public String getRateDilution() {
		return rateDilution;
	}

	public void setRateDilution(String rateDilution) {
		this.rateDilution = rateDilution;
	}

	public String getProjectAmount() {
		return projectAmount;
	}

	public void setProjectAmount(String projectAmount) {
		this.projectAmount = projectAmount;
	}

	public String getUnits() {
		return units;
	}

	public void setUnits(String units) {
		this.units = units;
	}

	public String getAparentitemname() {
		return aparentitemname;
	}

	public void setAparentitemname(String aparentitemname) {
		this.aparentitemname = aparentitemname;
	}

	public String getProcid() {
		return procid;
	}

	public void setProcid(String procid) {
		this.procid = procid;
	}

	public String getInstalltype() {
		return installtype;
	}

	public void setInstalltype(String installtype) {
		this.installtype = installtype;
	}

	public String getInstallcontent() {
		return installcontent;
	}

	public void setInstallcontent(String installcontent) {
		this.installcontent = installcontent;
	}

	public String getDemo() {
		return demo;
	}

	public void setDemo(String demo) {
		this.demo = demo;
	}

	public String getUnits3() {
		return units3;
	}

	public void setUnits3(String units3) {
		this.units3 = units3;
	}

	public String getProjectamount3() {
		return projectamount3;
	}

	public void setProjectamount3(String projectamount3) {
		this.projectamount3 = projectamount3;
	}

	public String getIsupload() {
		return isupload;
	}

	public void setIsupload(String isupload) {
		this.isupload = isupload;
	}
}