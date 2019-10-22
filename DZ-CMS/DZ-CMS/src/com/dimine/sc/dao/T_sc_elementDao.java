package com.dimine.sc.dao;

import com.dimine.base.dao.BaseDao;
import com.dimine.base.dao.BizDao;
import com.dimine.sc.entity.T_sc_elementEntity;

/**
 * 元素表 Mapper
 * 
 * @author dimine 2017-09-05 09:59:00
 * 
 */
@BizDao
public interface T_sc_elementDao<T> extends BaseDao<T> {
	/**
	 * 检查元素是否重复
	 * @param bean
	 * @return
	 */
	public Integer checkElement(T_sc_elementEntity bean);
}
