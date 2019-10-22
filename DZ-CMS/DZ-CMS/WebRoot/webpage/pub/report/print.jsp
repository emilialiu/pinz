<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>报表打印/导出预览</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<script>
			var url = "../report/JRPrintServlet";
			document.write('<APPLET ID="JrPrt" CODE = "JRViewApplet.class" CODEBASE = "../../../applets" ARCHIVE = "jasperreports-applet-5.2.0.jar,commons-logging-1.1.1.jar,commons-collections-3.2.1.jar" WIDTH = "0" HEIGHT = "0">'); 
			document.write('<PARAM NAME = "type" VALUE="application/x-java-applet;version=1.2.2">'); 
			document.write('<PARAM NAME = "scriptable" VALUE="false">');
			document.write('<PARAM NAME = "REPORT_URL" VALUE ="'+url+'">');
			document.write('</APPLET>');
		</script>
	</head>
	<body>
	</body>
</html>
