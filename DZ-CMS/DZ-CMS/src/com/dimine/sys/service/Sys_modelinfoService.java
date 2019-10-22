package com.dimine.sys.service;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dimine.base.common.ValidException;
import com.dimine.base.service.BaseService;
import com.dimine.base.util.KeyUtils;
import com.dimine.sys.dao.Sys_modelinfoDao;
import com.dimine.sys.entity.Sys_modelinfoEntity;

/**
 * EXCEL导入模板信息表管理事务处理
 * 
 * @author dimine 2014-12-18 15:45:29
 * 
 */
@Service("sys_modelinfoService")
public class Sys_modelinfoService<T> extends BaseService<T> {

	@Autowired
	private Sys_modelinfoDao<T> dao;

	public Sys_modelinfoDao<T> getDao() {
		return dao;
	}

	/**
	 * 添加新的EXCEL导入模板信息表
	 * 
	 * @param bean
	 * @param actiontype
	 * @param user
	 */
	public void insert(Sys_modelinfoEntity bean) throws Exception {		
		// 插入数据
		getDao().insert((T) bean);
	}

	/**
	 * 修改EXCEL导入模板信息表信息
	 * 
	 * @param bean
	 * @param actiontype
	 */
	public void update(Sys_modelinfoEntity bean, String actiontype) throws Exception {
		// 执行修改操作
		getDao().update((T) bean);
	}

	/**
	 * 删除EXCEL导入模板信息表信息
	 * 
	 * @param bean
	 * 
	 */
	public void delete(Sys_modelinfoEntity bean) {
		// 删除EXCEL导入模板信息表
		getDao().delete((T) bean);
	}
	
	/**
	 * 查询模版信息无翻页列表
	 * 
	 * @param bean
	 * 
	 */
	public List<Sys_modelinfoEntity> selectModelinfoList(Sys_modelinfoEntity bean) {
		// 查询模版信息无翻页列表
		return getDao().selectModelinfoList(bean);
	}
	
	public String getBiztypename() {
		return "EXCEL导入模板信息表信息管理";
	}

}
