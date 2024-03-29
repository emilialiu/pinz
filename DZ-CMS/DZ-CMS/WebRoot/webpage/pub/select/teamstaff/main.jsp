<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/webpage/pub/biz/head.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/style/jquery/botree.css" type="text/css" />
<%@include file="/webpage/pub/biz/footer.jsp" %>
	<body style="background-color: #fff;overflow:hidden;">
		<div class="row" style="padding:0px;">
			<div class="col-xs-12" style="padding:0 2px 0 12px">
				<!-- PAGE CONTENT BEGINS -->				
				<div class="input-group col-sm-8">
					<span class="input-group-addon">
						<i class="ace-icon fa fa-search"></i>
					</span>					
					<input type="text" id="staff_text_search" class="form-control search-query" placeholder="员工编码，名称" />
					<span class="input-group-btn">
						<button id="staff_btn_search" type="button" class="btn btn-primary btn-sm">
							<span class="ace-icon fa fa-search icon-on-right bigger-110"></span>&nbsp;&nbsp;
							<s:text name="button.query" />
						</button>
						<button id="staff_btn_advsearch" style="margin-left:10px;" class="btn btn-primary btn-sm">
							<span class="ace-icon fa fa-filter icon-on-right bigger-110"></span>&nbsp;&nbsp;
							<s:text name="button.Advancedquery" />
						</button>
					</span>
				</div>
			</div><!-- /.col -->
			<div class="col-xs-12">
				<!-- <div class="col-xs-12 col-sm-2" style="padding:0 2px 0 10px">
					<div id="dept_tree_grid_box" class="widget-box widget-color-blue">
						<div class="widget-header">
							<h5 class="widget-title"><i class="ace-icon fa fa-sitemap"></i>组织机构树</h5>
						</div>
						<div>
							<ul id="dept_tree" class="ztree" style="height: 339px;overflow:auto;"></ul>
						</div>
					</div>
				</div> -->
				<div class="col-xs-12 col-sm-12" style="padding:0 10px 0 2px">
					<div id="staff_grid_box" class="widget-box widget-color-blue">
						<div class="widget-header">
							<h5 class="widget-title"><i class="ace-icon fa fa-list"></i>员工信息</h5>
							<div class="widget-toolbar">
								<a href="#" data-action="fullscreen" class="orange2">
									<i class="ace-icon fa fa-expand"></i>
								</a>
								<a href="#" id="staff_grid_box_filter" data-action="filter">
									<i class="ace-icon fa fa-filter bigger red"></i>
								</a>
								<a href="#" data-action="collapse">
									<i class="ace-icon fa fa-chevron-up"></i>
								</a>
							</div>
						</div>
	
						<div class="widget-body">
							<div class="widget-main no-padding">
								<table id="staff_grid"></table>
						
								<div id="staff_grid_pager"></div>
							</div>
						</div>
					</div>
				</div>
			</div><!-- /.col -->
			<!-- PAGE CONTENT ENDS -->
		</div><!-- /.row -->
		<script type="text/javascript">
			var multiselect = <%=request.getParameter("multiselect")%>;
			var hideClearBtn = <%=request.getParameter("hideClearBtn")%>;
			var teamgroup = <%=request.getParameter("teamgroup")%>;
		</script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/plugins/jquery/botree.js"></script>
		<script type="text/javascript" src="./tree.js"></script>
		<script type="text/javascript" src="./bizgrid.js"></script>
	</body>
</html>
