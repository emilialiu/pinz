package com.dimine.sys.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.dimine.base.action.BaseAction;
import com.dimine.base.util.SQLCallbackImpl;
import com.dimine.base.util.SQLTool;
import com.dimine.base.util.json.JSONUtil;
import com.dimine.sys.entity.DictEntity;
import com.dimine.sys.service.DictService;

/**
 * 资源属性
 * 
 * @author LCF
 */
@Namespace("/manager/sys/dict")
@Scope("request")
@Controller("dictAction")
public class DictAction extends BaseAction {

	private static final long serialVersionUID = 3525445612504421307L;

	// 逻辑处理类
	@Resource
	private DictService<DictEntity> dictService = null;

	private DictEntity bean = new DictEntity();
	private String param;

	private String filters;

	/**
	 * 资源属性列表显示
	 * 
	 * @return
	 */
	@Action(value = "dictlist", results = { @Result(name = "success", location = "/webpage/pub/grid/pager.jsp") })
	public String doList() {
		try {
			/*if (filters != null) {
				String query = "and dicttypeid='" + filters + "'";
				bean.setQuery(query);
			}*/
			if(filters!=null){
				String query = new SQLTool().constructWhere(filters, new SQLCallbackImpl() {
				});
				bean.setQuery(query);
			}
			bean.setPager(this.getPager());
			// bean.setTypeid("CBLX");
			bean.setTypeid(filters);
			List<DictEntity> dataList = dictService.selectByList(bean);
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
	@Action(value = "doAdd", results = { @Result(name = "success", location = "/webpage/manager/sys/dict/dict_info.jsp") })
	public String doAdd() {
		// TODO 其他业务处理
		this.setActiontype(ACTIONTYPE_ADDSAVE);
		return SUCCESS;
	}

	/**
	 * 添加数据
	 * 
	 * @return
	 */
	@Action(value = "adddict", results = { @Result(name = "success", location = "/webpage/pub/beanresult.jsp") })
	public String doAdds() {
		try {
			dictService.insert(bean);
		} catch (Exception e) {
			bean.setErrorMessage("failed");
			e.printStackTrace();
		}
		this.setJsonStr(JSONUtil.toJSONString(bean));
		return SUCCESS;
	}

	/**
	 * 更新数据
	 * 
	 * @return
	 */
	@Action(value = "modifydict", results = { @Result(name = "success", location = "/webpage/pub/beanresult.jsp") })
	public String doModifydict() {
		try {
			dictService.update(bean);
		} catch (Exception ee) {
			ee.printStackTrace();
			bean.setErrorMessage(ee);
		}
		this.setJsonStr(JSONUtil.toJSONString(bean));
		return SUCCESS;
	}

	/**
	 * 进行修改查询操作
	 */
	@Action(value = "doModify", results = { @Result(name = "success", location = "/webpage/manager/sys/dict/dict_info.jsp") })
	public String doModify() {
		try {
			if (getActiontype().equals(ACTIONTYPE_MODIFY)) {
				this.setActiontype(ACTIONTYPE_MODIFYSAVE);
			} else if (getActiontype().equals(ACTIONTYPE_VIEW)) {
				this.setActiontype(ACTIONTYPE_VIEW);
			} else if (getActiontype().equals(ACTIONTYPE_DELETE)) {
				this.setActiontype(ACTIONTYPE_DELETESAVE);
			}
			bean = dictService.selectById(bean.getCode());
		} catch (Exception e) {
			this.setErrorMessage(this.getText("modifyfailure"));
		}
		return SUCCESS;
	}

	/**
	 * 删除数据
	 * 
	 * @return
	 */
	@Action(value = "deletedict", results = { @Result(name = "success", location = "/webpage/pub/beanresult.jsp") })
	public String doDelete() {
		try {
			dictService.delete(bean.getCode());
		} catch (Exception ee) {
			bean.setErrorMessage(ee);
		}
		this.setJsonStr(JSONUtil.toJSONString(bean));
		return SUCCESS;
	}

	public DictEntity getBean() {
		return bean;
	}

	public void setBean(DictEntity bean) {
		this.bean = bean;
	}

	public DictService<DictEntity> getDictService() {
		return dictService;
	}

	public void setDictService(DictService<DictEntity> dictService) {
		this.dictService = dictService;
	}

	public String getType_code() {
		return bean.getTypeid();
	}

	public void setType_code(String type_code) {
		bean.setTypeid(type_code);
	}

	public void setCode(String id) {
		bean.setCode(id);
	}

	public String getCode() {
		if (bean != null && bean.getCode() != null)
			return bean.getCode();
		return "";
	}

	public String getBiztypename() {
		return "dict";
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
