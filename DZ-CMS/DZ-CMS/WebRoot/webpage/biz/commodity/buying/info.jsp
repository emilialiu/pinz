<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/webpage/pub/biz/headinfo.jsp" %>
<body>
<div id="dz_commodity_buying_info" >
	<form id="dz_commodity_buying_form" action="<%=request.getContextPath()%>/webpage/biz/commodity/buying/doAddSave.action" method="post" class="form-horizontal">
		<input type="hidden" id="id" name="id" value="${bean.id}"/>

			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-3 no-padding-right"
					for="activity_name">活动名称</label>
				<div class="col-xs-12 col-sm-9">
					<div class="col-sm-6">
						<!-- class="clearfix" -->
						<input type="text" name="activity_name" id="activity_name"
							value="${bean.activity_name}" class="col-xs-12" />
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-3 no-padding-right"
					for="start_time">活动开始时间</label>
				<div class="col-xs-12 col-sm-9">
					<div class="col-sm-6">
						<!-- class="clearfix" -->
						<div class="input-group">
							<input type="text" name="start_time" id="start_time"
								value="${bean.start_time}" class="form-control"
								onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" /><span
								class="input-group-addon"><i
								class="ace-icon fa fa-calendar"></i> </span>
						</div>
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-3 no-padding-right"
					for="end_time">活动结束时间</label>
				<div class="col-xs-12 col-sm-9">
					<div class="col-sm-6">
						<!-- class="clearfix" -->
						<div class="input-group">
							<input type="text" name="end_time" id="end_time"
								value="${bean.end_time}" class="form-control"
								onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" /><span
								class="input-group-addon"><i
								class="ace-icon fa fa-calendar"></i> </span>
						</div>
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-3 no-padding-right"
					for="">是否启用</label>
				<div class="col-xs-12 col-sm-9">
					<div class="col-sm-6">
						<!-- class="clearfix" -->
						<select id="is_start" class="col-xs-12" >
							<option value="0">是</option>
							<option value="1">否</option>
						</select>
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>

		</form>
</div>
</body>
<%@include file="/webpage/pub/biz/footerinfo.jsp" %>
<script type="text/javascript" src="<%=request.getContextPath()%>/webpage/biz/commodity/buying/info.js"></script>
<script type="text/javascript">
		var id = '<%=request.getAttribute("bean.id")%>';
		var is_start = '<%=request.getAttribute("bean.is_start")%>';
	var actiontype = '<%=request.getAttribute("actiontype")%>';
	function onSave() {
		if(actiontype=='addsave')
			addSave("dz_commodity_buying");
		else if(actiontype=='modifysave')
			modifySave("dz_commodity_buying");
		else if(actiontype=='deletesave')
			deleteSave("dz_commodity_buying");
	}
	initvalid("dz_commodity_buying");
	$(function(){
	  $("#is_start  option[value='"+is_start+"']").attr("selected","selected");
		if(actiontype=='view')
			parent.setDialogButtons("dz_commodity_buying",{"取消": function() {parent.closewin("dz_commodity_buying");}});
		else 
			parent.setDialogButtons("dz_commodity_buying",{"取消": function() {parent.closewin("dz_commodity_buying");},"确定": function() {parent.window.frames["dz_commodity_buying_frame"].onSave();}});
	});
	
</script>
</html>