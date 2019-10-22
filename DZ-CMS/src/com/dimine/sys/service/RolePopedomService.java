package com.dimine.sys.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dimine.base.service.BaseService;
import com.dimine.sys.dao.RoleDao;
import com.dimine.sys.dao.RolePopedomDao;
import com.dimine.sys.entity.FuncEntity;
import com.dimine.sys.entity.RoleEntity;
import com.dimine.sys.entity.RolePopedomEntity;

@Service("rolePopedomService")
public class RolePopedomService<T> extends BaseService<T> {

	@Autowired
	private RolePopedomDao<T> dao;

	public RolePopedomDao<T> getDao() {
		return dao;
	}

	@Autowired
	private RoleDao<T> roleDao;

	public RoleDao<T> getRoleDao() {
		return roleDao;
	}

	/**
	 * 已授予功能列表
	 * 
	 * @return
	 */
	public List<FuncEntity> yiShouYu(RolePopedomEntity bean) {
		return dao.yiShouYu(bean);
	}

	/**
	 * 未授予功能列表
	 * 
	 * @return
	 */
	public List<FuncEntity> weiShouYu(Map<String, String> map) {
		return dao.weiShouYu(map);
	}
	/**
	 * 快捷功能中未授予功能列表
	 * 
	 * @param map
	 * @return
	 */
	public List<FuncEntity> weiShouYuForshortcut(Map<String, String> map) {
		return dao.weiShouYuForshortcut(map);
	}

	/**
	 * <给角色授予功能> <功能详细描述>
	 * 
	 * @param funccodes
	 * @param bean
	 * @see [类、类#方法、类#成员]
	 */
	public void insert(String[] funccodes, RolePopedomEntity bean) {
		for (String funccode : funccodes) {
			bean.setFunccode(funccode);
			List list = dao.yiShouYuFu(bean);
			if (list.size() > 0) {// 如果找到了父节点才添加到数据库
				FuncEntity rpe = new FuncEntity();
				for (int j = 0; j < list.size(); j++) {
					rpe = (FuncEntity) list.get(j);// 取得相关字段
					bean.setFunccode(rpe.getFunccode());
					dao.insert((T) bean);// 把父节点添加到数据库
				}
			}
		}
	}

	/**
	 * <撤销角色的功能> <功能详细描述>
	 * 
	 * @param funccodes
	 * @param bean
	 * @see [类、类#方法、类#成员]
	 */
	public void delete(String[] funccodes, RolePopedomEntity bean) {
		// 删除对应的信息
		dealRole(bean.getRoleid(), funccodes);
		for (String funccode : funccodes) {
			bean.setFunccode(funccode);
			dao.delete((T) bean);// 删除选中孩子节点
			List listbrother = dao.yiShouYuErJi(bean);// 找叶子节点的兄弟节点
			if (listbrother.size() == 0) {// 如果兄弟节点为零 则删除父节点
				FuncEntity rpe = dao.fuJieDian(bean);// 根据子节点找父节点
				// 如果为空
				if (rpe == null) {
					break;
				}
				bean.setFunccode(rpe.getFunccode());
				dao.delete((T) bean);// 删除选中节点的父节点
				List listfather = dao.yiShouYuErJi(bean);// 找父节点的兄弟节点
				if (listfather.size() == 0) {// 如果父节点的兄弟节点为0
												// 则删除祖先节点
					FuncEntity rpefunc1 = dao.fuJieDian(bean);// 根据父节点找祖先节点
					bean.setFunccode(rpefunc1.getFunccode());
					dao.delete((T) bean);// 删除父祖先节点
					List listGrandfather = dao.yiShouYuErJi(bean);// 找祖先节点的兄弟节点
					if (listGrandfather.size() == 0) {// 如果祖先节点的兄弟节点为0
														// 则删除父祖先节点
						FuncEntity rpefunc = dao.fuJieDian(bean);// 根据祖先节点找曾祖先节点
						if (rpefunc != null)
							bean.setFunccode(rpefunc.getFunccode());
						dao.delete((T) bean);// 删除曾祖先节点
					}
				}
			}

		}
	}

	/**
	 * 用递归的方式删除 <功能详细描述>
	 * 
	 * @param roleid
	 * @param funcs
	 * @see [类、类#方法、类#成员]
	 */
	public void dealRole(String roleid, String[] funcs) {
		// 获得角色信息
		RoleEntity roleTmp = (RoleEntity) roleDao.selectById(roleid);
		// 获得该角色所在的机构下所有的子机构所创建的角色
		List<String> roleList = roleDao.allRoleByDeptid(roleTmp.getDeptid());
		// 删除角色与功能的关系
		int length = funcs.length;
		for (int i = 0; i < length; i++) {
			for (String tmproleid : roleList) {
				RolePopedomEntity tmp = new RolePopedomEntity();
				tmp.setFunccode(funcs[i]);
				tmp.setRoleid(tmproleid);
				dao.delete((T) tmp);
			}
		}
	}

	public String getBiztypename() {
		// TODO Auto-generated method stub
		return "角色功能信息管理";
	}

}
