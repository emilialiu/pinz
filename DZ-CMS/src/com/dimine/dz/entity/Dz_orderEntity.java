package com.dimine.dz.entity;

import com.dimine.base.entity.BaseEntity;

/**
 * 订单管理信息类
 * 
 * @author dimine 2019-08-29 19:52:32
 * 
 */
public class Dz_orderEntity extends BaseEntity {

	/** id */
	private String id = "";
	/** 订单编号 */
	private String order_code = "";
	/** 客户id */
	private String customer_id = "";
	/** 总价金额 */
	private String total_money = "";
	/** 已付定金金额 */
	private String deposit_paid = "";
	/** 付定金时间 */
	private String deposit_paid_time = "";
	/** 剩余尾款金额 */
	private String final_payment = "";
	/** 运费 */
	private String freight = "";
	/** 订单状态 */
	private String state = "";
	/** 是否取消订单 */
	private String is_cancel = "";
	/** 创建订单时间 */
	private String create_time = "";
	/** 创建客户id */
	private String create_by = "";
	/** 管理员修改订单状态时间 */
	private String update_time = "";
	/** 修改人 */
	private String update_by = "";
	/** 付尾款时间 */
	private String payment_time = "";
	/** 已付尾款金额 */
	private String payment_money = "";
	/** 物流单号 */
	private String logistics_number = "";

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrder_code() {
		return order_code;
	}
	public void setOrder_code(String order_code) {
		this.order_code = order_code;
	}
	public String getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}
	public String getTotal_money() {
		return total_money;
	}
	public void setTotal_money(String total_money) {
		this.total_money = total_money;
	}
	public String getDeposit_paid() {
		return deposit_paid;
	}
	public void setDeposit_paid(String deposit_paid) {
		this.deposit_paid = deposit_paid;
	}
	public String getDeposit_paid_time() {
		return deposit_paid_time;
	}
	public void setDeposit_paid_time(String deposit_paid_time) {
		this.deposit_paid_time = deposit_paid_time;
	}
	public String getFinal_payment() {
		return final_payment;
	}
	public void setFinal_payment(String final_payment) {
		this.final_payment = final_payment;
	}
	public String getFreight() {
		return freight;
	}
	public void setFreight(String freight) {
		this.freight = freight;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getIs_cancel() {
		return is_cancel;
	}
	public void setIs_cancel(String is_cancel) {
		this.is_cancel = is_cancel;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public String getCreate_by() {
		return create_by;
	}
	public void setCreate_by(String create_by) {
		this.create_by = create_by;
	}
	public String getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}
	public String getUpdate_by() {
		return update_by;
	}
	public void setUpdate_by(String update_by) {
		this.update_by = update_by;
	}
	public String getPayment_time() {
		return payment_time;
	}
	public void setPayment_time(String payment_time) {
		this.payment_time = payment_time;
	}
	public String getPayment_money() {
		return payment_money;
	}
	public void setPayment_money(String payment_money) {
		this.payment_money = payment_money;
	}
	public String getLogistics_number() {
		return logistics_number;
	}
	public void setLogistics_number(String logistics_number) {
		this.logistics_number = logistics_number;
	}

}
