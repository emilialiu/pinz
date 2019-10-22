package com.dimine.sc.dao;

import java.util.List;

import com.dimine.base.dao.BaseDao;
import com.dimine.base.dao.BizDao;
import com.dimine.sc.entity.T_sc_weightparameterEntity;

/**
 * 业务参数配置 Mapper
 * 
 * @author dimine 2016-07-04 09:45:39
 * 
 */
@BizDao
public interface T_sc_weightparameterDao<T> extends BaseDao<T> {
	List<T_sc_weightparameterEntity> selectList(T_sc_weightparameterEntity entity);
}
