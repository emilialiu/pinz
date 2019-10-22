package com.dimine.bi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dimine.base.service.BaseService;
import com.dimine.base.util.KeyUtils;
import com.dimine.base.util.StringUtils;
import com.dimine.bi.dao.TechnologyProjectDao;
import com.dimine.bi.entity.ProjectinfoEntity;
import com.dimine.bi.entity.TechnologyProjectEntity;

/**
 * 工艺对应工程信息管理事务处理
 * 
 * @author dimine 2016-08-23 10:30:24
 * 
 */
@Service("technologyProjectService")
public class TechnologyProjectService<T> extends BaseService<T> {

	@Autowired
	private TechnologyProjectDao<T> dao;

	public TechnologyProjectDao<T> getDao() {
		return dao;
	}

	public String getBiztypename() {
		return "作业地点信息管理";
	}

	/**
	 * 已分配作业地点列表
	 * 
	 * @param bean
	 * @return
	 */
	public List<ProjectinfoEntity> yiFenPei(ProjectinfoEntity bean) {
		return dao.yiFenPei(bean);
	}

	/**
	 * 未分配作业地点列表
	 * 
	 * @param bean
	 * @return
	 */
	public List<ProjectinfoEntity> weiFenPei(ProjectinfoEntity bean) {
		List<ProjectinfoEntity> temp = dao.weiFenPei(bean);
		// List<ProjectinfoEntity> list = new ArrayList<ProjectinfoEntity>();
		// AreaProjectEntity areaProject = new AreaProjectEntity();
		// for (ProjectinfoEntity project : temp) {
		// if ("1".equals(project.getIsleaf())) {
		// areaProject.setProjectid(project.getProjectid());
		// int count = dao.isFenPei(areaProject);
		// if (count == 0) {// 如果未分配,则添加到list中
		// list.add(project);
		// }
		// } else {
		// list.add(project);
		// }
		// }
		// return list;
		return temp;
	}

	/**
	 * 作业地点分配
	 * 
	 * @param projectids
	 *            作业地点id
	 * @param pids
	 *            没有选择子节点的中段或者分段id
	 * @param areaProject
	 */
	public void addAreaProject(String[] projectids, String[] pids,
			TechnologyProjectEntity technologyProject) {
		// 先检查中段或者分段下是否有可分配的子节点
		for (String projectid : pids) {
			if (StringUtils.isNotBlank(projectid)) {
				TechnologyProjectEntity temp = new TechnologyProjectEntity();
				temp.setTdeptid(technologyProject.getTdeptid());
				temp.setProjectid(projectid);
				List<ProjectinfoEntity> list = dao.getChildList(temp);
				if (list == null || list.isEmpty()) {
					return;
				}
			}
		}
		for (String projectid : projectids) {
			technologyProject.setProjectid(projectid);
			int count = dao.isFenPei(technologyProject);
			if (count == 0) {// 如果未找到记录则添加到数据库
				technologyProject.setApid(KeyUtils.createKeyID());
				dao.insert((T) technologyProject);// 把作业地点添加到数据库
				dao.insertByPrtid(technologyProject);// 把作业地点下的安装信息添加到数据库
			}
		}
		// 以下是将没有选择子节点的中段或者分段下的所有节点分配过去
		for (String projectid : pids) {
			TechnologyProjectEntity temp = new TechnologyProjectEntity();
			TechnologyProjectEntity temp2 = new TechnologyProjectEntity();
			TechnologyProjectEntity temp3 = new TechnologyProjectEntity();
			temp.setTdeptid(technologyProject.getTdeptid());
			// temp.setDeptid(areaProject.getDeptid());
			temp2.setTdeptid(technologyProject.getTdeptid());
			// temp2.setDeptid(areaProject.getDeptid());
			temp3.setTdeptid(technologyProject.getTdeptid());
			// temp3.setDeptid(areaProject.getDeptid());
			temp.setProjectid(projectid);
			List<ProjectinfoEntity> list = dao.getChildList(temp);

			for (ProjectinfoEntity project : list) {
				if ("0".equals(project.getIsleaf())) {// 非叶子节点
					temp.setProjectid(project.getProjectid());
					temp.setTechid(technologyProject.getTechid());
					temp.setApid(KeyUtils.createKeyID());
					dao.insert((T) temp);// 把作业地点添加到数据库

					temp2.setProjectid(project.getProjectid());
					List<ProjectinfoEntity> list2 = dao.getChildList(temp2);
					for (ProjectinfoEntity project2 : list2) {
						temp2.setProjectid(project2.getProjectid());
						int count = dao.isFenPei(temp2);
						if (count == 0) {// 如果未找到记录则添加到数据库
							temp2.setApid(KeyUtils.createKeyID());
							temp2.setTechid(technologyProject.getTechid());
							dao.insert((T) temp2);// 把作业地点添加到数据库
							dao.insertByPrtid(temp2);// 把作业地点下的安装信息添加到数据库
						}
					}
				} else {// 叶子节点
					temp.setProjectid(project.getProjectid());
					int count = dao.isFenPei(temp);
					if (count == 0) {// 如果未找到记录则添加到数据库
						temp.setApid(KeyUtils.createKeyID());
						temp.setTechid(technologyProject.getTechid());
						dao.insert((T) temp);// 把作业地点添加到数据库
						dao.insertByPrtid(temp);// 把作业地点下的安装信息添加到数据库
					}
				}

			}

		}
	}

