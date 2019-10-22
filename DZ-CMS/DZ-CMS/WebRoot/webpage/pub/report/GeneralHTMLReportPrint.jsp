<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="net.sf.jasperreports.engine.*" %>
<%@ page import="net.sf.jasperreports.engine.util.*" %>
<%@ page import="net.sf.jasperreports.engine.export.*" %>
<%@ page import="net.sf.jasperreports.j2ee.servlets.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>
<%@ page import="com.dimine.report.ReportUtil" %>

<% 
	JasperPrint jasperPrint = (JasperPrint)session.getAttribute(ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE);
String reportName = (String)session.getAttribute(ReportUtil.REPORT_NAME);
//	if (request.getParameter("reload") != null || jasperPrint == null)
//	{
  jasperPrint = (JasperPrint)session.getAttribute(reportName);
  session.setAttribute(ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE,jasperPrint);
//	}

  String reportTitle = (String)session.getAttribute(ReportUtil.REPORT_TITLE);

  JRHtmlExporter exporter = new JRHtmlExporter();
  int pageIndex = 0;
  int lastPageIndex = 0;
  if (jasperPrint.getPages() != null){
    lastPageIndex = jasperPrint.getPages().size() - 1;
  }

  String reportclass = request.getParameter("reportclass");
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
<title><%=reportTitle %></title>
		<link rel="stylesheet"
			href="<%=request.getContextPath()%>/style/style.css"
			type="text/css" />
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/jquery/jquery.js"></script>
<script language="VBScript">
dim hkey_root,hkey_path,hkey_key
hkey_root="HKEY_CURRENT_USER"
hkey_path="\Software\Microsoft\Internet Explorer\PageSetup"
'//设置网页打印的页眉页脚为空
sub pagesetup_null
    on error resume next
    Set RegWsh = CreateObject("WScript.Shell")
    hkey_key="\header"    
    RegWsh.RegWrite hkey_root+hkey_path+hkey_key,""
    hkey_key="\footer"
    RegWsh.RegWrite hkey_root+hkey_path+hkey_key,""
 end sub 
'//设置网页打印的页眉页脚为默认值
sub pagesetup_default
    on error resume next
    Set RegWsh = CreateObject("WScript.Shell")
    hkey_key="\header"    
    RegWsh.RegWrite hkey_root+hkey_path+hkey_key,"&w&b页码,&p/&P"
    hkey_key="\footer"
    RegWsh.RegWrite hkey_root+hkey_path+hkey_key,"&u&b&d"
end sub
</script>
<!--media=print 这个属性可以在打印时有效-->
<style media=print>
.Noprint{display:none;}
.PageNext{page-break-after: always;}
</style>

<style>
.tdp
{
    border-bottom: 1 solid #000000;
    border-left: ? solid #000000;
    border-right: ? solid #ffffff;
    border-top: 0 solid #ffffff;
}
.tabp
{
    border-color: #000000 #000000 #000000 #000000;
    border-style: solid;
    border-top-width: 2px;
    border-right-width: 2px;
    border-bottom-width: 1px;
    border-left-width: 1px;
}
.NOPRINT {
 font-family: "宋体";
 font-size: 9pt;
}

</style>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/reportstyle.css">
</head>
<body style="overflow:auto">

  <OBJECT  id=WebBrowser  classid=CLSID:8856F961-340A-11D0-A96B-00C04FD705A2  height=0  width=0>
  </OBJECT>
<%if(reportTitle != null && !"".equals(reportTitle)){%>
<table class="titleBarT">
    <tr>
      <td>
       <%=reportTitle %>
      </td>
    </tr>
</table>
<%}%>
<table width="98%" cellpadding="0" cellspacing="0" border="0" height="*">
<!-- 下面是数据部分-->
	<tr>
  		<td width="50%">&nbsp;</td>
  		<td align="left">
  			<%=sbuffer.toString()%>
  		</td>
  		<td width="50%">&nbsp;</td>
	</tr>
	<tr>
		<td align="center" colspan="3">
			<input type="button" id="confirm" value="Conform" style="width: 70px" />
			<input type="button" id="cancel" value="Cancel" style="width: 70px" onclick="parent.closeWin();"/>
			<input type="button" id="show" value="print" onclick="show()" style="width: 70px" />
		</td>
	</tr>
</table>
</body>
<script type="text/javascript">
	function show(){
	   	try{
			var sbtitle=document.getElementById("show");
			var confirm=document.getElementById("confirm");
			var cancel=document.getElementById("cancel");
			if(confirm){
			    confirm.style.display='none';
			}
			if(cancel){
			    cancel.style.display='none';
			}
			if(sbtitle){
			    sbtitle.style.display='none';
			    window.print();
			}
		}catch(e){}
	}
</script>
</html>
