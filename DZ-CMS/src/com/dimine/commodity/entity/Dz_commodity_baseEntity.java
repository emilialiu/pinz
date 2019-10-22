package com.dimine.commodity.entity;

import com.dimine.base.entity.BaseEntity;

/**
 * 商品基础表信息类
 * 
 * @author dimine 2019-08-29 20:15:45
 * 
 */
public class Dz_commodity_baseEntity extends BaseEntity {

	/** id */
	private String id = "";
	/** 商品名称 */
	private String title = "";
	/** 商品分类 */
	private String product_category_id = "";
	/** 产品款号 */
	private String code = "";
	/** 面料支数 */
	private String fabric_zhishu = "";
	/** 面料厚度 */
	private String fabric_thickness = "";
	/** 面料克重 */
	private String fabric_weight = "";
	/** 面料弹力 */
	private String fabric_elasticity = "";
	/** 适用季节 */
	private String work_season = "";
	/** 面料成分 */
	private String fabric_ingredient = "";
	/** banner图 */
	private String banner_img = "";
	/** 产品描述图片 */
	private String context_img = "";
	/** 原价 */
	private String original_price = "";
	/** 定金 */
	private String earnest_money = "";
	/** 秒杀价 */
	private String seckill_price = "";
	/** 是否热品 */
	private String is_hot_money = "";
	/** 是否新品 */
	private String is_new_product = "";
	/** 是否推荐 */
	private String is_recommend = "";
	/** 销量 */
	private String sales = "";
	/** 库存 */
	private String stock = "";

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getProduct_category_id() {
		return product_category_id;
	}
	public void setProduct_category_id(String product_category_id) {
		this.product_category_id = product_category_id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getFabric_zhishu() {
		return fabric_zhishu;
	}
	public void setFabric_zhishu(String fabric_zhishu) {
		this.fabric_zhishu = fabric_zhishu;
	}
	public String getFabric_thickness() {
		return fabric_thickness;
	}
	public void setFabric_thickness(String fabric_thickness) {
		this.fabric_thickness = fabric_thickness;
	}
	public String getFabric_weight() {
		return fabric_weight;
	}
	public void setFabric_weight(String fabric_weight) {
		this.fabric_weight = fabric_weight;
	}
	public String getFabric_elasticity() {
		return fabric_elasticity;
	}
	public void setFabric_elasticity(String fabric_elasticity) {
		this.fabric_elasticity = fabric_elasticity;
	}
	public String getWork_season() {
		return work_season;
	}
	public void setWork_season(String work_season) {
		this.work_season = work_season;
	}
	public String getFabric_ingredient() {
		return fabric_ingredient;
	}
	public void setFabric_ingredient(String fabric_ingredient) {
		this.fabric_ingredient = fabric_ingredient;
	}
	public String getBanner_img() {
		return banner_img;
	}
	public void setBanner_img(String banner_img) {
		this.banner_img = banner_img;
	}
	public String getContext_img() {
		return context_img;
	}
	public void setContext_img(String context_img) {
		this.context_img = context_img;
	}
	public String getOriginal_price() {
		return original_price;
	}
	public void setOriginal_price(String original_price) {
		this.original_price = original_price;
	}
	public String getEarnest_money() {
		return earnest_money;
	}
	public void setEarnest_money(String earnest_money) {
		this.earnest_money = earnest_money;
	}
	public String getSeckill_price() {
		return seckill_price;
	}
	public void setSeckill_price(String seckill_price) {
		this.seckill_price = seckill_price;
	}
	public String getIs_hot_money() {
		return is_hot_money;
	}
	public void setIs_hot_money(String is_hot_money) {
		this.is_hot_money = is_hot_money;
	}
	public String getIs_new_product() {
		return is_new_product;
	}
	public void setIs_new_product(String is_new_product) {
		this.is_new_product = is_new_product;
	}
	public String getIs_recommend() {
		return is_recommend;
	}
	public void setIs_recommend(String is_recommend) {
		this.is_recommend = is_recommend;
	}
	public String getSales() {
		return sales;
	}
	public void setSales(String sales) {
		this.sales = sales;
	}
	public String getStock() {
		return stock;
	}
	public void setStock(String stock) {
		this.stock = stock;
	}

}
