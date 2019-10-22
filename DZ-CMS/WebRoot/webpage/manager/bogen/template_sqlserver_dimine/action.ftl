package com.dimine.${modelPath ?lower_case}.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.dimine.base.action.BaseAction;
import com.dimine.base.util.json.JSONUtil;
import com.dimine.base.util.SQLCallbackImpl;
import com.dimine.base.util.SQLTool;
import com.dimine.sys.util.PublicUtil;
import com.dimine.base.common.ValidException;
import com.dimine.${modelPath ?lower_case}.entity.${actionName ?cap_first}Entity;
import com.dimine.${modelPath ?lower_case}.service.${actionName ?cap_first}Service;

/**
 * 用于对${title}进行系列的操作的action
 * 
 * @author dimine ${sysdate}
 * 
 */
@Namespace("/${filePath ?lower_case}")
@Scope("request")
@Controller("${actionName ?lower_case}Action")
public class ${actionName ?cap_first}Action extends BaseAction {
	private static final long serialVersionUID = 1L;
	private final static Logger logger = Logger.getLogger(${actionName ?cap_first}Action.class);
	// 业务处理
	@Resource
	private ${actionName ?cap_first}Service<${actionName ?cap_first}Entity> ${actionName ?lower_case}Service;

	// 参数实体
	private ${actionName ?cap_first}Entity bean = new ${actionName ?cap_first}Entity();
	/**
	 * 添加类型 addmore为新增保存
	 */
	private String addtype;
	
	private String somename;
	
	private String encode;
	
	private String param;
	
	private String filters;
	 

	/**
	 * 对${title}进行列表查询操作
	 * 
	 * @return
	 */
	@Action(value = "list", results = { @Result(name = "success", location = "/pub/grid/pager.jsp") })
	public String list() {
		try {
			if(filters!=null){
				String query = new SQLTool().constructWhere(filters, new SQLCallbackImpl());
				bean.setQuery(query);
			}
			bean.setPager(this.getPager());
			List<${actionName ?cap_first}Entity> dataList = ${actionName ?lower_case}Service.selectByList(bean);
			// 设置页面数据
			Map<String, Object> jsonMap = new HashMap<String, Object>();
			jsonMap.put("page", bean.getPager().getPageId());
			jsonMap.put("total", bean.getPager().getPageCount());
			jsonMap.put("records", bean.getPager().getRowCount());
			jsonMap.put("rows", dataList);
			this.setJsonStr(JSONUtil.toJSONString(jsonMap));
		} catch (Exception e) {
			this.setErrorMessage(e);
		}
		return SUCCESS;
	}

	/**
	 * 新增${title}信息保存 <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@Action(value = "doAddSave", results = { @Result(name = "success", location = "/pub/result.jsp") })
	public String doAddSave() {
		try {
			${actionName ?lower_case}Service.insert(bean);
			<#assign n = 0 />
			<#list primaryKey as primaryKey>
			<#assign n = n+1 />
			<#if n <= 1>
			this.setJsonStr(bean.get${primaryKey ?cap_first}());
			</#if>
			</#list>
		} catch (ValidException ee) {
			this.setErrorMessage(ee);
			ee.printStackTrace();
		} catch (Exception e) {
			this.setErrorMessage("failed");
			e.printStackTrace();
		} 
		return SUCCESS;
	}

	/**
	 * 删除${title}信息 <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@Action(value = "delete", results = { @Result(name = "success", location = "/pub/result.jsp") })
	public String doDelete() {
		try {
			${actionName ?lower_case}Service.delete(bean);
			<#assign n = 0 />
			<#list primaryKey as primaryKey>
			<#assign n = n+1 />
			<#if n <= 1>
			this.setJsonStr(bean.get${primaryKey ?cap_first}());
			</#if>
			</#list>
		} catch (Exception e) {
			this.setErrorMessage("failed");
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 对${title}进行修改查询操作
	 */
	@Action(value = "doModify", results = { @Result(name = "success", location = "/pub/jsondata.jsp") })
	public String doModify() {
		try {			
			<#assign n = 0 />
			<#list primaryKey as primaryKey>
			<#assign n = n+1 />
			<#if n <= 1>
			bean = ${actionName ?lower_case}Service.selectById(bean);
			</#if>
			</#list>			
			this.setJsonStr("["+JSONUtil.toJSONString(bean)+"]");
			this.setActiontype(ACTIONTYPE_MODIFYSAVE);
		} catch (Exception e) {
			this.setErrorMessage(this.getText("modifyfailure"));
		}
		return SUCCESS;
	}

	/**
	 * 对${title}进行存储和修改操作
	 */
	@Action(value = "doModifySave", results = { @Result(name = "success", location = "/pub/result.jsp") })
	public String doModifySave() {
		try {
   			if (ACTIONTYPE_MODIFYSAVE.equals(getActiontype())) {// 修改保存
				${actionName ?lower_case}Service.update(bean, getActiontype());				
			<#list primaryKey as primaryKey>
			this.setJsonStr(bean.get${primaryKey ?cap_first}());
			</#list>
			}
		} catch (ValidException ee) {
			this.setErrorMessage(ee);
			ee.printStackTrace();
		} catch (Exception e) {
			this.setErrorMessage("failed");
			e.printStackTrace();
		} 
		return SUCCESS;
	}

	// 日志文件用
	@Override
	public String getBiztypename() {
		return "${title}信息管理";
	}

	public ${actionName ?cap_first}Entity getBean() {
		return bean;
	}

	public void setBean(${actionName ?cap_first}Entity bean) {
		this.bean = bean;
	}

	public ${actionName ?cap_first}Service<${actionName ?cap_first}Entity> get${actionName ?cap_first}Service() {
		return ${actionName ?lower_case}Service;
	}

	public void set${actionName ?cap_first}Service(${actionName ?cap_first}Service<${actionName ?cap_first}Entity> ${actionName ?lower_case}Service) {
		this.${actionName ?lower_case}Service = ${actionName ?lower_case}Service;
	}

	public String getAddtype() {
		return addtype;
	}

	public void setAddtype(String addtype) {
		this.addtype = addtype;
	}

	public String getSomename() {
		return somename;
	}

	public void setSomename(String somename) {
		this.somename = somename;
	}

	public String getEncode() {
		return encode;
	}

	public void setEncode(String encode) {
		this.encode = encode;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public String getFilters() {
		return filters;
	}

	public void setFilters(String filters) {
		this.filters = filters;
	}
	
}
