<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/webpage/pub/biz/headinfo.jsp" %>
<body>
<div id="dz_order_info" >
	<form id="dz_order_form" action="<%=request.getContextPath()%>/webpage/biz/dz/order/doAddSave.action" method="post" class="form-horizontal">
		<input type="hidden" id="id" name="id" value="${bean.id}"/>
		
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="order_code">订单编号</label><div class="col-xs-12 col-sm-9"><div class="col-sm-6"><!-- class="clearfix" --><input type="text" name="order_code" id="order_code" value="${bean.order_code}" class="col-xs-12" /></div><div class="col-sm-3"></div></div>
		</div>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="customer_id">客户id</label><div class="col-xs-12 col-sm-9"><div class="col-sm-6"><!-- class="clearfix" --><input type="text" name="customer_id" id="customer_id" value="${bean.customer_id}" class="col-xs-12" /></div><div class="col-sm-3"></div></div>
		</div>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="total_money">总价金额</label><div class="col-xs-12 col-sm-9"><div class="col-sm-6"><!-- class="clearfix" --><input type="text" name="total_money" id="total_money" value="${bean.total_money}" class="col-xs-12" /></div><div class="col-sm-3"></div></div>
		</div>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="deposit_paid">已付定金金额</label><div class="col-xs-12 col-sm-9"><div class="col-sm-6"><!-- class="clearfix" --><input type="text" name="deposit_paid" id="deposit_paid" value="${bean.deposit_paid}" class="col-xs-12" /></div><div class="col-sm-3"></div></div>
		</div>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="deposit_paid_time">付定金时间</label><div class="col-xs-12 col-sm-9"><div class="col-sm-6"><!-- class="clearfix" --><input type="text" name="deposit_paid_time" id="deposit_paid_time" value="${bean.deposit_paid_time}" class="col-xs-12" /></div><div class="col-sm-3"></div></div>
		</div>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="final_payment">剩余尾款金额</label><div class="col-xs-12 col-sm-9"><div class="col-sm-6"><!-- class="clearfix" --><input type="text" name="final_payment" id="final_payment" value="${bean.final_payment}" class="col-xs-12" /></div><div class="col-sm-3"></div></div>
		</div>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="freight">运费</label><div class="col-xs-12 col-sm-9"><div class="col-sm-6"><!-- class="clearfix" --><input type="text" name="freight" id="freight" value="${bean.freight}" class="col-xs-12" /></div><div class="col-sm-3"></div></div>
		</div>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="state">订单状态</label><div class="col-xs-12 col-sm-9"><div class="col-sm-6"><!-- class="clearfix" --><input type="text" name="state" id="state" value="${bean.state}" class="col-xs-12" /></div><div class="col-sm-3"></div></div>
		</div>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="is_cancel">是否取消订单</label><div class="col-xs-12 col-sm-9"><div class="col-sm-6"><!-- class="clearfix" --><input type="text" name="is_cancel" id="is_cancel" value="${bean.is_cancel}" class="col-xs-12" /></div><div class="col-sm-3"></div></div>
		</div>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="create_time">创建订单时间</label><div class="col-xs-12 col-sm-9"><div class="col-sm-6"><!-- class="clearfix" --><div class="input-group"><input type="text" name="create_time" id="create_time" value="${bean.create_time}" class="form-control" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" /><span class="input-group-addon"><i class="ace-icon fa fa-calendar"></i> </span></div></div><div class="col-sm-3"></div></div>
		</div>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="update_time">管理员修改订单状态时间</label><div class="col-xs-12 col-sm-9"><div class="col-sm-6"><!-- class="clearfix" --><div class="input-group"><input type="text" name="update_time" id="update_time" value="${bean.update_time}" class="form-control" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" /><span class="input-group-addon"><i class="ace-icon fa fa-calendar"></i> </span></div></div><div class="col-sm-3"></div></div>
		</div>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="update_by">修改人</label><div class="col-xs-12 col-sm-9"><div class="col-sm-6"><!-- class="clearfix" --><input type="text" name="update_by" id="update_by" value="${bean.update_by}" class="col-xs-12" /></div><div class="col-sm-3"></div></div>
		</div>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="payment_time">付尾款时间</label><div class="col-xs-12 col-sm-9"><div class="col-sm-6"><!-- class="clearfix" --><div class="input-group"><input type="text" name="payment_time" id="payment_time" value="${bean.payment_time}" class="form-control" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" /><span class="input-group-addon"><i class="ace-icon fa fa-calendar"></i> </span></div></div><div class="col-sm-3"></div></div>
		</div>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="payment_money">已付尾款金额</label><div class="col-xs-12 col-sm-9"><div class="col-sm-6"><!-- class="clearfix" --><input type="text" name="payment_money" id="payment_money" value="${bean.payment_money}" class="col-xs-12" /></div><div class="col-sm-3"></div></div>
		</div>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="logistics_number">物流单号</label><div class="col-xs-12 col-sm-9"><div class="col-sm-6"><!-- class="clearfix" --><input type="text" name="logistics_number" id="logistics_number" value="${bean.logistics_number}" class="col-xs-12" /></div><div class="col-sm-3"></div></div>
		</div>
		
	</form>
</div>
</body>
<%@include file="/webpage/pub/biz/footerinfo.jsp" %>
<script type="text/javascript" src="<%=request.getContextPath()%>/webpage/biz/dz/order/info.js"></script>
<script type="text/javascript">
		var id = '<%=request.getAttribute("bean.id")%>';
	var actiontype = '<%=request.getAttribute("actiontype")%>';
	function onSave() {
		if(actiontype=='addsave')
			addSave("dz_order");
		else if(actiontype=='modifysave')
			modifySave("dz_order");
		else if(actiontype=='deletesave')
			deleteSave("dz_order");
	}
	initvalid("dz_order");
	$(function(){
		if(actiontype=='view')
			parent.setDialogButtons("dz_order",{"取消": function() {parent.closewin("dz_order");}});
		else 
			parent.setDialogButtons("dz_order",{"取消": function() {parent.closewin("dz_order");},"确定": function() {parent.window.frames["dz_order_frame"].onSave();}});
	});
</script>
</html>