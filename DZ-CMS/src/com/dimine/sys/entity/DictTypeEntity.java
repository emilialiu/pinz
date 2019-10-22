package com.dimine.sys.entity;

import com.dimine.base.entity.BaseEntity;

/**
 * 数据字典类型
 * 
 * @author LCF
 * 
 */
public class DictTypeEntity extends BaseEntity {
	/**
	 * DictTypeEntity.ListByCanmodify
	 */
	public static final String IBATIS_KEY_DICTTYPEENTITY_LIST_BY_CANMODIFY = "DictTypeEntity.ListByCanmodify";

	/**
	 * DictTypeEntity.ListByCanmodify
	 */
	public static final String IBATIS_KEY_DICTTYPE_DELETE = "DictTypeEntity.delete";

	/**
	 * DictTypeEntity.count
	 */
	public static final String IBATIS_KEY_DICTTYPE_COUNT = "DictTypeEntity.count";

	private String recode = "";

	private String code = ""; // 分类编号

	private String name = ""; // 名称

	private String remark = ""; // 备注

	private String canmodify = "1"; // 是否可修改,默认为1,即能修改

	//
	private String tongpeifu = "";
	// 参数
	private String param = "";
	// 高级查询
	private String query = "";

	public String getTongpeifu() {
		return tongpeifu;
	}

	public void setTongpeifu(String tongpeifu) {
		this.tongpeifu = tongpeifu;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCanmodify() {
		return canmodify;
	}

	public void setCanmodify(String canmodify) {
		this.canmodify = canmodify;
	}

	public String getRecode() {
		return recode;
	}

	public void setRecode(String recode) {
		this.recode = recode;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

}
