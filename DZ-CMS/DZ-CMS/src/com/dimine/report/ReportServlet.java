package com.dimine.report;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.base.JRBaseReport;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.util.JRSaver;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class ReportServlet extends HttpServlet {
	private static final long serialVersionUID = -3892307586582639859L;
	private static Logger log = LogManager.getLogger(ReportServlet.class);

	public void init() throws ServletException {
		log.info("initialized successfully!");
	}

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String reportClass = request.getParameter("reportclass");

		log.debug("运行报表:" + reportClass);

		boolean isExcelFormat = false;

		if (reportClass == null) {
			throw new IllegalArgumentException("Jasper Class Unspecified");
		}

		String reportTitle = request.getParameter("reporttitle");
		if (reportTitle == null) {
			reportTitle = reportClass;
		}
		String fileName = reportTitle;

		fileName = new String(reportTitle.getBytes("GB2312"), "ISO_8859_1");

		String reportFormat = request.getParameter("reportformat");

		if (reportFormat == null) {
			reportFormat = "jasperPrint";
		}
		try {
			JasperReport myRpt = (JasperReport) JRLoader.loadObject(request
					.getRealPath(ReportUtil.REPORT_ROOTPATH + reportClass
							+ ".jasper"));

			isExcelFormat = reportFormat.equalsIgnoreCase("excel");

			if (isExcelFormat) {
				Field margin = JRBaseReport.class
						.getDeclaredField("leftMargin");
				margin.setAccessible(true);
				margin.setInt(myRpt, 0);

				margin = JRBaseReport.class.getDeclaredField("topMargin");
				margin.setAccessible(true);
				margin.setInt(myRpt, 0);

				margin = JRBaseReport.class.getDeclaredField("bottomMargin");
				margin.setAccessible(true);
				margin.setInt(myRpt, 0);

				Field pageHeight = JRBaseReport.class
						.getDeclaredField("pageHeight");
				pageHeight.setAccessible(true);
				pageHeight.setInt(myRpt, 2147483647);

				if (myRpt.getGroups() != null) {
					for (int i = 0; i < myRpt.getGroups().length; i++) {
						myRpt.getGroups()[i].setReprintHeaderOnEachPage(false);
					}
				}
			}

			Map params = new HashMap(10);
			Enumeration enu = request.getParameterNames();

			while (enu.hasMoreElements()) {
				String key = (String) enu.nextElement();
				params.put(key, request.getParameter(key).toUpperCase()
						.replaceAll("'", "''"));
				log.debug(key + "=" + request.getParameter(key));
			}

			Connection conn = ReportDB.getConnection();
			JasperPrint rptPnt = null;
			try {
				rptPnt = JasperFillManager.fillReport(myRpt, params, conn);
			} catch (Exception localException1) {
			} finally {
				ReportDB.release(conn);
			}

			OutputStream httpOut = response.getOutputStream();
			try {
				if (reportFormat.equalsIgnoreCase("print")) {
					response.setContentType("application/octet-stream");
					ServletOutputStream ouputStream = response.getOutputStream();
					ObjectOutputStream oos = new ObjectOutputStream(ouputStream);
					oos.writeObject(rptPnt);
					oos.flush();
					oos.close();
					ouputStream.flush();
					ouputStream.close();
				} else if (reportFormat.equalsIgnoreCase("jasperPrint")) {
					response.setContentType("application/octet-stream");
					JRSaver.saveObject(rptPnt, httpOut);
				} else if (reportFormat.equalsIgnoreCase("pdf")) {
					response.setContentType("application/pdf");
					response.setHeader("Content-Disposition",
							"attachment;filename=\"" + fileName + ".pdf\"");
					JRPdfExporter exporter = new JRPdfExporter();

					exporter.setParameter(JRExporterParameter.JASPER_PRINT,
							rptPnt);
					exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,
							httpOut);
					exporter.exportReport();
				} else if (reportFormat.equalsIgnoreCase("rtf")) {
					response.setContentType("application/rtf");
					response.setHeader("Content-Disposition",
							"attachment;filename=\"" + fileName + ".rtf\"");
					JRRtfExporter exporter = new JRRtfExporter();
					exporter.setParameter(JRExporterParameter.JASPER_PRINT,
							rptPnt);
					exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,
							httpOut);
					exporter.exportReport();
				} else if (reportFormat.equalsIgnoreCase("excel")) {
					response.setContentType("application/vnd.ms-Excel");
					response.setHeader("Content-Disposition",
							"attachment;filename=\"" + fileName + ".xls\"");

					JRXlsExporter exporter = new JRXlsExporter();

					exporter.setParameter(JRExporterParameter.JASPER_PRINT,
							rptPnt);
					exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,
							httpOut);

					exporter.setParameter(
							JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
							Boolean.TRUE);

					exporter.setParameter(
							JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,
							Boolean.FALSE);

					exporter.setParameter(
							JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,
							Boolean.FALSE);

					exporter.exportReport();
				} else if (reportFormat.equalsIgnoreCase("html")) {
					JRHtmlExporter exporter = new JRHtmlExporter();
					response.setContentType("text/html");

					Map imagesMap = new HashMap();

					request.getSession().setAttribute("IMAGES_MAP", imagesMap);

					exporter.setParameter(JRHtmlExporterParameter.IMAGES_MAP,
							imagesMap);
					exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI,
							request.getContextPath()
									+ "/report/ImageServlet?image=");
					exporter.setParameter(JRExporterParameter.JASPER_PRINT,
							rptPnt);
					exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,
							httpOut);

					exporter.exportReport();
				}
			} catch (JRException e) {
				e.printStackTrace();
				throw new ServletException(e);
			} finally {
				if (httpOut != null)
					try {
						httpOut.close();
					} catch (IOException localIOException) {
					}
			}
			log.debug("Report Exported");
		} catch (Exception ex) {
			log.error("Error Occured", ex);
		}
	}
}