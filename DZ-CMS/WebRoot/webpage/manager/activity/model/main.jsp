<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/webpage/pub/biz/head.jsp" %>
		<title>新建流程</title>
		<div class="row">
			<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS -->				
				<div class="input-group col-sm-8">
					<span class="input-group-addon">
						<i class="ace-icon fa fa-search"></i>
					</span>					
					<input type="text" id="act_model_text_search" class="form-control search-query" placeholder="<s:text name="dmmes.sys.model.search" />" />
					<span class="input-group-btn">
						<button id="act_model_btn_search" type="button" class="btn btn-primary btn-sm">
							<span class="ace-icon fa fa-search icon-on-right bigger-110"></span>&nbsp;&nbsp;
							<s:text name="button.query" />
						</button>
						<button id="act_model_btn_advsearch" style="margin-left:10px;" class="btn btn-primary btn-sm">
							<span class="ace-icon fa fa-filter icon-on-right bigger-110"></span>&nbsp;&nbsp;
							<s:text name="button.Advancedquery" />
						</button>
					</span>
				</div>
			</div><!-- /.col -->
			<div class="col-xs-12">				
				<div id="act_model_grid_box" class="widget-box widget-color-blue">
					<div class="widget-header">
						<h5 class="widget-title"><i class="ace-icon fa fa-list"></i><s:text name="dmmes.sys.model.title" /></h5>
						<div class="widget-toolbar">
							<a href="#" data-action="fullscreen" class="white">
								<i class="ace-icon fa fa-expand"></i>
							</a>
							<a href="#" id="act_model_grid_box_filter" class="white" data-action="filter">
								<i class="ace-icon fa fa-filter bigger"></i>
							</a>
							<a href="#" data-action="collapse" class="white">
								<i class="ace-icon fa fa-chevron-up"></i>
							</a>
						</div>
						<div id="act_model_grid_btn_add" class="widget-toolbar no-border">
							<button class="btn btn-xs btn-primary bigger no-border">
								<i class="ace-icon fa fa-plus"></i>
								<s:text name="button.add" />
							</button>
						</div>
							<div id="act_workflow_grid_btn" class="widget-toolbar no-border">
							<button class="btn btn-xs btn-primary bigger no-border" onclick="doimport()">
								<i class="ace-icon fa fa-plus"></i>
									<s:text name="dmmes.sys.model.btnimport" />
							</button>
						</div>
					</div>

					<div class="widget-body">
						<div class="widget-main no-padding">
							<table id="act_model_grid"></table>
							<div id="act_model_grid_pager"></div>
						</div>
					</div>
				</div>
			</div><!-- /.col -->
				<!-- PAGE CONTENT ENDS -->
		</div><!-- /.row -->
<%@include file="/webpage/pub/biz/footer.jsp" %>
<script type="text/javascript">
	var pub_operation='<s:text name="pub.operation" />';
	var sys_model_id = '<s:text name="dmmes.sys.model.id" />';
	var sys_model_key = '<s:text name="dmmes.sys.model.key" />';
	var sys_model_name = '<s:text name="dmmes.sys.model.name" />';
	var sys_model_version = '<s:text name="dmmes.sys.model.version" />';
	var sys_model_createTime = '<s:text name="dmmes.sys.model.createTime" />';
	var sys_model_lastUpdateTime = '<s:text name="dmmes.sys.model.lastUpdateTime" />';
	var sys_model_btnedit = '<s:text name="dmmes.sys.model.btnedit" />';
	var sys_model_deploy = '<s:text name="dmmes.sys.model.deploy" />';
	var sys_model_export = '<s:text name="dmmes.sys.model.export" />';
	var button_delete = '<s:text name="button.delete"/>';
</script>
<script type="text/javascript" src="<%=request.getContextPath()%>/plugins/jquery/ajaxfileupload.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/webpage/manager/activity/model/bizgrid.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/webpage/manager/activity/model/info.js"></script>
