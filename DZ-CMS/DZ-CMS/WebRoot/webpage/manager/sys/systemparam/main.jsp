<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>系统参数管理</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style/jquery/boui.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style/jquery/icon.css" />
	<!--[if lt IE 9]>
			<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery-1.9.1.min.js"></script>
	<![endif]-->
	<!--[if gte IE 9]><!-->
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery-2.1.0.min.js"></script>
	<!--<![endif]--> 
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/boui.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/boui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="bizgrid.js"></script>
</head>

<body class="boui-layout" >  
	<div region="center" style="padding:2px;overflow: hidden;" border="false">  	
		 <table id="grid" ></table>
	</div>
</body>
</html>