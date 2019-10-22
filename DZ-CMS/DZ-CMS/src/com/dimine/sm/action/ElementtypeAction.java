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
import com.dimine.sm.entity.ElementtypeEntity;
import com.dimine.sm.service.ElementtypeService;

/**
 * 用于对要素分类表进行系列的操作的action
 * 
 * @author dimine 2015-11-09 11:23:34
 * 
 */
@Namespace("/biz/sm/elementtype")
@Scope("request")
@Controller("elementtypeAction")
public class ElementtypeAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private final static Logger logger = Logger
			.getLogger(ElementtypeAction.class);
	// 业务处理
	@Resource
	private ElementtypeService<ElementtypeEntity> elementtypeService;

	// 参数实体
	private ElementtypeEntity bean = new ElementtypeEntity();
	/**
	 * 添加类型 addmore为新增保存
	 */
	private String addtype;

	private String somename;

	private String encode;

	private String param;

	private String filters;

	/**
	 * 对要素分类表进行列表查询操作
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
			bean.setDeptid(this.getLoginUser().getOrgid());
			List<ElementtypeEntity> dataList = elementtypeService
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
	 * 新增信息查询 <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@Action(value = "doAdd", results = { @Result(name = "success", location = "/webpage/biz/sm/elementtype/info.jsp") })
	public String doAdd() {
		// TODO 其他业务处理
		this.setActiontype(ACTIONTYPE_ADDSAVE);
		return SUCCESS;
	}

	/**
	 * 新增要素分类表信息保存 <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@Action(value = "doAddSave", results = { @Result(name = "success", location = "/webpage/pub/beanresult.jsp") })
	public String doAddSave() {
		try {
			bean.setCreateid(this.getLoginUser().getUserid());
			bean.setDeptid(this.getLoginUser().getOrgid());
			if (elementtypeService.selectByEletypeAndDept(bean)) {
				this.setErrorMessage("已经存在分类");
			} else {
				elementtypeService.insert(bean);
			}
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
	 * 删除要素分类表信息 <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@Action(value = "delete", results = { @Result(name = "success", location = "/webpage/pub/beanresult.jsp") })
	public String doDelete() {
		try {
			elementtypeService.delete(bean);
		} catch (Exception e) {
			this.setErrorMessage("failed");
			e.printStackTrace();
		}
		this.setJsonStr(JSONUtil.toJSONString(bean));
		return SUCCESS;
	}

	/**
	 * 对要素分类表进行修改查询操作
	 */
	@Action(value = "doModify", results = { @Result(name = "success", location = "/webpage/biz/sm/elementtype/info.jsp") })
	public String doModify() {
		try {
			if (getActiontype().equals(ACTIONTYPE_MODIFY)) {
				this.setActiontype(ACTIONTYPE_MODIFYSAVE);
			} else if (getActiontype().equals(ACTIONTYPE_VIEW)) {
				this.setActiontype(ACTIONTYPE_VIEW);
			} else if (getActiontype().equals(ACTIONTYPE_DELETE)) {
				this.setActiontype(ACTIONTYPE_DELETESAVE);
			}
			bean = elementtypeService.selectById(bean);
		} catch (Exception e) {
			this.setErrorMessage(this.getText("modifyfailure"));
		}
		return SUCCESS;
	}

	/**
	 * 对要素分类表进行存储和修改操作
	 */
	@Action(value = "doModifySave", results = { @Result(name = "success", location = "/webpage/pub/beanresult.jsp") })
	public String doModifySave() {
		try {
			bean.setModifyid(this.getLoginUser().getUserid());
			bean.setDeptid(this.getLoginUser().getOrgid());
			elementtypeService.update(bean, getActiontype());
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

	@Action(value = "allinsert", results = { @Result(name = "success", location = "/webpage/pub/grid/pager.jsp") })
	public String allinsert() {
		try {
			String info = "yes";
			bean.setDeptid(this.getLoginUser().getOrgid());
			int c = elementtypeService.isimportinsert(bean);
			if (c == 0) {
				elementtypeService.allinsert(bean);
			} else {
				info = "no";
			}
			this.setJsonStr(info);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}

	// 日志文件用
	@Override
	public String getBiztypename() {
		return "要素分类表信息管理";
	}

	public ElementtypeEntity getBean() {
		return bean;
	}

	public void setBean(ElementtypeEntity bean) {
		this.bean = bean;
	}

	public ElementtypeService<ElementtypeEntity> getElementtypeService() {
		return elementtypeService;
	}

	public void setElementtypeService(
			ElementtypeService<ElementtypeEntity> elementtypeService) {
		this.elementtypeService = elementtypeService;
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
