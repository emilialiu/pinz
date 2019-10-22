package com.dimine.sys.entity;

import com.dimine.base.entity.BaseEntity;

/**
 * 角色权限类
 * 
 * @author LGY
 */
public class RolePopedomEntity extends BaseEntity {
	/**
	 * RolePopedomEntity.yishouyu
	 */
	public static final String IBATIS_KEY_ROLE_FUNC_YISHOUYU = "RolePopedomEntity.yishouyu";

	/**
	 * RolePopedomEntity.weishouyu
	 */
	public static final String IBATIS_KEY_ROLE_FUNC_WEISHOUYU = "RolePopedomEntity.weishouyu";

	/**
	 * RolePopedomEntity.funclistbyroleid
	 */
	public static final String IBATIS_KEY_FUNC_LIST_BY_ROLEID = "RolePopedomEntity.funclistbyroleid";

	/**
	 * RolePopedomEntity.youxiaodizhibyroleid
	 */
	public static final String IBATIS_KEY_YOUXIAODIZHI_BY_ROLEID = "RolePopedomEntity.youxiaodizhibyroleid";

	/**
	 * RolePopedomEntity.yishouyufu
	 */
	public static final String IBATIS_KEY_ROLE_FUNC_YISHOUYUFU = "RolePopedomEntity.yishouyufu";

	// 权限编号
	private String funccode;

	// 角色编号
	private String roleid;

	//
	private String rolename = "";

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public String getFunccode() {
		return funccode;
	}

	public void setFunccode(String funccode) {
		this.funccode = funccode;
	}

	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}
}
