package com.dimine.pub.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dimine.base.service.BaseService;
import com.dimine.pub.dao.TomatterDao;
import com.dimine.pub.entity.TomatterEntity;

/**
 * 
 * 待办信息service
 * 
 * @author LCF
 * @version [版本号, 2014-12-12]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Service("tomatterService")
public class TomatterService<T> extends BaseService<T> {
	@Autowired
	private TomatterDao<T> dao;

	public TomatterDao<T> getDao() {
		return dao;
	}

	/**
	 * 根据当前登录人id取得此人处理的告警信息列表
	 * 
	 * @param userid
	 * @return
	 */
	public List<TomatterEntity> getTomatterListByUserid(String userid) {
		return dao.getTomatterListByUserid(userid);
	}
	/**
	 * 统计当前用户未办事项
	 * @param bean
	 * @return
	 */
	public Integer countMatterByUser(TomatterEntity bean){
		return dao.countMatterByUser(bean);
	}
	/**
	 * 查询当前用户未办事项
	 * @param bean
	 * @return
	 */
	public List<TomatterEntity> findMatterByuser(TomatterEntity bean){
		return dao.findMatterByuser(bean);
	}
}
