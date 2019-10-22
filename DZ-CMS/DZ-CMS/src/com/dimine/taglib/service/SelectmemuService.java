package com.dimine.taglib.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dimine.base.service.BaseService;
import com.dimine.taglib.dao.SelectmemuDao;
import com.dimine.taglib.entity.SelectmemuEntity;
@Service("selectmemuService")
public class SelectmemuService<T> extends BaseService<T> {

	@Autowired
	private SelectmemuDao<T> dao;

	public List<Map> selectmemulist(SelectmemuEntity bean) {
		return dao.selectmemulist(bean);
	} 
	
	@Override
	public SelectmemuDao<T> getDao() {
		// TODO Auto-generated method stub
		return dao;
	}
}
