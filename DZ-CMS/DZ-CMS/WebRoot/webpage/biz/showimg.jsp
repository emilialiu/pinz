<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
	String path1 = request.getParameter("path");
	String filepath = path1.replaceAll("\\\\", "/");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>查看图片</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/plugins/viewerjs/viewer.min.css"
	type="text/css" />
<style>
* {
	margin: 0;
	padding: 0;
}

#jq22 {
	width: 700px;
	margin: 0 auto;
	font-size: 0;
}

#jq22 li {
	display: inline-block;
	width: 32%;
	margin-left: 1%;
	padding-top: 1%;
}

#jq22 li img {
	width: 100%;
}
</style>
</head>

<body>
	<ul id="jq22">
		<li><img style="display: none"
			data-original="<%=request.getContextPath()%>/upfile<%=filepath%>"
			src="<%=request.getContextPath()%>/upfile<%=filepath%>" alt="图片1">
		</li>
	</ul>
</body>

<script type="text/javascript"
	src="<%=request.getContextPath()%>/plugins/viewerjs/jquery.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/plugins/viewerjs/viewer.min.js"></script>
<script>
	var viewer = new Viewer(document.getElementById('jq22'), {
		url : 'data-original',
		hide : function() {
			window.close();
		}
	});
	$(document).ready(function() {
		viewer.show();
	});
	/**为API生，为框架死，为debug奋斗一辈子；吃符号亏，上大小写的当，最后死在需求上*/
</script>
</html>