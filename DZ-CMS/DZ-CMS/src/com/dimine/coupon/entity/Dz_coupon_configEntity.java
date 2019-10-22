package com.dimine.coupon.entity;

import com.dimine.base.entity.BaseEntity;

/**
 * 优惠券配置信息类
 * 
 * @author dimine 2019-08-29 19:40:49
 * 
 */
public class Dz_coupon_configEntity extends BaseEntity {

	/** id */
	private String id = "";
	/** 分类id */
	private String type_id = "";
	/** 过期时间,具体时间 */
	private String expired_time = "";
	/** 过期时间,天数 */
	private String expired_num = "";
	/** 说明 */
	private String remarks = "";
	/** 优惠券金额 */
	private String voucher_amount = "";
	/** 商品id */
	private String commodity_id = "";
	private String commodityid = "";
	/** 会员等级id */
	private String member_level = "";
	private String memberlevel = "";
	/** 商品分类id */
	private String commodity_type_id = "";
	private String commoditytypeid = "";
	/** 创建时间 */
	private String create_time = "";
	/** 创建人 */
	private String create_by = "";
	/** 修改时间 */
	private String update_time = "";
	/** 修改人 */
	private String update_by = "";
	/** 状态 */
	private String state = "";
	private String statename="";
	private String typename="";
	
	
	public String getStatename() {
		return statename;
	}
	public void setStatename(String statename) {
		this.statename = statename;
	}
	public String getTypename() {
		return typename;
	}
	public void setTypename(String typename) {
		this.typename = typename;
	}
	/** 商品名称*/
	private String commodity_name = "";

	public String getCommodityid() {
		return commodityid;
	}
	public void setCommodityid(String commodityid) {
		this.commodityid = commodityid;
	}
	public String getMemberlevel() {
		return memberlevel;
	}
	public void setMemberlevel(String memberlevel) {
		this.memberlevel = memberlevel;
	}
	public String getCommoditytypeid() {
		return commoditytypeid;
	}
	public void setCommoditytypeid(String commoditytypeid) {
		this.commoditytypeid = commoditytypeid;
	}
	public String getCommodity_name() {
		return commodity_name;
	}
	public void setCommodity_name(String commodity_name) {
		this.commodity_name = commodity_name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType_id() {
		return type_id;
	}
	public void setType_id(String type_id) {
		this.type_id = type_id;
	}
	public String getExpired_time() {
		return expired_time;
	}
	public void setExpired_time(String expired_time) {
		this.expired_time = expired_time;
	}
	public String getExpired_num() {
		return expired_num;
	}
	public void setExpired_num(String expired_num) {
		this.expired_num = expired_num;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getVoucher_amount() {
		return voucher_amount;
	}
	public void setVoucher_amount(String voucher_amount) {
		this.voucher_amount = voucher_amount;
	}
	public String getCommodity_id() {
		return commodity_id;
	}
	public void setCommodity_id(String commodity_id) {
		this.commodity_id = commodity_id;
	}
	public String getMember_level() {
		return member_level;
	}
	public void setMember_level(String member_level) {
		this.member_level = member_level;
	}
	public String getCommodity_type_id() {
		return commodity_type_id;
	}
	public void setCommodity_type_id(String commodity_type_id) {
		this.commodity_type_id = commodity_type_id;
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
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}

}
