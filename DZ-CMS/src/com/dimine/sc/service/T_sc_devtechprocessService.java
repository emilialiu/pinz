package com.dimine.sc.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dimine.base.service.BaseService;
import com.dimine.sc.dao.T_sc_devtechprocessDao;
import com.dimine.sc.entity.T_sc_devtechprocessEntity;

/**
 * 工序对应设备管理事务处理
 * 
 * @author dimine 2016-08-17 11:06:21
 * 
 */
@Service("t_sc_devtechprocessService")
public class T_sc_devtechprocessService<T> extends BaseService<T> {

	@Autowired
	private T_sc_devtechprocessDao<T> dao;

	public T_sc_devtechprocessDao<T> getDao() {
		return dao;
	}

	/**
	 * 添加新的工序对应设备
	 * 
	 * @param bean
	 * @param actiontype
	 * @param user
	 */
	public void insert(T_sc_devtechprocessEntity bean) throws Exception {
		//String keyID = KeyUtils.createKeyID();
		// 生成编号
		//bean.setProcid(keyID);
		// 插入数据
		getDao().insert((T) bean);
	}

	/**
	 * 修改工序对应设备信息
	 * 
	 * @param bean
	 * @param actiontype
	 */
	public void update(T_sc_devtechprocessEntity bean, String actiontype) throws Exception {
		// 执行修改操作
		getDao().update((T) bean);
	}

	/**
	 * 删除工序对应设备信息
	 * 
	 * @param bean
	 * 
	 */
	public void delete(T_sc_devtechprocessEntity bean) {
		// 删除工序对应设备
		getDao().delete((T) bean);
	}
	public List<T_sc_devtechprocessEntity> selectDeviceByList(T_sc_devtechprocessEntity bean)throws Exception {
		return getDao().selectDeviceByList(bean);
	}
	public Integer selectDeviceByCount(T_sc_devtechprocessEntity bean)throws Exception {
		return getDao().selectDeviceByCount(bean);
	}
	
	
	public String getBiztypename() {
		return "工序对应设备信息管理";
	}

}
