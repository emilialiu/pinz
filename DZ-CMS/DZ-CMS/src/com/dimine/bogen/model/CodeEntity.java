package com.dimine.bogen.model;

import com.dimine.base.entity.BaseEntity;

public class CodeEntity extends BaseEntity {
	private String type;//HTML 类型
	private String filedName;//字段名称
	private String description;//字段描述
	private String data_type;//字段类型
	private String isRequirement;//是否查询条件
	private String validator;//验证规则
	private String primaryKey;//是否主键
	private String modelName;//模块名称
	private String filePath;//JSP文件路径
	private String title;//列表标题
	private String className;//表名
	private String table_name;//表名
	private String funcShow;//是否在列表页面显示
	private String pageShow;//是否在增删改页面上显示
	private String isShowPK;//页面上主键是否显示
	
	
	public String getFuncShow() {
		return funcShow;
	}
	public void setFuncShow(String funcShow) {
		this.funcShow = funcShow;
	}
	public String getPageShow() {
		return pageShow;
	}
	public void setPageShow(String pageShow) {
		this.pageShow = pageShow;
	}
	public String getTable_name() {
		return table_name;
	}
	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getFiledName() {
		return filedName;
	}
	public void setFiledName(String filedName) {
		this.filedName = filedName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getValidator() {
		return validator;
	}
	public void setValidator(String validator) {
		this.validator = validator;
	}
	public String getPrimaryKey() {
		return primaryKey;
	}
	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}
	public String getData_type() {
		return data_type;
	}
	public void setData_type(String data_type) {
		this.data_type = data_type;
	}
	public String getIsRequirement() {
		return isRequirement;
	}
	public void setIsRequirement(String isRequirement) {
		this.isRequirement = isRequirement;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getIsShowPK() {
		return isShowPK;
	}
	public void setIsShowPK(String isShowPK) {
		this.isShowPK = isShowPK;
	}

}
