package com.dimine.dz.entity;

import com.dimine.base.entity.BaseEntity;

/**
 * 客户等级管理信息类
 * 
 * @author dimine 2019-08-29 19:13:18
 * 
 */
public class Dz_memberEntity extends BaseEntity {

	/** id */
	private String id = "";
	/** 等级名称 */
	private String lever_name = "";
	/** 积分值,最大值 */
	private String maximum = "";
	/** 积分值,最小值 */
	private String minimum = "";
	/** 等级备注 */
	private String remarks = "";

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLever_name() {
		return lever_name;
	}
	public void setLever_name(String lever_name) {
		this.lever_name = lever_name;
	}
	public String getMaximum() {
		return maximum;
	}
	public void setMaximum(String maximum) {
		this.maximum = maximum;
	}
	public String getMinimum() {
		return minimum;
	}
	public void setMinimum(String minimum) {
		this.minimum = minimum;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
