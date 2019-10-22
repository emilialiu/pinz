package com.dimine.order.entity;

import com.dimine.base.entity.BaseEntity;

/**
 * 商品明细信息类
 * 
 * @author dimine 2019-09-03 19:10:58
 * 
 */
public class Dz_order_detailEntity extends BaseEntity {

	/** id */
	private String id = "";
	/** 订单编号 */
	private String order_code = "";
	/** 商品id */
	private String commodity_id = "";
	/** 商品规格尺寸 */
	private String sku_content = "";
	/** 客户定制明细 */
	private String style_content = "";
	/** 客户身体尺寸 */
	private String custome_size_content = "";
	/** 商品标题 */
	private String commodity_title = "";
	/** 购买数量 */
	private String buy_count = "";
	/** 商品价格 */
	private String product_price = "";
	/** 定金 */
	private String ear_money = "";
	/** 商品图片 */
	private String commodity_img = "";
	/** 尾款 */
	private String tail_money = "";
	/** 线性条数 */
	private String linear_number = "";
	/** 线性材质 */
	private String linear_material_name = "";
	private String title="";
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
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
	public String getCommodity_id() {
		return commodity_id;
	}
	public void setCommodity_id(String commodity_id) {
		this.commodity_id = commodity_id;
	}
	public String getSku_content() {
		return sku_content;
	}
	public void setSku_content(String sku_content) {
		this.sku_content = sku_content;
	}
	public String getStyle_content() {
		return style_content;
	}
	public void setStyle_content(String style_content) {
		this.style_content = style_content;
	}
	public String getCustome_size_content() {
		return custome_size_content;
	}
	public void setCustome_size_content(String custome_size_content) {
		this.custome_size_content = custome_size_content;
	}
	public String getCommodity_title() {
		return commodity_title;
	}
	public void setCommodity_title(String commodity_title) {
		this.commodity_title = commodity_title;
	}
	public String getBuy_count() {
		return buy_count;
	}
	public void setBuy_count(String buy_count) {
		this.buy_count = buy_count;
	}
	public String getProduct_price() {
		return product_price;
	}
	public void setProduct_price(String product_price) {
		this.product_price = product_price;
	}
	public String getEar_money() {
		return ear_money;
	}
	public void setEar_money(String ear_money) {
		this.ear_money = ear_money;
	}
	public String getCommodity_img() {
		return commodity_img;
	}
	public void setCommodity_img(String commodity_img) {
		this.commodity_img = commodity_img;
	}
	public String getTail_money() {
		return tail_money;
	}
	public void setTail_money(String tail_money) {
		this.tail_money = tail_money;
	}
	public String getLinear_number() {
		return linear_number;
	}
	public void setLinear_number(String linear_number) {
		this.linear_number = linear_number;
	}
	public String getLinear_material_name() {
		return linear_material_name;
	}
	public void setLinear_material_name(String linear_material_name) {
		this.linear_material_name = linear_material_name;
	}

}
