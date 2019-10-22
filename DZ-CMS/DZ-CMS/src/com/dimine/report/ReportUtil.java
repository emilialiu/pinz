package com.dimine.report;

import java.io.File;
import java.sql.Connection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRRuntimeException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

public class ReportUtil {
	public static String REPORT_DATASOURCE = "java:comp/env/jdbc/reportdb";

	public static String REPORT_PRINT = "REPORTPRINT";

	public static String REPORT_TITLE = "REPORTTITLE";

	public static String REPORT_NAME = "REPORTNAME";

	public static String REPORT_ROOTPATH = "webpage/report/jasper/";

	public static int DATA_AMOUNT_PER_PAGE = 10;

	public static int PICTURE_WIDTH = 600;

	public static int PICTURE_HEIGHT = 475;
	public static String report_jasperprint;

	public static boolean createReport(HttpServletRequest request,
			String reportName) throws Exception {
		return createReport(request, reportName, new JREmptyDataSource());
	}

	public static boolean createReport(HttpServletRequest request,
			String reportName, JRDataSource ds) throws Exception {
		Map parameters = new HashMap(10);
		Enumeration enu = request.getParameterNames();

		while (enu.hasMoreElements()) {
			String key = (String) enu.nextElement();
			parameters.put(key, request.getParameter(key).toUpperCase()
					.replaceAll("'", "''"));
		}
		return createReport(request, reportName, ds, parameters);
	}

	public static boolean createReport(HttpServletRequest request,
			String reportName, JRDataSource ds, Map parameters)
			throws Exception {
		return createReport(request, reportName, ds, parameters, "");
	}

	public static boolean createReport(HttpServletRequest request,
			String reportName, JRDataSource ds, Map parameters,
			String reporttitle) throws Exception {
		boolean createflag = true;
		File reportFile = new File(request.getRealPath(REPORT_ROOTPATH
				+ reportName + ".jasper"));
		if (!reportFile.exists()) {
			File reportSourceFile = new File(
					request.getRealPath(REPORT_ROOTPATH + reportName + ".jrxml"));
			if (reportFile.exists()) {
				JasperCompileManager.compileReportToFile(reportSourceFile
						.getPath());
				reportFile = new File(request.getRealPath(REPORT_ROOTPATH
						+ reportName + ".jasper"));
			} else {
				System.out.print("没有报表文件与报表源文件");

				throw new JRRuntimeException("没有报表文件与报表源文件.");
			}
		}

		JasperReport jasperReport = (JasperReport) JRLoader
				.loadObject(reportFile.getPath());

		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,
				parameters, ds);
		request.getSession().setAttribute(REPORT_PRINT, jasperPrint);
		request.getSession().setAttribute(REPORT_TITLE, reporttitle);
		return createflag;
	}

	public static boolean createDBReport(HttpServletRequest request,
			String reportName, Connection conn) throws Exception {
		System.out.print("createdbreport start");
		report_jasperprint = reportName;
		Map parameters = new HashMap(10);
		Enumeration enu = request.getParameterNames();

		while (enu.hasMoreElements()) {
			String key = (String) enu.nextElement();

//			parameters.put(key, request.getParameter(key).toUpperCase());
			parameters.put(key, request.getParameter(key));
		}
		return createDBReport(request, reportName, conn, parameters);
	}

	public static boolean createDBReport(HttpServletRequest request,
			String reportName, Connection conn, Map parameters)
			throws Exception {
		return createDBReport(request, reportName, conn, parameters, "");
	}

	public static boolean createDBReport(HttpServletRequest request,
			String reportName, Connection conn, Map parameters,
			String reporttitle) throws Exception {
		boolean createflag = true;
		File reportFile = new File(request.getRealPath(REPORT_ROOTPATH
				+ reportName + ".jasper"));
		if (!reportFile.exists()) {
			File reportSourceFile = new File(
					request.getRealPath(REPORT_ROOTPATH + reportName + ".jrxml"));
			if (reportFile.exists()) {
				JasperCompileManager.compileReportToFile(reportSourceFile
						.getPath());
				reportFile = new File(request.getRealPath(REPORT_ROOTPATH
						+ reportName + ".jasper"));
			} else {
				System.out.print("没有报表文件与报表源文件");

				throw new JRRuntimeException("没有报表文件与报表源文件.");
			}
		}

		JasperReport jasperReport = (JasperReport) JRLoader
				.loadObject(reportFile.getPath());
		JRExporterParameter a = JRExporterParameter.PAGE_INDEX;
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,
				parameters, conn);
		request.getSession().setAttribute(report_jasperprint, jasperPrint);
		request.getSession().setAttribute(REPORT_TITLE, reporttitle);
		request.getSession().setAttribute(REPORT_NAME, reportName);
		return createflag;
	}
}