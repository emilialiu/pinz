package com.dimine.sys.dao;

import java.util.List;

import com.dimine.base.dao.BaseDao;
import com.dimine.base.dao.BizDao;
import com.dimine.sys.entity.DictEntity;
import com.dimine.sys.entity.FuncEntity;
import com.dimine.sys.entity.ShortcutEntity;

/**
 * Func Mapper
 * 
 * @author LCF
 * 
 */
@BizDao
public interface FuncDao<T> extends BaseDao<T> {
	/**
	 * 取得树形数据列表
	 * 
	 * @param funccode
	 * @return
	 */
	public List<FuncEntity> tree(String funccode);

	/**
	 * 查询指定功能名称的数量
	 * 
	 * @param func
	 * @return
	 */
	public int countByName(FuncEntity func);

	/**
	 * 查找与有无子功能
	 * 
	 * @param func
	 * @return
	 */
	public int listByChild(FuncEntity func);

	/**
	 * 删除功能与角色关系表中的记录
	 * 
	 * @param func
	 */
	public void deleteForRole(FuncEntity func);

	/**
	 * 根据父功能code查找所有子功能
	 * 
	 * @param func
	 * @return
	 */
	public List<FuncEntity> listByParent(FuncEntity func);
	
	public List<ShortcutEntity> listByShortCut(ShortcutEntity shortcut);
	/**
	 * 查找所有的父级菜单
	 * @param func
	 * @return
	 */
	public List<FuncEntity> listByParentTop(FuncEntity func);
	
	public List<FuncEntity> listByParentSec(FuncEntity func);
	public List<FuncEntity> listByParentTopShortCut(FuncEntity func);
	
	public List<FuncEntity> listByParentSecShortCut(FuncEntity func);

	/**
	 * 根据角色查找满足条件的数据
	 * 
	 * @param func
	 * @return
	 */
	public List<FuncEntity> searchByRoleid(FuncEntity func);
	
	public FuncEntity searchByFuncEntity(String funccode);
	/**
	 * 删除所有快捷键记录
	 * 
	 * @param func
	 */
	public void deleteAllShortcut(ShortcutEntity shortcut);
	/**
	 * 插入快捷键记录
	 * 
	 * @param func
	 */
	public void insertShortcut(ShortcutEntity shortcut);
	/**
	 * 通过快捷键查询出FUN
	 * 
	 * @param func
	 */
	public List<FuncEntity> listShortcutByFunc(ShortcutEntity shortcut);
	/**
	 * 获得图标类型
	 * 
	 * @param func
	 */
	public List<DictEntity> getSelectTblx(String jb);
}
