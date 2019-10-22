package com.dimine.sys.dao;

import java.util.List;
import java.util.Map;

import com.dimine.base.dao.BaseDao;
import com.dimine.base.dao.BizDao;
import com.dimine.sys.entity.DeptEntity;

@BizDao
public interface DeptDao<T> extends BaseDao<T> {
	public List<DeptEntity> getAllList(String parentdeptid);
	public List<DeptEntity> getAllList1(String parentdeptid);
	public List<DeptEntity> getAllList2(String parentdeptid);
	//根据orgtype查询公司的deptid
	public DeptEntity getOrgtype(String orgtype);
	/**
	 * 根据登录人orgid查询项目部名称
	 * @param deptid
	 * @return
	 */
	public List<DeptEntity> selectdeptname(String deptid);
	/**
	 * 获取单个的部门信息
	 * 
	 * @param parentdeptid
	 * @return
	 */
	public DeptEntity getDept(String parentdeptid);

	/**
	 * 获取是否相同的节点数字
	 * 
	 * @param dep
	 * @return
	 */
	public int getSamenode(DeptEntity dep);

	/**
	 * 部门编码是否存在
	 * 
	 * @param dept
	 * @return
	 */
	public int getCodeexist(DeptEntity dept);

	/**
	 * 查询指定节点的所有上级节点为组织的节点
	 * 
	 * @param deptid
	 * @return
	 */
	public String getOrgID(String deptid);

	/**
	 * 统计指定部门的子部门树
	 * 
	 * @param id
	 * @return
	 */
	public int getcountChild(String id);

	/**
	 * 删除用户
	 * 
	 * @param id
	 */
	public void deleteUser(String id);

	/**
	 * 删除部门
	 * 
	 * @param id
	 */
	public void deleteDept(String id);

	/**
	 * 查询指定节点的所有下级节点为不是组织的节点
	 * 
	 * @param id
	 * @return
	 */
	public List isNotorgchild(String id);

	/**
	 * 修改所有指定节点及其子节点的组织ID
	 * 
	 * @param map
	 */
	public void Updateorgidchilds(Map map);

	/**
	 * 根据用户查询组织机构
	 * 
	 * @param user
	 */
	public String getDeptByUser(String user);

	/**
	 * 修改部门主管人
	 * 
	 * @param dept
	 */
	public void updateManager(DeptEntity dept);

	public String getDeptnameByUser(String user);

	public List<DeptEntity> getDeptByParentid(String parentid);

	public List<DeptEntity> getGgByParentDeptId(String parentDeptId);

	/**
	 * 获取部门信息 三维软件使用
	 * 
	 * @param map
	 * @return
	 */
	public List<DeptEntity> getDeptFor3D(Map map);

		
}
