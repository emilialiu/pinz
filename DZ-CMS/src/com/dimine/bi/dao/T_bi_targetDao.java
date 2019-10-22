package com.dimine.bi.dao;

import java.util.List;

import com.dimine.base.dao.BaseDao;
import com.dimine.base.dao.BizDao;
import com.dimine.bi.entity.T_bi_targetEntity;

/**
 * 指标信息 Mapper
 * 
 * @author dimine 2016-07-04 11:23:07
 * 
 */
@BizDao
public interface T_bi_targetDao<T> extends BaseDao<T> {
	/**
	 * 根据工序id，查询所有的指标
	 * @param bean
	 * @return
	 */
	List<T_bi_targetEntity> selectByProcID(T_bi_targetEntity bean);
	/**
	 * 根据工序id，查询所有带有公式的指标
	 * @param bean
	 * @return
	 */
	List<T_bi_targetEntity> selectByTgorithm(T_bi_targetEntity bean);
	/**
	 * 通过工序查找指标信息
	 * 
	 */
	public List<T_bi_targetEntity> selectByTargetList(T_bi_targetEntity bean);
	public int selectByTargetCount(T_bi_targetEntity bean);
	/**
	 * 通过工序id查找指标信息
	 * 
	 */
	public List<T_bi_targetEntity> selectTargetByProcid(T_bi_targetEntity bean);
	
	/**
	 * 查找该工序下面指标的数量
	 */
	public int selectTargetCountByProcID(T_bi_targetEntity bean);
	
}
