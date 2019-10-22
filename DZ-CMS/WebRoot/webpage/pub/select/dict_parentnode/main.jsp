<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<title>父节点选择</title>
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
		<div class="row" style="padding:0 2px 0px 12px"">
			<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS -->				
				<div class="input-group col-sm-10">
					<span class="input-group-addon">
						<i class="ace-icon fa fa-search"></i>
					</span>					
					<input type="text" id="sys_dict_text_search" class="form-control search-query"  placeholder="<s:text name="dmmes.sys.dict.number" />、<s:text name="dmmes.sys.dict.name" />" />
					<span class="input-group-btn">
						<button id="sys_dict_btn_search" type="button" class="btn btn-primary btn-sm">
							<span class="ace-icon fa fa-search icon-on-right bigger-110"></span>&nbsp;&nbsp;
							<s:text name="button.query" />
						</button>
						<button id="sys_dict_btn_advsearch" style="margin-left:10px;" class="btn btn-primary btn-sm">
							<span class="ace-icon fa fa-filter icon-on-right bigger-110"></span>&nbsp;&nbsp;
							<s:text name="button.Advancedquery" />
						</button>
					</span>			
					
				</div>
			</div><!-- /.col -->
				<div class="col-xs-12" style="padding:0 2px 0 2px">
					<div id="sys_dict_grid_box" class="widget-box widget-color-blue">
					<div class="widget-header">
						<h5 class="widget-title"><i class="ace-icon fa fa-list"></i><s:text name="dmmes.sys.dict.parent" /></h5>
						<div class="widget-toolbar">
							<a href="#" data-action="fullscreen" class="orange2">
								<i class="ace-icon fa fa-expand"></i>
							</a>
							<a href="#" id="sys_dict_grid_box_filter" data-action="filter">
								<i class="ace-icon fa fa-filter bigger red"></i>
							</a>
							<a href="#" data-action="collapse">
								<i class="ace-icon fa fa-chevron-up"></i>
							</a>
						</div>
					</div>
					<div class="widget-body">
						<div class="widget-main no-padding">
							<table id="sys_dict_grid"></table>
					
							<div id="sys_dict_grid_pager"></div>
						</div>
					</div>
				</div>
			</div><!-- /.col -->
			<!-- PAGE CONTENT ENDS -->
		<!-- /.row -->
<script type="text/javascript">
 var sys_dict_description='<s:text name="dmmes.sys.dict.description" />';
 var sys_dict_whether ='<s:text name="dmmes.sys.dict.whether" />';
 var sys_dict_resourceNumber ='<s:text name="dmmes.sys.dict.resourceNumber" />';
 var sys_dict_resourceName ='<s:text name="dmmes.sys.dict.resourceName" />';
 var sys_dict_resourceType ='<s:text name="dmmes.sys.dict.resourceType" />';
</script>
		
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
			var multiselect = "<%=request.getParameter("multiselect")%>";
			var tdid = "<%=request.getParameter("tdid")%>";
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
	   	<script src="../../../pub/biz/info_en.js"></script>
	   	<script src="../../../pub/biz/grid_en.js"></script>
	   	<script src="../../../demojs/js/jquery.ui.touch-punch.min.js"></script>
	   	<script src="../../../demojs/js/bootbox.min.js"></script>
	   	<script src="../../../demojs/js/date-time/bootstrap-datepicker.min.js"></script>
	   	<script src="../../../demojs/js/jqgrid/jquery.jqgrid.min.js"></script>
	   	<script src="../../../demojs/js/jqgrid/i18n/grid.locale-cn.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/webpage/main/pub.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/plugins/jquery/botree.js"></script>
		<script type="text/javascript" src="./bizgrid.js"></script>
	</body>
</html>
