<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/taglibs.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<title>兰秋妮定制平台</title>
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
			'background-color:#307ecc;
		}
		</style>
		<!-- 
		<script type="text/javascript" src="http://api.map.baidu.com/api?v=1.5&ak=922856ecacac61bde4c03cecef1ed6bc"></script>
		<!--加载鼠标绘制工具
		<script type="text/javascript" src="<%=request.getContextPath()%>/plugins/bdmap/DrawingManager_min.js"></script>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/style/bdmap/DrawingManager_min.css" />-->
		<!--加载检索信息窗口
		<script type="text/javascript" src="<%=request.getContextPath()%>/plugins/bdmap/SearchInfoWindow_min.js"></script>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/style/bdmap/SearchInfoWindow_min.css" /> -->	
	</head>

	<body class="no-skin" onload="doSelectMemu()">
		<!-- 引入头部 代码 -->
		<%@ include file="top.jsp"%>
		<!-- /section:basics/navbar.layout -->
		<div class="main-container" id="main-container">
			<script type="text/javascript">
				try{ace.settings.check('main-container' , 'fixed')}catch(e){}
			</script>
			<!-- 引入左边导航 代码 -->
			<%@ include file="leftmenu.jsp"%>
			<div class="main-content" >
				<div class="page-content">
					<div class="row">
						<div class="col-xs-12" style="width: 99%;height:100%;padding-left:2px;padding-right: 2px;" id="tabs">
                            <ul class="nav nav-tabs" role="tablist">
                               <%--  <li class="active" id="tab_tab_0"><a href="#Index" role="tab" data-toggle="tab"><s:text name="dmmes.home.homePage" /></a></li> --%>
                            </ul>
                            <div class="tab-content">
                                <div role="tabpanel" class="tab-pane active" id="Index" style="height:680px">
                                	<iframe id="content_tab_0" name="content_tab_0" src="<%-- <%=request.getContextPath()%>/main/home/doEnter.action --%>" width="100%" height="100%" frameborder="no" 
                                		border="0" marginwidth="0" marginheight="0" scrolling="yes" allowtransparency="yes"></iframe>
                                </div>
                            </div>
                        </div>
					</div><!-- /.row -->
				</div>
			</div>
			<div id='div_loadingImg'></div>
			<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
				<i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
			</a>
		</div><!-- /.main-container -->

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

		<script src="<%=request.getContextPath()%>/plugins/components/bootstrap/dist/js/bootstrap.min.js"></script>
		<script src="<%=request.getContextPath()%>/plugins/components/_mod/jquery-ui.custom/jquery-ui.custom.js"></script>
		<script src="<%=request.getContextPath()%>/plugins/components/jqueryui-touch-punch/jquery.ui.touch-punch.js"></script>
		<script src="<%=request.getContextPath()%>/plugins/components/select2/dist/js/select2.min.js"></script>
		<script src="<%=request.getContextPath()%>/plugins/components/typeahead.js/dist/typeahead.jquery.min.js"></script>
		<script src="<%=request.getContextPath()%>/plugins/components/jquery.gritter/js/jquery.gritter.min.js"></script>
	   	<script src="<%=request.getContextPath()%>/plugins/components/bootbox.js/bootbox.min.js"></script>
	   	<script src="<%=request.getContextPath()%>/plugins/components/bootstrap-datepicker/dist/js/bootstrap-datepicker.js"></script>
	   	<script src="<%=request.getContextPath()%>/plugins/components/spin.js/spin.min.js"></script>
		<script src="<%=request.getContextPath()%>/plugins/components/jquery-ui/jquery-ui.min.js"></script>
		
	   	<script src="<%=request.getContextPath()%>/plugins/validform/Validform_v5.3.2.js"></script>
	   	<script src="<%=request.getContextPath()%>/plugins/validform/Validform_Datatype.js"></script>
		<script src="<%=request.getContextPath()%>/plugins/jquery/jquery.contextmenu.js"></script>
	   	<script src="<%=request.getContextPath()%>/plugins/jquery/jquery.cookie.js"></script>
	   	<script src="<%=request.getContextPath()%>/plugins/My97DatePicker/WdatePicker.js"></script>	
	   	<script src="<%=request.getContextPath()%>/plugins/jquery/CommonPerson.js"></script>
		<script src="<%=request.getContextPath()%>/plugins/jquery/owin_<s:text name="dimine.lang" />.js"></script>
		<script src="pub.js"></script>
		<script src="menu.js"></script>
		<script src="top.js"></script>
		<script src="bootstrap-tab.js"></script>
		
		<!-- ace scripts -->
		<script src="<%=request.getContextPath()%>/plugins/assets/js/ace.min.js"></script>
		<script src="<%=request.getContextPath()%>/plugins/assets/js/ace-elements.min.js"></script>
	</body>
</html>
