package com.dimine.pub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dimine.base.service.BaseService;
import com.dimine.base.util.KeyUtils;
import com.dimine.pub.dao.T_pub_tomatterDao;
import com.dimine.pub.entity.T_pub_messagevistEntity;
import com.dimine.pub.entity.T_pub_tomatterEntity;

/**
 * 待办事项管理管理事务处理
 * 
 * @author dimine 2017-11-16 15:35:42
 * 
 */
@Service("t_pub_tomatterService")
public class T_pub_tomatterService<T> extends BaseService<T> {

	@Autowired
	private T_pub_tomatterDao<T> dao;

	public T_pub_tomatterDao<T> getDao() {
		return dao;
	}

	/**
	 * 添加新的待办事项管理
	 * 
	 * @param bean
	 * @param actiontype
	 * @param user
	 */
	public void insert(T_pub_tomatterEntity bean) throws Exception {
		String keyID = KeyUtils.createKeyID();
		// 生成编号
		bean.setMatterid(keyID);
		// 插入数据
		getDao().insert((T) bean);
	}

	/**
	 * 修改待办事项管理信息
	 * 
	 * @param bean
	 * @param actiontype
	 */
	public void update(T_pub_tomatterEntity bean, String actiontype)
			throws Exception {
		// 执行修改操作
		getDao().update((T) bean);
		getDao().deleteBiz(bean);
	}

	/**
	 * 修改待办事项管理信息
	 * 
	 * @param bean
	 * @param actiontype
	 */
	public void updateStatus(T_pub_tomatterEntity bean)
			throws Exception {
		// 执行修改操作
		getDao().update((T) bean);
	}

	/**
	 * 删除待办事项管理信息
	 * 
	 * @param bean
	 * 
	 */
	public void delete(T_pub_tomatterEntity bean) {
		// 删除待办事项管理
		getDao().delete((T) bean);
	}

	public void insetMsgVist(T_pub_messagevistEntity bean) throws Exception {
		String keyID = KeyUtils.createKeyID();
		// 生成编号
		bean.setMid(keyID);
		// 插入数据
		getDao().insetMsgVist(bean);
	}

	public String getBiztypename() {
		return "待办事项管理信息管理";
	}
}
