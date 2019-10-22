<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/webpage/pub/biz/head.jsp" %>
	<body>
		<title>矿业权基本信息</title>
		<div class="row">
			<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS -->				
				<div class="input-group col-sm-8">
					<span class="input-group-addon">
						<i class="ace-icon fa fa-search"></i>
					</span>					
					<input type="text" id="t_bi_mrbaseinfo_text_search" class="form-control search-query" placeholder="项目名称" />
					<span class="input-group-btn">
						<button id="t_bi_mrbaseinfo_btn_search" type="button" class="btn btn-purple btn-sm">
							<span class="ace-icon fa fa-search icon-on-right bigger-110"></span>&nbsp;&nbsp;
							查询
						</button>
						<button id="t_bi_mrbaseinfo_btn_advsearch" style="margin-left:10px;" class="btn btn-pink btn-sm">
							<span class="ace-icon fa fa-filter icon-on-right bigger-110"></span>&nbsp;&nbsp;
							高级查询
						</button>
					</span>
				</div>
			</div><!-- /.col -->
			<div class="col-xs-12">				
				<div id="t_bi_mrbaseinfo_grid_box" class="widget-box widget-color-blue">
					<div class="widget-header">
						<h5 class="widget-title"><i class="ace-icon fa fa-list"></i>矿业权基本信息</h5>
						<div class="widget-toolbar">
							<a href="#" data-action="fullscreen" class="white">
								<i class="ace-icon fa fa-expand"></i>
							</a>
							<a href="#" id="t_bi_mrbaseinfo_grid_box_filter" class="white" data-action="filter">
								<i class="ace-icon fa fa-filter bigger"></i>
							</a>
							<a href="#" data-action="collapse" class="white">
								<i class="ace-icon fa fa-chevron-up"></i>
							</a>
						</div>
						<div id="t_bi_mrbaseinfo_grid_btn_add" class="widget-toolbar no-border">
							<button class="btn btn-xs btn-primary bigger no-border">
								<i class="ace-icon fa fa-plus"></i>
								新增采矿权
							</button>
						</div>
						<div id="btn_add_tkq" class="widget-toolbar no-border">
							<button class="btn btn-xs btn-primary bigger no-border">
								<i class="ace-icon fa fa-plus"></i>
								新增探矿权
							</button>
						</div>
					</div>

					<div class="widget-body">
						<div class="widget-main no-padding">
							<table id="t_bi_mrbaseinfo_grid"></table>
					
							<div id="t_bi_mrbaseinfo_grid_pager"></div>
						</div>
					</div>
				</div>
			</div><!-- /.col -->
			<div class="col-xs-12">				
				<div id="t_bi_mrbaseinfofee_grid_box" class="widget-box widget-color-blue">
					<div class="widget-header">
						<h5 class="widget-title"><i class="ace-icon fa fa-list"></i>矿业权资金信息</h5>
						<div class="widget-toolbar">
							<a href="#" data-action="fullscreen" class="white">
								<i class="ace-icon fa fa-expand"></i>
							</a>
							<a href="#" id="t_bi_mrbaseinfofee_grid_box_filter" class="white" data-action="filter">
								<i class="ace-icon fa fa-filter bigger"></i>
							</a>
							<a href="#" data-action="collapse" class="white">
								<i class="ace-icon fa fa-chevron-up"></i>
							</a>
						</div>
						<div id="t_bi_mrbaseinfofee_grid_btn_add" class="widget-toolbar no-border">
							<button class="btn btn-xs btn-primary bigger no-border">
								<i class="ace-icon fa fa-plus"></i>
								新增
							</button>
						</div>
					</div>

					<div class="widget-body">
						<div class="widget-main no-padding">
							<table id="t_bi_mrbaseinfofee_grid"></table>
					
							<div id="t_bi_mrbaseinfofee_grid_pager"></div>
						</div>
					</div>
				</div>
			</div><!-- /.col -->	
			<!-- PAGE CONTENT ENDS -->
		</div><!-- /.row -->
	</body>
<%@include file="/webpage/pub/biz/footer.jsp" %>
<script type="text/javascript" src="<%=request.getContextPath()%>/webpage/biz/bi/mrbaseinfo/bizgrid.js"></script>
</html>