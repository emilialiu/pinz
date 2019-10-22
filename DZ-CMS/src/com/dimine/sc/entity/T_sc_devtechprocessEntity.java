package com.dimine.sc.entity;

import com.dimine.base.entity.BaseEntity;

/**
 * 工序对应设备信息类
 * 
 * @author dimine 2016-08-17 11:06:21
 * 
 */
public class T_sc_devtechprocessEntity extends BaseEntity {

	/** 工序ID */
	private String procid = "";
	/** 设备ID */
	private String devid = "";
	/** 主表所属矿山 */
	private String tdeptid = "";
	/** 附表所属矿山 */
	private String deptid = "";
	/** 使用部门      **/
 	private String usedept="";
 	private String usedeptname="";
	private String deptname="";
	/** 设备编号 */
	private String devcode = "";
	/** 自编号 */
	private String devccode = "";
	/** 设备名称 */
	private String devname = "";
	/** 设备类别：井下铲运机、凿岩钻机、运输车辆、矿山设备、轻型汽车、矿井卷扬、空气压缩机、矿井通风机、工业泵类、工程机械、建筑机械 */
	private String devclass = "";
	/** 设备类型 */
	private String devtype = "";
	/** 设备型号 */
	private String devmodel = "";
	/** 设备用途：基建，采矿 */
	private String devuse = "";
	/** 投用时间 */
	private String userdate = "";
	/** 规定年限 */
	private String ruleyear = "";
	/** 已使用年限 */
	private String useredyear = "";
	/** 额定载重量 */
	private String ratedweight = "";
	/** 设备性质：自有、甲供 */
	private String devprop = "";
	/** 装满系数 */
	private String devratio = "";
	/** 容积 */
	private String volume = "";
	/** 创建人 */
	private String createid = "";
	/** 创建日期 */
	private String createdate = "";
	/** 修改人 */
	private String modifyid = "";
	/** 修改日期 */
	private String modifydate = "";
	/** 备注 */
	private String memo = "";
	/** 所属工段id */
	private String belongunit="";
	/** 所属工段名称（列表显示用，其他地方用id） */
	private String belongname="";
	/** 发动机型号 */
	private String engineno="";
	/** 车架号 */
	private String frameno="";
	/** 设备状态 */
	private String devstatus = "";
	private String devstatusname="";
	/** 设备类型ID */
	private String dtid = "";
	private String dtidname="";
	/** 供应商ID */
	private String vendorid = "";
	/** 供应商设备ID */
	private String vdevid = "";
	/** 设备种类 */
	private String devkind = "";
	private String devkindname="";
	/** 出厂时间 */
	private String leavedate = "";
	/** 安装地点 */
	private String installaddr = "";
	/** 设备原值 */
	private String startvalue = "";
	/** 设备残值 */
	private String remainvalue = "";
	/** 生产厂家 */
	private String manufacturer = "";
	/** 出厂编号 */
	private String manufacturingno = "";
	/** 设备净值 */
	private String devvalue = "";
	
