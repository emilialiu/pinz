package com.dimine.taglib.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dimine.base.service.BaseService;
import com.dimine.taglib.dao.CheckboxDao;
import com.dimine.taglib.entity.CheckboxEntity;
@Service("checkboxService")
public class CheckboxService<T> extends BaseService<T> {

	@Autowired
	private CheckboxDao<T> dao;
	
	public List<Map> checkboxselectlist(CheckboxEntity bean){
		return dao.selectcheckboxlist(bean);
	}
	
	@Override
	public CheckboxDao<T> getDao() {
		// TODO Auto-generated method stub
		return dao;
	}

}
