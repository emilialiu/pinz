package com.dimine.sm.action;

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
import com.dimine.base.util.SQLCallbackImpl;
import com.dimine.base.util.SQLTool;
import com.dimine.base.util.json.JSONUtil;
import com.dimine.sm.entity.ElementpropertyEntity;
import com.dimine.sm.service.ElementpropertyService;

/**
 * 用于对要素属性表进行系列的操作的action
 * 
 * @author dimine 2015-11-09 11:23:11
 * 
 */
@Namespace("/biz/sm/elementproperty")
@Scope("request")
@Controller("elementpropertyAction")
public class ElementpropertyAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private final static Logger logger = Logger
			.getLogger(ElementpropertyAction.class);
	// 业务处理
	@Resource
	private ElementpropertyService<ElementpropertyEntity> elementpropertyService;

	// 参数实体
	private ElementpropertyEntity bean = new ElementpropertyEntity();
	/**
	 * 添加类型 addmore为新增保存
	 */
	private String addtype;

	private String somename;

	private String encode;

	private String param;

	private String filters;

	/**
	 * 对要素属性表进行列表查询操作
	 * 
	 * @return
	 */
	@Action(value = "list", results = { @Result(name = "success", location = "/webpage/pub/grid/pager.jsp") })
	public String list() {
		try {
			if (filters != null) {
				String query = new SQLTool().constructWhere(filters,
						new SQLCallbackImpl());
				bean.setQuery(query);
			}

			bean.setPager(this.getPager());
			bean.setEleproid(this.getLoginUser().getOrgid());
			List<ElementpropertyEntity> dataList = elementpropertyService
					.selectByList(bean);
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
	 * 对要素属性表名称进行列表查询操作
	 * 
	 * @return
	 */
	@Action(value = "listelename", results = { @Result(name = "success", location = "/webpage/pub/jsondata.jsp") })
	public String listelebname() {
		try {
			if (filters != null) {
				String query = new SQLTool().constructWhere(filters,
						new SQLCallbackImpl());
				bean.setQuery(query);
			}
			bean.setPager(this.getPager());
			bean.setEleproid(this.getLoginUser().getOrgid());
			List<ElementpropertyEntity> datalist = elementpropertyService
					.selectelename(bean);
			// 设置页面数据
			if (datalist != null && datalist.size() > 0) {
				StringBuffer sbf = new StringBuffer();
				for (ElementpropertyEntity data : datalist) {
					sbf.append("'").append(data.getEleproid()).append("':'")
							.append(data.getElename()).append("',");
				}
				String _jsonData = sbf.toString();
				int length = _jsonData.length();
				this.setJsonStr(_jsonData.substring(0, length - 1));
			} else {
				this.setJsonStr("");
			}
		} catch (Exception e) {
			this.setErrorMessage(e);
		}
		return SUCCESS;
	}

	/**
	 * 对要素属性表进行列表查询根据元素类型
	 * 
	 * @return
	 */
	@Action(value = "listbyeletype", results = { @Result(name = "success", location = "/webpage/pub/grid/pager.jsp") })
	public String listByeletype() {
		try {
			if (filters != null) {
				String query = new SQLTool().constructWhere(filters,
						new SQLCallbackImpl());
				bean.setQuery(query);
			}
			bean.setPager(this.getPager());
			List<ElementpropertyEntity> dataList = elementpropertyService
					.selectByListByeletype(bean);
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
	 * 新增信息查询 <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@Action(value = "doAdd", results = { @Result(name = "success", location = "/webpage/biz/sm/elementproperty/info.jsp") })
	public String doAdd() {
		// TODO 其他业务处理
		this.setActiontype(ACTIONTYPE_ADDSAVE);
		return SUCCESS;
	}

	/**
	 * 新增要素属性表信息保存 <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@Action(value = "doAddSave", results = { @Result(name = "success", location = "/webpage/pub/beanresult.jsp") })
	public String doAddSave() {
		try {
			bean.setCreateid(this.getLoginUser().getUserid());
			elementpropertyService.insert(bean);
		} catch (ValidException ee) {
			this.setErrorMessage(ee);
			ee.printStackTrace();
		} catch (Exception e) {
			this.setErrorMessage("failed");
			e.printStackTrace();
		}
		this.setJsonStr(JSONUtil.toJSONString(bean));
		return SUCCESS;
	}

	/**
	 * 删除要素属性表信息 <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@Action(value = "delete", results = { @Result(name = "success", location = "/webpage/pub/beanresult.jsp") })
	public String doDelete() {
		try {
			elementpropertyService.delete(bean);
		} catch (Exception e) {
			this.setErrorMessage("failed");
			e.printStackTrace();
		}
		this.setJsonStr(JSONUtil.toJSONString(bean));
		return SUCCESS;
	}

	/**
	 * 对要素属性表进行修改查询操作
	 */
	@Action(value = "doModify", results = { @Result(name = "success", location = "/webpage/biz/sm/elementproperty/info.jsp") })
	public String doModify() {
		try {
			if (getActiontype().equals(ACTIONTYPE_MODIFY)) {
				this.setActiontype(ACTIONTYPE_MODIFYSAVE);
			} else if (getActiontype().equals(ACTIONTYPE_VIEW)) {
				this.setActiontype(ACTIONTYPE_VIEW);
			} else if (getActiontype().equals(ACTIONTYPE_DELETE)) {
				this.setActiontype(ACTIONTYPE_DELETESAVE);
			}
			bean = elementpropertyService.selectById(bean);
		} catch (Exception e) {
			this.setErrorMessage(this.getText("modifyfailure"));
		}
		return SUCCESS;
	}

	/**
	 * 对要素属性表进行存储和修改操作
	 */
	@Action(value = "doModifySave", results = { @Result(name = "success", location = "/webpage/pub/beanresult.jsp") })
	public String doModifySave() {
		try {
			bean.setCreateid(this.getLoginUser().getUserid());
			elementpropertyService.update(bean, getActiontype());
		} catch (ValidException ee) {
			this.setErrorMessage(ee);
			ee.printStackTrace();
		} catch (Exception e) {
			this.setErrorMessage("failed");
			e.printStackTrace();
		}
		this.setJsonStr(JSONUtil.toJSONString(bean));
		return SUCCESS;
	}

	// 日志文件用
	@Override
	public String getBiztypename() {
		return "要素属性表信息管理";
	}

	public ElementpropertyEntity getBean() {
		return bean;
	}

	public void setBean(ElementpropertyEntity bean) {
		this.bean = bean;
	}

	public ElementpropertyService<ElementpropertyEntity> getElementpropertyService() {
		return elementpropertyService;
	}

	public void setElementpropertyService(
			ElementpropertyService<ElementpropertyEntity> elementpropertyService) {
		this.elementpropertyService = elementpropertyService;
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
