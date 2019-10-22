package com.dimine.sm.entity;

import com.dimine.base.entity.BaseEntity;

/**
 * 员工信息信息类
 * 
 * @author dimine 2015-06-16 17:51:24
 * 
 */
public class StaffEntity extends BaseEntity {

	/** 员工ID */
	private String staffid;
	/** 部门id */
	private String deptid;
	private String deptname;
	/** 员工编码 */
	private String staffcode;
	/** 姓名 */
	private String staffname;
	/** 性别 */
	private String sex;
	/** 民族 */
	private String nation;
	/** 年龄 */
	private String age;
	/** 身份证号码 */
	private String idcard;
	/** 职务 */
	private String jobtitle;
	/** 职称 */
	private String tptitle;
	/** 入职时间 */
	private String entrydate;
	/** 岗位 */
	private String station;
	/** 家庭住址 */
	private String address;
	/** 联系电话 */
	private String relaphone;
	/** 是否离职 */
	private String isleaveoffice;
	/** 离职时间 */
	private String lodate;
	/** 离职原因 */
	private String loreason;
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
	/** 删除标识 */
	private String delflag;
	/** 操作类别 */
	private String operatetype;
	private String operatetypename;
	/** 操作类别学历 */
	private String education;
	private String educationname;
	private String sexname;
	private String ismanager;// 是否是部门主管
	private String loginname;// 登录名称
	private String loginpwd;// 登录密码
	/** 班组id */
	private String teamid;
	/** 角色名称 */
	private String rolename;

	public String getTeamid() {
		return teamid;
	}

	public void setTeamid(String teamid) {
		this.teamid = teamid;
	}

	public String getStaffid() {
		return staffid;
	}

	public void setStaffid(String staffid) {
		this.staffid = staffid;
	}

	public String getDeptid() {
		return deptid;
	}

	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}

	public String getDeptname() {
		return deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	public String getStaffcode() {
		return staffcode;
	}

	public void setStaffcode(String staffcode) {
		this.staffcode = staffcode;
	}

	public String getStaffname() {
		return staffname;
	}

	public void setStaffname(String staffname) {
		this.staffname = staffname;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getJobtitle() {
		return jobtitle;
	}

	public void setJobtitle(String jobtitle) {
		this.jobtitle = jobtitle;
	}

	public String getTptitle() {
		return tptitle;
	}

	public void setTptitle(String tptitle) {
		this.tptitle = tptitle;
	}

	public String getEntrydate() {
		return entrydate;
	}

	public void setEntrydate(String entrydate) {
		this.entrydate = entrydate;
	}

	public String getStation() {
		return station;
	}

	public void setStation(String station) {
		this.station = station;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRelaphone() {
		return relaphone;
	}

	public void setRelaphone(String relaphone) {
		this.relaphone = relaphone;
	}

	public String getIsleaveoffice() {
		return isleaveoffice;
	}

	public void setIsleaveoffice(String isleaveoffice) {
		this.isleaveoffice = isleaveoffice;
	}

	public String getLodate() {
		return lodate;
	}

	public void setLodate(String lodate) {
		this.lodate = lodate;
	}

	public String getLoreason() {
		return loreason;
	}

	public void setLoreason(String loreason) {
		this.loreason = loreason;
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

	public String getDelflag() {
		return delflag;
	}

	public void setDelflag(String delflag) {
		this.delflag = delflag;
	}

	public String getOperatetype() {
		return operatetype;
	}

	public void setOperatetype(String operatetype) {
		this.operatetype = operatetype;
	}

	public String getIsmanager() {
		return ismanager;
	}

	public void setIsmanager(String ismanager) {
		this.ismanager = ismanager;
	}

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getLoginpwd() {
		return loginpwd;
	}

	public void setLoginpwd(String loginpwd) {
		this.loginpwd = loginpwd;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getOperatetypename() {
		return operatetypename;
	}

	public void setOperatetypename(String operatetypename) {
		this.operatetypename = operatetypename;
	}

	public String getEducationname() {
		return educationname;
	}

	public void setEducationname(String educationname) {
		this.educationname = educationname;
	}

	public String getSexname() {
		return sexname;
	}

	public void setSexname(String sexname) {
		this.sexname = sexname;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

}
