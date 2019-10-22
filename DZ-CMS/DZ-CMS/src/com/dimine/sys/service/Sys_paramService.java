package com.dimine.sys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dimine.base.service.BaseService;
import com.dimine.base.util.KeyUtils;
import com.dimine.sys.dao.Sys_paramDao;
import com.dimine.sys.entity.Sys_paramEntity;

/**
 * 系统参数表管理事务处理
 * 
 * @author dimine 2014-12-18 12:01:02
 * 
 */
@Service("sys_paramService")
public class Sys_paramService<T> extends BaseService<T> {

	@Autowired
	private Sys_paramDao<T> dao;

	public Sys_paramDao<T> getDao() {
		return dao;
	}

	/**
	 * 添加新的系统参数表
	 * 
	 * @param bean
	 * @param actiontype
	 * @param user
	 */
	public void insert(Sys_paramEntity bean) throws Exception {
		String keyID = KeyUtils.createKeyID();
		// 生成编号
		bean.setParamcode(keyID);
		// 插入数据
		getDao().insert((T) bean);
	}

	/**
	 * 修改系统参数表信息
	 * 
	 * @param bean
	 * @param actiontype
	 */
	public void update(Sys_paramEntity bean, String actiontype) throws Exception {
		// 执行修改操作
		getDao().update((T) bean);
	}

	/**
	 * 删除系统参数表信息
	 * 
	 * @param bean
	 * 
	 */
	public void delete(Sys_paramEntity bean) {
		// 删除系统参数表
		getDao().delete((T) bean);
	}
	
	public String getBiztypename() {
		return "系统参数表信息管理";
	}

	public String getValueByCode(String paramCode) {
		return getDao().getValueByCode(paramCode);
	}

}
