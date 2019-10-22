package com.dimine.taglib.dao;

import java.util.List;
import java.util.Map;

import com.dimine.base.dao.BaseDao;
import com.dimine.base.dao.BizDao;
import com.dimine.taglib.entity.AutocompleteEntity;
@BizDao
public interface AutotagDao<T> extends BaseDao<T> {
	public List<Map> selectautolist(AutocompleteEntity bean);
}
