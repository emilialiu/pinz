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
import com.dimine.pub.entity.AlarmEntity;
import com.dimine.pub.service.AlarmService;

/***
 * 告警信息action
 * 
 * @author LCF
 * 
 */
@Namespace("/manager/sys/alarm")
@Scope("request")
@Controller("alarmAction")
public class AlarmAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	// 业务逻辑处理
	@Resource
	private AlarmService<AlarmEntity> alarmService = null;

	// 实例bean
	private AlarmEntity bean = new AlarmEntity();
	
	private String filters;
	
	/**
	 * 对告警进行列表查询操作
	 * 
	 * @return
	 */
	@Action(value = "list", results = { @Result(name = "success", location = "/webpage/pub/grid/pager.jsp") })
	public String list() {
		try {
			if(filters!=null){String query = new SQLTool().constructWhere(filters, new SQLCallback(){
				@Override
				public String executeField(String f) {
					if(f.equals("handletype"))return "d2.PARAMNAME"; 
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
			System.out.println(query);
			bean.setQuery(query);
			}
			bean.setPager(this.getPager());
			bean.setUserid(this.getLoginUser().getUserid());
			List<AlarmEntity> dataList = alarmService.selectByList(bean);
			// 设置页面数据
			Map<String, Object> jsonMap = new HashMap<String, Object>();
			jsonMap.put("page", bean.getPager().getPageId());
			jsonMap.put("total", bean.getPager().getPageCount());
			jsonMap.put("records", bean.getPager().getRowCount());
			jsonMap.put("rows", dataList);
			//System.out.println(JSONUtil.toJSONString(jsonMap));
			this.setJsonStr(JSONUtil.toJSONString(jsonMap));
		} catch (Exception e) {
			this.setErrorMessage(e);
		}
		return SUCCESS;
	}

	@Action(value = "getAlarmListByUserid", results = { @Result(name = "success", location = "/webpage/pub/jsondata.jsp") })
	public String getAlarmListByUserid() {
		List<AlarmEntity> alarmList = alarmService
				.getAlarmListByUserid(getLoginUser().getUserid());
		StringBuffer sbf = new StringBuffer();
		sbf.append("({\"count\":\"").append(alarmList.size());
		sbf.append("\",\"list\":[");

		for (int i = 0; i < alarmList.size(); i++) {
			AlarmEntity alarm = alarmList.get(i);
			sbf.append("{\"id\":\"").append(alarm.getAlarmid())
					.append("\",\"title\":\"").append(alarm.getAlarmtitle())
					.append("\",\"alarmlevel\":\"").append(alarm.getAlarmlevel())
					.append("\"}");

			if (i != alarmList.size() - 1) {
				sbf.append(",");
			}
		}
		sbf.append("]})");
		this.getRequest().setAttribute("jsonStr", sbf.toString());
		return SUCCESS;
	}

	public AlarmService<AlarmEntity> getAlarmService() {
		return alarmService;
	}

	public void setAlarmService(AlarmService<AlarmEntity> alarmService) {
		this.alarmService = alarmService;
	}

	public AlarmEntity getBean() {
		return bean;
	}

	public void setBean(AlarmEntity bean) {
		this.bean = bean;
	}

	public String getFilters() {
		return filters;
	}

	public void setFilters(String filters) {
		this.filters = filters;
	}

}
