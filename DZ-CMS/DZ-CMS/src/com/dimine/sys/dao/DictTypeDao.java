package com.dimine.sys.dao;

import java.util.List;

import com.dimine.base.dao.BaseDao;
import com.dimine.base.dao.BizDao;

/**
 * DictType Mapper
 * 
 * @author LCF
 * 
 */
@BizDao
public interface DictTypeDao<T> extends BaseDao<T> {
	/**
	 * 查询指定资源类型编号记录数
	 * 
	 * @param code
	 * @return
	 */
	public int countByCode(String code);
	
}
