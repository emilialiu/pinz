package com.dimine.activity.action;

import java.io.ByteArrayInputStream;
import java.text.SimpleDateFormat;
import java.util.List;

import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.dimine.activity.entity.WorkflowEntity;
import com.dimine.base.action.BaseAction;
import com.dimine.base.util.SQLCallback;
import com.dimine.base.util.SQLTool;
import com.dimine.base.util.json.JSONUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * 流程模型控制器
 *
 * @author SSM
 */
@Namespace("/manager/model")
@Scope("request")
@Controller("modelAction")
public class ModelAction extends BaseAction{

    protected Logger logger = LoggerFactory.getLogger(ModelAction.class);
    @Autowired
    RepositoryService repositoryService;
    //模型id
    private String modelId;
    private String name;
    private String key;
    private String description;
    private WorkflowEntity bean = new WorkflowEntity();
	//查询条件
	private String filters;
	private String param;
    
	
	@Action(value = "doAdd", results = { @Result(name = "success", location = "/webpage/manager/activity/model/info.jsp") })
	public String doAdd() {
		// TODO 其他业务处理
		this.setActiontype(ACTIONTYPE_ADDSAVE);
		return SUCCESS;
	}
	
	@Action(value = "doImport", results = { @Result(name = "success", location = "/webpage/manager/activity/model/workflowinfo.jsp") })
	public String doImport() {
		// TODO 其他业务处理
		this.setActiontype(ACTIONTYPE_ADDSAVE);
		return SUCCESS;
	}
	

