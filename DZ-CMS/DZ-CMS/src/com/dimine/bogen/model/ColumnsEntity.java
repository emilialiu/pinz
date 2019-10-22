package com.dimine.bogen.model;

import com.dimine.base.entity.BaseEntity;
/**
 * 表所有列的信息实体
 * <p> ColumnsEntity </p>
 * 
 * @author  Aaron
 * @version 2014-12-12
 */
public class ColumnsEntity extends BaseEntity{
	private String column_name; //列名
	private String data_type; //列数据类型
	private String comments; //列注释
	public String getColumn_name() {
		return column_name;
	}
	public void setColumn_name(String column_name) {
		this.column_name = column_name;
	}
	public String getData_type() {
		return data_type;
	}
	public void setData_type(String data_type) {
		this.data_type = data_type;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}

}
