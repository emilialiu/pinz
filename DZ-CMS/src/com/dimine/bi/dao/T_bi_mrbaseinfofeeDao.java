package com.dimine.bi.dao;

import com.dimine.base.dao.BaseDao;
import com.dimine.base.dao.BizDao;

/**
 * 矿业权资金信息 Mapper
 * 
 * @author dimine 2017-08-25 18:36:53
 * 
 */
@BizDao
public interface T_bi_mrbaseinfofeeDao<T> extends BaseDao<T> {
	public abstract void deleteAllByRightId(Object rightId);
	
}
