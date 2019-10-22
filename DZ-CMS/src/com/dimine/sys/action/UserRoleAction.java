package com.dimine.sys.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.dimine.base.action.BaseAction;
import com.dimine.base.util.SQLCallbackImpl;
import com.dimine.base.util.SQLTool;
import com.dimine.base.util.json.JSONUtil;
import com.dimine.sys.entity.DictEntity;
import com.dimine.sys.entity.UserRoleEntity;
import com.dimine.sys.service.UserRoleService;

/**
 * 用户角色逻辑处理类
 * 
 * @author LCF
 */
@Namespace("/manager/sys/userrole")
@Scope("request")
@Controller("userRoleAction")
public class UserRoleAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	// 业务逻辑处理
	@Resource
	private UserRoleService userRoleService = null;
	// 实例Bean
	private UserRoleEntity bean = new UserRoleEntity();
	// 角色编号
	private String roles;
	//
	private String date = "";
	
	private String filters;
	
	/**
	 * 执行授权的操作 授权和撤销两个操作是同事进行的
	 * 
	 * @return
	 */
	@Action(value = "doAdd", results = { @Result(name = "success", location = "/webpage/pub/result.jsp") })
	public String doAdd() {
		try {
			userRoleService.insert(roles.split(","), bean);
		} catch (Exception ee) {
			this.setErrorMessage(ee);
		}
		return SUCCESS;
	}

	/**
	 * 执行撤销的操作
	 * 
	 * @return
	 */
	@Action(value = "userroledel", results = { @Result(name = "success", location = "/webpage/pub/result.jsp") })
	public String doDel() {
		try {
			// 不知道为什么一定要加这个才行啊
			// this.setActiontype("why");
			userRoleService.delete(roles.split(","), bean);
		} catch (Exception ee) {
			this.setErrorMessage(ee);
		}
		return SUCCESS;
	}

	/**
	 * 用户组列表显示 已被授权的
	 * 
	 * @return
	 */
	@Action(value = "userrolelist", results = { @Result(name = "success", location = "/webpage/pub/grid/pager.jsp") })
	public String doList() {
		try {
			if(filters!=null){
				String query = new SQLTool().constructWhere(filters, new SQLCallbackImpl());
				System.out.println(query);
				bean.setQuery(query);
			}
			
			bean.setPager(this.getPager());
			// if (!getLoginUser().getUserid().equals(PublicUtil.ADMIN_USER_ID))
			// {
			// bean.setDeptid(getLoginUser().getOrgid());
			// }
			List<DictEntity> dataList = userRoleService.selectByList(bean);
			// 设置页面数据
			Map<String, Object> jsonMap = new HashMap<String, Object>();
			jsonMap.put("page", bean.getPager().getPageId());
			jsonMap.put("total", bean.getPager().getPageCount());
			jsonMap.put("records", bean.getPager().getRowCount());
			jsonMap.put("rows", dataList);
			this.setJsonStr(JSONUtil.toJSONString(jsonMap));
		} catch (Exception e) {
			this.setErrorMessage(e);
		}
		return SUCCESS;
	}

	/**
	 * 用户组列表显示 未被授权的
	 * 
	 * @return
	 */
	@Action(value = "userroleunlist", results = { @Result(name = "success", location = "/webpage/pub/grid/pager.jsp") })
	public String doUnList() {
		try {
			if(filters!=null){
				String query = new SQLTool().constructWhere(filters, new SQLCallbackImpl());
				System.out.println(query);
				bean.setQuery(query);
			}
			
			bean.setPager(this.getPager());
			// if (!getLoginUser().getUserid().equals(PublicUtil.ADMIN_USER_ID))
			// {
			// bean.setDeptid(getLoginUser().getOrgid());
			// }
			List<DictEntity> dataList = userRoleService.selectUnList(bean);
			// 设置页面数据
			Map<String, Object> jsonMap = new HashMap<String, Object>();
			jsonMap.put("page", bean.getPager().getPageId());
			jsonMap.put("total", bean.getPager().getPageCount());
			jsonMap.put("records", bean.getPager().getRowCount());
			jsonMap.put("rows", dataList);
			this.setJsonStr(JSONUtil.toJSONString(jsonMap));
		} catch (Exception e) {
			this.setErrorMessage(e);
		}
		return SUCCESS;
	}

	public String getBiztypename() {
		return "用户角色信息管理";
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public UserRoleService getUserRoleService() {
		return userRoleService;
	}

	public void setUserRoleService(UserRoleService userRoleService) {
		this.userRoleService = userRoleService;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public UserRoleEntity getBean() {
		return bean;
	}

	public void setBean(UserRoleEntity bean) {
		this.bean = bean;
	}

	public String getFilters() {
		return filters;
	}

	public void setFilters(String filters) {
		this.filters = filters;
	}

}
