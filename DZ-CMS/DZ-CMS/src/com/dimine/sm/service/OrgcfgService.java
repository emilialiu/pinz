package com.dimine.sm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dimine.base.common.ValidException;
import com.dimine.base.service.BaseService;
import com.dimine.base.util.KeyUtils;
import com.dimine.sm.dao.OrgcfgDao;
import com.dimine.sm.entity.OrgcfgEntity;

/**
 * 配置组织机构类型是属于组织机构还是部门。管理事务处理
 * 
 * @author dimine 2015-06-23 11:20:50
 * 
 */
@Service("orgcfgService")
public class OrgcfgService<T> extends BaseService<T> {

	@Autowired
	private OrgcfgDao<T> dao;

	public OrgcfgDao<T> getDao() {
		return dao;
	}

	/**
	 * 添加新的配置组织机构类型是属于组织机构还是部门。
	 * 
	 * @param bean
	 * @param actiontype
	 * @param user
	 */
	public void insert(OrgcfgEntity bean) throws Exception {
		String keyID = KeyUtils.createKeyID();
		// 生成编号
		bean.setOrgcfgid(keyID);
		checkOrgtypeExist(bean, "addsave");
		// 插入数据
		getDao().insert((T) bean);
	}

	/**
	 * 修改配置组织机构类型是属于组织机构还是部门。信息
	 * 
	 * @param bean
	 * @param actiontype
	 */
	public void update(OrgcfgEntity bean, String actiontype) throws Exception {
		checkOrgtypeExist(bean, "modifysave");
		// 执行修改操作
		getDao().update((T) bean);
	}

	/**
	 * 删除配置组织机构类型是属于组织机构还是部门。信息
	 * 
	 * @param bean
	 * 
	 */
	public void delete(OrgcfgEntity bean) {
		// 删除配置组织机构类型是属于组织机构还是部门。
		getDao().delete((T) bean);
	}

	// 验证唯一性同一个组织机构类型不能重复提交
	public void checkOrgtypeExist(OrgcfgEntity bean, String actiontype)
			throws ValidException {
		int count = dao.getSameOrgtype(bean);
		if (count > 0 && "addsave".equals(actiontype))
			throw new ValidException((new StringBuilder("当前组织机构类型【"))
					.append(bean.getOrgtype()).append("】已经存在!").toString());
		if (count == 1 && "modifysave".equals(actiontype))
			throw new ValidException((new StringBuilder("当前组织机构类型【"))
					.append(bean.getOrgtype()).append("】已经存在!").toString());
		else
			return;
	}

	public String getBiztypename() {
		return "组织机构类型配置管理";
	}

	/**
	 * 根据组织机构类型查询信息
	 * 
	 * @param orgtype
	 * @return
	 */
	public OrgcfgEntity getInfoBytype(String orgtype) {
		return getDao().getInfoBytype(orgtype);
	}

}
