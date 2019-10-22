package com.dimine.bi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dimine.base.service.BaseService;
import com.dimine.base.util.KeyUtils;
import com.dimine.bi.dao.T_bi_mrbaseinfofeeDao;
import com.dimine.bi.entity.T_bi_mrbaseinfofeeEntity;

/**
 * 矿业权资金信息管理事务处理
 * 
 * @author dimine 2017-08-25 18:36:53
 * 
 */
@Service("t_bi_mrbaseinfofeeService")
public class T_bi_mrbaseinfofeeService<T> extends BaseService<T> {

	@Autowired
	private T_bi_mrbaseinfofeeDao<T> dao;

	public T_bi_mrbaseinfofeeDao<T> getDao() {
		return dao;
	}

	/**
	 * 添加新的矿业权资金信息
	 * 
	 * @param bean
	 * @param actiontype
	 * @param user
	 */
	public void insert(T_bi_mrbaseinfofeeEntity bean) throws Exception {
		String keyID = KeyUtils.createKeyID();
		// 生成编号
		bean.setFeeid(keyID);
		// 插入数据
		getDao().insert((T) bean);
	}

	/**
	 * 修改矿业权资金信息信息
	 * 
	 * @param bean
	 * @param actiontype
	 */
	public void update(T_bi_mrbaseinfofeeEntity bean, String actiontype) throws Exception {
		// 执行修改操作
		getDao().update((T) bean);
	}

	/**
	 * 删除矿业权资金信息信息
	 * 
	 * @param bean
	 * 
	 */
	public void delete(T_bi_mrbaseinfofeeEntity bean) {
		// 删除矿业权资金信息
		getDao().delete((T) bean);
	}
	
	public void deleteAllByRightId(Object rightId) {
		// 删除矿权费用信息
		getDao().deleteAllByRightId(rightId);
	}
	
	public String getBiztypename() {
		return "矿业权资金信息信息管理";
	}

}
