package com.dimine.order.service;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dimine.base.common.ValidException;
import com.dimine.base.service.BaseService;
import com.dimine.base.util.KeyUtils;
import com.dimine.order.dao.Dz_order_detailDao;
import com.dimine.order.entity.Dz_order_detailEntity;

/**
 * 商品明细管理事务处理
 * 
 * @author dimine 2019-09-03 19:10:58
 * 
 */
@Service("dz_order_detailService")
public class Dz_order_detailService<T> extends BaseService<T> {

	@Autowired
	private Dz_order_detailDao<T> dao;

	public Dz_order_detailDao<T> getDao() {
		return dao;
	}

	/**
	 * 添加新的商品明细
	 * 
	 * @param bean
	 * @param actiontype
	 * @param user
	 */
	public void insert(Dz_order_detailEntity bean) throws Exception {
		String keyID = KeyUtils.createKeyID();
		// 生成编号
		bean.setId(keyID);
		// 插入数据
		getDao().insert((T) bean);
	}

	/**
	 * 修改商品明细信息
	 * 
	 * @param bean
	 * @param actiontype
	 */
	public void update(Dz_order_detailEntity bean, String actiontype) throws Exception {
		// 执行修改操作
		getDao().update((T) bean);
	}

	/**
	 * 删除商品明细信息
	 * 
	 * @param bean
	 * 
	 */
	public void delete(Dz_order_detailEntity bean) {
		// 删除商品明细
		getDao().delete((T) bean);
	}
	
	public String getBiztypename() {
		return "商品明细信息管理";
	}

}
