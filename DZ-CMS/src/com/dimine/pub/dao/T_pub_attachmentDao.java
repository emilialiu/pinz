package com.dimine.pub.dao;

import java.util.List;

import com.dimine.base.dao.BaseDao;
import com.dimine.base.dao.BizDao;
import com.dimine.pub.entity.T_pub_attachmentEntity;

/**
 * 附件表(T_PUB_Attachment) Mapper
 * 
 * @author dimine 2017-01-13 15:25:40
 * 
 */
@BizDao
public interface T_pub_attachmentDao<T> extends BaseDao<T> {

	/**
	 * 根据业务ID删除附件
	 * 
	 * @param businessid
	 */
	void deleteBybizid(String businessid);

	/**
	 * 根据业务id查询附件信息
	 * 
	 * @param businessid
	 * @return
	 */
	public List<T_pub_attachmentEntity> selectByBusinessid(
			T_pub_attachmentEntity bean);

	public Integer selectByCountm(T_pub_attachmentEntity bean);

	public List<T_pub_attachmentEntity> selectByListm(T_pub_attachmentEntity bean);

}
