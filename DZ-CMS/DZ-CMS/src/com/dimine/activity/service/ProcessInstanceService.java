package com.dimine.activity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dimine.activity.dao.ProcessInstanceDao;
import com.dimine.base.dao.BaseDao;
import com.dimine.base.service.BaseService;
@Service("processInstanceService")
public class ProcessInstanceService<T> extends BaseService<T> {
	@Autowired
	private ProcessInstanceDao<T> dao;
	@Override
	public BaseDao<T> getDao() {
		// TODO Auto-generated method stub
		return dao;
	}

}
