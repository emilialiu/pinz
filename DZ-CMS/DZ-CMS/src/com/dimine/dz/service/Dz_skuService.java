package com.dimine.dz.service;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dimine.base.common.ValidException;
import com.dimine.base.service.BaseService;
import com.dimine.base.util.KeyUtils;
import com.dimine.dz.dao.Dz_skuDao;
import com.dimine.dz.entity.Dz_skuEntity;

/**
 * 规格配置表管理事务处理
 * 
 * @author dimine 2019-09-05 18:58:10
 * 
 */
@Service("dz_skuService")
public class Dz_skuService<T> extends BaseService<T> {

	@Autowired
	private Dz_skuDao<T> dao;

	public Dz_skuDao<T> getDao() {
		return dao;
	}

	/**
	 * 添加新的规格配置表
	 * 
	 * @param bean
	 * @param actiontype
	 * @param user
	 */
	public void insert(Dz_skuEntity bean) throws Exception {
		// 生成编号
		// 插入数据
		getDao().insert((T) bean);
	}

	/**
	 * 修改规格配置表信息
	 * 
	 * @param bean
	 * @param actiontype
	 */
	public void update(Dz_skuEntity bean, String actiontype) throws Exception {
		// 执行修改操作
		getDao().update((T) bean);
	}

	/**
	 * 删除规格配置表信息
	 * 
	 * @param bean
	 * 
	 */
	public void delete(Dz_skuEntity bean) {
		// 删除规格配置表
		getDao().delete((T) bean);
	}
	
	public String getBiztypename() {
		return "规格配置表信息管理";
	}
	public List<Dz_skuEntity> selectByList2(Dz_skuEntity bean){
		return getDao().selectByList2(bean);
	}
	public List<Dz_skuEntity> findsku(Dz_skuEntity bean){
		return getDao().findsku(bean);
	}
	public List<Dz_skuEntity> findskusum(Dz_skuEntity bean){
		return getDao().findskusum(bean);				
	}
	public Integer countjson(String id){
		return getDao().countjson(id);
	}
	public String findjson(String id){
		return getDao().findjson(id);
	}
}
