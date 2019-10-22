package com.dimine.sc.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dimine.base.service.BaseService;
import com.dimine.sc.dao.T_sc_weightparameterDao;
import com.dimine.sc.entity.T_sc_weightparameterEntity;

/**
 * 业务参数配置管理事务处理
 * 
 * @author dimine 2016-07-04 09:45:39
 * 
 */
@Service("t_sc_weightparameterService")
public class T_sc_weightparameterService<T> extends BaseService<T> {

	@Autowired
	private T_sc_weightparameterDao<T> dao;

	public T_sc_weightparameterDao<T> getDao() {
		return dao;
	}

	/**
	 * 添加新的业务参数配置
	 * 
	 * @param bean
	 * @param actiontype
	 * @param user
	 */
	public void insert(T_sc_weightparameterEntity bean) throws Exception {
		/*String keyID = KeyUtils.createKeyID();
		// 生成编号
		bean.setParaid(keyID);*/
		// 插入数据
		getDao().insert((T) bean);
	}

	/**
	 * 修改业务参数配置信息
	 * 
	 * @param bean
	 * @param actiontype
	 */
	public void update(T_sc_weightparameterEntity bean, String actiontype) throws Exception {
		// 执行修改操作
		getDao().update((T) bean);
	}

	/**
	 * 删除业务参数配置信息
	 * 
	 * @param bean
	 * 
	 */
	public void delete(T_sc_weightparameterEntity bean) {
		// 删除业务参数配置
		getDao().delete((T) bean);
	}
	public List<T_sc_weightparameterEntity> selectList(T_sc_weightparameterEntity entity){
		return getDao().selectList(entity);
		
	}
	
	public String getBiztypename() {
		return "业务参数配置信息管理";
	}

}
