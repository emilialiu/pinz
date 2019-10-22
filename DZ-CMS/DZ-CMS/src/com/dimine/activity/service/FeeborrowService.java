package com.dimine.activity.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dimine.activity.dao.FeeborrowDao;
import com.dimine.activity.entity.FeeborrowEntity;
import com.dimine.base.dao.BaseDao;
import com.dimine.base.service.BaseService;
import com.dimine.base.util.KeyUtils;
@Service("feeborrowService")
public class FeeborrowService<T> extends BaseService<T>{
	@Autowired
	private FeeborrowDao<T> dao;
	@Autowired
	private ActivityService activityService;
	@Override
	public BaseDao<T> getDao() {
		// TODO Auto-generated method stub
		return dao;
	}
	public  void  insert(FeeborrowEntity bean) throws Exception{
		String borrowid = KeyUtils.createKeyID();
		bean.setBorrowid(borrowid);
		dao.insert((T) bean);
	}
	public  void  update(FeeborrowEntity bean) throws Exception{
		dao.update((T) bean);
		Map<String, Object> variables = new HashMap<String, Object>();
		activityService.startWorkflow(bean.getUserid(),"borrow",bean.getBorrowid(), variables);
	}
	/**
	 * 修改业务表流程状态
	 * @param bean
	 * @throws Exception
	 */
	public  void  modifyflow(FeeborrowEntity bean) throws Exception{
		dao.updatestate(bean);
	}
}
