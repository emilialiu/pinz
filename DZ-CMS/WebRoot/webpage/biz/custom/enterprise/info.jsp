<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/webpage/pub/biz/headinfo.jsp" %>
<body>
<div id="dz_custom_enterprise_info" >
	<form id="dz_custom_enterprise_form" action="<%=request.getContextPath()%>/webpage/biz/custom/enterprise/doAddSave.action" method="post" class="form-horizontal">
		<input type="hidden" id="id" name="id" value="${bean.id}"/>
		
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="name">姓名</label><div class="col-xs-12 col-sm-9"><div class="col-sm-6"><!-- class="clearfix" --><input type="text" name="name" id="name" value="${bean.name}" class="col-xs-12" /></div><div class="col-sm-3"></div></div>
		</div>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="phone">手机号码</label><div class="col-xs-12 col-sm-9"><div class="col-sm-6"><!-- class="clearfix" --><input type="text" name="phone" id="phone" value="${bean.phone}" class="col-xs-12" /></div><div class="col-sm-3"></div></div>
		</div>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="description">团购意向：什么类型，多少预算</label><div class="col-xs-12 col-sm-9"><div class="col-sm-6"><!-- class="clearfix" --><textarea name="description" id="description" class="col-xs-12" ></textarea></div><div class="col-sm-3"></div></div>
		</div>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="remarks">说明</label><div class="col-xs-12 col-sm-9"><div class="col-sm-6"><!-- class="clearfix" --><textarea name="remarks" id="remarks" class="col-xs-12" ></textarea></div><div class="col-sm-3"></div></div>
		</div>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="demand_count">需求数量,数量由数据字典提供区间值</label><div class="col-xs-12 col-sm-9"><div class="col-sm-6"><!-- class="clearfix" --><input type="text" name="demand_count" id="demand_count" value="${bean.demand_count}" class="col-xs-12" /></div><div class="col-sm-3"></div></div>
		</div>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="create_time">定制时间</label><div class="col-xs-12 col-sm-9"><div class="col-sm-6"><!-- class="clearfix" --><div class="input-group"><input type="text" name="create_time" id="create_time" value="${bean.create_time}" class="form-control" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" /><span class="input-group-addon"><i class="ace-icon fa fa-calendar"></i> </span></div></div><div class="col-sm-3"></div></div>
		</div>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="customer_id">定制客户id</label><div class="col-xs-12 col-sm-9"><div class="col-sm-6"><!-- class="clearfix" --><input type="text" name="customer_id" id="customer_id" value="${bean.customer_id}" class="col-xs-12" /></div><div class="col-sm-3"></div></div>
		</div>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="state">处理状态</label><div class="col-xs-12 col-sm-9"><div class="col-sm-6"><!-- class="clearfix" --><input type="text" name="state" id="state" value="${bean.state}" class="col-xs-12" /></div><div class="col-sm-3"></div></div>
		</div>
		
	</form>
</div>
</body>
<%@include file="/webpage/pub/biz/footerinfo.jsp" %>
<script type="text/javascript" src="<%=request.getContextPath()%>/webpage/biz/custom/enterprise/info.js"></script>
<script type="text/javascript">
		var id = '<%=request.getAttribute("bean.id")%>';
	var actiontype = '<%=request.getAttribute("actiontype")%>';
	function onSave() {
		if(actiontype=='addsave')
			addSave("dz_custom_enterprise");
		else if(actiontype=='modifysave')
			modifySave("dz_custom_enterprise");
		else if(actiontype=='deletesave')
			deleteSave("dz_custom_enterprise");
	}
	initvalid("dz_custom_enterprise");
	$(function(){
		if(actiontype=='view')
			parent.setDialogButtons("dz_custom_enterprise",{"取消": function() {parent.closewin("dz_custom_enterprise");}});
		else 
			parent.setDialogButtons("dz_custom_enterprise",{"取消": function() {parent.closewin("dz_custom_enterprise");},"确定": function() {parent.window.frames["dz_custom_enterprise_frame"].onSave();}});
	});
</script>
</html>