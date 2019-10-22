		<title>${title}</title>
		<div class="row">
			<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS -->				
				<div class="input-group col-sm-8">
					<span class="input-group-addon">
						<i class="ace-icon fa fa-search"></i>
					</span>					
					<input type="text" id="${actionName ?lower_case}_text_search" class="form-control search-query" placeholder="${queryName}" />
					<span class="input-group-btn">
						<button id="${actionName ?lower_case}_btn_search" type="button" class="btn btn-purple btn-sm">
							<span class="ace-icon fa fa-search icon-on-right bigger-110"></span>&nbsp;&nbsp;
							查询
						</button>
						<button id="${actionName ?lower_case}_btn_advsearch" style="margin-left:10px;" class="btn btn-pink btn-sm">
							<span class="ace-icon fa fa-filter icon-on-right bigger-110"></span>&nbsp;&nbsp;
							高级查询
						</button>
					</span>
				</div>
			</div><!-- /.col -->
			<div class="col-xs-12">				
				<div id="${actionName ?lower_case}_grid_box" class="widget-box widget-color-blue">
					<div class="widget-header">
						<h5 class="widget-title"><i class="ace-icon fa fa-list"></i>${title}</h5>
						<div class="widget-toolbar">
							<a href="#" data-action="fullscreen" class="orange2">
								<i class="ace-icon fa fa-expand"></i>
							</a>
							<a href="#" id="${actionName ?lower_case}_grid_box_filter" data-action="filter">
								<i class="ace-icon fa fa-filter bigger red"></i>
							</a>
							<a href="#" data-action="collapse">
								<i class="ace-icon fa fa-chevron-up"></i>
							</a>
						</div>
						<div id="${actionName ?lower_case}_grid_btn_add" class="widget-toolbar no-border">
							<button class="btn btn-xs btn-success bigger no-border">
								<i class="ace-icon fa fa-plus"></i>
								新增
							</button>
						</div>
					</div>

					<div class="widget-body">
						<div class="widget-main no-padding">
							<table id="${actionName ?lower_case}_grid"></table>
					
							<div id="${actionName ?lower_case}_grid_pager"></div>
						</div>
					</div>
				</div>
			</div><!-- /.col -->
				
				<%@ include file="info.jsp"%>
				
				<!-- PAGE CONTENT ENDS -->
		</div><!-- /.row -->
<script type="text/javascript" src="<%=request.getContextPath()%>/${filePath ?lower_case}/info.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/${filePath ?lower_case}/bizgrid.js"></script>
