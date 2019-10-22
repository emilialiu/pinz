package com.dimine.bi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dimine.base.service.BaseService;
import com.dimine.base.util.KeyUtils;
import com.dimine.bi.dao.T_bi_threeoreDao;
import com.dimine.bi.entity.T_bi_threeoreEntity;

/**
 * 三级矿量信息表管理事务处理
 * 
 * @author dimine 2017-08-15 13:43:48
 * 
 */
@Service("t_bi_threeoreService")
public class T_bi_threeoreService<T> extends BaseService<T> {

	@Autowired
	private T_bi_threeoreDao<T> dao;

	public T_bi_threeoreDao<T> getDao() {
		return dao;
	}

	public List<T_bi_threeoreEntity> selectByYearValue(T_bi_threeoreEntity bean) {
		return getDao().selectByYearValue(bean);
	}
	
	/**
	 * 添加新的三级矿量信息表
	 * 
	 * @param bean
	 * @param actiontype
	 * @param user
	 */
	public void insert(T_bi_threeoreEntity bean) throws Exception {
		String keyID = KeyUtils.createKeyID();
		// 生成编号
		bean.setThreeoreid(keyID);
		// 插入数据
		getDao().insert((T) bean);
	}

	/**
	 * 修改三级矿量信息表信息
	 * 
	 * @param bean
	 * @param actiontype
	 */
	public void update(T_bi_threeoreEntity bean, String actiontype) throws Exception {
		// 执行修改操作
		getDao().update((T) bean);
	}

	/**
	 * 删除三级矿量信息表信息
	 * 
	 * @param bean
	 * 
	 */
	public void delete(T_bi_threeoreEntity bean) {
		// 删除三级矿量信息表
		getDao().delete((T) bean);
	}
	
	public String getBiztypename() {
		return "三级矿量信息表信息管理";
	}

}
