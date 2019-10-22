<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/taglibs.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<title>作业地点选择</title>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/assets/css/bootstrap.min.css" />
		<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/components/font-awesome/css/font-awesome.min.css" />
		<link rel="stylesheet" href="<%=request.getContextPath()%>/webpage/demojs/css/jquery-ui.min.css" />
		<!-- text fonts -->
		<link rel="stylesheet" href="<%=request.getContextPath()%>/webpage/demojs/css/ace-fonts.min.css" />
		<!-- ace styles -->
		<link rel="stylesheet" href="<%=request.getContextPath()%>/webpage/demojs/css/ace.min.css" class="ace-main-stylesheet" id="main-ace-style" />
		<!--[if lte IE 9]>
			<link rel="stylesheet" href="../../../assets/css/ace-part2.min.css" class="ace-main-stylesheet" />
		<![endif]-->

		<!--[if lte IE 9]>
		  <link rel="stylesheet" href="../../../assets/css/ace-ie.min.css" />
		<![endif]-->

		<!-- ace settings handler -->
		<script src="<%=request.getContextPath()%>/webpage/demojs/js/ace-extra.min.js"></script>

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
							<h4 class="widget-title lighter smaller"><i class="ace-icon fa fa-sitemap"></i>作业地点树</h4>
						</div>
						<div class="widget-body">
							<div class="widget-main padding-8" id="leftid">
								<ul id="project_tree" class="ztree"></ul>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<!--[if !IE]> -->
		<script type="text/javascript">
			window.jQuery || document.write("<script src='<%=request.getContextPath()%>/webpage/demojs/js/jquery.min.js'>"+"<"+"/script>");
		</script>

		<!-- <![endif]-->

		<!--[if IE]>
		<script type="text/javascript">
		 window.jQuery || document.write("<script src='../../../assets/js/jquery1x.min.js'>"+"<"+"/script>");
		</script>
		<![endif]-->
		<script type="text/javascript">
			if('ontouchstart' in document.documentElement) document.write("<script src='<%=request.getContextPath()%>/webpage/demojs/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
		</script>
		<script src="<%=request.getContextPath()%>/plugins/components/bootstrap/dist/js/bootstrap.min.js"></script>
		<script src="<%=request.getContextPath()%>/plugins/assets/js/ace.min.js"></script>
		<script src="<%=request.getContextPath()%>/plugins/assets/js/ace-elements.min.js"></script>
		<script src="<%=request.getContextPath()%>/webpage/demojs/js/jquery.bootstrap-duallistbox.min.js"></script>
		<script src="<%=request.getContextPath()%>/webpage/demojs/js/jquery.raty.min.js"></script>
		<script src="<%=request.getContextPath()%>/webpage/demojs/js/bootstrap-multiselect.min.js"></script>
		<script src="<%=request.getContextPath()%>/webpage/demojs/js/select2.min.js"></script>
		<script src="<%=request.getContextPath()%>/webpage/demojs/js/typeahead.jquery.min.js"></script>
		<script src="<%=request.getContextPath()%>/webpage/demojs/js/jquery-ui.min.js"></script>
		<script src="<%=request.getContextPath()%>/webpage/demojs/js/jquery.gritter.min.js"></script>
	   	<script src="<%=request.getContextPath()%>/plugins/jquery/jquery.cookie.js"></script>
	   	<script src="<%=request.getContextPath()%>/webpage/pub/biz/info_en.js"></script>
	   	<script src="<%=request.getContextPath()%>/webpage/pub/biz/grid_en.js"></script>
	   	<script src="<%=request.getContextPath()%>/webpage/demojs/js/jquery.ui.touch-punch.min.js"></script>
	   	<script src="<%=request.getContextPath()%>/webpage/demojs/js/bootbox.min.js"></script>
	   	<script src="<%=request.getContextPath()%>/webpage/demojs/js/date-time/bootstrap-datepicker.min.js"></script>
	   	<script src="<%=request.getContextPath()%>/webpage/demojs/js/jqgrid/jquery.jqgrid.min.js"></script>
	   	<script src="<%=request.getContextPath()%>/webpage/demojs/js/jqgrid/i18n/grid.locale-cn.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/webpage/main/pub.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/plugins/jquery/botree.js"></script>
		<script type="text/javascript">
			var prcoid = '<%=request.getParameter("prcoid")%>';
			var root;
			if(prcoid=='f3554fc251d4408bba852eadb85ae2c2'){//破碎机台账
				 root = [{"id":"c06ff4fa722145bf9a322463dbb15058", "pid":"", "name":"破碎机采场", "isParent":"true", "close":"true"}];
			}else{
				 root = [{"id":"0000", "pid":"", "name":"工程信息", "isParent":"true", "close":"true"}];
			}
			var url='';
			if(prcoid=='289e476b86af45b2800ae099d5477853'){//穿孔台账
				 url = "/biz/bi/projectinfo/doTree1.action";
			}else{
				 url = "/biz/bi/projectinfo/doTree.action";
			}
			var pub_selectone='<s:text name="dmmes.pub.selectone" />';
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
					url:rootpath+url,
					autoParam:["id=bean.projectid"]
				}
			};
			
			var BOTree;

			$(document).ready(function(){
				BOTree = $.fn.zTree.init($("#project_tree"), setting, root);
				//调用默认展开第一个结点
			    var nodes = BOTree.getNodes();
			    BOTree.expandNode(nodes[0], true);
			});
			$("button:contains('清空选择')", window.parent.document).hide();
			$("button:contains('Empty choice')", window.parent.document).hide();
			function onSave() {
				var no = BOTree.getCheckedNodes(true);
				if (no.length == 0) {
					alertMsg( pub_selectone+" !");
					return;
				}

				try{
					parent.PageObject.itemMap['selectproject'].callback(no);
				}catch(e){
					parent.closewin('selectproject');
				}
				parent.closewin('selectproject');
			}
			
			function uncheck() {
				parent.PageObject.itemMap['selectproject'].callback([]);
				parent.closewin('selectproject');
			}
		</script>
	</body>
</html>
