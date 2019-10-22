<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/webpage/pub/biz/headinfo.jsp"%>
<body>
	<div id="dz_customer_adress_info">
		<form id="dz_customer_adress_form"
			action="<%=request.getContextPath()%>/webpage/biz/customer/adress/doAddSave.action"
			method="post" class="form-horizontal">
			<input type="hidden" id="id" name="id" value="${bean.id}" />

			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-3 no-padding-right"
					for="name">客户</label>
				<div class="col-xs-12 col-sm-9">
					<div class="col-sm-6">
						<!-- class="clearfix" -->
						<input type="text" name="customer_id" id="customer_id"
							value="${bean.name}" class="col-xs-12" />
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-3 no-padding-right"
					for="pro_name">省级名称</label>
				<div class="col-xs-12 col-sm-9">
					<div class="col-sm-6">
						<!-- class="clearfix" -->
						<input type="text" name="pro_name" id="pro_name"
							value="${bean.pro_name}" class="col-xs-12" />
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-3 no-padding-right"
					for="city_name">市级名称</label>
				<div class="col-xs-12 col-sm-9">
					<div class="col-sm-6">
						<!-- class="clearfix" -->
						<input type="text" name="city_name" id="city_name"
							value="${bean.city_name}" class="col-xs-12" />
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-3 no-padding-right"
					for="district_name">区级名称</label>
				<div class="col-xs-12 col-sm-9">
					<div class="col-sm-6">
						<!-- class="clearfix" -->
						<input type="text" name="district_name" id="district_name"
							value="${bean.district_name}" class="col-xs-12" />
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-3 no-padding-right"
					for="complet_address">详细地址</label>
				<div class="col-xs-12 col-sm-9">
					<div class="col-sm-6">
						<!-- class="clearfix" -->
						<textarea name="complet_address" id="complet_address"
							class="col-xs-12"></textarea>
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-3 no-padding-right"
					for="consignee_name">收货人姓名</label>
				<div class="col-xs-12 col-sm-9">
					<div class="col-sm-6">
						<!-- class="clearfix" -->
						<input type="text" name="consignee_name" id="consignee_name"
							value="${bean.consignee_name}" class="col-xs-12" />
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-3 no-padding-right"
					for="phone">电话号码</label>
				<div class="col-xs-12 col-sm-9">
					<div class="col-sm-6">
						<!-- class="clearfix" -->
						<input type="text" name="phone" id="phone" value="${bean.phone}"
							class="col-xs-12" />
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-3 no-padding-right"
					for="is_default">是否设置为默认地址</label>
				<div class="col-xs-12 col-sm-9">
					<div class="col-sm-6">
						<!-- class="clearfix" -->
						<input type="text" name="is_default" id="is_default"
							value="${bean.is_default}" class="col-xs-12" />
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>

		</form>
	</div>
</body>
<%@include file="/webpage/pub/biz/footerinfo.jsp"%>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/webpage/biz/customer/adress/info.js"></script>
<script type="text/javascript">
		var id = '<%=request.getAttribute("bean.id")%>';
	var actiontype = '<%=request.getAttribute("actiontype")%>
	';
	function onSave() {
		if (actiontype == 'addsave')
			addSave("dz_customer_adress");
		else if (actiontype == 'modifysave')
			modifySave("dz_customer_adress");
		else if (actiontype == 'deletesave')
			deleteSave("dz_customer_adress");
	}
	initvalid("dz_customer_adress");
	$(function() {
		if (actiontype == 'view')
			parent.setDialogButtons("dz_customer_adress", {
				"取消" : function() {
					parent.closewin("dz_customer_adress");
				}
			});
		else
			parent.setDialogButtons("dz_customer_adress", {
				"取消" : function() {
					parent.closewin("dz_customer_adress");
				},
				"确定" : function() {
					parent.window.frames["dz_customer_adress_frame"].onSave();
				}
			});
	});
</script>
</html>