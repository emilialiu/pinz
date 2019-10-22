package com.dimine.customer.service;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dimine.base.common.ValidException;
import com.dimine.base.service.BaseService;
import com.dimine.base.util.KeyUtils;
import com.dimine.customer.dao.Dz_customer_member_recordDao;
import com.dimine.customer.entity.Dz_customer_member_recordEntity;

/**
 * 客户等级升级表管理事务处理
 * 
 * @author dimine 2019-09-01 15:29:27
 * 
 */
@Service("dz_customer_member_recordService")
public class Dz_customer_member_recordService<T> extends BaseService<T> {

	@Autowired
	private Dz_customer_member_recordDao<T> dao;

	public Dz_customer_member_recordDao<T> getDao() {
		return dao;
	}

	/**
	 * 添加新的客户等级升级表
	 * 
	 * @param bean
	 * @param actiontype
	 * @param user
	 */
	public void insert(Dz_customer_member_recordEntity bean) throws Exception {
		String keyID = KeyUtils.createKeyID();
		// 生成编号
		bean.setId(keyID);
		// 插入数据
		getDao().insert((T) bean);
	}

	/**
	 * 修改客户等级升级表信息
	 * 
	 * @param bean
	 * @param actiontype
	 */
	public void update(Dz_customer_member_recordEntity bean, String actiontype) throws Exception {
		// 执行修改操作
		getDao().update((T) bean);
	}

	/**
	 * 删除客户等级升级表信息
	 * 
	 * @param bean
	 * 
	 */
	public void delete(Dz_customer_member_recordEntity bean) {
		// 删除客户等级升级表
		getDao().delete((T) bean);
	}
	
	public String getBiztypename() {
		return "客户等级升级表信息管理";
	}

}
