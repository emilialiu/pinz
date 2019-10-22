package com.dimine.activity.action;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.identity.User;
import org.activiti.engine.identity.UserQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ch.qos.logback.core.subst.Token.Type;

import com.dimine.activity.entity.LeaveEntity;
import com.dimine.activity.service.LeaveWorkflowService;
import com.dimine.base.action.BaseAction;
import com.dimine.base.util.json.JSONUtil;
import com.dimine.sys.entity.RoleEntity;
import com.dimine.sys.entity.UserEntity;
import com.dimine.sys.service.RoleService;
import com.dimine.sys.service.UserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 请假控制器，包含保存、启动流程
 *
 * @author HenryYan
 */
@Namespace("/manager/leave")
@Scope("request")
@Controller("leaveAction")
public class LeaveAction extends BaseAction{

    private Logger logger = LoggerFactory.getLogger(getClass());


    @Resource
    protected LeaveWorkflowService<LeaveEntity> workflowService;

    @Autowired
    protected RuntimeService runtimeService;

    @Autowired
    protected TaskService taskService;
    @Autowired
    protected IdentityService identityService;
    private LeaveEntity bean = new LeaveEntity();
    @Resource
    private UserService<UserEntity> userService;
    @Resource
    private RoleService<RoleEntity> roleService;
    private String taskId;
    private String ispass;
    //部门经理驳回的原因
    private String leaderBackReason;

//    @RequestMapping(value = {"apply", ""})
//    public String createForm(Model model) {
//        model.addAttribute("leave", new Leave());
//        return "/oa/leave/leaveApply";
//    }

    /**
     * 启动请假流程
     *
     * @param leave
     */
    @Action(value = "doAddSave", results = { @Result(name = "success", location = "/pub/result.jsp") })
    public String startWorkflow() {
        try {
            bean.setUserId(this.getLoginUser().getUserid());
            bean.setId(System.currentTimeMillis()+"");
            Map<String, Object> variables = new HashMap<String, Object>();
            ProcessInstance processInstance = workflowService.startWorkflow(bean, variables);
            this.setJsonStr("流程已启动，流程ID：" + processInstance.getId());
        } catch (ActivitiException e) {
            if (e.getMessage().indexOf("no processes deployed with key") != -1) {
                logger.warn("没有部署流程!", e);
                this.setJsonStr("没有部署流程，请在[工作流]->[流程管理]页面点击<重新部署流程>");
            } else {
                logger.error("启动请假流程失败：", e);
                this.setErrorMessage( "系统内部错误！");
            }
        } catch (Exception e) {
            logger.error("借支申请失败：", e);
            this.setErrorMessage("申请失败");
        }
        return SUCCESS;
    }

