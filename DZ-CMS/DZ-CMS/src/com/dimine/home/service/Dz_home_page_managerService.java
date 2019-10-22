package com.dimine.home.service;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dimine.base.common.ValidException;
import com.dimine.base.service.BaseService;
import com.dimine.base.util.KeyUtils;
import com.dimine.home.dao.Dz_home_page_managerDao;
import com.dimine.home.entity.Dz_home_page_managerEntity;

/**
 * 首页banner管理管理事务处理
 * 
 * @author dimine 2019-08-29 20:03:20
 * 
 */
@Service("dz_home_page_managerService")
public class Dz_home_page_managerService<T> extends BaseService<T> {

	@Autowired
	private Dz_home_page_managerDao<T> dao;

	public Dz_home_page_managerDao<T> getDao() {
		return dao;
	}

	/**
	 * 添加新的首页banner管理
	 * 
	 * @param bean
	 * @param actiontype
	 * @param user
	 */
	public void insert(Dz_home_page_managerEntity bean) throws Exception {
		String keyID = KeyUtils.createKeyID();
		// 生成编号
		bean.setId(keyID);
		// 插入数据
		getDao().insert((T) bean);
	}

	/**
	 * 修改首页banner管理信息
	 * 
	 * @param bean
	 * @param actiontype
	 */
	public void update(Dz_home_page_managerEntity bean, String actiontype) throws Exception {
		// 执行修改操作
		getDao().update((T) bean);
	}

	/**
	 * 删除首页banner管理信息
	 * 
	 * @param bean
	 * 
	 */
	public void delete(Dz_home_page_managerEntity bean) {
		// 删除首页banner管理
		getDao().delete((T) bean);
	}
	
	public String getBiztypename() {
		return "首页banner管理信息管理";
	}

}
