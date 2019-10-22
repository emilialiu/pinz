package com.dimine.taglib.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dimine.base.dao.BaseDao;
import com.dimine.base.service.BaseService;
import com.dimine.taglib.dao.ComboDao;
import com.dimine.taglib.entity.ComboEntity;

@Service("comboService")
public class ComboService<T> extends BaseService<T> {
	@Autowired
	private ComboDao<T> dao;
	
	public List<Map> selectcombolist(ComboEntity bean){
		
		return dao.selectcombolist(bean);
	}
	
	@Override
	public BaseDao<T> getDao() {
		return dao;
	}

	
}
