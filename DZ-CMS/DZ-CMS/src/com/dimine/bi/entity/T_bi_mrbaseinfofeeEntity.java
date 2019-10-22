package com.dimine.bi.entity;

import com.dimine.base.entity.BaseEntity;

/**
 * 矿业权资金信息信息类
 * 
 * @author dimine 2017-08-25 18:36:53
 * 
 */
public class T_bi_mrbaseinfofeeEntity extends BaseEntity {

	/** 矿业权资金信息ID */
	private String feeid = "";
	/** 矿业权ID */
	private String rightid = "";
	/** 费用名称 */
	private String feename = "";
	/** 费用类型 */
	private String feetype = "";
	/** 费用发生阶段 */
	private String feestage = "";
	/** 费用（￥） */
	private String feeamount = "";
	/** 费用产生日期 */
	private String feedate = "";
	/** 备注 */
	private String memo = "";
	/** 创建人 */
	private String createid = "";
	/** 创建时间 */
	private String createdate = "";
	/** 修改人 */
	private String modifyid = "";
	/** 修改时间 */
	private String modifydate = "";

	public String getFeeid() {
		return feeid;
	}
	public void setFeeid(String feeid) {
		this.feeid = feeid;
	}
	public String getRightid() {
		return rightid;
	}
	public void setRightid(String rightid) {
		this.rightid = rightid;
	}
	public String getFeename() {
		return feename;
	}
	public void setFeename(String feename) {
		this.feename = feename;
	}
	public String getFeetype() {
		return feetype;
	}
	public void setFeetype(String feetype) {
		this.feetype = feetype;
	}
	public String getFeestage() {
		return feestage;
	}
	public void setFeestage(String feestage) {
		this.feestage = feestage;
	}
	public String getFeeamount() {
		return feeamount;
	}
	public void setFeeamount(String feeamount) {
		this.feeamount = feeamount;
	}
	public String getFeedate() {
		return feedate;
	}
	public void setFeedate(String feedate) {
		this.feedate = feedate;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
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

}
