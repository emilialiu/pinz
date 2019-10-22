package com.dimine.sm.dao;

import java.util.List;

import com.dimine.base.dao.BaseDao;
import com.dimine.base.dao.BizDao;
import com.dimine.pub.entity.SelectListEntity;
import com.dimine.sm.entity.ClasscfgEntity;

/**
 * 班次配置表 Mapper
 * 
 * @author dimine 2015-07-08 16:55:30
 * 
 */
@BizDao
public interface ClasscfgDao<T> extends BaseDao<T> {

	/**
	 * 根据所属部门查询班次
	 * 
	 * @param bean
	 * @return
	 */
	List<ClasscfgEntity> selectList(ClasscfgEntity bean);

	List<ClasscfgEntity> selectListData(ClasscfgEntity bean);

	List<SelectListEntity> selectBcByDeptid(ClasscfgEntity bean);

	List<ClasscfgEntity> selectalltime(ClasscfgEntity bean);

	public List<ClasscfgEntity> selectBcinfo(String value);

	/**
	 * 查询当前时间的班次
	 * 
	 * @param bean
	 * @return
	 */
	public ClasscfgEntity selectCurClass(ClasscfgEntity bean);
}
