package com.dimine.sc.dao;

import java.util.List;

import com.dimine.base.dao.BaseDao;
import com.dimine.base.dao.BizDao;
import com.dimine.sc.entity.T_sc_devtechprocessEntity;

/**
 * 工序对应设备 Mapper
 * 
 * @author dimine 2016-08-17 11:06:21
 * 
 */
@BizDao
public interface T_sc_devtechprocessDao<T> extends BaseDao<T> {
	List<T_sc_devtechprocessEntity> selectDeviceByList(T_sc_devtechprocessEntity bean)throws Exception ;
	public Integer selectDeviceByCount(T_sc_devtechprocessEntity bean)throws Exception ;
}
