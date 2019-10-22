package com.dimine.activity.dao;
import com.dimine.activity.entity.LeaveEntity;
import com.dimine.base.dao.BaseDao;
import com.dimine.base.dao.BizDao;

/**
 * 请假实体管理接口
 *
 * @author HenryYan
 */
@BizDao
public interface LeaveDao<T> extends BaseDao<T>  {
	public LeaveEntity getLeave(String businessKey);
}
