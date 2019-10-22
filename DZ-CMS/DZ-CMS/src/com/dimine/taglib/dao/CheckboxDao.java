package com.dimine.taglib.dao;

import java.util.List;
import java.util.Map;

import com.dimine.base.dao.BaseDao;
import com.dimine.base.dao.BizDao;
import com.dimine.taglib.entity.CheckboxEntity;
@BizDao
public interface CheckboxDao<T> extends BaseDao<T> {
	
	public List<Map> selectcheckboxlist(CheckboxEntity bean);
}
