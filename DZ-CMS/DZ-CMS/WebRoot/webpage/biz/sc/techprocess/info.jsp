<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/webpage/pub/biz/headinfo.jsp" %>
<body>
<div id="t_sc_techprocess_info" >
	<form id="t_sc_techprocess_form" action="<%=request.getContextPath()%>/webpage/biz/sc/techprocess/doAddSave.action" method="post" class="form-horizontal">
		<input type="hidden" id="techid" name="techid" value="${bean.techid}"/>
		<input type="hidden" id="procid" name="procid" value="${bean.procid}"/>
		
		
	</form>
</div>
</body>
<%@include file="/webpage/pub/biz/footerinfo.jsp" %>
<script type="text/javascript" src="<%=request.getContextPath()%>/webpage/biz/sc/techprocess/info.js"></script>
<script type="text/javascript">
		var techid = '<%=request.getAttribute("bean.techid")%>';
		var procid = '<%=request.getAttribute("bean.procid")%>';
	var actiontype = '<%=request.getAttribute("actiontype")%>';
	function onSave() {
		if(actiontype=='addsave')
			addSave("t_sc_techprocess");
		else if(actiontype=='modifysave')
			modifySave("t_sc_techprocess");
		else if(actiontype=='deletesave')
			deleteSave("t_sc_techprocess");
	}
	initvalid("t_sc_techprocess");
	$(function(){
		if(actiontype=='view')
			parent.setDialogButtons("t_sc_techprocess",{"取消": function() {parent.closewin("t_sc_techprocess");}});
		else 
			parent.setDialogButtons("t_sc_techprocess",{"取消": function() {parent.closewin("t_sc_techprocess");},"确定": function() {parent.window.frames["t_sc_techprocess_frame"].onSave();}});
	});
</script>
</html>