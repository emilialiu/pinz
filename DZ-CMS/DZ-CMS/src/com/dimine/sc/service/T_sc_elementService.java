package com.dimine.sc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dimine.base.service.BaseService;
import com.dimine.base.util.KeyUtils;
import com.dimine.sc.dao.T_sc_elementDao;
import com.dimine.sc.entity.T_sc_elementEntity;

/**
 * 元素表管理事务处理
 * 
 * @author dimine 2017-09-05 09:59:00
 * 
 */
@Service("t_sc_elementService")
public class T_sc_elementService<T> extends BaseService<T> {

	@Autowired
	private T_sc_elementDao<T> dao;

	public T_sc_elementDao<T> getDao() {
		return dao;
	}

	/**
	 * 添加新的元素表
	 * 
	 * @param bean
	 * @param actiontype
	 * @param user
	 */
	public void insert(T_sc_elementEntity bean) throws Exception {
		String keyID = KeyUtils.createKeyID();
		// 生成编号
		bean.setElementid(keyID);
		// 插入数据
		getDao().insert((T) bean);
	}

	/**
	 * 修改元素表信息
	 * 
	 * @param bean
	 * @param actiontype
	 */
	public void update(T_sc_elementEntity bean, String actiontype) throws Exception {
		// 执行修改操作
		getDao().update((T) bean);
	}

	/**
	 * 删除元素表信息
	 * 
	 * @param bean
	 * 
	 */
	public void delete(T_sc_elementEntity bean) {
		// 删除元素表
		getDao().delete((T) bean);
	}
	/**
	 * 检查元素是否重复
	 * @param bean
	 * @return
	 */
	public Integer checkElement(T_sc_elementEntity bean){
		return getDao().checkElement(bean);
	}
	
	public String getBiztypename() {
		return "元素表信息管理";
	}

}
