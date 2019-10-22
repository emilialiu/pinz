<%@page language="java" pageEncoding="UTF-8"%>
<%@ page import="net.sf.jasperreports.engine.*" %>
<%@ page import="net.sf.jasperreports.engine.util.*" %>
<%@ page import="net.sf.jasperreports.engine.export.*" %>
<%@ page import="net.sf.jasperreports.j2ee.servlets.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>
<%@ page import="com.dimine.report.ReportUtil" %>

<%
request.setCharacterEncoding("UTF-8");

  String reportName = (String)session.getAttribute(ReportUtil.REPORT_NAME);
  JasperPrint jasperPrint = (JasperPrint)session.getAttribute(ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE);
//	if (request.getParameter("reload") != null || jasperPrint == null)
//	{
  String reportclass = "";
  if(request.getParameter("reportclass")==null||"".equals(request.getParameter("reportclass"))){
  	  reportclass = reportName;
  }else{
      reportclass = request.getParameter("reportclass");
  }
  jasperPrint = (JasperPrint)session.getAttribute(reportclass);
  //jasperPrint = (JasperPrint)session.getAttribute(ReportUtil.REPORT_PRINT);
  session.setAttribute(ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE,jasperPrint);
//	}
  String reporttitle = request.getParameter("reporttitle");

  JRHtmlExporter exporter = new JRHtmlExporter();
  int pageIndex = 0;
  int lastPageIndex = 0;
  if (jasperPrint.getPages() != null){
    lastPageIndex = jasperPrint.getPages().size() - 1;
  }
  String pageStr = request.getParameter("pageIndex");
  try{
    if( pageStr != null)
        pageIndex = Integer.parseInt(pageStr);
  }catch(Exception e){
    //e.printStackTrace();
  }

  if (pageIndex < 0){
    pageIndex = 0;
  }

  if (pageIndex > lastPageIndex){
    pageIndex = lastPageIndex;
  }

  StringBuffer sbuffer = new StringBuffer();

  exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
  exporter.setParameter(JRExporterParameter.OUTPUT_STRING_BUFFER, sbuffer);
  exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI, request.getContextPath()+"/report/ImageServlet?image=");
  exporter.setParameter(JRExporterParameter.PAGE_INDEX, new Integer(pageIndex));
  exporter.setParameter(JRHtmlExporterParameter.HTML_HEADER, "");
  exporter.setParameter(JRHtmlExporterParameter.BETWEEN_PAGES_HTML, "");
  exporter.setParameter(JRHtmlExporterParameter.HTML_FOOTER, "");
  try{
    exporter.exportReport();
    }catch(Exception e){
      //e.printStackTrace();
      System.err.println("无查询结果或其他错误！");
    }
%>

