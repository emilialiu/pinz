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
import com.dimine.base.util.json.JSONUtil;
import com.dimine.sm.entity.TeamEntity;
import com.dimine.sm.service.TeamService;

/**
 * 用于对班组进行系列的操作的action
 * 
 * @author dimine 2015-06-16 17:53:00
 * 
 */
@Namespace("/biz/sm/team")
@Scope("request")
@Controller("teamAction")
public class TeamAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private final static Logger logger = Logger.getLogger(TeamAction.class);
	// 业务处理
	@Resource
	private TeamService<TeamEntity> teamService;

	// 参数实体
	private TeamEntity bean = new TeamEntity();
	/**
	 * 添加类型 addmore为新增保存
	 */
	private String addtype;

	private String somename;

	private String encode;

	private String param;

	private String filters;

	/**
	 * 对班组进行列表查询操作
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
								if (f.equals("teamtypename"))
									return "d.paramname";
								if (f.equals("deptname"))
									return "dept.deptname";
								if (f.equals("dutymanname"))
									return "s.staffname";
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
				bean.setQuery(query);
			}
			if (this.getSort() == null || "".equals(this.getSort())) {
				this.setSort("TeamCode");
			}
			bean.setPager(this.getPager());
			List<TeamEntity> dataList = teamService.selectByList(bean);
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
	@Action(value = "doAdd", results = { @Result(name = "success", location = "/webpage/biz/sm/team/info.jsp") })
	public String doAdd() {
		// TODO 其他业务处理
		bean.setTdeptid(getLoginUser().getOrgid());
		this.setActiontype(ACTIONTYPE_ADDSAVE);
		return SUCCESS;
	}

	/**
	 * 新增班组信息保存 <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@Action(value = "doAddSave", results = { @Result(name = "success", location = "/webpage/pub/beanresult.jsp") })
	public String doAddSave() {
		try {
			bean.setCreateid(this.getLoginUser().getUserid());
			teamService.insert(bean);
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
	 * 删除班组信息 <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@Action(value = "delete", results = { @Result(name = "success", location = "/webpage/pub/beanresult.jsp") })
	public String doDelete() {
		try {
			teamService.deleteTeam(bean.getTeamid());
		} catch (Exception ex) {
			bean.setErrorMessage("failed");
			logger.error(ex);
		}
		this.setJsonStr(JSONUtil.toJSONString(bean));
		return SUCCESS;
	}

	/**
	 * 对班组进行修改查询操作
	 */
	@Action(value = "doModify", results = { @Result(name = "success", location = "/webpage/biz/sm/team/info.jsp") })
	public String doModify() {
		try {
			if (getActiontype().equals(ACTIONTYPE_MODIFY)) {
				this.setActiontype(ACTIONTYPE_MODIFYSAVE);
			} else if (getActiontype().equals(ACTIONTYPE_VIEW)) {
				this.setActiontype(ACTIONTYPE_VIEW);
			} else if (getActiontype().equals(ACTIONTYPE_DELETE)) {
				this.setActiontype(ACTIONTYPE_DELETESAVE);
			}
			bean = teamService.selectById(bean);
		} catch (Exception ex) {
			this.setErrorMessage(this.getText("modifyfailure"));
			logger.error(ex);
		}
		return SUCCESS;
	}

	/**
	 * 对班组进行存储和修改操作
	 */
	@Action(value = "doModifySave", results = { @Result(name = "success", location = "/webpage/pub/beanresult.jsp") })
	public String doModifySave() {
		try {
			bean.setModifyid(this.getLoginUser().getUserid());
			teamService.update(bean, getActiontype());
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
	 * 根据项目部id及业务类型取得班组类型列表
	 */
	@Action(value = "getTeamSelect", results = { @Result(name = "success", location = "/webpage/pub/jsondata.jsp") })
	public String getTeamSelect() {
		try {
			List<TeamEntity> list = teamService.selectTeamTypeList(bean);
			if (list != null && list.size() > 0) {
				StringBuffer sbf = new StringBuffer();
				for (TeamEntity data : list) {
					sbf.append("'").append(data.getTeamtype()).append("':'")
							.append(data.getTeamtypename()).append("',");
				}
				String _jsonData = sbf.toString();
				int length = _jsonData.length();
				this.setJsonStr(_jsonData.substring(0, length - 1));
			} else {
				this.setJsonStr("");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 根据项目部id取得班组
	 * 
	 * @return
	 */
	@Action(value = "getTeams", results = { @Result(name = "success", location = "/webpage/pub/jsondatas.jsp") })
	public String getTeams() {
		StringBuffer sbf = new StringBuffer();
		try {
			List<TeamEntity> list = teamService.selectByTdeptId(bean);
			int i = 0;
			for (TeamEntity team : list) {
				sbf.append("{'teamid':'").append(team.getTeamid())
						.append("','teamname':'").append(team.getTeamname())
						.append("'}");
				if (i != list.size() - 1) {
					sbf.append(",");
				}
				i++;
			}
		} catch (Exception ex) {
			bean.setErrorMessage("failed");
			logger.error(ex);
		}
		getRequest().setAttribute("jsonString", sbf.toString());
		return SUCCESS;
	}

	// 日志文件用
	@Override
	public String getBiztypename() {
		return "班组信息管理";
	}

	public TeamEntity getBean() {
		return bean;
	}

	public void setBean(TeamEntity bean) {
		this.bean = bean;
	}

	public TeamService<TeamEntity> getTeamService() {
		return teamService;
	}

	public void setTeamService(TeamService<TeamEntity> teamService) {
		this.teamService = teamService;
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
