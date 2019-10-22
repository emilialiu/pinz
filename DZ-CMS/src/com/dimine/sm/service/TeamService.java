package com.dimine.sm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dimine.base.common.ValidException;
import com.dimine.base.service.BaseService;
import com.dimine.base.util.KeyUtils;
import com.dimine.sm.dao.TeamDao;
import com.dimine.sm.entity.TeamEntity;

/**
 * 班组管理事务处理
 * 
 * @author dimine 2015-06-16 17:53:00
 * 
 */
@Service("teamService")
public class TeamService<T> extends BaseService<T> {

	@Autowired
	private TeamDao<T> dao;

	public TeamDao<T> getDao() {
		return dao;
	}

	/**
	 * 添加新的班组
	 * 
	 * @param bean
	 * @param actiontype
	 * @param user
	 */
	public void insert(TeamEntity bean) throws Exception {
		String keyID = KeyUtils.createKeyID();
		// 生成编号
		bean.setTeamid(keyID);
		checkCodeExist(bean, "addsave");
		// 插入数据
		getDao().insert((T) bean);
	}

	/**
	 * 修改班组信息
	 * 
	 * @param bean
	 * @param actiontype
	 */
	public void update(TeamEntity bean, String actiontype) throws Exception {
		checkCodeExist(bean, "modifysave");
		// 执行修改操作
		getDao().update((T) bean);
	}

	/**
	 * 删除班组信息
	 * 
	 * @param bean
	 * 
	 */
	public void delete(TeamEntity bean) {
		// 删除班组
		getDao().delete((T) bean);
	}

	/**
	 * 删除班组（逻辑删除）
	 * 
	 * @param staffid
	 */
	public void deleteTeam(String teamid) {
		// 删除班组信息
		getDao().deleteTeam(teamid);
	}

	// 验证唯一性同一个班组编号不能重复提交
	public void checkCodeExist(TeamEntity bean, String actiontype)
			throws ValidException {
		int count = dao.getSameCode(bean);
		if (count > 0 && "addsave".equals(actiontype))
			throw new ValidException((new StringBuilder("当前班组编号【"))
					.append(bean.getTeamcode()).append("】已经存在!").toString());
		if (count == 1 && "modifysave".equals(actiontype))
			throw new ValidException((new StringBuilder("当前班组编号【"))
					.append(bean.getTeamcode()).append("】已经存在!").toString());
		else
			return;
	}

	/**
	 * 根据tdeptid查询班组信息
	 * 
	 * @param tdeptid
	 */
	public List<TeamEntity> selectByTdeptId(TeamEntity bean) {
		return getDao().selectByTdeptId(bean);
	}

	public String getBiztypename() {
		return "班组信息管理";
	}

	/**
	 * 根据项目部id及业务类型取得班组类型列表
	 * 
	 * @param bean
	 * @return
	 */
	public List<TeamEntity> selectTeamTypeList(TeamEntity bean) {
		return dao.selectTeamTypeList(bean);
	}

	/**
	 * 根据部门ID获得班组数据
	 * 
	 * @param bean
	 * @return
	 */
	public List<TeamEntity> genTeamList(TeamEntity bean){
		return getDao().genTeamList(bean);
	}
	
	/**
	 * 根据工序ID获得班组数据(月验收)
	 * 
	 * @param bean
	 * @return
	 */
	public List<TeamEntity> genAetailTeamList(TeamEntity bean){
		return getDao().genAetailTeamList(bean);
	}
}
