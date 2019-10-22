package com.dimine.sys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dimine.base.dao.BaseDao;
import com.dimine.base.service.BaseService;
import com.dimine.sys.dao.LogLoginDao;
import com.dimine.sys.entity.LogLoginEntity;
@Service("logLoginService")
public class LogLoginService<T> extends BaseService<T> {
	@Autowired
	private LogLoginDao<T> dao;
	public BaseDao<T> getDao() {
		// TODO Auto-generated method stub
		return dao;
	}
	/**
	 * 批量删除日志
	 * @param ids
	 */
	public void deleteobjs(String ids){
		String []lids = ids.split(",");
		for(int i=0;i<lids.length;i++){
			dao.delete((T) lids[i]);
		}
	}
	/**
	 * 删除所有的日志
	 */
	public void deleteall(){
		
	}

}
