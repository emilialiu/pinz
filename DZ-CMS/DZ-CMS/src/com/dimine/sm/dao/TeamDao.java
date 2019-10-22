package com.dimine.sm.dao;

import java.util.List;

import com.dimine.base.dao.BaseDao;
import com.dimine.base.dao.BizDao;
import com.dimine.sm.entity.TeamEntity;

/**
 * 班组 Mapper
 * 
 * @author dimine 2015-06-16 17:53:00
 * 
 */
@BizDao
public interface TeamDao<T> extends BaseDao<T> {
	/**
	 * 删除班组（逻辑删除）
	 * 
	 * @param staffid
	 */
	public void deleteTeam(String teamid);

	/**
	 * 取得班组编号记录数
	 * 
	 * @param bean
	 * @return
	 */
	public int getSameCode(TeamEntity bean);

	/**
	 * 根据tdeptid查询班组信息
	 * 
	 * @param tdeptid
	 */
	public List<TeamEntity> selectByTdeptId(TeamEntity bean);

	/**
	 * 根据班组编号取得记录数
	 * 
	 * @param bean
	 * @return
	 */
	public int getCountByCode(String code);

	/**
	 * 根据项目部id及业务类型取得班组类型列表
	 * 
	 * @param bean
	 * @return
	 */
	public List<TeamEntity> selectTeamTypeList(TeamEntity bean);

	List<TeamEntity> genTeamList(TeamEntity bean);
	List<TeamEntity> genAetailTeamList(TeamEntity bean);
}
