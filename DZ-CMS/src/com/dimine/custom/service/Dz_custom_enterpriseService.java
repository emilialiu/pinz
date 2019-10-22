package com.dimine.custom.service;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dimine.base.common.ValidException;
import com.dimine.base.service.BaseService;
import com.dimine.base.util.KeyUtils;
import com.dimine.custom.dao.Dz_custom_enterpriseDao;
import com.dimine.custom.entity.Dz_custom_enterpriseEntity;

/**
 * 企业定制管理管理事务处理
 * 
 * @author dimine 2019-08-29 19:57:52
 * 
 */
@Service("dz_custom_enterpriseService")
public class Dz_custom_enterpriseService<T> extends BaseService<T> {

	@Autowired
	private Dz_custom_enterpriseDao<T> dao;

	public Dz_custom_enterpriseDao<T> getDao() {
		return dao;
	}

	/**
	 * 添加新的企业定制管理
	 * 
	 * @param bean
	 * @param actiontype
	 * @param user
	 */
	public void insert(Dz_custom_enterpriseEntity bean) throws Exception {
		String keyID = KeyUtils.createKeyID();
		// 生成编号
		bean.setId(keyID);
		// 插入数据
		getDao().insert((T) bean);
	}

	/**
	 * 修改企业定制管理信息
	 * 
	 * @param bean
	 * @param actiontype
	 */
	public void update(Dz_custom_enterpriseEntity bean, String actiontype) throws Exception {
		// 执行修改操作
		getDao().update((T) bean);
	}

	/**
	 * 删除企业定制管理信息
	 * 
	 * @param bean
	 * 
	 */
	public void delete(Dz_custom_enterpriseEntity bean) {
		// 删除企业定制管理
		getDao().delete((T) bean);
	}
	
	public String getBiztypename() {
		return "企业定制管理信息管理";
	}

}
