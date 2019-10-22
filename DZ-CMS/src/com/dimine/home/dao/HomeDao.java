package com.dimine.home.dao;

import java.util.List;

import com.dimine.base.dao.BaseDao;
import com.dimine.base.dao.BizDao;
import com.dimine.sys.entity.FuncEntity;

/**
 * 首页dao
 * 
 * @author ssm
 * 
 */
@BizDao
public interface HomeDao<T> extends BaseDao<T> {
	/**
	 * 根据父功能code查找所有子功能
	 * 
	 * @param func
	 * @return
	 */
	public List<FuncEntity> listByParent(FuncEntity func);

	/**
	 * 查找所有的父级菜单
	 * 
	 * @param func
	 * @return
	 */
	public List<FuncEntity> listByParentTop(FuncEntity func);

	public List<FuncEntity> listSelectParentTop(FuncEntity func);

	public List<FuncEntity> listByParentSec(FuncEntity func);

	/**
	 * 根据角色查找满足条件的数据
	 * 
	 * @param func
	 * @return
	 */
	public List<FuncEntity> searchByRoleid(FuncEntity func);

	/**
	 * 根据参数编号取得参数值
	 * 
	 * @param code
	 * @return
	 */
	public String getValueByCode(String code);

	/**
	 * 导航栏搜索
	 * 
	 * @param func
	 * @return
	 */
	public List<FuncEntity> searchBynav(FuncEntity func);

	/**
	 * 首页菜单搜索
	 * 
	 * @param func
	 * @return
	 */
	public List<FuncEntity> doFuncSearch(FuncEntity func);

	/**
	 * 首页菜单搜索
	 * 
	 * @param func
	 * @return
	 */
	public List<FuncEntity> doFuncSearch1(FuncEntity func);
}
