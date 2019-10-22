package com.dimine.sys.dao;

import java.util.List;
import java.util.Map;

import com.dimine.base.dao.BaseDao;
import com.dimine.base.dao.BizDao;
import com.dimine.sys.entity.FuncEntity;
import com.dimine.sys.entity.RolePopedomEntity;

/**
 * RolePopedomDao Mapper
 * 
 * @author LCF
 * 
 */
@BizDao
public interface RolePopedomDao<T> extends BaseDao<T> {

	/**
	 * 查找指定角色所有的功能信息
	 * 
	 * @param roleid
	 * @return
	 */
	public List<String> funcListByRoleid(String roleid);

	/**
	 * 已授予功能列表
	 * 
	 * @param bean
	 * @return
	 */
	public List<FuncEntity> yiShouYu(RolePopedomEntity bean);

	/**
	 * 功能撤销 找叶子节点的兄弟节点
	 * 
	 * @param bean
	 * @return
	 */
	public List<FuncEntity> yiShouYuErJi(RolePopedomEntity bean);

	/**
	 * 功能撤销 根据子节点找父节点
	 * 
	 * @param bean
	 * @return
	 */
	public FuncEntity fuJieDian(RolePopedomEntity bean);

	/**
	 * 已授予功能 子节点找父节点
	 * 
	 * @param bean
	 * @return
	 */
	public List<FuncEntity> yiShouYuFu(RolePopedomEntity bean);

	/**
	 * 未授予功能列表
	 * 
	 * @param map
	 * @return
	 */
	public List<FuncEntity> weiShouYu(Map<String, String> map);
	/**
	 * 快捷功能中未授予功能列表
	 * 
	 * @param map
	 * @return
	 */
	public List<FuncEntity> weiShouYuForshortcut(Map<String, String> map);
}
