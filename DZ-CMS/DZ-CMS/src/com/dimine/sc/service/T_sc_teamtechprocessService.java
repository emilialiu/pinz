package com.dimine.sc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dimine.base.service.BaseService;
import com.dimine.sc.dao.T_sc_teamtechprocessDao;
import com.dimine.sc.entity.T_sc_teamtechprocessEntity;

/**
 * 工序对应班组管理事务处理
 * 
 * @author dimine 2016-08-11 10:54:09
 * 
 */
@Service("t_sc_teamtechprocessService")
public class T_sc_teamtechprocessService<T> extends BaseService<T> {

	@Autowired
	private T_sc_teamtechprocessDao<T> dao;

	public T_sc_teamtechprocessDao<T> getDao() {
		return dao;
	}

	/**
	 * 添加新的工序对应班组
	 * 
	 * @param bean
	 * @param actiontype
	 * @param user
	 */
	public void insert(T_sc_teamtechprocessEntity bean) throws Exception {
		// 插入数据
		getDao().insert((T) bean);
	}

	/**
	 * 修改工序对应班组信息
	 * 
	 * @param bean
	 * @param actiontype
	 */
	public void update(T_sc_teamtechprocessEntity bean, String actiontype) throws Exception {
		// 执行修改操作
		getDao().update((T) bean);
	}

	/**
	 * 删除工序对应班组信息
	 * 
	 * @param bean
	 * 
	 */
	public void delete(T_sc_teamtechprocessEntity bean) {
		// 删除工序对应班组
		getDao().delete((T) bean);
	}
	
	/**
	 * 查找某项目部所有的班组
	 * @param bean
	 * @return
	 */
	public List<T_sc_teamtechprocessEntity> selectTeam(T_sc_teamtechprocessEntity bean) throws Exception{
		bean.getPager().setRowCount(getDao().selectByTeamCount(bean));
		return getDao().selectTeam(bean);
	}
	
	/**
	 * 查询该工序对应的班组
	 */
	public List<T_sc_teamtechprocessEntity> selectTeamByGx(T_sc_teamtechprocessEntity bean){
		return getDao().selectTeamByGx(bean);
	}
	
	/**
	 * 通过工序查找某项目部所有的班组
	 * @param bean
	 * @return
	 */
	public List<T_sc_teamtechprocessEntity> selectTeamByProc(T_sc_teamtechprocessEntity bean) throws Exception{
		bean.getPager().setRowCount(getDao().selectByTeamCount2(bean));
		return getDao().selectTeamByProc(bean);
	}
	
	public String getBiztypename() {
		return "工序对应班组信息管理";
	}

}
