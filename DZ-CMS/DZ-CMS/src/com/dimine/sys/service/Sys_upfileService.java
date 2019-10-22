package com.dimine.sys.service;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dimine.base.dao.BaseDao;
import com.dimine.base.service.BaseService;
import com.dimine.base.util.KeyUtils;
import com.dimine.sys.dao.Sys_upfileDao;
import com.dimine.sys.entity.Sys_upfileEntity;

@Service("sys_upfileService")
public class Sys_upfileService<T> extends BaseService<T> {

	@Autowired
	private Sys_upfileDao<T> dao;
	@Resource
	private Sys_upfileService<T> sys_upfileService;
	
	public void insert(Sys_upfileEntity bean){
		bean.setUpfileid(KeyUtils.createKeyID());
		
		dao.insert((T) bean);
	}
	
	@Override
	public BaseDao<T> getDao() {
		// TODO Auto-generated method stub
		return null;
	}



	public Sys_upfileService<T> getSys_upfileService() {
		return sys_upfileService;
	}



	public void setSys_upfileService(Sys_upfileService<T> sys_upfileService) {
		this.sys_upfileService = sys_upfileService;
	}
	
}
