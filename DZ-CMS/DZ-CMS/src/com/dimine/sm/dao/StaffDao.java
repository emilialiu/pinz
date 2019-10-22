package com.dimine.sm.dao;

import java.util.List;

import com.dimine.base.dao.BaseDao;
import com.dimine.base.dao.BizDao;
import com.dimine.sm.entity.StaffEntity;

/**
 * 员工信息 Mapper
 * 
 * @author dimine 2015-06-16 17:51:24
 * 
 */
@BizDao
public interface StaffDao<T> extends BaseDao<T> {
	/**
	 * 删除员工（逻辑删除）
	 * 
	 * @param staffid
	 */
	public void deleteStaff(String staffid);

	/**
	 * 取得员工编码记录数
	 * 
	 * @param bean
	 * @return
	 */
	public int getSameCode(StaffEntity bean);
	
	
	
	public List<StaffEntity> selectByList1(StaffEntity bean);
	public int selectByCount1(StaffEntity bean);
}
