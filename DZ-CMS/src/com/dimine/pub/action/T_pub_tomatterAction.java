package com.dimine.pub.action;

import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.dimine.base.common.ValidException;
import com.dimine.base.page.Pager;
import com.dimine.base.util.SQLCallbackImpl;
import com.dimine.base.util.SQLTool;
import com.dimine.base.util.json.JSONUtil;
import com.dimine.pub.entity.T_pub_tomatterEntity;
import com.dimine.pub.service.T_pub_tomatterService;
import com.dimine.util.StringUtils;

/**
 * 用于对待办事项管理进行系列的操作的action
 * 
 * @author dimine 2017-11-16 15:35:42
 * 
 */
@Namespace("/webpage/biz/pub/tomatter")
@Scope("request")
@Controller("t_pub_tomatterAction")
public class T_pub_tomatterAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private final static Logger logger = Logger.getLogger(T_pub_tomatterAction.class);
	// 业务处理
	@Resource
	private T_pub_tomatterService<T_pub_tomatterEntity> t_pub_tomatterService;

	// 参数实体
	private T_pub_tomatterEntity bean = new T_pub_tomatterEntity();
	/**
	 * 添加类型 addmore为新增保存
	 */
	private String addtype;
	
	private String somename;
	
	private String encode;
	
	private String param;
	
	private String filters;
	 

	/**
	 * 对待办事项管理进行列表查询操作
	 * 
	 * @return
	 */
	@Action(value = "list", results = { @Result(name = "success", location = "/webpage/pub/grid/pager.jsp") })
	public String list() {
		try {
			if(filters!=null){
				String query = new SQLTool().constructWhere(filters, new SQLCallbackImpl());
				bean.setQuery(query);
			}
			Pager pager = this.getPager();
			if(StringUtils.isEmpty(pager.getOrderField())){
				pager.setOrderDirection(false);
				pager.setOrderField("addtime");
			}
			bean.setPager(pager);
			bean.setUserid(this.getLoginUser().getUserid());
			List<T_pub_tomatterEntity> dataList = t_pub_tomatterService.selectByList(bean);
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
	 * 新增待办事项管理信息查询 <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@Action(value = "doAdd", results = { @Result(name = "success", location = "/webpage/biz/pub/tomatter/info.jsp") })
	public String doAdd() {
		// TODO 其他业务处理
		this.setActiontype(ACTIONTYPE_ADDSAVE);
		return SUCCESS;
	}

	/**
	 * 新增待办事项管理信息保存 <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@Action(value = "doAddSave", results = { @Result(name = "success", location = "/webpage/pub/beanresult.jsp") })
	public String doAddSave() {
		try {
			t_pub_tomatterService.insert(bean);
		} catch (ValidException ee) {
			bean.setErrorMessage(ee.getMessage());
			ee.printStackTrace();
		} catch (Exception e) {
			bean.setErrorMessage("failed");
			e.printStackTrace();
		}
		this.setJsonStr(JSONUtil.toJSONString(bean));
		return SUCCESS;
	}

	/**
	 * 删除待办事项管理信息 <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@Action(value = "delete", results = { @Result(name = "success", location = "/webpage/pub/beanresult.jsp") })
	public String doDelete() {
		try {
			t_pub_tomatterService.delete(bean);
		} catch (Exception e) {
			bean.setErrorMessage("failed");
			e.printStackTrace();
		}
		this.setJsonStr(JSONUtil.toJSONString(bean));
		return SUCCESS;
	}

	/**
	 * 对待办事项管理进行修改查询操作
	 */
	@Action(value = "doModify", results = { @Result(name = "success", location = "/webpage/biz/pub/tomatter/info.jsp") })
	public String doModify() {
		try {
			if (getActiontype().equals(ACTIONTYPE_MODIFY)) {
				this.setActiontype(ACTIONTYPE_MODIFYSAVE);
			} else if (getActiontype().equals(ACTIONTYPE_VIEW)) {
				this.setActiontype(ACTIONTYPE_VIEW);
			} else if (getActiontype().equals(ACTIONTYPE_DELETE)) {
				this.setActiontype(ACTIONTYPE_DELETESAVE);
			}			
			bean = t_pub_tomatterService.selectById(bean.getMatterid());
		} catch (Exception e) {
			this.setErrorMessage(this.getText("modifyfailure"));
		}
		return SUCCESS;
	}

	/**
	 * 对待办事项管理进行存储和修改操作
	 */
	@Action(value = "doModifySave", results = { @Result(name = "success", location = "/webpage/pub/beanresult.jsp") })
	public String doModifySave() {
		try {
			if(bean.getMattertype().equals("CLZT002")){
				bean.setHandlename(this.getLoginUser().getUserid());
				Date date = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				bean.setHandletime(sdf.format(date));
			}
			bean.setUserid(this.getLoginUser().getUserid());
   			t_pub_tomatterService.update(bean, getActiontype());				
		} catch (ValidException ee) {
			bean.setErrorMessage(ee.getMessage());
			ee.printStackTrace();
		} catch (Exception e) {
			bean.setErrorMessage("failed");
			e.printStackTrace();
		}
		this.setJsonStr(JSONUtil.toJSONString(bean));
		return SUCCESS;
	}
	@Action(value = "doviewdealt", results = { @Result(name = "success", location = "/webpage/pub/jsondata.jsp") })
	public String doviewdealt(){
		try{
			Map<String, Object> jsonMap = new HashMap<String, Object>();
			bean.setUserid(this.getLoginUser().getUserid());
			bean.setUserid(this.getLoginUser().getUserid());
			Integer total = t_pub_tomatterService.selectByCount(bean);
			jsonMap.put("total",total);
			this.setJsonStr(JSONUtil.toJSONString(jsonMap));
		}catch (Exception e) {
			e.printStackTrace();
			this.setErrorMessage(e);
		}
		return SUCCESS;
		
	}
	// 日志文件用
	@Override
	public String getBiztypename() {
		return "待办事项管理信息管理";
	}

	public T_pub_tomatterEntity getBean() {
		return bean;
	}

	public void setBean(T_pub_tomatterEntity bean) {
		this.bean = bean;
	}

	public T_pub_tomatterService<T_pub_tomatterEntity> getT_pub_tomatterService() {
		return t_pub_tomatterService;
	}

	public void setT_pub_tomatterService(T_pub_tomatterService<T_pub_tomatterEntity> t_pub_tomatterService) {
		this.t_pub_tomatterService = t_pub_tomatterService;
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
