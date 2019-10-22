package com.dimine.shortcut.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dimine.base.service.BaseService;
import com.dimine.shortcut.dao.Sys_shortcut_funcDao;
import com.dimine.shortcut.entity.Sys_shortcut_funcEntity;

/**
 * 人员快捷功能管理事务处理
 * 
 * @author dimine 2016-09-09 17:39:35
 * 
 */
@Service("sys_shortcut_funcService")
public class Sys_shortcut_funcService<T> extends BaseService<T> {

	@Autowired
	private Sys_shortcut_funcDao<T> dao;

	public Sys_shortcut_funcDao<T> getDao() {
		return dao;
	}

	/**
	 * 添加新的人员快捷功能
	 * 
	 * @param bean
	 * @param actiontype
	 * @param user
	 */
	public void insert(Sys_shortcut_funcEntity bean) throws Exception {
		/*String keyID = KeyUtils.createKeyID();
		// 生成编号
		bean.setUserid(keyID);
		// 插入数据
*/		getDao().insert((T) bean);
	}

	/**
	 * 修改人员快捷功能信息
	 * 
	 * @param bean
	 * @param actiontype
	 */
	public void update(Sys_shortcut_funcEntity bean, String actiontype) throws Exception {
		// 执行修改操作
		getDao().update((T) bean);
	}

	/**
	 * 删除人员快捷功能信息
	 * 
	 * @param bean
	 * 
	 */
	public void delete(Sys_shortcut_funcEntity bean) {
		// 删除人员快捷功能
		getDao().delete((T) bean);
	}
	public List<Sys_shortcut_funcEntity> selectList(Sys_shortcut_funcEntity bean){
		return getDao().selectList(bean);
	}
	
	public int selectMaxOrderno(Sys_shortcut_funcEntity entity){
		return getDao().selectMaxOrderno(entity);
	}
	public int selectCount(){
		return getDao().selectCount();
	}
	public String getBiztypename() {
		return "人员快捷功能信息管理";
	}

}
