package com.dimine.activity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dimine.activity.dao.BuinessProcessDao;
import com.dimine.base.dao.BaseDao;
import com.dimine.base.service.BaseService;


@Service("buinessProcessService")
public class BuinessProcessService<T> extends BaseService<T> {
	@Autowired
	private BuinessProcessDao<T> dao;
	@Override
	public BaseDao<T> getDao() {
		// TODO Auto-generated method stub
		return dao;
	}

}
