package com.dimine.bi.action;

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
import com.dimine.base.util.SQLCallback;
import com.dimine.base.util.SQLTool;
import com.dimine.base.util.StringUtils;
import com.dimine.base.util.json.JSONUtil;
import com.dimine.bi.entity.ProjectinfoEntity;
import com.dimine.bi.service.ProjectinfoService;

/**
 * 用于对作业地点进行系列的操作的action
 * 
 * @author dimine 2015-08-26 17:26:56
 * 
 */
@Namespace("/biz/bi/projectinfo")
@Scope("request")
@Controller("projectinfoAction")
public class ProjectinfoAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private final static Logger logger = Logger
			.getLogger(ProjectinfoAction.class);
	// 业务处理
	@Resource
	private ProjectinfoService<ProjectinfoEntity> projectinfoService;

	// 参数实体
	private ProjectinfoEntity bean = new ProjectinfoEntity();
	/**
	 * 添加类型 addmore为新增保存
	 */
	private String addtype;

	private String somename;

	private String encode;

	private String param;

	private String filters;

	@Action(value = "tree", results = { @Result(name = "success", location = "/webpage/pub/jsonpager.jsp") })
	public String tree() {
		try {
			if (StringUtils.isNotBlank(bean.getProjectid())) {
				bean.setDeptid(getLoginUser().getOrgid());
			}
			List<ProjectinfoEntity> porjectList = projectinfoService
					.getall(bean);
			String jsonData = createJsonData(porjectList);
			getRequest().setAttribute("jsonString", jsonData);
		} catch (Exception ee) {
			setErrorMessage(ee);
			ee.printStackTrace();
		}
		return "success";
	}

	@Action(value = "doTree", results = { @Result(name = "success", location = "/webpage/pub/jsondatas.jsp") })
	public String doTree() {
		try {
			if (StringUtils.isNotBlank(bean.getProjectid())) {
				bean.setDeptid(getLoginUser().getOrgid());
			}
			List<ProjectinfoEntity> porjectList = projectinfoService
					.getall(bean);
			String jsonData = createJsonData(porjectList);
			getRequest().setAttribute("jsonString", jsonData);
		} catch (Exception ee) {
			setErrorMessage(ee);
			ee.printStackTrace();
		}
		return "success";
	}
	
	/**
	 * 得到所有台阶
	 * @return
	 */
	@Action(value = "doTree1", results = { @Result(name = "success", location = "/webpage/pub/jsondatas.jsp") })
	public String doTree1() {
		try {
			if (StringUtils.isNotBlank(bean.getProjectid())) {
				bean.setDeptid(getLoginUser().getOrgid());
			}
			List<ProjectinfoEntity> porjectList = projectinfoService
					.getGCJB(bean);
			String jsonData = createJsonData(porjectList);
			getRequest().setAttribute("jsonString", jsonData);
		} catch (Exception ee) {
			setErrorMessage(ee);
			ee.printStackTrace();
		}
		return "success";
	}

	/**
	 * 新增作业地点信息保存 <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@Action(value = "add", results = { @Result(name = "success", location = "/webpage/pub/jsonpager.jsp") })
	public String doAddSave() {
		try {
			bean.setCreateid(getLoginUser().getUserid());
			bean.setDeptid(getLoginUser().getOrgid());
			String projectid = projectinfoService.insert(bean);
			StringBuffer result_string = new StringBuffer("{\"projectid\":\"");
			result_string.append(projectid);
			result_string.append("\"}");
			getRequest().setAttribute("jsonString", result_string.toString());
		} catch (ValidException ee) {
			this.setErrorMessage(ee);
			logger.error(ee);
		} catch (Exception ex) {
			this.setErrorMessage(ex.getMessage());
			logger.error(ex);
		}
		return SUCCESS;
	}

	/**
	 * 删除作业地点信息 <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@Action(value = "delete", results = {
			@Result(name = "success", location = "/webpage/pub/result.jsp"),
			@Result(name = "modify_success", location = "/webpage/pub/result.jsp") })
	public String doDelete() {
		try {
			projectinfoService.deleteProject(bean);
		} catch (Exception ee) {
			setErrorMessage(ee.getMessage());
		}
		return "success";
	}

	/**
	 * 对作业地点进行修改查询操作
	 */
	@Action(value = "modify", results = {
			@Result(name = "modify", location = "/webpage/pub/jsonpager.jsp"),
			@Result(name = "modify_success", location = "/webpage/pub/result.jsp") })
	public String doModify() {
		String result = "modify";
		try {
			if ("modifysave".equals(getActiontype())) {
				result = "modify_success";
				bean.setModifyid(getLoginUser().getUserid());
				bean.setDeptid(getLoginUser().getOrgid());
				projectinfoService.update(bean, "modifysave");
			} else {
				bean = projectinfoService.selectById(bean.getProjectid());
				getRequest().setAttribute("jsonString",
						createInfoData(bean, "modifysave"));
			}
		} catch (Exception ee) {
			setErrorMessage(ee.getMessage());
			ee.printStackTrace();
		}
		return result;
	}

	private String createJsonData(List<ProjectinfoEntity> projectList) {
		StringBuffer jsonData = new StringBuffer();
		int length = projectList.size();
		if (length <= 0)
			return "";
		for (int i = 0; i < length; i++) {
			ProjectinfoEntity project = projectList.get(i);
			jsonData.append("{\"id\":\"");
			jsonData.append(project.getProjectid());
			jsonData.append("\", \"pid\":\"");
			if (project.getParentitemid() == null
					|| project.getParentitemid() == "")
				jsonData.append("");
			else
				jsonData.append(project.getParentitemid());
			jsonData.append("\", \"name\":\"");
			jsonData.append(project.getProjectname());
			jsonData.append("\", \"projectlevel\":\"");
			jsonData.append(project.getProjectlevel());
			jsonData.append("\", \"isleaf\":\"");
			jsonData.append(project.getIsleaf());
			if (Integer.valueOf(project.getChildCount()).intValue() >= 1) {
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

	private String createInfoData(ProjectinfoEntity project, String actiontype) {
		StringBuffer jsonData = new StringBuffer("{");
		jsonData.append("\"parentitemid\":\"");
		jsonData.append(project.getParentitemid());
		jsonData.append("\", \"projectid\":\"");
		jsonData.append(project.getProjectid() != null ? project.getProjectid()
				: "");
		jsonData.append("\", \"actiontype\":\"");
		jsonData.append(actiontype);
		jsonData.append("\", \"projectname\":\"");
		jsonData.append(project.getProjectname() != null ? project
				.getProjectname() : "");
		jsonData.append("\", \"projectcode\":\"");
		jsonData.append(project.getProjectcode() != null ? project
				.getProjectcode() : "");
		jsonData.append("\", \"isleaf\":\"");
		jsonData.append(project.getIsleaf() != null ? project.getIsleaf() : "");
		jsonData.append("\", \"projectstatus\":\"");
		jsonData.append(project.getProjectstatus() != null ? project
				.getProjectstatus() : "");
		jsonData.append("\", \"projectlevel\":\"");
		jsonData.append(project.getProjectlevel() != null ? project
				.getProjectlevel() : "");
		jsonData.append("\", \"indexno\":\"");
		jsonData.append(project.getIndexno() != null ? project.getIndexno()
				: "");
		jsonData.append("\", \"memo\":\"");
		jsonData.append(project.getMemo() != null ? project.getMemo() : "");
		jsonData.append("\", \"parentname\":\"");
		jsonData.append(project.getParentname() != null ? project
				.getParentname() : "");
		jsonData.append("\"}");
		return jsonData.toString();
	}

	/**
	 * 对作业地点进行列表查询操作
	 * 
	 * @return
	 */
	@Action(value = "list", results = { @Result(name = "success", location = "/webpage/pub/grid/pager.jsp") })
	public String list() {
		try {
			if (filters != null) {
				String query = new SQLTool().constructWhere(filters,
						new SQLCallback() {
							@Override
							public String executeField(String f) {
								if (f.equals("projectname"))
									return "p.projectname";
								if (f.equals("projectamount3"))
									return "i.projectamount";
								if (f.equals("projectAmount"))
									return "o.ProjectAmount";
								if (f.equals("rdtype"))
									return "d1.paramname";
								if (f.equals("jjtype"))
									return "d2.paramname";
								if (f.equals("rockHardness"))
									return "d3.paramname";
								if (f.equals("mineMethod"))
									return "d4.paramname";
								if (f.equals("oreType"))
									return "d5.paramname";
								if (f.equals("rockHardness1"))
									return "d6.paramname";
								if (f.equals("units"))
									return "d7.paramname";
								if (f.equals("installtype"))
									return "d8.paramname";
								if (f.equals("units3"))
									return "d9.paramname";
								return f;
							}

							@Override
							public String executeData(String f, String o,
									String d) {
								if (o.equals("bw") || o.equals("en"))
									return (new StringBuilder("'")).append(d)
											.append("%'").toString();
								if (o.equals("ew") || o.equals("en"))
									return (new StringBuilder("'%")).append(d)
											.append("'").toString();
								if (o.equals("cn") || o.equals("nc")) {
									if (d.contains("%")) {
										String y = "";
										char[] b = d.toCharArray();
										for (char c : b) {
											if (c == '%') {
												String e = "\\" + c;
												y += e;
											} else {
												y += c;
											}
										}
										d = y;
									}
									return (new StringBuilder("'%")).append(d)
											.append("%'").toString();
								} else
									return (new StringBuilder("'")).append(d)
											.append("'").toString();
							}
						});
				bean.setQuery(query);
			}
			bean.setPager(this.getPager());
			List<ProjectinfoEntity> dataList = projectinfoService
					.selectByList(bean);
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

	// 日志文件用
	@Override
	public String getBiztypename() {
		return "作业地点信息管理";
	}

	public ProjectinfoEntity getBean() {
		return bean;
	}

	public void setBean(ProjectinfoEntity bean) {
		this.bean = bean;
	}

	public ProjectinfoService<ProjectinfoEntity> getProjectinfoService() {
		return projectinfoService;
	}

	public void setProjectinfoService(
			ProjectinfoService<ProjectinfoEntity> projectinfoService) {
		this.projectinfoService = projectinfoService;
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
