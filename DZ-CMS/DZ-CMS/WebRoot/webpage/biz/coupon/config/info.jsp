<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/webpage/pub/biz/headinfo.jsp" %>
<body>
<div id="dz_coupon_config_info" >
	<form id="dz_coupon_config_form" action="<%=request.getContextPath()%>/webpage/biz/coupon/config/doAddSave.action" method="post" class="form-horizontal">
		<input type="hidden" id="id" name="id" value="${bean.id}"/>
		<input type="hidden" id="commodity_id" name="commodity_id" value="${bean.commodity_id}"/>
		<input type="hidden" id="commodity_type_id" name="commodity_type_id" value="${bean.commodity_type_id}"/>
		<div class="form-group">
				<label class="control-label col-xs-12 col-sm-3 no-padding-right"
					for="type_id">优惠卷类别</label>
				<div class="col-xs-12 col-sm-9">
					<div class="col-sm-6">
						<!-- class="clearfix" -->
							<dmtag:combomanager table="dz_coupon_type"
							cssClass="chosen-select form-control" name="type_id"
							combovalue="id" combotext="name"
							parameter=""
							isnull="false"></dmtag:combomanager>
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-3 no-padding-right"
					for="expired_time">过期时间,具体时间</label>
				<div class="col-xs-12 col-sm-9">
					<div class="col-sm-6">
						<!-- class="clearfix" -->
						<div class="input-group">
							<input type="text" name="expired_time" id="expired_time"
								value="${bean.expired_time}" class="form-control"
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
					for="expired_num">过期时间,天数</label>
				<div class="col-xs-12 col-sm-9">
					<div class="col-sm-6">
						<!-- class="clearfix" -->
						<input type="text" name="expired_num" id="expired_num"
							value="${bean.expired_num}" class="col-xs-12" />
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-3 no-padding-right"
					for="remarks">说明</label>
				<div class="col-xs-12 col-sm-9">
					<div class="col-sm-6">
						<!-- class="clearfix" -->
						<input type="text" name="remarks" id="remarks"
							value="${bean.remarks}" class="col-xs-12" />
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-3 no-padding-right"
					for="voucher_amount">优惠券金额</label>
				<div class="col-xs-12 col-sm-9">
					<div class="col-sm-6">
						<!-- class="clearfix" -->
						<input type="text" name="voucher_amount" id="voucher_amount"
							value="${bean.voucher_amount}" class="col-xs-12" />
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-3 no-padding-right"
					for="commodity_id">商品名称</label>
				<div class="col-xs-12 col-sm-9">
					<div class="col-sm-6">
						<!-- class="clearfix" -->
						<input type="text" name="commodityid" id="commodityid"
							value="${bean.commodityid}"  readonly="readonly" onclick="selectsp()" class="col-xs-12" />
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-3 no-padding-right"
					for="member_level">会员等级</label>
				<div class="col-xs-12 col-sm-9">
					<div class="col-sm-6">
						<!-- class="clearfix" -->
							<dmtag:combomanager table="Dz_member"
							cssClass="chosen-select form-control" name="member_level"
							combovalue="id" combotext="lever_name"
							parameter=""
							isnull="false"></dmtag:combomanager>
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-3 no-padding-right"
					for="commodity_type_id">商品分类</label>
				<div class="col-xs-12 col-sm-9">
					<div class="col-sm-6">
						<!-- class="clearfix" -->
						<input type="text" name="commoditytypeid" id="commoditytypeid"
							value="${bean.commoditytypeid}" readonly="readonly" class="col-xs-12" />
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-3 no-padding-right"
					for="state">状态</label>
				<div class="col-xs-12 col-sm-9">
					<div class="col-sm-6">
						<!-- class="clearfix" -->
						<select id="state" class="col-xs-12" >
							<option value="0">已过期</option>
							<option value="1">未过期</option>
						</select>
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>

		</form>
</div>
</body>
<%@include file="/webpage/pub/biz/footerinfo.jsp" %>
<script type="text/javascript" src="<%=request.getContextPath()%>/webpage/biz/coupon/config/info.js"></script>
<script type="text/javascript">
	var id = '<%=request.getAttribute("bean.id")%>';
	var state = '<%=request.getAttribute("bean.state")%>';
	var actiontype = '<%=request.getAttribute("actiontype")%>';
	function onSave() {
		if(actiontype=='addsave')
			addSave("dz_coupon_config");
		else if(actiontype=='modifysave')
			modifySave("dz_coupon_config");
		else if(actiontype=='deletesave')
			deleteSave("dz_coupon_config");
	}
	initvalid("dz_coupon_config");
	$(function(){
		$("#state  option[value='"+state+"']").attr("selected","selected");
		if(actiontype=='view'){
			parent.setDialogButtons("dz_coupon_config",{"取消": function() {parent.closewin("dz_coupon_config");}});
		}else {
			parent.setDialogButtons("dz_coupon_config",{"取消": function() {parent.closewin("dz_coupon_config");},"确定": function() {parent.window.frames["dz_coupon_config_frame"].onSave();}});
		}
	});
	function selectsp() {
		parent.openwin("dz_commodity_base_select","/webpage/pub/select/dz_commodity_base/main.jsp?multiselect=false", "选择单品",window.screen.availWidth * 0.6, 570);
		parent.PageObject.itemMap['dz_commodity_base_select'].callback = function(data) {
			$("#commodity_id").val(data[0].id);
			$("#commodityid").val(data[0].title);
			$("#commodity_type_id").val(data[0].product_category_id);
			$("#commoditytypeid").val(data[0].productcategoryid);
		}
	}
</script>
</html>