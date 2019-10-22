package com.dimine.activity.service;

import java.util.Map;

import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dimine.activity.entity.BuinessProcessEntity;
import com.dimine.sys.entity.Sys_wfparamEntity;
import com.dimine.sys.service.Sys_wfparamService;
/**
 * ACtivityService的相关的操作
 * @author Administrator
 *
 */
@Service("activityService")
public class ActivityService {
	private static Logger logger = LoggerFactory.getLogger(ActivityService.class);
	@Autowired
	private RuntimeService runtimeService;
    @Autowired
    private IdentityService identityService;
    @Autowired
    private RepositoryService repositoryService;
    private Sys_wfparamService<Sys_wfparamEntity> sys_wfparamService;
    @Autowired
    private BuinessProcessService<BuinessProcessEntity>  buinessProcessService;
	/**
	 * 流程启动方法
	 * @param userid         用户id
	 * @param processid      流程定义的id
	 * @param businessKey    业务id
	 * @param variables      传入流程的一些参数
	 * @return
	 */
    
	public ProcessInstance startWorkflow(String userid,String processid,String businessKey, Map<String, Object> variables) throws Exception {
        ProcessInstance processInstance = null;
        try {
            // 用来设置启动流程的人员ID，引擎会自动把用户ID保存到activiti:initiator中
            identityService.setAuthenticatedUserId(userid);
            variables.put("startuserid",userid);
            processInstance = runtimeService.startProcessInstanceByKey(processid, businessKey, variables);
            String processInstanceId = processInstance.getId();
            logger.debug("start process of {key={}, bkey={}, pid={}, variables={}}", new Object[]{processid, businessKey, processInstanceId, variables});
          
        }catch (Exception e) {
			e.printStackTrace();
		}
        finally {
            identityService.setAuthenticatedUserId(null);
        }
        return processInstance;
    }
	public BuinessProcessService<BuinessProcessEntity> getBuinessProcessService() {
		return buinessProcessService;
	}
	@Autowired
	public void setBuinessProcessService(
			BuinessProcessService<BuinessProcessEntity> buinessProcessService) {
		this.buinessProcessService = buinessProcessService;
	}
	public Sys_wfparamService<Sys_wfparamEntity> getSys_wfparamService() {
		return sys_wfparamService;
	}
	@Autowired
	public void setSys_wfparamService(
			Sys_wfparamService<Sys_wfparamEntity> sys_wfparamService) {
		this.sys_wfparamService = sys_wfparamService;
	}
	

}
