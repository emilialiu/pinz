<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/webpage/pub/biz/head.jsp" %>
<div id="act_workflow_his_info" >   
	<div class="col-xs-12">
		
<div id="act_workflow_his_grid_box" class="widget-box widget-color-blue">
			<div class="widget-body">
				<div class="widget-main no-padding">
					<table id="act_workflow_his_grid"></table>
			
					<div id="act_workflow_his_grid_pager"></div>
				</div>
			</div>
		</div>
		
	</div>
	</div>
<%@include file="/webpage/pub/biz/footer.jsp" %>
<script>
	var key = '<%=request.getParameter("key")%>';
	var sys_workflow_name = '<s:text name="dmmes.sys.workflow.title" />';
	var sys_workflow_version = '<s:text name="dmmes.sys.workflow.title" />';
	var sys_workflow_diagramResourceName = '<s:text name="dmmes.sys.workflow.title" />';
	var sys_workflow_deploymentTime = '<s:text name="dmmes.sys.workflow.title" /> ';
	var sys_workflow_suspended = '<s:text name="dmmes.sys.workflow.title" />';
	var sys_workflow_title = '<s:text name="dmmes.sys.workflow.title" />';
	var sys_workflow_search = '<s:text name="dmmes.sys.workflow.title" />';
	var pub_operation = '<s:text name="pub.operation" />';
	var sys_workflow_viewxml = '<s:text name="dmmes.sys.workflow.viewxml" />';
	var sys_workflow_viewpic = '<s:text name="dmmes.sys.workflow.viewpic" />';
</script>
<script type="text/javascript" src="<%=request.getContextPath()%>/webpage/manager/activity/workflow/hisbizgrid.js"></script>