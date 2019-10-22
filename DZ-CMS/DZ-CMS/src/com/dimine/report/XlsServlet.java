package com.dimine.report;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.j2ee.servlets.BaseHttpServlet;

public class XlsServlet extends BaseHttpServlet {
	private static final long serialVersionUID = -3702154265607404257L;

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String reportClass = request.getParameter("reportclass");
		if (reportClass == null) {
			reportClass = "reportfile";
		}

		String reportTitle = request.getParameter("reporttitle");
		if (reportTitle == null) {
			reportTitle = reportClass;
		}
		String fileName = reportTitle;

		fileName = new String(reportTitle.getBytes("GB2312"), "ISO_8859_1");

		JasperPrint jasperPrint = (JasperPrint) request.getSession()
				.getAttribute(reportClass);
		response.setContentType("application/vnd.ms-Excel");
		response.setHeader("Content-Disposition", "attachment;filename=\""
				+ fileName + ".xls\"");

		OutputStream ouputStream = response.getOutputStream();
		try {
			JRXlsExporter exporter = new JRXlsExporter();

			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,
					ouputStream);
			exporter.setParameter(
					JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
					Boolean.TRUE);
			exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,
					Boolean.FALSE);
			exporter.setParameter(
					JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,
					Boolean.FALSE);
			exporter.exportReport();
		} catch (JRException e) {
			throw new ServletException(e);
		} finally {
			if (ouputStream != null) {
				try {
					ouputStream.close();
				} catch (IOException localIOException) {
				}
			}
		}
	}
}