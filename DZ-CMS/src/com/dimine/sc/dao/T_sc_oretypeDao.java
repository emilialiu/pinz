package com.dimine.sc.dao;

import com.dimine.base.dao.BaseDao;
import com.dimine.base.dao.BizDao;
import com.dimine.sc.entity.T_sc_oretypeEntity;

/**
 * 矿种表 Mapper
 * 
 * @author dimine 2017-09-05 10:02:05
 * 
 */
@BizDao
public interface T_sc_oretypeDao<T> extends BaseDao<T> {
	/**
	 * 检查矿种是否重复
	 * @param bean
	 * @return
	 */
	public Integer checkOretype(T_sc_oretypeEntity bean);
}
