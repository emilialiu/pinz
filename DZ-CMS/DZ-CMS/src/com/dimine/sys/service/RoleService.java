package com.dimine.sys.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dimine.base.common.ValidException;
import com.dimine.base.service.BaseService;
import com.dimine.base.util.KeyUtils;
import com.dimine.sys.dao.RoleDao;
import com.dimine.sys.dao.RolePopedomDao;
import com.dimine.sys.entity.RoleEntity;
import com.dimine.sys.entity.RolePopedomEntity;

/**
 * 角色管理事务处理
 * 
 * @author 徐飞雄（dominic）-多米
 * 
 */
@Service("roleService")
public class RoleService<T> extends BaseService<T> {

	// 生成主键
	// KeyUtils.createKeyID();

	@Autowired
	private RoleDao<T> dao;
	@Autowired
	private RolePopedomDao<T> rolePodedomdao;

	public RoleDao<T> getDao() {
		return dao;
	}

	public RolePopedomDao<T> getRolePodedomdao() {
		return rolePodedomdao;
	}

	/**
	 * 判断新的角色名是否已经存在
	 * 
	 * @param bean
	 * @param actiontype
	 * @return
	 * @throws VaildException 
	 * @throws AlarmException
	 */
	private void roleNameIsUsed(RoleEntity bean, String actiontype)
			throws ValidException,Exception {

		Integer count = (Integer) getDao().countByIdAndName(bean);
		// 修改保存
		if (count.intValue() > 1 && "modifysave".equals(actiontype)) {
			throw new ValidException("角色名称[ " + bean.getRolename()
					+ " ]已经存在!");
		} else if (count.intValue() > 0 && "addsave".equals(actiontype)) {// 添加保存
			throw new ValidException("角色名称[ " + bean.getRolename()
					+ " ]已经存在!");
		}
	}

	/**
	 * 添加新的角色，同时给创建者添加这种角色
	 * 
	 * @param bean
	 * @param actiontype
	 * @param user
	 */
	public void insert(RoleEntity bean) throws Exception {
		// 判断该角色名称是否已经存在
		roleNameIsUsed(bean, "addsave");
		String roleID = KeyUtils.createKeyID();
		// 生成编号
		bean.setRoleid(roleID);
		// 插入数据
		getDao().insert((T) bean);
	}

	/**
	 * 修改角色信息
	 * 
	 * @param bean
	 * @param actiontype
	 */
	public void update(RoleEntity bean, String actiontype) throws Exception {
		// 判断该角色名称是否已经存在
		roleNameIsUsed(bean, actiontype);
		// 执行修改操作
		getDao().update((T) bean);
	}
	
	/**
	 * 添加新的角色，同时给创建者添加这种角色
	 * 
	 * @param bean
	 * @param actiontype
	 * @param user
	 */
	public void update(RoleEntity bean) throws Exception {
		// 判断该角色名称是否已经存在
		roleNameIsUsed(bean, "modifysave");
		// 插入数据
		getDao().update((T) bean);
	}

	/**
	 * 删除角色信息
	 * 
	 * @param roleids
	 *            类型是:String[]
	 */
	public void delete(RoleEntity bean) {
		deleteRole(bean.getRoleid());
		// 删除角色与功能之间的关系
		getDao().deleteForFunc(bean.getRoleid());
		// 删除角色与用户之间的关系
		getDao().deleteForUser(bean.getRoleid());
		// 删除角色
		getDao().delete((T) bean);
	}

	/**
	 * 用递归的方式 <功能详细描述>
	 * 
	 * @param roleid
	 * @see [类、类#方法、类#成员]
	 */
	@SuppressWarnings("unchecked")
	public void deleteRole(String roleid) {
		// 获得该角色所有的功能信息
		List<String> funcList = rolePodedomdao.funcListByRoleid(roleid);
		// 获得角色信息
		RoleEntity roleTmp = (RoleEntity) dao.selectById(roleid);
		// 获得该角色所在的机构下所有的子机构所创建的角色
		List<String> roleList = dao.allRoleByDeptid(roleTmp.getDeptid());
		// 删除角色与功能的关系
		for (String funccode : funcList) {
			for (String tmproleid : roleList) {
				RolePopedomEntity tmp = new RolePopedomEntity();
				tmp.setFunccode(funccode);
				tmp.setRoleid(tmproleid);
				rolePodedomdao.delete((T) tmp);
			}
		}
	}
	/**
	 * 删除部门角色
	 * @param deptid
	 */
	public void depeteByDeptID(String deptid) {
		List roleList = dao.getRolebydeptid(deptid);
		if (roleList.size() == 0) {
			return;
		} else {
			String roleids[] = (String[]) roleList.toArray(new String[0]);
			for(int i=0;i<roleList.size();i++){
				dao.deleteForFunc(roleList.get(i));
				dao.deleteForUser(roleList.get(i));
			}
			dao.deleteBydeptid(deptid);
			return;
		}
	}
	/**
	 * 修改角色
	 * @param map
	 */
	public void Updateorg(Map map){
		dao.Updateorg(map);
	}
	/**
	 * 根据用户名称查找角色
	 * @return
	 */
	public List<RoleEntity> findRoleByname(String userid){
		return dao.findGroupsByUserId(userid);
	}
	public String getBiztypename() {
		return "角色信息管理";
	}

}
