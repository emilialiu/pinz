package com.dimine.sys.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dimine.base.common.ValidException;
import com.dimine.base.service.BaseService;
import com.dimine.base.util.KeyUtils;
import com.dimine.sys.dao.Sys_modelmappDao;
import com.dimine.sys.entity.ColumnEntity;
import com.dimine.sys.entity.DictEntity;
import com.dimine.sys.entity.Sys_modelmappEntity;

/**
 * EXCEL导入属性对应关系表管理事务处理
 * 
 * @author dimine 2014-12-18 15:53:05
 * 
 */
@Service("sys_modelmappService")
public class Sys_modelmappService<T> extends BaseService<T> {

	@Autowired
	private Sys_modelmappDao<T> dao;

	public Sys_modelmappDao<T> getDao() {
		return dao;
	}

	/**
	 * 添加新的EXCEL导入属性对应关系表
	 * 
	 * @param bean
	 * @param actiontype
	 * @param user
	 */
	public void insert(Sys_modelmappEntity bean) throws Exception {
		String keyID = KeyUtils.createKeyID();
		// 生成编号
		bean.setMid(keyID);
		// 插入数据
		getDao().insert((T) bean);
	}

	/**
	 * 修改EXCEL导入属性对应关系表信息
	 * 
	 * @param bean
	 * @param actiontype
	 */
	public void update(Sys_modelmappEntity bean, String actiontype) throws Exception {
		// 执行修改操作
		getDao().update((T) bean);
	}

	/**
	 * 删除EXCEL导入属性对应关系表信息
	 * 
	 * @param bean
	 * 
	 */
	public void delete(Sys_modelmappEntity bean) {
		// 删除EXCEL导入属性对应关系表
		getDao().delete((T) bean);
	}
	
	/**
	 * 获取表中所有字段信息集合
	 * 
	 * @param bean
	 * 
	 */
	public List<ColumnEntity> getFieldObjects(String param) {
		// 删除EXCEL导入属性对应关系表
		return getDao().getFieldObjects(param);
	}
	
	/**
	 * 获取字典资源类别信息集合
	 * 
	 * @param bean
	 * 
	 */
	public List<ColumnEntity> getDicttypeObjects() {
		// 删除EXCEL导入属性对应关系表
		return getDao().getDicttypeObjects();
	}
	
	/**
	 * 查询无翻页列表信息
	 * 
	 * @param bean
	 * 
	 */
	public List<Sys_modelmappEntity> selectByAll(Sys_modelmappEntity model) {
		// 查询无翻页列表信息
		return getDao().selectByAll(model);
	}
	
	/**
	 * 根据字典类别ID、字典资源名称获取字典资源ID
	 * 
	 * @param bean
	 * 
	 */
	public String selectDictID(DictEntity dict) {
		// 根据字典类别ID、字典资源名称获取字典资源ID
		return getDao().selectDictID(dict);
	}
	
	public String getBiztypename() {
		return "EXCEL导入属性对应关系表信息管理";
	}

}
