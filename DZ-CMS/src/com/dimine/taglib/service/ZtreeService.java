package com.dimine.taglib.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dimine.base.dao.BaseDao;
import com.dimine.base.service.BaseService;
import com.dimine.taglib.dao.ZtreeDao;
import com.dimine.taglib.entity.ZtreeEntity;
@Service("ztreeService")
public class ZtreeService<T> extends BaseService<T> {

	@Autowired
	private ZtreeDao<T> dao;
	
	public List<Map> selectztreelist(ZtreeEntity bean){
		return dao.selectztreelist(bean);
	}
	public List<Map> updatedrop(ZtreeEntity bean){
		return dao.updatedrop(bean);
	}
	public List<Map> selectztreehead(ZtreeEntity bean){
		return dao.selectztreehead(bean);
	}
	
	@Override
	public ZtreeDao<T> getDao() {
		// TODO Auto-generated method stub
		return dao;
	}

}
