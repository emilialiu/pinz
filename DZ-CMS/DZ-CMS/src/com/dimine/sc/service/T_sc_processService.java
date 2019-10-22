package com.dimine.sc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dimine.base.service.BaseService;
import com.dimine.sc.dao.T_sc_processDao;
import com.dimine.sc.entity.T_sc_processEntity;

/**
 * 工序信息管理事务处理
 * 
 * @author dimine 2016-07-04 11:14:52
 * 
 */
@Service("t_sc_processService")
public class T_sc_processService<T> extends BaseService<T> {

	@Autowired
	private T_sc_processDao<T> dao;

	public T_sc_processDao<T> getDao() {
		return dao;
	}

	/**
	 * 添加新的工序信息
	 * 
	 * @param bean
	 * @param actiontype
	 * @param user
	 */
	public void insert(T_sc_processEntity bean) throws Exception {
		//String keyID = KeyUtils.createKeyID();
		// 生成编号
		//bean.setProcid(keyID);
		// 插入数据
		getDao().insert((T) bean);
		getDao().updateParentIsNotLeaf(bean);
	}

	/**
	 * 修改工序信息信息
	 * 
	 * @param bean
	 * @param actiontype
	 */
	public void update(T_sc_processEntity bean, String actiontype) throws Exception {
		// 执行修改操作
		getDao().update((T) bean);
	}

	
	/**
	 * 删除工序信息信息
	 * 
	 * @param bean
	 * 
	 */
	public void delete(T_sc_processEntity bean) {
		// 删除工序信息
		getDao().delete((T) bean);
	}
	
	/**
	 * 单元树工序信息显示
	 * @param bean
	 * @return
	 */
	public List<T_sc_processEntity> getGxList(T_sc_processEntity bean){
		return getDao().getGxList(bean);
	}
	/**
	 * 根据工艺查询是月计划的工序
	 * @param bean
	 * @return
	 */
	public List<T_sc_processEntity> selectGongXuByGongYi(T_sc_processEntity bean){
		return getDao().selectGongXuByGongYi(bean);
	}
	/**
	 * 通过parentid获取parentstr
	 */
	public String getParentStr(T_sc_processEntity bean){
		return getDao().getParentStr(bean);
	}
	/**
	 * 通过parentid删除信息
	 */
	public void deleteByParentid(T_sc_processEntity bean){
		getDao().deleteByParentid(bean);
	}

	/**
	 * 更新父节点不是叶子节点
	 */
	public void updateParentIsNotLeaf(T_sc_processEntity bean){
		getDao().updateParentIsNotLeaf(bean);
	}
	/**
	 * 查询孩子节点的数量
	 */
	public int selectChildCount(T_sc_processEntity bean){
		return getDao().selectChildCount(bean);
	}
	/**
	 * 把该节点的父节点更新isleaf为1
	 */
	public void updateParentLeafInfo(T_sc_processEntity bean){
		getDao().updateParentLeafInfo(bean);
	}

	/**
	 *SSM
	 * 通过工艺id，查询出所有的工序
	 * @param bean
	 * @return
	 */
	public List<T_sc_processEntity> genProcBytechId(T_sc_processEntity bean) {
		// TODO Auto-generated method stub
		return getDao().genProcBytechId(bean);
	}

	public String getBiztypename() {
		return "工序信息信息管理";
	}
	
	/**
	 *SSM
	 * 通过工艺id，查询出所有的工序(验收)
	 * @param bean
	 * @return
	 */
	public List<T_sc_processEntity> genYSProcBytechId(T_sc_processEntity bean) {
		// TODO Auto-generated method stub
		return getDao().genYSProcBytechId(bean);
	}

}
