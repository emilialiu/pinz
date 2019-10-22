package com.dimine.sm.dao;

import com.dimine.base.dao.BaseDao;
import com.dimine.base.dao.BizDao;
import com.dimine.sm.entity.OrgcfgEntity;

/**
 * 配置组织机构类型是属于组织机构还是部门。 Mapper
 * 
 * @author dimine 2015-06-23 11:20:50
 * 
 */
@BizDao
public interface OrgcfgDao<T> extends BaseDao<T> {

	/**
	 * 取得组织机构类型记录数
	 * 
	 * @param bean
	 * @return
	 */
	public int getSameOrgtype(OrgcfgEntity bean);

	/**
	 * 根据组织机构类型查询信息
	 * 
	 * @param orgtype
	 * @return
	 */
	public OrgcfgEntity getInfoBytype(String orgtype);

}
