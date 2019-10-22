package com.dimine.sc.dao;

import java.util.List;

import com.dimine.base.dao.BaseDao;
import com.dimine.base.dao.BizDao;
import com.dimine.sc.entity.T_sc_teamtechprocessEntity;

/**
 * 工序对应班组 Mapper
 * 
 * @author dimine 2016-08-11 10:54:09
 * 
 */
@BizDao
public interface T_sc_teamtechprocessDao<T> extends BaseDao<T> {
	/**
	 * 查找某项目部所有的班组
	 * @param bean
	 * @return
	 */
	public List<T_sc_teamtechprocessEntity> selectTeam(T_sc_teamtechprocessEntity bean);
	public int selectByTeamCount(T_sc_teamtechprocessEntity bean);
	/**
	 * 查询该工序对应的班组
	 */
	public List<T_sc_teamtechprocessEntity> selectTeamByGx(T_sc_teamtechprocessEntity bean);
	
	/**
	 * 通过工序查找某项目部所有的班组
	 * @param bean
	 * @return
	 */
	public List<T_sc_teamtechprocessEntity> selectTeamByProc(T_sc_teamtechprocessEntity bean);
	public int selectByTeamCount2(T_sc_teamtechprocessEntity bean);
}
