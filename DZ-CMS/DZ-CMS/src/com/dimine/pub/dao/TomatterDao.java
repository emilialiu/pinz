package com.dimine.pub.dao;

import java.util.List;

import com.dimine.base.dao.BaseDao;
import com.dimine.base.dao.BizDao;
import com.dimine.pub.entity.TomatterEntity;

/**
 * Tomatter Mapper
 * 
 * @author LCF
 * 
 */
@BizDao
public interface TomatterDao<T> extends BaseDao<T> {

	/**
	 * 根据当前登录人id取得此人处理的待办信息列表
	 * 
	 * @param userid
	 * @return
	 */
	public List<TomatterEntity> getTomatterListByUserid(String userid);
	/**
	 * 统计当前用户未办事项
	 * @param bean
	 * @return
	 */
	public Integer countMatterByUser(TomatterEntity bean);
	/**
	 * 查询当前用户未办事项
	 * @param bean
	 * @return
	 */
	public List<TomatterEntity> findMatterByuser(TomatterEntity bean);
}
