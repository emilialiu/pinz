<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/webpage/pub/biz/head.jsp" %>
		<title>运行中的流程</title>
<body>
		<div class="row">
			<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS -->				
				<div class="input-group col-sm-8">
					<span class="input-group-addon">
						<i class="ace-icon fa fa-search"></i>
					</span>					
					<input type="text" id="act_processinstance_text_search" class="form-control search-query" placeholder="<s:text name="dmmes.sys.processinstance.search" />" />
					<span class="input-group-btn">
						<button id="act_processinstance_btn_search" type="button" class="btn btn-primary btn-sm">
							<span class="ace-icon fa fa-search icon-on-right bigger-110"></span>&nbsp;&nbsp;
							<s:text name="button.query" />
						</button>
						<button id="act_processinstance_btn_advsearch" style="margin-left:10px;" class="btn btn-primary btn-sm">
							<span class="ace-icon fa fa-filter icon-on-right bigger-110"></span>&nbsp;&nbsp;
							<s:text name="button.Advancedquery" />
						</button>
					</span>
				</div>
			</div><!-- /.col -->
			<div class="col-xs-12">				
				<div id="act_processinstance_grid_box" class="widget-box widget-color-blue">
					<div class="widget-header">
						<h5 class="widget-title"><i class="ace-icon fa fa-list"></i><s:text name="dmmes.sys.processinstance.title" /></h5>
						<div class="widget-toolbar">
							<a href="#" data-action="fullscreen" class="white">
								<i class="ace-icon fa fa-expand"></i>
							</a>
							<a href="#" id="act_processinstance_grid_box_filter" class="white" data-action="filter">
								<i class="ace-icon fa fa-filter bigger"></i>
							</a>
							<a href="#" data-action="collapse" class="white">
								<i class="ace-icon fa fa-chevron-up"></i>
							</a>
						</div>
						
					</div>

					<div class="widget-body">
						<div class="widget-main no-padding">
							<table id="act_processinstance_grid"></table>
							<div id="act_processinstance_grid_pager"></div>
						</div>
					</div>
				</div>
			</div><!-- /.col -->
				<!-- PAGE CONTENT ENDS -->
		</div><!-- /.row -->
</body>
<script>
	var pub_operation='<s:text name="pub.operation" />';
	var sys_processinstance_processname='<s:text name="dmmes.sys.processinstance.processname" />';
	var sys_processinstance_ywname= '<s:text name="dmmes.sys.processinstance.ywname" />';
	var sys_processinstance_appusername= '<s:text name="dmmes.sys.processinstance.appusername" />';
	var sys_processinstance_taskname= '<s:text name="dmmes.sys.processinstance.taskname" />';
	var sys_processinstance_createtime= '<s:text name="dmmes.sys.processinstance.createtime" />'; 
	var sys_processinstance_suspensionState= '<s:text name="dmmes.sys.processinstance.suspensionState" />';
	var sys_processinstance_search =  '<s:text name="dmmes.sys.processinstance.search" />';
	var sys_processinstance_title = '<s:text name="dmmes.sys.processinstance.title" />';
	var sys_processinstance_btnHangup = '<s:text name="dmmes.sys.processinstance.btnHangup" />';
	var sys_processinstance_currentnode = '<s:text name="dmmes.sys.processinstance.currentnode"/>';
	var sys_processinstance_activate = '<s:text name="dmmes.sys.processinstance.activate"/>';
	var sys_processinstance_viewflowchart= '<s:text name="dmmes.sys.processinstance.viewflowchart"/>';
	var sys_processinstance_yes = '<s:text name="dmmes.sys.processinstance.yes"/>';
	var sys_processinstance_no = '<s:text name="dmmes.sys.processinstance.no"/>';
	var sys_processinstance_processtracking = '<s:text name="dmmes.sys.processinstance.processtracking"/>';
	var pub_messageajaxerror = '<s:text name="pub.messageajaxerror"/>'; 
	var operationfaile = '<s:text name="operationfaile"/>'; 
</script>
<%@include file="/webpage/pub/biz/footer.jsp" %>
<script type="text/javascript" src="<%=request.getContextPath()%>/webpage/manager/activity/processinstance/bizgrid.js"></script>



