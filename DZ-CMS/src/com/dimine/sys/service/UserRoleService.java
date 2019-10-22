package com.dimine.sys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dimine.base.service.BaseService;
import com.dimine.sys.dao.UserRoleDao;
import com.dimine.sys.entity.UserRoleEntity;

/**
 * 
 * <用户角色信息处理> <功能详细描述>
 * 
 * @author LCF
 * @version [版本号, 2014-7-25]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Service("userRoleService")
public class UserRoleService<T> extends BaseService<T> {
	@Autowired
	private UserRoleDao<T> dao;

	public UserRoleDao<T> getDao() {
		return dao;
	}

	/**
	 * <给用户添加指定的角色信息> <功能详细描述>
	 * 
	 * @param roles
	 * @param bean
	 * @see [类、类#方法、类#成员]
	 */
	public void insert(String[] roles, UserRoleEntity bean) {
		for (String role : roles) {
			// 获得roleid
			bean.setRoleid(role);
			dao.insert((T) bean);
		}
	}

	/**
	 * <撤销指定用户的指定角色信息> <功能详细描述>
	 * 
	 * @param roles
	 * @param bean
	 * @see [类、类#方法、类#成员]
	 */
	public void delete(String[] roles, UserRoleEntity bean) {
		for (String role : roles) {
			// 获得roleid
			bean.setRoleid(role);
			// 执行删除操作
			dao.delete((T) bean);
		}
	}

	/**
	 * 未被授予的角色列表
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	public List<UserRoleEntity> selectUnList(UserRoleEntity bean)
			throws Exception {
		Integer rowCount = dao.selectUnCount(bean);
		bean.getPager().setRowCount(rowCount);
		return (List<UserRoleEntity>) dao.selectUnList(bean);
	}

	/**
	 * 根据角色id查询有该角色的用户信息
	 * 
	 * @param roleid
	 * @return
	 */
	public List<UserRoleEntity> selectByRoleid(String roleid) {
		return dao.selectByRoleid(roleid);
	}

	/**
	 * 根据用户id查询该用户所有的角色信息
	 * @param userid
	 * @return
	 */
	public List<UserRoleEntity> selectByUserid(String userid) {
		return dao.selectByUserid(userid);
	}

	public String getBiztypename() {
		// TODO Auto-generated method stub
		return "用户角色信息管理";
	}

}
