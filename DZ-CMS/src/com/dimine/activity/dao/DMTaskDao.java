package com.dimine.activity.dao;

import java.util.List;

import com.dimine.activity.entity.TaskEntity;
import com.dimine.base.dao.BaseDao;
import com.dimine.base.dao.BizDao;

/**
 * 流程运行时候任务的dao
 * @author SSM
 *
 */
@BizDao
public interface DMTaskDao<T> extends BaseDao<T> {
	//查询我的办结
	public Integer selectHispartCount(TaskEntity bean);
	//查询我的办结列表
	public List<TaskEntity> selectHispartList(TaskEntity bean);
	//查询我发起的人物
	public List<TaskEntity> selectStartTaskList(TaskEntity bean);
	//查询我发起的任务个数
	public int selectStartTaskCount(TaskEntity bean);
	
}
