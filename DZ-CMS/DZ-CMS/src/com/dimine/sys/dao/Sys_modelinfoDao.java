package com.dimine.sys.dao;

import java.util.List;
import java.util.Map;

import com.dimine.base.dao.BaseDao;
import com.dimine.base.dao.BizDao;
import com.dimine.sys.entity.Sys_modelinfoEntity;

/**
 * EXCEL导入模板信息表 Mapper
 * 
 * @author dimine 2014-12-18 15:45:29
 * 
 */
@BizDao
public interface Sys_modelinfoDao<T> extends BaseDao<T> {
	/**
	 * 查询模版信息无翻页列表
	 * 
	 * @param bean
	 * 
	 */
	public List<Sys_modelinfoEntity> selectModelinfoList(Sys_modelinfoEntity bean);
	
}
