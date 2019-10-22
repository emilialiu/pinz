package com.dimine.pub.dao;

import com.dimine.base.dao.BaseDao;
import com.dimine.base.dao.BizDao;
import com.dimine.pub.entity.T_pub_messagevistEntity;
import com.dimine.pub.entity.T_pub_tomatterEntity;

/**
 * 待办事项管理 Mapper
 * 
 * @author dimine 2017-11-16 15:35:42
 * 
 */
@BizDao
public interface T_pub_tomatterDao<T> extends BaseDao<T> {
	/**
	 * 删除业务表其他代办信息
	 * 
	 * @param bean
	 */
	public void deleteBiz(T_pub_tomatterEntity bean);

	/**
	 * 插入待办接收人记录
	 * 
	 * @param bean
	 */
	public void insetMsgVist(T_pub_messagevistEntity bean);
}
