<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/webpage/pub/biz/headinfo.jsp" %>
<body>
<div id="dz_home_page_manager_info" >
	<form id="dz_home_page_manager_form" action="<%=request.getContextPath()%>/webpage/biz/home/page/doAddSave.action" method="post" class="form-horizontal">
		<input type="hidden" id="id" name="id" value="${bean.id}"/>
		
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="img_url">banner图url</label><div class="col-xs-12 col-sm-9"><div class="col-sm-6"><!-- class="clearfix" --><textarea name="img_url" id="img_url" class="col-xs-12" ></textarea></div><div class="col-sm-3"></div></div>
		</div>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="is_start">是否启用</label><div class="col-xs-12 col-sm-9"><div class="col-sm-6"><!-- class="clearfix" --><input type="text" name="is_start" id="is_start" value="${bean.is_start}" class="col-xs-12" /></div><div class="col-sm-3"></div></div>
		</div>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="sort">排序</label><div class="col-xs-12 col-sm-9"><div class="col-sm-6"><!-- class="clearfix" --><input type="text" name="sort" id="sort" value="${bean.sort}" class="col-xs-12" /></div><div class="col-sm-3"></div></div>
		</div>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="remarks">备注</label><div class="col-xs-12 col-sm-9"><div class="col-sm-6"><!-- class="clearfix" --><textarea name="remarks" id="remarks" class="col-xs-12" ></textarea></div><div class="col-sm-3"></div></div>
		</div>
		
	</form>
</div>
</body>
<%@include file="/webpage/pub/biz/footerinfo.jsp" %>
<script type="text/javascript" src="<%=request.getContextPath()%>/webpage/biz/home/page/info.js"></script>
<script type="text/javascript">
		var id = '<%=request.getAttribute("bean.id")%>';
	var actiontype = '<%=request.getAttribute("actiontype")%>';
	function onSave() {
		if(actiontype=='addsave')
			addSave("dz_home_page_manager");
		else if(actiontype=='modifysave')
			modifySave("dz_home_page_manager");
		else if(actiontype=='deletesave')
			deleteSave("dz_home_page_manager");
	}
	initvalid("dz_home_page_manager");
	$(function(){
		if(actiontype=='view')
			parent.setDialogButtons("dz_home_page_manager",{"取消": function() {parent.closewin("dz_home_page_manager");}});
		else 
			parent.setDialogButtons("dz_home_page_manager",{"取消": function() {parent.closewin("dz_home_page_manager");},"确定": function() {parent.window.frames["dz_home_page_manager_frame"].onSave();}});
	});
</script>
</html>