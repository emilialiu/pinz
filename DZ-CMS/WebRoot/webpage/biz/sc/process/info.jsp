<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/webpage/pub/biz/headinfo.jsp" %>
<div id="t_sc_process_info" >
	<form id="t_sc_process_form" action="<%=request.getContextPath()%>/webpage/biz/sc/process/doAddSave.action" method="post" class="form-horizontal">
		<input type="hidden" id="procid" name="procid" value="${bean.procid}"/>
		
		
	</form>
</div>
<%@include file="/webpage/pub/biz/footerinfo.jsp" %>
<script type="text/javascript" src="<%=request.getContextPath()%>/webpage/biz/sc/process/info.js"></script>
<script type="text/javascript">
		var procid = '<%=request.getAttribute("bean.procid")%>';
	var actiontype = '<%=request.getAttribute("actiontype")%>';
	function onSave() {
		if(actiontype=='addsave')
			addSave("t_sc_process");
		else if(actiontype=='modifysave')
			modifySave("t_sc_process");
		else if(actiontype=='deletesave')
			deleteSave("t_sc_process");
	}
	initvalid("t_sc_process");
	$(function(){
		if(actiontype=='view')
			parent.setDialogButtons("t_sc_process",{"取消": function() {parent.closewin("t_sc_process");}});
		else 
			parent.setDialogButtons("t_sc_process",{"取消": function() {parent.closewin("t_sc_process");},"确定": function() {parent.window.frames["t_sc_process_frame"].onSave();}});
	});
</script>