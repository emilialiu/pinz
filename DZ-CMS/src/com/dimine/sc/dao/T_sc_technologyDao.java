package com.dimine.sc.dao;

import java.util.List;

import com.dimine.base.dao.BaseDao;
import com.dimine.base.dao.BizDao;
import com.dimine.sc.entity.T_sc_technologyEntity;

/**
 * 工艺信息 Mapper
 * 
 * @author dimine 2016-06-30 11:11:37
 * 
 */
@BizDao
public interface T_sc_technologyDao<T> extends BaseDao<T> {
	/**
	 * 单元树工艺信息显示
	 * @param bean
	 * @return
	 */
	public List<T_sc_technologyEntity> getGyList(T_sc_technologyEntity bean);
	/**
	 * 通过parentid获取parentstr
	 */
	public String getParentStr(T_sc_technologyEntity bean);
	/**
	 * 把该节点的父节点更新isleaf为0
	 */
	public void updateParentIsLeaf(T_sc_technologyEntity bean);
	/**
	 * 查询孩子节点的数量
	 */
	public int selectChildCount(T_sc_technologyEntity bean);
	/**
	 * 把该节点的父节点更新isleaf为1
	 */
	public void updateParentLeafInfo(T_sc_technologyEntity bean);
	/**
	 * 更新工艺信息
	 * @return
	 */
	public void updateTech(T_sc_technologyEntity bean);
	//药剂消耗查询工艺
	public List<T_sc_technologyEntity> selectList();
	public List<T_sc_technologyEntity> selectListByTeamid(T_sc_technologyEntity bean);
	public List<T_sc_technologyEntity> selectGongXuByLoginUser(T_sc_technologyEntity bean);
	public List<T_sc_technologyEntity> selectByReport(T_sc_technologyEntity bean);

}
