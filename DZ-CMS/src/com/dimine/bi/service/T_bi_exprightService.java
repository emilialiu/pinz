package com.dimine.bi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dimine.base.service.BaseService;
import com.dimine.bi.dao.T_bi_exprightDao;
import com.dimine.bi.entity.T_bi_exprightEntity;

/**
 * 探矿权信息表管理事务处理
 * 
 * @author dimine 2017-08-15 10:37:06
 * 
 */
@Service("t_bi_exprightService")
public class T_bi_exprightService<T> extends BaseService<T> {

	@Autowired
	private T_bi_exprightDao<T> dao;

	public T_bi_exprightDao<T> getDao() {
		return dao;
	}

	/**
	 * 添加新的探矿权信息表
	 * 
	 * @param bean
	 * @param actiontype
	 * @param user
	 */
	public void insert(T_bi_exprightEntity bean) throws Exception {
		// String keyID = KeyUtils.createKeyID();
		// // 生成编号
		// bean.setRightid(keyID);
		// 插入数据
		getDao().insert((T) bean);
	}

	/**
	 * 修改探矿权信息表信息
	 * 
	 * @param bean
	 * @param actiontype
	 */
	public void update(T_bi_exprightEntity bean, String actiontype)
			throws Exception {
		// 执行修改操作
		getDao().update((T) bean);
	}

	/**
	 * 删除探矿权信息表信息
	 * 
	 * @param bean
	 * 
	 */
	public void delete(T_bi_exprightEntity bean) {
		// 删除探矿权信息表
		getDao().delete((T) bean);
	}

	public String getBiztypename() {
		return "探矿权信息表信息管理";
	}

}
