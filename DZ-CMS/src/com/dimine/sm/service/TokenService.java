package com.dimine.sm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dimine.base.service.BaseService;
import com.dimine.base.util.KeyUtils;
import com.dimine.sm.dao.TokenDao;
import com.dimine.sm.entity.TokenEntity;

/**
 * 登录令牌管理事务处理
 * 
 * @author dimine 2016-04-12 10:57:25
 * 
 */
@Service("tokenService")
public class TokenService<T> extends BaseService<T> {

	@Autowired
	private TokenDao<T> dao;

	public TokenDao<T> getDao() {
		return dao;
	}

	/**
	 * 添加新的登录令牌信息
	 * 
	 * @param bean
	 * @param actiontype
	 * @param user
	 */
	public void insert(TokenEntity bean) throws Exception {
		// 插入数据
		getDao().insert((T) bean);
	}

	/**
	 * 修改登录令牌信息
	 * 
	 * @param bean
	 * @param actiontype
	 */
	public void update(TokenEntity bean, String actiontype) throws Exception {
		// 执行修改操作
		getDao().update((T) bean);
	}

	/**
	 * 删除登录令牌信息
	 * 
	 * @param bean
	 * 
	 */
	public void delete(TokenEntity bean) {
		// 删除登录令牌信息
		getDao().delete((T) bean);
	}

	public String getBiztypename() {
		return "登录令牌信息管理";
	}

}