    /**
     * 任务列表
     *
     * @param leave
     */
    @Action(value = "list", results = { @Result(name = "success", location = "/pub/grid/pager.jsp") })
    public String  taskList() {
       try{
       String userId = getLoginUser().getUserid();
        List<LeaveEntity> results = new ArrayList<LeaveEntity>();
        // 根据当前人的ID查询
        TaskQuery taskQuery = taskService.createTaskQuery().taskCandidateOrAssigned(userId);
        //TaskQuery taskQuery2 = taskService.createTaskQuery().taskCandidateOrAssigned(userId);
        
        List<Task> tasks = taskQuery.listPage((this.getPager().getStartrow()-1),this.getPager().getPageSize());
        // 根据流程的业务ID查询实体并关联
        for (Task task : tasks) {
            String processInstanceId = task.getProcessInstanceId();
            ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).active().singleResult();
            String businessKey = processInstance.getBusinessKey();
            if (businessKey == null) {
                continue;
            }
            LeaveEntity leave = (LeaveEntity) workflowService.selectById(businessKey);
            leave.setTask(task);
            leave.setProcessInstance(processInstance);
            leave.setProcessDefinition(workflowService.getProcessDefinition(processInstance.getProcessDefinitionId()));
            results.add(leave);
        }
//        /**
//		 * 拼接成对应的json字符窜，因为它们实体定义成的是接口
//		 */
		StringBuffer sb = new StringBuffer();
		//查询出所有的记录条数
		long  records = taskQuery.count();
		//分页为所有的记录条数/每个页面的条数
		//long bz = records/this.getPager().getPageSize();
		long total = (records+this.getPager().getPageSize()-1)/this.getPager().getPageSize();
		
		//long total = (((records/this.getPager().getPageSize())<=0)?(records/this.getPager().getPageSize())+1:(records/this.getPager().getPageSize()));
		//将部署的时间格式化
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sb.append("{").append("\"total\":").append(total).append(",");
		sb.append("\"page\":").append(this.getPager().getPageId()).append(",");
		sb.append("\"records\":").append(records).append(",");
		sb.append("\"rows\":[");
		//System.out.println(uq.getLastName());;
		for(LeaveEntity leave:results){
			UserEntity u = userService.selectById(leave.getUserId());
			sb.append("{\"leaveType\":\"").append(leave.getLeaveType()).append("\",");
			sb.append("\"userId\":\"").append(leave.getUserId()).append("\",");
			sb.append("\"applyTime\":\"").append(leave.getApplyTime()).append("\",");
			sb.append("\"startTime\":\"").append(leave.getStartTime()).append("\",");
			sb.append("\"endTime\":\"").append(leave.getEndTime()).append("\",");
			sb.append("\"name\":\"").append(leave.getTask().getName()).append("\",");
			sb.append("\"createTime\":\"").append(sdf.format(leave.getTask().getCreateTime())).append("\",");
			sb.append("\"username\":\"").append(u.getUsername()).append("\",");
			sb.append("\"version\":\"").append(leave.getProcessDefinition().getVersion()).append("\",");
			sb.append("\"taskid\":\"").append(leave.getTask().getId()).append("\",");
			sb.append("\"id\":\"").append(leave.getId()).append("\",");
			// ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(leave.getId()).active().singleResult();
			sb.append("\"suspended\":\"").append(leave.getProcessInstance().isSuspended()).append("\",");
			sb.append("\"assignee\":\"").append(leave.getTask().getAssignee()).append("\"},");
		}
		String str = sb.toString();
		if(results.size()>0){
			str = str.substring(0,str.length()-1);
		}
		String jsonstr = str+"]}";
		this.setJsonStr(jsonstr);
       }catch (Exception e) {
    	   this.setErrorMessage(e);
    	   e.printStackTrace();
	}
        return SUCCESS;
    }

	
//    /**
//     * 读取运行中的流程实例
//     *
//     * @return
//     */
//    @RequestMapping(value = "list/running")
//    public ModelAndView runningList(HttpServletRequest request) {
//        ModelAndView mav = new ModelAndView("/oa/leave/running");
//        Page<Leave> page = new Page<Leave>(PageUtil.PAGE_SIZE);
//        int[] pageParams = PageUtil.init(page, request);
//        workflowService.findRunningProcessInstaces(page, pageParams);
//        mav.addObject("page", page);
//        return mav;
//    }

