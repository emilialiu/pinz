package com.dimine.bi.dao;

import java.util.HashMap;
import java.util.List;

import com.dimine.base.dao.BaseDao;
import com.dimine.base.dao.BizDao;
import com.dimine.bi.entity.ProjectinfoEntity;

/**
 * 作业地点 Mapper
 * 
 * @author dimine 2015-08-26 17:26:56
 * 
 */
@BizDao
public interface ProjectinfoDao<T> extends BaseDao<T> {

	public List<ProjectinfoEntity> getAllList(ProjectinfoEntity bean);

	/**
	 * 修改工程单元名称
	 * 
	 * @param bean
	 */
	public void updateProName(ProjectinfoEntity bean);

	/**
	 * 删除作业地点信息-- 逻辑删除
	 * 
	 * @param bean
	 */
	public void deleteProject(ProjectinfoEntity bean);

	/**
	 * 更新工程信息父节点ID(aParentItemID)
	 * 
	 * @param map
	 * @throws Exception
	 */
	public void updateAparentItemID(HashMap<String, Object> map)
			throws Exception;

	public List<ProjectinfoEntity> projectBySql(String sql);

	public List<ProjectinfoEntity> getProjectinfoData(ProjectinfoEntity bean)
			throws Exception;

	public void updateIsupload(ProjectinfoEntity bean);

	public List<ProjectinfoEntity> getGCJB(ProjectinfoEntity bean);

	/**
	 * 判断是否有相同名称的作业地点
	 * 
	 * @param bean
	 * @return
	 */
	public int getProjectExist(ProjectinfoEntity bean);

	public List<ProjectinfoEntity> getchildData(ProjectinfoEntity project);
}
