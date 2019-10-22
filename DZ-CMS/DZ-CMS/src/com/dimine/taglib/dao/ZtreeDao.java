package com.dimine.taglib.dao;

import java.util.List;
import java.util.Map;

import com.dimine.base.dao.BaseDao;
import com.dimine.base.dao.BizDao;
import com.dimine.taglib.entity.ZtreeEntity;

@BizDao
public interface ZtreeDao<T> extends BaseDao<T> {

	public List<Map> selectztreelist(ZtreeEntity bean);
	
	public List<Map> updatedrop(ZtreeEntity bean);
	
	public List<Map> selectztreehead(ZtreeEntity bean);
}
