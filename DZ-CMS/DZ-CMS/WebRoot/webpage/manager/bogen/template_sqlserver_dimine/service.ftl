package com.dimine.${modelPath ?lower_case}.service;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dimine.base.common.ValidException;
import com.dimine.base.service.BaseService;
import com.dimine.base.util.KeyUtils;
import com.dimine.${modelPath ?lower_case}.dao.${actionName ?cap_first}Dao;
import com.dimine.${modelPath ?lower_case}.entity.${actionName ?cap_first}Entity;

/**
 * ${title}管理事务处理
 * 
 * @author dimine ${sysdate}
 * 
 */
@Service("${actionName ?lower_case}Service")
public class ${actionName ?cap_first}Service<T> extends BaseService<T> {

	@Autowired
	private ${actionName ?cap_first}Dao<T> dao;

	public ${actionName ?cap_first}Dao<T> getDao() {
		return dao;
	}

	/**
	 * 添加新的${title}
	 * 
	 * @param bean
	 * @param actiontype
	 * @param user
	 */
	public void insert(${actionName ?cap_first}Entity bean) throws Exception {
		String keyID = KeyUtils.createKeyID();
		// 生成编号
		<#assign n = 0 />
		<#list primaryKey as primaryKey>
		<#assign n = n+1 />
		<#if n <= 1>		
		bean.set${primaryKey ?cap_first}(keyID);
		</#if>
		</#list>
		// 插入数据
		getDao().insert((T) bean);
	}

	/**
	 * 修改${title}信息
	 * 
	 * @param bean
	 * @param actiontype
	 */
	public void update(${actionName ?cap_first}Entity bean, String actiontype) throws Exception {
		// 执行修改操作
		getDao().update((T) bean);
	}

	/**
	 * 删除${title}信息
	 * 
	 * @param bean
	 * 
	 */
	public void delete(${actionName ?cap_first}Entity bean) {
		// 删除${title}
		getDao().delete((T) bean);
	}
	
	public String getBiztypename() {
		return "${title}信息管理";
	}

}
