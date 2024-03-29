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
import com.dimine.base.util.SQLCallback;
import com.dimine.base.util.SQLTool;
import com.dimine.base.util.StringUtils;
import com.dimine.base.util.json.JSONUtil;
import com.dimine.sm.entity.OrgcfgEntity;
import com.dimine.sm.service.OrgcfgService;

/**
 * 用于对配置组织机构类型是属于组织机构还是部门。进行系列的操作的action
 * 
 * @author dimine 2015-06-23 11:20:50
 * 
 */
@Namespace("/biz/sm/orgcfg")
@Scope("request")
@Controller("orgcfgAction")
public class OrgcfgAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private final static Logger logger = Logger.getLogger(OrgcfgAction.class);
	// 业务处理
	@Resource
	private OrgcfgService<OrgcfgEntity> orgcfgService;

	// 参数实体
	private OrgcfgEntity bean = new OrgcfgEntity();
	/**
	 * 添加类型 addmore为新增保存
	 */
	private String addtype;

	private String somename;

	private String encode;

	private String param;

	private String filters;

	/**
	 * 对配置组织机构类型是属于组织机构还是部门。进行列表查询操作
	 * 
	 * @return
	 */
	@Action(value = "list", results = { @Result(name = "success", location = "/webpage/pub/grid/pager.jsp") })
	public String list() {
		try {
			if (filters != null) {
				String query = new SQLTool().constructWhere(filters,
						new SQLCallback() {
							@Override
							public String executeField(String f) {
								if (f.equals("orgtypename"))
									return "orgtypename.paramname";
								return f;
							}

							@Override
							public String executeData(String f, String o,
									String d) {
								if (o.equals("bw") || o.equals("en"))
									return (new StringBuilder("'")).append(d)
											.append("%'").toString();
								if (o.equals("ew") || o.equals("en"))
									return (new StringBuilder("'%")).append(d)
											.append("'").toString();
								if (o.equals("cn") || o.equals("nc"))
									return (new StringBuilder("'%")).append(d)
											.append("%'").toString();
								else
									return (new StringBuilder("'")).append(d)
											.append("'").toString();
							}
						});
				query = StringUtils.replace(query, "是", "1").replace("否", "0");
				bean.setQuery(query);
			}
			this.setSort("orgtype");
			bean.setPager(this.getPager());
			List<OrgcfgEntity> dataList = orgcfgService.selectByList(bean);
			// 设置页面数据
			Map<String, Object> jsonMap = new HashMap<String, Object>();
			jsonMap.put("page", bean.getPager().getPageId());
			jsonMap.put("total", bean.getPager().getPageCount());
			jsonMap.put("records", bean.getPager().getRowCount());
			jsonMap.put("rows", dataList);
			this.setJsonStr(JSONUtil.toJSONString(jsonMap));
		} catch (Exception ex) {
			this.setErrorMessage(ex);
			logger.error(ex);
		}
		return SUCCESS;
	}

	/**
	 * 新增信息查询 <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@Action(value = "doAdd", results = { @Result(name = "success", location = "/webpage/biz/sm/orgcfg/info.jsp") })
	public String doAdd() {
		// TODO 其他业务处理
		this.setActiontype(ACTIONTYPE_ADDSAVE);
		return SUCCESS;
	}

	/**
	 * 新增配置组织机构类型是属于组织机构还是部门。信息保存 <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@Action(value = "doAddSave", results = { @Result(name = "success", location = "/webpage/pub/beanresult.jsp") })
	public String doAddSave() {
		try {
			orgcfgService.insert(bean);
		} catch (ValidException ee) {
			bean.setErrorMessage(ee);
			logger.error(ee);
		} catch (Exception ex) {
			bean.setErrorMessage("failed");
			logger.error(ex);
		}
		this.setJsonStr(JSONUtil.toJSONString(bean));
		return SUCCESS;
	}

	/**
	 * 删除配置组织机构类型是属于组织机构还是部门。信息 <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@Action(value = "delete", results = { @Result(name = "success", location = "/webpage/pub/beanresult.jsp") })
	public String doDelete() {
		try {
			orgcfgService.delete(bean);
		} catch (Exception ex) {
			bean.setErrorMessage("failed");
			logger.error(ex);
		}
		this.setJsonStr(JSONUtil.toJSONString(bean));
		return SUCCESS;
	}

	/**
	 * 对配置组织机构类型是属于组织机构还是部门。进行修改查询操作
	 */
	@Action(value = "doModify", results = { @Result(name = "success", location = "/webpage/biz/sm/orgcfg/info.jsp") })
	public String doModify() {
		try {
			if (getActiontype().equals(ACTIONTYPE_MODIFY)) {
				this.setActiontype(ACTIONTYPE_MODIFYSAVE);
			} else if (getActiontype().equals(ACTIONTYPE_VIEW)) {
				this.setActiontype(ACTIONTYPE_VIEW);
			} else if (getActiontype().equals(ACTIONTYPE_DELETE)) {
				this.setActiontype(ACTIONTYPE_DELETESAVE);
			}
			bean = orgcfgService.selectById(bean);
		} catch (Exception ex) {
			this.setErrorMessage(this.getText("modifyfailure"));
			logger.error(ex);
		}
		return SUCCESS;
	}

	/**
	 * 对配置组织机构类型是属于组织机构还是部门。进行存储和修改操作
	 */
	@Action(value = "doModifySave", results = { @Result(name = "success", location = "/webpage/pub/beanresult.jsp") })
	public String doModifySave() {
		try {
			orgcfgService.update(bean, getActiontype());
		} catch (ValidException ee) {
			bean.setErrorMessage(ee);
			logger.error(ee);
		} catch (Exception ex) {
			bean.setErrorMessage("failed");
			logger.error(ex);
		}
		this.setJsonStr(JSONUtil.toJSONString(bean));
		return SUCCESS;
	}

	// 日志文件用
	@Override
	public String getBiztypename() {
		return "配置组织机构类型是属于组织机构还是部门。信息管理";
	}

	public OrgcfgEntity getBean() {
		return bean;
	}

	public void setBean(OrgcfgEntity bean) {
		this.bean = bean;
	}

	public OrgcfgService<OrgcfgEntity> getOrgcfgService() {
		return orgcfgService;
	}

	public void setOrgcfgService(OrgcfgService<OrgcfgEntity> orgcfgService) {
		this.orgcfgService = orgcfgService;
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
