package com.dimine.customer.action;

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
import com.dimine.base.util.json.JSONUtil;
import com.dimine.sys.util.PublicUtil;
import com.dimine.base.common.ValidException;
import com.dimine.customer.entity.Dz_customer_adressEntity;
import com.dimine.customer.service.Dz_customer_adressService;

/**
 * 用于对客户地址管理进行系列的操作的action
 * 
 * @author dimine 2019-09-01 15:26:59
 * 
 */
@Namespace("/webpage/biz/customer/adress")
@Scope("request")
@Controller("dz_customer_adressAction")
public class Dz_customer_adressAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private final static Logger logger = Logger.getLogger(Dz_customer_adressAction.class);
	// 业务处理
	@Resource
	private Dz_customer_adressService<Dz_customer_adressEntity> dz_customer_adressService;

	// 参数实体
	private Dz_customer_adressEntity bean = new Dz_customer_adressEntity();
	/**
	 * 添加类型 addmore为新增保存
	 */
	private String addtype;
	
	private String somename;
	
	private String encode;
	
	private String param;
	
	private String filters;
	 

	/**
	 * 对客户地址管理进行列表查询操作
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
			bean.setPager(this.getPager());
			List<Dz_customer_adressEntity> dataList = dz_customer_adressService.selectByList(bean);
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
	 * 新增客户地址管理信息查询 <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@Action(value = "doAdd", results = { @Result(name = "success", location = "/webpage/biz/customer/adress/info.jsp") })
	public String doAdd() {
		// TODO 其他业务处理
		this.setActiontype(ACTIONTYPE_ADDSAVE);
		return SUCCESS;
	}

	/**
	 * 新增客户地址管理信息保存 <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@Action(value = "doAddSave", results = { @Result(name = "success", location = "/webpage/pub/beanresult.jsp") })
	public String doAddSave() {
		try {
			dz_customer_adressService.insert(bean);
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
	 * 删除客户地址管理信息 <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@Action(value = "delete", results = { @Result(name = "success", location = "/webpage/pub/beanresult.jsp") })
	public String doDelete() {
		try {
			dz_customer_adressService.delete(bean);
		} catch (Exception e) {
			bean.setErrorMessage("failed");
			e.printStackTrace();
		}
		this.setJsonStr(JSONUtil.toJSONString(bean));
		return SUCCESS;
	}

	/**
	 * 对客户地址管理进行修改查询操作
	 */
	@Action(value = "doModify", results = { @Result(name = "success", location = "/webpage/biz/customer/adress/info.jsp") })
	public String doModify() {
		try {
			if (getActiontype().equals(ACTIONTYPE_MODIFY)) {
				this.setActiontype(ACTIONTYPE_MODIFYSAVE);
			} else if (getActiontype().equals(ACTIONTYPE_VIEW)) {
				this.setActiontype(ACTIONTYPE_VIEW);
			} else if (getActiontype().equals(ACTIONTYPE_DELETE)) {
				this.setActiontype(ACTIONTYPE_DELETESAVE);
			}			
			bean = dz_customer_adressService.selectById(bean.getId());
		} catch (Exception e) {
			this.setErrorMessage(this.getText("modifyfailure"));
		}
		return SUCCESS;
	}

	/**
	 * 对客户地址管理进行存储和修改操作
	 */
	@Action(value = "doModifySave", results = { @Result(name = "success", location = "/webpage/pub/beanresult.jsp") })
	public String doModifySave() {
		try {
   			dz_customer_adressService.update(bean, getActiontype());				
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

	// 日志文件用
	@Override
	public String getBiztypename() {
		return "客户地址管理信息管理";
	}

	public Dz_customer_adressEntity getBean() {
		return bean;
	}

	public void setBean(Dz_customer_adressEntity bean) {
		this.bean = bean;
	}

	public Dz_customer_adressService<Dz_customer_adressEntity> getDz_customer_adressService() {
		return dz_customer_adressService;
	}

	public void setDz_customer_adressService(Dz_customer_adressService<Dz_customer_adressEntity> dz_customer_adressService) {
		this.dz_customer_adressService = dz_customer_adressService;
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
