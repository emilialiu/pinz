package com.dimine.customer.entity;

import com.dimine.base.entity.BaseEntity;

/**
 * 客户等级升级表信息类
 * 
 * @author dimine 2019-09-01 15:29:27
 * 
 */
public class Dz_customer_member_recordEntity extends BaseEntity {

	/** id */
	private String id = "";
	/** 客户id */
	private String customer_id = "";
	/** 会员等级id */
	private String member_id = "";
	/** 升级时间 */
	private String create_time = "";
	private String nickname="";
	private String levername="";
	
	
	
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getLevername() {
		return levername;
	}
	public void setLevername(String levername) {
		this.levername = levername;
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
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

}
