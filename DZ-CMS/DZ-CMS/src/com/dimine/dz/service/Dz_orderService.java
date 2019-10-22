package com.dimine.dz.service;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dimine.base.common.ValidException;
import com.dimine.base.service.BaseService;
import com.dimine.base.util.KeyUtils;
import com.dimine.dz.dao.Dz_orderDao;
import com.dimine.dz.entity.Dz_orderEntity;

/**
 * 订单管理管理事务处理
 * 
 * @author dimine 2019-08-29 19:52:32
 * 
 */
@Service("dz_orderService")
public class Dz_orderService<T> extends BaseService<T> {

	@Autowired
	private Dz_orderDao<T> dao;

	public Dz_orderDao<T> getDao() {
		return dao;
	}

	/**
	 * 添加新的订单管理
	 * 
	 * @param bean
	 * @param actiontype
	 * @param user
	 */
	public void insert(Dz_orderEntity bean) throws Exception {
		String keyID = KeyUtils.createKeyID();
		// 生成编号
		bean.setId(keyID);
		// 插入数据
		getDao().insert((T) bean);
	}

	/**
	 * 修改订单管理信息
	 * 
	 * @param bean
	 * @param actiontype
	 */
	public void update(Dz_orderEntity bean, String actiontype) throws Exception {
		// 执行修改操作
		getDao().update((T) bean);
	}

	/**
	 * 删除订单管理信息
	 * 
	 * @param bean
	 * 
	 */
	public void delete(Dz_orderEntity bean) {
		// 删除订单管理
		getDao().delete((T) bean);
	}
	
	public String getBiztypename() {
		return "订单管理信息管理";
	}

}
