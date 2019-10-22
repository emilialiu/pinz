package com.dimine.sm.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.dimine.base.dao.BaseDao;
import com.dimine.base.dao.BizDao;
import com.dimine.sm.entity.ElementtypeEntity;

/**
 * 要素分类表 Mapper
 * 
 * @author dimine 2015-11-09 11:23:34
 * 
 */
@BizDao
public interface ElementtypeDao<T> extends BaseDao<T> {
	//根据部门的要素类型查询是否有重复
	public List<ElementtypeEntity> selectByEletypeAndDept(ElementtypeEntity bean);
	
	public int isimportinsert(ElementtypeEntity bean);
	public void allinsert(HashMap<String,Object> args);
}
