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
import com.dimine.sys.entity.FuncEntity;
import com.dimine.sys.entity.RolePopedomEntity;
import com.dimine.sys.service.RolePopedomService;

/**
 * 角色功能类
 * 
 * @author LCF
 * 
 */
@Namespace("/manager/sys/rolepopedom")
@Scope("request")
@Controller("rolePopedomAction")
public class RolePopedomAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	// 业务逻辑处理
	@Resource
	private RolePopedomService<RolePopedomEntity> rolePopedomService = null;

	private RolePopedomEntity bean = new RolePopedomEntity();

	private String funccodes;// 页面返回

	/**
	 * 功能授予
	 * 
	 * @return
	 */
	@Action(value = "add", results = { @Result(name = "success", location = "/webpage/pub/result.jsp") })
	public String doAdd() {
		try {
			rolePopedomService.insert(funccodes.split(","), bean);
		} catch (Exception ee) {
			this.setErrorMessage(ee);
		}
		return SUCCESS;
	}

	/**
	 * 功能授予
	 * 
	 * @return
	 */
	@Action(value = "delete", results = { @Result(name = "success", location = "/webpage/pub/result.jsp") })
	public String doDelete() {
		try {
			rolePopedomService.delete(funccodes.split(","), bean);
		} catch (Exception ee) {
			this.setErrorMessage(ee);
		}
		return SUCCESS;
	}

	/**
	 * 已授权功能列表
	 * 
	 * @return
	 */
	@Action(value = "rolepopedomlist", results = { @Result(name = "success", location = "/webpage/pub/jsonpager.jsp") })
	public String doList() {
		try {
			List dataList = rolePopedomService.yiShouYu(bean);
			String jsonData = createJsonData(dataList);
			getRequest().setAttribute("jsonString", jsonData);
		} catch (Exception ee) {
			this.setErrorMessage(ee);
		}

		return SUCCESS;
	}

	/**
	 * 未授权功能列表
	 * 
	 * @return
	 */
	@Action(value = "rolepopedomunlist", results = { @Result(name = "success", location = "/webpage/pub/jsonpager.jsp") })
	public String doUnList() {
		try {
			Map<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("userID", getLoginUser().getUserid());
			paramMap.put("funccode", bean.getFunccode());
			List undataList = rolePopedomService.weiShouYu(paramMap);
			String jsonData = createJsonData(undataList);
			getRequest().setAttribute("jsonString", jsonData);
		} catch (Exception e) {
			this.setErrorMessage(e);
		}
		return SUCCESS;
	}
	//为快捷功能管理查询未分配的功能
	@Action(value = "rolepopedomunlist1", results = { @Result(name = "success", location = "/webpage/pub/jsonpager.jsp") })
	public String doUnList1() {
		try {
			Map<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("userID", getLoginUser().getUserid());
			paramMap.put("funccode", bean.getFunccode());
			List undataList = rolePopedomService.weiShouYuForshortcut(paramMap);
			String jsonData = createJsonData(undataList);
			getRequest().setAttribute("jsonString", jsonData);
		} catch (Exception e) {
			this.setErrorMessage(e);
		}
		return SUCCESS;
	}

	/**
	 * 创建JSON格式的对象 <功能详细描述>
	 * 
	 * @param funcList
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	private String createJsonData(List<FuncEntity> funcList) {
		StringBuffer jsonData = new StringBuffer();
		int length = funcList.size();
		if (length == 0) {
			return "";
		}
		FuncEntity func;
		for (int i = 0; i < length; i++) {
			func = funcList.get(i);
			jsonData.append("{\"id\":\"");
			jsonData.append(func.getFunccode());
			jsonData.append("\", \"pid\":\"");
			jsonData.append(func.getParentfunccode());
			jsonData.append("\", \"name\":\"");
			jsonData.append(func.getFuncname());
			if (Integer.valueOf(func.getChildCount()) >= 1) {
				jsonData.append("\", \"isParent\":\"");
				jsonData.append("true");
				jsonData.append("\", \"close\":\"");
				jsonData.append("true");
				jsonData.append("\"},");
			} else {
				jsonData.append("\", \"isChild\":\"");
				jsonData.append("true");
				jsonData.append("\", \"open\":\"");
				jsonData.append("false");
				jsonData.append("\"},");
			}
		}
		String _jsonData = jsonData.toString();
		length = _jsonData.length();
		return _jsonData.substring(0, length - 1);
	}

	public String getBiztypename() {
		return "角色授予功能";
	}

	public void setBean(RolePopedomEntity bean) {
		this.bean = bean;
	}

	public RolePopedomEntity getBean() {
		return bean;
	}

	public String getFunccodes() {
		return funccodes;
	}

	public void setFunccodes(String funccodes) {
		this.funccodes = funccodes;
	}

	public RolePopedomService<RolePopedomEntity> getRolePopedomService() {
		return rolePopedomService;
	}

	public void setRolePopedomService(
			RolePopedomService<RolePopedomEntity> rolePopedomService) {
		this.rolePopedomService = rolePopedomService;
	}

}
