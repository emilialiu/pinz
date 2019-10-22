package com.dimine.sm.dao;

import java.util.List;
import java.util.Map;

import com.dimine.base.dao.BaseDao;
import com.dimine.base.dao.BizDao;
import com.dimine.sm.entity.ElementpropertyEntity;

/**
 * 要素属性表 Mapper
 * 
 * @author dimine 2015-11-09 11:23:11
 * 
 */
@BizDao
public interface ElementpropertyDao<T> extends BaseDao<T> {
	List<ElementpropertyEntity> selectByListByeletype(ElementpropertyEntity bean);

	int selectByListByeletypeCount(ElementpropertyEntity bean);

	List<ElementpropertyEntity> selectelename(ElementpropertyEntity bean);

	/**
	 * 根据项目部ID及要素分类类型查询工程级别
	 * 
	 * @param map
	 * @return
	 */
	List<ElementpropertyEntity> getProjectLevelByProID(Map map);

	List<ElementpropertyEntity> selectList(ElementpropertyEntity bean);

}
