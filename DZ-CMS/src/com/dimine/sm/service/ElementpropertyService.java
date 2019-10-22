package com.dimine.sm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dimine.base.service.BaseService;
import com.dimine.base.util.KeyUtils;
import com.dimine.sm.dao.ElementpropertyDao;
import com.dimine.sm.entity.ElementpropertyEntity;

/**
 * 要素属性表管理事务处理
 * 
 * @author dimine 2015-11-09 11:23:11
 * 
 */
@Service("elementpropertyService")
public class ElementpropertyService<T> extends BaseService<T> {

	@Autowired
	private ElementpropertyDao<T> dao;

	public ElementpropertyDao<T> getDao() {
		return dao;
	}

	/**
	 * 添加新的要素属性表
	 * 
	 * @param bean
	 * @param actiontype
	 * @param user
	 */
	public void insert(ElementpropertyEntity bean) throws Exception {
		String keyID = KeyUtils.createKeyID();
		// 生成编号
		bean.setEleproid(keyID);
		// 插入数据
		getDao().insert((T) bean);
	}

	/**
	 * 修改要素属性表信息
	 * 
	 * @param bean
	 * @param actiontype
	 */
	public void update(ElementpropertyEntity bean, String actiontype)
			throws Exception {
		// 执行修改操作
		getDao().update((T) bean);
	}

	/**
	 * 删除要素属性表信息
	 * 
	 * @param bean
	 * 
	 */
	public void delete(ElementpropertyEntity bean) {
		// 删除要素属性表
		getDao().delete((T) bean);
	}

	public List<ElementpropertyEntity> selectByListByeletype(
			ElementpropertyEntity bean) {
		bean.getPager().setRowCount(getDao().selectByListByeletypeCount(bean));
		return getDao().selectByListByeletype(bean);
	}

	public List<ElementpropertyEntity> selectelename(ElementpropertyEntity bean) {
		return getDao().selectelename(bean);
	}

	public List<ElementpropertyEntity> selectList(ElementpropertyEntity bean) {
		return getDao().selectList(bean);
	}

	public String getBiztypename() {
		return "要素属性表信息管理";
	}

}
