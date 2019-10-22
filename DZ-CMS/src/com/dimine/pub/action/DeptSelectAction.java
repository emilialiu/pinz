package com.dimine.pub.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.dimine.base.action.BaseAction;
import com.dimine.sys.entity.DeptEntity;
import com.dimine.sys.service.DeptService;

@Namespace("/pub/select/dept")
@Scope("request")
@Controller("deptSelectAction")
public class DeptSelectAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource
	private DeptService<DeptEntity> deptService;
	private DeptEntity bean = new DeptEntity();

	/**
	 * 进入主页面 <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@Action(value = "doEnter", results = { @Result(name = "success", location = "/webpage/pub/select/dept/dept.jsp") })
	public String doEnter() {
		try {
			// 根据登录用户所在部门orgid查询部门信息
			DeptEntity dept = deptService.selectById(getLoginUser().getOrgid());
			// 判断当前所在部门是否是组织。如果是组织，则当前所在部门就是根节点；如果不是组织，则向上递归查询父级为组织的节点，取最近的一个节点设置为根节点
			if ("0".equals(dept.getIsOrg())) {
				dept = deptService.selectById(bean.getParentdeptid());
			}
			List<DeptEntity> list = new ArrayList<DeptEntity>();
			list.add(dept);
			String jsonData = createJsonData(list);
			getRequest().setAttribute("jsonString", jsonData);
		} catch (Exception ee) {
			setErrorMessage(ee);
		}
		return SUCCESS;
	}

	@Action(value = "doEnter1", results = { @Result(name = "success", location = "/webpage/pub/select/dept/dept1.jsp") })
	public String doEnter1() {
		try {
			// 根据登录用户所在部门orgid查询部门信息
			DeptEntity dept = deptService.selectById(getLoginUser().getOrgid());
			// 判断当前所在部门是否是组织。如果是组织，则当前所在部门就是根节点；如果不是组织，则向上递归查询父级为组织的节点，取最近的一个节点设置为根节点
			if ("0".equals(dept.getIsOrg())) {
				dept = deptService.selectById(bean.getParentdeptid());
			}
			List<DeptEntity> list = new ArrayList<DeptEntity>();
			list.add(dept);
			String jsonData = createJsonData(list);
			getRequest().setAttribute("jsonString", jsonData);
		} catch (Exception ee) {
			setErrorMessage(ee);
		}
		return SUCCESS;
	}

	@Action(value = "showDeptModelDlg", results = { @Result(name = "success", location = "/webpage/pub/select/dept/deptDlg.jsp") })
	public String showDeptModelDlg() {
		try {
			// 根据登录用户所在部门orgid查询部门信息
			DeptEntity dept = deptService.selectById(getLoginUser().getOrgid());
			// 判断当前所在部门是否是组织。如果是组织，则当前所在部门就是根节点；如果不是组织，则向上递归查询父级为组织的节点，取最近的一个节点设置为根节点
			if ("0".equals(dept.getIsOrg())) {
				dept = deptService.selectById(bean.getParentdeptid());
			}
			List<DeptEntity> list = new ArrayList<DeptEntity>();
			list.add(dept);
			String jsonData = createJsonData(list);
			getRequest().setAttribute("jsonString", jsonData);
		} catch (Exception ee) {
			setErrorMessage(ee);
		}
		return SUCCESS;
	}

	@Action(value = "selectDept", results = { @Result(name = "success", location = "/webpage/pub/select/dept/selectdept.jsp") })
	public String selectDept() {
		try {
			List<DeptEntity> deptList = deptService.getall(bean.getDeptid());
			String jsonData = createJsonData(deptList);
			getRequest().setAttribute("jsonString", jsonData);
		} catch (Exception ee) {
			setErrorMessage(ee);
			ee.printStackTrace();
		}
		return SUCCESS;
	}
	
	@Action(value = "selectdeptname", results = { @Result(name = "success", location = "/webpage/pub/grid/pager.jsp") })
	public String selectdeptname() {
		try {
			String info = "";
			List<DeptEntity> deptList = deptService.selectdeptname(this.getLoginUser().getOrgid());
			for (DeptEntity deptEntity : deptList) {
				info = deptEntity.getDeptname();
			}
			this.setJsonStr(info);
		} catch (Exception ee) {
			setErrorMessage(ee);
			ee.printStackTrace();
		}
		return SUCCESS;
	}
	@Action(value = "selectDept1", results = { @Result(name = "success", location = "/webpage/pub/select/dept/selectdept1.jsp") })
	public String selectDept1() {
		try {
			List<DeptEntity> deptList = deptService.getall1(bean.getDeptid());
			String jsonData = createJsonData(deptList);
			getRequest().setAttribute("jsonString", jsonData);
		} catch (Exception ee) {
			setErrorMessage(ee);
			ee.printStackTrace();
		}
		return SUCCESS;
	}

	@Action(value = "getRootDept", results = { @Result(name = "success", location = "/webpage/pub/jsonpager.jsp") })
	public String getRootDept() {
		try {
			// 根据登录用户所在部门orgid查询部门信息
			DeptEntity dept = deptService.selectById(getLoginUser().getOrgid());
			/*// 判断当前所在部门是否是组织。如果是组织，则当前所在部门就是根节点；如果不是组织，则向上递归查询父级为组织的节点，取最近的一个节点设置为根节点
			if ("0".equals(dept.getIsOrg())) {
				dept = deptService.selectById(bean.getParentdeptid());
			}*/
			List<DeptEntity> list = new ArrayList<DeptEntity>();
			list.add(dept);
			String jsonData = createJsonData(list);
			getRequest().setAttribute("jsonString", jsonData);
		} catch (Exception ee) {
			setErrorMessage(ee);
		}
		return SUCCESS;
	}
	@Action(value = "getRootDeptByDeptId", results = { @Result(name = "success", location = "/webpage/pub/jsonpager.jsp") })
	public String getRootDeptByDeptId() {
		try {
			// 根据登录用户所在部门orgid查询部门信息
			DeptEntity dept = deptService.selectById(bean.getDeptid());
			/*// 判断当前所在部门是否是组织。如果是组织，则当前所在部门就是根节点；如果不是组织，则向上递归查询父级为组织的节点，取最近的一个节点设置为根节点
			if ("0".equals(dept.getIsOrg())) {
				dept = deptService.selectById(bean.getParentdeptid());
			}*/
			List<DeptEntity> list = new ArrayList<DeptEntity>();
			list.add(dept);
			String jsonData = createJsonData(list);
			getRequest().setAttribute("jsonString", jsonData);
		} catch (Exception ee) {
			setErrorMessage(ee);
		}
		return SUCCESS;
	}

	@Action(value = "doTree", results = { @Result(name = "success", location = "/webpage/pub/jsondatas.jsp") })
	public String doTree() {
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

	@Action(value = "doTree1", results = { @Result(name = "success", location = "/webpage/pub/jsondatas.jsp") })
	public String doTree1() {
		try {
			List<DeptEntity> deptList = deptService.getall1(bean.getDeptid());
			String jsonData = createJsonData1(deptList);
			getRequest().setAttribute("jsonString", jsonData);
		} catch (Exception ee) {
			setErrorMessage(ee);
			ee.printStackTrace();
		}
		return "success";
	}
	
	@Action(value = "getDeptName", results = { @Result(name = "success", location = "/webpage/pub/jsondata.jsp") })
	public String getDeptName() {
		try {
			// 根据登录用户所在部门orgid查询部门信息
			DeptEntity dept = deptService.selectById(getLoginUser().getOrgid());
			getRequest().setAttribute("jsonStr", dept.getDeptname());
		} catch (Exception ee) {
			setErrorMessage(ee);
		}
		return SUCCESS;
	}

	private String createJsonData(List<DeptEntity> deptList) {
		StringBuffer jsonData = new StringBuffer();
		int length = deptList.size();
		if (length <= 0)
			return "";
		for (int i = 0; i < length; i++) {
			DeptEntity dept = (DeptEntity) deptList.get(i);
			jsonData.append("{\"id\":\"");
			jsonData.append(dept.getDeptid());
			jsonData.append("\",\"orgtype\":\"");
			jsonData.append(dept.getOrgtype());
			jsonData.append("\", \"pid\":\"");
			if (dept.getParentdeptid() == null || dept.getParentdeptid() == "")
				jsonData.append("");
			else
				jsonData.append(dept.getParentdeptid());
			jsonData.append("\", \"name\":\"");
			jsonData.append(dept.getDeptname());
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

	private String createJsonData1(List<DeptEntity> deptList) {
		StringBuffer jsonData = new StringBuffer();
		int length = deptList.size();
		if (length <= 0)
			return "";
		for (int i = 0; i < length; i++) {
			DeptEntity dept = (DeptEntity) deptList.get(i);
			jsonData.append("{\"id\":\"");
			jsonData.append(dept.getDeptid());
			jsonData.append("\",\"orgtype\":\"");
			jsonData.append(dept.getOrgtype());
			jsonData.append("\", \"pid\":\"");
			if (dept.getParentdeptid() == null || dept.getParentdeptid() == "")
				jsonData.append("");
			else
				jsonData.append(dept.getParentdeptid());
			jsonData.append("\", \"name\":\"");
			jsonData.append(dept.getDeptname());
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

	@Override
	public String getBiztypename() {
		return null;
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
