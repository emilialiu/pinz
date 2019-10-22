package com.dimine.pub.entity;

import java.io.FileInputStream;

import com.dimine.base.entity.BaseEntity;

/**
 * 附件表(T_PUB_Attachment)信息类
 * 
 * @author dimine 2017-01-13 15:25:40
 * 
 */
public class T_pub_attachmentEntity extends BaseEntity {

	/** 附件ID */
	private String attachmentid = "";
	/** 所属矿山 */
	private String deptid = "";
	/** 附件业务类型 */
	private String bzstype = "";
	/** 业务ID */
	private String businessid = "";
	/** 附件类型： */
	private String attachmenttype = "";
	/** 附件名称 */
	private String attachmentname = "";
	/** 附件所在路径 */
	private String attachmenturl = "";
	/** 附件所属年份 */
	private String yearvalue = "";
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
	private String atname = "";
	private FileInputStream photo = null;
	private String infoid;
	private String detailid;
	private String filename;
	private String filepath;
	
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	public String getInfoid() {
		return infoid;
	}
	public void setInfoid(String infoid) {
		this.infoid = infoid;
	}
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
	public FileInputStream getPhoto() {
		return photo;
	}
	public void setPhoto(FileInputStream photo) {
		this.photo = photo;
	}
	public String getAtname() {
		return atname;
	}
	public void setAtname(String atname) {
		this.atname = atname;
	}
	public String getDetailid() {
		return detailid;
	}
	public void setDetailid(String detailid) {
		this.detailid = detailid;
	}
	
}
