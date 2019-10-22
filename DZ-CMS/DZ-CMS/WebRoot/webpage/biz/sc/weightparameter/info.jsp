<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/webpage/pub/biz/headinfo.jsp" %>
<div id="t_sc_weightparameter_info" >
	<form id="t_sc_weightparameter_form" action="<%=request.getContextPath()%>/webpage/biz/sc/weightparameter/doAddSave.action" method="post" class="form-horizontal">
		<input type="hidden" id="paraid" name="paraid" value="${bean.paraid}"/>
		
		
	</form>
</div>
<%@include file="/webpage/pub/biz/footerinfo.jsp" %>
<script type="text/javascript" src="<%=request.getContextPath()%>/webpage/biz/sc/weightparameter/info.js"></script>
<script type="text/javascript">
		var paraid = '<%=request.getAttribute("bean.paraid")%>';
	var actiontype = '<%=request.getAttribute("actiontype")%>';
	function onSave() {
		if(actiontype=='addsave')
			addSave("t_sc_weightparameter");
		else if(actiontype=='modifysave')
			modifySave("t_sc_weightparameter");
		else if(actiontype=='deletesave')
			deleteSave("t_sc_weightparameter");
	}
	initvalid("t_sc_weightparameter");
	$(function(){
		if(actiontype=='view')
			parent.setDialogButtons("t_sc_weightparameter",{"取消": function() {parent.closewin("t_sc_weightparameter");}});
		else 
			parent.setDialogButtons("t_sc_weightparameter",{"取消": function() {parent.closewin("t_sc_weightparameter");},"确定": function() {parent.window.frames["t_sc_weightparameter_frame"].onSave();}});
	});
</script>