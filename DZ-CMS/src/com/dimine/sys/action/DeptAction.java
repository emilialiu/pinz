package com.dimine.sys.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.dimine.base.action.BaseAction;
import com.dimine.sys.entity.DeptEntity;
import com.dimine.sys.entity.UserEntity;
import com.dimine.sys.service.DeptService;

/***
 * 对组织机构进行操作的action
 * 
 * @author Administrator
 * 
 */
@Namespace("/manager/sys/dept")
@Scope("request")
@Controller("deptAction")
public class DeptAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final static Logger logger = Logger.getLogger(DeptAction.class);
	// 业务处理
	@Resource
	private DeptService<DeptEntity> deptService;
	private DeptEntity bean = new DeptEntity();

	@Action(value = "tree", results = { @Result(name = "success", location = "/webpage/pub/jsonpager.jsp") })
	public String tree() {
		try {
			List<DeptEntity> deptList = deptService.getall(bean.getDeptid());
			String jsonData = createJsonData(deptList);
			getRequest().setAttribute("jsonString", jsonData);
		} catch (Exception ee) {
			setErrorMessage(ee);
			ee.printStackTrace();
		}
		return "success";
	}

	// 只有组织机构
	@Action(value = "tree1", results = { @Result(name = "success", location = "/webpage/pub/jsonpager.jsp") })
	public String tree1() {
		try {
			List<DeptEntity> deptList = deptService.getall1(bean.getDeptid());
			String jsonData = createJsonData(deptList);
			getRequest().setAttribute("jsonString", jsonData);
		} catch (Exception ee) {
			setErrorMessage(ee);
			ee.printStackTrace();
		}
		return "success";
	}

	// 某部门
	@Action(value = "tree2", results = { @Result(name = "success", location = "/webpage/pub/jsonpager.jsp") })
	public String tree2() {
		try {
			List<DeptEntity> deptList = deptService.getall2(bean.getDeptid());
			String jsonData = createJsonData(deptList);
			getRequest().setAttribute("jsonString", jsonData);
		} catch (Exception ee) {
			setErrorMessage(ee);
			ee.printStackTrace();
		}
		return "success";
	}

	/**
	 * 机构的修改查看
	 * 
	 * @return
	 */
	@Action(value = "modify", results = {
			@Result(name = "modify", location = "/webpage/pub/jsonpager.jsp"),
			@Result(name = "modify_success", location = "/webpage/manager/sys/dept/result.jsp") })
	public String doModify() {
		String result = "modify";
		try {
			if ("modifysave".equals(getActiontype())) {
				result = "modify_success";
				deptService.update(bean, getLoginUser().getUserid());
			} else {
				bean = deptService.getDept(bean.getDeptid());
				getRequest().setAttribute("jsonString",
						createInfoData(bean, "modifysave"));
			}
		} catch (Exception ee) {
			setErrorMessage(ee);
			ee.printStackTrace();
		}
		return result;
	}

	@Action(value = "delete", results = {
			@Result(name = "success", location = "/webpage/pub/result.jsp"),
			@Result(name = "modify_success", location = "/webpage/manager/sys/dept/result.jsp") })
	public String doDelete() {
		try {
			deptService.delete(bean.getDeptid());
		} catch (Exception ee) {
			setErrorMessage(ee);
		}
		return "success";
	}

	private String createJsonData(List deptList) {
		StringBuffer jsonData = new StringBuffer();
		int length = deptList.size();
		if (length <= 0)
			return "";
		for (int i = 0; i < length; i++) {
			DeptEntity dept = (DeptEntity) deptList.get(i);
			jsonData.append("{\"id\":\"");
			jsonData.append(dept.getDeptid());
			jsonData.append("\", \"pid\":\"");
			if (dept.getParentdeptid() == null || dept.getParentdeptid() == "")
				jsonData.append("");
			else
				jsonData.append(dept.getParentdeptid());
			jsonData.append("\", \"name\":\"");
			jsonData.append(dept.getDeptname());
			jsonData.append("\", \"isorg\":\"");
			jsonData.append(dept.getIsOrg());
			jsonData.append("\", \"orglevel\":\"");
			jsonData.append(dept.getOrglevel());
			jsonData.append("\", \"orgtype\":\"");
			jsonData.append(dept.getOrgtype());
			if (Integer.valueOf(dept.getChildCount()).intValue() >= 1) {
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

	private String createInfoData(DeptEntity dept, String actiontype) {
		StringBuffer jsonData = new StringBuffer("{");
		jsonData.append("\"parentdeptid\":\"");
		jsonData.append(dept.getParentdeptid());
		jsonData.append("\", \"deptid\":\"");
		jsonData.append(dept.getDeptid() != null ? dept.getDeptid() : "");
		jsonData.append("\", \"actiontype\":\"");
		jsonData.append(actiontype);
		jsonData.append("\", \"deptname\":\"");
		jsonData.append(dept.getDeptname() != null ? dept.getDeptname() : "");
		jsonData.append("\", \"deptcode\":\"");
		jsonData.append(dept.getDeptCode() != null ? dept.getDeptCode() : "");
		jsonData.append("\", \"orderno\":\"");
		jsonData.append(dept.getOrderNo() != null ? dept.getOrderNo() : "");
		jsonData.append("\", \"memo\":\"");
		jsonData.append(dept.getMemo() != null ? dept.getMemo() : "");
		jsonData.append("\", \"tel\":\"");
		jsonData.append(dept.getTel() != null ? dept.getTel() : "");
		jsonData.append("\", \"fax\":\"");
		jsonData.append(dept.getFax() != null ? dept.getFax() : "");
		jsonData.append("\", \"orglevel\":\"");
		jsonData.append(dept.getOrglevel() != null ? dept.getOrglevel() : "");
		jsonData.append("\", \"orgtype\":\"");
		jsonData.append(dept.getOrgtype() != null ? dept.getOrgtype() : "");
		jsonData.append("\", \"isorg\":\"");
		jsonData.append(dept.getIsOrg());
		jsonData.append("\", \"parentName\":\"");
		jsonData.append(dept.getParentdeptname() != null ? dept
				.getParentdeptname() : "");
		jsonData.append("\"}");
		return jsonData.toString();
	}

	/**
	 * 部门添加
	 * 
	 * @return
	 */
	@Action(value = "add", results = { @Result(name = "success", location = "/webpage/pub/jsonpager.jsp") })
	public String doAdd() {
		try {
			UserEntity user = new UserEntity();
			user.setUserid(getLoginUser().getUserid());
			String deptID = deptService.insert(bean, user);
			StringBuffer result_string = new StringBuffer("{\"deptID\":\"");
			result_string.append(deptID);
			result_string.append("\"}");
			getRequest().setAttribute("jsonString", result_string.toString());
		} catch (Exception ee) {
			setErrorMessage(ee);
			ee.printStackTrace();
		}
		return "success";
	}

	/**
	 * 根据项目部ID取得区队
	 * 
	 * @return
	 */
	@Action(value = "getGgByParentDeptId", results = { @Result(name = "success", location = "/webpage/pub/jsondatas.jsp") })
	public String getGgByParentDeptId() {
		StringBuffer sbf = new StringBuffer();
		try {
			List<DeptEntity> list = deptService.getGgByParentDeptId(bean
					.getDeptid());
			int i = 0;
			for (DeptEntity dept : list) {
				sbf.append("{'deptid':'").append(dept.getDeptid())
						.append("','deptname':'").append(dept.getDeptname())
						.append("'}");
				if (i != list.size() - 1) {
					sbf.append(",");
				}
				i++;
			}
		} catch (Exception ee) {
			setErrorMessage(ee);
			ee.printStackTrace();
		}
		getRequest().setAttribute("jsonString", sbf.toString());
		return SUCCESS;
	}

	public DeptService<DeptEntity> getDeptService() {
		return deptService;
	}

	public void setDeptService(DeptService<DeptEntity> deptService) {
		this.deptService = deptService;
	}

	public DeptEntity getBean() {
		return bean;
	}

	public void setBean(DeptEntity bean) {
		this.bean = bean;
	}

}
