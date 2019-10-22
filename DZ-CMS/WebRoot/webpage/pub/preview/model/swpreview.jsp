<%@page import="com.dimine.util.StringUtils"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>查看模型</title>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/assets/css/bootstrap.min.css" />
		<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/assets/css/font-awesome.min.css" />
		<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/assets/css/jquery-ui.min.css" />
		<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/assets/css/ui.jqgrid.min.css" />
		<!-- text fonts -->
		<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/assets/css/ace-fonts.min.css" />
		<!-- ace styles -->
		<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/assets/css/ace.min.css" class="ace-main-stylesheet" id="main-ace-style" />
		<!--[if lte IE 9]>
			<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/assets/css/ace-part2.min.css" class="ace-main-stylesheet" />
		<![endif]-->

		<!--[if lte IE 9]>
		  <link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/assets/css/ace-ie.min.css" />
		<![endif]-->

		<!-- ace settings handler -->
		<script src="<%=request.getContextPath()%>/plugins/assets/js/ace-extra.min.js"></script>

		<!-- HTML5 shim and Respond.min.js IE8 support of HTML5 elements and media queries -->

		<!--[if lte IE 8]>
		<script src="<%=request.getContextPath()%>/plugins/assets/js/html5shiv.min.js"></script>
		<script src="<%=request.getContextPath()%>/plugins/assets/js/respond.min.js"></script>
		<![endif]-->
		<link rel="stylesheet" href="<%=request.getContextPath()%>/style/jquery/botree.css" type="text/css" />
	</head>
	
	<body style="background-color: #fff;overflow:hidden;">
		<div class="row" style="padding:0px;padding-left:10px;">
			<div class="col-xs-12">
				<div class="col-xs-12 col-sm-6">
					<div class="col-xs-12 col-sm-6" style="text-align: left;margin-top:6px;">
						<img src="<%=request.getContextPath()%>/pub/select/dimine/xz.png" onclick="orbit()" title="旋转" />&nbsp;&nbsp;
						<img src="<%=request.getContextPath()%>/pub/select/dimine/yd.png" onclick="pan()" title="平移"/>&nbsp;&nbsp;
						<img src="<%=request.getContextPath()%>/pub/select/dimine/fw.png" onclick="zoomToExtents()" title="复位视图"/>&nbsp;&nbsp;
						<img src="<%=request.getContextPath()%>/pub/select/dimine/bottom.png" onclick="xZPlane()" title="从上往下看"/>&nbsp;&nbsp;
						<img src="<%=request.getContextPath()%>/pub/select/dimine/back.png" onclick="yxPlane()" title="从前向后看"/>&nbsp;&nbsp;
						<img src="<%=request.getContextPath()%>/pub/select/dimine/left.png" onclick="yzPlane()" title="从右向左看"/>&nbsp;&nbsp;
						<img src="<%=request.getContextPath()%>/pub/select/dimine/ch.png" onclick="select()" title="选择"/>
					</div>
				</div>
				
			</div>
			<div class="col-xs-12" style="padding-bottom:10px;">
				<div class="col-xs-12 col-sm-12" style="height:700px;" id="flex_tb">
					<object id="DimineWindow"
						classid="CLSID:224DB674-8091-4F6B-9762-B622895002CA"
						codebase="<%=request.getContextPath()%>/attachment/upfile/dimine/DimineX.cab#version=1,0,0,1"
						style="width: 100%; height: 75%">
					</object>
				</div>
			</div><!-- /.col -->
			<!-- PAGE CONTENT ENDS -->
		</div><!-- /.row -->

		<!--[if !IE]> -->
		<script type="text/javascript">
			window.jQuery || document.write("<script src='<%=request.getContextPath()%>/plugins/assets/js/jquery.min.js'>"+"<"+"/script>");
		</script>

		<!-- <![endif]-->

		<!--[if IE]>
		<script type="text/javascript">
		 window.jQuery || document.write("<script src='<%=request.getContextPath()%>/plugins/assets/js/jquery1x.min.js'>"+"<"+"/script>");
		</script>
		<![endif]-->
		<script type="text/javascript">
			if('ontouchstart' in document.documentElement) document.write("<script src='<%=request.getContextPath()%>/plugins/assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
			var fileurl = '<%=request.getAttribute("fileurl")%>';
			var port = "<%=request.getServerPort()%>";
			var host = "<%=request.getLocalAddr()%>";
		</script>
		<script src="<%=request.getContextPath()%>/plugins/assets/js/bootstrap.min.js"></script>
		<script src="<%=request.getContextPath()%>/plugins/assets/js/ace.min.js"></script>
		<script src="<%=request.getContextPath()%>/plugins/assets/js/ace-elements.min.js"></script>
		<script src="<%=request.getContextPath()%>/plugins/assets/js/jquery.bootstrap-duallistbox.min.js"></script>
		<script src="<%=request.getContextPath()%>/plugins/assets/js/jquery.raty.min.js"></script>
		<script src="<%=request.getContextPath()%>/plugins/assets/js/bootstrap-multiselect.min.js"></script>
		<script src="<%=request.getContextPath()%>/plugins/assets/js/select2.min.js"></script>
		<script src="<%=request.getContextPath()%>/plugins/assets/js/typeahead.jquery.min.js"></script>
		<script src="<%=request.getContextPath()%>/plugins/assets/js/jquery-ui.min.js"></script>
		<script src="<%=request.getContextPath()%>/plugins/assets/js/jquery.gritter.min.js"></script>
	   	<script src="<%=request.getContextPath()%>/plugins/jquery/jquery.cookie.js"></script>
	   	<script src="<%=request.getContextPath()%>/webpage/pub/biz/info.js"></script>
	   	<script src="<%=request.getContextPath()%>/webpage/pub/biz/grid.js"></script>
	   	<script src="<%=request.getContextPath()%>/plugins/assets/js/jquery.ui.touch-punch.min.js"></script>
	   	<script src="<%=request.getContextPath()%>/plugins/assets/js/bootbox.min.js"></script>
	   	<script src="<%=request.getContextPath()%>/plugins/assets/js/date-time/bootstrap-datepicker.min.js"></script>
	   	<script src="<%=request.getContextPath()%>/plugins/assets/js/jqgrid/jquery.jqgrid.min.js"></script>
	   	<script src="<%=request.getContextPath()%>/plugins/assets/js/jqgrid/i18n/grid.locale-cn.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/main/pub.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/botree.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/pub/select/dimine/swpreview.js"></script>
	</body>
</html>
