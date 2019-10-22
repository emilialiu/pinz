<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/webpage/pub/biz/headinfo.jsp" %>
<body>
<div id="dz_coupon_type_info" >
	<form id="dz_coupon_type_form" action="<%=request.getContextPath()%>/webpage/biz/coupon/type/doAddSave.action" method="post" class="form-horizontal">
		<input type="hidden" id="id" name="id" value="${bean.id}"/>
		
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="name">分类名称</label><div class="col-xs-12 col-sm-9"><div class="col-sm-6"><!-- class="clearfix" --><input type="text" name="name" id="name" value="${bean.name}" class="col-xs-12" /></div><div class="col-sm-3"></div></div>
		</div>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="remarks">备注</label><div class="col-xs-12 col-sm-9"><div class="col-sm-6"><!-- class="clearfix" --><textarea name="remarks" id="remarks" class="col-xs-12" ></textarea></div><div class="col-sm-3"></div></div>
		</div>
		
	</form>
</div>
</body>
<%@include file="/webpage/pub/biz/footerinfo.jsp" %>
<script type="text/javascript" src="<%=request.getContextPath()%>/webpage/biz/coupon/type/info.js"></script>
<script type="text/javascript">
		var id = '<%=request.getAttribute("bean.id")%>';
	var actiontype = '<%=request.getAttribute("actiontype")%>';
	function onSave() {
		if(actiontype=='addsave')
			addSave("dz_coupon_type");
		else if(actiontype=='modifysave')
			modifySave("dz_coupon_type");
		else if(actiontype=='deletesave')
			deleteSave("dz_coupon_type");
	}
	initvalid("dz_coupon_type");
	$(function(){
		if(actiontype=='view')
			parent.setDialogButtons("dz_coupon_type",{"取消": function() {parent.closewin("dz_coupon_type");}});
		else 
			parent.setDialogButtons("dz_coupon_type",{"取消": function() {parent.closewin("dz_coupon_type");},"确定": function() {parent.window.frames["dz_coupon_type_frame"].onSave();}});
	});
</script>
</html>