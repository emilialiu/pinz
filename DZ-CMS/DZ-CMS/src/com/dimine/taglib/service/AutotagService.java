package com.dimine.taglib.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dimine.base.service.BaseService;
import com.dimine.taglib.dao.AutotagDao;
import com.dimine.taglib.entity.AutocompleteEntity;

@Service("autotagService")
public class AutotagService<T> extends BaseService<T> {

	@Autowired
	private AutotagDao<T> dao;
	
	public List<Map> selectautolist(AutocompleteEntity bean){
		return dao.selectautolist(bean);
	}

	@Override
	public AutotagDao<T> getDao() {
		return dao;
	}
}
