<%@ page language="java" pageEncoding="utf-8"%>
		<title>模板应用表</title>
		<div class="row">
			<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS -->				
				<div class="input-group col-sm-8">
					<span class="input-group-addon">
						<i class="ace-icon fa fa-search"></i>
					</span>					
					<input type="text" id="sys_modelentitymapp_text_search" class="form-control search-query" placeholder="表名称" />
					<span class="input-group-btn">
						<button id="sys_modelentitymapp_btn_search" type="button" class="btn btn-primary btn-sm">
							<span class="ace-icon fa fa-search icon-on-right bigger-110"></span>&nbsp;&nbsp;
							查询
						</button>
						<button id="sys_modelentitymapp_btn_advsearch" style="margin-left:10px;" class="btn btn-primary btn-sm">
							<span class="ace-icon fa fa-filter icon-on-right bigger-110"></span>&nbsp;&nbsp;
							高级查询
						</button>
					</span>
				</div>
			</div><!-- /.col -->
			<div class="col-xs-12">				
				<div id="sys_modelentitymapp_grid_box" class="widget-box widget-color-blue">
					<div class="widget-header">
						<h5 class="widget-title"><i class="ace-icon fa fa-list"></i>模板应用表</h5>
						<div class="widget-toolbar">
							<a href="#" data-action="fullscreen" class="white">
								<i class="ace-icon fa fa-expand"></i>
							</a>
							<a href="#" id="sys_modelentitymapp_grid_box_filter" data-action="filter">
								<i class="ace-icon fa fa-filter bigger white"></i>
							</a>
							<a href="#" data-action="collapse" class="white">
								<i class="ace-icon fa fa-chevron-up"></i>
							</a>
						</div>
						<div id="sys_modelentitymapp_grid_btn_add" class="widget-toolbar no-border">
							<button class="btn btn-xs btn-primary bigger no-border">
								<i class="ace-icon fa fa-plus"></i>
								新增
							</button>
						</div>
					</div>

					<div class="widget-body">
						<div class="widget-main no-padding">
							<table id="sys_modelentitymapp_grid"></table>
					
							<div id="sys_modelentitymapp_grid_pager"></div>
						</div>
					</div>
				</div>
			</div><!-- /.col -->
				
				<%@ include file="info.jsp"%>
				
				<!-- PAGE CONTENT ENDS -->
		</div><!-- /.row -->
<script type="text/javascript" src="<%=request.getContextPath()%>/manager/sys/modelentitymapp/bizgrid.js"></script>
