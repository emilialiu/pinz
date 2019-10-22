<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/webpage/pub/biz/headinfo.jsp" %>
<div id="t_bi_target_info" >
	<form id="t_bi_target_form" action="<%=request.getContextPath()%>/webpage/biz/bi/target/doAddSave.action" method="post" class="form-horizontal">
		<input type="hidden" id="targetid" name="targetid" value="${bean.targetid}"/>
		
		
	</form>
</div>
<%@include file="/webpage/pub/biz/footerinfo.jsp" %>
<script type="text/javascript" src="<%=request.getContextPath()%>/webpage/biz/bi/target/info.js"></script>
<script type="text/javascript">
		var targetid = '<%=request.getAttribute("bean.targetid")%>';
	var actiontype = '<%=request.getAttribute("actiontype")%>';
	function onSave() {
		if(actiontype=='addsave')
			addSave("t_bi_target");
		else if(actiontype=='modifysave')
			modifySave("t_bi_target");
		else if(actiontype=='deletesave')
			deleteSave("t_bi_target");
	}
	initvalid("t_bi_target");
	$(function(){
		if(actiontype=='view')
			parent.setDialogButtons("t_bi_target",{"取消": function() {parent.closewin("t_bi_target");}});
		else 
			parent.setDialogButtons("t_bi_target",{"取消": function() {parent.closewin("t_bi_target");},"确定": function() {parent.window.frames["t_bi_target_frame"].onSave();}});
	});
</script>