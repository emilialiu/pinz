package com.dimine.activity.bpm;



import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.UserQueryImpl;
import org.activiti.engine.impl.persistence.entity.GroupEntity;
import org.activiti.engine.impl.persistence.entity.UserEntityManager;

import com.dimine.sys.entity.RoleEntity;
import com.dimine.sys.entity.UserEntity;
import com.dimine.sys.service.RoleService;
import com.dimine.sys.service.UserService;

/**
 * 自定义的用户实体管理类
 * @author: Henry Yan
 */
public class AiaUserEntityManager extends UserEntityManager {
	@Resource
    private  UserService<UserEntity> userService;
	private  RoleService<RoleEntity> roleService ;
	/**
	 * 重新根据id查询user的方法
	 */
	public User findUserById(String userId){
		try{
			org.activiti.engine.impl.persistence.entity.UserEntity user = new org.activiti.engine.impl.persistence.entity.UserEntity();
			UserEntity u = userService.selectById(userId);
			user.setEmail(u.getEmail());
			user.setId(u.getUserid());
			user.setFirstName(u.getLoginname());
			user.setLastName(u.getUsername());
			return user;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Group> findGroupsByUser(final String userCode) {
		if (userCode == null)
			return null;
		List<RoleEntity> bGroups = roleService.findRoleByname(userCode);
		List<Group> groups = new ArrayList<Group>(bGroups.size());
		for (RoleEntity role : bGroups) {
            GroupEntity group = new GroupEntity();
            group.setName(role.getRolename()); // 角色中文名称
            group.setId(role.getRoleid()); // 角色英文名称
            groups.add(group);
        }
		return groups;

	}

	public void setUserService(UserService<UserEntity> userService) {
		this.userService = userService;
	}

	public void setRoleService(RoleService<RoleEntity> roleService) {
		this.roleService = roleService;
	}

    
}
