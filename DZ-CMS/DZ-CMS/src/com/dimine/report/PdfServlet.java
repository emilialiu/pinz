package com.dimine.report;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import net.sf.jasperreports.j2ee.servlets.BaseHttpServlet;

public class PdfServlet extends BaseHttpServlet {
	private static final long serialVersionUID = -3702154265607404257L;

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String reportClass = request.getParameter("reportclass");
		if (reportClass == null) {
			reportClass = "reportfile";
		}
		System.out.println("reportclass=:" + reportClass);

		String reportTitle = request.getParameter("reporttitle");
		if (reportTitle == null) {
			reportTitle = reportClass;
		}
		String fileName = reportTitle;

		fileName = new String(reportTitle.getBytes("GB2312"), "ISO_8859_1");

		System.out.println("reporttitle=:" + reportTitle);

		JasperPrint jasperPrint = (JasperPrint) request.getSession()
				.getAttribute(reportClass);
		if (jasperPrint == null) {
			return;
		}

		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "attachment; filename=\""
				+ fileName + ".pdf\"");

		OutputStream ouputStream = response.getOutputStream();
		try {
			JRPdfExporter exporter = new JRPdfExporter();

			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,
					ouputStream);

			exporter.setParameter(JRPdfExporterParameter.PERMISSIONS,
					new Integer(2068));

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