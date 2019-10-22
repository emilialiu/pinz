package com.dimine.sys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dimine.base.common.ValidException;
import com.dimine.base.service.BaseService;
import com.dimine.sys.dao.DictDao;
import com.dimine.sys.dao.DictTypeDao;
import com.dimine.sys.entity.DictTypeEntity;

/**
 * 
 * <资源类型信息处理类> <功能详细描述>
 * 
 * @author LCF
 * @version [版本号, 2014-7-25]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Service("dictTypeService")
public class DictTypeService<T> extends BaseService<T> {
	@Autowired
	private DictTypeDao<T> dao;

	public DictTypeDao<T> getDao() {
		return dao;
	}

	@Autowired
	private DictDao<T> dictDao;

	public DictDao<T> getDictDao() {
		return dictDao;
	}

	/**
	 * <判断类型编号是否已经存在> <功能详细描述>
	 * 
	 * @param typeCode
	 * @param actiontype
	 * @see [类、类#方法、类#成员]
	 */
	public void typeCodeExist(String typeCode, String actiontype)
			throws ValidException {
		int count = dao.countByCode(typeCode);
		if ("addsave".equals(actiontype) && count != 0) {
			throw new ValidException("已经存在该编号的资源类型");
		}
	}

	/**
	 * <添加资源类型信息> <功能详细描述>
	 * 
	 * @throws Exception
	 * @throws AlarmException
	 * @see [类、类#方法、类#成员]
	 */
	public void insert(DictTypeEntity bean) throws ValidException {
		// 首先判断该资源类型编号是是否已经存在
		typeCodeExist(bean.getCode(), "addsave");
		dao.insert((T) bean);
	}

	/**
	 * <添加资源类型信息> <功能详细描述>
	 * 
	 * @throws Exception
	 * @throws AlarmException
	 * @see [类、类#方法、类#成员]
	 */
	public void update(DictTypeEntity bean, String actiontype) throws Exception {
		// 执行修改操作
		getDao().update((T) bean);
	}
	/**
	 * 耗材资源类型 <功能详细描述>
	 * 
	 * @param typeCodes
	 * @see [类、类#方法、类#成员]
	 */
	public void delete(String typeCode) {
		// 首先删除资源属性
		dictDao.deleteByTypeCode(typeCode);
		dao.delete((T) typeCode);
	}

	public String getBiztypename() {
		// TODO Auto-generated method stub
		return "资源类型信息管理类";
	}

}
