package com.dimine.sm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dimine.base.service.BaseService;
import com.dimine.base.util.KeyUtils;
import com.dimine.sm.dao.TeamstaffDao;
import com.dimine.sm.entity.TeamEntity;
import com.dimine.sm.entity.TeamstaffEntity;

/**
 * 班组对应人员管理事务处理
 * 
 * @author dimine 2015-06-16 17:53:39
 * 
 */
@Service("teamstaffService")
public class TeamstaffService<T> extends BaseService<T> {

	@Autowired
	private TeamstaffDao<T> dao;

	public TeamstaffDao<T> getDao() {
		return dao;
	}

	/**
	 * 添加新的班组对应人员
	 * 
	 * @param bean
	 * @param actiontype
	 * @param user
	 */
	public void insert(TeamstaffEntity bean) throws Exception {
		String keyID = KeyUtils.createKeyID();
		// 生成编号
		bean.setTeamid(keyID);
		// 插入数据
		getDao().insert((T) bean);
	}

	/**
	 * 修改班组对应人员信息
	 * 
	 * @param bean
	 * @param actiontype
	 */
	public void update(TeamstaffEntity bean, String actiontype)
			throws Exception {
		// 执行修改操作
		getDao().update((T) bean);
	}

	/**
	 * 删除班组对应人员信息
	 * 
	 * @param bean
	 * 
	 */
	public void delete(TeamstaffEntity bean) {
		// 删除班组对应人员
		getDao().delete((T) bean);
	}

	public String getBiztypename() {
		return "班组对应人员信息管理";
	}

	/**
	 * 批量添加班组人员
	 * 
	 * @param list
	 */
	public void batchInsert(List<TeamstaffEntity> list) {
		dao.batchInsert(list);
	}

	public void delete(String[] staffids, TeamstaffEntity bean) {
		for (String staffid : staffids) {
			bean.setStaffid(staffid);
			// 执行删除操作
			dao.delete((T) bean);
		}
	}

	/**
	 * 未被添加的人员列表
	 * 
	 * @param bean
	 * @return
	 */
	public List<TeamstaffEntity> selectUnList(TeamstaffEntity bean) {
		Integer rowCount = dao.selectUnCount(bean);
		bean.getPager().setRowCount(rowCount);
		return (List<TeamstaffEntity>) dao.selectUnList(bean);
	}

	/**
	 * 根据当前用户id查询该用户所在班组信息
	 * @param userid
	 * @return
	 */
	public TeamEntity getTeamInfoByUserid(String userid) {
		// TODO Auto-generated method stub
		return dao.getTeamInfoByUserid(userid);
	}

}
