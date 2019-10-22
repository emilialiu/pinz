package com.dimine.sm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dimine.base.common.ValidException;
import com.dimine.base.service.BaseService;
import com.dimine.base.util.Md5Encrypt;
import com.dimine.base.util.StringUtils;
import com.dimine.sm.dao.StaffDao;
import com.dimine.sm.entity.StaffEntity;
import com.dimine.sys.dao.UserDao;
import com.dimine.sys.entity.UserDeptEntity;
import com.dimine.sys.entity.UserEntity;

/**
 * 员工信息管理事务处理
 * 
 * @author dimine 2015-06-16 17:51:24
 * 
 */
@Service("staffService")
public class StaffService<T> extends BaseService<T> {

	@Autowired
	private StaffDao<T> dao;

	public StaffDao<T> getDao() {
		return dao;
	}

	@Autowired
	private UserDao<T> userDao;

	public UserDao<T> getUserDao() {
		return userDao;
	}

	/**
	 * 添加新的员工信息
	 * 
	 * @param bean
	 * @param actiontype
	 * @param user
	 */
	public void insert(StaffEntity bean) throws Exception {
		if (StringUtils.isNotBlank(bean.getStaffcode())) {
			checkCodeExist(bean, "addsave");
		}
		if (StringUtils.isNotBlank(bean.getLoginname())) {// 如果登录用户名不为空，则添加一条用户信息
			UserEntity user = new UserEntity();
			user.setUserid(bean.getStaffid());
			user.setDeptid(bean.getDeptid());
			user.setUsername(bean.getStaffname());
			user.setLoginname(bean.getLoginname());
			if (StringUtils.isNotBlank(bean.getLoginpwd())) {
				user.setLoginpwd(bean.getLoginpwd());
			} else {// 如果密码为空，则设置默认密码为123
				user.setLoginpwd("123");
			}
			loginNameExist(user, "addsave");
			user.setCreateman(bean.getCreateid());
			String enCoder = Md5Encrypt.md5(user.getLoginpwd());
			user.setLoginpwd(enCoder);
			userDao.insert((T) user);
			UserDeptEntity ud = new UserDeptEntity();
			ud.setUserid(user.getUserid());
			ud.setDeptid(user.getDeptid());
			userDao.insertEmployee(ud);
		}
		// 插入数据
		getDao().insert((T) bean);
	}

	/**
	 * 修改员工信息信息
	 * 
	 * @param bean
	 * @param actiontype
	 */
	public void update(StaffEntity bean, String actiontype) throws Exception {
		if (StringUtils.isNotBlank(bean.getStaffcode())) {
			checkCodeExist(bean, "modifysave");
		}
		UserEntity user = (UserEntity) userDao.selectById(bean.getStaffid());
		if (user != null) {
			user.setLoginname(bean.getLoginname());
			user.setUsername(bean.getStaffname());
			user.setModifyman(bean.getModifyid());
			loginNameExist(user, "modifysave");
			userDao.update((T) user);
		} else {
			if (StringUtils.isNotBlank(bean.getLoginname())) {// 如果登录用户名不为空，则添加一条用户信息
				user = new UserEntity();
				user.setUserid(bean.getStaffid());
				user.setDeptid(bean.getDeptid());
				user.setUsername(bean.getStaffname());
				user.setLoginname(bean.getLoginname());
				if (StringUtils.isNotBlank(bean.getLoginpwd())) {
					user.setLoginpwd(bean.getLoginpwd());
				} else {// 如果密码为空，则设置默认密码为123
					user.setLoginpwd("123");
				}
				loginNameExist(user, "addsave");
				user.setCreateman(bean.getCreateid());
				String enCoder = Md5Encrypt.md5(user.getLoginpwd());
				user.setLoginpwd(enCoder);
				userDao.insert((T) user);
				UserDeptEntity ud = new UserDeptEntity();
				ud.setUserid(user.getUserid());
				ud.setDeptid(user.getDeptid());
				userDao.insertEmployee(ud);
			}
		}
		// 执行修改操作
		getDao().update((T) bean);
	}

	/**
	 * 删除员工信息信息
	 * 
	 * @param bean
	 * 
	 */
	public void delete(StaffEntity bean) {
		// 删除员工信息
		getDao().delete((T) bean);
	}

	/**
	 * 删除员工（逻辑删除）
	 * 
	 * @param staffid
	 */
	public void deleteStaff(String staffid) {
		// 删除员工信息
		getDao().deleteStaff(staffid);
	}

	// 验证唯一性同一个员工编码不能重复提交
	public void checkCodeExist(StaffEntity bean, String actiontype)
			throws ValidException {
		int count = dao.getSameCode(bean);
		if (count > 0 && "addsave".equals(actiontype))
			throw new ValidException((new StringBuilder("当前员工编码【"))
					.append(bean.getStaffcode()).append("】已经存在!").toString());
		if (count == 1 && "modifysave".equals(actiontype))
			throw new ValidException((new StringBuilder("当前员工编码【"))
					.append(bean.getStaffcode()).append("】已经存在!").toString());
		else
			return;
	}

	public void loginNameExist(UserEntity user, String actiontype)
			throws ValidException {
		int count = userDao.countByIdAndName(user);
		if (count >= 1 && "addsave".equals(actiontype))
			throw new ValidException((new StringBuilder("登陆名【"))
					.append(user.getLoginname()).append("】已经被使用!").toString());
		if (count >= 2 && "modifysave".equals(actiontype))
			throw new ValidException((new StringBuilder("登陆名【"))
					.append(user.getLoginname()).append("】已经被使用!").toString());
		else
			return;
	}

	public String getBiztypename() {
		return "员工信息信息管理";
	}

	public List<StaffEntity> selectByList1(StaffEntity bean) {
		bean.getPager().setRowCount(getDao().selectByCount1(bean));
		return getDao().selectByList1(bean);

	}

}
