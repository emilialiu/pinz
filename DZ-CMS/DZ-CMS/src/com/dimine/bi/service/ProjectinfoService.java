package com.dimine.bi.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dimine.base.service.BaseService;
import com.dimine.base.util.KeyUtils;
import com.dimine.bi.dao.ProjectinfoDao;
import com.dimine.bi.entity.ProjectinfoEntity;

/**
 * 作业地点管理事务处理
 * 
 * @author dimine 2015-08-26 17:26:56
 * 
 */
@Service("projectinfoService")
public class ProjectinfoService<T> extends BaseService<T> {

	@Autowired
	private ProjectinfoDao<T> dao;

	public ProjectinfoDao<T> getDao() {
		return dao;
	}

	/**
	 * 添加新的作业地点
	 * 
	 * @param bean
	 * @param actiontype
	 * @param user
	 * @return
	 */
	public String insert(ProjectinfoEntity bean) throws Exception {
		projectExist(bean, "addsave");
		String keyID = KeyUtils.createKeyID();
		// 生成编号
		bean.setProjectid(keyID);
		// 插入数据
		getDao().insert((T) bean);

		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("deptid", bean.getDeptid());
		getDao().updateAparentItemID(params);
		return keyID;
	}

	/**
	 * 修改作业地点信息
	 * 
	 * @param bean
	 * @param actiontype
	 */
	public void update(ProjectinfoEntity bean, String actiontype)
			throws Exception {
		projectExist(bean, "modifysave");
		// 执行修改操作
		getDao().update((T) bean);
		// 更新工程信息父节点ID
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("deptid", bean.getDeptid());
		getDao().updateAparentItemID(params);
	}

	/**
	 * 删除作业地点信息-- 逻辑删除
	 * 
	 * @param bean
	 * 
	 */
	public void deleteProject(ProjectinfoEntity bean) throws Exception {
		ProjectinfoEntity temBean = new ProjectinfoEntity();
		temBean.setParentitemid(bean.getProjectid());
		temBean.setIsdel("0");
		int count = getDao().selectByCount(temBean);
		if (count > 0) {
			throw new Exception("该工程还含有作业点信息，不能直接被删除");
		}
		// 删除作业地点
		getDao().deleteProject(bean);
	}

	/**
	 * 判断同一个父节点下是否有相同名字的子节点
	 * 
	 * @param bean
	 * 
	 */
	public void projectExist(ProjectinfoEntity bean, String actiontype)
			throws Exception {
		int count = getDao().getProjectExist(bean);
		if (count > 0 && "addsave".equals(actiontype)) {
			throw new Exception("该父节点下已存在相同名称的作业地点，请重新命名！");
		}
		if (count >= 1 && "modifysave".equals(actiontype)) {
			throw new Exception("该父节点下已存在相同名称的作业地点，请重新命名！");
		}
	}

	public String getBiztypename() {
		return "作业地点信息管理";
	}

	public List<ProjectinfoEntity> getall(ProjectinfoEntity bean) {
		List<ProjectinfoEntity> list = new ArrayList<ProjectinfoEntity>();
		try {
			list = dao.getAllList(bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<ProjectinfoEntity> getProjectinfoData(ProjectinfoEntity bean)
			throws Exception {
		return dao.getProjectinfoData(bean);
	}

	/**
	 * 更新工程信息父节点ID(aParentItemID)
	 * 
	 * @param map
	 * @throws Exception
	 */
	public void updateAparentItemID(HashMap<String, Object> map)
			throws Exception {
		dao.updateAparentItemID(map);
	}

	public List<ProjectinfoEntity> projectBySql(String sql) {
		return dao.projectBySql(sql);
	}

	public void updateIsupload(ProjectinfoEntity bean) {
		dao.updateIsupload(bean);
	}

	public List<ProjectinfoEntity> getGCJB(ProjectinfoEntity bean) {
		List<ProjectinfoEntity> list = new ArrayList<ProjectinfoEntity>();
		try {
			list = dao.getGCJB(bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	};

	public List<ProjectinfoEntity> getchildData(ProjectinfoEntity project)
			throws Exception {
		// TODO Auto-generated method stub
		return dao.getchildData(project);
	};
}
