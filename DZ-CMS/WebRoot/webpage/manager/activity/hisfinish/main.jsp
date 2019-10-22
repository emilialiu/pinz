<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/webpage/pub/biz/head.jsp" %>
		<title>历史流程列表</title>
		<div class="row">
			<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS -->				
				<div class="input-group col-sm-8">
					<span class="input-group-addon">
						<i class="ace-icon fa fa-search"></i>
					</span>					
					<input type="text" id="act_hisfinish_text_search" class="form-control search-query" placeholder="<s:text name="dmmes.sys.hisfinish.search" />" />
					<span class="input-group-btn">
						<button id="act_hisfinish_btn_search" type="button" class="btn btn-primary btn-sm">
							<span class="ace-icon fa fa-search icon-on-right bigger-110"></span>&nbsp;&nbsp;
							<s:text name="button.query" />
						</button>
						<button id="act_hisfinish_btn_advsearch" style="margin-left:10px;" class="btn btn-primary btn-sm">
							<span class="ace-icon   fa fa-filter icon-on-right bigger-110"></span>&nbsp;&nbsp;
							<s:text name="button.Advancedquery" />
						</button>
					</span>
				</div>
			</div><!-- /.col -->
			<div class="col-xs-12">				
				<div id="act_hisfinish_grid_box" class="widget-box widget-color-blue">
					<div class="widget-header">
						<h5 class="widget-title"><i class="ace-icon fa fa-list"></i><s:text name="dmmes.sys.hisfinish.title" /></h5>
						<div class="widget-toolbar">
							<a href="#" data-action="fullscreen" class="white">
								<i class="ace-icon fa fa-expand"></i>
							</a>
							<a href="#" id="act_hisfinish_grid_box_filter" class="white" data-action="filter">
								<i class="ace-icon fa fa-filter bigger"></i>
							</a>
							<a href="#" data-action="collapse" class="white">
								<i class="ace-icon fa fa-chevron-up"></i>
							</a>
						</div>
					</div>

					<div class="widget-body">
						<div class="widget-main no-padding">
							<table id="act_hisfinish_grid"></table>
							<div id="act_hisfinish_grid_pager"></div>
						</div>
					</div>
				</div>
			</div><!-- /.col -->
			
			
			
				
				<!-- PAGE CONTENT ENDS -->
		</div><!-- /.row -->
<%@include file="/webpage/pub/biz/footer.jsp" %>
<script>
var sys_hisfinish_id = '<s:text name="dmmes.sys.hisfinish.id" />';
var sys_hisfinish_processDefinitionId ='<s:text name="dmmes.sys.hisfinish.processDefinitionId" />';
var sys_hisfinish_startTime = '<s:text name="dmmes.sys.hisfinish.startTime" />';
var sys_hisfinish_endTime = '<s:text name="dmmes.sys.hisfinish.endTime" />';
var sys_hisfinish_deleteReason = '<s:text name="dmmes.sys.hisfinish.deleteReason" />';
</script>

<script type="text/javascript" src="<%=request.getContextPath()%>/webpage/manager/activity/hisfinish/bizgrid.js"></script>
