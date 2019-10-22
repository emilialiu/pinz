package com.dimine.sc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dimine.base.service.BaseService;
import com.dimine.sc.dao.T_sc_techprocessDao;
import com.dimine.sc.entity.T_sc_techprocessEntity;

/**
 * 工艺对应工序管理事务处理
 * 
 * @author dimine 2016-07-13 16:04:47
 * 
 */
@Service("t_sc_techprocessService")
public class T_sc_techprocessService<T> extends BaseService<T> {

	@Autowired
	private T_sc_techprocessDao<T> dao;

	public T_sc_techprocessDao<T> getDao() {
		return dao;
	}

	/**
	 * 添加新的工艺对应工序
	 * 
	 * @param bean
	 * @param actiontype
	 * @param user
	 */
	public void insert(T_sc_techprocessEntity bean) throws Exception {
		//String keyID = KeyUtils.createKeyID();
		// 生成编号
		//bean.setTechid(keyID);
		// 插入数据
		getDao().insert((T) bean);
	}

	/**
	 * 修改工艺对应工序信息
	 * 
	 * @param bean
	 * @param actiontype
	 */
	public void update(T_sc_techprocessEntity bean, String actiontype) throws Exception {
		// 执行修改操作
		getDao().update((T) bean);
	}

	/**
	 * 删除工艺对应工序信息
	 * 
	 * @param bean
	 * 
	 */
	public void delete(T_sc_techprocessEntity bean) {
		// 删除工艺对应工序
		getDao().delete((T) bean);
	}
	
	public String getBiztypename() {
		return "工艺对应工序信息管理";
	}

}
