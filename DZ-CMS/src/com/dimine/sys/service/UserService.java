package com.dimine.sys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dimine.base.common.ValidException;
import com.dimine.base.dao.BaseDao;
import com.dimine.base.service.BaseService;
import com.dimine.base.util.KeyUtils;
import com.dimine.base.util.Md5Encrypt;
import com.dimine.sys.dao.UserDao;
import com.dimine.sys.entity.UserDeptEntity;
import com.dimine.sys.entity.UserEntity;

@Service("userService")
public class UserService<T> extends BaseService<T> {
	@Autowired
	private UserDao<T> dao;

	@Override
	public BaseDao<T> getDao() {
		// TODO Auto-generated method stub
		return dao;
	}

	/**
	 * 更新密码
	 * 
	 * @param user
	 */
	public void updatePassword(UserEntity user) {
		String loginpwd = Md5Encrypt.md5(user.getLoginpwd());
		user.setLoginpwd(loginpwd);
		dao.updatepassword(user);
	}

	/**
	 * 删除
	 * 
	 * @param userid
	 * @param user
	 */
	public void delete(String userid) {
		dao.Deletebyuserid(userid);
		dao.DeleteDeptbyuserid(userid);
		dao.userDelete(userid);
	}

	/**
	 * 新增
	 * 
	 * @param bean
	 * @param user
	 */
	public void insert(UserEntity bean, String user) throws Exception {
		String userID = KeyUtils.createKeyID();
		bean.setUserid(userID);
		String loginName = bean.getLoginname().trim();
		bean.setLoginname(loginName);
		loginNameExist(bean, "addsave");
		bean.setCreateman(user);
		String enCoder = Md5Encrypt.md5(bean.getLoginpwd());
		bean.setLoginpwd(enCoder);
		dao.insert((T) bean);
		UserDeptEntity ud = new UserDeptEntity();
		ud.setUserid(bean.getUserid());
		ud.setDeptid(bean.getDeptid());
		dao.insertEmployee(ud);
	}

	/**
	 * 修改
	 * 
	 * @param bean
	 * @param actiontype
	 * @throws Exception
	 */
	public void update(UserEntity bean, String actiontype) throws Exception {
		loginNameExist(bean, "modifysave");
		// 执行修改操作
		getDao().update((T) bean);
	}

	public void loginNameExist(UserEntity user, String actiontype)
			throws ValidException {
		int count = dao.countByIdAndName(user);
		if (count >= 1 && "addsave".equals(actiontype))
			throw new ValidException((new StringBuilder("登陆名【"))
					.append(user.getLoginname()).append("】已经被使用!").toString());
		if (count >= 2 && "modifysave".equals(actiontype))
			throw new ValidException((new StringBuilder("登陆名【"))
					.append(user.getLoginname()).append("】已经被使用!").toString());
		else
			return;
	}

	/**
	 * 根据用户名和密码查询用户信息
	 * 
	 * @param loginname
	 * @param loginpwd
	 * @return
	 */
	public UserEntity selectByLoginInfo(UserEntity user){
		return dao.selectByLoginInfo(user);
	}

	/**
	 * 取得当前登录人所在部门id
	 * 
	 * @param userid
	 * @return
	 */
	// public String getCurUserDeptid(String userid) {
	// return dao.getCurUserDeptid(userid);
	// }

}
