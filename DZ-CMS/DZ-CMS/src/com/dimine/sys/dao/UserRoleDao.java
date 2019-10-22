package com.dimine.sys.dao;

import java.util.List;

import com.dimine.base.dao.BaseDao;
import com.dimine.base.dao.BizDao;
import com.dimine.sys.entity.UserRoleEntity;

@BizDao
public interface UserRoleDao<T> extends BaseDao<T> {
	/**
	 * 未被授予的角色列表
	 * 
	 * @param bean
	 * @return
	 */
	public List<UserRoleEntity> selectUnList(UserRoleEntity bean);

	/**
	 * 未被授予的角色记录数
	 * 
	 * @param bean
	 * @return
	 */
	public int selectUnCount(UserRoleEntity bean);
	
	/**
	 * 根据角色id查询有该角色的用户信息
	 * 
	 * @param roleid
	 * @return
	 */
	public List<UserRoleEntity> selectByRoleid(String roleid);

	/**
	 * 根据用户id查询该用户所有的角色信息
	 * 
	 * @param userid
	 * @return
	 */
	public List<UserRoleEntity> selectByUserid(String userid);
}
