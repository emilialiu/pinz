package com.dimine.taglib.dao;

import java.util.List;
import java.util.Map;

import com.dimine.base.dao.BaseDao;
import com.dimine.base.dao.BizDao;
import com.dimine.taglib.entity.RadioEntity;
@BizDao
public interface RadioDao<T> extends BaseDao<T> {

	public List<Map> selectradiolist(RadioEntity bean);
}
