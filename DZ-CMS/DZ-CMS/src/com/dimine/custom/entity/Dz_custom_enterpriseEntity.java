package com.dimine.custom.entity;

import com.dimine.base.entity.BaseEntity;

/**
 * 企业定制管理信息类
 * 
 * @author dimine 2019-08-29 19:57:52
 * 
 */
public class Dz_custom_enterpriseEntity extends BaseEntity {

	/** id */
	private String id = "";
	/** 姓名 */
	private String name = "";
	/** 手机号码 */
	private String phone = "";
	/** 团购意向：什么类型，多少预算 */
	private String description = "";
	/** 说明 */
	private String remarks = "";
	/** 需求数量,数量由数据字典提供区间值 */
	private String demand_count = "";
	/** 定制时间 */
	private String create_time = "";
	/** 定制客户id */
	private String customer_id = "";
	/** 处理状态 */
	private String state = "";
	private String demandcount="";
	private String statename="";
	
	public String getStatename() {
		return statename;
	}
	public void setStatename(String statename) {
		this.statename = statename;
	}
	public String getDemandcount() {
		return demandcount;
	}
	public void setDemandcount(String demandcount) {
		this.demandcount = demandcount;
	}
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getDemand_count() {
		return demand_count;
	}
	public void setDemand_count(String demand_count) {
		this.demand_count = demand_count;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public String getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}

}
