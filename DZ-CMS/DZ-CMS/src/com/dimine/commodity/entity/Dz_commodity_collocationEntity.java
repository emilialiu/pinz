package com.dimine.commodity.entity;

import com.dimine.base.entity.BaseEntity;

/**
 * 商品搭配管理表信息类
 * 
 * @author dimine 2019-08-29 20:17:47
 * 
 */
public class Dz_commodity_collocationEntity extends BaseEntity {

	/** id */
	private String id = "";
	/** banner图路径 */
	private String banner_url = "";
	/** 设计师说明内容 */
	private String designer_said = "";
	/** 单品组成ids，用逗号分隔 */
	private String single_ids = "";
	/** 备注 */
	private String remarks = "";
	/** 创建时间 */
	private String create_time = "";
	/** 创建人 */
	private String create_by = "";
	/** 修改时间 */
	private String update_time = "";
	/** 修改人 */
	private String update_by = "";
	/** 设计师名称 */
	private String designer_name = "";
	/** 设计师头像 */
	private String designer_head_url = "";
	private String names="";
	public String getNames() {
		return names;
	}
	public void setNames(String names) {
		this.names = names;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBanner_url() {
		return banner_url;
	}
	public void setBanner_url(String banner_url) {
		this.banner_url = banner_url;
	}
	public String getDesigner_said() {
		return designer_said;
	}
	public void setDesigner_said(String designer_said) {
		this.designer_said = designer_said;
	}
	public String getSingle_ids() {
		return single_ids;
	}
	public void setSingle_ids(String single_ids) {
		this.single_ids = single_ids;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
	public String getDesigner_name() {
		return designer_name;
	}
	public void setDesigner_name(String designer_name) {
		this.designer_name = designer_name;
	}
	public String getDesigner_head_url() {
		return designer_head_url;
	}
	public void setDesigner_head_url(String designer_head_url) {
		this.designer_head_url = designer_head_url;
	}

}
