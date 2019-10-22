<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/webpage/pub/biz/headinfo.jsp" %>
<div id="act_model_info" >
	<form id="act_model_form" method="post" class="form-horizontal" action="<%=request.getContextPath()%>/manager/model/doAddSave.action" method="post" >
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right"
				for="name">
				名称:
			</label>
			<div class="col-xs-12 col-sm-9">
				<div class="col-sm-6""><!-- class="clearfix" -->
					<input type="text" name="name" datatype="*" nullmsg="名称必填!" maxlength="50" id="name" class="col-xs-12" />
				</div>
				<div class="col-sm-3">
					<div class="info"><span class="Validform_checktip"></span><span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s></span></div>
				</div>
			</div>
		</div>
		
		<div class="space-2"></div>
		
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right"
				for="key">
				KEY:
			</label>
			<div class="col-xs-12 col-sm-9">
				<div class="col-sm-6""><!-- class="clearfix" -->
					<input type="text" name="key" datatype="*" nullmsg="key必填!" maxlength="50" id="key" class="col-xs-12" />
				</div>
				<div class="col-sm-3">
					<div class="info"><span class="Validform_checktip"></span><span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s></span></div>
				</div>
			</div>
		</div>
		
		<div class="space-2"></div>
		
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right"
				for="description">
				描述:
			</label>
			<div class="col-xs-12 col-sm-9">
				<div class="col-sm-6"><!-- class="clearfix" -->
					<textarea name="description" id="description" class="col-sm-12"></textarea>
				</div>
			</div>
		</div>
	</form>
</div>
<%@include file="/webpage/pub/biz/footerinfo.jsp" %>
<script type="text/javascript" src="<%=request.getContextPath()%>/webpage/manager/activity/model/info.js"></script>
<script type="text/javascript">
	var actiontype = '<%=request.getAttribute("actiontype")%>';
	function onSave() {
		if(actiontype=='addsave')
			addSave("act_model");
		else if(actiontype=='modifysave')
			modifySave("act_model");
		else if(actiontype=='deletesave')
			deleteSave("act_model");
	}
	initvalid("act_model");
	$(function(){
		if(actiontype=='view')
			parent.setDialogButtons("act_model",{"取消": function() {parent.closewin("act_model");}});
		else 
			parent.setDialogButtons("act_model",{"取消": function() {parent.closewin("act_model");},"确定": function() {parent.window.frames["act_model_frame"].onSave();}});
	});
</script>