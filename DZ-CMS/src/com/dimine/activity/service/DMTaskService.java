package com.dimine.activity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dimine.activity.dao.DMTaskDao;
import com.dimine.activity.entity.TaskEntity;
import com.dimine.base.dao.BaseDao;
import com.dimine.base.service.BaseService;

/**
 * 任务的service
 * @author SSM
 *
 */
@Service("dmtaskService")
public class DMTaskService<T> extends BaseService<T> {
	@Autowired
	private DMTaskDao<T> dao;
	/**
	 * 查看我的已办理的事项
	 * @param bean
	 * @return
	 */
	public List<TaskEntity> selectHispartList(TaskEntity bean){
		int rowCount = getDao().selectHispartCount(bean);
		bean.getPager().setRowCount(rowCount);
		return getDao().selectHispartList(bean);
	}
	/**
	 * 查询我发起的流程
	 * @param bean
	 * @return
	 */
	public List<TaskEntity> selectStartTaskList(TaskEntity bean) {
		int rowCount = getDao().selectStartTaskCount(bean);
		bean.getPager().setRowCount(rowCount);
		return getDao().selectStartTaskList(bean);
	}
	@Override
	public DMTaskDao<T> getDao() {
		// TODO Auto-generated method stub
		return dao;
	}
	
	
}
