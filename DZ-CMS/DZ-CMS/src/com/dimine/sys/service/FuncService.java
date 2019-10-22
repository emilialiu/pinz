package com.dimine.sys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dimine.base.dao.BaseDao;
import com.dimine.base.service.BaseService;
import com.dimine.base.util.KeyUtils;
import com.dimine.sys.dao.FuncDao;
import com.dimine.sys.dao.RolePopedomDao;
import com.dimine.sys.entity.DictEntity;
import com.dimine.sys.entity.FuncEntity;
import com.dimine.sys.entity.RolePopedomEntity;
import com.dimine.sys.entity.ShortcutEntity;
import com.dimine.sys.util.PublicUtil;

/**
 * 
 * 功能逻辑信息维护 <功能详细描述>
 * 
 * @author LCF
 * @version [版本号, 2014-07-25]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Service("funcService")
public class FuncService<T> extends BaseService<T> {
	@Autowired
	private FuncDao<T> dao;

	public BaseDao<T> getDao() {
		return dao;
	}

	@Autowired
	private RolePopedomDao<T> rolePopedomDao;

	public RolePopedomDao<T> getRolePopedomDao() {
		return rolePopedomDao;
	}

	public List<DictEntity> getSelectTblx(String jb){
		return dao.getSelectTblx(jb);
	}
	/**
	 * 取得树形数据列表
	 * 
	 * @param funccode
	 * @return
	 */
	public List<FuncEntity> tree(String funccode) {
		return dao.tree(funccode);
	}

	/**
	 * <验证功能名称是否已经存在> <功能详细描述>
	 * 
	 * @param func
	 * @param actiontype
	 * @throws AlarmException
	 * @see [类、类#方法、类#成员]
	 */
	public void funcExist(FuncEntity func, String actiontype) throws Exception {
		int count = dao.countByName(func);
		if (count != 0 && "addsave".equals(actiontype)) {
			throw new Exception("此功能名称已经存在");
		}
	}

	/**
	 * 添加功能信息 <功能详细描述>
	 * 
	 * @param func
	 * @throws AlarmException
	 * @see [类、类#方法、类#成员]
	 */
	public String insert(FuncEntity func) throws Exception {
		// funcExist(func, "addsave");
		// 添加新的功能信息
		String funcCode = KeyUtils.createKeyID();
		func.setFunccode(funcCode);
		dao.insert((T) func);
		// 将新添加的功能赋给超级管理员的角色
		RolePopedomEntity rp = new RolePopedomEntity();
		rp.setFunccode(funcCode);
		rp.setRoleid(PublicUtil.ADMIN_ROLE_ID);
		rolePopedomDao.insert((T) rp);

		return funcCode;
	}

	/**
	 * 修改功能信息 <功能详细描述>
	 * 
	 * @param func
	 * @see [类、类#方法、类#成员]
	 */
	public void update(FuncEntity func) {
		dao.update((T) func);
	}

	/**
	 * 删除功能 <功能详细描述>
	 * 
	 * @param funccode
	 *            功能编号数组
	 * @throws
	 * @see [类、类#方法、类#成员]
	 */
	public void delete(String funccode) throws Exception {
		// 这里是可以用循环的
		// for (String funccode : funcInfo)
		// {
		FuncEntity func = new FuncEntity();
		func.setFunccode(funccode);
		// 看看功能是否有子功能
		int count = dao.listByChild(func);
		// 如果有子节点，则必须先把子节点全部删除之后才能够删除
		if (count != 0) {
			throw new Exception("该功能有子功能，必须先把子节点全部删除之后才能够删除！");
		}
		// 删除功能与角色的关系
		dao.deleteForRole(func);
		// 删除子功能
		dao.delete((T) funccode); // 删除成功
		// }
	}

	/**
	 * 通过快捷键查找Fun<功能详细描述>
	 * 
	 * @param funccode
	 * @throws
	 * @see [类、类#方法、类#成员]
	 */
	public List<FuncEntity> listShortcutByFunc(String userid) throws Exception {
		ShortcutEntity shortcut = new ShortcutEntity();
		shortcut.setOwnerid(userid);
		return dao.listShortcutByFunc(shortcut);
	}
	/**
	 * 插入快捷键 <功能详细描述>
	 * 
	 * @param funccode
	 * @throws
	 * @see [类、类#方法、类#成员]
	 */
	public void insertShortcut(String funccode,int orderno,String userid) throws Exception {
		FuncEntity func = dao.searchByFuncEntity(funccode);
		ShortcutEntity shortcut = new ShortcutEntity();
		shortcut.setFunccode(func.getFunccode());
		shortcut.setFuncname(func.getFuncname());
		shortcut.setOrderno(orderno+"");
		shortcut.setOwnerid(userid);
		dao.insertShortcut(shortcut);
	}
	public void deleteShortcut(String userid) throws Exception {
		ShortcutEntity shortcut = new ShortcutEntity();
		shortcut.setOwnerid(userid);
		dao.deleteAllShortcut(shortcut);
	}
	/**
	 * 根据父功能code查找所有子功能
	 * 
	 * @param func
	 * @return
	 */
	public List<FuncEntity> listByParent(FuncEntity func) {
		return dao.listByParent(func);
	}
	
	public List<FuncEntity> listByParentTop(FuncEntity func) {
		return dao.listByParentTop(func);
	}
	public List<FuncEntity> listByParentSec(FuncEntity func) {
		return dao.listByParentSec(func);
	}
	public List<FuncEntity> listByParentTopShortCut(FuncEntity func) {
		return dao.listByParentTopShortCut(func);
	}
	public List<FuncEntity> listByParentSecShortCut(FuncEntity func) {
		return dao.listByParentSecShortCut(func);
	}
	
	public List<ShortcutEntity> listByShortCut(String userid) {
		ShortcutEntity shortcut = new ShortcutEntity();
		shortcut.setOwnerid(userid);
		return dao.listByShortCut(shortcut);
	}
	/**
	 * 根据角色查找满足条件的数据
	 * 
	 * @param func
	 * @return
	 */
	public List<FuncEntity> searchByRoleid(FuncEntity func) {
		return dao.searchByRoleid(func);
	}

	public String getBiztypename() {
		return "系统功能信息管理";
	}
}
