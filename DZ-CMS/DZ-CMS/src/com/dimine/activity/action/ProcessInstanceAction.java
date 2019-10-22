package com.dimine.activity.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.dimine.activity.entity.ProcessInstanceEntity;
import com.dimine.activity.service.ProcessInstanceService;
import com.dimine.base.action.BaseAction;
import com.dimine.base.util.SQLCallback;
import com.dimine.base.util.SQLTool;
import com.dimine.base.util.json.JSONUtil;

@Namespace("/manager/processInstance")
@Scope("request")
@Controller("processInstanceAction")
public class ProcessInstanceAction extends BaseAction {

	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private RepositoryService repositoryService;
	@Resource
	private ProcessInstanceService<ProcessInstanceEntity> processInstanceService;
	private ProcessInstanceEntity bean = new ProcessInstanceEntity();
	private String state;
	private String processInstanceId;
	// private WorkflowEntity bean = new WorkflowEntity();
	private String filters;
	private String param;

	@Action(value = "list", results = { @Result(name = "success", location = "/webpage/pub/grid/pager.jsp") })
	public String list() {
		try {

			if (filters != null) {
				String query = new SQLTool().constructWhere(filters,
						new SQLCallback() {
							@Override
							public String executeField(String f) {
								if (f.equals("processname"))
									return "p.NAME_";
								if (f.equals("ywname"))
									return "u.TEXT_";
								if (f.equals("appusername"))
									return "st.StaffName";
								if (f.equals("taskname"))
									return "ru.NAME_";
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
								if (o.equals("cn") || o.equals("nc"))
									return (new StringBuilder("'%")).append(d)
											.append("%'").toString();
								else
									return (new StringBuilder("'")).append(d)
											.append("'").toString();
							}
						});
				bean.setQuery(query);
			}
			bean.setPager(this.getPager());

			List<ProcessInstanceEntity> dataList = processInstanceService
					.selectByList(bean);
			// 设置页面数据
			Map<String, Object> jsonMap = new HashMap<String, Object>();
			jsonMap.put("page", bean.getPager().getPageId());
			jsonMap.put("total", bean.getPager().getPageCount());
			jsonMap.put("records", bean.getPager().getRowCount());
			jsonMap.put("rows", dataList);
			this.setJsonStr(JSONUtil.toJSONString(jsonMap));
		} catch (Exception e) {
			e.printStackTrace();
			this.setErrorMessage(e);
		}
		return SUCCESS;
	}

	/**
	 * 挂起、激活流程实例
	 */
	@Action(value = "updateState", results = { @Result(name = "success", location = "/webpage/pub/result.jsp") })
	public String updateState() {
		try {
			if (state.equals("active")) {
				this.setJsonStr(getText("dmmes.sys.processinstance.messageactivated")+"[" + processInstanceId + "]"+getText("dmmes.sys.processinstance.processdefinition"));
				runtimeService.activateProcessInstanceById(processInstanceId);
			} else if (state.equals("suspend")) {
				runtimeService.suspendProcessInstanceById(processInstanceId);
				this.setJsonStr(getText("dmmes.sys.processinstance.messagesuspended")+"[" + processInstanceId + "]"+getText("dmmes.sys.processinstance.processdefinition"));
			}
		} catch (Exception e) {
			this.setErrorMessage(e);
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 查询出我参与的正在执行的程序
	 * 
	 * @return
	 */
	public String listrunning() {
		runtimeService.createProcessInstanceQuery()
				.involvedUser(this.getLoginUser().getUserid()).list();
		return SUCCESS;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public ProcessInstanceEntity getBean() {
		return bean;
	}

	public void setBean(ProcessInstanceEntity bean) {
		this.bean = bean;
	}

	public String getFilters() {
		return filters;
	}

	public void setFilters(String filters) {
		this.filters = filters;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

}
