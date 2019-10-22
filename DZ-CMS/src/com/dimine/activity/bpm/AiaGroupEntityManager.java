package com.dimine.activity.bpm;

import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.UserQueryImpl;
import org.activiti.engine.impl.persistence.entity.GroupEntity;
import org.activiti.engine.impl.persistence.entity.GroupEntityManager;

import com.dimine.sys.entity.RoleEntity;
import com.dimine.sys.service.RoleService;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

/**
 * @author: Henry Yan
 */
public class AiaGroupEntityManager extends GroupEntityManager {

    private RoleService<RoleEntity> roleService;
    @Resource
    public void setRoleService(RoleService<RoleEntity> roleService) {
		this.roleService = roleService;
	}

	@Override
    public List<Group> findGroupsByUser(String userId) {
        List<RoleEntity> roles = roleService.findRoleByname(userId);
        List<Group> groups = new ArrayList<Group>(roles.size());
        for (RoleEntity role : roles) {
            GroupEntity group = new GroupEntity();
            group.setName(role.getRolename()); // 角色中文名称
            group.setId(role.getRoleid()); // 角色英文名称
            groups.add(group);
        }
        return groups;
    }
	
	public GroupEntity findGroupById(String groupCode){
		if(groupCode==null)
			return null;
		try{
			RoleEntity role = roleService.selectById(groupCode);
			GroupEntity e = new GroupEntity();
			e.setRevision(1);
			e.setType("assignment");
			e.setType(role.getRoleid());
			e.setName(role.getRolename());
			return e;
		}catch (Exception e) {
			return null;
		}
		
		
	}
	
}
