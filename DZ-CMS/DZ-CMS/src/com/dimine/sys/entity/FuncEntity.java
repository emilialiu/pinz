package com.dimine.sys.entity;

import com.dimine.base.entity.BaseEntity;

/**
 * 权限功能
 * 
 * @author LCF
 */
public class FuncEntity extends BaseEntity {

	/**
	 * FuncEntity.listbychild
	 */
	public static final String IBATIS_KEY_FUNC_CHILD_LIST = "FuncEntity.listbychild";

	/**
	 * FuncEntity.deleteforrole
	 */
	public static final String IBATIS_KEY_FUNC_DELETE_BY_FUNCCODE = "FuncEntity.deleteforrole";

	/**
	 * FuncEntity.countbyname
	 */
	public static final String IBATIS_KEY_FUNC_COUNT_BY_NAME = "FuncEntity.countbyname";

	/**
	 * FuncEntity.tree
	 */
	public static final String IBATIS_KEY_FUNC_TREE = "FuncEntity.tree";

	// 功能编号
	private String funccode;
	// 上级功能
	private String parentfunccode;
	// 功能名称
	private String funcname;
	// 功能名称(用于判断)
	private String rename = "";
	// URL
	private String url;
	// 功能类型
	private String functype;
	// 功能图片
	private String funcicon = "";
	// 顺序
	private Integer orderno;
	// 备注
	private String memo;
	// 功能名称拼音
	private String funcnamepy;
	// 功能名称拼音首字母缩写
	private String funcnamepyshort;
	// 备注拼音
	private String memopy;
	// 备注拼音首字母缩写
	private String memopyshort;
	// 功能名称(英文名称)
	private String funcnameen;
	// 上级功能名称
	private String parentfunccodeName;
	// 上级功能名称
	private String reparent = "";
	// 用户编号
	private String userid = "";
	// 用于判断(如果为空，则parentfunccode不为空)
	private String flag = "";
	private String level;// 等级
	private String parentfuncName;
	private String rootpath;
	private String classpath;

	public String getRootpath() {
		return rootpath;
	}

	public void setRootpath(String rootpath) {
		this.rootpath = rootpath;
	}

	// 子节点个数
	private String childCount = "0";

	public String getFunccode() {
		return funccode;
	}

	public String getCode() {
		return funccode;
	}

	public void setFunccode(String funccode) {
		this.funccode = funccode;
	}

	public String getParentdeptid() {
		if (parentfunccode != null && parentfunccode.trim().equals(""))
			return null;
		return parentfunccode;
	}

	public String getParentfunccode() {
		if (parentfunccode != null && parentfunccode.trim().equals(""))
			return null;
		return parentfunccode;
	}

	public void setParentfunccode(String parentfunccode) {
		this.parentfunccode = parentfunccode;
	}

	public String getDeptname() {
		return funcname;
	}

	public String getFuncname() {
		return funcname;
	}

	public void setFuncname(String funcname) {
		this.funcname = funcname;
	}

	public String getUrl() {
		return url;
	}

	// 获得上级功能名称
	public String getParentfunccodeName() {
		return parentfunccodeName;
	}

	public void setParentfunccodeName(String parentfunccodeName) {
		this.parentfunccodeName = parentfunccodeName;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getFunctype() {
		return functype;
	}

	public void setFunctype(String functype) {
		this.functype = functype;
	}

	public String getFuncicon() {
		return funcicon;
	}

	public void setFuncicon(String funcicon) {
		this.funcicon = funcicon;
	}

	public Integer getOrderno() {
		return orderno;
	}

	public void setOrderno(Integer orderno) {
		this.orderno = orderno;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getFuncnamepy() {
		return funcnamepy;
	}

	public void setFuncnamepy(String funcnamepy) {
		this.funcnamepy = funcnamepy;
	}

	public String getFuncnamepyshort() {
		return funcnamepyshort;
	}

	public void setFuncnamepyshort(String funcnamepyshort) {
		this.funcnamepyshort = funcnamepyshort;
	}

	public String getMemopy() {
		return memopy;
	}

	public void setMemopy(String memopy) {
		this.memopy = memopy;
	}

	public String getMemopyshort() {
		return memopyshort;
	}

	public void setMemopyshort(String memopyshort) {
		this.memopyshort = memopyshort;
	}

	public String getRename() {
		return rename;
	}

	public void setRename(String rename) {
		this.rename = rename;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getReparent() {
		return reparent;
	}

	public void setReparent(String reparent) {
		this.reparent = reparent;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getParentfuncName() {
		return parentfuncName;
	}

	public void setParentfuncName(String parentfuncName) {
		this.parentfuncName = parentfuncName;
	}

	public String getChildCount() {
		return childCount;
	}

	public void setChildCount(String childCount) {
		this.childCount = childCount;
	}

	public String getClasspath() {
		return classpath;
	}

	public void setClasspath(String classpath) {
		this.classpath = classpath;
	}

	public String getFuncnameen() {
		return funcnameen;
	}

	public void setFuncnameen(String funcnameen) {
		this.funcnameen = funcnameen;
	}

}
