package com.dimine.home.entity;

import com.dimine.base.entity.BaseEntity;

/**
 * 首页banner管理信息类
 * 
 * @author dimine 2019-08-29 20:03:20
 * 
 */
public class Dz_home_page_managerEntity extends BaseEntity {

	/** id */
	private String id = "";
	/** banner图url */
	private String img_url = "";
	/** 是否启用 */
	private String is_start = "";
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
	/** 备注 */
	private String remarks = "";
	private String isstart="";
	
	public String getIsstart() {
		return isstart;
	}
	public void setIsstart(String isstart) {
		this.isstart = isstart;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getImg_url() {
		return img_url;
	}
	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}
	public String getIs_start() {
		return is_start;
	}
	public void setIs_start(String is_start) {
		this.is_start = is_start;
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
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
