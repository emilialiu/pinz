package com.dimine.coupon.entity;

import com.dimine.base.entity.BaseEntity;

/**
 * 优惠券使用记录表信息类
 * 
 * @author dimine 2019-08-29 19:42:55
 * 
 */
public class Dz_coupon_user_recordEntity extends BaseEntity {

	/** id */
	private String id = "";
	/** 订单id */
	private String order_id = "";
	/** 客户id */
	private String customer_id = "";
	/** 使用时间 */
	private String create_time = "";
	/** 状态 */
	private String state = "";
	/** 过期时间 */
	private String expire_time = "";
	/** 优惠券金额 */
	private String coupon_amount = "";

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public String getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getExpire_time() {
		return expire_time;
	}
	public void setExpire_time(String expire_time) {
		this.expire_time = expire_time;
	}
	public String getCoupon_amount() {
		return coupon_amount;
	}
	public void setCoupon_amount(String coupon_amount) {
		this.coupon_amount = coupon_amount;
	}

}
