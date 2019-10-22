<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/webpage/pub/biz/head.jsp" %>
	<body>
		<title>线性条数管理</title>
		<div class="row">
			<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS -->				
				<div class="input-group col-sm-8">
					<span class="input-group-addon">
						<i class="ace-icon fa fa-search"></i>
					</span>					
					<input type="text" id="dz_linear_number_text_search" class="form-control search-query" placeholder="" />
					<span class="input-group-btn">
						<button id="dz_linear_number_btn_search" type="button" class="btn btn-purple btn-sm">
							<span class="ace-icon fa fa-search icon-on-right bigger-110"></span>&nbsp;&nbsp;
							查询
						</button>
						<button id="dz_linear_number_btn_advsearch" style="margin-left:10px;" class="btn btn-pink btn-sm">
							<span class="ace-icon fa fa-filter icon-on-right bigger-110"></span>&nbsp;&nbsp;
							高级查询
						</button>
					</span>
				</div>
			</div><!-- /.col -->
			<div class="col-xs-12">				
				<div id="dz_linear_number_grid_box" class="widget-box widget-color-blue">
					<div class="widget-header">
						<h5 class="widget-title"><i class="ace-icon fa fa-list"></i>线性条数管理</h5>
						<div class="widget-toolbar">
							<a href="#" data-action="fullscreen" class="white">
								<i class="ace-icon fa fa-expand"></i>
							</a>
							<a href="#" id="dz_linear_number_grid_box_filter" class="white" data-action="filter">
								<i class="ace-icon fa fa-filter bigger"></i>
							</a>
							<a href="#" data-action="collapse" class="white">
								<i class="ace-icon fa fa-chevron-up"></i>
							</a>
						</div>
						<div id="dz_linear_number_grid_btn_add" class="widget-toolbar no-border">
							<button class="btn btn-xs btn-primary bigger no-border">
								<i class="ace-icon fa fa-plus"></i>
								新增
							</button>
						</div>
					</div>

					<div class="widget-body">
						<div class="widget-main no-padding">
							<table id="dz_linear_number_grid"></table>
					
							<div id="dz_linear_number_grid_pager"></div>
						</div>
					</div>
				</div>
			</div><!-- /.col -->
				
			<!-- PAGE CONTENT ENDS -->
		</div><!-- /.row -->
	</body>
<%@include file="/webpage/pub/biz/footer.jsp" %>
<script type="text/javascript" src="<%=request.getContextPath()%>/webpage/biz/linear/number/bizgrid.js"></script>
</html>