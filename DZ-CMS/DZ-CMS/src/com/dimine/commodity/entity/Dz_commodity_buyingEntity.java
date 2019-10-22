package com.dimine.commodity.entity;

import com.dimine.base.entity.BaseEntity;

/**
 * 商品抢购管理表信息类
 * 
 * @author dimine 2019-08-29 20:20:09
 * 
 */
public class Dz_commodity_buyingEntity extends BaseEntity {

	/** id */
	private String id = "";
	/** 活动名称 */
	private String activity_name = "";
	/** 活动开始时间 */
	private String start_time = "";
	/** 活动结束时间 */
	private String end_time = "";
	/** 是否启用 */
	private String is_start = "";
	/** 创建时间 */
	private String create_time = "";
	/** 创建人 */
	private String create_by = "";
	/** 修改时间 */
	private String update_time = "";
	/** 修改人 */
	private String update_by = "";
	private String start="";
	
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getActivity_name() {
		return activity_name;
	}
	public void setActivity_name(String activity_name) {
		this.activity_name = activity_name;
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	public String getIs_start() {
		return is_start;
	}
	public void setIs_start(String is_start) {
		this.is_start = is_start;
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
