package com.dimine.report;

import java.sql.Connection;

import javax.sql.DataSource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.dimine.base.action.BaseAction;

@Namespace("/pub/report")
@Scope("request")
@Controller("reportAction")
public class ReportAction extends BaseAction {
	private DataSource dataSource = null;

	public String getBiztypename() {
		return null;
	}

	@Action(value = "doReport", results = { @Result(name = "success", location = "/webpage/pub/report/GeneralHTMLReportViewer.jsp") })
	public String doReport() {
		try {
			Connection conn = this.dataSource.getConnection();
			try {
				ReportUtil.createDBReport(getRequest(), getRequest()
						.getParameter("reportclass"), conn);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				ReportDB.release(conn);
			}

			ReportDB.release(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	public DataSource getDataSource() {
		return this.dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
}
