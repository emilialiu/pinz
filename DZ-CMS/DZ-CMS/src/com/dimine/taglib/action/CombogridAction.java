package com.dimine.taglib.action;

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
import com.dimine.base.util.json.JSONUtil;
import com.dimine.taglib.entity.CombogridEntity;
import com.dimine.taglib.service.CombogridService;

@Namespace("/dm/combogrid")
@Scope("prototype")
@Controller("combogridAction")
public class CombogridAction extends BaseAction {

	/**
	 * 下拉列表
	 */
	private static final long serialVersionUID = 5529869786240223133L;

	@Resource
	private CombogridService<CombogridEntity> combogridService;

	private CombogridEntity bean = new CombogridEntity();
	
	@Action(value = "gridlist", results = { @Result(name = "success", location = "/pub/grid/pager.jsp") })
	public String list(){
		try {
			bean.setPager(this.getPager());
			List<CombogridEntity> dataList = combogridService.selectByList(bean);
			// 设置页面数据
			Map<String, Object> jsonMap = new HashMap<String, Object>();
			jsonMap.put("total", bean.getPager().getRowCount());
			jsonMap.put("rows", dataList);
			this.setJsonStr(JSONUtil.toJSONString(jsonMap));
		} catch (Exception e) {
			this.setErrorMessage(e);
		}
		return SUCCESS;
	}
	
	public CombogridService<CombogridEntity> getCombogridService() {
		return combogridService;
	}

	public void setCombogridService(
			CombogridService<CombogridEntity> combogridService) {
		this.combogridService = combogridService;
	}

	public CombogridEntity getBean() {
		return bean;
	}

	public void setBean(CombogridEntity bean) {
		this.bean = bean;
	}
	
}
