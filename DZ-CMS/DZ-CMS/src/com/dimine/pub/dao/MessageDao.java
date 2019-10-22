package com.dimine.pub.dao;

import java.util.List;

import com.dimine.base.dao.BaseDao;
import com.dimine.base.dao.BizDao;
import com.dimine.pub.entity.MessageEntity;

/**
 * Message Mapper
 * 
 * @author lijun
 */
@BizDao
public interface MessageDao<T> extends BaseDao<T> {

	/**
	 * 根据当前登录人id取得此人可以看到的公告信息列表
	 * 
	 * @param userid
	 * @return
	 */
	public List<MessageEntity> getMessageListByUserid(String userid);
	public Integer updateMessageState(MessageEntity bean);
}
