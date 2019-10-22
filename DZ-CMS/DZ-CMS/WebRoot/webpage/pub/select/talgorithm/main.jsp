<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<title>工序选择</title>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<meta name="viewport" content="width=sys_dict-width, initial-scale=1.0, maximum-scale=1.0" />
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link rel="stylesheet" href="../../../demojs/css/bootstrap.min.css" />
		<link rel="stylesheet" href="../../../demojs/css/font-awesome.min.css" />
		<link rel="stylesheet" href="../../../demojs/css/jquery-ui.min.css" />
		<link rel="stylesheet" href="../../../demojs/css/ui.jqgrid.min.css" />
		<!-- text fonts -->
		<link rel="stylesheet" href="../../../demojs/css/ace-fonts.min.css" />
		<!-- ace styles -->
		<link rel="stylesheet" href="../../../demojs/css/ace.min.css" class="ace-main-stylesheet" id="main-ace-style" />
		<!--[if lte IE 9]>
			<link rel="stylesheet" href="../../../demojs/css/ace-part2.min.css" class="ace-main-stylesheet" />
		<![endif]-->

		<!--[if lte IE 9]>
		  <link rel="stylesheet" href="../../../demojs/css/ace-ie.min.css" />
		<![endif]-->

		<!-- ace settings handler -->
		<script src="../../../demojs/js/ace-extra.min.js"></script>

		<!-- HTML5 shim and Respond.min.js IE8 support of HTML5 elements and media queries -->

		<!--[if lte IE 8]>
		<script src="../../../demojs/js/html5shiv.min.js"></script>
		<script src="../../../demojs/js/respond.min.js"></script>
		<![endif]-->
		<link rel="stylesheet" href="<%=request.getContextPath()%>/style/jquery/botree.css" type="text/css" />
	</head>

	<body style="background-color: #fff">
	<div id="counter" >
	<!-- <div style="text-align:center;clear:both;">
	</div> -->
	<input type="hidden" id="talgorithm" value=""/>
	<div id="bg" style="margin:0;">
		<div id="counter_content" >
			<h3><input id="input1" readonly="true" type="text" value="" /></h3>
			<ul>
				<li>+</li>
				<li>-</li>
				<li>*</li>
				<li>/</li>
				<li>(</li>
				<li>)</li>
				<li>清除</li>
				<li>回退</li>
				<li>1</li>
				<li>2</li>
				<li>3</li>
				<li>4</li>
				<li>5</li>
				<li>6</li>
				<li>7</li>
				<li>8</li>
				<li>9</li>
				<li>0</li>
			</ul>
		</div>
		</div>

</div>
			<!-- PAGE CONTENT ENDS -->
		<!-- /.row -->

		
		<!--[if !IE]> -->
		<script type="text/javascript">
			window.jQuery || document.write("<script src='../../../demojs/js/jquery.min.js'>"+"<"+"/script>");
		</script>

		<!-- <![endif]-->

		<!--[if IE]>
		<script type="text/javascript">
		 window.jQuery || document.write("<script src='../../../demojs/js/jquery1x.min.js'>"+"<"+"/script>");
		</script>
		<![endif]-->
		<script type="text/javascript">
			if('ontouchstart' in document.documentElement) document.write("<script src='../../../demojs/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
			var procid="<%=request.getParameter("procid")%>";
		</script>
		<script src="../../../demojs/js/bootstrap.min.js"></script>
		<script src="../../../demojs/js/ace.min.js"></script>
		<script src="../../../demojs/js/ace-elements.min.js"></script>
		<script src="../../../demojs/js/jquery.bootstrap-duallistbox.min.js"></script>
		<script src="../../../demojs/js/jquery.raty.min.js"></script>
		<script src="../../../demojs/js/bootstrap-multiselect.min.js"></script>
		<script src="../../../demojs/js/select2.min.js"></script>
		<script src="../../../demojs/js/typeahead.jquery.min.js"></script>
		<script src="../../../demojs/js/jquery-ui.min.js"></script>
		<script src="../../../demojs/js/jquery.gritter.min.js"></script>
	   	<script src="<%=request.getContextPath()%>/plugins/jquery/jquery.cookie.js"></script>
	   	<script src="../../../pub/biz/info.js"></script>
	   	<script src="../../../pub/biz/grid.js"></script>
	   	<script src="../../../demojs/js/jquery.ui.touch-punch.min.js"></script>
	   	<script src="../../../demojs/js/bootbox.min.js"></script>
	   	<script src="../../../demojs/js/date-time/bootstrap-datepicker.min.js"></script>
	   	<script src="../../../demojs/js/jqgrid/jquery.jqgrid.min.js"></script>
	   	<script src="../../../demojs/js/jqgrid/i18n/grid.locale-cn.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/webpage/main/pub.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/plugins/jquery/botree.js"></script>
		<script type="text/javascript" src="./bizgrid.js"></script>
		<link rel="stylesheet" type="text/css" href="./style.css" />
		<script type="text/javascript" src="./script.js"></script>
	</body>
</html>
