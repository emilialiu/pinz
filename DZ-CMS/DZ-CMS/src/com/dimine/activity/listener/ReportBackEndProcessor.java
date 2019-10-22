package com.dimine.activity.listener;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.dimine.activity.entity.LeaveEntity;
import com.dimine.activity.service.LeaveWorkflowService;

/**
 * 销假后处理器
 * <p>设置销假时间</p>
 * <p>使用Spring代理，可以注入Bean，管理事物</p>
 *
 * @author HenryYan
 */
@Controller("reportBackEndProcessor")
public class ReportBackEndProcessor implements TaskListener {

    private static final long serialVersionUID = 1L;

    @Autowired
    LeaveWorkflowService<LeaveEntity> leaveWorkflowService;

    @Autowired
    RuntimeService runtimeService;

    /* (non-Javadoc)
     * @see org.activiti.engine.delegate.TaskListener#notify(org.activiti.engine.delegate.DelegateTask)
     */
    public void notify(DelegateTask delegateTask) {
        try{
        String processInstanceId = delegateTask.getProcessInstanceId();
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        LeaveEntity leave = leaveWorkflowService.findById(processInstance.getBusinessKey());

        Object realityStartTime = delegateTask.getVariable("realityStartTime");
        leave.setRealityStartTime(realityStartTime.toString());
        Object realityEndTime = delegateTask.getVariable("realityEndTime");
        leave.setRealityEndTime(realityEndTime.toString());

        leaveWorkflowService.update(leave);
        }catch (Exception e) {
        	e.printStackTrace();
		}
    }

	public void setLeaveWorkflowService(
			LeaveWorkflowService<LeaveEntity> leaveWorkflowService) {
		this.leaveWorkflowService = leaveWorkflowService;
	}
    
}
