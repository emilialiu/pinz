package com.dimine.commodity.service;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dimine.base.common.ValidException;
import com.dimine.base.service.BaseService;
import com.dimine.base.util.KeyUtils;
import com.dimine.commodity.dao.Dz_commodity_reviewDao;
import com.dimine.commodity.entity.Dz_commodity_reviewEntity;

/**
 * 商品评价表管理事务处理
 * 
 * @author dimine 2019-08-29 19:55:44
 * 
 */
@Service("dz_commodity_reviewService")
public class Dz_commodity_reviewService<T> extends BaseService<T> {

	@Autowired
	private Dz_commodity_reviewDao<T> dao;

	public Dz_commodity_reviewDao<T> getDao() {
		return dao;
	}

	/**
	 * 添加新的商品评价表
	 * 
	 * @param bean
	 * @param actiontype
	 * @param user
	 */
	public void insert(Dz_commodity_reviewEntity bean) throws Exception {
		String keyID = KeyUtils.createKeyID();
		// 生成编号
		bean.setId(keyID);
		// 插入数据
		getDao().insert((T) bean);
	}

	/**
	 * 修改商品评价表信息
	 * 
	 * @param bean
	 * @param actiontype
	 */
	public void update(Dz_commodity_reviewEntity bean, String actiontype) throws Exception {
		// 执行修改操作
		getDao().update((T) bean);
	}

	/**
	 * 删除商品评价表信息
	 * 
	 * @param bean
	 * 
	 */
	public void delete(Dz_commodity_reviewEntity bean) {
		// 删除商品评价表
		getDao().delete((T) bean);
	}
	
	public String getBiztypename() {
		return "商品评价表信息管理";
	}

}
