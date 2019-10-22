package com.dimine.taglib.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dimine.base.service.BaseService;
import com.dimine.taglib.dao.RadioDao;
import com.dimine.taglib.entity.RadioEntity;
@Service("radioService")
public class RadioService<T> extends BaseService<T> {

	@Autowired
	private RadioDao<T> dao;
	
	public List<Map> selectradiolist(RadioEntity bean){
		
		return dao.selectradiolist(bean);
	}
	
	@Override
	public RadioDao<T> getDao() {
		// TODO Auto-generated method stub
		return dao;
	}

}
