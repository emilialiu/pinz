package com.dimine.report;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.j2ee.servlets.BaseHttpServlet;

public class RtfServlet extends BaseHttpServlet {
	private static final long serialVersionUID = 3912929329725398581L;

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

		Boolean isBuffered = Boolean.valueOf(request.getParameter("buffered"));
		JasperPrint jasperPrint = (JasperPrint) request.getSession()
				.getAttribute(reportClass);
		if (jasperPrint == null) {
			return;
		}
		if (isBuffered.booleanValue()) {
			JRRtfExporter exporter = new JRRtfExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);
			try {
				exporter.exportReport();
			} catch (JRException e) {
				throw new ServletException(e);
			}

			byte[] bytes = baos.toByteArray();

			if ((bytes != null) && (bytes.length > 0)) {
				response.setContentType("application/msword");
				response.setHeader("Content-Disposition",
						"attachment; filename=\"" + fileName + ".rtf\"");
				response.setContentLength(bytes.length);
				OutputStream ouputStream = response.getOutputStream();
				try {
					ouputStream.write(bytes, 0, bytes.length);
					ouputStream.flush();
				} finally {
					if (ouputStream != null) {
						try {
							ouputStream.close();
						} catch (IOException localIOException) {
						}
					}
				}
				try {
					ouputStream.close();
				} catch (IOException localIOException1) {
				}

			}

		} else {
			response.setContentType("application/msword");
			response.setHeader("Content-Disposition", "attachment; filename=\""
					+ fileName + ".rtf\"");

			JRRtfExporter exporter = new JRRtfExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);

			OutputStream ouputStream = response.getOutputStream();
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,
					ouputStream);
			try {
				exporter.exportReport();
			} catch (JRException e) {
				throw new ServletException(e);
			} finally {
				if (ouputStream != null) {
					try {
						ouputStream.close();
					} catch (IOException localIOException4) {
					}
				}
			}
		}
	}
}