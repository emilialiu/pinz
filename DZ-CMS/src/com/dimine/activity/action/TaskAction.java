package com.dimine.activity.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.dimine.activity.entity.TaskEntity;
import com.dimine.activity.service.DMTaskService;
import com.dimine.base.action.BaseAction;
import com.dimine.base.util.SQLCallback;
import com.dimine.base.util.SQLTool;
import com.dimine.base.util.json.JSONUtil;

/**
 * 流程任务的action
 * @author SSM
 *
 */
@Namespace("/manager/task")
@Scope("request")
@Controller("taskAction")
public class TaskAction extends BaseAction {
	private String filters;
	private String param;
	private TaskEntity bean = new TaskEntity();
	@Resource
	private DMTaskService<TaskEntity> dmtaskService;
	@Action(value = "list", results = { @Result(name = "success", location = "/webpage/pub/grid/pager.jsp") })
    public String list() {
		try{
			 
			if(filters!=null){
				String query = new SQLTool().constructWhere(filters,
						new SQLCallback() {
							@Override
							public String executeField(String f) {
								if (f.equals("nameWithoutCascade"))
									return "a.NAME_";						
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
			bean.setUserid(this.getLoginUser().getUserid());
			List<TaskEntity> dataList = dmtaskService.selectByList(bean);
			// 设置页面数据
			Map<String, Object> jsonMap = new HashMap<String, Object>();
			jsonMap.put("page", bean.getPager().getPageId());
			jsonMap.put("total", bean.getPager().getPageCount());
			jsonMap.put("records", bean.getPager().getRowCount());
			jsonMap.put("rows", dataList);
			this.setJsonStr(JSONUtil.toJSONString(jsonMap));
		}catch (Exception e) {
			e.printStackTrace();
			this.setErrorMessage(e);
		}
        return SUCCESS;
    }
	/**
	 * 首页查看我的代办
	 * @return
	 */
	@Action(value = "doviewdealt", results = { @Result(name = "success", location = "/webpage/pub/jsondata.jsp") })
	public String doviewdealt(){
		try{
			Map<String, Object> jsonMap = new HashMap<String, Object>();
			bean.setUserid(this.getLoginUser().getUserid());
			Integer total = dmtaskService.selectByCount(bean);
			jsonMap.put("total",total);
			this.setJsonStr(JSONUtil.toJSONString(jsonMap));
		}catch (Exception e) {
			e.printStackTrace();
			this.setErrorMessage(e);
		}
		return SUCCESS;
		
	}
	@Action(value = "selectHispartList", results = { @Result(name = "success", location = "/webpage/pub/grid/pager.jsp") })
	public String selectHispartList(){
		try{
			 
			if(filters!=null){
				String query = new SQLTool().constructWhere(filters,
						new SQLCallback() {
							@Override
							public String executeField(String f) {
								if (f.equals("processname"))
									return "c.NAME_";	
								if (f.equals("processname"))
									return "c.NAME_";	
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
			bean.setUserid(this.getLoginUser().getUserid());
			bean.setAssigneeWithoutCascade(this.getLoginUser().getUserid());
			bean.setDeletereason("completed");
			List<TaskEntity> dataList = dmtaskService.selectHispartList(bean);
			// 设置页面数据
			Map<String, Object> jsonMap = new HashMap<String, Object>();
			jsonMap.put("page", bean.getPager().getPageId());
			jsonMap.put("total", bean.getPager().getPageCount());
			jsonMap.put("records", bean.getPager().getRowCount());
			jsonMap.put("rows", dataList);
			this.setJsonStr(JSONUtil.toJSONString(jsonMap));
		}catch (Exception e) {
			e.printStackTrace();
			this.setErrorMessage(e);
		}
		return SUCCESS;
	}
	
	@Action(value = "startTaskList", results = { @Result(name = "success", location = "/webpage/pub/grid/pager.jsp") })
	public String startTaskList(){
		try{
			 
			if(filters!=null){
				String query = new SQLTool().constructWhere(filters,
						new SQLCallback() {
							@Override
							public String executeField(String f) {
								if (f.equals("processname"))
									return "c.NAME_";	
								if (f.equals("processname"))
									return "c.NAME_";	
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
			bean.setUserid(this.getLoginUser().getUserid());
			bean.setDeletereason("completed");
			List<TaskEntity> dataList = dmtaskService.selectStartTaskList(bean);
			// 设置页面数据
			Map<String, Object> jsonMap = new HashMap<String, Object>();
			jsonMap.put("page", bean.getPager().getPageId());
			jsonMap.put("total", bean.getPager().getPageCount());
			jsonMap.put("records", bean.getPager().getRowCount());
			jsonMap.put("rows", dataList);
			this.setJsonStr(JSONUtil.toJSONString(jsonMap));
		}catch (Exception e) {
			e.printStackTrace();
			this.setErrorMessage(e);
		}
		return SUCCESS;
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
	public TaskEntity getBean() {
		return bean;
	}
	public void setBean(TaskEntity bean) {
		this.bean = bean;
	}
	public DMTaskService<TaskEntity> getDmtaskService() {
		return dmtaskService;
	}
	public void setDmtaskService(DMTaskService<TaskEntity> dmtaskService) {
		this.dmtaskService = dmtaskService;
	}
	
}
