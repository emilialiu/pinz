package com.dimine.taglib.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dimine.base.service.BaseService;
import com.dimine.sys.entity.DictEntity;
import com.dimine.taglib.dao.ResourceDao;
import com.dimine.taglib.entity.ResourceBean;

@Service("resourceService")
public class ResourceService<T> extends BaseService<T> {

	@Autowired
	private ResourceDao<T> dao;
	
	public List<ResourceBean> selectdictlist(DictEntity entity){
		return dao.selectdictlist(entity);
	}
	
	public List<ResourceBean> selectgxlist(DictEntity entity){
		return dao.selectgxlist(entity);
	}
	
	public List<ResourceBean> selectdictsecondlist(DictEntity entity){
		return dao.selectdictsecondlist(entity);
	}
	/**
	 * 获取元素表信息
	 * @param bean
	 * @return
	 */
	public List<ResourceBean> findElement(DictEntity bean){
		return dao.findElement(bean);
	}
	@Override
	public ResourceDao<T> getDao() {
		// TODO Auto-generated method stub
		return dao;
	}

}
