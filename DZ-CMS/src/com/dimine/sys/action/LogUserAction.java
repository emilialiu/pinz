package com.dimine.sys.action;

import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.dimine.sys.entity.LogUserEntity;
import com.dimine.sys.service.ExportExcelService;
import com.dimine.sys.service.LogUserService;

@Namespace("/manager/sys/ulog")
@Scope("request")
@Controller("sysLogUserAction")
/**
 * 对日志进行操作action
 */
public class LogUserAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource
	private LogUserService<LogUserEntity> logUserService;
	@Resource
	private ExportExcelService<LogUserEntity> exportExcelService;
	private LogUserEntity bean = new LogUserEntity();
	private String id_arrays;
	private String filename;
	private String username;

	private String fileName;

	private String addtype;

	private String somename;

	private String encode;

	private String param;

	private String filters;

	@Action(value = "list", results = { @Result(name = "success", location = "/webpage/pub/grid/pager.jsp") })
	public String doList() {
		try {
			if (filters != null) {
				String query = new SQLTool().constructWhere(filters,
						new SQLCallback() {
							@Override
							public String executeField(String f) {
								if (f.equals("deptname"))
									return "de.deptname";
								if (f.equals("username"))
									return "u.username";
								if (f.equals("use_ip"))
									return "log.use_ip";
								if (f.equals("use_module"))
									return "log.use_module";
								if (f.equals("use_time"))
									return SQLTool.dateformat("log.use_time",
											"%Y-%m-%d %H:%i:%s");
								if (f.equals("use_operation"))
									return "log.use_operation";
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
			bean.setPager(this.getPager());
			List<LogUserEntity> dataList = logUserService.selectByList(bean);
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

	@Action(value = "delete", results = { @Result(name = "success", location = "/webpage/pub/result.jsp") })
	public String doDelInfo() {
		try {
			logUserService.deltebatch(bean.getLogid());
		} catch (Exception ee) {
			setErrorMessage(ee);
		}
		return "success";
	}

	/**
	 * 修改查询操作
	 */
	@Action(value = "doModify", results = { @Result(name = "success", location = "/webpage/pub/jsondata.jsp") })
	public String doModify() {
		try {
			bean = logUserService.selectById(bean.getLogid());
			this.setJsonStr("[" + JSONUtil.toJSONString(bean) + "]");
			this.setActiontype("modifysave");
		} catch (Exception e) {
			this.setErrorMessage(this.getText("modifyfailure"));
		}
		return SUCCESS;
	}

	/**
	 * 删除所有的日志记录
	 * 
	 * @return
	 */
	@Action(value = "deleteall", results = { @Result(name = "success", location = "/webpage/pub/result.jsp") })
	public String deleteall() {
		try {
			logUserService.deleteall();
		} catch (Exception ee) {
			setErrorMessage(ee);
			ee.printStackTrace();
		}
		return "success";
	}

	@Action(value = "exportuserexcl")
	public String exportExcel() {
		try {
			exportExcelService.exportuseExcel(bean.getUserip(),
					bean.getStartTime(), bean.getEndTime(), username);
			filename = (new StringBuilder("系统日志信息("))
					.append((new SimpleDateFormat("yyyy-MM-dd"))
							.format(new Date())).append(").txt").toString();
		} catch (Exception ee) {
			setErrorMessage(ee);
		}
		return "none";
	}

	public LogUserService<LogUserEntity> getLogUserService() {
		return logUserService;
	}

	public void setLogUserService(LogUserService<LogUserEntity> logUserService) {
		this.logUserService = logUserService;
	}

	public LogUserEntity getBean() {
		return bean;
	}

	public void setBean(LogUserEntity bean) {
		this.bean = bean;
	}

	public String getId_arrays() {
		return id_arrays;
	}

	public void setId_arrays(String id_arrays) {
		this.id_arrays = id_arrays;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public ExportExcelService<LogUserEntity> getExportExcelService() {
		return exportExcelService;
	}

	public void setExportExcelService(
			ExportExcelService<LogUserEntity> exportExcelService) {
		this.exportExcelService = exportExcelService;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
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
