package com.dimine.sys.dao;

import com.dimine.base.dao.BaseDao;
import com.dimine.base.dao.BizDao;

/**
 * 系统参数表 Mapper
 * 
 * @author dimine 2014-12-18 12:01:02
 * 
 */
@BizDao
public interface Sys_paramDao<T> extends BaseDao<T> {

	String getValueByCode(String paramCode);
	
}
