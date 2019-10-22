package com.dimine.activity.entity;

import com.dimine.base.entity.BaseEntity;
/**
 * 流程任务运行时候的实体类
 * @author SSM
 *
 */
public class TaskEntity extends BaseEntity {
	private String id;
	private String revision;
	private String nameWithoutCascade;
	private String parentTaskIdWithoutCascade;
	private String descriptionWithoutCascade;
	private String priorityWithoutCascade;
	private String createTime;
	private String ownerWithoutCascade;
	private String assigneeWithoutCascade;
	private String delegationStateString;
	private String executionId;
	private String processInstanceId;
	private String processDefinitionId;
	private String taskDefinitionKeyWithoutCascade;
	private String dueDateWithoutCascade;
	private String categoryWithoutCascade;
	private String suspensionState;
	private String tenantId;
	private String formKey;
	private String uri;
	private String nodeaction;
	private String bussinesskey;
	private String userid;
	private String ywname;
	private String applyUserId="${applyUserId}";
	private String processname;
	private String appusername;
	/**手机端的访问界面的标识*/
	private String mobileinfouri;
	/**手机端的处理待办的action**/
	private String mobilenodeaction;
	/**删除原因**/
	private String deletereason;
	/**审批是否通过**/
	private String comment1;
	/**审批意见**/
	private String comment2;
	private String username;
	private String endtime;
	private String starttime;
	private String duration;
	private String isend;
	
	public String getRevision() {
		return revision;
	}
	public void setRevision(String revision) {
		this.revision = revision;
	}
	public String getNameWithoutCascade() {
		return nameWithoutCascade;
	}
	public void setNameWithoutCascade(String nameWithoutCascade) {
		this.nameWithoutCascade = nameWithoutCascade;
	}
	public String getParentTaskIdWithoutCascade() {
		return parentTaskIdWithoutCascade;
	}
	public void setParentTaskIdWithoutCascade(String parentTaskIdWithoutCascade) {
		this.parentTaskIdWithoutCascade = parentTaskIdWithoutCascade;
	}
	public String getDescriptionWithoutCascade() {
		return descriptionWithoutCascade;
	}
	public void setDescriptionWithoutCascade(String descriptionWithoutCascade) {
		this.descriptionWithoutCascade = descriptionWithoutCascade;
	}
	public String getPriorityWithoutCascade() {
		return priorityWithoutCascade;
	}
	public void setPriorityWithoutCascade(String priorityWithoutCascade) {
		this.priorityWithoutCascade = priorityWithoutCascade;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getOwnerWithoutCascade() {
		return ownerWithoutCascade;
	}
	public void setOwnerWithoutCascade(String ownerWithoutCascade) {
		this.ownerWithoutCascade = ownerWithoutCascade;
	}
	public String getAssigneeWithoutCascade() {
		return assigneeWithoutCascade;
	}
	public void setAssigneeWithoutCascade(String assigneeWithoutCascade) {
		this.assigneeWithoutCascade = assigneeWithoutCascade;
	}
	public String getDelegationStateString() {
		return delegationStateString;
	}
	public void setDelegationStateString(String delegationStateString) {
		this.delegationStateString = delegationStateString;
	}
	public String getExecutionId() {
		return executionId;
	}
	public void setExecutionId(String executionId) {
		this.executionId = executionId;
	}
	public String getProcessInstanceId() {
		return processInstanceId;
	}
	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}
	public String getProcessDefinitionId() {
		return processDefinitionId;
	}
	public void setProcessDefinitionId(String processDefinitionId) {
		this.processDefinitionId = processDefinitionId;
	}
	public String getTaskDefinitionKeyWithoutCascade() {
		return taskDefinitionKeyWithoutCascade;
	}
	public void setTaskDefinitionKeyWithoutCascade(
			String taskDefinitionKeyWithoutCascade) {
		this.taskDefinitionKeyWithoutCascade = taskDefinitionKeyWithoutCascade;
	}
	public String getDueDateWithoutCascade() {
		return dueDateWithoutCascade;
	}
	public void setDueDateWithoutCascade(String dueDateWithoutCascade) {
		this.dueDateWithoutCascade = dueDateWithoutCascade;
	}
	public String getCategoryWithoutCascade() {
		return categoryWithoutCascade;
	}
	public void setCategoryWithoutCascade(String categoryWithoutCascade) {
		this.categoryWithoutCascade = categoryWithoutCascade;
	}
	public String getSuspensionState() {
		return suspensionState;
	}
	public void setSuspensionState(String suspensionState) {
		this.suspensionState = suspensionState;
	}
	public String getTenantId() {
		return tenantId;
	}
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	public String getFormKey() {
		return formKey;
	}
	public void setFormKey(String formKey) {
		this.formKey = formKey;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String getNodeaction() {
		return nodeaction;
	}
	public void setNodeaction(String nodeaction) {
		this.nodeaction = nodeaction;
	}
	public String getBussinesskey() {
		return bussinesskey;
	}
	public void setBussinesskey(String bussinesskey) {
		this.bussinesskey = bussinesskey;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getYwname() {
		return ywname;
	}
	public void setYwname(String ywname) {
		this.ywname = ywname;
	}
	public String getApplyUserId() {
		return "${applyUserId}";
	}
	public void setApplyUserId(String applyUserId) {
		this.applyUserId = applyUserId;
	}
	public String getProcessname() {
		return processname;
	}
	public void setProcessname(String processname) {
		this.processname = processname;
	}
	public String getAppusername() {
		return appusername;
	}
	public void setAppusername(String appusername) {
		this.appusername = appusername;
	}
	public String getMobileinfouri() {
		return mobileinfouri;
	}
	public void setMobileinfouri(String mobileinfouri) {
		this.mobileinfouri = mobileinfouri;
	}
	public String getMobilenodeaction() {
		return mobilenodeaction;
	}
	public void setMobilenodeaction(String mobilenodeaction) {
		this.mobilenodeaction = mobilenodeaction;
	}
	public String getDeletereason() {
		return deletereason;
	}
	public void setDeletereason(String deletereason) {
		this.deletereason = deletereason;
	}
	public String getComment1() {
		return comment1;
	}
	public void setComment1(String comment1) {
		this.comment1 = comment1;
	}
	public String getComment2() {
		return comment2;
	}
	public void setComment2(String comment2) {
		this.comment2 = comment2;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getIsend() {
		return isend;
	}
	public void setIsend(String isend) {
		this.isend = isend;
	}
	
	
}
