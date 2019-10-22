package com.dimine.pub.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dimine.base.service.BaseService;
import com.dimine.pub.dao.AlarmDao;
import com.dimine.pub.entity.AlarmEntity;

/**
 * 
 * 告警信息service
 * 
 * @author LCF
 * @version [版本号, 2014-12-12]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Service("alarmService")
public class AlarmService<T> extends BaseService<T> {
	@Autowired
	private AlarmDao<T> dao;

	public AlarmDao<T> getDao() {
		return dao;
	}

	/**
	 * 根据当前登录人id取得此人处理的告警信息列表
	 * 
	 * @param userid
	 * @return
	 */
	public List<AlarmEntity> getAlarmListByUserid(String userid) {
		return dao.getAlarmListByUserid(userid);
	}
	public List<AlarmEntity> getAlarmListByUserid1(AlarmEntity bean){
		return dao.getAlarmListByUserid1(bean);
	}
}
