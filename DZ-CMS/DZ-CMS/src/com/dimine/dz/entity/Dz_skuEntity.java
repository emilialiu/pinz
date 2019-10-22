package com.dimine.dz.entity;

import java.util.List;

import com.dimine.base.entity.BaseEntity;

/**
 * 规格配置表信息类
 * 
 * @author dimine 2019-09-05 18:58:10
 * 
 */
public class Dz_skuEntity extends BaseEntity {

	/** id */
	private String id = "";
	/** 规格名称 */
	private String sku_name = "";
	/** 规格值 */
	private String sku_value = "";
	/** 父级id */
	private String parent_id = "";
	/** 排序 */
	private String sort = "";
	/** 商品分类ID */
	private String product_category_id = "";
	/** 创建人 */
	private String create_by = "";
	/** 创建时间 */
	private String create_time = "";
	/** 修改人 */
	private String update_by = "";
	/** 修改时间 */
	private String update_time = "";
	/** 规格code用于标识唯一性 */
	private String sku_code = "";
	/** 规格限制id */
	private String limit_ids = "";
	private String categoryname="";
	private List<Dz_skuEntity> list1;
	private List<Dz_skuEntity> list2;
	private List<String> codelist;
	
	public List<String> getCodelist() {
		return codelist;
	}
	public void setCodelist(List<String> codelist) {
		this.codelist = codelist;
	}
	public List<Dz_skuEntity> getList1() {
		return list1;
	}
	public void setList1(List<Dz_skuEntity> list1) {
		this.list1 = list1;
	}
	public List<Dz_skuEntity> getList2() {
		return list2;
	}
	public void setList2(List<Dz_skuEntity> list2) {
		this.list2 = list2;
	}
	public String getCategoryname() {
		return categoryname;
	}
	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSku_name() {
		return sku_name;
	}
	public void setSku_name(String sku_name) {
		this.sku_name = sku_name;
	}
	public String getSku_value() {
		return sku_value;
	}
	public void setSku_value(String sku_value) {
		this.sku_value = sku_value;
	}
	public String getParent_id() {
		return parent_id;
	}
	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getProduct_category_id() {
		return product_category_id;
	}
	public void setProduct_category_id(String product_category_id) {
		this.product_category_id = product_category_id;
	}
	public String getCreate_by() {
		return create_by;
	}
	public void setCreate_by(String create_by) {
		this.create_by = create_by;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public String getUpdate_by() {
		return update_by;
	}
	public void setUpdate_by(String update_by) {
		this.update_by = update_by;
	}
	public String getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}
	public String getSku_code() {
		return sku_code;
	}
	public void setSku_code(String sku_code) {
		this.sku_code = sku_code;
	}
	public String getLimit_ids() {
		return limit_ids;
	}
	public void setLimit_ids(String limit_ids) {
		this.limit_ids = limit_ids;
	}

}
