package com.dimine.activity.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.dimine.activity.dao.LeaveDao;
import com.dimine.activity.entity.LeaveEntity;
import com.dimine.base.dao.BaseDao;
import com.dimine.base.service.BaseService;

/**
 * 请假流程Service
 *
 * @author HenryYan
 */
@Component
@Transactional
public class LeaveWorkflowService<T> extends BaseService<T> {

    private static Logger logger = LoggerFactory.getLogger(LeaveWorkflowService.class);

    //private LeaveDao<LeaveEntity> leaveManager;

    private RuntimeService runtimeService;

    protected TaskService taskService;

    protected HistoryService historyService;

    protected RepositoryService repositoryService;

    @Autowired
    private IdentityService identityService;
    @Autowired
	private LeaveDao<T> dao;

    /**
     * 启动流程
     *
     * @param entity
     */
    public ProcessInstance startWorkflow(LeaveEntity entity, Map<String, Object> variables) {
        dao.insert((T) entity);
        logger.debug("save entity: {}", entity);
        String businessKey = entity.getId()+"";

        ProcessInstance processInstance = null;
        try {
            // 用来设置启动流程的人员ID，引擎会自动把用户ID保存到activiti:initiator中
            identityService.setAuthenticatedUserId(entity.getUserId());

            processInstance = runtimeService.startProcessInstanceByKey("leave", businessKey, variables);
            String processInstanceId = processInstance.getId();
            entity.setProcessInstanceId(processInstanceId);
            logger.debug("start process of {key={}, bkey={}, pid={}, variables={}}", new Object[]{"leave", businessKey, processInstanceId, variables});
        } finally {
            identityService.setAuthenticatedUserId(null);
        }
        return processInstance;
    }

   

//    /**
//     * 读取运行中的流程
//     *
//     * @return
//     */
//    @Transactional(readOnly = true)
//    public List<Leave> findRunningProcessInstaces(Page<Leave> page, int[] pageParams) {
//        List<Leave> results = new ArrayList<Leave>();
//        ProcessInstanceQuery query = runtimeService.createProcessInstanceQuery().processDefinitionKey("leave").active().orderByProcessInstanceId().desc();
//        List<ProcessInstance> list = query.listPage(pageParams[0], pageParams[1]);
//
//        // 关联业务实体
//        for (ProcessInstance processInstance : list) {
//            String businessKey = processInstance.getBusinessKey();
//            if (businessKey == null) {
//                continue;
//            }
//            Leave leave = leaveManager.getLeave(new Long(businessKey));
//            leave.setProcessInstance(processInstance);
//            leave.setProcessDefinition(getProcessDefinition(processInstance.getProcessDefinitionId()));
//            results.add(leave);
//
//            // 设置当前任务信息
//            List<Task> tasks = taskService.createTaskQuery().processInstanceId(processInstance.getId()).active().orderByTaskCreateTime().desc().listPage(0, 1);
//            leave.setTask(tasks.get(0));
//        }
//
//        page.setTotalCount(query.count());
//        page.setResult(results);
//        return results;
//    }

//    /**
//     * 读取已结束中的流程
//     *
//     * @return
//     */
//    @Transactional(readOnly = true)
//    public List<Leave> findFinishedProcessInstaces(Page<Leave> page, int[] pageParams) {
//        List<Leave> results = new ArrayList<Leave>();
//        HistoricProcessInstanceQuery query = historyService.createHistoricProcessInstanceQuery().processDefinitionKey("leave").finished().orderByProcessInstanceEndTime().desc();
//        List<HistoricProcessInstance> list = query.listPage(pageParams[0], pageParams[1]);
//
//        // 关联业务实体
//        for (HistoricProcessInstance historicProcessInstance : list) {
//            String businessKey = historicProcessInstance.getBusinessKey();
//            Leave leave = leaveManager.getLeave(new Long(businessKey));
//            leave.setProcessDefinition(getProcessDefinition(historicProcessInstance.getProcessDefinitionId()));
//            leave.setHistoricProcessInstance(historicProcessInstance);
//            results.add(leave);
//        }
//        page.setTotalCount(query.count());
//        page.setResult(results);
//        return results;
//    }
      public LeaveEntity findById(String id){
    	  return (LeaveEntity) dao.selectById(id);
      }
    /**
     * 查询流程定义对象
     *
     * @param processDefinitionId 流程定义ID
     * @return
     */
    public  ProcessDefinition getProcessDefinition(String processDefinitionId) {
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).singleResult();
        return processDefinition;
    }

  
    @Autowired
    public void setRuntimeService(RuntimeService runtimeService) {
        this.runtimeService = runtimeService;
    }

    @Autowired
    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

    @Autowired
    public void setHistoryService(HistoryService historyService) {
        this.historyService = historyService;
    }

    @Autowired
    public void setRepositoryService(RepositoryService repositoryService) {
        this.repositoryService = repositoryService;
    }

	@Override
	public BaseDao<T> getDao() {
		// TODO Auto-generated method stub
		return dao;
	}

}
