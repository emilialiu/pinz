package com.dimine.sc.dao;

import java.util.List;

import com.dimine.base.dao.BaseDao;
import com.dimine.base.dao.BizDao;
import com.dimine.sc.entity.T_sc_processEntity;


/**
 * 工序信息 Mapper
 * 
 * @author dimine 2016-07-04 11:14:52
 * 
 */
@BizDao
public interface T_sc_processDao<T> extends BaseDao<T> {
	/**
	 * 单元树工序信息显示
	 * @param bean
	 * @return
	 */
	public List<T_sc_processEntity> getGxList(T_sc_processEntity bean);
	/**
	 * 通过parentid获取parentstr
	 */
	public String getParentStr(T_sc_processEntity bean);
	/**
	 * 通过parentid删除信息
	 */
	public void deleteByParentid(T_sc_processEntity bean);

	/**
	 * 更新父节点不是叶子节点
	 */
	public void updateParentIsNotLeaf(T_sc_processEntity bean);
	/**
	 * 查询孩子节点的数量
	 */
	public int selectChildCount(T_sc_processEntity bean);
	/**
	 * 把该节点的父节点更新isleaf为1
	 */
	public void updateParentLeafInfo(T_sc_processEntity bean);

	/**
	 * 根据工艺id，查询出相应的工序信息
	 * @param bean
	 * @return
	 */
	public List<T_sc_processEntity> genProcBytechId(T_sc_processEntity bean);

	/**
	 * 根据工艺id，查询出相应的工序信息(验收)
	 * @param bean
	 * @return
	 */
	public List<T_sc_processEntity> genYSProcBytechId(T_sc_processEntity bean);
	public List<T_sc_processEntity> selectGongXuByGongYi(T_sc_processEntity bean);

}
