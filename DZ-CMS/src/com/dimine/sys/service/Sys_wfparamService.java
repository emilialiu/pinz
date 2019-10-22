package com.dimine.sys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dimine.base.dao.BaseDao;
import com.dimine.base.service.BaseService;
import com.dimine.sys.dao.Sys_wfparamDao;
@Service("sys_wfparamService")
public class Sys_wfparamService<T> extends BaseService<T> {
	@Autowired
	private Sys_wfparamDao<T> dao;
	@Override
	public BaseDao<T> getDao() {
		// TODO Auto-generated method stub
		return dao;
	}

}
