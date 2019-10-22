<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/webpage/pub/biz/head.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/style/jquery/botree.css" type="text/css" />
<%@include file="/webpage/pub/biz/footer.jsp" %>
<body>
		<title>班组</title>
		<div class="row">
			<div class="col-xs-12">
				<div class="col-xs-12 col-sm-2 ui-widget-content resizable" style="padding-left: 0px;">
					<div id="dept_tree_grid_box" class="widget-box widget-color-blue">
						<div class="widget-header">
							<h5 class="widget-title"><i class="ace-icon fa fa-sitemap"></i>组织机构树</h5>
						</div>
						<div>
							<ul id="dept_tree" class="ztree" style="height: 604px;overflow:auto;"></ul>
						</div>
					</div>
				</div>
				<div class="col-xs-12 col-sm-10">
					<div id="team_grid_box" class="widget-box widget-color-blue">
						<div class="widget-header">
							<h5 class="widget-title"><i class="ace-icon fa fa-list"></i>班组</h5>
							<div class="widget-toolbar">
								<a href="#" data-action="fullscreen" class="orange2">
									<i class="ace-icon fa fa-expand"></i>
								</a>
								<a href="#" id="team_grid_box_filter" data-action="filter">
									<i class="ace-icon fa fa-filter bigger red"></i>
								</a>
								<a href="#" data-action="collapse">
									<i class="ace-icon fa fa-chevron-up"></i>
								</a>
							</div>
							<div id="team_grid_btn_add" class="widget-toolbar no-border">
								<button class="btn btn-xs btn-success bigger no-border">
									<i class="ace-icon fa fa-plus"></i>
									新增
								</button>
							</div>
						</div>
	
						<div class="widget-body">
							<div class="widget-main no-padding">
								<table id="team_grid"></table>
						
								<div id="team_grid_pager"></div>
							</div>
						</div>
					</div>

					<div id="teamstaff_grid_box" class="widget-box widget-color-blue">
						<div class="widget-header">
							<h5 class="widget-title"><i class="ace-icon fa fa-list"></i>班组对应人员</h5>
							<div class="widget-toolbar">
								<a href="#" data-action="fullscreen" class="orange2">
									<i class="ace-icon fa fa-expand"></i>
								</a>
								<a href="#" id="teamstaff_grid_box_filter" data-action="filter">
									<i class="ace-icon fa fa-filter bigger red"></i>
								</a>
								<a href="#" data-action="collapse">
									<i class="ace-icon fa fa-chevron-up"></i>
								</a>
							</div>
							<div id="teamstaff_grid_btn_add" class="widget-toolbar no-border">
								<button class="btn btn-xs btn-success bigger no-border">
									<i class="ace-icon fa fa-plus"></i>
									新增
								</button>
							</div>
						</div>
	
						<div class="widget-body">
							<div class="widget-main no-padding">
								<table id="teamstaff_grid"></table>
						
								<div id="teamstaff_grid_pager"></div>
							</div>
						</div>
					</div>
				</div><!-- /.col -->
			
			</div><!-- /.col -->	
			<!-- PAGE CONTENT ENDS -->
		</div><!-- /.row -->
<script type="text/javascript" src="<%=request.getContextPath()%>/plugins/jquery/botree.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/webpage/biz/sm/team/tree.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/webpage/biz/sm/team/bizgrid.js"></script>
</body>
</html>