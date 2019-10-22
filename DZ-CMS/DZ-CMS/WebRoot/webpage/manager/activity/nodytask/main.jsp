<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/webpage/pub/biz/head.jsp" %>
		<title>我的待办</title>
		<div class="row">
			<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS -->				
				<div class="input-group col-sm-8">
					<span class="input-group-addon">
						<i class="ace-icon fa fa-search"></i>
					</span>					
					<input type="text" id="act_nodytask_text_search" class="form-control search-query" placeholder="<s:text name="dmmes.sys.task.search" />" />
					<span class="input-group-btn">
						<button id="act_nodytask_btn_search" type="button" class="btn btn-primary btn-sm">
							<span class="ace-icon fa fa-search icon-on-right bigger-110"></span>&nbsp;&nbsp;
								<s:text name="button.query" />
						</button>
						<button id="act_nodytask_btn_advsearch" style="margin-left:10px;" class="btn btn-primary btn-sm">
							<span class="ace-icon fa fa-filter icon-on-right bigger-110"></span>&nbsp;&nbsp;
							<s:text name="button.Advancedquery" />
						</button>
					</span>
				</div>
			</div><!-- /.col -->
			<div class="col-xs-12">				
				<div id="act_nodytask_grid_box" class="widget-box widget-color-blue">
					<div class="widget-header">
						<h5 class="widget-title"><i class="ace-icon fa fa-list"></i><s:text name="dmmes.sys.task.title" /></h5>
						<div class="widget-toolbar">
							<a href="#" data-action="fullscreen" class="white">
								<i class="ace-icon fa fa-expand"></i>
							</a>
							<a href="#" id="act_nodytask_grid_box_filter" class="white" data-action="filter">
								<i class="ace-icon fa fa-filter bigger"></i>
							</a>
							<a href="#" data-action="collapse" class="white">
								<i class="ace-icon fa fa-chevron-up"></i>
							</a>
						</div>
						
					</div>
					
					<div class="widget-body">
						<div class="widget-main no-padding">
							<table id="act_nodytask_grid"></table>
							<div id="act_nodytask_grid_pager"></div>
						</div>
					</div>
				</div>
			</div><!-- /.col -->
				<!-- PAGE CONTENT ENDS -->
			
		</div><!-- /.row -->
<%@include file="/webpage/pub/biz/footer.jsp" %>
<script type="text/javascript">
	var id = "<%=request.getParameter("id")%>";
	var pub_operation='<s:text name="pub.operation" />';
	var sys_task_id = '<s:text name="dmmes.sys.task.id"/>';
	var sys_task_taskDefinitionKey = '<s:text name="dmmes.sys.task.taskDefinitionKey"/>';
	var sys_task_processname = '<s:text name="dmmes.sys.task.processname"/>';
	var sys_task_ywname = '<s:text name="dmmes.sys.task.ywname"/>';
	var sys_task_appusername = '<s:text name="dmmes.sys.task.appusername"/>';
	var sys_task_nameWithoutCascade = '<s:text name="dmmes.sys.task.nameWithoutCascade"/>';
	var sys_task_createTime = '<s:text name="dmmes.sys.task.createTime"/>';
	var sys_task_claim = '<s:text name="dmmes.sys.task.claim"/>';
	var sys_task_handle = '<s:text name="dmmes.sys.task.handle"/>';
	var button_cancel = '<s:text name="button.cancel"/>';
	var button_submit = '<s:text name="button.submit"/>';
	var pub_messageajaxerror = '<s:text name="pub.messageajaxerror"/>';
</script>
<script type="text/javascript" src="<%=request.getContextPath()%>/webpage/manager/activity/nodytask/bizgrid.js"></script>