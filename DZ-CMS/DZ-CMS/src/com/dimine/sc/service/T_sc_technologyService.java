package com.dimine.sc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dimine.base.service.BaseService;
import com.dimine.base.util.KeyUtils;
import com.dimine.sc.dao.T_sc_technologyDao;
import com.dimine.sc.entity.T_sc_technologyEntity;

/**
 * 工艺信息管理事务处理
 * 
 * @author dimine 2016-06-30 11:11:37
 * 
 */
@Service("t_sc_technologyService")
public class T_sc_technologyService<T> extends BaseService<T> {

	@Autowired
	private T_sc_technologyDao<T> dao;

	public T_sc_technologyDao<T> getDao() {
		return dao;
	}

	/**
	 * 添加新的工艺信息
	 * 
	 * @param bean
	 * @param actiontype
	 * @param user
	 */
	public void insert(T_sc_technologyEntity bean) throws Exception {
		String keyID = KeyUtils.createKeyID();
		// 生成编号
		bean.setTechid(keyID);
		// 插入数据
		getDao().insert((T) bean);
		//更新该工艺的父节点不是叶子节点
		getDao().updateParentIsLeaf(bean);
	}
	/**
	 * 
	 * 根据登录人查找工序名称
	 * @param bean
	 * @return
	 */
	public List<T_sc_technologyEntity> selectGongXuByLoginUser(T_sc_technologyEntity bean){
		return getDao().selectGongXuByLoginUser(bean);
	}
	
	/**
	 * 把该节点的父节点更新isleaf为1
	 */
	public void updateParentIsLeaf(T_sc_technologyEntity bean){
		getDao().updateParentIsLeaf(bean);
	}
	/**
	 * 修改工艺信息信息
	 * 
	 * @param bean
	 * @param actiontype
	 */
	public void update(T_sc_technologyEntity bean, String actiontype) throws Exception {
		// 执行修改操作
		getDao().update((T) bean);
	}

	/**
	 * 删除工艺信息信息
	 * 
	 * @param bean
	 * 
	 */
	public void delete(T_sc_technologyEntity bean) {
		// 删除工艺信息
		getDao().delete((T) bean);
	}
	/**
	 * 单元树工艺信息显示
	 * @param bean
	 * @return
	 */
	public List<T_sc_technologyEntity> getGyList(T_sc_technologyEntity bean){
		return getDao().getGyList(bean);
	}
	/**
	 * 通过parentid获取parentstr
	 */
	public String getParentStr(T_sc_technologyEntity bean){
		return getDao().getParentStr(bean);
	}
	/**
	 * 查询孩子节点的数量
	 */
	public int selectChildCount(T_sc_technologyEntity bean){
		return getDao().selectChildCount(bean);
	}
	/**
	 * 把该节点的父节点更新isleaf为1
	 */
	public void updateParentLeafInfo(T_sc_technologyEntity bean){
		getDao().updateParentLeafInfo(bean);
	}
	/**
	 * 更新工艺信息
	 * @return
	 */
	public void updateTech(T_sc_technologyEntity bean){
		getDao().updateTech(bean);
	}
	public List<T_sc_technologyEntity> selectList(){
		return getDao().selectList();
	}
	public List<T_sc_technologyEntity> selectListByTeamid(T_sc_technologyEntity bean){
		return getDao().selectListByTeamid(bean);
	}
	public String getBiztypename() {
		return "工艺信息信息管理";
	}

	public List<T_sc_technologyEntity> selectByReport(T_sc_technologyEntity bean) {
		return getDao().selectByReport(bean);
	}

}
