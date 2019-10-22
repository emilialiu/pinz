package com.dimine.bi.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.dimine.base.action.BaseAction;
import com.dimine.base.util.json.JSONUtil;
import com.dimine.bi.entity.ProjectinfoEntity;
import com.dimine.bi.entity.TechnologyProjectEntity;
import com.dimine.bi.service.TechnologyProjectService;

/**
 * 用于对工艺对应工程信息进行系列的操作的action
 * 
 * @author dimine 2016-08-23 10:58:32
 * 
 */
@Namespace("/biz/bi/technologyProject")
@Scope("request")
@Controller("technologyProjectAction")
public class TechnologyProjectAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private final static Logger logger = Logger
			.getLogger(TechnologyProjectAction.class);
	// 业务处理
	@Resource
	private TechnologyProjectService<TechnologyProjectEntity> technologyProjectService;

	// 参数实体
	private ProjectinfoEntity bean = new ProjectinfoEntity();
	private TechnologyProjectEntity technologyProject = new TechnologyProjectEntity();
	/**
	 * 添加类型 addmore为新增保存
	 */
	private String addtype;

	private String somename;

	private String encode;

	private String param;

	private String filters;
	private String pids;// 没有选择子节点的中段或者分段id

	/**
	 * 作业地点分配
	 * 
	 * @return
	 */
	@Action(value = "addTechnologyProject", results = { @Result(name = "success", location = "/webpage/pub/result.jsp") })
	public String addAreaProject() {
		try {
			technologyProject.setTdeptid(getLoginUser().getOrgid());
			technologyProjectService.addAreaProject(param.split(","),
					pids.split(","), technologyProject);
		} catch (Exception ee) {
			this.setErrorMessage(ee);
		}
		return SUCCESS;
	}

	/**
	 * 作业地点撤销
	 * 
	 * @return
	 */
	@Action(value = "delTechnologyProject", results = { @Result(name = "success", location = "/webpage/pub/result.jsp") })
	public String delAreaProject() {
		try {
			technologyProject.setTdeptid(getLoginUser().getOrgid());
			technologyProjectService.delAreaProject(param.split(","),
					technologyProject);
		} catch (Exception ee) {
			this.setErrorMessage(ee);
		}
		return SUCCESS;
	}

	/**
	 * 已分配作业地点列表
	 * 
	 * @return
	 */
	@Action(value = "rolepopedomlist", results = { @Result(name = "success", location = "/webpage/pub/jsonpager.jsp") })
	public String doList() {
		try {
			bean.setDeptid(getLoginUser().getOrgid());
			List<ProjectinfoEntity> dataList = technologyProjectService
					.yiFenPei(bean);
			String jsonData = createJsonData(dataList);
			getRequest().setAttribute("jsonString", jsonData);
		} catch (Exception ee) {
			this.setErrorMessage(ee);
		}

		return SUCCESS;
	}

	/**
	 * 未分配作业地点列表
	 * 
	 * @return
	 */
	@Action(value = "rolepopedomunlist", results = { @Result(name = "success", location = "/webpage/pub/jsonpager.jsp") })
	public String doUnList() {
		try {
			bean.setDeptid(getLoginUser().getOrgid());
			List<ProjectinfoEntity> undataList = technologyProjectService
					.weiFenPei(bean);
			String jsonData = createJsonData(undataList);
			getRequest().setAttribute("jsonString", jsonData);
		} catch (Exception e) {
			this.setErrorMessage(e);
		}
		return SUCCESS;
	}

	/**
	 * 删除工区对应工程信息 <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@Action(value = "delete", results = { @Result(name = "success", location = "/webpage/pub/beanresult.jsp") })
	public String doDelete() {
		try {
			technologyProjectService.delete(technologyProject);
		} catch (Exception ex) {
			bean.setErrorMessage(ex.getMessage());
			logger.error(ex);
		}
		this.setJsonStr(JSONUtil.toJSONString(bean));
		return SUCCESS;
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

	// 日志文件用
	@Override
	public String getBiztypename() {
		return "工区对应工程信息管理";
	}

	public TechnologyProjectService<TechnologyProjectEntity> getTechnologyProjectService() {
		return technologyProjectService;
	}

	public void setTechnologyProjectService(
			TechnologyProjectService<TechnologyProjectEntity> technologyProjectService) {
		this.technologyProjectService = technologyProjectService;
	}

	public TechnologyProjectEntity getTechnologyProject() {
		return technologyProject;
	}

	public void setTechnologyProject(TechnologyProjectEntity technologyProject) {
		this.technologyProject = technologyProject;
	}

	public ProjectinfoEntity getBean() {
		return bean;
	}

	public void setBean(ProjectinfoEntity bean) {
		this.bean = bean;
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

	public String getPids() {
		return pids;
	}

	public void setPids(String pids) {
		this.pids = pids;
	}

}
