package com.dimine.sm.action;

import java.util.ArrayList;
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
import com.dimine.base.util.SQLCallbackImpl;
import com.dimine.base.util.SQLTool;
import com.dimine.base.util.json.JSONUtil;
import com.dimine.sm.entity.TeamEntity;
import com.dimine.sm.entity.TeamstaffEntity;
import com.dimine.sm.service.TeamstaffService;

/**
 * 用于对班组对应人员进行系列的操作的action
 * 
 * @author dimine 2015-06-16 17:53:39
 * 
 */
@Namespace("/biz/sm/teamstaff")
@Scope("request")
@Controller("teamstaffAction")
public class TeamstaffAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private final static Logger logger = Logger
			.getLogger(TeamstaffAction.class);
	// 业务处理
	@Resource
	private TeamstaffService<TeamstaffEntity> teamstaffService;

	// 参数实体
	private TeamstaffEntity bean = new TeamstaffEntity();
	/**
	 * 添加类型 addmore为新增保存
	 */
	private String addtype;

	private String somename;

	private String encode;

	private String param;

	private String filters;
	private String staffids;// 员工id

	/**
	 * 对班组对应人员进行列表查询操作
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
			List<TeamstaffEntity> dataList = teamstaffService
					.selectByList(bean);
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

	@Action(value = "doAdd", results = { @Result(name = "success", location = "/webpage/pub/result.jsp") })
	public String doAdd() {
		try {
			String sids[] = staffids.split(",");
			TeamstaffEntity tempBean = null;
			List<TeamstaffEntity> list = new ArrayList<TeamstaffEntity>();
			for (int i = 0; i < sids.length; i++) {
				tempBean = new TeamstaffEntity();
				tempBean.setTeamid(bean.getTeamid());
				tempBean.setStaffid(sids[i]);
				list.add(tempBean);
			}
			teamstaffService.batchInsert(list);
		} catch (Exception ee) {
			this.setErrorMessage(ee);
		}
		return SUCCESS;
	}

	/**
	 * 执行撤销的操作
	 * 
	 * @return
	 */
	@Action(value = "doDel", results = { @Result(name = "success", location = "/webpage/pub/result.jsp") })
	public String doDel() {
		try {
			teamstaffService.delete(staffids.split(","), bean);
		} catch (Exception ee) {
			this.setErrorMessage(ee);
		}
		return SUCCESS;
	}

	/**
	 * 用户组列表显示 已添加的
	 * 
	 * @return
	 */
	@Action(value = "doList", results = { @Result(name = "success", location = "/webpage/pub/grid/pager.jsp") })
	public String doList() {
		try {
			if (filters != null) {
				String query = new SQLTool().constructWhere(filters,
						new SQLCallbackImpl());
				bean.setQuery(query);
			}

			bean.setPager(this.getPager());
			List<TeamstaffEntity> dataList = teamstaffService
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
	 * 用户组列表显示 未添加的
	 * 
	 * @return
	 */
	@Action(value = "doUnList", results = { @Result(name = "success", location = "/webpage/pub/grid/pager.jsp") })
	public String doUnList() {
		try {
			if (filters != null) {
				String query = new SQLTool().constructWhere(filters,
						new SQLCallbackImpl());
				bean.setQuery(query);
			}

			bean.setPager(this.getPager());
			List<TeamstaffEntity> dataList = teamstaffService
					.selectUnList(bean);
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
	 * 根据当前用户id查询该用户所在班组信息
	 * @return
	 */
	@Action(value = "getTeamInfoByUserid", results = { @Result(name = "success", location = "/webpage/pub/beanresult.jsp") })
	public String getTeamInfoByUserid() {
		try {
			TeamEntity team = teamstaffService.getTeamInfoByUserid(getLoginUser().getUserid());
			this.setJsonStr(JSONUtil.toJSONString(team));
		} catch (Exception ee) {
			this.setErrorMessage(ee);
		}
		return SUCCESS;
	}

	// 日志文件用
	@Override
	public String getBiztypename() {
		return "班组对应人员信息管理";
	}

	public TeamstaffEntity getBean() {
		return bean;
	}

	public void setBean(TeamstaffEntity bean) {
		this.bean = bean;
	}

	public TeamstaffService<TeamstaffEntity> getTeamstaffService() {
		return teamstaffService;
	}

	public void setTeamstaffService(
			TeamstaffService<TeamstaffEntity> teamstaffService) {
		this.teamstaffService = teamstaffService;
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

	public String getStaffids() {
		return staffids;
	}

	public void setStaffids(String staffids) {
		this.staffids = staffids;
	}

}
