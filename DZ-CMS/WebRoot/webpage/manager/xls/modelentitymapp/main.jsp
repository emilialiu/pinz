<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>表名与实体类映射</title>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style/jquery/boui.css" />
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style/jquery/icon.css" />
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/boui.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/boui-lang-zh_CN.js"></script>
		<script type="text/javascript" src="bizgrid.js"></script>
	</head>

	<body class="boui-layout">
		<div region="center" style="padding:2px;overflow: hidden;" border="false">
			 <table id="grid" ></table>
		</div>
	</body>
</html>