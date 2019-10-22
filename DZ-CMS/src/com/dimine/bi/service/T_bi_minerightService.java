package com.dimine.bi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dimine.base.service.BaseService;
import com.dimine.bi.dao.T_bi_minerightDao;
import com.dimine.bi.entity.T_bi_minerightEntity;

/**
 * 采矿权信息表管理事务处理
 * 
 * @author dimine 2017-08-15 10:39:07
 * 
 */
@Service("t_bi_minerightService")
public class T_bi_minerightService<T> extends BaseService<T> {

	@Autowired
	private T_bi_minerightDao<T> dao;

	public T_bi_minerightDao<T> getDao() {
		return dao;
	}

	/**
	 * 添加新的采矿权信息表
	 * 
	 * @param bean
	 * @param actiontype
	 * @param user
	 */
	public void insert(T_bi_minerightEntity bean) throws Exception {
		// String keyID = KeyUtils.createKeyID();
		// // 生成编号
		// bean.setRightid(keyID);
		// 插入数据
		getDao().insert((T) bean);
	}

	/**
	 * 修改采矿权信息表信息
	 * 
	 * @param bean
	 * @param actiontype
	 */
	public void update(T_bi_minerightEntity bean, String actiontype)
			throws Exception {
		// 执行修改操作
		getDao().update((T) bean);
	}

	/**
	 * 删除采矿权信息表信息
	 * 
	 * @param bean
	 * 
	 */
	public void delete(T_bi_minerightEntity bean) {
		// 删除采矿权信息表
		getDao().delete((T) bean);
	}

	public String getBiztypename() {
		return "采矿权信息表信息管理";
	}

}
