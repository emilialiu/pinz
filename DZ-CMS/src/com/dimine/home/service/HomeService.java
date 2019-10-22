package com.dimine.home.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dimine.base.dao.BaseDao;
import com.dimine.base.service.BaseService;
import com.dimine.home.dao.HomeDao;
import com.dimine.sys.entity.FuncEntity;

/**
 * 首页Service
 * 
 * @author ssm
 * 
 */
@Service("homeService")
public class HomeService<T> extends BaseService<T> {
	@Autowired
	private HomeDao<T> dao;

	public BaseDao<T> getDao() {
		return dao;
	}

	/**
	 * 根据父功能code查找所有子功能
	 * 
	 * @param func
	 * @return
	 */
	public List<FuncEntity> listByParent(FuncEntity func) {
		return dao.listByParent(func);
	}

	public List<FuncEntity> listByParentTop(FuncEntity func) {
		return dao.listByParentTop(func);
	}

	public List<FuncEntity> listSelectParentTop(FuncEntity func) {
		return dao.listSelectParentTop(func);
	}

	public List<FuncEntity> listByParentSec(FuncEntity func) {
		return dao.listByParentSec(func);
	}

	public List<FuncEntity> searchBynav(FuncEntity func) {
		if (func.getFuncname().length() > 1) {
			return dao.doFuncSearch(func);
		} else {
			return dao.doFuncSearch1(func);
		}
	}

	public List<FuncEntity> doFuncSearch(FuncEntity func) {
		if (func.getFuncname().length() > 1) {
			return dao.doFuncSearch(func);
		} else {
			return dao.doFuncSearch1(func);
		}
	}

	/**
	 * 根据参数编号取得参数值
	 * 
	 * @param code
	 * @return
	 */
	public String getValueByCode(String code) {
		return dao.getValueByCode(code);
	}

	public void setDao(HomeDao<T> dao) {
		this.dao = dao;
	}

}
