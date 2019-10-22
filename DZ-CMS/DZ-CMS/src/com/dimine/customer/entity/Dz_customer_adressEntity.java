package com.dimine.customer.entity;

import com.dimine.base.entity.BaseEntity;

/**
 * 客户地址管理信息类
 * 
 * @author dimine 2019-09-01 15:26:59
 * 
 */
public class Dz_customer_adressEntity extends BaseEntity {

	/** id */
	private String id = "";
	/** 客户id */
	private String customer_id = "";
	/** 省级id */
	private String pro_id = "";
	/** 省级名称 */
	private String pro_name = "";
	/** 市级id */
	private String city_id = "";
	/** 市级名称 */
	private String city_name = "";
	/** 区级id */
	private String district_id = "";
	/** 区级名称 */
	private String district_name = "";
	/** 详细地址 */
	private String complet_address = "";
	/** 收货人姓名 */
	private String consignee_name = "";
	/** 电话号码 */
	private String phone = "";
	/** 是否设置为默认地址 */
	private String is_default = "";
	private String name="";
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}
	public String getPro_id() {
		return pro_id;
	}
	public void setPro_id(String pro_id) {
		this.pro_id = pro_id;
	}
	public String getPro_name() {
		return pro_name;
	}
	public void setPro_name(String pro_name) {
		this.pro_name = pro_name;
	}
	public String getCity_id() {
		return city_id;
	}
	public void setCity_id(String city_id) {
		this.city_id = city_id;
	}
	public String getCity_name() {
		return city_name;
	}
	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}
	public String getDistrict_id() {
		return district_id;
	}
	public void setDistrict_id(String district_id) {
		this.district_id = district_id;
	}
	public String getDistrict_name() {
		return district_name;
	}
	public void setDistrict_name(String district_name) {
		this.district_name = district_name;
	}
	public String getComplet_address() {
		return complet_address;
	}
	public void setComplet_address(String complet_address) {
		this.complet_address = complet_address;
	}
	public String getConsignee_name() {
		return consignee_name;
	}
	public void setConsignee_name(String consignee_name) {
		this.consignee_name = consignee_name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getIs_default() {
		return is_default;
	}
	public void setIs_default(String is_default) {
		this.is_default = is_default;
	}

}
