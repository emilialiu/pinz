package com.dimine.coupon.service;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dimine.base.common.ValidException;
import com.dimine.base.service.BaseService;
import com.dimine.base.util.KeyUtils;
import com.dimine.coupon.dao.Dz_coupon_configDao;
import com.dimine.coupon.entity.Dz_coupon_configEntity;

/**
 * 优惠券配置管理事务处理
 * 
 * @author dimine 2019-08-29 19:40:49
 * 
 */
@Service("dz_coupon_configService")
public class Dz_coupon_configService<T> extends BaseService<T> {

	@Autowired
	private Dz_coupon_configDao<T> dao;

	public Dz_coupon_configDao<T> getDao() {
		return dao;
	}

	/**
	 * 添加新的优惠券配置
	 * 
	 * @param bean
	 * @param actiontype
	 * @param user
	 */
	public void insert(Dz_coupon_configEntity bean) throws Exception {
		String keyID = KeyUtils.createKeyID();
		// 生成编号
		bean.setId(keyID);
		// 插入数据
		getDao().insert((T) bean);
	}

	/**
	 * 修改优惠券配置信息
	 * 
	 * @param bean
	 * @param actiontype
	 */
	public void update(Dz_coupon_configEntity bean, String actiontype) throws Exception {
		// 执行修改操作
		getDao().update((T) bean);
	}

	/**
	 * 删除优惠券配置信息
	 * 
	 * @param bean
	 * 
	 */
	public void delete(Dz_coupon_configEntity bean) {
		// 删除优惠券配置
		getDao().delete((T) bean);
	}
	
	public String getBiztypename() {
		return "优惠券配置信息管理";
	}

}