    /**
     * 模型列表
     */
	@Action(value = "list", results = { @Result(name = "success", location = "/webpage/pub/grid/pager.jsp") })
    public String modelList() {
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
						if(f.equals("createTime")) return "RES.CREATE_TIME_";
						if(f.equals("name")) return "RES.NAME_";
						if(f.equals("key")) return "RES.KEY_";
						if(f.equals("version")) return "RES.VERSION_";
						if(f.equals("lastUpdateTime")) return "RES.LAST_UPDATE_TIME_";
						return f;
					}
					
				});
			}
	        List<Model> list = repositoryService.createNativeModelQuery()
	        		.sql("select distinct RES.* from ACT_RE_MODEL RES where 1=1 "+query+" "+paramsql+" order by RES.LAST_UPDATE_TIME_ desc")
	        		.listPage((this.getPager().getStartrow()-1),this.getPager().getPageSize());
	        //查询出所有的记录条数
			long  records = repositoryService.createNativeModelQuery()
	        		.sql("select count(1) from ACT_RE_MODEL RES  where 1=1 "+query+" "+paramsql)
	        		.count();
			long total = (records+this.getPager().getPageSize()-1)/this.getPager().getPageSize();
			//将部署的时间格式化
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    	/**
			 * 拼接成对应的json字符窜，因为它们实体定义成的是接口
			 */
			StringBuffer sb = new StringBuffer();
			sb.append("{").append("\"total\":").append(total).append(",");
			sb.append("\"page\":").append(this.getPager().getPageId()).append(",");
			sb.append("\"records\":").append(records).append(",");
			sb.append("\"rows\":[");
			for(Model model:list){
				sb.append("{");
				sb.append("\"id\":").append("\"").append(model.getId()).append("\",");
				sb.append("\"key\":").append("\"").append(model.getKey()).append("\",");
				sb.append("\"name\":").append("\"").append(model.getName()).append("\",");
				sb.append("\"version\":").append("\"").append(model.getVersion()).append("\",");
				sb.append("\"createTime\":").append("\"").append(sdf.format(model.getCreateTime())).append("\",");
				sb.append("\"lastUpdateTime\":").append("\"").append(sdf.format(model.getLastUpdateTime())).append("\"},");
				//sb.append("\"metaInfo\":").append("'").append(model.getMetaInfo()).append("'},");
				
			}
			String str = sb.toString();
			if(list.size()>0){
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

    /**
     * 创建模型
     */
    @Action(value = "doAddSave", results = { @Result(name = "success", location = "/webpage/pub/beanresult.jsp") })
    public String create() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode editorNode = objectMapper.createObjectNode();
            editorNode.put("id", "canvas");
            editorNode.put("resourceId", "canvas");
            ObjectNode stencilSetNode = objectMapper.createObjectNode();
            stencilSetNode.put("namespace", "http://b3mn.org/stencilset/bpmn2.0#");
            editorNode.put("stencilset", stencilSetNode);
            //根据key查询出是否有相同的model在数据库里面
            Model modelold = repositoryService.createModelQuery().modelKey(key).singleResult();
            if(modelold!=null){
            	//如果有老版本的model就应该将其删除
                this.setJsonStr("{\"errorMessage\":\"此模型的key已经存在!\",\"success\":false}");
            	return SUCCESS;
            }
            Model modelData = repositoryService.newModel();
            ObjectNode modelObjectNode = objectMapper.createObjectNode();
            modelObjectNode.put(ModelDataJsonConstants.MODEL_NAME, name);
            modelObjectNode.put(ModelDataJsonConstants.MODEL_REVISION, 1);
            description = StringUtils.defaultString(description);
            modelObjectNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, description);
            modelData.setMetaInfo(modelObjectNode.toString());
            modelData.setName(name);
            modelData.setKey(StringUtils.defaultString(key));
            
            repositoryService.saveModel(modelData);
            repositoryService.addModelEditorSource(modelData.getId(), editorNode.toString().getBytes("utf-8"));
            String str = JSONUtil.toJSONString(modelData);
            str = str.substring(0, str.length()-1);
            str += ",\"errorMessage\":\"\",\"success\":true}";
            this.setJsonStr(str);
            //this.getResponse().sendRedirect(this.getRequest().getContextPath() + "/service/editor?id=" + modelData.getId());
         
        } catch (Exception e) {
        	this.setErrorMessage("failed");
        	e.printStackTrace();
            logger.error("创建模型失败：", e);
        }
        return SUCCESS;
    }

    /**
     * 根据Model部署流程
     */
    @Action(value = "deploy", results = { @Result(name = "success",  location = "/webpage/pub/result.jsp") })
    public String deploy() {
        try {
            Model modelData = repositoryService.getModel(modelId);
            ObjectNode modelNode = (ObjectNode) new ObjectMapper().readTree(repositoryService.getModelEditorSource(modelData.getId()));
            byte[] bpmnBytes = null;

            BpmnModel model = new BpmnJsonConverter().convertToBpmnModel(modelNode);
            bpmnBytes = new BpmnXMLConverter().convertToXML(model);
            //解决中文不能部署的问题
            String bpmnString=new String(bpmnBytes,"UTF-8");
            String processName = modelData.getName() + ".bpmn20.xml";
            Deployment deployment = repositoryService.createDeployment().name(modelData.getName()).addString(processName,bpmnString).deploy();
            this.setJsonStr("部署成功，部署ID=" + deployment.getId());
        } catch (Exception e) {
            logger.error("根据模型部署流程失败：modelId={}", modelId, e);
            this.setErrorMessage(e);
        }
        return SUCCESS;
    }

    /**
     * 导出model的xml文件
     */
    @Action(value = "export")
    public void export() {
        try {
            Model modelData = repositoryService.getModel(modelId);
            BpmnJsonConverter jsonConverter = new BpmnJsonConverter();
            JsonNode editorNode = new ObjectMapper().readTree(repositoryService.getModelEditorSource(modelData.getId()));
            BpmnModel bpmnModel = jsonConverter.convertToBpmnModel(editorNode);
            BpmnXMLConverter xmlConverter = new BpmnXMLConverter();
            byte[] bpmnBytes = xmlConverter.convertToXML(bpmnModel);

            ByteArrayInputStream in = new ByteArrayInputStream(bpmnBytes);
            IOUtils.copy(in, this.getResponse().getOutputStream());
            String filename = bpmnModel.getMainProcess().getId() + ".bpmn20.xml";
            this.getResponse().setHeader("Content-Disposition", "attachment; filename=" + filename);
            this.getResponse().flushBuffer();
        } catch (Exception e) {
            logger.error("导出model的xml文件失败：modelId={}", modelId, e);
            this.setErrorMessage(e);
        }
    }

	@Action(value = "delete", results = { @Result(name = "success", location = "/webpage/pub/result.jsp") })
    public String delete() {
		try{
	        repositoryService.deleteModel(modelId);
	        this.setJsonStr(modelId);
		}catch (Exception e) {
			this.setErrorMessage("failed");
			e.printStackTrace();
		}
        return SUCCESS;
    }

	public String getModelId() {
		return modelId;
	}

	public void setModelId(String modelId) {
		this.modelId = modelId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public WorkflowEntity getBean() {
		return bean;
	}

	public void setBean(WorkflowEntity bean) {
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
