<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>机构选择</title>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link rel="stylesheet" href="../../../assets/css/bootstrap.min.css" />
		<link rel="stylesheet" href="../../../assets/css/font-awesome.min.css" />
		<link rel="stylesheet" href="../../../assets/css/jquery-ui.min.css" />
		<!-- text fonts -->
		<link rel="stylesheet" href="../../../assets/css/ace-fonts.min.css" />
		<!-- ace styles -->
		<link rel="stylesheet" href="../../../assets/css/ace.min.css" class="ace-main-stylesheet" id="main-ace-style" />
		<!--[if lte IE 9]>
			<link rel="stylesheet" href="../../../assets/css/ace-part2.min.css" class="ace-main-stylesheet" />
		<![endif]-->

		<!--[if lte IE 9]>
		  <link rel="stylesheet" href="../../../assets/css/ace-ie.min.css" />
		<![endif]-->

		<!-- ace settings handler -->
		<script src="../../../assets/js/ace-extra.min.js"></script>

		<!-- HTML5 shim and Respond.min.js IE8 support of HTML5 elements and media queries -->

		<!--[if lte IE 8]>
		<script src="../../../assets/js/html5shiv.min.js"></script>
		<script src="../../../assets/js/respond.min.js"></script>
		<![endif]-->
		<link rel="stylesheet" href="<%=request.getContextPath()%>/style/jquery/botree.css" type="text/css" />
	</head>

	<body style="background-color: #fff">
		<div class="row" style="padding:0px;">
			<div class="col-xs-12" style="padding:0px;">
				<div class="col-sm-4">
					<div class="widget-box widget-color-blue">
						<div class="widget-header">
							<h4 class="widget-title lighter smaller"><i class="ace-icon fa fa-sitemap"></i>组织机构树</h4>
						</div>
						<div class="widget-body">
							<div class="widget-main padding-8" id="leftid">
								<ul id="dept_tree" class="ztree"></ul>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<!--[if !IE]> -->
		<script type="text/javascript">
			window.jQuery || document.write("<script src='../../../assets/js/jquery.min.js'>"+"<"+"/script>");
		</script>

		<!-- <![endif]-->

		<!--[if IE]>
		<script type="text/javascript">
		 window.jQuery || document.write("<script src='../../../assets/js/jquery1x.min.js'>"+"<"+"/script>");
		</script>
		<![endif]-->
		<script type="text/javascript">
			if('ontouchstart' in document.documentElement) document.write("<script src='../../../assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
		</script>
		<script src="../../../assets/js/bootstrap.min.js"></script>
		<script src="../../../assets/js/ace.min.js"></script>
		<script src="../../../assets/js/ace-elements.min.js"></script>
		<script src="../../../assets/js/jquery.bootstrap-duallistbox.min.js"></script>
		<script src="../../../assets/js/jquery.raty.min.js"></script>
		<script src="../../../assets/js/bootstrap-multiselect.min.js"></script>
		<script src="../../../assets/js/select2.min.js"></script>
		<script src="../../../assets/js/typeahead.jquery.min.js"></script>
		<script src="../../../assets/js/jquery-ui.min.js"></script>
		<script src="../../../assets/js/jquery.gritter.min.js"></script>
	   	<script src="../../../js/jquery/jquery.cookie.js"></script>
	   	<script src="../../../pub/biz/info.js"></script>
	   	<script src="../../../pub/biz/grid.js"></script>
	   	<script src="../../../assets/js/jquery.ui.touch-punch.min.js"></script>
	   	<script src="../../../assets/js/bootbox.min.js"></script>
	   	<script src="../../../assets/js/date-time/bootstrap-datepicker.min.js"></script>
	   	<script src="../../../assets/js/jqgrid/jquery.jqgrid.min.js"></script>
	   	<script src="../../../assets/js/jqgrid/i18n/grid.locale-cn.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/main/pub.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/botree.js"></script>
		<script type="text/javascript">
			var root = <%= "["+(String) request.getAttribute("jsonString")+"]"%>;
			var setting = {
				data: {
					simpleData: {
						enable: true,
						idKey: "id",
						pIdKey: "pid"
					}
				},
				check: {
					enable: true,
					autoCheckTrigger: true,
					chkStyle: "<%=request.getParameter("checkStyle")==null?"radio":request.getParameter("checkStyle") %>",
					radioType: "all",
					chkboxType: { "Y": "", "N": "" }
				},
				async: {
					enable: true,
					type: "post",
					url:rootpath+"/pub/select/dept/doTree.action",
					autoParam:["id=bean.deptid"]
				}
			};
			
			var BOTree;

			$(document).ready(function(){
				BOTree = $.fn.zTree.init($("#dept_tree"), setting, root);
				//调用默认展开第一个结点
			    var nodes = BOTree.getNodes();
			    BOTree.expandNode(nodes[0], true);
			});
			
			function onSave() {
				var no = BOTree.getCheckedNodes(true);
				if (no.length == 0) {
					alertMsg("请选择一个部门!");
					return;
				}
				try{
					parent.PageObject.itemMap['selectdept'].callback(no);
				}catch(e){
					parent.closewin('selectdept');
				}
				parent.closewin('selectdept');
			}
			
			function uncheck() {
				parent.PageObject.itemMap['selectdept'].callback([]);
				parent.closewin('selectdept');
			}
		</script>
	</body>
</html>
