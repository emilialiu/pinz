package com.dimine.sys.action;

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
import com.dimine.sys.entity.Sys_modelentitymappEntity;
import com.dimine.sys.service.Sys_modelentitymappService;

/**
 * 用于对模板应用表进行系列的操作的action
 * 
 * @author dimine 2014-12-18 15:40:29
 * 
 */
@Namespace("/manager/sys/modelentitymapp")
@Scope("request")
@Controller("sys_modelentitymappAction")
public class Sys_modelentitymappAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private final static Logger logger = Logger.getLogger(Sys_modelentitymappAction.class);
	// 业务处理
	@Resource
	private Sys_modelentitymappService<Sys_modelentitymappEntity> sys_modelentitymappService;

	// 参数实体
	private Sys_modelentitymappEntity bean = new Sys_modelentitymappEntity();
	/**
	 * 添加类型 addmore为新增保存
	 */
	private String addtype;
	
	private String somename;
	
	private String encode;
	
	private String param;
	
	private String filters;
	 

	/**
	 * 对模板应用表进行列表查询操作
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
			List<Sys_modelentitymappEntity> dataList = sys_modelentitymappService.selectByList(bean);
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
	 * 新增模板应用表信息保存 <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@Action(value = "doAddSave", results = { @Result(name = "success", location = "/pub/result.jsp") })
	public String doAddSave() {
		try {
			if("0".equals(bean.getIsauto())){
				bean.setDbkey("");
			}
			sys_modelentitymappService.insert(bean);
			this.setJsonStr(bean.getTablename());
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
	 * 删除模板应用表信息 <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@Action(value = "delete", results = { @Result(name = "success", location = "/pub/result.jsp") })
	public String doDelete() {
		try {
			sys_modelentitymappService.delete(bean);
			this.setJsonStr(bean.getTablename());
		} catch (Exception e) {
			this.setErrorMessage("failed");
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 对模板应用表进行修改查询操作
	 */
	@Action(value = "doModify", results = { @Result(name = "success", location = "/pub/jsondata.jsp") })
	public String doModify() {
		try {			
			bean = sys_modelentitymappService.selectById(bean);
			this.setJsonStr("["+JSONUtil.toJSONString(bean)+"]");
			this.setActiontype(ACTIONTYPE_MODIFYSAVE);
		} catch (Exception e) {
			this.setErrorMessage(this.getText("modifyfailure"));
		}
		return SUCCESS;
	}

	/**
	 * 对模板应用表进行存储和修改操作
	 */
	@Action(value = "doModifySave", results = { @Result(name = "success", location = "/pub/result.jsp") })
	public String doModifySave() {
		try {
   			if (ACTIONTYPE_MODIFYSAVE.equals(getActiontype())) {// 修改保存
   				if("0".equals(bean.getIsauto())){
   					bean.setDbkey("");
   				}
				sys_modelentitymappService.update(bean, getActiontype());				
			this.setJsonStr(bean.getTablename());
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
		return "模板应用表信息管理";
	}

	public Sys_modelentitymappEntity getBean() {
		return bean;
	}

	public void setBean(Sys_modelentitymappEntity bean) {
		this.bean = bean;
	}

	public Sys_modelentitymappService<Sys_modelentitymappEntity> getSys_modelentitymappService() {
		return sys_modelentitymappService;
	}

	public void setSys_modelentitymappService(Sys_modelentitymappService<Sys_modelentitymappEntity> sys_modelentitymappService) {
		this.sys_modelentitymappService = sys_modelentitymappService;
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
