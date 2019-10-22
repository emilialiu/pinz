package com.dimine.shortcut.dao;

import java.util.List;

import com.dimine.base.dao.BaseDao;
import com.dimine.base.dao.BizDao;
import com.dimine.shortcut.entity.Sys_shortcut_funcEntity;

/**
 * 人员快捷功能 Mapper
 * 
 * @author dimine 2016-09-09 17:39:35
 * 
 */
@BizDao
public interface Sys_shortcut_funcDao<T> extends BaseDao<T> {
	List<Sys_shortcut_funcEntity> selectList(Sys_shortcut_funcEntity bean);
	int selectMaxOrderno(Sys_shortcut_funcEntity bean);
	int selectCount();
}
