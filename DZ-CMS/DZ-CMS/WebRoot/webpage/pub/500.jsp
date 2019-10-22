<%@ page language="java" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	<title>500 - 系统内部错误</title>
	</head>

	<body>
<div><h1>系统发生内部错误.</h1></div>
<div><a href="<%=basePath%>">返回首页</a></div>
</body>
</html>
