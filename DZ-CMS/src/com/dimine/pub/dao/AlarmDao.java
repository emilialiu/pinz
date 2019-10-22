package com.dimine.pub.dao;

import java.util.List;

import com.dimine.base.dao.BaseDao;
import com.dimine.base.dao.BizDao;
import com.dimine.pub.entity.AlarmEntity;

/**
 * Alarm Mapper
 * 
 * @author LCF
 * 
 */
@BizDao
public interface AlarmDao<T> extends BaseDao<T> {
	/**
	 * 根据当前登录人id取得此人处理的告警信息列表
	 * 
	 * @param userid
	 * @return
	 */
	public List<AlarmEntity> getAlarmListByUserid(String userid);
	
	List<AlarmEntity> getAlarmListByUserid1(AlarmEntity bean);
}
