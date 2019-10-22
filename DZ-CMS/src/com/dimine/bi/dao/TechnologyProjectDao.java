package com.dimine.bi.dao;

import java.util.List;

import com.dimine.base.dao.BaseDao;
import com.dimine.base.dao.BizDao;
import com.dimine.bi.entity.ProjectinfoEntity;
import com.dimine.bi.entity.TechnologyProjectEntity;

/**
 * 作业地点 Mapper
 * 
 * @author dimine 2016-08-23 09:45:12
 * 
 */
@BizDao
public interface TechnologyProjectDao<T> extends BaseDao<T> {

	public List<ProjectinfoEntity> getAllList(ProjectinfoEntity bean);

	// 修改工程单元名称
	public void updateProName(ProjectinfoEntity bean);

	/**
	 * 已分配作业地点列表
	 * 
	 * @param bean
	 * @return
	 */
	public List<ProjectinfoEntity> yiFenPei(ProjectinfoEntity bean);

	/**
	 * 未分配作业地点列表
	 * 
	 * @param bean
	 * @return
	 */
	public List<ProjectinfoEntity> weiFenPei(ProjectinfoEntity bean);

	/**
	 * 判断作业地点是否已分配
	 * 
	 * @param technologyProject
	 * @return
	 */
	public int isFenPei(TechnologyProjectEntity technologyProject);

	/**
	 * 作业地点撤销 找叶子节点的兄弟节点
	 * 
	 * @param technologyProject
	 * @return
	 */
	public List<TechnologyProjectEntity> yiFenPeiErJi(
			TechnologyProjectEntity technologyProject);

	/**
	 * 作业地点撤销 根据子节点找父节点
	 * 
	 * @param technologyProject
	 * @return
	 */
	public TechnologyProjectEntity fuJieDian(
			TechnologyProjectEntity technologyProject);

	/**
	 * 查询子节点（不在工区对应工程信息表里面的）
	 * 
	 * @param technologyProject
	 * @return
	 */
	public List<ProjectinfoEntity> getChildList(
			TechnologyProjectEntity technologyProject);

	/**
	 * 根据父节点删除子节点
	 * 
	 * @param bean
	 */
	public void deleteByPrtid(TechnologyProjectEntity bean);

	/**
	 * 根据父节点添加子节点
	 * 
	 * @param bean
	 */
	public void insertByPrtid(TechnologyProjectEntity bean);

	/**
	 * 根据工程id查询信息
	 * 
	 * @param bean
	 */
	public TechnologyProjectEntity selectByProjectid(String projectid);
}
