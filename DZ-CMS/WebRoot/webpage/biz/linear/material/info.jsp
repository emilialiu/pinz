<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/webpage/pub/biz/headinfo.jsp" %>
<body>
<div id="dz_linear_material_info" >
	<form id="dz_linear_material_form" action="<%=request.getContextPath()%>/webpage/biz/linear/material/doAddSave.action" method="post" class="form-horizontal">
		<input type="hidden" id="id" name="id" value="${bean.id}"/>
		
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="name">材质名称</label><div class="col-xs-12 col-sm-9"><div class="col-sm-6"><!-- class="clearfix" --><input type="text" name="name" id="name" value="${bean.name}" class="col-xs-12" /></div><div class="col-sm-3"></div></div>
		</div>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="price">材质基础价</label><div class="col-xs-12 col-sm-9"><div class="col-sm-6"><!-- class="clearfix" --><input type="text" name="price" id="price" value="${bean.price}" class="col-xs-12" /></div><div class="col-sm-3"></div></div>
		</div>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="remarks">备注</label><div class="col-xs-12 col-sm-9"><div class="col-sm-6"><!-- class="clearfix" --><input type="text" name="remarks" id="remarks" value="${bean.remarks}" class="col-xs-12" /></div><div class="col-sm-3"></div></div>
		</div>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="sort">排序</label><div class="col-xs-12 col-sm-9"><div class="col-sm-6"><!-- class="clearfix" --><input type="text" name="sort" id="sort" value="${bean.sort}" class="col-xs-12" /></div><div class="col-sm-3"></div></div>
		</div>
		
	</form>
</div>
</body>
<%@include file="/webpage/pub/biz/footerinfo.jsp" %>
<script type="text/javascript" src="<%=request.getContextPath()%>/webpage/biz/linear/material/info.js"></script>
<script type="text/javascript">
		var id = '<%=request.getAttribute("bean.id")%>';
	var actiontype = '<%=request.getAttribute("actiontype")%>';
	function onSave() {
		if(actiontype=='addsave')
			addSave("dz_linear_material");
		else if(actiontype=='modifysave')
			modifySave("dz_linear_material");
		else if(actiontype=='deletesave')
			deleteSave("dz_linear_material");
	}
	initvalid("dz_linear_material");
	$(function(){
		if(actiontype=='view')
			parent.setDialogButtons("dz_linear_material",{"取消": function() {parent.closewin("dz_linear_material");}});
		else 
			parent.setDialogButtons("dz_linear_material",{"取消": function() {parent.closewin("dz_linear_material");},"确定": function() {parent.window.frames["dz_linear_material_frame"].onSave();}});
	});
</script>
</html>