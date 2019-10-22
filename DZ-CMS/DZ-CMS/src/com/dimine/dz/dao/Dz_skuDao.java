package com.dimine.dz.dao;

import java.util.List;

import com.dimine.base.dao.BaseDao;
import com.dimine.base.dao.BizDao;
import com.dimine.dz.entity.Dz_skuEntity;

/**
 * 规格配置表 Mapper
 * 
 * @author dimine 2019-09-05 18:58:10
 * 
 */
@BizDao
public interface Dz_skuDao<T> extends BaseDao<T> {
	public List<Dz_skuEntity> selectByList2(Dz_skuEntity bean);
	public List<Dz_skuEntity> findsku(Dz_skuEntity bean);
	public List<Dz_skuEntity> findskusum(Dz_skuEntity bean);
	public Integer countjson(String id);
	public String findjson(String id);
}
