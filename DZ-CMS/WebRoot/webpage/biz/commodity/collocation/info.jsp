<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/webpage/pub/biz/headinfo.jsp" %>
<body>
<div id="dz_commodity_collocation_info" >
	<form id="dz_commodity_collocation_form" action="<%=request.getContextPath()%>/webpage/biz/commodity/collocation/doAddSave.action" method="post" class="form-horizontal">
		<input type="hidden" id="id" name="id" value="${bean.id}"/>
		
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="banner_url">banner图路径</label><div class="col-xs-12 col-sm-9"><div class="col-sm-6"><!-- class="clearfix" --><textarea name="banner_url" id="banner_url" class="col-xs-12" ></textarea></div><div class="col-sm-3"></div></div>
		</div>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="designer_said">设计师说明内容</label><div class="col-xs-12 col-sm-9"><div class="col-sm-6"><!-- class="clearfix" --><textarea name="designer_said" id="designer_said" class="col-xs-12" ></textarea></div><div class="col-sm-3"></div></div>
		</div>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="single_ids">单品组成ids，用逗号分隔</label><div class="col-xs-12 col-sm-9"><div class="col-sm-6"><!-- class="clearfix" --><input type="text" name="single_ids" id="single_ids" value="${bean.single_ids}" class="col-xs-12" /></div><div class="col-sm-3"></div></div>
		</div>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="remarks">备注</label><div class="col-xs-12 col-sm-9"><div class="col-sm-6"><!-- class="clearfix" --><textarea name="remarks" id="remarks" class="col-xs-12" ></textarea></div><div class="col-sm-3"></div></div>
		</div>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="create_time">创建时间</label><div class="col-xs-12 col-sm-9"><div class="col-sm-6"><!-- class="clearfix" --><div class="input-group"><input type="text" name="create_time" id="create_time" value="${bean.create_time}" class="form-control" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" /><span class="input-group-addon"><i class="ace-icon fa fa-calendar"></i> </span></div></div><div class="col-sm-3"></div></div>
		</div>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="create_by">创建人</label><div class="col-xs-12 col-sm-9"><div class="col-sm-6"><!-- class="clearfix" --><input type="text" name="create_by" id="create_by" value="${bean.create_by}" class="col-xs-12" /></div><div class="col-sm-3"></div></div>
		</div>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="update_time">修改时间</label><div class="col-xs-12 col-sm-9"><div class="col-sm-6"><!-- class="clearfix" --><div class="input-group"><input type="text" name="update_time" id="update_time" value="${bean.update_time}" class="form-control" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" /><span class="input-group-addon"><i class="ace-icon fa fa-calendar"></i> </span></div></div><div class="col-sm-3"></div></div>
		</div>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="update_by">修改人</label><div class="col-xs-12 col-sm-9"><div class="col-sm-6"><!-- class="clearfix" --><input type="text" name="update_by" id="update_by" value="${bean.update_by}" class="col-xs-12" /></div><div class="col-sm-3"></div></div>
		</div>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="designer_name">设计师名称</label><div class="col-xs-12 col-sm-9"><div class="col-sm-6"><!-- class="clearfix" --><input type="text" name="designer_name" id="designer_name" value="${bean.designer_name}" class="col-xs-12" /></div><div class="col-sm-3"></div></div>
		</div>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="designer_head_url">设计师头像</label><div class="col-xs-12 col-sm-9"><div class="col-sm-6"><!-- class="clearfix" --><textarea name="designer_head_url" id="designer_head_url" class="col-xs-12" ></textarea></div><div class="col-sm-3"></div></div>
		</div>
		
	</form>
</div>
</body>
<%@include file="/webpage/pub/biz/footerinfo.jsp" %>
<script type="text/javascript" src="<%=request.getContextPath()%>/webpage/biz/commodity/collocation/info.js"></script>
<script type="text/javascript">
		var id = '<%=request.getAttribute("bean.id")%>';
	var actiontype = '<%=request.getAttribute("actiontype")%>';
	function onSave() {
		if(actiontype=='addsave')
			addSave("dz_commodity_collocation");
		else if(actiontype=='modifysave')
			modifySave("dz_commodity_collocation");
		else if(actiontype=='deletesave')
			deleteSave("dz_commodity_collocation");
	}
	initvalid("dz_commodity_collocation");
	$(function(){
		if(actiontype=='view')
			parent.setDialogButtons("dz_commodity_collocation",{"取消": function() {parent.closewin("dz_commodity_collocation");}});
		else 
			parent.setDialogButtons("dz_commodity_collocation",{"取消": function() {parent.closewin("dz_commodity_collocation");},"确定": function() {parent.window.frames["dz_commodity_collocation_frame"].onSave();}});
	});
</script>
</html>