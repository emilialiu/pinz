package com.dimine.bi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dimine.base.service.BaseService;
import com.dimine.bi.dao.T_bi_targetDao;
import com.dimine.bi.entity.T_bi_targetEntity;

/**
 * 指标信息管理事务处理
 * 
 * @author dimine 2016-07-04 11:23:07
 * 
 */
@Service("t_bi_targetService")
public class T_bi_targetService<T> extends BaseService<T> {

	@Autowired
	private T_bi_targetDao<T> dao;

	public T_bi_targetDao<T> getDao() {
		return dao;
	}

	/**
	 * 添加新的指标信息
	 * 
	 * @param bean
	 * @param actiontype
	 * @param user
	 */
	public void insert(T_bi_targetEntity bean) throws Exception {
//		String keyID = KeyUtils.createKeyID();
//		// 生成编号
//		bean.setTargetid(keyID);
		// 插入数据
		getDao().insert((T) bean);
	}

	/**
	 * 修改指标信息信息
	 * 
	 * @param bean
	 * @param actiontype
	 */
	public void update(T_bi_targetEntity bean, String actiontype) throws Exception {
		// 执行修改操作
		getDao().update((T) bean);
	}

	/**
	 * 删除指标信息信息
	 * 
	 * @param bean
	 * 
	 */
	public void delete(T_bi_targetEntity bean) {
		// 删除指标信息
		getDao().delete((T) bean);
	}
	/**SSM
	 * 根据工序id，查询所有的指标信息
	 * @param bean
	 * @return
	 */
	public List<T_bi_targetEntity> selectByProcID(T_bi_targetEntity bean) {
		// TODO Auto-generated method stub
		return  getDao().selectByProcID(bean);
	}
	/**SSM
	 * 查询带有公式的指标
	 * @param bean
	 * @return
	 */
	public List<T_bi_targetEntity> selectByTgorithm(T_bi_targetEntity bean) {
		// TODO Auto-generated method stub
		return  getDao().selectByTgorithm(bean);
	}
	/**
	 * 通过工序查找指标信息
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	public List<T_bi_targetEntity> selectByTargetList(T_bi_targetEntity bean){
		bean.getPager().setRowCount(getDao().selectByTargetCount(bean));
		return getDao().selectByTargetList(bean);
	}
	/**
	 * 通过工序id查找指标信息
	 * 
	 */
	public List<T_bi_targetEntity> selectTargetByProcid(T_bi_targetEntity bean){
		return getDao().selectTargetByProcid(bean);
	}
	/**
	 * 查找该工序下面指标的数量
	 */
	public int selectTargetCountByProcID(T_bi_targetEntity bean){
		return getDao().selectTargetCountByProcID(bean);
	}
	
	public String getBiztypename() {
		return "指标信息信息管理";
	}

	

}
