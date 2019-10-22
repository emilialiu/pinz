package com.dimine.dz.service;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dimine.base.common.ValidException;
import com.dimine.base.service.BaseService;
import com.dimine.base.util.KeyUtils;
import com.dimine.dz.dao.Dz_memberDao;
import com.dimine.dz.entity.Dz_memberEntity;

/**
 * 客户等级管理管理事务处理
 * 
 * @author dimine 2019-08-29 19:13:18
 * 
 */
@Service("dz_memberService")
public class Dz_memberService<T> extends BaseService<T> {

	@Autowired
	private Dz_memberDao<T> dao;

	public Dz_memberDao<T> getDao() {
		return dao;
	}

	/**
	 * 添加新的客户等级管理
	 * 
	 * @param bean
	 * @param actiontype
	 * @param user
	 */
	public void insert(Dz_memberEntity bean) throws Exception {
		String keyID = KeyUtils.createKeyID();
		// 生成编号
		bean.setId(keyID);
		// 插入数据
		getDao().insert((T) bean);
	}

	/**
	 * 修改客户等级管理信息
	 * 
	 * @param bean
	 * @param actiontype
	 */
	public void update(Dz_memberEntity bean, String actiontype) throws Exception {
		// 执行修改操作
		getDao().update((T) bean);
	}

	/**
	 * 删除客户等级管理信息
	 * 
	 * @param bean
	 * 
	 */
	public void delete(Dz_memberEntity bean) {
		// 删除客户等级管理
		getDao().delete((T) bean);
	}
	
	public String getBiztypename() {
		return "客户等级管理信息管理";
	}

}
