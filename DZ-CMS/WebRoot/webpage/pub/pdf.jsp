<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>在线浏览</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/pdf/pdfobject.js"></script>
		<script type="text/javascript">
		    window.onload = function (){
		        var myPDF = new PDFObject({ url: "<%=request.getContextPath()%>/upfile<%=request.getParameter("filepath")%>" }).embed();
		    };
		</script>
	</head>
	<body>
	</body>
</html>
