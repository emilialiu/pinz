package com.dimine.sm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dimine.base.service.BaseService;
import com.dimine.base.util.KeyUtils;
import com.dimine.pub.entity.SelectListEntity;
import com.dimine.sm.dao.ClasscfgDao;
import com.dimine.sm.entity.ClasscfgEntity;

/**
 * 班次配置表管理事务处理
 * 
 * @author dimine 2015-07-08 16:55:30
 * 
 */
@Service("classcfgService")
public class ClasscfgService<T> extends BaseService<T> {

	@Autowired
	private ClasscfgDao<T> dao;

	public ClasscfgDao<T> getDao() {
		return dao;
	}

	/*
	 * 根据所属部门查找该部门的班次
	 */
	public List<ClasscfgEntity> selectBcinfo(String value) {
		return getDao().selectBcinfo(value);
	}

	/**
	 * 添加新的班次配置表
	 * 
	 * @param bean
	 * @param actiontype
	 * @param user
	 */
	public void insert(ClasscfgEntity bean) throws Exception {
		String keyID = KeyUtils.createKeyID();
		// 生成编号
		bean.setClasscfgid(keyID);
		// 插入数据
		getDao().insert((T) bean);
	}

	/**
	 * 修改班次配置表信息
	 * 
	 * @param bean
	 * @param actiontype
	 */
	public void update(ClasscfgEntity bean, String actiontype) throws Exception {
		// 执行修改操作
		getDao().update((T) bean);
	}

	/**
	 * 删除班次配置表信息
	 * 
	 * @param bean
	 * 
	 */
	public void delete(ClasscfgEntity bean) {
		// 删除班次配置表
		getDao().delete((T) bean);
	}

	public String getBiztypename() {
		return "班次配置表信息管理";
	}

	/**
	 * 根据所属部门查询班次
	 * 
	 * @param bean
	 * @return
	 */
	public List<ClasscfgEntity> selectList(ClasscfgEntity bean) {
		return getDao().selectList(bean);
	}

	/**
	 * 查询数据
	 * 
	 * @param bean
	 * @return
	 */
	public List<ClasscfgEntity> selectListData(ClasscfgEntity bean) {
		bean.getPager().setRowCount(getDao().selectByCount(bean));
		return getDao().selectListData(bean);
	}

	/**
	 * 查询筛选班次列表
	 * 
	 * @param bean
	 * @return
	 */
	public List<SelectListEntity> selectBcByDeptid(ClasscfgEntity bean) {

		return getDao().selectBcByDeptid(bean);
	}

	/**
	 * 查询某项目部所有时间
	 * 
	 * @param bean
	 * @return
	 */
	public List<ClasscfgEntity> selectalltime(ClasscfgEntity bean) {
		return getDao().selectalltime(bean);
	}

	/**
	 * 查询当前时间的班次
	 * 
	 * @param bean
	 * @return
	 */
	public ClasscfgEntity selectCurClass(ClasscfgEntity bean) {
		return getDao().selectCurClass(bean);
	}
}
