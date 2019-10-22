<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>用户选择</title>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
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
		
		<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/assets/css/ace.dm.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/style/jquery/botree.css" type="text/css" />
		<!-- HTML5 shim and Respond.min.js IE8 support of HTML5 elements and media queries -->

		<!--[if lte IE 8]>
		<script src="<%=request.getContextPath()%>/plugins/components/html5shiv/dist/html5shiv.min.js"></script>
		<script src="<%=request.getContextPath()%>/plugins/components/respond/dest/respond.min.js"></script>
		<![endif]-->
	</head>

	<body style="background-color: #fff;overflow:hidden;">
		<div class="row" style="padding:0px;">
			<div class="col-xs-12" style="padding:0 2px 0 12px">
				<!-- PAGE CONTENT BEGINS -->				
				<div class="input-group col-sm-8">
					<span class="input-group-addon">
						<i class="ace-icon fa fa-search"></i>
					</span>					
					<input type="text" id="sys_user_text_search" class="form-control search-query" placeholder="用户姓名" />
					<span class="input-group-btn">
						<button id="sys_user_btn_search" type="button" class="btn btn-primary btn-sm">
							<span class="ace-icon fa fa-search icon-on-right bigger-110"></span>&nbsp;&nbsp;
							查询
						</button>
						<button id="sys_user_btn_advsearch" style="margin-left:10px;" class="btn btn-primary btn-sm">
							<span class="ace-icon fa fa-filter icon-on-right bigger-110"></span>&nbsp;&nbsp;
							高级查询
						</button>
					</span>
				</div>
			</div><!-- /.col -->
			<div class="col-xs-12">
				<div class="col-xs-12 col-sm-2 ui-widget-content resizable" style="padding-left: 0px;">
					<div id="dept_tree_grid_box" class="widget-box widget-color-blue">
						<div class="widget-header">
							<h5 class="widget-title"><i class="ace-icon fa fa-sitemap"></i>组织机构树</h5>
						</div>
						<div>
							<ul id="dept_tree" class="ztree" style="height: 550px;overflow:auto;"></ul>
						</div>
					</div>
				</div>
				<div class="col-xs-12 col-sm-10" style="padding:0 10px 0 10px">
					<div id="sys_user_grid_box" class="widget-box widget-color-blue">
						<div class="widget-header">
							<h5 class="widget-title"><i class="ace-icon fa fa-list"></i>用户信息</h5>
							<div class="widget-toolbar">
								<a href="#" data-action="fullscreen" class="orange2">
									<i class="ace-icon fa fa-expand"></i>
								</a>
								<a href="#" id="sys_user_grid_box_filter" data-action="filter">
									<i class="ace-icon fa fa-filter bigger red"></i>
								</a>
								<a href="#" data-action="collapse">
									<i class="ace-icon fa fa-chevron-up"></i>
								</a>
							</div>
						</div>
	
						<div class="widget-body">
							<div class="widget-main no-padding">
								<table id="sys_user_grid"></table>
						
								<div id="sys_user_grid_pager"></div>
							</div>
						</div>
					</div>
				</div>
			</div><!-- /.col -->
			<!-- PAGE CONTENT ENDS -->
		</div><!-- /.row -->

		<!--[if !IE]> -->
		<script src="<%=request.getContextPath()%>/plugins/components/jquery/dist/jquery.js"></script>
		<!-- <![endif]-->
		<script type="text/javascript" src="<%=request.getContextPath()%>/plugins/jquery/botree.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/webpage/pub/select/user/tree.js"></script>
		<!--[if IE]>
		<script src="<%=request.getContextPath()%>/plugins/components/jquery.1x/dist/jquery.js"></script>
		<![endif]-->
	
		<script type="text/javascript">
			if('ontouchstart' in document.documentElement) document.write("<script src='<%=request.getContextPath()%>/plugins/components/_mod/jquery.mobile.custom/jquery.mobile.custom.js'>"+"<"+"/script>");
			var multiselect = <%=request.getParameter("multiselect")%>;
			var hideClearBtn = <%=request.getParameter("hideClearBtn")%>;
		</script>
	
		<script src="<%=request.getContextPath()%>/plugins/components/bootstrap/dist/js/bootstrap.min.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/components/_mod/jquery-ui.custom/jquery-ui.custom.min.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/components/jqueryui-touch-punch/jquery.ui.touch-punch.min.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/components/select2/dist/js/select2.min.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/components/typeahead.js/dist/typeahead.jquery.min.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/components/jquery.gritter/js/jquery.gritter.min.js"></script>
   	<script src="<%=request.getContextPath()%>/plugins/components/bootbox.js/bootbox.min.js"></script>
   	<script src="<%=request.getContextPath()%>/plugins/components/bootstrap-datepicker/dist/js/bootstrap-datepicker.min.js"></script>
   	<script src="<%=request.getContextPath()%>/plugins/components/spin.js/spin.min.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/components/jquery-ui/jquery-ui.min.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/components/jqgrid/js/jquery.jqgrid.min.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/components/jqgrid/js/i18n/grid.locale-cn.js"></script>
	
   	<script src="<%=request.getContextPath()%>/plugins/validform/Validform_v5.3.2.js"></script>
   	<script src="<%=request.getContextPath()%>/plugins/validform/Validform_Datatype.js"></script>
   	<script src="<%=request.getContextPath()%>/plugins/My97DatePicker/WdatePicker.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/jquery/jquery.contextmenu.js"></script>
   	<script src="<%=request.getContextPath()%>/plugins/jquery/jquery.cookie.js"></script>
   	<script src="<%=request.getContextPath()%>/plugins/jquery/CommonPerson.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/jquery/owin.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/jquery/jquery-json.js"></script>
	<script src="<%=request.getContextPath()%>/webpage/main/pub.js"></script>
   	<script src="<%=request.getContextPath()%>/webpage/pub/biz/grid.js"></script>
		
		<!-- ace scripts -->
		<script src="<%=request.getContextPath()%>/plugins/assets/js/ace.min.js"></script>
		<script src="<%=request.getContextPath()%>/plugins/assets/js/ace-elements.min.js"></script>
		<script type="text/javascript" src="./bizgrid.js"></script>
	</body>
</html>
