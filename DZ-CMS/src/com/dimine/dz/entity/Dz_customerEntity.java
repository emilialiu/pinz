package com.dimine.dz.entity;

import com.dimine.base.entity.BaseEntity;

/**
 * 会员信息信息类
 * 
 * @author dimine 2019-08-29 19:07:02
 * 
 */
public class Dz_customerEntity extends BaseEntity {

	/** id */
	private String uid = "";
	/** 微信open_id */
	private String openid = "";
	/** 会员等级： 0普通 1vip */
	private String vip_level = "";
	/** 姓名 */
	private String name = "";
	/** 昵称 */
	private String nick_name = "";
	/** 头像路径 */
	private String head_url = "";
	/** 积分数 */
	private String integral_num = "";
	/** 性别 */
	private String sex = "";
	/** 微信标识 */
	private String union_id = "";
	/** 所在市 */
	private String city = "";
	/** 所在省 */
	private String province = "";
	/** 所在国家 */
	private String country = "";
	/** 创建时间 */
	private String create_time = "";
	/** 最后登录时间 */
	private String last_logon_time = "";
	/** 所属代理商id */
	private String belong_agent = "";
	private String sexname = "";
	private String viplevelname = "";
	
	public String getSexname() {
		return sexname;
	}
	public void setSexname(String sexname) {
		this.sexname = sexname;
	}

	public String getViplevelname() {
		return viplevelname;
	}
	public void setViplevelname(String viplevelname) {
		this.viplevelname = viplevelname;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getVip_level() {
		return vip_level;
	}
	public void setVip_level(String vip_level) {
		this.vip_level = vip_level;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNick_name() {
		return nick_name;
	}
	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}
	public String getHead_url() {
		return head_url;
	}
	public void setHead_url(String head_url) {
		this.head_url = head_url;
	}
	public String getIntegral_num() {
		return integral_num;
	}
	public void setIntegral_num(String integral_num) {
		this.integral_num = integral_num;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getUnion_id() {
		return union_id;
	}
	public void setUnion_id(String union_id) {
		this.union_id = union_id;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public String getLast_logon_time() {
		return last_logon_time;
	}
	public void setLast_logon_time(String last_logon_time) {
		this.last_logon_time = last_logon_time;
	}
	public String getBelong_agent() {
		return belong_agent;
	}
	public void setBelong_agent(String belong_agent) {
		this.belong_agent = belong_agent;
	}

}
