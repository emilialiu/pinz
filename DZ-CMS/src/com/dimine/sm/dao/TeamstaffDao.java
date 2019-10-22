package com.dimine.sm.dao;

import java.util.List;

import com.dimine.base.dao.BaseDao;
import com.dimine.base.dao.BizDao;
import com.dimine.sm.entity.TeamEntity;
import com.dimine.sm.entity.TeamstaffEntity;

/**
 * 班组对应人员 Mapper
 * 
 * @author dimine 2015-06-16 17:53:39
 * 
 */
@BizDao
public interface TeamstaffDao<T> extends BaseDao<T> {
	/**
	 * 批量添加班组人员
	 * 
	 * @param list
	 */
	public void batchInsert(List<TeamstaffEntity> list);

	/**
	 * 未被添加的人员列表
	 * 
	 * @param bean
	 * @return
	 */
	public List<TeamstaffEntity> selectUnList(TeamstaffEntity bean);

	/**
	 * 未被添加的人员记录数
	 * 
	 * @param bean
	 * @return
	 */
	public int selectUnCount(TeamstaffEntity bean);

	/**
	 * 根据当前用户id查询该用户所在班组信息
	 * @param userid
	 * @return
	 */
	public TeamEntity getTeamInfoByUserid(String userid);

}
