package com.dimine.sys.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.dimine.base.action.BaseAction;
import com.dimine.base.common.ValidException;
import com.dimine.base.page.Pager;
import com.dimine.base.util.SQLCallbackImpl;
import com.dimine.base.util.SQLTool;
import com.dimine.base.util.json.JSONUtil;
import com.dimine.sys.entity.RoleEntity;
import com.dimine.sys.service.RoleService;
import com.dimine.sys.util.PublicUtil;

/**
 * 用于对角色进行系列的操作的action
 * 
 * @author 徐飞雄（dominic）-多米
 * 
 */
@Namespace("/manager/sys/role")
@Scope("request")
@Controller("roleAction")
public class RoleAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private final static Logger logger = Logger.getLogger(RoleAction.class);
	// 业务处理
	@Resource
	private RoleService<RoleEntity> roleService;

	// 参数实体
	private RoleEntity bean = new RoleEntity();
	/**
	 * 添加类型 addmore为新增保存
	 */
	private String addtype;

	private String somename;

	private String encode;

	private String param;

	private String filters;

	/**
	 * 对角色进行列表查询操作
	 * 
	 * @return
	 */
	@Action(value = "list", results = { @Result(name = "success", location = "/webpage/pub/grid/pager.jsp") })
	public String list() {
		try {
			if (filters != null) {
				String query = new SQLTool().constructWhere(filters,
						new SQLCallbackImpl());
				System.out.println(query);
				bean.setQuery(query);
			}
			bean.setPager(this.getPager());
			List<RoleEntity> dataList = roleService.selectByList(bean);
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
	 * extjs 对角色进行查询操作
	 * SSM
	 * @return
	 */
	@Action(value = "extlist", results = { @Result(name = "success", location = "/webpage/pub/grid/pager.jsp") })
	public String extlist() {
		try {
			if (filters != null) {
				String query = new SQLTool().constructWhere(filters,
						new SQLCallbackImpl());
				System.out.println(query);
				bean.setQuery(query);
			}
			Pager pager = new Pager();
			pager.setPageSize(100);
			bean.setPager(pager);
			List<RoleEntity> dataList = roleService.selectByList(bean);
			// 设置页面数据
			Map<String, Object> jsonMap = new HashMap<String, Object>();
			jsonMap.put("page", bean.getPager().getPageId());
			jsonMap.put("results", bean.getPager().getRowCount());
			jsonMap.put("datastr", dataList);
			this.setJsonStr(JSONUtil.toJSONString(jsonMap));
		} catch (Exception e) {
			this.setErrorMessage(e);
		}
		return SUCCESS;
	}
	/**
	 * 新增角色信息查询 <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@Action(value = "doAdd", results = { @Result(name = "success", location = "/webpage/manager/sys/role/info.jsp") })
	public String doAdd() {
		// TODO 其他业务处理
		this.setActiontype(ACTIONTYPE_ADDSAVE);
		return SUCCESS;
	}

	/**
	 * 新增角色信息保存 <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@Action(value = "doAddSave", results = { @Result(name = "success", location = "/webpage/pub/beanresult.jsp") })
	public String doAddSave() {
		try {
			roleService.insert(bean);
		} catch (ValidException ee) {
			bean.setErrorMessage(ee.getMessage());
			ee.printStackTrace();
		} catch (Exception e) {
			bean.setErrorMessage("failed");
			e.printStackTrace();
		}
		this.setJsonStr(JSONUtil.toJSONString(bean));
		return SUCCESS;
	}

	/**
	 * 删除角色信息 <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@Action(value = "delete", results = { @Result(name = "success", location = "/webpage/pub/beanresult.jsp") })
	public String doDelete() {
		try {
			if (PublicUtil.ADMIN_ROLE_ID.equals(bean.getRoleid())) {
				bean.setErrorMessage("对不起，您没有权限删除该角色!");
			} else {
				roleService.delete(bean);
			}
		} catch (Exception e) {
			bean.setErrorMessage("failed");
			e.printStackTrace();
		}
		this.setJsonStr(JSONUtil.toJSONString(bean));
		return SUCCESS;
	}

	/**
	 * 对角色进行修改查询操作
	 */
	@Action(value = "doModify", results = { @Result(name = "success", location = "/webpage/manager/sys/role/info.jsp") })
	public String doModify() {
		try {
			if (getActiontype().equals(ACTIONTYPE_MODIFY)) {
				this.setActiontype(ACTIONTYPE_MODIFYSAVE);
			} else if (getActiontype().equals(ACTIONTYPE_VIEW)) {
				this.setActiontype(ACTIONTYPE_VIEW);
			} else if (getActiontype().equals(ACTIONTYPE_DELETE)) {
				this.setActiontype(ACTIONTYPE_DELETESAVE);
			}
			bean = roleService.selectById(bean.getRoleid());
		} catch (Exception e) {
			this.setErrorMessage(this.getText("modifyfailure"));
		}
		return SUCCESS;
	}

	/**
	 * 对角色进行存储和修改操作
	 */
	@Action(value = "doModifySave", results = { @Result(name = "success", location = "/webpage/pub/beanresult.jsp") })
	public String doModifySave() {
		try {
			roleService.update(bean, ACTIONTYPE_MODIFYSAVE);
			this.setJsonStr(JSONUtil.toJSONString(bean));
		} catch (ValidException ee) {
			bean.setErrorMessage(ee.getMessage());
			ee.printStackTrace();
		} catch (Exception e) {
			bean.setErrorMessage("failed");
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 跳转到给角色划分功能的页面
	 * 
	 * @return
	 */
	@Action(value = "toFunc", results = { @Result(name = "success", location = "/webpage/manager/sys/rolepopedom/main.jsp") })
	public String toFunc() {
		try {
			// 如果创建角色的机构不是当前用户所属的机构，那么就抛出异常。
			// if (!bean.getDeptid().equals(getUser().getOrgid()))
			// {
			// throw new Exception("对不起，您没有权限对该角色进行权限的修改！");
			// }
			bean = roleService.selectById(bean.getRoleid());
			// 获得已经被授予的权限集合
			// // List<String> funcList = (List) baseService.getObjects(
			// // RolePopedomEntity.IBATIS_KEY_ROLE_FUNC_LIST_BY_ROLEID, bean
			// // .getRoleid());
			// ServletActionContext.getRequest().setAttribute("funccodes",
			// funcList.toArray(new String[0]));
		} catch (Exception e) {
			this.setErrorMessage(e);
		}
		return SUCCESS;
	}

	// 日志文件用
	@Override
	public String getBiztypename() {
		return "角色信息管理";
	}

	public RoleEntity getBean() {
		return bean;
	}

	public void setBean(RoleEntity bean) {
		this.bean = bean;
	}

	public RoleService<RoleEntity> getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService<RoleEntity> roleService) {
		this.roleService = roleService;
	}

	public String getAddtype() {
		return addtype;
	}

	public void setAddtype(String addtype) {
		this.addtype = addtype;
	}

	public String getSomename() {
		return somename;
	}

	public void setSomename(String somename) {
		this.somename = somename;
	}

	public String getEncode() {
		return encode;
	}

	public void setEncode(String encode) {
		this.encode = encode;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public String getFilters() {
		return filters;
	}

	public void setFilters(String filters) {
		this.filters = filters;
	}

}
