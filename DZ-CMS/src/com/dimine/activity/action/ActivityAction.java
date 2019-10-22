package com.dimine.activity.action;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.zip.ZipInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.form.FormProperty;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricDetail;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricTaskInstanceQuery;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.history.HistoricVariableUpdate;
import org.activiti.engine.identity.User;
import org.activiti.engine.impl.UserQueryProperty;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.form.StartFormDataImpl;
import org.activiti.engine.impl.form.TaskFormDataImpl;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.persistence.entity.DeploymentEntity;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.activiti.image.ProcessDiagramGenerator;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dimine.activity.entity.BuinessProcessEntity;
import com.dimine.activity.entity.WorkflowEntity;
import com.dimine.activity.service.BuinessProcessService;
import com.dimine.activity.service.WorkflowTraceService;
import com.dimine.activity.util.Variable;
import com.dimine.activity.util.WorkflowUtils;
import com.dimine.base.action.BaseAction;
import com.dimine.base.util.SQLCallback;
import com.dimine.base.util.SQLTool;
import com.dimine.base.util.json.JSONUtil;
import com.dimine.sm.entity.StaffEntity;
import com.dimine.sm.service.StaffService;
import com.dimine.sys.entity.Sys_wfparamEntity;
import com.dimine.sys.entity.UserEntity;
import com.dimine.sys.service.Sys_wfparamService;
import com.dimine.sys.service.UserService;
import com.google.common.hash.HashingInputStream;
import com.sun.org.apache.bcel.internal.generic.DMUL;

/**
 * 流程管理控制器
 * 
 * @author SSM
 */
@Namespace("/manager/activity")
@Scope("request")
@Controller("activityAction")
public class ActivityAction extends BaseAction {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	// protected WorkflowProcessDefinitionService
	// workflowProcessDefinitionService;
	protected RepositoryService repositoryService;
	
	@Autowired
	protected WorkflowTraceService traceService;

	@Autowired
	ProcessEngineFactoryBean processEngine;

	@Autowired
	ProcessEngineConfiguration processEngineConfiguration;
	@Autowired
	HistoryService historyService;
	@Autowired
    private FormService formService;
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private ManagementService  managementService;
	//上传的部署文件
	private File file;
	//上传的部署文件的名字
	private String fileFileName;
	//部署的文件id
	private String deploymentId;
	
	private WorkflowEntity bean = new WorkflowEntity();
	//读取的文件资源类型
	private String resourceType;
	/**
	 * 流程状态
	 */
	private String suspended;
	/**
	 * 流程id
	 */
	private String processDefinitionId;
	@Autowired
    private IdentityService identityService;
	@Autowired
    private TaskService taskService;
	@Autowired
	private StaffService<StaffEntity> staffService;
	/**
	 * 流程实例和业务关联id
	 */
	@Autowired
	private BuinessProcessService<BuinessProcessEntity> buinessProcessService;
	@Autowired
	private Sys_wfparamService<Sys_wfparamEntity> sys_wfparamService;
	private UserService<UserEntity> userService;
	private String key;
	/**
	 * 表单数据
	 */
	private String formdata;
	/**
	 * 任务id
	 */
	private String taskId;
	/**
	 * 参数属性
	 */
	private Variable va;
	/**
	 * 流程实例id
	 */
	private String processInstanceId;
	private String bussinesskey;
	//查询条件
	private String filters;
	private String param;
	
