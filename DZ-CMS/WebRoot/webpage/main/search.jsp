<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html>
	<head>
		<script>
			window.location.href="../webpage/main/adminindex.jsp";
		</script>
		<title>Dimine Search</title>
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
		<!-- text fonts -->
		<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/assets/css/ace-fonts.min.css" />
		<!-- ace styles -->
		<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/assets/css/ace.min.css" class="ace-main-stylesheet" id="main-ace-style" />
		
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
		<script type="text/javascript">
			var rootpath = "<%=path%>";
		</script>
		<style>
			.highlight{color: red;}
		</style>
	</head>

	<body class="no-skin" style="background-color: #fff">
		<!-- #section:basics/navbar.layout -->
		<div id="menusearch" class="navbar navbar-default" style="display:none;">
			<div class="navbar-container" id="navbar-container">
				<!-- #section:basics/sidebar.mobile.toggle -->
				<button type="button" class="navbar-toggle menu-toggler pull-left"
					id="menu-toggler" data-target="#sidebar">
					<span class="sr-only">Toggle sidebar</span>
		
					<span class="icon-bar"></span>
		
					<span class="icon-bar"></span>
		
					<span class="icon-bar"></span>
				</button>
		
				<!-- /section:basics/sidebar.mobile.toggle -->
				<div class="navbar-header pull-left input-group">
					<!-- #section:basics/navbar.layout.brand -->
					<a href="#" class="navbar-brand"> <small> 
					<img src="../images/dm_logo.png" height="25" witdh="25"/> 
					<span id="logoname">长沙迪迈科技</span></small> </a>
				</div>
				<div class="navbar-buttons navbar-header" style="display:table;margin-top:5px;">
					<input type="text" id="menu_search" class="form-control search-query typeahead" placeholder="迪迈搜索 ..." />
					<button id="menu_btn_search" type="button" class="btn btn-success btn-sm" style="margin-top:-3px;">
						<span class="ace-icon fa fa-search icon-on-right bigger-110"></span>&nbsp;&nbsp;查询
					</button>
				</div>
			</div>
		</div>	
		<div class="main-container">
		<div class="container" id="textsearch">
			<div class="row" style="height:200px"></div>
			<div class="row">
				<div class="col-xs-12 center">
					<img src="../images/logo.png" style="width:15%;"/>
				</div>
			</div>
			<br/>
			<div class="row">
				<div class="col-xs-12 center">
					<!-- PAGE CONTENT BEGINS -->
					<div class="col-sm-2"></div>				
					<div class="input-group col-sm-8">
						<input type="text" id="text_search" class="form-control search-query" placeholder="迪迈搜索 ..." />
						<span class="input-group-btn">
							<button id="text_btn_search" type="button" style="width: 100px;" class="btn btn-success btn-sm">
								<span class="ace-icon fa fa-search icon-on-right bigger-110"></span>&nbsp;&nbsp;查询
							</button>
						</span>
					</div>
					<div class="col-sm-2"></div>
				</div><!-- /.col -->
			</div>
		</div>
		<div class="container" id="searchresults">			
			<div class="row" style="margin-left:-52px;">
				<div class="col-xs-12">
					<div class="widget-box transparent">
						<div class="widget-header widget-header-small" style="border:0px;">
							<h4 class="widget-title blue smaller" id="resultscount"></h4>
						</div>

						<div class="widget-body">
							<div class="widget-main padding-8">
								<!-- #section:pages/profile.feed -->
								<div id="profile-feed-1" class="profile-feed">
								</div>
								<!-- /section:pages/profile.feed -->
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="footer">
			<div class="footer-inner">
				<!-- #section:basics/footer -->
				<div class="footer-content">
					<span class="bigger-120">
						<a href="<%=path%>/main/adminindex.jsp">进入管理界面</a>
					</span>
					<br>
					<span class="bigger-120">
						Changsha Digital Mine Co.,Ltd. All Right Reserved
					</span>
				</div>
				<!-- /section:basics/footer -->
			</div>
		</div>
		</div>

		<!-- basic scripts -->
		<!--[if !IE]> -->
		<script src="<%=request.getContextPath()%>/plugins/components/jquery/dist/jquery.js"></script>
		<!-- <![endif]-->
		<!--[if IE]>
		<script src="<%=request.getContextPath()%>/plugins/components/jquery.1x/dist/jquery.js"></script>
		<![endif]-->
		<script type="text/javascript">
			if('ontouchstart' in document.documentElement) document.write("<script src='<%=request.getContextPath()%>/plugins/components/_mod/jquery.mobile.custom/jquery.mobile.custom.js'>"+"<"+"/script>");
		</script>
		<script src="<%=request.getContextPath()%>/plugins/components/bootstrap/dist/js/bootstrap.js"></script>
		<script src="<%=request.getContextPath()%>/plugins/components/typeahead.js/dist/typeahead.jquery.min.js"></script>
		<script src="<%=request.getContextPath()%>/plugins/components/_mod/jquery-ui.custom/jquery-ui.custom.js"></script>
		<script src="<%=request.getContextPath()%>/plugins/jquery/jquery.highlight-4.js"></script>
		<script src="search.js"></script>
		
		<!-- ace scripts -->
		<script src="<%=request.getContextPath()%>/plugins/assets/js/ace.min.js"></script>
		<script src="<%=request.getContextPath()%>/plugins/assets/js/ace-elements.min.js"></script>
	</body>
</html>
