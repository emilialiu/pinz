<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/webpage/pub/biz/headinfo.jsp" %>
<body>
<div id="dz_coupon_user_record_info" >
	<form id="dz_coupon_user_record_form" action="<%=request.getContextPath()%>/webpage/biz/coupon/user/doAddSave.action" method="post" class="form-horizontal">
		<input type="hidden" id="id" name="id" value="${bean.id}"/>
		
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="create_time">使用时间</label><div class="col-xs-12 col-sm-9"><div class="col-sm-6"><!-- class="clearfix" --><input type="text" name="create_time" id="create_time" value="${bean.create_time}" class="col-xs-12" /></div><div class="col-sm-3"></div></div>
		</div>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="state">状态</label><div class="col-xs-12 col-sm-9"><div class="col-sm-6"><!-- class="clearfix" --><input type="text" name="state" id="state" value="${bean.state}" class="col-xs-12" /></div><div class="col-sm-3"></div></div>
		</div>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="expire_time">过期时间</label><div class="col-xs-12 col-sm-9"><div class="col-sm-6"><!-- class="clearfix" --><div class="input-group"><input type="text" name="expire_time" id="expire_time" value="${bean.expire_time}" class="form-control" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" /><span class="input-group-addon"><i class="ace-icon fa fa-calendar"></i> </span></div></div><div class="col-sm-3"></div></div>
		</div>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="coupon_amount">优惠券金额</label><div class="col-xs-12 col-sm-9"><div class="col-sm-6"><!-- class="clearfix" --><input type="text" name="coupon_amount" id="coupon_amount" value="${bean.coupon_amount}" class="col-xs-12" /></div><div class="col-sm-3"></div></div>
		</div>
		
	</form>
</div>
</body>
<%@include file="/webpage/pub/biz/footerinfo.jsp" %>
<script type="text/javascript" src="<%=request.getContextPath()%>/webpage/biz/coupon/user/info.js"></script>
<script type="text/javascript">
		var id = '<%=request.getAttribute("bean.id")%>';
	var actiontype = '<%=request.getAttribute("actiontype")%>';
	function onSave() {
		if(actiontype=='addsave')
			addSave("dz_coupon_user_record");
		else if(actiontype=='modifysave')
			modifySave("dz_coupon_user_record");
		else if(actiontype=='deletesave')
			deleteSave("dz_coupon_user_record");
	}
	initvalid("dz_coupon_user_record");
	$(function(){
		if(actiontype=='view')
			parent.setDialogButtons("dz_coupon_user_record",{"取消": function() {parent.closewin("dz_coupon_user_record");}});
		else 
			parent.setDialogButtons("dz_coupon_user_record",{"取消": function() {parent.closewin("dz_coupon_user_record");},"确定": function() {parent.window.frames["dz_coupon_user_record_frame"].onSave();}});
	});
</script>
</html>