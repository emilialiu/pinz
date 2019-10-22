<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/taglibs.jsp"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<title>Welcome - Dimine</title>

		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

		<!-- bootstrap & fontawesome -->
		<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/assets/css/bootstrap.min.css" />
		<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/components/font-awesome/css/font-awesome.min.css" />
		<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/components/_mod/jquery-ui/jquery-ui.css" />
		<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/components/jquery.gritter/css/jquery.gritter.min.css" />
		<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/components/bootstrap-datepicker/dist/css/bootstrap-datepicker3.css" />
		<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/components/_mod/jqgrid/ui.jqgrid.css" />
		
		<!-- text fonts -->
		<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/assets/css/ace-fonts.min.css" />

		<!-- ace styles -->
		<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/assets/css/ace.min.css" class="ace-main-stylesheet" id="main-ace-style" />
		
		<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/assets/css/ace.dm.css" />
		<!-- valid styles -->
		<link rel="stylesheet" href="<%=request.getContextPath()%>/style/validform/style.css"/>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/jquery/jquery.contextmenu.css"/>
		
		<!--[if lte IE 9]>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/assets/css/ace-part2.min.css" class="ace-main-stylesheet" />
		<![endif]-->
		<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/assets/css/ace-skins.min.css" />
		<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/assets/css/ace-rtl.min.css" />

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
		input::-ms-clear{
			display: none;
		}
		.ui-dialog .ui-dialog-titlebar {
			padding: .4em 1em;
			position: relative;
			background-color:#307ecc;
		}
		body{background-color: #FFFFFF;margin:0px 12px;}
		</style>		
	</head>