	private String prokey;
	/**
	 * 流程定义列表
	 * 
	 * @return
	 */
	@Action(value = "list", results = { @Result(name = "success", location = "/webpage/pub/grid/pager.jsp") })
	public String processList() {
		/*
		 * 保存两个对象，一个是ProcessDefinition（流程定义），一个是Deployment（流程部署）
		 */
		try{
			String query = "";
			String paramsql = "";
			if(bean.getParam()!=null && !bean.getParam().equals("")){
				paramsql = " and (RES.NAME_ like '%"+bean.getParam()+"%' or RES.KEY_ LIKE '%"+bean.getParam()+"%')" ;
			}
			if(filters!=null){
				 query = new SQLTool().constructWhere(filters, new SQLCallback(){

					@Override
					public String executeData(String f, String o, String d) {
						if(o.equals("bw")||o.equals("en"))
							return(new StringBuilder("'")).append(d).append("%'").toString();
						if(o.equals("ew")||o.equals("en"))
							return(new StringBuilder("'%")).append(d).append("'").toString();
						if(o.equals("cn")||o.equals("nc"))
							return(new StringBuilder("'%")).append(d).append("%'").toString();
						else
							return(new StringBuilder("'")).append(d).append("'").toString();
					}

					@Override
					public String executeField(String f) {
						if(f.equals("id")) return "RES.ID_";
						if(f.equals("deploymentId")) return "RES.DEPLOYMENT_ID_";
						if(f.equals("name")) return "RES.NAME_";
						if(f.equals("key")) return "RES.KEY_";
						if(f.equals("version")) return "RES.VERSION_";
						if(f.equals("resourceName")) return "RES.RESOURCE_NAME_";
						if(f.equals("diagramResourceName")) return "RES.DGRM_RESOURCE_NAME_";
						return f;
					}
					
				});
			}
			
			List<ProcessDefinition> processDefinitionList = repositoryService.createNativeProcessDefinitionQuery().sql("select distinct RES.* from ACT_RE_PROCDEF RES WHERE RES.VERSION_ = (select max(VERSION_) from ACT_RE_PROCDEF where KEY_ = RES.KEY_ ) "+query+" "+paramsql+"order by RES.ID_ desc ")
					//sql语句是limit查询几行数据  offset从哪行开始
					.listPage((this.getPager().getStartrow()-1),this.getPager().getPageSize());
			/**
			 * 拼接成对应的json字符窜，因为它们实体定义成的是接口
			 */
			StringBuffer sb = new StringBuffer();
			//查询出所有的记录条数
			//long  records = processDefinitionQuery.count();
			//分页为所有的记录条数/每个页面的条数
			//long bz = records/this.getPager().getPageSize();
			long records = repositoryService.createNativeProcessDefinitionQuery().sql("select count(1) from ACT_RE_PROCDEF RES WHERE RES.VERSION_ = (select max(VERSION_) from ACT_RE_PROCDEF where KEY_ = RES.KEY_ )"+query+" "+paramsql).count();
			long total = (records+this.getPager().getPageSize()-1)/this.getPager().getPageSize();
			//long total = (((records/this.getPager().getPageSize())<=0)?(records/this.getPager().getPageSize())+1:(records/this.getPager().getPageSize()));
			//将部署的时间格式化  
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			sb.append("{").append("\"total\":").append(total).append(",");
			sb.append("\"page\":").append(this.getPager().getPageId()).append(",");
			sb.append("\"records\":").append(records).append(",");
			sb.append("\"rows\":[");
			for (ProcessDefinition processDefinition : processDefinitionList) {
				String deploymentId = processDefinition.getDeploymentId();
				Deployment deployment = repositoryService.createDeploymentQuery()
						.deploymentId(deploymentId).singleResult();
				sb.append("{");
				sb.append("\"id\":").append("\"").append(processDefinition.getId()).append("\",");
				sb.append("\"deploymentId\":").append("\"").append(processDefinition.getDeploymentId()).append("\",");
				sb.append("\"name\":").append("\"").append(processDefinition.getName()).append("\",");
				sb.append("\"key\":").append("\"").append(processDefinition.getKey()).append("\",");
				sb.append("\"version\":").append("\"").append(processDefinition.getVersion()).append("\",");
				sb.append("\"resourceName\":").append("\"").append(processDefinition.getResourceName()).append("\",");
				sb.append("\"diagramResourceName\":").append("\"").append(processDefinition.getDiagramResourceName()).append("\",");
				sb.append("\"deploymentTime\":").append("\"").append(deployment.getDeploymentTime()==null?"":sdf.format(deployment.getDeploymentTime())).append("\",");
				sb.append("\"suspended\":").append("\"").append(processDefinition.isSuspended()).append("\"},");	
			}
			String str = sb.toString();
			if(processDefinitionList.size()>0){
				str = str.substring(0,str.length()-1);
			}
			String jsonstr = str+"]}";
			this.setJsonStr(jsonstr);	
			
		}catch (Exception e) {
			e.printStackTrace();
			this.setErrorMessage(e);
		}
		return SUCCESS;
	}
	/**
	 * 查看历史流程信息
	 * @return
	 */
	@Action(value = "hislist", results = { @Result(name = "success", location = "/webpage/pub/grid/pager.jsp") })
	public String dohislist(){
		/*
		 * 保存两个对象，一个是ProcessDefinition（流程定义），一个是Deployment（流程部署）
		 */
		try{
			String query = "";
			String paramsql = "";
			if(key!=null && !key.equals("")){
				paramsql = " and ( RES.KEY_  ='"+key+"')" ;
			}
			if(filters!=null){
				 query = new SQLTool().constructWhere(filters, new SQLCallback(){

					@Override
					public String executeData(String f, String o, String d) {
						if(o.equals("bw")||o.equals("en"))
							return(new StringBuilder("'")).append(d).append("%'").toString();
						if(o.equals("ew")||o.equals("en"))
							return(new StringBuilder("'%")).append(d).append("'").toString();
						if(o.equals("cn")||o.equals("nc"))
							return(new StringBuilder("'%")).append(d).append("%'").toString();
						else
							return(new StringBuilder("'")).append(d).append("'").toString();
					}

					@Override
					public String executeField(String f) {
						if(f.equals("id")) return "RES.ID_";
						if(f.equals("deploymentId")) return "RES.DEPLOYMENT_ID_";
						if(f.equals("name")) return "RES.NAME_";
						if(f.equals("key")) return "RES.KEY_";
						if(f.equals("version")) return "RES.VERSION_";
						if(f.equals("resourceName")) return "RES.RESOURCE_NAME_";
						if(f.equals("diagramResourceName")) return "RES.DGRM_RESOURCE_NAME_";
						return f;
					}
					
				});
			}
			ProcessDefinitionQuery processDefinitionQuery = repositoryService
					.createProcessDefinitionQuery().latestVersion().orderByProcessDefinitionId().desc();
			List<ProcessDefinition> processDefinitionList = repositoryService.createNativeProcessDefinitionQuery().sql("select distinct RES.* from ACT_RE_PROCDEF RES WHERE 1=1 "+query+" "+paramsql+"order by RES.ID_ desc ")
					//sql语句是limit查询几行数据  offset从哪行开始
					.listPage((this.getPager().getStartrow()-1),this.getPager().getPageSize());
			/**
			 * 拼接成对应的json字符窜，因为它们实体定义成的是接口
			 */
			StringBuffer sb = new StringBuffer();
			//查询出所有的记录条数
			//long  records = processDefinitionQuery.count();
			//分页为所有的记录条数/每个页面的条数
			//long bz = records/this.getPager().getPageSize();
			long records = repositoryService.createNativeProcessDefinitionQuery().sql("select count(1) from ACT_RE_PROCDEF RES WHERE RES.VERSION_ = (select max(VERSION_) from ACT_RE_PROCDEF where KEY_ = RES.KEY_ )"+query+" "+paramsql).count();
			long total = (records+this.getPager().getPageSize()-1)/this.getPager().getPageSize();
			//long total = (((records/this.getPager().getPageSize())<=0)?(records/this.getPager().getPageSize())+1:(records/this.getPager().getPageSize()));
			//将部署的时间格式化  
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			sb.append("{").append("\"total\":").append(total).append(",");
			sb.append("\"page\":").append(this.getPager().getPageId()).append(",");
			sb.append("\"records\":").append(records).append(",");
			sb.append("\"rows\":[");
			for (ProcessDefinition processDefinition : processDefinitionList) {
				String deploymentId = processDefinition.getDeploymentId();
				Deployment deployment = repositoryService.createDeploymentQuery()
						.deploymentId(deploymentId).singleResult();
				sb.append("{");
				sb.append("\"id\":").append("\"").append(processDefinition.getId()).append("\",");
				sb.append("\"deploymentId\":").append("\"").append(processDefinition.getDeploymentId()).append("\",");
				sb.append("\"name\":").append("\"").append(processDefinition.getName()).append("\",");
				sb.append("\"key\":").append("\"").append(processDefinition.getKey()).append("\",");
				sb.append("\"version\":").append("\"").append(processDefinition.getVersion()).append("\",");
				sb.append("\"resourceName\":").append("\"").append(processDefinition.getResourceName()).append("\",");
				sb.append("\"diagramResourceName\":").append("\"").append(processDefinition.getDiagramResourceName()).append("\",");
				sb.append("\"deploymentTime\":").append("\"").append(deployment.getDeploymentTime()==null?"":sdf.format(deployment.getDeploymentTime())).append("\",");
				sb.append("\"suspended\":").append("\"").append(processDefinition.isSuspended()).append("\"},");	
			}
			String str = sb.toString();
			if(processDefinitionList.size()>0){
				str = str.substring(0,str.length()-1);
			}
			String jsonstr = str+"]}";
			this.setJsonStr(jsonstr);	
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	  return SUCCESS;
	}
	/**
	 * 文件部署
	 * @return
	 */
	@Action(value = "deploy", results = { @Result(name = "success",  location = "/webpage/pub/result.jsp") })
	public String deploy(){

        try {
            InputStream fileInputStream = new FileInputStream(file);
            Deployment deployment = null;
            //获取目标文件夹
            String targetFolder = getRequest().getRealPath("/tmp/activity");
            String extension = FilenameUtils.getExtension(fileFileName);
            if (extension.equals("zip") || extension.equals("bar")) {
                ZipInputStream zip = new ZipInputStream(fileInputStream);
                deployment = repositoryService.createDeployment().addZipInputStream(zip).deploy();
            } else {
                deployment = repositoryService.createDeployment().addInputStream(fileFileName, fileInputStream).deploy();
            }

            List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().deploymentId(deployment.getId()).list();
            
            for (ProcessDefinition processDefinition : list) {
                WorkflowUtils.exportDiagramToFile(repositoryService, processDefinition,targetFolder);
              //转换为model
               convermodel(processDefinition.getId());
            }
            
        	this.setJsonStr(deployment.getId());
        } catch (Exception e) {
        	this.setErrorMessage("failed");
            logger.error("error on deploy process, because of file input stream", e);
        }

        return SUCCESS;
	}
	/**
     * 删除部署的流程，级联删除流程实例
     *
     * @param deploymentId 流程部署ID
     */
	@Action(value = "delete", results = { @Result(name = "success", location = "/webpage/pub/result.jsp") })
    public String delete() {
        try {
        	  repositoryService.deleteDeployment(bean.getDeploymentId(), true);
			this.setJsonStr(bean.getId());
		} catch (Exception e) {
			this.setErrorMessage("failed");
			e.printStackTrace();
		}
        return SUCCESS;
    }
	/**
	 * 信息查询
	 * @return
	 */
	@Action(value = "doModify", results = { @Result(name = "success", location = "/webpage/pub/jsondata.jsp") })
	public String doModify(){
		try{
			ProcessDefinition   processDefinition=  repositoryService.getProcessDefinition(bean.getId());
			String deploymentId = processDefinition.getDeploymentId();
			Deployment deployment = repositoryService.createDeploymentQuery()
					.deploymentId(deploymentId).singleResult();
			StringBuffer sb = new StringBuffer();
			sb.append("{");
			sb.append("\"id\":").append("\"").append(processDefinition.getId()).append("\",");
			sb.append("\"deploymentId\":").append("\"").append(processDefinition.getDeploymentId()).append("\",");
			sb.append("\"name\":").append("\"").append(processDefinition.getName()).append("\",");
			sb.append("\"key\":").append("\"").append(processDefinition.getKey()).append("\",");
			sb.append("\"version\":").append("\"").append(processDefinition.getVersion()).append("\",");
			sb.append("\"resourceName\":").append("\"").append(processDefinition.getResourceName()).append("\",");
			sb.append("\"diagramResourceName\":").append("\"").append(processDefinition.getDiagramResourceName()).append("\",");
			//将部署的时间格式化
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			sb.append("\"deploymentTime\":").append("\"").append(deployment.getDeploymentTime()==null?"":sdf.format(deployment.getDeploymentTime())).append("\",");
			sb.append("\"suspended\":").append("\"").append(processDefinition.isSuspended()).append("\"}");	
			this.setJsonStr("["+sb.toString()+"]");
			this.setActiontype(ACTIONTYPE_MODIFYSAVE);
		}catch (Exception e) {
			this.setErrorMessage(this.getText("modifyfailure"));
			e.printStackTrace();
		}
		return SUCCESS;
	}
	 /**
     * 读取资源，通过部署ID
     *
     * @param processDefinitionId 流程定义
     * @param resourceType        资源类型(xml|image)
     * @throws Exception
     */
	@Action(value = "view")
    public void  loadByDeployment() throws Exception {
		HttpServletResponse response = getResponse();
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(bean.getId()).singleResult();
        String resourceName = "";
        if (resourceType.equals("image")) {
            resourceName = processDefinition.getDiagramResourceName();
        } else if (resourceType.equals("xml")) {
            resourceName = processDefinition.getResourceName();
        }
        InputStream resourceAsStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(), resourceName);
        byte[] b = new byte[1024];
        int len = -1;
        while ((len = resourceAsStream.read(b, 0, 1024)) != -1) {
            response.getOutputStream().write(b, 0, len);
        }
    }
	/**
     * 挂起、激活流程实例
     */
	@Action(value = "doActity", results = { @Result(name = "success", location = "/webpage/pub/result.jsp") })
    public String doActity() {
		try{
	        if (suspended.equals("true")) {
	            repositoryService.activateProcessDefinitionById(bean.getId(), true, null);
	            this.setJsonStr(getText("dmmes.sys.processinstance.messageactivated")+"[" + bean.getId() + "]"+getText("dmmes.sys.processinstance.processdefinition"));
	        } else if (suspended.equals("false")) {
	            repositoryService.suspendProcessDefinitionById(bean.getId(), true, null);
	            this.setJsonStr(getText("dmmes.sys.processinstance.messagesuspended")+"[" + bean.getId() + "]"+getText("dmmes.sys.processinstance.processdefinition"));
	        }
		}catch (Exception e) {
			this.setErrorMessage("failed");
			e.printStackTrace();
		}
        return SUCCESS;
    }
	/**
	 * 将流程转换为model
	 * @return
	 */
	@Action(value = "convertToModel", results = { @Result(name = "success", location = "/webpage/pub/result.jsp") })
    public String convertToModel(){
		try{
			convermodel(bean.getId());
	        this.setJsonStr("流程ID为[" + bean.getId() + "]的流程转化为model成功。");
		}catch (Exception e) {
			this.setErrorMessage(e);
		}
        return SUCCESS;
    }
	/**
     * 输出跟踪流程信息
     *
     * @param processInstanceId
     * @return
     * @throws Exception
     */
	@Action(value = "trace", results = { @Result(name = "success", location = "/webpage/pub/beanresult.jsp") })
    public String traceProcess()  {
		try{
        List<Map<String, Object>> activityInfos = traceService.traceProcess(bean.getId());
        String str = JSONUtil.toJSONString(activityInfos);
        //JSONObject jsonObjectFromMap = JSONObject.fromObject(); 

        this.setJsonStr(str);
        return SUCCESS;
	    
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 已经结束的流程实例
	 * @return
	 */
	@Action(value = "finished", results = { @Result(name = "success", location = "/webpage/pub/grid/pager.jsp") })
	public String finished() {
		try{
			String query = "";
			String paramsql = "";
			if(bean.getParam()!=null && !bean.getParam().equals("")){
				paramsql = " and (RES.PROC_INST_ID_ like '%"+bean.getParam()+"%' or RES.PROC_DEF_ID_ LIKE '%"+bean.getParam()+"%')" ;
			}
			if(filters!=null){
				 query = new SQLTool().constructWhere(filters, new SQLCallback(){

					@Override
					public String executeData(String f, String o, String d) {
						if(o.equals("bw")||o.equals("en"))
							return(new StringBuilder("'")).append(d).append("%'").toString();
						if(o.equals("ew")||o.equals("en"))
							return(new StringBuilder("'%")).append(d).append("'").toString();
						if(o.equals("cn")||o.equals("nc"))
							return(new StringBuilder("'%")).append(d).append("%'").toString();
						else
							return(new StringBuilder("'")).append(d).append("'").toString();
					}

					@Override
					public String executeField(String f) {
						if(f.equals("id")) return "RES.ID_";
						if(f.equals("processDefinitionId")) return "RES.PROC_DEF_ID_";
						if(f.equals("name")) return "RES.NAME_";
						if(f.equals("startTime")) return "RES.START_TIME_";
						if(f.equals("endTime")) return "RES.END_TIME_";
						if(f.equals("deleteReason")) return "RES.DELETE_REASON_";
						return f;
					}
					
				});
			}
			List<HistoricProcessInstance> list = historyService.createNativeHistoricProcessInstanceQuery().sql("select RES.* from ACT_HI_PROCINST RES  WHERE RES.END_TIME_ is not NULL "+query+" "+paramsql+"order by RES.END_TIME_ desc")
			.listPage((this.getPager().getStartrow()-1),this.getPager().getPageSize());
			
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	      
	        StringBuffer sb = new StringBuffer();
			//查询出所有的记录条数
			long  records = historyService.createNativeHistoricProcessInstanceQuery().sql("select count(1) from ACT_HI_PROCINST RES WHERE RES.END_TIME_ is not NULL "+query+" "+paramsql).count();
			long total = (records+this.getPager().getPageSize()-1)/this.getPager().getPageSize();
			sb.append("{").append("\"total\":").append(total).append(",");
			sb.append("\"page\":").append(this.getPager().getPageId()).append(",");
			sb.append("\"records\":").append(records).append(",");
			sb.append("\"rows\":[");
			for (HistoricProcessInstance hi :list) {
				sb.append("{");
				sb.append("\"id\":").append("\"").append(hi.getId()).append("\",");
				sb.append("\"processDefinitionId\":").append("\"").append(hi.getProcessDefinitionId()).append("\",");
				sb.append("\"startTime\":").append("\"").append(sdf.format(hi.getStartTime())).append("\",");
				sb.append("\"endTime\":").append("\"").append(sdf.format(hi.getEndTime())).append("\",");
				sb.append("\"deleteReason\":").append("\"").append(hi.getDeleteReason()==null?"正常结束":hi.getDeleteReason()).append("\"},");
			}
			String str = sb.toString();
			if(list.size()>0){
				str = str.substring(0,str.length()-1);
			}
			String jsonstr = str+"]}";
			this.setJsonStr(jsonstr);	
		}catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 已经结束的流程实例
	 * @return
	 */
	@Action(value = "histask", results = { @Result(name = "success", location = "/webpage/pub/grid/pager.jsp") })
	public String histask() {
		try{
			String query = "";
			String paramsql = "";
			if(bean.getParam()!=null && !bean.getParam().equals("")){
				paramsql = " and (RES.DELETE_REASON_ like '%"+bean.getParam()+"%' or RES.NAME_ LIKE '%"+bean.getParam()+"%')" ;
			}
			if(filters!=null){
				 query = new SQLTool().constructWhere(filters, new SQLCallback(){

					@Override
					public String executeData(String f, String o, String d) {
						if(o.equals("bw")||o.equals("en"))
							return(new StringBuilder("'")).append(d).append("%'").toString();
						if(o.equals("ew")||o.equals("en"))
							return(new StringBuilder("'%")).append(d).append("'").toString();
						if(o.equals("cn")||o.equals("nc"))
							return(new StringBuilder("'%")).append(d).append("%'").toString();
						else
							return(new StringBuilder("'")).append(d).append("'").toString();
					}

					@Override
					public String executeField(String f) {
						if(f.equals("id")) return "RES.ID_";
						if(f.equals("processDefinitionId")) return "RES.PROC_DEF_ID_";
						if(f.equals("name")) return "RES.NAME_";
						if(f.equals("startTime")) return "RES.START_TIME_";
						if(f.equals("endTime")) return "RES.END_TIME_";
						if(f.equals("deleteReason")) return "RES.DELETE_REASON_";
						return f;
					}
					
				});
			}
			List<HistoricProcessInstance> list = historyService.createNativeHistoricProcessInstanceQuery().sql("select RES.* from ACT_HI_PROCINST RES WHERE RES.END_TIME_ is not NULL "+query+" "+paramsql+"order by RES.END_TIME_ desc")
			.listPage((this.getPager().getStartrow()-1),this.getPager().getPageSize());
			
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	      
	        StringBuffer sb = new StringBuffer();
			//查询出所有的记录条数
			long  records = historyService.createNativeHistoricProcessInstanceQuery().sql("select count(1) from ACT_HI_PROCINST RES WHERE RES.END_TIME_ is not NULL "+query+" "+paramsql).count();
			long total = (records+this.getPager().getPageSize()-1)/this.getPager().getPageSize();
			sb.append("{").append("\"total\":").append(total).append(",");
			sb.append("\"page\":").append(this.getPager().getPageId()).append(",");
			sb.append("\"records\":").append(records).append(",");
			sb.append("\"rows\":[");
			for (HistoricProcessInstance hi :list) {
				sb.append("{");
				sb.append("\"id\":").append("\"").append(hi.getId()).append("\",");
				sb.append("\"processDefinitionId\":").append("\"").append(hi.getProcessDefinitionId()).append("\",");
				sb.append("\"startTime\":").append("\"").append(sdf.format(hi.getStartTime())).append("\",");
				sb.append("\"endTime\":").append("\"").append(sdf.format(hi.getEndTime())).append("\",");
				sb.append("\"deleteReason\":").append("\"").append(hi.getDeleteReason()==null?"正常结束":hi.getDeleteReason()).append("\"},");
			}
			String str = sb.toString();
			if(list.size()>0){
				str = str.substring(0,str.length()-1);
			}
			String jsonstr = str+"]}";
			this.setJsonStr(jsonstr);	
		}catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	/**
	 * 转换为model
	 * @param id
	 */
	private void convermodel(String id) throws Exception{
		 ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
	                .processDefinitionId(id).singleResult();
	        InputStream bpmnStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(),
	                processDefinition.getResourceName());
	        XMLInputFactory xif = XMLInputFactory.newInstance();
	        InputStreamReader in = new InputStreamReader(bpmnStream, "UTF-8");
	        XMLStreamReader xtr = xif.createXMLStreamReader(in);
	        BpmnModel bpmnModel = new BpmnXMLConverter().convertToBpmnModel(xtr);
	
	        BpmnJsonConverter converter = new BpmnJsonConverter();
	        com.fasterxml.jackson.databind.node.ObjectNode modelNode = converter.convertToJson(bpmnModel);
	        Model modelData = repositoryService.newModel();
	        //根据key查询出是否有相同的model在数据库里面
            Model modelold = repositoryService.createModelQuery().modelKey(processDefinition.getKey()).singleResult();
            if(modelold!=null){
            	//如果有老版本的model就应该将其删除
            	repositoryService.deleteModel(modelold.getId());
            	modelData.setVersion((modelold.getVersion()+1));
            	//repositoryService.createNativeModelQuery().sql(" delete from ACT_RE_MODEL RES where RES.KEY='"+modelold.getKey()+"'").notify();
            }
	        modelData.setKey(processDefinition.getKey());
	        modelData.setName(processDefinition.getResourceName());
	        modelData.setCategory(processDefinition.getDeploymentId());
	       
	
	        ObjectNode modelObjectNode = new ObjectMapper().createObjectNode();
	        modelObjectNode.put(ModelDataJsonConstants.MODEL_NAME, processDefinition.getName());
	        //版本为老版本+1
	       // System.out.println(modelold.getVersion());
	        modelObjectNode.put(ModelDataJsonConstants.MODEL_REVISION,1);
	        modelObjectNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, processDefinition.getDescription());
	        
	        modelData.setMetaInfo(modelObjectNode.toString());
	
	        repositoryService.saveModel(modelData);
	        
	        repositoryService.addModelEditorSource(modelData.getId(), modelNode.toString().getBytes("utf-8"));
	}
	
	 /**
     * 初始化启动流程，读取启动流程的表单字段来渲染start form
     */
	@Action(value = "startform", results = { @Result(name = "success", location = "/webpage/pub/jsondata.jsp") })
    public String findStartForm() throws Exception {
        Map<String, Object> result = new HashMap<String, Object>();
        StartFormDataImpl startFormData = (StartFormDataImpl) formService.getStartFormData(processDefinitionId);
        startFormData.setProcessDefinition(null);
	    /*
	     * 读取enum类型数据，用于下拉框
	     */
        List<FormProperty> formProperties = startFormData.getFormProperties();
        for (FormProperty formProperty : formProperties) {
            Map<String, String> values = (Map<String, String>) formProperty.getType().getInformation("values");
            if (values != null) {
                for (Entry<String, String> enumEntry : values.entrySet()) {
                    logger.debug("enum, key: {}, value: {}", enumEntry.getKey(), enumEntry.getValue());
                }
                result.put("enum_" + formProperty.getId(), values);
            }
        }

        result.put("form", startFormData);
        this.setJsonStr("["+JSONUtil.toJSONString(result)+"]");
        return SUCCESS;
    }
	/**
     * 提交启动流程
     */
	@Action(value = "submitStartForm", results = { @Result(name = "success", location = "/webpage/pub/result.jsp") })
    public String submitStartFormAndStartProcessInstance() {
        Map<String, String> formProperties = new HashMap<String, String>();
        // 从request中读取参数然后转换
        String []str = formdata.split("\\^\\%\\$\\#\\^");
        for (int i=0;i<str.length;i++) {
            String []pstr = str[i].split("\\@\\#\\$\\%\\^");
            // fp_的意思是form paremeter
            formProperties.put(pstr[0], pstr[1]);
           
        }

        logger.debug("start form parameters: {}", formProperties);

      
        ProcessInstance processInstance = null;
        try {
            identityService.setAuthenticatedUserId(this.getLoginUser().getUserid());
            processInstance = formService.submitStartFormData(processDefinitionId, formProperties);
            logger.debug("start a processinstance: {}", processInstance);
        } finally {
            identityService.setAuthenticatedUserId(null);
        }
        this.setJsonStr("启动成功，流程ID：" + processInstance.getId());

        return SUCCESS;
    }
	/**
     * task列表
     *
     * @param model
     * @return
     */
	@Action(value = "taskList", results = { @Result(name = "success", location = "/webpage/pub/grid/pager.jsp") })
    public String taskList() {
		try{
			String query = "";
			if(filters!=null){
				 query = new SQLTool().constructWhere(filters, new SQLCallback(){

					@Override
					public String executeData(String f, String o, String d) {
						if(o.equals("bw")||o.equals("en"))
							return(new StringBuilder("'")).append(d).append("%'").toString();
						if(o.equals("ew")||o.equals("en"))
							return(new StringBuilder("'%")).append(d).append("'").toString();
						if(o.equals("cn")||o.equals("nc"))
							return(new StringBuilder("'%")).append(d).append("%'").toString();
						else
							return(new StringBuilder("'")).append(d).append("'").toString();
					}

					@Override
					public String executeField(String f) {
						if(f.equals("id")) return "RES.ID_";
						if(f.equals("taskDefinitionKey")) return "RES.TASK_DEF_KEY_";
						if(f.equals("name")) return "RES.NAME_";
						if(f.equals("processDefinitionId")) return "RES.PROC_DEF_ID_";
						if(f.equals("priority")) return "RES.PRIORITY_";
						if(f.equals("createTime")) return "RES.CREATE_TIME_";
						if(f.equals("dueDate")) return "RES.DUE_DATE_";
						if(f.equals("description")) return "u.text_";
						if(f.equals("owner")) return "RES.OWNER_";
						return f;
					}
					
				});
				
			}
			List<Task> tasks = new ArrayList<Task>();
			String paramsql = "";
			if(bean.getParam()!=null && !bean.getParam().equals("")){
				paramsql = " and RES.NAME_ like '%"+bean.getParam()+"%'";
			}
			tasks = taskService.createNativeTaskQuery()
			        .sql("select distinct RES.ID_,RES.TASK_DEF_KEY_,RES.ASSIGNEE_,RES.NAME_,RES.PROC_DEF_ID_,RES.PRIORITY_,RES.CREATE_TIME_,RES.DUE_DATE_,u.text_ DESCRIPTION_,RES.OWNER_,RES.PROC_DEF_ID_ ,RES.PROC_INST_ID_ from ACT_RU_TASK RES left join ACT_RU_IDENTITYLINK I on I.TASK_ID_ = RES.ID_  " +
			        		"LEFT JOIN act_ru_variable v on RES.PROC_INST_ID_=v.PROC_INST_ID_ " +
			        		"AND v.NAME_='deptid' " +
			        		"LEFT JOIN act_ru_variable u on RES.PROC_INST_ID_=u.PROC_INST_ID_  and u.NAME_='decription'" +
			        		"left join sys_dept sd on sd.deptid=v.TEXT_ WHERE RES.SUSPENSION_STATE_ = 1" +
			        		" and (RES.ASSIGNEE_ = #{userid} or (RES.ASSIGNEE_ is null and (I.USER_ID_ = #{userid} or I.GROUP_ID_ IN  " +
			        		"(select r.roleid  from sys_user_role s left join sys_role r on s.ROLEID=r.ROLEID left join sys_user  u on s.USERID=u.USERID  LEFT JOIN sys_dept_employee emp on u.USERID=emp.USERID  LEFT JOIN sys_dept dept on dept.DEPTID=emp.DEPTID   where s.USERID=#{userid} AND (dept.orgid=v.TEXT_ or sd.parentdeptid=dept.orgid)) " 
			        		//" or  I.GROUP_ID_ IN  "+
			        		//"(select r.roleid  from sys_user_role s left join sys_role r on s.ROLEID=r.ROLEID left join sys_user  u on s.USERID=u.USERID  left join sys_dept_employee e on e.USERID=u.USERID  left join sys_dept de on de.PARENTDEPTID=e.DEPTID  where s.USERID=#{userid} ) 
			        		+") ) ) " 
			        		+query+" "+paramsql+" order by RES.ID_ desc")
			        .parameter("assignee", this.getLoginUser().getUserid()).parameter("userid",this.getLoginUser().getUserid())
			        .listPage((this.getPager().getStartrow()-1),this.getPager().getPageSize());
	    	/**
			 * 拼接成对应的json字符窜，因为它们实体定义成的是接口
			 */
			StringBuffer sb = new StringBuffer();
			//查询出所有的记录条数
			long  records = taskService.createNativeTaskQuery()
					.sql("select count(1) from ACT_RU_TASK RES left join ACT_RU_IDENTITYLINK I on I.TASK_ID_ = RES.ID_  " +
			        		"LEFT JOIN act_ru_variable v on RES.PROC_INST_ID_=v.PROC_INST_ID_ " +
			        		"AND v.NAME_='deptid' left join sys_dept sd on sd.deptid=v.TEXT_ WHERE RES.SUSPENSION_STATE_ = 1" +
			        		" and (RES.ASSIGNEE_ = #{userid} or (RES.ASSIGNEE_ is null and (I.USER_ID_ = #{userid} or I.GROUP_ID_ IN  " +
			        		"(select r.roleid  from sys_user_role s left join sys_role r on s.ROLEID=r.ROLEID left join sys_user  u on s.USERID=u.USERID  LEFT JOIN sys_dept_employee emp on u.USERID=emp.USERID  LEFT JOIN sys_dept dept on dept.DEPTID=emp.DEPTID   where s.USERID=#{userid} AND (dept.orgid=v.TEXT_ or sd.parentdeptid=dept.orgid)) " 
			        		//" or  I.GROUP_ID_ IN  "+
			        		//"(select r.roleid  from sys_user_role s left join sys_role r on s.ROLEID=r.ROLEID left join sys_user  u on s.USERID=u.USERID  left join sys_dept_employee e on e.USERID=u.USERID  left join sys_dept de on de.PARENTDEPTID=e.DEPTID  where s.USERID=#{userid} ) 
			        		+") ) ) " 
			        		+query+" "+paramsql+" order by RES.ID_ desc")
			        .parameter("assignee",this.getLoginUser().getUserid()).parameter("userid",this.getLoginUser().getUserid()).count();
			        
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
	        for(Task ta :tasks){
	        	sb.append("{");
				sb.append("\"id\":").append("\"").append(ta.getId()).append("\",");
	        	sb.append("\"taskDefinitionKey\":").append("\"").append(ta.getTaskDefinitionKey()).append("\",");
	        	sb.append("\"name\":").append("\"").append(ta.getName()).append("\",");
	        	sb.append("\"processDefinitionId\":").append("\"").append(ta.getProcessDefinitionId()==null?"":ta.getProcessDefinitionId()).append("\",");
	        	sb.append("\"priority\":").append("\"").append(ta.getPriority()).append("\",");
	        	sb.append("\"createTime\":").append("\"").append(ta.getCreateTime()==null?"":sdf.format(ta.getCreateTime())).append("\",");
	        	sb.append("\"dueDate\":").append("\"").append(ta.getDueDate()==null?"":sdf.format(ta.getDueDate())).append("\",");
	        	sb.append("\"description\":").append("\"").append(ta.getDescription()==null?"":ta.getDescription()).append("\",");
	        	sb.append("\"owner\":").append("\"").append(ta.getOwner()==null?"":ta.getOwner()).append("\",");
	        	sb.append("\"assignee\":").append("\"").append(ta.getAssignee()==null?"":ta.getAssignee()).append("\",");
	        	//根据流程实例id查询出业务id和uri
	        	//sb.append("\"bussinesskey\":").append("\"").append(bu.getBussinesskey()==null?"":bu.getBussinesskey()).append("\",");
	        	ProcessDefinition prd = repositoryService.createProcessDefinitionQuery().processDefinitionId(ta.getProcessDefinitionId()).singleResult();
	        	Sys_wfparamEntity wf = sys_wfparamService.selectById(prd.getKey());
	        	sb.append("\"uri\":").append("\"").append(wf.getInfouri()==null?"":wf.getInfouri()).append("\",");
	        	sb.append("\"nodeaction\":").append("\"").append(wf.getNodeaction()==null?"":wf.getNodeaction()).append("\",");
	        	ProcessInstance pro = runtimeService.createProcessInstanceQuery().processInstanceId(ta.getProcessInstanceId()).singleResult();
	        	sb.append("\"bussinesskey\":").append("\"").append(pro.getBusinessKey()==null?"":pro.getBusinessKey()).append("\",");
	        	sb.append("\"processInstanceId\":").append("\"").append(ta.getProcessInstanceId()==null?"":ta.getProcessInstanceId()).append("\"},");
	        	
	        }
	        String str = sb.toString();
			if(tasks.size()>0){
				str = str.substring(0,str.length()-1);
			}
			String jsonstr = str+"]}";
			this.setJsonStr(jsonstr);	
			System.out.println(jsonstr);
		}catch (Exception e) {
			e.printStackTrace();
		}
        return SUCCESS;
    }
	/**
     * 签收任务
     */
    @Action(value = "claim", results = { @Result(name = "success", location = "/webpage/pub/result.jsp") })
    public String claim() {
        String userId = this.getLoginUser().getUserid();
        taskService.claim(taskId, userId);
        this.setJsonStr("任务已签收");
        return SUCCESS;
    }

    /**
     * 读取Task的表单
     */
	@Action(value = "getTaskForm", results = { @Result(name = "success", location = "/webpage/pub/jsondata.jsp") })
    public String findTaskForm() throws Exception {
        Map<String, Object> result = new HashMap<String, Object>();
        TaskFormDataImpl taskFormData = (TaskFormDataImpl) formService.getTaskFormData(taskId);

        // 设置task为null，否则输出json的时候会报错
        taskFormData.setTask(null);

        result.put("taskFormData", taskFormData);
    /*
     * 读取enum类型数据，用于下拉框
     */
        List<FormProperty> formProperties = taskFormData.getFormProperties();
        for (FormProperty formProperty : formProperties) {
            Map<String, String> values = (Map<String, String>) formProperty.getType().getInformation("values");
            if (values != null) {
                for (Entry<String, String> enumEntry : values.entrySet()) {
                    logger.debug("enum, key: {}, value: {}", enumEntry.getKey(), enumEntry.getValue());
                }
                result.put(formProperty.getId(), values);
            }
        }

        this.setJsonStr("["+JSONUtil.toJSONString(result)+"]");
        return SUCCESS;
    }
	  /**
     * 办理任务，提交task的并保存form
     */
	@Action(value = "completeTask", results = { @Result(name = "success", location = "/webpage/pub/result.jsp") })
    public String completeTask() {
        Map<String, String> formProperties = new HashMap<String, String>();
        // 从request中读取参数然后转换
        String []str = formdata.split("\\^\\%\\$\\#\\^");
        for (int i=0;i<str.length;i++) {
            String []pstr = str[i].split("\\@\\#\\$\\%\\^");
            // fp_的意思是form paremeter
            formProperties.put(pstr[0], pstr[1]);
           
        }
        try {
            identityService.setAuthenticatedUserId(this.getLoginUser().getUserid());
            formService.submitTaskFormData(taskId, formProperties);
        }catch (Exception e) {
			e.printStackTrace();
		}
        finally {
            identityService.setAuthenticatedUserId(null);
        }

        this.setJsonStr("任务完成：taskId=" + taskId);
        return SUCCESS;
    }
	 /**
     * 办理不是动态表单任务
     */
	@Action(value = "completenodyTask", results = { @Result(name = "success", location = "/webpage/pub/result.jsp") })
    public String completenodyTask() {
        try {
        	Map<String, Object> variables = va.getVariableMap();
            taskService.complete(taskId, variables);
        }catch (Exception e) {
			e.printStackTrace();
		}
        this.setJsonStr("任务完成：taskId=" + taskId);
        return SUCCESS;
    }
	/**
	 * 审批的历史信息
	 * @return
	 */
	@Action(value = "approvalhis", results = { @Result(name = "success", location = "/webpage/pub/grid/pager.jsp") })
	public String  approvalhis(){
		try{   
			   //根据流程id查询出流程key，根据流程key查询出所有的流程定义id
			   //，然后查询出所有的流程实例，最后根据流程实例id查询出所有的历史记录
			   StringBuffer sb = new StringBuffer();
			   List<ProcessDefinition> listpro=new ArrayList<ProcessDefinition>();
			   if(prokey==null || "".equals(prokey.trim())){
				   ProcessDefinitionQuery pro = repositoryService.createProcessDefinitionQuery();
				   ProcessDefinition prd = pro.processDefinitionId(processDefinitionId).singleResult();
				   listpro = repositoryService.createProcessDefinitionQuery().processDefinitionKey(prd.getKey()).orderByProcessDefinitionVersion().desc().list();
			   }else{
				   listpro = repositoryService.createProcessDefinitionQuery().processDefinitionKey(prokey).orderByProcessDefinitionVersion().desc().list();
			   }
			   long  records = 0;
			   List<HistoricTaskInstance> hvisall = new ArrayList<HistoricTaskInstance>();
			   List<HistoricActivityInstance> hisactall = new ArrayList<HistoricActivityInstance>();
			   String comppro = "";
			   for(ProcessDefinition proceed : listpro){
				   List<HistoricTaskInstance> hvis = historyService.createNativeHistoricTaskInstanceQuery().sql("select distinct RES.* from ACT_HI_TASKINST RES inner join ACT_HI_PROCINST HPI ON RES.PROC_INST_ID_ = HPI.ID_ " 
						   	+" WHERE  RES.PROC_DEF_ID_ = #{PROC_DEF_ID_}  and HPI.BUSINESS_KEY_ = #{BUSINESS_KEY_} AND (IFNULL(RES.DELETE_REASON_,'')!='deleted' )  "
						   	+"   order by  RES.START_TIME_ DESC,  RES.END_TIME_ ASC ").parameter("PROC_DEF_ID_",proceed.getId()).parameter("BUSINESS_KEY_",bussinesskey)
						   	.list();//创建一个历史流程变量实例查询对象
				   records += historyService.createNativeHistoricTaskInstanceQuery().sql("select count(1) from (select distinct RES.* from ACT_HI_TASKINST RES inner join ACT_HI_PROCINST HPI ON RES.PROC_INST_ID_ = HPI.ID_ " 
						   	+" WHERE RES.PROC_DEF_ID_ = #{PROC_DEF_ID_} and HPI.BUSINESS_KEY_ = #{BUSINESS_KEY_}  AND (IFNULL(RES.DELETE_REASON_,'')!='deleted' )  "
						   	+" ) as a ").parameter("PROC_DEF_ID_",proceed.getId()).parameter("BUSINESS_KEY_",bussinesskey)
						   	.count();
				   /*if(hvis.size()>0){
					   for(int i=0;i<hvis.size();i++){
						   HistoricTaskInstance hist = hvis.get(i);
							   List<HistoricActivityInstance> hisacts = historyService.createHistoricActivityInstanceQuery().activityType("userTask")
									   .processInstanceId(hist.getProcessInstanceId()).orderByHistoricActivityInstanceStartTime().desc().orderByHistoricActivityInstanceEndTime().asc().list();      
							   hisactall.addAll(hisacts);
					   }
					 
				   }*/
				   hvisall.addAll(hvis);
			   }
			   long total = (records+this.getPager().getPageSize()-1)/this.getPager().getPageSize();
			   //将部署的时间格式化 
			   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			   sb.append("{").append("\"total\":").append(total).append(",");
			   sb.append("\"page\":").append(this.getPager().getPageId()).append(",");
			   sb.append("\"records\":").append(records).append(",");
			   sb.append("\"rows\":[");
			   long counter = (this.getPager().getStartrow()-1+this.getPager().getPageSize())>records?records:(this.getPager().getStartrow()-1+this.getPager().getPageSize());
			   for(int i=(this.getPager().getStartrow()-1);i<counter;i++){
				   HistoricTaskInstance hi = hvisall.get(i);
				   sb.append("{");
				   sb.append("\"id\":").append("\"").append(hi.getId()).append("\",");
				   //获取是任务询ACT_HI_ACTINST表的任务id
				   //HistoricActivityInstance ac = hisactall.get(i);
				   List<Comment> comments = taskService.getTaskComments(hi.getId());
				   sb.append("\"name\":").append("\"").append(hi.getName()).append("\",");
				   //UserEntity user = userService.selectById(hi.getAssignee());
				   if(hi.getAssignee()!=null && !"".equals(hi.getAssignee())){
					   StaffEntity st = staffService.selectById(hi.getAssignee());
					   sb.append("\"username\":").append("\"").append(st.getStaffname()).append("\",");
				   }
				   if(comments.size()>0){
					   if(comments.get(1).getFullMessage() !=null && (comments.get(1).getFullMessage().equals("true") || comments.get(1).getFullMessage().equals("false"))  ){
						   if(comments.get(1).getFullMessage().equals("true")){
							   sb.append("\"message1\":").append("\"").append( comments.get(1).getFullMessage()==null?"":"通过").append("\",");
						   }else{
							   sb.append("\"message1\":").append("\"").append( comments.get(1).getFullMessage()==null?"":"驳回").append("\",");
						   }
					   }
					   if(comments.get(0).getFullMessage() !=null && (comments.get(0).getFullMessage().equals("true") || comments.get(0).getFullMessage().equals("false"))  ){
						   if(comments.get(0).getFullMessage().equals("true")){
							   sb.append("\"message1\":").append("\"").append( comments.get(0).getFullMessage()==null?"":"通过").append("\",");
						   }else{
							   sb.append("\"message1\":").append("\"").append( comments.get(0).getFullMessage()==null?"":"驳回").append("\",");
						   }
					   }
					   if(comments.get(1).getFullMessage() !=null && !comments.get(1).getFullMessage().equals("true") && !comments.get(1).getFullMessage().equals("false")  ){
						   sb.append("\"message2\":").append("\"").append( comments.get(1).getFullMessage()==null?"":comments.get(1).getFullMessage()).append("\",");
					   }
					   if(comments.get(0).getFullMessage() !=null && !comments.get(0).getFullMessage().equals("true") && !comments.get(0).getFullMessage().equals("false")  ){
						   sb.append("\"message2\":").append("\"").append( comments.get(0).getFullMessage()==null?"":comments.get(0).getFullMessage()).append("\",");
					   }
					  // sb.append("\"message2\":").append("\"").append( comments.get(0).getFullMessage()==null?"":comments.get(1).getFullMessage()).append("\",");
				   }
				   sb.append("\"endtime\":").append("\"").append(hi.getEndTime()==null?"":sdf.format(hi.getEndTime())).append("\"},");
				   
			   }
			    String str = sb.toString();
				if(hvisall.size()>0){
					str = str.substring(0,str.length()-1);
				}
				String jsonstr = str+"]}";
				
				System.out.println(jsonstr);
				this.setJsonStr(jsonstr);	
			   
		}
		 catch (Exception e) {
			e.printStackTrace();
		}
		   return SUCCESS;

	}
	/**
	 * 我参加的已经待办的任务
	 * @return
	 */
	@Action(value = "hispartMisson", results = { @Result(name = "success", location = "/webpage/pub/grid/pager.jsp") })
	public String hispartMisson(){
		  try{   
			   //根据流程id查询出流程key，根据流程key查询出所有的流程定义id
			   //，然后查询出所有的流程实例，最后根据流程实例id查询出所有的历史记录
			   StringBuffer sb = new StringBuffer();
			   long  records = 0;
			   List<HistoricTaskInstance> hvisall = new ArrayList<HistoricTaskInstance>();
			   List<HistoricActivityInstance> hisactall = new ArrayList<HistoricActivityInstance>();
			   hvisall =  historyService.createHistoricTaskInstanceQuery().taskAssignee(this.getLoginUser().getUserid()).orderByHistoricTaskInstanceStartTime().desc().orderByHistoricTaskInstanceEndTime().asc().listPage((this.getPager().getStartrow()-1),this.getPager().getPageSize());;//创建一个历史流程变量实例查询对象
			   records = historyService.createHistoricTaskInstanceQuery().taskAssignee(this.getLoginUser().getUserid()).count();
			   long total = (records+this.getPager().getPageSize()-1)/this.getPager().getPageSize();
			   //将部署的时间格式化 
			   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			   sb.append("{").append("\"total\":").append(total).append(",");
			   sb.append("\"page\":").append(this.getPager().getPageId()).append(",");
			   sb.append("\"records\":").append(records).append(",");
			   sb.append("\"rows\":[");
			  // long counter = (this.getPager().getStartrow()-1+this.getPager().getPageSize())>records?records:(this.getPager().getStartrow()-1+this.getPager().getPageSize());
			   for(int i=0;i<records;i++){
				   HistoricTaskInstance hi = hvisall.get(i);
				   Task task = taskService.createTaskQuery().executionId(hi.getExecutionId()).singleResult();
				   //业务名称
				   String ywname = (String) taskService.getVariable(task.getId(),"decription");
				   sb.append("{");
				   sb.append("\"id\":").append("\"").append(hi.getId()).append("\",");
				   //获取是任务询ACT_HI_ACTINST表的任务id
				   //HistoricActivityInstance ac = hisactall.get(i);
				   sb.append("\"name\":").append("\"").append(hi.getName()).append("\",");
				   sb.append("\"ywname\":").append("\"").append(ywname).append("\",");
				   //UserEntity user = userService.selectById(hi.getAssignee());
				   if(hi.getAssignee()!=null && !"".equals(hi.getAssignee())){
					   StaffEntity st = staffService.selectById(hi.getAssignee());
					   sb.append("\"username\":").append("\"").append(st.getStaffname()).append("\",");
				   }
				   //查询流程名称
				   ProcessDefinition pro = repositoryService.createProcessDefinitionQuery().processDefinitionId(hi.getProcessDefinitionId()).singleResult();
				   sb.append("\"processname\":").append("\"").append(pro.getName()).append("\",");
				   
				   //查询任务发起人
				   StaffEntity st = staffService.selectById((String) taskService.getVariable(task.getId(),"startuserid"));
				   sb.append("\"appusername\":").append("\"").append(st.getStaffname()).append("\",");
				  
				   sb.append("\"message1\":").append("\"").append(taskService.getTaskComments(hi.getId(),"nodepass").size()>0?taskService.getTaskComments(hi.getId(),"nodepass").get(0).getFullMessage():"").append("\",");
				   sb.append("\"message2\":").append("\"").append(taskService.getTaskComments(hi.getId(),"comment").size()>0?taskService.getTaskComments(hi.getId(),"comment").get(0).getFullMessage():"").append("\",");
				  
				   sb.append("\"endtime\":").append("\"").append(hi.getEndTime()==null?"":sdf.format(hi.getEndTime())).append("\"},");
				   
			   }
			    String str = sb.toString();
				if(hvisall.size()>0){
					str = str.substring(0,str.length()-1);
				}
				String jsonstr = str+"]}";
				
				this.setJsonStr(jsonstr);	
			   
		}
		 catch (Exception e) {
			e.printStackTrace();
		}
		   return SUCCESS;
	}
    /**
     * 
     * @param repositoryService
     */
	@Autowired
	public void setRepositoryService(RepositoryService repositoryService) {
		this.repositoryService = repositoryService;
	}
	
	@Autowired
	public WorkflowTraceService getTraceService() {
		return traceService;
	}
	public void setTraceService(WorkflowTraceService traceService) {
		this.traceService = traceService;
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public String getFileFileName() {
		return fileFileName;
	}
	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}
	public String getDeploymentId() {
		return deploymentId;
	}
	public void setDeploymentId(String deploymentId) {
		this.deploymentId = deploymentId;
	}
	
	public WorkflowEntity getBean() {
		return bean;
	}
	public void setBean(WorkflowEntity bean) {
		this.bean = bean;
	}
	public String getResourceType() {
		return resourceType;
	}
	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}
	public String getSuspended() {
		return suspended;
	}
	public void setSuspended(String suspended) {
		this.suspended = suspended;
	}
	public String getProcessDefinitionId() {
		return processDefinitionId;
	}
	public void setProcessDefinitionId(String processDefinitionId) {
		this.processDefinitionId = processDefinitionId;
	}
	public String getFormdata() {
		return formdata;
	}
	public void setFormdata(String formdata) {
		this.formdata = formdata;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public Variable getVa() {
		return va;
	}
	public void setVa(Variable va) {
		this.va = va;
	}
	public String getProcessInstanceId() {
		return processInstanceId;
	}
	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}
	@Autowired
	public void setUserService(UserService<UserEntity> userService) {
		this.userService = userService;
	}
	public String getBussinesskey() {
		return bussinesskey;
	}
	public void setBussinesskey(String bussinesskey) {
		this.bussinesskey = bussinesskey;
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
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getProkey() {
		return prokey;
	}
	public void setProkey(String prokey) {
		this.prokey = prokey;
	}
	
	
}