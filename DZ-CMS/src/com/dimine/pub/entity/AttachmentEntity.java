package com.dimine.pub.entity;

import com.dimine.base.entity.BaseEntity;

public class AttachmentEntity extends BaseEntity {

	/** 附件ID */
	private String attachmentid;
	/** 矿山ID */
	private String deptid;
	private String deptname;
	/** 业务类型 */
	private String bzstype;
	private String bzstypename;
	/** 业务ID */
	private String businessid;
	/** 附件类型 */
	private String attachmenttype;
	/** 附件名称 */
	private String attachmentname;
	/** 附件所在路径 */
	private String attachmenturl;
	/** 附件所属年份 */
	private String yearvalue;
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

	/** 培训类型：三级安全教育培训、特种作业人员培训、转岗、复岗培训、“三违”人员培训、分层次培训、四新培训、其他培训 */
	private String trainingtype;
	private String trainingtypename;

	public String getAttachmentid() {
		return attachmentid;
	}

	public void setAttachmentid(String attachmentid) {
		this.attachmentid = attachmentid;
	}

	public String getDeptid() {
		return deptid;
	}

	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}

	public String getBzstype() {
		return bzstype;
	}

	public void setBzstype(String bzstype) {
		this.bzstype = bzstype;
	}

	public String getBusinessid() {
		return businessid;
	}

	public void setBusinessid(String businessid) {
		this.businessid = businessid;
	}

	public String getAttachmenttype() {
		return attachmenttype;
	}

	public void setAttachmenttype(String attachmenttype) {
		this.attachmenttype = attachmenttype;
	}

	public String getAttachmentname() {
		return attachmentname;
	}

	public void setAttachmentname(String attachmentname) {
		this.attachmentname = attachmentname;
	}

	public String getAttachmenturl() {
		return attachmenturl;
	}

	public void setAttachmenturl(String attachmenturl) {
		this.attachmenturl = attachmenturl;
	}

	public String getYearvalue() {
		return yearvalue;
	}

	public void setYearvalue(String yearvalue) {
		this.yearvalue = yearvalue;
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

	public String getBzstypename() {
		return bzstypename;
	}

	public void setBzstypename(String bzstypename) {
		this.bzstypename = bzstypename;
	}

	public String getDeptname() {
		return deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	public String getTrainingtype() {
		return trainingtype;
	}

	public void setTrainingtype(String trainingtype) {
		this.trainingtype = trainingtype;
	}

	public String getTrainingtypename() {
		return trainingtypename;
	}

	public void setTrainingtypename(String trainingtypename) {
		this.trainingtypename = trainingtypename;
	}

}
