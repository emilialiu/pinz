package com.dimine.pub.dao;

import java.util.List;

import com.dimine.base.dao.BaseDao;
import com.dimine.base.dao.BizDao;
import com.dimine.pub.entity.T_pub_alarmEntity;

/**
 * 告警信息管理 Mapper
 * 
 * @author dimine 2017-10-26 14:27:39
 * 
 */
@BizDao
public interface T_pub_alarmDao<T> extends BaseDao<T> {
	List<T_pub_alarmEntity> findUser(T_pub_alarmEntity bean);//查询用户
	void insetAlarmBiz(T_pub_alarmEntity bean);//插入业务数据
	String findElementName(String code);//查看元素名称
	void insetTomatter(T_pub_alarmEntity bean);//插入带班
}
