<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/webpage/pub/biz/head.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/style/jquery/botree.css" type="text/css" />
<body>
<div class="row">
	<div class="col-xs-12">
		<div class="col-xs-12 col-sm-3">
			<div id="dept_tree_grid_box" class="widget-box widget-color-blue">
				<div class="widget-header">
					<h5 class="widget-title"><i class="ace-icon fa fa-sitemap"></i>组织机构树</h5>
				</div>
				<div>
					<ul id="dept_tree" class="ztree" style="height: 528px;overflow:auto;"></ul>
				</div>
			</div>
		</div>
		<div class="col-xs-12 col-sm-9">				
			<div id="sys_user_grid_box" class="widget-box widget-color-blue">
				<div class="widget-header">
					<h5 class="widget-title"><i class="ace-icon fa fa-list"></i>用户信息</h5>
					<div class="widget-toolbar">
						<a href="#" data-action="fullscreen" class="white">
							<i class="ace-icon fa fa-expand"></i>
						</a>
						<a href="#" id="sys_user_grid_box_filter" class="white" data-action="filter">
							<i class="ace-icon fa fa-filter bigger"></i>
						</a>
						<a href="#" data-action="collapse" class="white">
							<i class="ace-icon fa fa-chevron-up"></i>
						</a>
					</div>
					<div id="sys_user_grid_btn_add" class="widget-toolbar no-border">
						<button class="btn btn-xs btn-primary bigger no-border">
							<i class="ace-icon fa fa-plus"></i>
							新增
						</button>
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
	</div>
</div>
</body>
<%@include file="/webpage/pub/biz/footer.jsp" %>
<script type="text/javascript" src="<%=request.getContextPath()%>/plugins/jquery/botree.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/webpage/manager/sys/user/tree.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/webpage/manager/sys/user/bizgrid.js"></script>
</html>