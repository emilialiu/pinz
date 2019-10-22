package com.dimine.activity.action;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.runtime.ProcessInstance;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.dimine.activity.entity.TaskEntity;
import com.dimine.activity.service.DMTaskService;
import com.dimine.activity.util.Variable;
import com.dimine.base.action.BaseAction;

/**
 * 	节点处理Action
 * @author SSM
 *
 */
public abstract class BaseNodeAction extends BaseAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
    private TaskService taskService;
	 /*
	  * 参数属性
	 */
	private Variable variable;
	/**
	 * 任务id
	 */
	private String taskId;
	private String taskDefinitionKey;
	private String bussinessid;
	private String processInstanceId;
	@Autowired
	private HistoryService historyService;
	@Autowired
	private RuntimeService rutimeService;
	
	@Autowired
	private DMTaskService<TaskEntity> dmtaskService;
	
	private Map<String, Object> variables;
	private  HistoricActivityInstance hisacts;
	/**
     * 办理不是动态表单任务
     */
	@Action(value = "completenodyTask", results = { @Result(name = "success", location = "/webpage/pub/result.jsp") })
    public String completenodyTask() {
		try {
        	variables = variable.getVariableMap();
        	variables.put("deptid",this.getLoginUser().getOrgid());
        	variables.put("userid",this.getLoginUser().getUserid());
        	Map<String,Object> bizmap = getBizMap(taskDefinitionKey, bussinessid);
        	if ((Boolean) variables.get("nodepass")){
        		taskService.addComment(taskId,processInstanceId,"true");
        	}else{
        		taskService.addComment(taskId,processInstanceId,"false");
        	}
        	taskService.addComment(taskId,processInstanceId,(String) variables.get("comments"));
        	if(!bizmap.isEmpty()){
        		variables.putAll(bizmap);
        	}
        	taskService.complete(taskId, variables);
        	hisacts = historyService.createHistoricActivityInstanceQuery().activityType("endEvent").processInstanceId(processInstanceId).singleResult();
        	//驳回状态 结束流程
        	if (!(Boolean) variables.get("nodepass")){
        		ProcessInstance
        		pi = rutimeService.createProcessInstanceQuery()
                        .processInstanceId(processInstanceId).singleResult();
        		if(pi!=null){
          		   	//执行结束流程实例的方法
          		   	rutimeService.deleteProcessInstance(processInstanceId, "");
          	    }
        		updatebuiness(bussinessid, (Boolean)variables.get("nodepass"));
        	}else{
	    		HistoricVariableInstance His =historyService.createHistoricVariableInstanceQuery().variableName("nodepass").processInstanceId(processInstanceId).singleResult();
	            if(hisacts!=null){
	           	   	updatebuiness(bussinessid, (Boolean)His.getValue());
	            }
        	}
            this.setJsonStr("审批成功!");
        }catch (Exception e) {
			e.printStackTrace();
			this.setErrorMessage("审批失败!");
		}
        return SUCCESS;
    }
	/**
	 * 需要被继承类重写
	 * @param taskDefinitionKey 任务的定义key，
	 * @param bussinessid	业务id
	 * @return
	 */
	public abstract Map getBizMap(String taskDefinitionKey,String bussinessid) throws Exception;
	/**
	 * 
	 * @param buinnesskey 根据结束的时候的是否通过，修改业务表的审批撞他
	 * @param nodepass    审批结果
	 * @throws Exception
	 */
	public abstract void updatebuiness(String buinnesskey,boolean nodepass)throws Exception;
	public Variable getVariable() {
		return variable;
	}

	public void setVariable(Variable variable) {
		this.variable = variable;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getTaskDefinitionKey() {
		return taskDefinitionKey;
	}
	public void setTaskDefinitionKey(String taskDefinitionKey) {
		this.taskDefinitionKey = taskDefinitionKey;
	}
	public String getBussinessid() {
		return bussinessid;
	}
	public void setBussinessid(String bussinessid) {
		this.bussinessid = bussinessid;
	}
	public String getProcessInstanceId() {
		return processInstanceId;
	}
	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}
	public Map<String, Object> getVariables() {
		return variables;
	}
	public void setVariables(Map<String, Object> variables) {
		this.variables = variables;
	}
	public TaskService getTaskService() {
		return taskService;
	}
	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}
	public HistoryService getHistoryService() {
		return historyService;
	}
	public void setHistoryService(HistoryService historyService) {
		this.historyService = historyService;
	}
	public RuntimeService getRutimeService() {
		return rutimeService;
	}
	public void setRutimeService(RuntimeService rutimeService) {
		this.rutimeService = rutimeService;
	}
	public DMTaskService<TaskEntity> getDmtaskService() {
		return dmtaskService;
	}
	public void setDmtaskService(DMTaskService<TaskEntity> dmtaskService) {
		this.dmtaskService = dmtaskService;
	}
	public HistoricActivityInstance getHisacts() {
		return hisacts;
	}
	public void setHisacts(HistoricActivityInstance hisacts) {
		this.hisacts = hisacts;
	}
	
}
