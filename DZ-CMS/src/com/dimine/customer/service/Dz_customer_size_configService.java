package com.dimine.customer.service;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dimine.base.common.ValidException;
import com.dimine.base.service.BaseService;
import com.dimine.base.util.KeyUtils;
import com.dimine.customer.dao.Dz_customer_size_configDao;
import com.dimine.customer.entity.Dz_customer_size_configEntity;

/**
 * 客户尺寸管理事务处理
 * 
 * @author dimine 2019-09-01 15:10:45
 * 
 */
@Service("dz_customer_size_configService")
public class Dz_customer_size_configService<T> extends BaseService<T> {

	@Autowired
	private Dz_customer_size_configDao<T> dao;

	public Dz_customer_size_configDao<T> getDao() {
		return dao;
	}

	/**
	 * 添加新的客户尺寸
	 * 
	 * @param bean
	 * @param actiontype
	 * @param user
	 */
	public void insert(Dz_customer_size_configEntity bean) throws Exception {
		String keyID = KeyUtils.createKeyID();
		// 生成编号
		bean.setId(keyID);
		// 插入数据
		getDao().insert((T) bean);
	}

	/**
	 * 修改客户尺寸信息
	 * 
	 * @param bean
	 * @param actiontype
	 */
	public void update(Dz_customer_size_configEntity bean, String actiontype) throws Exception {
		// 执行修改操作
		getDao().update((T) bean);
	}

	/**
	 * 删除客户尺寸信息
	 * 
	 * @param bean
	 * 
	 */
	public void delete(Dz_customer_size_configEntity bean) {
		// 删除客户尺寸
		getDao().delete((T) bean);
	}
	
	public String getBiztypename() {
		return "客户尺寸信息管理";
	}

}
