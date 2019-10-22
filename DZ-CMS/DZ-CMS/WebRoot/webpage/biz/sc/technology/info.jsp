<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/webpage/pub/biz/headinfo.jsp" %>
<div id="t_sc_technology_info" >
	<form id="t_sc_technology_form" action="<%=request.getContextPath()%>/webpage/biz/sc/technology/doAddSave.action" method="post" class="form-horizontal">
		<input type="hidden" id="techid" name="techid" value="${bean.techid}"/>
		
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="techname">工艺名称</label><div class="col-xs-12 col-sm-9"><div class="col-sm-6"><!-- class="clearfix" --><input type="text" name="techname" id="techname" value="${bean.techname}" datatype="*" nullmsg="工艺名称信息必填!" class="col-xs-12" /></div><div class="col-sm-3"><div class="info"><span class="Validform_checktip"></span><span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s></span></div></div></div>
		</div>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="techcode">工艺编码</label><div class="col-xs-12 col-sm-9"><div class="col-sm-6"><!-- class="clearfix" --><input type="text" name="techcode" id="techcode" value="${bean.techcode}" datatype="*" nullmsg="工艺编码信息必填!" class="col-xs-12" /></div><div class="col-sm-3"><div class="info"><span class="Validform_checktip"></span><span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s></span></div></div></div>
		</div>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="parentid">上级工艺</label><div class="col-xs-12 col-sm-9"><div class="col-sm-6"><!-- class="clearfix" --><input type="text" name="parentid" id="parentid" value="${bean.parentid}" datatype="*" nullmsg="上级工艺信息必填!" class="col-xs-12" /></div><div class="col-sm-3"><div class="info"><span class="Validform_checktip"></span><span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s></span></div></div></div>
		</div>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="isplan">计划阶段使用：0表示不使用，1表示使用</label><div class="col-xs-12 col-sm-9"><div class="checkbox col-sm-6"><!-- class="clearfix" --><label><input type="checkbox" name="isplan" id="isplan" value="" datatype="*" nullmsg="计划阶段使用：0表示不使用，1表示使用信息必填!" class="ace col-xs-12" /><span class="lbl">计划阶段使用：0表示不使用，1表示使用</span></label></div><div class="col-sm-3"><div class="info"><span class="Validform_checktip"></span><span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s></span></div></div></div>
		</div>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="isaccept">验收阶段使用：0表示不使用，1表示使用</label><div class="col-xs-12 col-sm-9"><div class="checkbox col-sm-6"><!-- class="clearfix" --><label><input type="checkbox" name="isaccept" id="isaccept" value="" datatype="*" nullmsg="验收阶段使用：0表示不使用，1表示使用信息必填!" class="ace col-xs-12" /><span class="lbl">验收阶段使用：0表示不使用，1表示使用</span></label></div><div class="col-sm-3"><div class="info"><span class="Validform_checktip"></span><span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s></span></div></div></div>
		</div>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="serialno">序号</label><div class="col-xs-12 col-sm-9"><div class="col-sm-6"><!-- class="clearfix" --><input type="text" name="serialno" id="serialno" value="${bean.serialno}" class="col-xs-12" /></div><div class="col-sm-3"></div></div>
		</div>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="memo">备注</label><div class="col-xs-12 col-sm-9"><div class="col-sm-6"><!-- class="clearfix" --><textarea name="memo" id="memo" class="col-xs-12" ></textarea></div><div class="col-sm-3"></div></div>
		</div>
		
	</form>
</div>
<%@include file="/webpage/pub/biz/footerinfo.jsp" %>
<script type="text/javascript" src="<%=request.getContextPath()%>/webpage/biz/sc/technology/info.js"></script>
<script type="text/javascript">
		var techid = '<%=request.getAttribute("bean.techid")%>';
	var actiontype = '<%=request.getAttribute("actiontype")%>';
	function onSave() {
		if(actiontype=='addsave')
			addSave("t_sc_technology");
		else if(actiontype=='modifysave')
			modifySave("t_sc_technology");
		else if(actiontype=='deletesave')
			deleteSave("t_sc_technology");
	}
	initvalid("t_sc_technology");
	$(function(){
		if(actiontype=='view')
			parent.setDialogButtons("t_sc_technology",{"取消": function() {parent.closewin("t_sc_technology");}});
		else 
			parent.setDialogButtons("t_sc_technology",{"取消": function() {parent.closewin("t_sc_technology");},"确定": function() {parent.window.frames["t_sc_technology_frame"].onSave();}});
	});
</script>