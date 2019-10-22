package com.dimine.pub.dao;

import java.util.List;
import com.dimine.base.dao.BaseDao;
import com.dimine.base.dao.BizDao;
import com.dimine.pub.entity.AttachmentEntity;

/**
 * 附件信息 Mapper
 * 
 * @author dimine 2015-03-10 15:52:56
 * 
 */
@BizDao
public interface AttachmentDao<T> extends BaseDao<T> {
	/**
	 * 根据业务id查询附件信息
	 * 
	 * @param businessid
	 * @return
	 */
	public List<AttachmentEntity> selectByBusinessid(AttachmentEntity bean);

	/**
	 * 课件查询
	 * 
	 * @param bean
	 * @return
	 */
	public List<AttachmentEntity> selectCwList(AttachmentEntity bean);

	/**
	 * 课件总数查询
	 * 
	 * @param bean
	 * @return
	 */
	public Integer selectCwCount(AttachmentEntity bean);

	/**
	 * 根据文件路径删除文件
	 * 
	 * @param bean
	 * @return
	 */
	public void deleteByUrl(AttachmentEntity bean);

	/**
	 * 修改文件路径
	 * 
	 * @param bean
	 */
	public void updateUrl(AttachmentEntity bean);
}
