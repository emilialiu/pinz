package com.dimine.sys.dao;

import com.dimine.base.dao.BaseDao;
import com.dimine.base.dao.BizDao;
import com.dimine.sys.entity.UserEntity;

@BizDao
public interface UserDao<T> extends BaseDao<T> {
	public void updatepassword(UserEntity user);

	/**
	 * 删除较色用户关联
	 * 
	 * @param userid
	 */
	public void Deletebyuserid(String userid);

	/**
	 * 删除部门用户关联
	 */
	public void DeleteDeptbyuserid(String userid);

	/**
	 * 删除用户
	 * 
	 * @param userid
	 */
	public void userDelete(String userid);

	/**
	 * 新增
	 * 
	 */
	public void insertEmployee(Object obj);

	/**
	 * 验证唯一性
	 */
	public int countByIdAndName(Object obj);

	/**
	 * 取得当前登录人所在部门id
	 * 
	 * @param userid
	 * @return
	 */
	public String getCurUserDeptid(String userid);

	/**
	 * 根据用户名和密码查询用户信息
	 * 
	 * @param loginname
	 * @param loginpwd
	 * @return
	 */
	public UserEntity selectByLoginInfo(UserEntity user);
}
