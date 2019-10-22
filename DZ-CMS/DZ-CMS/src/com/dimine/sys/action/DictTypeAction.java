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
import com.dimine.base.common.ValidException;
import com.dimine.base.page.Pager;
import com.dimine.base.util.SQLCallbackImpl;
import com.dimine.base.util.SQLTool;
import com.dimine.base.util.json.JSONUtil;
import com.dimine.sm.action.StaffAction;
import com.dimine.sys.entity.DictTypeEntity;
import com.dimine.sys.service.DictTypeService;

/**
 * 系统管理--字典类型相关信息处理
 * 
 * @author LCF
 * 
 */
@Namespace("/manager/sys/dicttype")
@Scope("request")
@Controller("dictTypeAction")
public class DictTypeAction extends BaseAction {
	private static final long serialVersionUID = 3525445612504421307L;
	private final static Logger logger = Logger.getLogger(StaffAction.class);

	// 业务逻辑处理
	@Resource
	private DictTypeService<DictTypeEntity> dictTypeService = null;

	/** 参数实体 */
	private DictTypeEntity bean = new DictTypeEntity();

	private String param;

	private String filters;

	/**
	 * 资源类型列表显示
	 * 
	 * @return
	 */
	@Action(value = "typelist", results = { @Result(name = "success", location = "/webpage/pub/grid/pager.jsp") })
	public String doList() {
		try {
			if (filters != null) {
				String query = new SQLTool().constructWhere(filters,
						new SQLCallbackImpl() {
						});
				bean.setQuery(query);
			}
			Pager page = this.getPager();
			page.setOrderField("dicttypeid");
			bean.setPager(page);
			List<DictTypeEntity> dataList = dictTypeService.selectByList(bean);
			// 设置页面数据
			Map<String, Object> jsonMap = new HashMap<String, Object>();
			jsonMap.put("page", bean.getPager().getPageId());
			jsonMap.put("total", bean.getPager().getPageCount());
			jsonMap.put("records", bean.getPager().getRowCount());
			jsonMap.put("rows", dataList);
			this.setJsonStr(JSONUtil.toJSONString(jsonMap));
		} catch (Exception ee) {
			this.setErrorMessage(ee);
		}
		return SUCCESS;
	}

	/**
	 * 新增资源类型表信息查询 <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@Action(value = "doAdd", results = { @Result(name = "success", location = "/webpage/manager/sys/dict/dicttype_info.jsp") })
	public String doAdd() {
		// TODO 其他业务处理
		this.setActiontype(ACTIONTYPE_ADDSAVE);
		return SUCCESS;
	}

	/**
	 * 新增资源类型 <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@Action(value = "addtype", results = { @Result(name = "success", location = "/webpage/pub/beanresult.jsp") })
	public String doAddType() {
		try {
			dictTypeService.insert(bean);
		} catch (ValidException ee) {
			bean.setErrorMessage(ee.getMessage());
			logger.error(ee);
		} catch (Exception ex) {
			bean.setErrorMessage("failed");
			logger.error(ex);
		}
		this.setJsonStr(JSONUtil.toJSONString(bean));
		return SUCCESS;
	}

	/**
	 * 更新数据 <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@Action(value = "modifytype", results = { @Result(name = "success", location = "/webpage/pub/beanresult.jsp") })
	public String doModifyType() {
		try {
			dictTypeService.update(bean, ACTIONTYPE_MODIFYSAVE);
		} catch (Exception ee) {
			bean.setErrorMessage("failed");
			logger.error(ee);
		}
		this.setJsonStr(JSONUtil.toJSONString(bean));
		return SUCCESS;
	}

	/**
	 * 对系统参数表进行修改查询操作
	 */
	@Action(value = "doModify", results = { @Result(name = "success", location = "/webpage/manager/sys/dict/dicttype_info.jsp") })
	public String doModify() {
		try {
			if (getActiontype().equals(ACTIONTYPE_MODIFY)) {
				this.setActiontype(ACTIONTYPE_MODIFYSAVE);
			} else if (getActiontype().equals(ACTIONTYPE_VIEW)) {
				this.setActiontype(ACTIONTYPE_VIEW);
			} else if (getActiontype().equals(ACTIONTYPE_DELETE)) {
				this.setActiontype(ACTIONTYPE_DELETESAVE);
			}
			bean = dictTypeService.selectById(bean.getCode());
		} catch (Exception e) {
			this.setErrorMessage(this.getText("modifyfailure"));
		}
		return SUCCESS;
	}

	/**
	 * 删除资源类型 <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@Action(value = "deletetype", results = { @Result(name = "success", location = "/webpage/pub/beanresult.jsp") })
	public String doDeleteType() {
		try {
			dictTypeService.delete(bean.getCode());
		} catch (Exception ee) {
			bean.setErrorMessage("failed");
			logger.error(ee);
		}
		this.setJsonStr(JSONUtil.toJSONString(bean));
		return SUCCESS;
	}

	public DictTypeEntity getBean() {
		return bean;
	}

	public void setBean(DictTypeEntity bean) {
		this.bean = bean;
	}

	public DictTypeService<DictTypeEntity> getDictTypeService() {
		return dictTypeService;
	}

	public void setDictTypeService(
			DictTypeService<DictTypeEntity> dictTypeService) {
		this.dictTypeService = dictTypeService;
	}

	public String getBiztypename() {
		return "资源类型管理";
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
