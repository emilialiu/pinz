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
import com.dimine.sys.entity.LogLoginEntity;
import com.dimine.sys.service.ExportExcelService;
import com.dimine.sys.service.LogLoginService;

/***
 * 对登陆日志进行操作的action
 * 
 * @author Administrator
 * 
 */
@Namespace("/manager/sys/llog")
@Scope("request")
@Controller("sysLogLoginAction")
public class LogLoginAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LogLoginEntity bean = new LogLoginEntity();
	private String id_arrays;
	@Resource
	private LogLoginService<LogLoginEntity> logLoginService;
	@Resource
	private ExportExcelService<LogLoginEntity> exportExcelService;
	private String fileName;

	private String addtype;

	private String somename;

	private String encode;

	private String param;

	private String filters;

	/**
	 * 对日志进行列表查询操作
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
								if (f.equals("deptname"))
									return "de.deptname";
								if (f.equals("login_name"))
									return "log.login_name";
								if (f.equals("login_time"))
									return SQLTool.dateformat("log.login_time",
											"%Y-%m-%d %H:%i:%s");
								if (f.equals("login_ip"))
									return "log.login_ip";
								if (f.equals("login_message"))
									return "log.login_message";
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
				System.out.println("Input Message:" + query);
				bean.setQuery(query);
			}
			bean.setPager(this.getPager());
			List<LogLoginEntity> dataList = logLoginService.selectByList(bean);
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
	 * 日志删除
	 * 
	 * @return
	 */
	@Action(value = "delete", results = { @Result(name = "success", location = "/webpage/pub/result.jsp") })
	public String dodelete() {
		try {
			logLoginService.deleteobjs(id_arrays);
		} catch (Exception ee) {
			setErrorMessage(ee);
		}
		return "success";

	}

	/**
	 * 删除所有的记录
	 * 
	 * @return
	 */
	@Action(value = "deleteall", results = { @Result(name = "success", location = "/webpage/pub/result.jsp") })
	public String doDeleteAll() {
		try {
			logLoginService.deleteall();
		} catch (Exception ee) {
			setErrorMessage(ee);
		}
		return "success";
	}

	@Action(value = "exportloginexcl")
	public String exportExcel() {
		try {
			exportExcelService.exportLoginExcel(bean);
			fileName = (new StringBuilder("登陆日志信息("))
					.append((new SimpleDateFormat("yyyy-MM-dd"))
							.format(new Date())).append(").txt").toString();
		} catch (Exception ee) {
			setErrorMessage(ee);
		}
		return "none";
	}

	public LogLoginEntity getBean() {
		return bean;
	}

	public void setBean(LogLoginEntity bean) {
		this.bean = bean;
	}

	public LogLoginService<LogLoginEntity> getLogLoginService() {
		return logLoginService;
	}

	public void setLogLoginService(
			LogLoginService<LogLoginEntity> logLoginService) {
		this.logLoginService = logLoginService;
	}

	public String getId_arrays() {
		return id_arrays;
	}

	public void setId_arrays(String id_arrays) {
		this.id_arrays = id_arrays;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public ExportExcelService<LogLoginEntity> getExportExcelService() {
		return exportExcelService;
	}

	public void setExportExcelService(
			ExportExcelService<LogLoginEntity> exportExcelService) {
		this.exportExcelService = exportExcelService;
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
