package com.dimine.coupon.entity;

import com.dimine.base.entity.BaseEntity;

/**
 * 优惠券分类管理信息类
 * 
 * @author dimine 2019-08-29 19:35:59
 * 
 */
public class Dz_coupon_typeEntity extends BaseEntity {

	/** id */
	private String id = "";
	/** 分类名称 */
	private String name = "";
	/** 备注 */
	private String remarks = "";

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
