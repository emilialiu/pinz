package com.dimine.pub.service;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dimine.base.common.ValidException;
import com.dimine.base.service.BaseService;
import com.dimine.base.util.KeyUtils;
import com.dimine.pub.dao.T_pub_alarmDao;
import com.dimine.pub.entity.T_pub_alarmEntity;

/**
 * 告警信息管理管理事务处理
 * 
 * @author dimine 2017-10-26 14:27:39
 * 
 */
@Service("t_pub_alarmService")
public class T_pub_alarmService<T> extends BaseService<T> {

	@Autowired
	private T_pub_alarmDao<T> dao;

	public T_pub_alarmDao<T> getDao() {
		return dao;
	}

	/**
	 * 添加新的告警信息管理
	 * 
	 * @param bean
	 * @param actiontype
	 * @param user
	 */
	public void insert(T_pub_alarmEntity bean) throws Exception {

		// 插入数据
		getDao().insert((T) bean);
	}

	/**
	 * 修改告警信息管理信息
	 * 
	 * @param bean
	 * @param actiontype
	 */
	public void update(T_pub_alarmEntity bean, String actiontype) throws Exception {
		// 执行修改操作
		getDao().update((T) bean);
	}

	/**
	 * 删除告警信息管理信息
	 * 
	 * @param bean
	 * 
	 */
	public void delete(T_pub_alarmEntity bean) {
		// 删除告警信息管理
		getDao().delete((T) bean);
	}
	
	public String getBiztypename() {
		return "告警信息管理信息管理";
	}
	/**
	 * 查询用户
	 * @param bean
	 * @return
	 */
	public List<T_pub_alarmEntity> findUser(T_pub_alarmEntity bean){
		return getDao().findUser(bean);
	}
	/**
	 * 插入业务数据
	 * @param bean
	 */
	public void insetAlarmBiz(T_pub_alarmEntity bean){
		String keyID = KeyUtils.createKeyID();
		bean.setMid(keyID);
		getDao().insetAlarmBiz(bean);
	}
	/**
	 * 查询元素名称
	 * @param code
	 * @return
	 */
	public String findElementName(String code){
		return getDao().findElementName(code);
	}
	/**
	 * 插入代办信息
	 * @param bean
	 */
	public void insetTomatter(T_pub_alarmEntity bean){
		 getDao().insetTomatter(bean);
	}
}
