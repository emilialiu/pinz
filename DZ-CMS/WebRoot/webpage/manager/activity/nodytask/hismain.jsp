<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/webpage/pub/biz/head.jsp" %>
<script type="text/javascript">
	var processDefinitionId='<%=request.getParameter("processDefinitionId")%>';
	var bussinesskey='<%=request.getParameter("bussinesskey")%>';
	var sys_task_approvallink = '<s:text name="dmmes.sys.task.approvallink"/>';
	var sys_task_approvalman = '<s:text name="dmmes.sys.task.approvalman"/>';
	var sys_task_approvaltime = '<s:text name="dmmes.sys.task.approvaltime"/>';
	var sys_task_examinationresult = '<s:text name="dmmes.sys.task.examinationresult"/>';
	var sys_task_approvalcomments = '<s:text name="dmmes.sys.task.approvalcomments"/>';
</script>
<div class="row" style="margin:0px;">
	<div class="col-xs-12">	
		<div id="act_nodytaskhis_grid_box" class="widget-box widget-color-blue">
			<div class="widget-body">
				<div class="widget-main no-padding">	
				 	<table id="act_nodytaskhis_grid"></table>
			     	<div id="act_nodytaskhis_grid_pager"></div>
				</div>
			</div>
		</div>
	</div> 
</div>
<%@include file="/webpage/pub/biz/footer.jsp" %>
<script type="text/javascript" src="<%=request.getContextPath()%>/webpage/manager/activity/nodytask/hismain.js"></script>

