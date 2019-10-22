package com.dimine.pub.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dimine.base.service.BaseService;
import com.dimine.base.util.KeyUtils;
import com.dimine.pub.dao.T_pub_attachmentDao;
import com.dimine.pub.entity.T_pub_attachmentEntity;

/**
 * 附件表(T_PUB_Attachment)管理事务处理
 * 
 * @author dimine 2017-01-13 15:25:40
 * 
 */
@Service("t_pub_attachmentService")
public class T_pub_attachmentService<T> extends BaseService<T> {

	@Autowired
	private T_pub_attachmentDao<T> dao;

	public T_pub_attachmentDao<T> getDao() {
		return dao;
	}

	/**
	 * 添加新的附件表(T_PUB_Attachment)
	 * 
	 * @param bean
	 * @param actiontype
	 * @param user
	 */
	public void insert(T_pub_attachmentEntity bean) throws Exception {
		String keyID = KeyUtils.createKeyID();
		// 生成编号
		bean.setAttachmentid(keyID);
		// 插入数据
		getDao().insert((T) bean);
	}

	/**
	 * 修改附件表(T_PUB_Attachment)信息
	 * 
	 * @param bean
	 * @param actiontype
	 */
	public void update(T_pub_attachmentEntity bean, String actiontype)
			throws Exception {
		// 执行修改操作
		getDao().update((T) bean);
	}

	/**
	 * 删除附件表(T_PUB_Attachment)信息
	 * 
	 * @param bean
	 * 
	 */
	public void delete(T_pub_attachmentEntity bean) {
		// 删除附件表(T_PUB_Attachment)
		getDao().delete((T) bean);
	}

	public String getBiztypename() {
		return "附件表(T_PUB_Attachment)信息管理";
	}

	public void deleteBybizid(String businessid) {
		getDao().deleteBybizid(businessid);
	}

	/**
	 * 根据业务id查询附件信息
	 * 
	 * @param bean
	 * @return
	 */
	public List<T_pub_attachmentEntity> selectByBusinessid(
			T_pub_attachmentEntity bean) {
		return getDao().selectByBusinessid(bean);
	}

	public List<T_pub_attachmentEntity> selectByListm(
			T_pub_attachmentEntity bean) {
		Integer count = getDao().selectByCountm(bean);
		bean.getPager().setRowCount(count);
		return getDao().selectByListm(bean);
	}

}
