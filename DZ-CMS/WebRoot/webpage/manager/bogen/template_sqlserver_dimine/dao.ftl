package com.dimine.${modelPath ?lower_case}.dao;

import java.util.List;
import java.util.Map;
import com.dimine.base.dao.BaseDao;
import com.dimine.base.dao.BizDao;

/**
 * ${title} Mapper
 * 
 * @author dimine ${sysdate}
 * 
 */
@BizDao
public interface ${actionName ?cap_first}Dao<T> extends BaseDao<T> {
	
}
