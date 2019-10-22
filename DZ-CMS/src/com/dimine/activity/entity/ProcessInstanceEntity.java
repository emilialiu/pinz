package com.dimine.activity.entity;

import com.dimine.base.entity.BaseEntity;
/**
 * activiti 运行中的流程实体类
 * @author Administrator
 *
 */
public class ProcessInstanceEntity extends BaseEntity {
	private String revision;
	private String processInstanceId;
	private String businessKey;
	private String processDefinitionId;
	private String activityId;
	private String isActive;
	private String isConcurrent;
	private String isScope;
	private String isEventScope;
	private String parentId;
	private String superExecutionId;
	private String suspensionState;
	private String cachedEntityState;
	private String tenantId;
	private String name;
	private String processdefinitionkey;
	private String processDefinitionid;
	private String processname;
	private String ywname;
	private String appusername;
	private String taskname;
	private String id;
	private String applyUserId="${applyUserId}";
	private Boolean issuspended;
	//任务创建日期
	private String  createtime;
	public String getRevision() {
		return revision;
	}
	public void setRevision(String revision) {
		this.revision = revision;
	}
	public String getProcessInstanceId() {
		return processInstanceId;
	}
	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}
	public String getBusinessKey() {
		return businessKey;
	}
	public void setBusinessKey(String businessKey) {
		this.businessKey = businessKey;
	}
	public String getProcessDefinitionId() {
		return processDefinitionId;
	}
	public void setProcessDefinitionId(String processDefinitionId) {
		this.processDefinitionId = processDefinitionId;
	}
	public String getActivityId() {
		return activityId;
	}
	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public String getIsConcurrent() {
		return isConcurrent;
	}
	public void setIsConcurrent(String isConcurrent) {
		this.isConcurrent = isConcurrent;
	}
	public String getIsScope() {
		return isScope;
	}
	public void setIsScope(String isScope) {
		this.isScope = isScope;
	}
	public String getIsEventScope() {
		return isEventScope;
	}
	public void setIsEventScope(String isEventScope) {
		this.isEventScope = isEventScope;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getSuperExecutionId() {
		return superExecutionId;
	}
	public void setSuperExecutionId(String superExecutionId) {
		this.superExecutionId = superExecutionId;
	}
	public String getSuspensionState() {
		return suspensionState;
	}
	public void setSuspensionState(String suspensionState) {
		this.suspensionState = suspensionState;
	}
	public String getCachedEntityState() {
		return cachedEntityState;
	}
	public void setCachedEntityState(String cachedEntityState) {
		this.cachedEntityState = cachedEntityState;
	}
	public String getTenantId() {
		return tenantId;
	}
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProcessdefinitionkey() {
		return processdefinitionkey;
	}
	public void setProcessdefinitionkey(String processdefinitionkey) {
		this.processdefinitionkey = processdefinitionkey;
	}
	public String getProcessDefinitionid() {
		return processDefinitionid;
	}
	public void setProcessDefinitionid(String processDefinitionid) {
		this.processDefinitionid = processDefinitionid;
	}
	public String getProcessname() {
		return processname;
	}
	public void setProcessname(String processname) {
		this.processname = processname;
	}
	public String getYwname() {
		return ywname;
	}
	public void setYwname(String ywname) {
		this.ywname = ywname;
	}
	public String getAppusername() {
		return appusername;
	}
	public void setAppusername(String appusername) {
		this.appusername = appusername;
	}
	public String getTaskname() {
		return taskname;
	}
	public void setTaskname(String taskname) {
		this.taskname = taskname;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getApplyUserId() {
		return "${applyUserId}";
	}
	public void setApplyUserId(String applyUserId) {
		this.applyUserId = applyUserId;
	}
	public Boolean getIssuspended() {
		return issuspended;
	}
	public void setIssuspended(Boolean issuspended) {
		this.issuspended = issuspended;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	
	
}
