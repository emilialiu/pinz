<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/webpage/pub/biz/head.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/style/jquery/botree.css" type="text/css" /> 
	<body>
		<title>工序对应班组</title>
		<div class="row">
			<div class="col-xs-3 col-sm-3" style="padding-left: 0px;">
					<div id="dept_tree_grid_box" class="widget-box widget-color-blue">
						<div class="widget-header">
							<h5 class="widget-title"><i class="ace-icon fa fa-sitemap"></i><s:text name="dmmes.sc.process.treename" /></h5>
						</div>
						<div>
							<ul id="process_tree" class="ztree" style="height: 598px;overflow:auto;"></ul>
						</div>
					</div>
			</div>
			<!-- /.col -->
			<div class="col-xs-9">				
				<div id="t_sc_teamtechprocess_grid_box" class="widget-box widget-color-blue">
					<div class="widget-header">
						<h5 class="widget-title"><i class="ace-icon fa fa-list"></i><s:text name="dmmes.sc.teamtechprocess.teamname" /></h5>
						<div class="widget-toolbar">
							<a href="#" data-action="fullscreen" class="orange2">
								<i class="ace-icon fa fa-expand"></i>
							</a>
							<a href="#" data-action="collapse">
								<i class="ace-icon fa fa-chevron-up"></i>
							</a>
						</div>
						<div id="t_sc_teamtechprocess_grid_btn_add" class="widget-toolbar no-border">
							<button class="btn btn-xs btn-success bigger no-border">
								<i class="ace-icon fa fa-plus"></i>
								<s:text name="dmmes.sc.technology.save" />
							</button>
						</div>
					</div>

					<div class="widget-body">
						<div class="widget-main no-padding">
							<table id="t_sc_teamtechprocess_grid"></table>
					
							<div id="t_sc_teamtechprocess_grid_pager"></div>
						</div>
					</div>
				</div>
			</div><!-- /.col -->
				
				<!-- PAGE CONTENT ENDS -->
		</div><!-- /.row -->
	</body>
<%@include file="/webpage/pub/biz/footer.jsp" %>
<script type="text/javascript">
 	 var sc_teamtechprocess_teamname = '<s:text name="dmmes.sc.teamtechprocess.teamname" />';
	 var sc_teamtechprocess_teamtypename = '<s:text name="dmmes.sc.teamtechprocess.teamtypename" />';
</script>
<script type="text/javascript" src="<%=request.getContextPath()%>/webpage/biz/sc/teamtechprocess/bizgrid.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/plugins/jquery/botree.js"></script> 
<script type="text/javascript" src="<%=request.getContextPath()%>/webpage/biz/sc/process/tree.js"></script>
</html>