<html>
	<head>
		<title><%=reporttitle %></title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/assets/css/bootstrap.min.css" />
		<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/components/font-awesome/css/font-awesome.min.css" />
		<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/components/_mod/jquery-ui/jquery-ui.css" />
		<!-- text fonts -->
		<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/assets/css/ace-fonts.min.css" />
		<!-- ace styles -->
		<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/assets/css/ace.min.css" class="ace-main-stylesheet" id="main-ace-style" />
		<!--[if lte IE 9]>
		  <link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/assets/css/ace-ie.min.css" />
		<![endif]-->

		<!-- ace settings handler -->
		<script src="<%=request.getContextPath()%>/plugins/assets/js/ace-extra.min.js"></script>

		<!-- HTML5 shim and Respond.min.js IE8 support of HTML5 elements and media queries -->

		<!--[if lte IE 8]>
		<script src="<%=request.getContextPath()%>/plugins/components/html5shiv/dist/html5shiv.min.js"></script>
		<script src="<%=request.getContextPath()%>/plugins/components/respond/dest/respond.min.js"></script>
		<![endif]-->
		<style>   
		  @media screen{.onlyPrint{display:none}}
		  @media print{.onlyShow{display:none}   .onlyPrint{page-break-before:always}}
		  .widget-toolbar{float:left;}
		  .col-xs-12{padding-right:0px;}
		  p{margin:0px;}
		</style>
	</head>
	<body style="background-color: #fff">
		<div class="row" style="width:100%;overflow-x:auto;overflow-y:hidden;">
			<div class="col-xs-12">
				<div class="widget-box widget-color-blue">
					<div class="widget-header">
						<div class="widget-toolbar">
							<%--a
								href="javascript:dopdf('<%=reportclass%>','<%=reporttitle%>')"><img
									src="<%=request.getContextPath()%>/images/report/pdf.gif"
									border="0">
							</a>
							<  a
								href="javascript:dortf('<%=reportclass%>','<%=reporttitle%>')"><img
									src="<%=request.getContextPath()%>/images/report/word.gif"
									border="0">
							</a --%>
							<a
								href="javascript:doxls('<%=reportclass%>','<%=reporttitle%>')"><img
									src="<%=request.getContextPath()%>/images/report/excel.gif"
									border="0">
							</a>
							<!-- 
							<a onclick="doPrint('打印预览')"><img
									src="<%=request.getContextPath()%>/images/report/print.gif"
									border="0">
							</a> -->
							<a onclick="openView()"><img
									src="<%=request.getContextPath()%>/images/report/print.gif"
									border="0">
							</a>
						</div>
						<div class="widget-toolbar">
							<%
					          if (pageIndex > 0)
					          {
					        %>
								<a
									href="<%=request.getContextPath()%>/webpage/pub/report/GeneralHTMLReportViewer.jsp?reportclass=<%=reportclass%>&pageIndex=0"><img
										src="<%=request.getContextPath()%>/images/report/FirstPage.gif"
										border="0">
								</a>
								<a
									href="<%=request.getContextPath()%>/webpage/pub/report/GeneralHTMLReportViewer.jsp?reportclass=<%=reportclass%>&pageIndex=<%=pageIndex - 1%>"><img
										src="<%=request.getContextPath()%>/images/report/PreviousPage.gif"
										border="0">
								</a>
							<%
					          }
					          else
					          {
					        %>
								<img
									src="<%=request.getContextPath()%>/images/report/FirstPage_disabled.gif"
									border="0" />
								<img
									src="<%=request.getContextPath()%>/images/report/PreviousPage_disabled.gif"
									border="0" />
							<%
		          			  }
					          if (pageIndex < lastPageIndex)
					          {
					        %>
								<a
									href="<%=request.getContextPath()%>/webpage/pub/report/GeneralHTMLReportViewer.jsp?reportclass=<%=reportclass%>&pageIndex=<%=pageIndex + 1%>"><img
										src="<%=request.getContextPath()%>/images/report/NextPage.gif"
										border="0">
								</a>
								<a
									href="<%=request.getContextPath()%>/webpage/pub/report/GeneralHTMLReportViewer.jsp?reportclass=<%=reportclass%>&pageIndex=<%=lastPageIndex%>"><img
										src="<%=request.getContextPath()%>/images/report/LastPage.gif"
										border="0">
								</a>
							<%
				          	  }
					          else
					          {
		            		%>
								<img
									src="<%=request.getContextPath()%>/images/report/NextPage_disabled.gif"
									border="0">
								<img
									src="<%=request.getContextPath()%>/images/report/LastPage_disabled.gif"
									border="0">
							<%
				          	  }
				            %>
						</div>
					</div>
				</div>
			</div>

			<table width="100%" cellpadding="0" cellspacing="0" border="0">
				<!-- 下面是数据部分-->
				<tr>
					<td align="center">
						<%=sbuffer.toString()%>
					</td>
				</tr>
			</table>
		</div>
		<!-- basic scripts -->
		<!--[if !IE]> -->
		<script src="<%=request.getContextPath()%>/plugins/components/jquery/dist/jquery.js"></script>
		<!-- <![endif]-->
	
		<!--[if IE]>
		<script src="<%=request.getContextPath()%>/plugins/components/jquery.1x/dist/jquery.js"></script>
		<![endif]-->
		
	   	<script type="text/javascript" src="<%=request.getContextPath()%>/plugins/jquery/CommonPerson.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/webpage/main/pub.js"></script>
	</body>
	<script language="javascript">
		try{
			parent.fullHide();
		}catch(e){
		}
		function printme(){
			document.getElementById("menudiv").style.display="none";
			document.getElementById("navigatediv").style.display="none";
			parent.window.frames["report"].focus();
			parent.window.frames["report"].print();
			document.getElementById("menudiv").style.display="";
			document.getElementById("navigatediv").style.display="";
	   	}
		
		function doPrint(how){
	        //打印文档对象
	        var myDoc = {
	            settings:{
	                pageWidth: 2100,    //自定义纸张宽度（单位为十分之一毫米）
	                pageHeight: 1400,  //自定义纸张高度（单位为十分之一毫米）
	                orientation: 1     //打印方向
	           	},
	            enableScreenOnlyClass:true, // 使所有使用 screen-only 样式类的对象，只在预览、显示时可见，打印时隐藏
	            documents: document, // 打印页面(div)们在本文档中
	            marginIgnored: true,
	           	settingsID: 'mydocc', //打印设置的ID保存在注册表中
	            copyrights: '杰创软件拥有版权  www.jatools.com' // 版权声明必须
	        };
			// 调用打印方法
			if(how == '打印预览')
				jatoolsPrinter.printPreview(myDoc);   // 打印预览
			else if(how == '打印')
			   jatoolsPrinter.print(myDoc, true);   // 打印前弹出打印设置对话框
			else
			   jatoolsPrinter.print(myDoc, false);       // 不弹出对话框打印
		}
		
		function openView() {
			window.open("<%=request.getContextPath()%>/webpage/pub/report/print.jsp", "_blank");
			/*
			var url = "../report/JRPrintServlet";
			document.write('<APPLET ID="JrPrt" CODE = "JRViewApplet.class" CODEBASE = "../../applets" ARCHIVE = "jasperreports-3.5.2-applet.jar,commons-logging-1.0.2.jar,commons-collections-2.1.jar" WIDTH = "0" HEIGHT = "0">'); 
			document.write('<PARAM NAME = "type" VALUE="application/x-java-applet;version=1.2.2">'); 
			document.write('<PARAM NAME = "scriptable" VALUE="false">');
			document.write('<PARAM NAME = "REPORT_URL" VALUE ="'+url+'">');
			document.write('</APPLET>');
			*/
		}
		
	   	function dopdf(rclass,rtitle){
		   	reporttitle = encodeURIComponent(rtitle);
		   	window.location.href="<%=request.getContextPath()%>/report/PdfServlet?reportclass="+rclass+"&reporttitle="+reporttitle;
	   	}
	   	function dortf(rclass,rtitle){
		   	reporttitle = encodeURIComponent(rtitle);
		   	window.location.href="<%=request.getContextPath()%>/report/RtfServlet?reportclass="+rclass+"&reporttitle="+reporttitle;
	   	}
	   	function doxls(rclass,rtitle){
		   	reporttitle = encodeURIComponent(rtitle);
		   	window.location.href="<%=request.getContextPath()%>/report/XlsServlet?reportclass="+rclass+"&reporttitle="+reporttitle;
	   	}
	   	//报表页面加载完成后，设置父页面iframe高度
	   	$(window).load(function() {
   	   		var main = $(window.parent.document).find("#reportframe");
   	   		main.height($(document).height());
        });
	</script>
</html>