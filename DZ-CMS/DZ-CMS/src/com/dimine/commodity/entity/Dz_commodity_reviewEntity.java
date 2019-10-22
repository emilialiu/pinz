package com.dimine.commodity.entity;

import com.dimine.base.entity.BaseEntity;

/**
 * 商品评价表信息类
 * 
 * @author dimine 2019-08-29 19:55:44
 * 
 */
public class Dz_commodity_reviewEntity extends BaseEntity {

	/** id */
	private String id = "";
	/** 订单id */
	private String order_id = "";
	/** 商品id */
	private String commodity_id = "";
	/** 客户id */
	private String customer_id = "";
	/** 满意系数：星星表示 */
	private String satisfaction = "";
	/** 评论内容 */
	private String comments = "";
	/** 评论时间 */
	private String create_time = "";
	/** 是否删除 */
	private String is_delete = "";
	/** 图片地址 */
	private String img_url = "";
	/** 回复内容 */
	private String reply_content = "";

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
	public String getCommodity_id() {
		return commodity_id;
	}
	public void setCommodity_id(String commodity_id) {
		this.commodity_id = commodity_id;
	}
	public String getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}
	public String getSatisfaction() {
		return satisfaction;
	}
	public void setSatisfaction(String satisfaction) {
		this.satisfaction = satisfaction;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public String getIs_delete() {
		return is_delete;
	}
	public void setIs_delete(String is_delete) {
		this.is_delete = is_delete;
	}
	public String getImg_url() {
		return img_url;
	}
	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}
	public String getReply_content() {
		return reply_content;
	}
	public void setReply_content(String reply_content) {
		this.reply_content = reply_content;
	}

}
