package com.dimine.sys.dao;

import java.util.List;
import java.util.Map;

import com.dimine.base.dao.BaseDao;
import com.dimine.base.dao.BizDao;
import com.dimine.sys.entity.LogExceptionEntity;
import com.dimine.sys.entity.LogLoginEntity;
/**
 * 日志导出dao
 * @author Administrator
 *
 * @param <T>
 */
@BizDao
public interface ExportExcelDao<T> extends BaseDao<T>{
public List exportuseExcel(Map map);
public List exportLoginExcel(LogLoginEntity tmp);
public List exportExceptionExcel(LogExceptionEntity tmp);
}
