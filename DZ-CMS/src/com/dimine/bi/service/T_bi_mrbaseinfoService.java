package com.dimine.bi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dimine.base.service.BaseService;
import com.dimine.base.util.KeyUtils;
import com.dimine.bi.dao.T_bi_mrbaseinfoDao;
import com.dimine.bi.entity.T_bi_mrbaseinfoEntity;

/**
 * 矿业权基本信息表管理事务处理
 * 
 * @author dimine 2017-08-15 10:34:46
 * 
 */
@Service("t_bi_mrbaseinfoService")
public class T_bi_mrbaseinfoService<T> extends BaseService<T> {

	@Autowired
	private T_bi_mrbaseinfoDao<T> dao;

	public T_bi_mrbaseinfoDao<T> getDao() {
		return dao;
	}

	/**
	 * 添加新的矿业权基本信息表
	 * 
	 * @param bean
	 * @param actiontype
	 * @param user
	 */
	public void insert(T_bi_mrbaseinfoEntity bean) throws Exception {
		String keyID = KeyUtils.createKeyID();
		// 生成编号
		bean.setRightid(keyID);
		// 插入数据
		getDao().insert((T) bean);
	}

	/**
	 * 修改矿业权基本信息表信息
	 * 
	 * @param bean
	 * @param actiontype
	 */
	public void update(T_bi_mrbaseinfoEntity bean, String actiontype)
			throws Exception {
		// 执行修改操作
		getDao().update((T) bean);
	}

	/**
	 * 删除矿业权基本信息表信息
	 * 
	 * @param bean
	 * 
	 */
	public void delete(T_bi_mrbaseinfoEntity bean) {
		// 删除矿业权基本信息表
		getDao().delete((T) bean);
	}

	public String getBiztypename() {
		return "矿业权基本信息表信息管理";
	}

}