	/**
	 * 作业地点撤销
	 * 
	 * @param projectids
	 * @param areaProject
	 */
	public void delAreaProject(String[] projectids,
			TechnologyProjectEntity areaProject) {
		for (String projectid : projectids) {
			areaProject.setProjectid(projectid);
			dao.delete((T) areaProject);// 删除选中孩子节点
			dao.deleteByPrtid(areaProject);// 删除作业地点下的安装信息
			List listbrother = dao.yiFenPeiErJi(areaProject);// 找叶子节点的兄弟节点
			if (listbrother.size() == 0) {// 如果兄弟节点为零 则删除父节点
				TechnologyProjectEntity rpe = dao.fuJieDian(areaProject);// 根据子节点找父节点
				// 如果为空
				if (rpe == null) {
					break;
				}
				areaProject.setProjectid(rpe.getProjectid());
				dao.delete((T) areaProject);// 删除选中节点的父节点
				List listfather = dao.yiFenPeiErJi(areaProject);// 找父节点的兄弟节点
				if (listfather.size() == 0) {// 如果父节点的兄弟节点为0 则删除祖先节点
					TechnologyProjectEntity rpefunc1 = dao
							.fuJieDian(areaProject);// 根据父节点找祖先节点
					// 如果为空
					if (rpefunc1 == null) {
						break;
					}
					areaProject.setProjectid(rpefunc1.getProjectid());
					dao.delete((T) areaProject);// 删除父祖先节点
					List listGrandfather = dao.yiFenPeiErJi(areaProject);// 找祖先节点的兄弟节点
					if (listGrandfather.size() == 0) {// 如果祖先节点的兄弟节点为0 则删除父祖先节点
						TechnologyProjectEntity rpefunc = dao
								.fuJieDian(areaProject);// 根据祖先节点找曾祖先节点
						if (rpefunc != null) {
							areaProject.setProjectid(rpefunc.getProjectid());
							dao.delete((T) areaProject);// 删除曾祖先节点
						}
					}
				}
			}
		}
	}

	/**
	 * 删除工区对应工程信息
	 * 
	 * @param bean
	 */
	public void delete(TechnologyProjectEntity bean) {
		getDao().delete((T) bean);
		dao.deleteByPrtid(bean);// 删除作业地点下的安装信息
	}

}
