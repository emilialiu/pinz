package com.dimine.dz.service;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dimine.base.common.ValidException;
import com.dimine.base.service.BaseService;
import com.dimine.base.util.KeyUtils;
import com.dimine.dz.dao.Dz_customerDao;
import com.dimine.dz.entity.Dz_customerEntity;

/**
 * 会员信息管理事务处理
 * 
 * @author dimine 2019-08-29 19:07:02
 * 
 */
@Service("dz_customerService")
public class Dz_customerService<T> extends BaseService<T> {

	@Autowired
	private Dz_customerDao<T> dao;

	public Dz_customerDao<T> getDao() {
		return dao;
	}

	/**
	 * 添加新的会员信息
	 * 
	 * @param bean
	 * @param actiontype
	 * @param user
	 */
	public void insert(Dz_customerEntity bean) throws Exception {
		String keyID = KeyUtils.createKeyID();
		// 生成编号
		bean.setUid(keyID);
		// 插入数据
		getDao().insert((T) bean);
	}

	/**
	 * 修改会员信息信息
	 * 
	 * @param bean
	 * @param actiontype
	 */
	public void update(Dz_customerEntity bean, String actiontype) throws Exception {
		// 执行修改操作
		getDao().update((T) bean);
	}

	/**
	 * 删除会员信息信息
	 * 
	 * @param bean
	 * 
	 */
	public void delete(Dz_customerEntity bean) {
		// 删除会员信息
		getDao().delete((T) bean);
	}
	
	public String getBiztypename() {
		return "会员信息信息管理";
	}

}
