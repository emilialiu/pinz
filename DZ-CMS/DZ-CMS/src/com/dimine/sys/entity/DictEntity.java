package com.dimine.sys.entity;

import com.dimine.base.entity.BaseEntity;

/**
 * 数据字典
 * 
 * @author LCF
 * 
 */
public class DictEntity extends BaseEntity {
	/**
	 * DictEntity.dictnamebyid
	 */
	public static final String IBATIS_KEY_DICT_NAME_BY_ID = "DictEntity.dictnamebyid";

	/**
	 * DictEntity.listAll
	 */
	public static final String IBATIS_KEY_DICT_LIST_BY_TYPEID = "DictEntity.listAll";

	/**
	 * DictEntity.delete
	 */
	public static final String IBATIS_KEY_DICT_DELETE = "DictEntity.delete";

	/**
	 * DictEntity.deletebytype
	 */
	public static final String IBATIS_KEY_DICT_DELETE_BY_TYPE = "DictEntity.deletebytype";

	private String code = ""; // 字典类型编码

	private String typeid = ""; // 字典列表编号

	private String paramname = ""; // 字典字段名称

	private String paramother = ""; // 字典其它参数

	private String paramremark = ""; // 字典字段描述

	private String parentid = ""; // 字典父编号

	private String canmodify = ""; // 是否可修改
	private String deptid="";//组织机构id
	
	private Integer orderno; // 序号
	// 参数
	private String param = "";
	// 高级查询
	private String query = "";
	//阶段
	private String stagetype="";
	
	/** 父节点的名称*/
	private String parentname;
	private String dicttypeid;
	
	public String getDeptid() {
		return deptid;
	}

	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}

	public String getDicttypeid() {
		return dicttypeid;
	}

	public void setDicttypeid(String dicttypeid) {
		this.dicttypeid = dicttypeid;
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTypeid() {
		return typeid;
	}

	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}

	public String getParamname() {
		return paramname;
	}

	public void setParamname(String paramname) {
		this.paramname = paramname;
	}

	public String getParamother() {
		return paramother;
	}

	public void setParamother(String paramother) {
		this.paramother = paramother;
	}

	public String getParamremark() {
		return paramremark;
	}

	public void setParamremark(String paramremark) {
		this.paramremark = paramremark;
	}

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	public String getCanmodify() {
		return canmodify;
	}

	public void setCanmodify(String canmodify) {
		this.canmodify = canmodify;
	}

	public Integer getOrderno() {
		return orderno;
	}

	public void setOrderno(Integer orderno) {
		this.orderno = orderno;
	}

	public String getStagetype() {
		return stagetype;
	}

	public void setStagetype(String stagetype) {
		this.stagetype = stagetype;
	}

	public String getParentname() {
		return parentname;
	}

	public void setParentname(String parentname) {
		this.parentname = parentname;
	}
	
	
}
