package com.dimine.sc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dimine.base.service.BaseService;
import com.dimine.base.util.KeyUtils;
import com.dimine.sc.dao.T_sc_oretypeDao;
import com.dimine.sc.entity.T_sc_oretypeEntity;

/**
 * 矿种表管理事务处理
 * 
 * @author dimine 2017-09-05 10:02:05
 * 
 */
@Service("t_sc_oretypeService")
public class T_sc_oretypeService<T> extends BaseService<T> {

	@Autowired
	private T_sc_oretypeDao<T> dao;

	public T_sc_oretypeDao<T> getDao() {
		return dao;
	}

	/**
	 * 添加新的矿种表
	 * 
	 * @param bean
	 * @param actiontype
	 * @param user
	 */
	public void insert(T_sc_oretypeEntity bean) throws Exception {
		String keyID = KeyUtils.createKeyID();
		// 生成编号
		bean.setOretypeid(keyID);
		// 插入数据
		getDao().insert((T) bean);
	}

	/**
	 * 修改矿种表信息
	 * 
	 * @param bean
	 * @param actiontype
	 */
	public void update(T_sc_oretypeEntity bean, String actiontype) throws Exception {
		// 执行修改操作
		getDao().update((T) bean);
	}

	/**
	 * 删除矿种表信息
	 * 
	 * @param bean
	 * 
	 */
	public void delete(T_sc_oretypeEntity bean) {
		// 删除矿种表
		getDao().delete((T) bean);
	}
	/**
	 * 检查矿种是否重复
	 * @param bean
	 * @return
	 */
	public Integer checkOretype(T_sc_oretypeEntity bean){
		return getDao().checkOretype(bean);
	}
	public String getBiztypename() {
		return "矿种表信息管理";
	}

}
