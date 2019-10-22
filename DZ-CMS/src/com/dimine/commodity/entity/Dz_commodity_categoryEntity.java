package com.dimine.commodity.entity;

import com.dimine.base.entity.BaseEntity;

/**
 * 商品分类表信息类
 * 
 * @author dimine 2019-08-29 20:06:46
 * 
 */
public class Dz_commodity_categoryEntity extends BaseEntity {

	/** id */
	private String id = "";
	/** 分类名称 */
	private String name = "";
	/** 性别 */
	private String sex = "";
	/** 备注 */
	private String remarks = "";
	/** 排序 */
	private String sort = "";
	/** 创建时间 */
	private String create_time = "";
	/** 创建人 */
	private String create_by = "";
	/** 修改时间 */
	private String update_time = "";
	/** 修改人 */
	private String update_by = "";

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
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
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

}
