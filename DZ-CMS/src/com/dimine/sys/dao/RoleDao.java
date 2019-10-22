package com.dimine.sys.dao;

import java.util.List;
import java.util.Map;

import com.dimine.base.dao.BaseDao;
import com.dimine.base.dao.BizDao;

/**
 * Role Mapper
 * 
 * @author 徐飞雄（dominic）-多米
 * 
 */
@BizDao
public interface RoleDao<T> extends BaseDao<T> {

	public int countByIdAndName(Object obj);

	public void deleteForFunc(Object obj);

	public void deleteForUser(Object obj);

	public List<String> allRoleByDeptid(String deptid);
	/**
	 * 根据部门查询较色
	 * @param deptid
	 */
	public List getRolebydeptid(String deptid);
	/**
	 * 删除部门的角色
	 * @param deptid
	 */
	public void deleteBydeptid(String deptid);
	/**
	 * 修改角色 
	 * @param map
	 */
	public void Updateorg(Map map);
	/**
	 * 根据用户id查找角色
	 * @param userid
	 */
	public List findGroupsByUserId(String userid);
}
