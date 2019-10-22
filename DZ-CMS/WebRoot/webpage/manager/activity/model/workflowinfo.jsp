<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/webpage/pub/biz/headinfo.jsp" %>
<div id="act_workflow_info" >
	<form id="act_workflow_form" method="post" class="form-horizontal"   action="<%=request.getContextPath()%>/manager/activity/deploy.action" >
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right"
				for="rolename">
				选择文件:
			</label>
			<div class="col-xs-12 col-sm-9">
				<div class="col-sm-6"><!-- class="clearfix" -->
					<input name="file" id="file" type="file" multiple="" datatype="*" nullmsg="上传文件不能为空！"  class="col-xs-12"/>
				</div>
				<div class="col-sm-3">
					<div class="info"><span class="Validform_checktip"></span><span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s></span></div>
				</div>
			</div>
		</div>
		
		<div class="space-2"></div>
		
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right"
				for="acginame">
			</label>
			<div class="col-xs-12 col-sm-9">
				<div class="col-sm-9"><!-- class="clearfix" -->
					(支持文件格式：zip、bar、bpmn、bpmn20.xml)
				</div>
				<div class="col-sm-3">
					<div class="info"><span class="Validform_checktip"></span><span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s></span></div>
				</div>
			</div>
		</div>
	</form>
</div>
<%@include file="/webpage/pub/biz/footerinfo.jsp" %>
<script type="text/javascript" src="<%=request.getContextPath()%>/plugins/jquery/ajaxfileupload.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/webpage/manager/activity/model/info.js"></script>
<script type="text/javascript">
	var actiontype = '<%=request.getAttribute("actiontype")%>';
	initvalid("act_workflow");
	function onSave() {
		wrokAddSave();
	}
	
	$(function(){
		if(actiontype=='view')
			parent.setDialogButtons("act_workflow",{"取消": function() {parent.closewin("act_workflow");}});
		else 
			parent.setDialogButtons("act_workflow",{"取消": function() {parent.closewin("act_workflow");},"确定": function() {parent.window.frames["act_workflow_frame"].onSave();}});
	});
	
</script>