//    /**
//     * 读取运行中的流程实例
//     *
//     * @return
//     */
//    @RequestMapping(value = "list/finished")
//    public ModelAndView finishedList(HttpServletRequest request) {
//        ModelAndView mav = new ModelAndView("/oa/leave/finished");
//        Page<Leave> page = new Page<Leave>(PageUtil.PAGE_SIZE);
//        int[] pageParams = PageUtil.init(page, request);
//        workflowService.findFinishedProcessInstaces(page, pageParams);
//        mav.addObject("page", page);
//        return mav;
//    }

    /**
     * 签收任务
     */
    @Action(value = "claim", results = { @Result(name = "success", location = "/pub/result.jsp") })
    public String claim() {
    	try{
	        String userId = this.getLoginUser().getUserid();
	        taskService.claim(taskId, userId);
	        //redirectAttributes.addFlashAttribute("message", "任务已签收");
	        this.setJsonStr("任务已签收");
    	}catch (Exception e) {
			e.printStackTrace();
			this.setErrorMessage(e);
		}
        return SUCCESS;
    }

    /**
     * 读取请假的信息
     *
     * @param id
     * @return
     */
    @Action(value = "view", results = { @Result(name = "success", location = "/pub/jsondata.jsp") })
	 public String getLeave() {
    	try {
		 LeaveEntity leave = workflowService.findById(bean.getId());
		 this.setJsonStr("["+JSONUtil.toJSONString(leave)+"]");
		 System.out.println(JSONUtil.toJSONString(leave));
		 this.setActiontype(ACTIONTYPE_MODIFYSAVE);
    	}catch (Exception e) {
    		this.setErrorMessage(this.getText("modifyfailure"));
		}
		 return SUCCESS;
	 }

//    /**
//     * 读取详细数据
//     *
//     * @param id
//     * @return
//     */
//    @RequestMapping(value = "detail-with-vars/{id}/{taskId}")
//    @ResponseBody
//    public Leave getLeaveWithVars(@PathVariable("id") Long id, @PathVariable("taskId") String taskId) {
//        Leave leave = leaveManager.getLeave(id);
//        Map<String, Object> variables = taskService.getVariables(taskId);
//        leave.setVariables(variables);
//        return leave;
//    }

    /**
     * 完成任务
     *
     * @param id
     * @return
     */
    @Action(value = "complete", results = { @Result(name = "success", location = "/pub/jsondata.jsp") })
    public String complete() {
        try {
        	
            Map<String, Object> variables = new HashMap<String, Object>();
            List<RoleEntity> rolelist =  roleService.findRoleByname(this.getLoginUser().getUserid());
            for(RoleEntity role :rolelist){
            	//如果角色是部门经理
            	if(role.getRoleid().equals("20000") && ispass.equals("pass")){
            		variables.put("deptLeaderPass",true);
            		break;
            	}
            	//如果角色是部门经理
            	else  if(role.getRoleid().equals("20000") && ispass.equals("nopass")){
            		variables.put("deptLeaderPass",false);
            		variables.put("leaderBackReason",leaderBackReason);
            		break;
            	}
            	//如果角色是人事经理
            	else if(role.getRoleid().equals("30000") && ispass.equals("pass")){
            		variables.put("hrPass",true);
            		break;
            	}
            	//如果角色是人事经理
            	else if(role.getRoleid().equals("30000") && ispass.equals("nopass")){
            		variables.put("hrPass",false);
            		variables.put("hrBackReason","hrBackReason");
            		break;
            	}
            	else{
            		variables.put("realityStartTime",bean.getRealityStartTime());
            		variables.put("realityendTime",bean.getRealityEndTime());
            	}
            }
            taskService.complete(taskId,variables);
        } catch (Exception e) {
           e.printStackTrace();
           this.setErrorMessage(e);
        }
        return SUCCESS;
    }
    
    public LeaveWorkflowService<LeaveEntity> getWorkflowService() {
		return workflowService;
	}

	public void setWorkflowService(LeaveWorkflowService<LeaveEntity> workflowService) {
		this.workflowService = workflowService;
	}

	public LeaveEntity getBean() {
		return bean;
	}

	public void setBean(LeaveEntity bean) {
		this.bean = bean;
	}

	public void setUserService(UserService<UserEntity> userService) {
		this.userService = userService;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public void setRoleService(RoleService<RoleEntity> roleService) {
		this.roleService = roleService;
	}

	public String getIspass() {
		return ispass;
	}

	public void setIspass(String ispass) {
		this.ispass = ispass;
	}

	public String getLeaderBackReason() {
		return leaderBackReason;
	}

	public void setLeaderBackReason(String leaderBackReason) {
		this.leaderBackReason = leaderBackReason;
	}
	
	
}
