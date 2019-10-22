package com.dimine.activity.dao;
import com.dimine.activity.entity.FeeborrowEntity;
import com.dimine.base.dao.BaseDao;
import com.dimine.base.dao.BizDao;
/**
 * 借支管理dao
 * 
 * @author 申生明
 * 
 */
@BizDao
public interface FeeborrowDao<T> extends BaseDao<T> {
	public void updatestate(FeeborrowEntity bean) throws Exception;

}