	/** 发动机号 */
	private String enginehao = "";
	/** 产量 */
	private String production = "";
	/** 运行小时数 */
	private String workhour = "";
	/** 柴油消耗 */
	private String fuelconsumption = "";
	/** 备件消耗 */
	private String partsconsumption = "";
	/** 故障小时数 */
	private String bughour = "";
	/** 设备来源 */
	private String source = "";
	public String getProcid() {
		return procid;
	}
	public void setProcid(String procid) {
		this.procid = procid;
	}
	public String getDevid() {
		return devid;
	}
	public void setDevid(String devid) {
		this.devid = devid;
	}
	public String getTdeptid() {
		return tdeptid;
	}
	public void setTdeptid(String tdeptid) {
		this.tdeptid = tdeptid;
	}
	public String getDeptid() {
		return deptid;
	}
	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}
	public String getUsedept() {
		return usedept;
	}
	public void setUsedept(String usedept) {
		this.usedept = usedept;
	}
	public String getUsedeptname() {
		return usedeptname;
	}
	public void setUsedeptname(String usedeptname) {
		this.usedeptname = usedeptname;
	}
	public String getDeptname() {
		return deptname;
	}
	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}
	public String getDevcode() {
		return devcode;
	}
	public void setDevcode(String devcode) {
		this.devcode = devcode;
	}
	public String getDevccode() {
		return devccode;
	}
	public void setDevccode(String devccode) {
		this.devccode = devccode;
	}
	public String getDevname() {
		return devname;
	}
	public void setDevname(String devname) {
		this.devname = devname;
	}
	public String getDevclass() {
		return devclass;
	}
	public void setDevclass(String devclass) {
		this.devclass = devclass;
	}
	public String getDevtype() {
		return devtype;
	}
	public void setDevtype(String devtype) {
		this.devtype = devtype;
	}
	public String getDevmodel() {
		return devmodel;
	}
	public void setDevmodel(String devmodel) {
		this.devmodel = devmodel;
	}
	public String getDevuse() {
		return devuse;
	}
	public void setDevuse(String devuse) {
		this.devuse = devuse;
	}
	public String getUserdate() {
		return userdate;
	}
	public void setUserdate(String userdate) {
		this.userdate = userdate;
	}
	public String getRuleyear() {
		return ruleyear;
	}
	public void setRuleyear(String ruleyear) {
		this.ruleyear = ruleyear;
	}
	public String getUseredyear() {
		return useredyear;
	}
	public void setUseredyear(String useredyear) {
		this.useredyear = useredyear;
	}
	public String getRatedweight() {
		return ratedweight;
	}
	public void setRatedweight(String ratedweight) {
		this.ratedweight = ratedweight;
	}
	public String getDevprop() {
		return devprop;
	}
	public void setDevprop(String devprop) {
		this.devprop = devprop;
	}
	public String getDevratio() {
		return devratio;
	}
	public void setDevratio(String devratio) {
		this.devratio = devratio;
	}
	public String getVolume() {
		return volume;
	}
	public void setVolume(String volume) {
		this.volume = volume;
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
	public String getBelongunit() {
		return belongunit;
	}
	public void setBelongunit(String belongunit) {
		this.belongunit = belongunit;
	}
	public String getBelongname() {
		return belongname;
	}
	public void setBelongname(String belongname) {
		this.belongname = belongname;
	}
	public String getEngineno() {
		return engineno;
	}
	public void setEngineno(String engineno) {
		this.engineno = engineno;
	}
	public String getFrameno() {
		return frameno;
	}
	public void setFrameno(String frameno) {
		this.frameno = frameno;
	}
	public String getDevstatus() {
		return devstatus;
	}
	public void setDevstatus(String devstatus) {
		this.devstatus = devstatus;
	}
	public String getDevstatusname() {
		return devstatusname;
	}
	public void setDevstatusname(String devstatusname) {
		this.devstatusname = devstatusname;
	}
	public String getDtid() {
		return dtid;
	}
	public void setDtid(String dtid) {
		this.dtid = dtid;
	}
	public String getDtidname() {
		return dtidname;
	}
	public void setDtidname(String dtidname) {
		this.dtidname = dtidname;
	}
	public String getVendorid() {
		return vendorid;
	}
	public void setVendorid(String vendorid) {
		this.vendorid = vendorid;
	}
	public String getVdevid() {
		return vdevid;
	}
	public void setVdevid(String vdevid) {
		this.vdevid = vdevid;
	}
	public String getDevkind() {
		return devkind;
	}
	public void setDevkind(String devkind) {
		this.devkind = devkind;
	}
	public String getDevkindname() {
		return devkindname;
	}
	public void setDevkindname(String devkindname) {
		this.devkindname = devkindname;
	}
	public String getLeavedate() {
		return leavedate;
	}
	public void setLeavedate(String leavedate) {
		this.leavedate = leavedate;
	}
	public String getInstalladdr() {
		return installaddr;
	}
	public void setInstalladdr(String installaddr) {
		this.installaddr = installaddr;
	}
	public String getStartvalue() {
		return startvalue;
	}
	public void setStartvalue(String startvalue) {
		this.startvalue = startvalue;
	}
	public String getRemainvalue() {
		return remainvalue;
	}
	public void setRemainvalue(String remainvalue) {
		this.remainvalue = remainvalue;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public String getManufacturingno() {
		return manufacturingno;
	}
	public void setManufacturingno(String manufacturingno) {
		this.manufacturingno = manufacturingno;
	}
	public String getDevvalue() {
		return devvalue;
	}
	public void setDevvalue(String devvalue) {
		this.devvalue = devvalue;
	}
	public String getEnginehao() {
		return enginehao;
	}
	public void setEnginehao(String enginehao) {
		this.enginehao = enginehao;
	}
	public String getProduction() {
		return production;
	}
	public void setProduction(String production) {
		this.production = production;
	}
	public String getWorkhour() {
		return workhour;
	}
	public void setWorkhour(String workhour) {
		this.workhour = workhour;
	}
	public String getFuelconsumption() {
		return fuelconsumption;
	}
	public void setFuelconsumption(String fuelconsumption) {
		this.fuelconsumption = fuelconsumption;
	}
	public String getPartsconsumption() {
		return partsconsumption;
	}
	public void setPartsconsumption(String partsconsumption) {
		this.partsconsumption = partsconsumption;
	}
	public String getBughour() {
		return bughour;
	}
	public void setBughour(String bughour) {
		this.bughour = bughour;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
}
