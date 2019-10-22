package com.dimine.sys.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dimine.base.service.BaseService;
import com.dimine.base.util.KeyUtils;
import com.dimine.sm.entity.OrgcfgEntity;
import com.dimine.sm.service.OrgcfgService;
import com.dimine.sys.dao.DeptDao;
import com.dimine.sys.entity.DeptEntity;
import com.dimine.sys.entity.UserEntity;

@Service("deptService")
public class DeptService<T> extends BaseService<T> {

	@Autowired
	private DeptDao<T> dao;
	@Resource
	private RoleService<T> roleService;
	@Resource
	private OrgcfgService<T> orgcfgService;

	public DeptDao<T> getDao() {
		// TODO Auto-generated method stub
		return dao;
	}

	public List<DeptEntity> selectdeptname(String deptid) {
		List<DeptEntity> list = new ArrayList<DeptEntity>();
		try {
			list = dao.selectdeptname(deptid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public List<DeptEntity> getall(String parentdeptid) {
		List<DeptEntity> list = new ArrayList<DeptEntity>();
		try {
			list = dao.getAllList(parentdeptid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public List<DeptEntity> getall1(String parentdeptid) {
		List<DeptEntity> list = new ArrayList<DeptEntity>();
		try {
			list = dao.getAllList1(parentdeptid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public List<DeptEntity> getall2(String parentdeptid) {
		List<DeptEntity> list = new ArrayList<DeptEntity>();
		try {
			list = dao.getAllList2(parentdeptid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 根据orgtype查询公司的deptid
	 * 
	 * @param orgtype
	 * @return
	 */
	public DeptEntity getOrgtype(String orgtype){
		return dao.getOrgtype(orgtype);
	}

	/**
	 * 获取部门信息
	 * 
	 * @param deptid
	 *            部门id
	 * @return
	 */
	public DeptEntity getDept(String deptid) {
		return dao.getDept(deptid);
	}

	public String getDeptByUser(String user) {
		return dao.getDeptByUser(user);
	}

	public String insert(DeptEntity dept, UserEntity user) throws Exception {
		deptExist(dept, "addsave");
		dept.setCreateman(user.getUserid());
		String deptID = KeyUtils.createKeyID();
		OrgcfgEntity orgcfg = orgcfgService.getInfoBytype(dept.getOrgtype());
		if (!"1".equals(orgcfg.getIsorg())) {// 如果当前节点不是组织,则取父节点的组织机构id
			String orgID = getOrgID(dept.getParentdeptid());
			orgID = orgID == null ? "" : orgID;
			dept.setOrgID(orgID);
		} else {
			dept.setIsOrg("1");
			dept.setOrgID(deptID);
		}
		dept.setDeptid(deptID);
		dao.insert((T) dept);
		return deptID;
	}

	public void deptExist(DeptEntity dept, String actiontype) throws Exception {
		int code_count = dao.getCodeexist(dept);
		if (code_count > 0 && "addsave".equals(actiontype))
			throw new Exception((new StringBuilder("机构代码【"))
					.append(dept.getDeptCode()).append("】已经存在,请重新确认!")
					.toString());
		if (code_count == 1 && "modifysave".equals(actiontype))
			throw new Exception((new StringBuilder("机构代码【"))
					.append(dept.getDeptCode()).append("】已经存在,请重新确认!")
					.toString());
		int deptName_count = dao.getSamenode(dept);
		if (deptName_count > 0 && "addsave".equals(actiontype))
			throw new Exception((new StringBuilder("机构名【"))
					.append(dept.getDeptname()).append("】已经存在,请重新确认!")
					.toString());
		if (deptName_count == 1 && "modifysave".equals(actiontype))
			throw new Exception((new StringBuilder("机构名【"))
					.append(dept.getDeptname()).append("】已经存在,请重新确认!")
					.toString());
		else
			return;
	}

	public void delete(String deptID) throws Exception {
		if ("1000".equals(deptID))
			throw new Exception("对不起，您不能够删除此根节点!");
		int dept_count = dao.getcountChild(deptID);
		if (dept_count != 0) {
			throw new Exception("对不起，您删除的部门信息含有子部门，所以该部门不能够删除。");
		} else {
			// 删除用户
			dao.deleteUser(deptID);
			dao.deleteDept(deptID);
			// 删除用户
			roleService.depeteByDeptID(deptID);
			return;
		}
	}

	public void update(DeptEntity dept, String userid) throws Exception,
			Exception {
		if ("1000".equals(dept.getDeptid())) {
			dept.setParentdeptid(null);
			dept.setDeptCode("1000");
		}
		deptExist(dept, "modifysave");
		dept.setModifyman(userid);
		dept.setModifydate(new Date());
		String orgID = dept.getDeptid();
		OrgcfgEntity orgcfg = orgcfgService.getInfoBytype(dept.getOrgtype());
		if (!"1".equals(orgcfg.getIsorg())) { // 如果当前节点不是组织,则取父节点的组织机构id
			orgID = getOrgID(dept.getParentdeptid());
			orgID = orgID == null ? "" : orgID;
			dept.setOrgID(orgID);
		} else {
			dept.setIsOrg("1");
			dept.setOrgID(orgID);
		}
		dao.update((T) dept);
		List<String> deptIDList = dao.isNotorgchild(dept.getDeptid());
		Map paramMap = new HashMap();
		paramMap.put("orgID", orgID);
		for (String deptID : deptIDList) {
			paramMap.put("deptid", deptID);
			dao.Updateorgidchilds(paramMap);
		}
		paramMap.put("orgID", orgID);
		paramMap.put("deptid", dept.getDeptid());
		roleService.Updateorg(paramMap);
	}
	
	private String getOrgID(String deptID) {
		String orgID = dao.getOrgID(deptID);
		return orgID;
	}

	public RoleService<T> getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService<T> roleService) {
		this.roleService = roleService;
	}

	/**
	 * 修改部门主管人
	 * 
	 * @param dept
	 */
	public void updateManager(DeptEntity dept) {
		dao.updateManager(dept);
	}

	public List<DeptEntity> getDeptByParentid(String parentid) {
		// TODO Auto-generated method stub
		return dao.getDeptByParentid(parentid);
	}

	public String getDeptnameByUser(String userid) {
		return dao.getDeptnameByUser(userid);
	}

	/**
	 * 根据项目部的deptid查询出下面的工区，(工区在项目部的下一级)
	 * */
	public List<DeptEntity> getGgByParentDeptId(String parentDeptId) {
		return dao.getGgByParentDeptId(parentDeptId);
	}

	/**
	 * 获取部门信息 三维软件使用
	 * 
	 * @param map
	 * @return
	 */
	public List<DeptEntity> getDeptFor3D(Map map) {
		List<DeptEntity> list = new ArrayList<DeptEntity>();
		try {
			list = dao.getDeptFor3D(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
