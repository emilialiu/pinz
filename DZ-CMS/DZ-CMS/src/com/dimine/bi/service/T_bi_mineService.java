package com.dimine.bi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dimine.base.service.BaseService;
import com.dimine.base.util.KeyUtils;
import com.dimine.bi.dao.T_bi_mineDao;
import com.dimine.bi.entity.T_bi_mineEntity;

/**
 * 矿山信息(T_BI_Mine)管理事务处理
 * 
 * @author dimine 2017-08-15 10:30:26
 * 
 */
@Service("t_bi_mineService")
public class T_bi_mineService<T> extends BaseService<T> {

	@Autowired
	private T_bi_mineDao<T> dao;

	public T_bi_mineDao<T> getDao() {
		return dao;
	}

	/**
	 * 添加新的矿山信息(T_BI_Mine)
	 * 
	 * @param bean
	 * @param actiontype
	 * @param user
	 */
	public void insert(T_bi_mineEntity bean) throws Exception {
		String keyID = KeyUtils.createKeyID();
		// 生成编号
		bean.setMineid(keyID);
		// 插入数据
		getDao().insert((T) bean);
	}

	/**
	 * 修改矿山信息(T_BI_Mine)信息
	 * 
	 * @param bean
	 * @param actiontype
	 */
	public void update(T_bi_mineEntity bean, String actiontype)
			throws Exception {
		// 执行修改操作
		getDao().update((T) bean);
	}

	/**
	 * 删除矿山信息(T_BI_Mine)信息
	 * 
	 * @param bean
	 * 
	 */
	public void delete(T_bi_mineEntity bean) {
		// 删除矿山信息(T_BI_Mine)
		getDao().delete((T) bean);
	}

	public String getBiztypename() {
		return "矿山信息(T_BI_Mine)信息管理";
	}

}
