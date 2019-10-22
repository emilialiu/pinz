package com.dimine.sys.dao;

import com.dimine.base.dao.BaseDao;
import com.dimine.base.dao.BizDao;
@BizDao
public interface LogDispatchDao<T> extends BaseDao<T> {
	/**
	 * 删除所有的操作日志记录
	 */
	public void deleteAll();
}
