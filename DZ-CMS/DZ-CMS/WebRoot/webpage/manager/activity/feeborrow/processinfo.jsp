<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/pub/biz/headinfo.jsp" %>
<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<title>Welcome - Dimine</title>

		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

		<!-- bootstrap & fontawesome -->
		<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/bootstrap.min.css" />
		<!--<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/font-awesome.min.css" />-->
		<!--<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/jquery.gritter.min.css" />
		
		<!--<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/jquery-ui.min.css" />-->
		<!--<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/datepicker.min.css" />-->
		<!--<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/ui.jqgrid.min.css" />-->
		
		<!-- text fonts -->
		<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/ace-fonts.min.css" />

		<!-- ace styles -->
		<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/ace.min.css" class="ace-main-stylesheet" id="main-ace-style" />
		
		<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/ace.dm.css" />
		
		<!-- valid styles -->
		<link rel="stylesheet" href="<%=request.getContextPath()%>/style/validform/style.css"/>
		
		<!--[if lte IE 9]>
			<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/ace-part2.min.css" class="ace-main-stylesheet" />
		<![endif]-->

		<!--[if lte IE 9]>
		  <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/ace-ie.min.css" />
		<![endif]-->

		<!-- ace settings handler -->
		<script src="<%=request.getContextPath()%>/assets/js/ace-extra.min.js"></script>

		<!-- HTML5 shim and Respond.min.js IE8 support of HTML5 elements and media queries -->

		<!--[if lte IE 8]>
		<script src="<%=request.getContextPath()%>/assets/js/html5shiv.min.js"></script>
		<script src="<%=request.getContextPath()%>/assets/js/respond.min.js"></script>
		<![endif]-->
		<style>
		input::-ms-clear{
		display: none;
		}
		.ui-dialog .ui-dialog-titlebar {
			padding: .4em 1em;
			position: relative;
			'background-color:#307ecc;
		}
		</style>		
</head>
<script type="text/javascript">
	var bussinesskey='<%=request.getParameter("bussinesskey")%>';
	var taskDefinitionKey='<%=request.getParameter("taskDefinitionKey")%>';
</script>
<div id="act_feeborrow_buiness_info" style="padding-left:4px;">
	<form id="act_feeborrow_buiness_form" method="post" class="form-horizontal">
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-6 no-padding-right"
				for="username">
				借支人:
			</label>
			<div class="col-xs-12 col-sm-9">
				<div class="col-sm-6"><!-- class="clearfix" -->
					<input type="text" name="username" datatype="*" id="username" class="col-xs-12" />
				</div>
				<div class="col-sm-3">
					<div class="info"><span class="Validform_checktip"></span><span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s></span></div>
				</div>
			</div>
		</div>
		
		<div class="space-2"></div>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right"
				for="borrowdate">
				借支日期:
			</label>
			<div class="col-xs-12 col-sm-9">
				<div class="col-sm-6"><!-- class="clearfix" -->
					<input type="text" name="borrowdate" datatype="*" id="borrowdate" class="col-xs-12" />
				</div>
				<div class="col-sm-3">
					<div class="info"><span class="Validform_checktip"></span><span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s></span></div>
				</div>
			</div>
		</div>
		
		<div class="space-2"></div>
		
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right"
				for="borrowamount">
				借支金额:
			</label>
			<div class="col-xs-12 col-sm-9">
				<div class="col-sm-6"><!-- class="clearfix" -->
					<input type="text" name="borrowamount" datatype="*" nullmsg="借支金额必填！" id="borrowamount" class="col-xs-12" />
				</div>
				<div class="col-sm-3">
					<div class="info"><span class="Validform_checktip"></span><span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s></span></div>
				</div>
			</div>
		</div>
		
		<div class="space-2"></div>
		
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right"
				for="cause">
				借支原因:
			</label>
			<div class="col-xs-12 col-sm-9">
				<div class="col-sm-6"><!-- class="clearfix" -->
					<textarea name="cause" datatype="*" nullmsg="借支原因必填！" id="cause" class="col-xs-12" /> </textarea>
				</div>
				<div class="col-sm-3">
					<div class="info"><span class="Validform_checktip"></span><span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s></span></div>
				</div>
			</div>
		</div>
		
		
	</form>
</div>
<!-- basic scripts -->

		<!--[if !IE]> -->
		<script type="text/javascript">
			window.jQuery || document.write("<script src='<%=request.getContextPath()%>/assets/js/jquery.min.js'>"+"<"+"/script>");
		</script>

		<!-- <![endif]-->

		<!--[if IE]>
		<script type="text/javascript">
		 window.jQuery || document.write("<script src='<%=request.getContextPath()%>/assets/js/jquery1x.min.js'>"+"<"+"/script>");
		</script>
		<![endif]-->
		<script type="text/javascript">
			if('ontouchstart' in document.documentElement) document.write("<script src='<%=request.getContextPath()%>/assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
		</script>
		<script src="<%=request.getContextPath()%>/assets/js/bootstrap.min.js"></script>
		
		<!-- ace scripts -->
		<script src="<%=request.getContextPath()%>/assets/js/ace.min.js"></script>
		<script src="<%=request.getContextPath()%>/assets/js/ace-elements.min.js"></script>
		<script src="<%=request.getContextPath()%>/assets/js/jquery.bootstrap-duallistbox.min.js"></script>
		<script src="<%=request.getContextPath()%>/assets/js/jquery.raty.min.js"></script>
		<script src="<%=request.getContextPath()%>/assets/js/bootstrap-multiselect.min.js"></script>
		<script src="<%=request.getContextPath()%>/assets/js/select2.min.js"></script>
		<script src="<%=request.getContextPath()%>/assets/js/typeahead.jquery.min.js"></script>
		<script src="<%=request.getContextPath()%>/assets/js/jquery-ui.min.js"></script>
		<script src="<%=request.getContextPath()%>/assets/js/jquery.gritter.min.js"></script>
	   	<script src="<%=request.getContextPath()%>/js/validform/Validform_v5.3.2.js"></script>
	   	<script src="<%=request.getContextPath()%>/js/validform/Validform_Datatype.js"></script>
	   	<script src="<%=request.getContextPath()%>/js/jquery/jquery.cookie.js"></script>
	   	<script src="<%=request.getContextPath()%>/main/pub.js"></script>
	   	<script src="<%=request.getContextPath()%>/pub/biz/info.js"></script>
	   	<script src="<%=request.getContextPath()%>/pub/biz/grid.js"></script>
	   	<script src="<%=request.getContextPath()%>/assets/js/jquery.ui.touch-punch.min.js"></script>
	   	<script src="<%=request.getContextPath()%>/assets/js/bootbox.min.js"></script>
	   	<script src="<%=request.getContextPath()%>/assets/js/date-time/bootstrap-datepicker.min.js"></script>
	   	<script src="<%=request.getContextPath()%>/assets/js/jqgrid/jquery.jqgrid.min.js"></script>
	   	<script src="<%=request.getContextPath()%>/assets/js/jqgrid/i18n/grid.locale-cn.js"></script>
	   	<script type="text/javascript" src="<%=request.getContextPath()%>/manager/activity/feeborrow/info.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/manager/activity/feeborrow/process.js"></script>
	   	
<%@include file="/pub/biz/footerinfo.jsp" %>