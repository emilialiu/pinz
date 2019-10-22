package com.dimine.sys.service;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dimine.base.common.ValidException;
import com.dimine.base.service.BaseService;
import com.dimine.base.util.KeyUtils;
import com.dimine.sys.dao.Sys_modelentitymappDao;
import com.dimine.sys.entity.Sys_modelentitymappEntity;

/**
 * 模板应用表管理事务处理
 * 
 * @author dimine 2014-12-18 15:40:29
 * 
 */
@Service("sys_modelentitymappService")
public class Sys_modelentitymappService<T> extends BaseService<T> {

	@Autowired
	private Sys_modelentitymappDao<T> dao;

	public Sys_modelentitymappDao<T> getDao() {
		return dao;
	}

	/**
	 * 添加新的模板应用表
	 * 
	 * @param bean
	 * @param actiontype
	 * @param user
	 */
	public void insert(Sys_modelentitymappEntity bean) throws Exception {
		// 插入数据
		getDao().insert((T) bean);
	}

	/**
	 * 修改模板应用表信息
	 * 
	 * @param bean
	 * @param actiontype
	 */
	public void update(Sys_modelentitymappEntity bean, String actiontype) throws Exception {
		// 执行修改操作
		getDao().update((T) bean);
	}

	/**
	 * 删除模板应用表信息
	 * 
	 * @param bean
	 * 
	 */
	public void delete(Sys_modelentitymappEntity bean) {
		// 删除模板应用表
		getDao().delete((T) bean);
	}
	
	public String getBiztypename() {
		return "模板应用表信息管理";
	}

}
