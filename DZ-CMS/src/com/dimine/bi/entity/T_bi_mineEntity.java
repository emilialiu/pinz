package com.dimine.bi.entity;

import com.dimine.base.entity.BaseEntity;

/**
 * 矿山信息(T_BI_Mine)信息类
 * 
 * @author dimine 2017-08-15 10:30:26
 * 
 */
public class T_bi_mineEntity extends BaseEntity {

	/** MineID */
	private String mineid = "";
	/** 组织机构 */
	private String orgid = "";
	/** 矿山编码 */
	private String mineno = "";
	/** 矿山名称 */
	private String minename = "";
	/** 矿石工业类型KSLX */
	private String oremanutype = "";
	/** 建矿日期 */
	private String builddate = "";
	/** 投产日期 */
	private String startingdate = "";
	/** 生产能力(万吨) */
	private String productivepower = "";
	/** 选矿能力(万吨) */
	private String siftpower = "";
	/** 开拓运输方案 */
	private String transportation = "";
	/** 开采方式 */
	private String miningmethod = "";
	/** 国家SYS007 */
	private String country = "";
	/** 省份SYS008 */
	private String province = "";
	/** 城市SYS009 */
	private String city = "";
	/** 地址 */
	private String address = "";
	/** 面积 */
	private String area = "";
	/** 宽度（Km） */
	private String minewidth = "";
	/** 长度（Km） */
	private String minelength = "";
	/** 标高（m） */
	private String mineheight = "";
	/** 东经起 */
	private String eastlognstart = "";
	/** 东经止 */
	private String eastlognend = "";
	/** 北纬起 */
	private String northlatstart = "";
	/** 北纬止 */
	private String northlatend = "";
	/** 矿山X坐标 */
	private String coordinatex = "";
	/** 矿山Y坐标 */
	private String coordinatey = "";
	/** 生产状态SCZT */
	private String productivestatus = "";
	/** 服务年限 */
	private String serviceyear = "";
	/** 剥离比 */
	private String strippingratio = "";
	/** 探采比 */
	private String explorationratio = "";
	/** 从业人员 */
	private String workusercount = "";
	/** 技术人员 */
	private String techusercount = "";
	/** 备注 */
	private String remark = "";
	/** 创建人 */
	private String createid = "";
	/** 创建时间 */
	private String createdate = "";
	/** 修改人 */
	private String modifyid = "";
	/** 修改时间 */
	private String modifydate = "";
	
	private String oremanutypename = "";//矿石类型
	private String miningmethodname = "";//开采方式
	private String productivestatusname = "";//生产状态
	
	private String countryname = "";
	/** 省份SYS008 */
	private String provincename = "";
	/** 城市SYS009 */
	private String cityname = "";
	private String orgname = "";//机构名称

	public String getMineid() {
		return mineid;
	}
	public void setMineid(String mineid) {
		this.mineid = mineid;
	}
	public String getOrgid() {
		return orgid;
	}
	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}
	public String getMineno() {
		return mineno;
	}
	public void setMineno(String mineno) {
		this.mineno = mineno;
	}
	public String getMinename() {
		return minename;
	}
	public void setMinename(String minename) {
		this.minename = minename;
	}
	public String getOremanutype() {
		return oremanutype;
	}
	public void setOremanutype(String oremanutype) {
		this.oremanutype = oremanutype;
	}
	public String getBuilddate() {
		return builddate;
	}
	public void setBuilddate(String builddate) {
		this.builddate = builddate;
	}
	public String getStartingdate() {
		return startingdate;
	}
	public void setStartingdate(String startingdate) {
		this.startingdate = startingdate;
	}
	public String getProductivepower() {
		return productivepower;
	}
	public void setProductivepower(String productivepower) {
		this.productivepower = productivepower;
	}
	public String getSiftpower() {
		return siftpower;
	}
	public void setSiftpower(String siftpower) {
		this.siftpower = siftpower;
	}
	public String getTransportation() {
		return transportation;
	}
	public void setTransportation(String transportation) {
		this.transportation = transportation;
	}
	public String getMiningmethod() {
		return miningmethod;
	}
	public void setMiningmethod(String miningmethod) {
		this.miningmethod = miningmethod;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getMinewidth() {
		return minewidth;
	}
	public void setMinewidth(String minewidth) {
		this.minewidth = minewidth;
	}
	public String getMinelength() {
		return minelength;
	}
	public void setMinelength(String minelength) {
		this.minelength = minelength;
	}
	public String getMineheight() {
		return mineheight;
	}
	public void setMineheight(String mineheight) {
		this.mineheight = mineheight;
	}
	public String getEastlognstart() {
		return eastlognstart;
	}
	public void setEastlognstart(String eastlognstart) {
		this.eastlognstart = eastlognstart;
	}
	public String getEastlognend() {
		return eastlognend;
	}
	public void setEastlognend(String eastlognend) {
		this.eastlognend = eastlognend;
	}
	public String getNorthlatstart() {
		return northlatstart;
	}
	public void setNorthlatstart(String northlatstart) {
		this.northlatstart = northlatstart;
	}
	public String getNorthlatend() {
		return northlatend;
	}
	public void setNorthlatend(String northlatend) {
		this.northlatend = northlatend;
	}
	public String getCoordinatex() {
		return coordinatex;
	}
	public void setCoordinatex(String coordinatex) {
		this.coordinatex = coordinatex;
	}
	public String getCoordinatey() {
		return coordinatey;
	}
	public void setCoordinatey(String coordinatey) {
		this.coordinatey = coordinatey;
	}
	public String getProductivestatus() {
		return productivestatus;
	}
	public void setProductivestatus(String productivestatus) {
		this.productivestatus = productivestatus;
	}
	public String getServiceyear() {
		return serviceyear;
	}
	public void setServiceyear(String serviceyear) {
		this.serviceyear = serviceyear;
	}
	public String getStrippingratio() {
		return strippingratio;
	}
	public void setStrippingratio(String strippingratio) {
		this.strippingratio = strippingratio;
	}
	public String getExplorationratio() {
		return explorationratio;
	}
	public void setExplorationratio(String explorationratio) {
		this.explorationratio = explorationratio;
	}
	public String getWorkusercount() {
		return workusercount;
	}
	public void setWorkusercount(String workusercount) {
		this.workusercount = workusercount;
	}
	public String getTechusercount() {
		return techusercount;
	}
	public void setTechusercount(String techusercount) {
		this.techusercount = techusercount;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
	public String getOremanutypename() {
		return oremanutypename;
	}
	public void setOremanutypename(String oremanutypename) {
		this.oremanutypename = oremanutypename;
	}
	public String getMiningmethodname() {
		return miningmethodname;
	}
	public void setMiningmethodname(String miningmethodname) {
		this.miningmethodname = miningmethodname;
	}
	public String getProductivestatusname() {
		return productivestatusname;
	}
	public void setProductivestatusname(String productivestatusname) {
		this.productivestatusname = productivestatusname;
	}
	public String getCountryname() {
		return countryname;
	}
	public void setCountryname(String countryname) {
		this.countryname = countryname;
	}
	public String getProvincename() {
		return provincename;
	}
	public void setProvincename(String provincename) {
		this.provincename = provincename;
	}
	public String getCityname() {
		return cityname;
	}
	public void setCityname(String cityname) {
		this.cityname = cityname;
	}
	public String getOrgname() {
		return orgname;
	}
	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}

}
