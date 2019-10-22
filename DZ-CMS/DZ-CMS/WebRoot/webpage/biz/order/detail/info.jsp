<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/webpage/pub/biz/headinfo.jsp"%>
<body>
	<div id="dz_order_detail_info">
		<form id="dz_order_detail_form"
			action="<%=request.getContextPath()%>/webpage/biz/order/detail/doAddSave.action"
			method="post" class="form-horizontal">
			<input type="hidden" id="id" name="id" value="${bean.id}" />
			<input type="hidden" id="order_code" name="order_code" value="${bean.order_code}" />
			<%-- <div class="form-group">
				<label class="control-label col-xs-12 col-sm-3 no-padding-right"
					for="order_code">订单编号</label>
				<div class="col-xs-12 col-sm-9">
					<div class="col-sm-6">
						<!-- class="clearfix" -->
						<input type="text" name="order_code" id="order_code"
							value="${bean.order_code}" class="col-xs-12" />
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div> --%>
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-3 no-padding-right"
					for="commodity_id">商品id</label>
				<div class="col-xs-12 col-sm-9">
					<div class="col-sm-6">
						<!-- class="clearfix" -->
						<input type="text" name="commodity_id" id="commodity_id"
							value="${bean.commodity_id}" class="col-xs-12" />
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-3 no-padding-right"
					for="sku_content">商品规格尺寸</label>
				<div class="col-xs-12 col-sm-9">
					<div class="col-sm-6">
						<!-- class="clearfix" -->
						<input type="text" name="sku_content" id="sku_content"
							value="${bean.sku_content}" class="col-xs-12" />
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-3 no-padding-right"
					for="style_content">客户定制明细</label>
				<div class="col-xs-12 col-sm-9">
					<div class="col-sm-6">
						<!-- class="clearfix" -->
						<input type="text" name="style_content" id="style_content"
							value="${bean.style_content}" class="col-xs-12" />
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-3 no-padding-right"
					for="custome_size_content">客户身体尺寸</label>
				<div class="col-xs-12 col-sm-9">
					<div class="col-sm-6">
						<!-- class="clearfix" -->
						<input type="text" name="custome_size_content"
							id="custome_size_content" value="${bean.custome_size_content}"
							class="col-xs-12" />
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-3 no-padding-right"
					for="commodity_title">商品标题</label>
				<div class="col-xs-12 col-sm-9">
					<div class="col-sm-6">
						<!-- class="clearfix" -->
						<input type="text" name="commodity_title" id="commodity_title"
							value="${bean.commodity_title}" class="col-xs-12" />
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-3 no-padding-right"
					for="buy_count">购买数量</label>
				<div class="col-xs-12 col-sm-9">
					<div class="col-sm-6">
						<!-- class="clearfix" -->
						<input type="text" name="buy_count" id="buy_count"
							value="${bean.buy_count}" class="col-xs-12" />
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-3 no-padding-right"
					for="product_price">商品价格</label>
				<div class="col-xs-12 col-sm-9">
					<div class="col-sm-6">
						<!-- class="clearfix" -->
						<input type="text" name="product_price" id="product_price"
							value="${bean.product_price}" class="col-xs-12" />
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-3 no-padding-right"
					for="ear_money">定金</label>
				<div class="col-xs-12 col-sm-9">
					<div class="col-sm-6">
						<!-- class="clearfix" -->
						<input type="text" name="ear_money" id="ear_money"
							value="${bean.ear_money}" class="col-xs-12" />
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-3 no-padding-right"
					for="commodity_img">商品图片</label>
				<div class="col-xs-12 col-sm-9">
					<div class="col-sm-6">
						<!-- class="clearfix" -->
						<input type="text" name="commodity_img" id="commodity_img"
							value="${bean.commodity_img}" class="col-xs-12" />
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-3 no-padding-right"
					for="tail_money">尾款</label>
				<div class="col-xs-12 col-sm-9">
					<div class="col-sm-6">
						<!-- class="clearfix" -->
						<input type="text" name="tail_money" id="tail_money"
							value="${bean.tail_money}" class="col-xs-12" />
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-3 no-padding-right"
					for="linear_number">线性条数</label>
				<div class="col-xs-12 col-sm-9">
					<div class="col-sm-6">
						<!-- class="clearfix" -->
						<input type="text" name="linear_number" id="linear_number"
							value="${bean.linear_number}" class="col-xs-12" />
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-3 no-padding-right"
					for="linear_material_name">线性材质</label>
				<div class="col-xs-12 col-sm-9">
					<div class="col-sm-6">
						<!-- class="clearfix" -->
						<input type="text" name="linear_material_name"
							id="linear_material_name" value="${bean.linear_material_name}"
							class="col-xs-12" />
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>

		</form>
	</div>
</body>
<%@include file="/webpage/pub/biz/footerinfo.jsp"%>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/webpage/biz/order/detail/info.js"></script>
<script type="text/javascript">
		var id = '<%=request.getAttribute("bean.id")%>';
	var actiontype = '<%=request.getAttribute("actiontype")%>';
	function onSave() {
		if(actiontype=='addsave')
			addSave("dz_order_detail");
		else if(actiontype=='modifysave')
			modifySave("dz_order_detail");
		else if(actiontype=='deletesave')
			deleteSave("dz_order_detail");
	}
	initvalid("dz_order_detail");
	$(function(){
		if(actiontype=='view')
			parent.setDialogButtons("dz_order_detail",{"取消": function() {parent.closewin("dz_order_detail");}});
		else 
			parent.setDialogButtons("dz_order_detail",{"取消": function() {parent.closewin("dz_order_detail");},"确定": function() {parent.window.frames["dz_order_detail_frame"].onSave();}});
	});
</script>
</html>