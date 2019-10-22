package com.dimine.sm.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dimine.base.service.BaseService;
import com.dimine.base.util.KeyUtils;
import com.dimine.sm.dao.ElementtypeDao;
import com.dimine.sm.entity.ElementtypeEntity;

/**
 * 要素分类表管理事务处理
 * 
 * @author dimine 2015-11-09 11:23:34
 * 
 */
@Service("elementtypeService")
public class ElementtypeService<T> extends BaseService<T> {

	@Autowired
	private ElementtypeDao<T> dao;

	public ElementtypeDao<T> getDao() {
		return dao;
	}

	/**
	 * 添加新的要素分类表
	 * 
	 * @param bean
	 * @param actiontype
	 * @param user
	 */
	public void insert(ElementtypeEntity bean) throws Exception {
		String keyID = KeyUtils.createKeyID();
		// 生成编号
		bean.setEletypeid(keyID);
		// 插入数据
		getDao().insert((T) bean);
	}

	/**
	 * 修改要素分类表信息
	 * 
	 * @param bean
	 * @param actiontype
	 */
	public void update(ElementtypeEntity bean, String actiontype)
			throws Exception {
		// 执行修改操作
		getDao().update((T) bean);
	}

	public int isimportinsert(ElementtypeEntity bean) {
		return getDao().isimportinsert(bean);
	}

	// 月计划 汇总横表 统计
	public void allinsert(ElementtypeEntity bean) {
		HashMap<String, Object> args = new HashMap<String, Object>();

		args.put("deptid", bean.getDeptid());
		args.put("eletypekind", bean.getEletypekind());

		getDao().allinsert(args);
	}

	/**
	 * 删除要素分类表信息
	 * 
	 * @param bean
	 * 
	 */
	public void delete(ElementtypeEntity bean) {
		// 删除要素分类表
		getDao().delete((T) bean);
	}

	public boolean selectByEletypeAndDept(ElementtypeEntity bean) {
		// 删除要素分类表
		if (getDao().selectByEletypeAndDept(bean).size() > 0) {
			return true;
		}
		return false;
	}

	public List<ElementtypeEntity> getList(ElementtypeEntity bean) {
		return (List<ElementtypeEntity>) dao.selectByList(bean);
	}

	public String getBiztypename() {
		return "要素分类表信息管理";
	}

}
