package com.dimine.taglib.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dimine.base.service.BaseService;
import com.dimine.taglib.dao.CombogridDao;
@Service("combogridService")
public class CombogridService<T> extends BaseService<T> {

	@Autowired
	private CombogridDao<T> dao;
	
	@Override
	public CombogridDao<T> getDao() {
		// TODO Auto-generated method stub
		return dao;
	}

}
