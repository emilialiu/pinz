package com.dimine.pub.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dimine.base.service.BaseService;
import com.dimine.base.util.KeyUtils;
import com.dimine.pub.dao.MessageDao;
import com.dimine.pub.entity.MessageEntity;

/**
 * 角色管理事务处理
 * 
 * @author 徐飞雄（dominic）-多米
 * 
 */
@Service("messageService")
public class MessageService<T> extends BaseService<T> {

	@Autowired
	private MessageDao<T> dao;

	public MessageDao<T> getDao() {
		return dao;
	}

	/**
	 * 添加
	 * 
	 * @param bean
	 * @param actiontype
	 * @param user
	 */
	public void insert(MessageEntity bean) throws Exception {
		String roleID = KeyUtils.createKeyID();
		// 生成编号
		bean.setMessageid(roleID);
		// 插入数据
		getDao().insert((T) bean);
	}

	/**
	 * 修改
	 * 
	 * @param bean
	 * @param actiontype
	 */
	public void update(MessageEntity bean, String actiontype) throws Exception {
		// 执行修改操作
		getDao().update((T) bean);
	}

	/**
	 * 修改
	 * 
	 * @param bean
	 * @param actiontype
	 * @param user
	 */
	public void update(MessageEntity bean) throws Exception {
		// 插入数据
		getDao().update((T) bean);
	}

	/**
	 * 删除
	 * 
	 * @param roleids
	 *            类型是:String[]
	 */
	public void delete(MessageEntity bean) {
		// 删除角色
		getDao().delete((T) bean);
	}

	/**
	 * 根据当前登录人id取得此人可以看到的公告信息列表
	 * 
	 * @param userid
	 * @return
	 */
	public List<MessageEntity> getMessageListByUserid(String userid) {
		return dao.getMessageListByUserid(userid);
	}
	public Integer updateMessageState(MessageEntity bean){
		return dao.updateMessageState(bean);
	}
	public String getBiztypename() {
		return "通知公告信息管理";
	}

}
