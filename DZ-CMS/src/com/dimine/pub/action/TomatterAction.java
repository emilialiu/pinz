package com.dimine.pub.action;

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
import com.dimine.base.util.SQLCallback;
import com.dimine.base.util.SQLTool;
import com.dimine.base.util.json.JSONUtil;
import com.dimine.pub.entity.TomatterEntity;
import com.dimine.pub.service.TomatterService;

/***
 * 待办信息action
 * 
 * @author LCF
 * 
 */
@Namespace("/pub/tomatter")
@Scope("request")
@Controller("tomatterAction")
public class TomatterAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	// 业务逻辑处理
	@Resource
	private TomatterService<TomatterEntity> tomatterService = null;

	// 实例bean
	private TomatterEntity bean = new TomatterEntity();
	
	private String filters;

	/**
	 * 对待办进行列表查询操作
	 * 
	 * @return
	 */
	@Action(value = "list", results = { @Result(name = "success", location = "/webpage/pub/grid/pager.jsp") })
	public String list() {
		try {
			System.out.println(filters);
			if(filters!=null){
				String query = new SQLTool().constructWhere(filters, new SQLCallback(){
					@Override
					public String executeField(String f) {
						if(f.equals("mattertype"))return "d.paramname"; 
						if(f.equals("handlename"))return "u.username"; 
						return f;
					} 
					@Override
					public String executeData(String f, String o, String d) {
						if(o.equals("bw")||o.equals("en"))
							return(new StringBuilder("'")).append(d).append("%'").toString();
						if(o.equals("ew")||o.equals("en"))
							return(new StringBuilder("'%")).append(d).append("'").toString();
						if(o.equals("cn")||o.equals("nc"))
							return(new StringBuilder("'%")).append(d).append("%'").toString();
						else
							return(new StringBuilder("'")).append(d).append("'").toString();
					}	
				});
				bean.setQuery(query);
			}
			bean.setPager(this.getPager());
			List<TomatterEntity> dataList = tomatterService.selectByList(bean);
			// 设置页面数据
			Map<String, Object> jsonMap = new HashMap<String, Object>();
			jsonMap.put("page", bean.getPager().getPageId());
			jsonMap.put("total", bean.getPager().getPageCount());
			jsonMap.put("records", bean.getPager().getRowCount());
			jsonMap.put("rows", dataList);
			System.out.println(JSONUtil.toJSONString(jsonMap));
			this.setJsonStr(JSONUtil.toJSONString(jsonMap));
		} catch (Exception e) {
			this.setErrorMessage(e);
		}
		return SUCCESS;
	}

	@Action(value = "getTomatterListByUserid", results = { @Result(name = "success", location = "/webpage/pub/jsondata.jsp") })
	public String getAlarmListByUserid() {
		List<TomatterEntity> tomatterList = tomatterService
				.getTomatterListByUserid(getLoginUser().getUserid());
		StringBuffer sbf = new StringBuffer();
		sbf.append("({\"count\":\"").append(tomatterList.size());
		sbf.append("\",\"list\":[");

		for (int i = 0; i < tomatterList.size(); i++) {
			TomatterEntity alarm = tomatterList.get(i);
			sbf.append("{\"id\":\"").append(alarm.getMatterid())
					.append("\",\"title\":\"").append(alarm.getMattername())
					.append("\"}");

			if (i != tomatterList.size() - 1) {
				sbf.append(",");
			}
		}
		sbf.append("]})");
		this.getRequest().setAttribute("jsonStr", sbf.toString());
		return SUCCESS;
	}

	public TomatterService<TomatterEntity> getTomatterService() {
		return tomatterService;
	}

	public void setTomatterService(
			TomatterService<TomatterEntity> tomatterService) {
		this.tomatterService = tomatterService;
	}

	public TomatterEntity getBean() {
		return bean;
	}

	public void setBean(TomatterEntity bean) {
		this.bean = bean;
	}

	public String getFilters() {
		return filters;
	}

	public void setFilters(String filters) {
		this.filters = filters;
	}

}
