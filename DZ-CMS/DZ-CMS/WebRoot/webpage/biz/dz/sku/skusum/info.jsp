<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/webpage/pub/biz/headinfo.jsp" %>
<body>
<div id="dz_skusum_info" >
		<form id="dz_skusum_form"
			action="<%=request.getContextPath()%>/webpage/biz/dz/sku/doAddSave.action"
			method="post" class="form-horizontal">
			<input type="hidden" id="id" name="id" value="${bean.id}" />
			<input type="hidden" id="parent_id" name="parent_id" value="${bean.parent_id}" />
				<input type="hidden" id="product_category_id" name="product_category_id" value="${bean.product_category_id}" />
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-3 no-padding-right"
					for="sku_name">规格名称</label>
				<div class="col-xs-12 col-sm-9">
					<div class="col-sm-6">
						<!-- class="clearfix" -->
						<input type="text" name="sku_name" id="sku_name"
							value="${bean.sku_name}" class="col-xs-12" />
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-3 no-padding-right"
					for="sku_value">规格值</label>
				<div class="col-xs-12 col-sm-9">
					<div class="col-sm-6">
						<!-- class="clearfix" -->
						<input type="text" name="sku_value" id="sku_value"
							value="${bean.sku_value}" class="col-xs-12" />
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-3 no-padding-right"
					for="sort">排序</label>
				<div class="col-xs-12 col-sm-9">
					<div class="col-sm-6">
						<!-- class="clearfix" -->
						<input type="text" name="sort" id="sort" value="${bean.sort}"
							class="col-xs-12" />
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-3 no-padding-right"
					for="sku_code">规格code</label>
				<div class="col-xs-12 col-sm-9">
					<div class="col-sm-6">
						<!-- class="clearfix" -->
						<input type="text" name="sku_code" id="sku_code"
							value="${bean.sku_code}" class="col-xs-12" />
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-3 no-padding-right"
					for="limit_ids">规格限制id</label>
				<div class="col-xs-12 col-sm-9">
					<div class="col-sm-6">
						<!-- class="clearfix" -->
						<input type="text" name="limit_ids" id="limit_ids"
							value="${bean.limit_ids}" class="col-xs-12" />
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>


		</form>
	</div>
</body>
<%@include file="/webpage/pub/biz/footerinfo.jsp" %>
<script type="text/javascript" src="<%=request.getContextPath()%>/webpage/biz/dz/sku/skusum/info.js"></script>
<script type="text/javascript">
	var id = '<%=request.getAttribute("bean.id")%>';
	var product_category_id = '<%=request.getAttribute("bean.product_category_id")%>';
	var actiontype = '<%=request.getAttribute("actiontype")%>';
	function onSave() {
		if(actiontype=='addsave')
			addSave("dz_skusum");
		else if(actiontype=='modifysave')
			modifySave("dz_skusum");
		else if(actiontype=='deletesave')
			deleteSave("dz_skusum");
	}
	initvalid("dz_skusum");
	$(function(){
	  $("#product_category_id  option[value='"+product_category_id+"']").attr("selected","selected");
		if(actiontype=='view'){
			parent.setDialogButtons("dz_skusum",{"取消": function() {parent.closewin("dz_skusum");}});
		}else{
			parent.setDialogButtons("dz_skusum",{"取消": function() {parent.closewin("dz_skusum");},"确定": function() {parent.window.frames["dz_skusum_frame"].onSave();}});
		}
	});
</script>
</html>