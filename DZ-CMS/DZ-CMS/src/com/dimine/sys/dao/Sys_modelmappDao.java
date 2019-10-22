package com.dimine.sys.dao;

import java.util.List;
import java.util.Map;

import com.dimine.base.dao.BaseDao;
import com.dimine.base.dao.BizDao;
import com.dimine.sys.entity.ColumnEntity;
import com.dimine.sys.entity.DictEntity;
import com.dimine.sys.entity.Sys_modelmappEntity;

/**
 * EXCEL导入属性对应关系表 Mapper
 * 
 * @author dimine 2014-12-18 15:53:05
 * 
 */
@BizDao
public interface Sys_modelmappDao<T> extends BaseDao<T> {
	/**
	 * 获取表中所有字段信息集合
	 * 
	 * @param bean
	 * 
	 */
	public List<ColumnEntity> getFieldObjects(String param);
	
	/**
	 * 获取字典资源类别信息集合
	 * 
	 * @param bean
	 * 
	 */
	public List<ColumnEntity> getDicttypeObjects();
	
	/**
	 * 查询无翻页列表信息
	 * 
	 * @param bean
	 * 
	 */
	public List<Sys_modelmappEntity> selectByAll(Sys_modelmappEntity model);
	
	/**
	 * 根据字典类别ID、字典资源名称获取字典资源ID
	 * 
	 * @param bean
	 * 
	 */
	public String selectDictID(DictEntity dict);
	
}
