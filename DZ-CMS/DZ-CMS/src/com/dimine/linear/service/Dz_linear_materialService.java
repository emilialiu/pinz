package com.dimine.linear.service;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dimine.base.common.ValidException;
import com.dimine.base.service.BaseService;
import com.dimine.base.util.KeyUtils;
import com.dimine.linear.dao.Dz_linear_materialDao;
import com.dimine.linear.entity.Dz_linear_materialEntity;

/**
 * 线性材质管理管理事务处理
 * 
 * @author dimine 2019-08-29 20:09:14
 * 
 */
@Service("dz_linear_materialService")
public class Dz_linear_materialService<T> extends BaseService<T> {

	@Autowired
	private Dz_linear_materialDao<T> dao;

	public Dz_linear_materialDao<T> getDao() {
		return dao;
	}

	/**
	 * 添加新的线性材质管理
	 * 
	 * @param bean
	 * @param actiontype
	 * @param user
	 */
	public void insert(Dz_linear_materialEntity bean) throws Exception {
		String keyID = KeyUtils.createKeyID();
		// 生成编号
		bean.setId(keyID);
		// 插入数据
		getDao().insert((T) bean);
	}

	/**
	 * 修改线性材质管理信息
	 * 
	 * @param bean
	 * @param actiontype
	 */
	public void update(Dz_linear_materialEntity bean, String actiontype) throws Exception {
		// 执行修改操作
		getDao().update((T) bean);
	}

	/**
	 * 删除线性材质管理信息
	 * 
	 * @param bean
	 * 
	 */
	public void delete(Dz_linear_materialEntity bean) {
		// 删除线性材质管理
		getDao().delete((T) bean);
	}
	
	public String getBiztypename() {
		return "线性材质管理信息管理";
	}

}
