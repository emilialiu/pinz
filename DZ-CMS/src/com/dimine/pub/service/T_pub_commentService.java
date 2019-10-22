package com.dimine.pub.service;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dimine.base.common.ValidException;
import com.dimine.base.service.BaseService;
import com.dimine.base.util.KeyUtils;
import com.dimine.pub.dao.T_pub_commentDao;
import com.dimine.pub.entity.T_pub_commentEntity;

/**
 * 评论信息（T_PUB_COMMENT）管理事务处理
 * 
 * @author dimine 2016-09-28 10:53:47
 * 
 */
@Service("t_pub_commentService")
public class T_pub_commentService<T> extends BaseService<T> {

	@Autowired
	private T_pub_commentDao<T> dao;

	public T_pub_commentDao<T> getDao() {
		return dao;
	}

	/**
	 * 添加新的评论信息（T_PUB_COMMENT）
	 * 
	 * @param bean
	 * @param actiontype
	 * @param user
	 */
	public void insert(T_pub_commentEntity bean) throws Exception {
		String keyID = KeyUtils.createKeyID();
		// 生成编号
		bean.setCommid(keyID);
		// 插入数据
		getDao().insert((T) bean);
	}

	/**
	 * 修改评论信息（T_PUB_COMMENT）信息
	 * 
	 * @param bean
	 * @param actiontype
	 */
	public void update(T_pub_commentEntity bean, String actiontype) throws Exception {
		// 执行修改操作
		getDao().update((T) bean);
	}

	/**
	 * 删除评论信息（T_PUB_COMMENT）信息
	 * 
	 * @param bean
	 * 
	 */
	public void delete(T_pub_commentEntity bean) {
		// 删除评论信息（T_PUB_COMMENT）
		getDao().delete((T) bean);
	}
	
	public String getBiztypename() {
		return "评论信息（T_PUB_COMMENT）信息管理";
	}

}
