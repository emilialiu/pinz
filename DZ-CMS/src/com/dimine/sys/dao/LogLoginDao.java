package com.dimine.sys.dao;

import com.dimine.base.dao.BaseDao;
import com.dimine.base.dao.BizDao;
@BizDao
public interface LogLoginDao<T> extends BaseDao<T>{
	/**
	 * 删除所有的日志
	 */
	public void deleteall();

}
