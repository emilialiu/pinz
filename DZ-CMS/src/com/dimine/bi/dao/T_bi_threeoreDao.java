package com.dimine.bi.dao;

import java.util.List;

import com.dimine.base.dao.BaseDao;
import com.dimine.base.dao.BizDao;
import com.dimine.bi.entity.T_bi_threeoreEntity;

/**
 * 三级矿量信息表 Mapper
 * 
 * @author dimine 2017-08-15 13:43:48
 * 
 */
@BizDao
public interface T_bi_threeoreDao<T> extends BaseDao<T> {
	
	public abstract List<T_bi_threeoreEntity> selectByYearValue(T_bi_threeoreEntity bean);
}
