package com.dimine.commodity.dao;

import com.dimine.base.dao.BaseDao;
import com.dimine.base.dao.BizDao;
import com.dimine.commodity.entity.Dz_commodity_baseEntity;

/**
 * 商品基础表 Mapper
 * 
 * @author dimine 2019-08-29 20:15:45
 * 
 */
@BizDao
public interface Dz_commodity_baseDao<T> extends BaseDao<T> {
	public void insertjson(Dz_commodity_baseEntity bean);
	public void updatejson(Dz_commodity_baseEntity bean);
}
