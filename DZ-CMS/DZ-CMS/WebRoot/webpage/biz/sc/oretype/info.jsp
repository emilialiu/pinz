<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/webpage/pub/biz/headinfo.jsp" %>
<body>
<div id="t_sc_oretype_info" >
	<form id="t_sc_oretype_form" action="<%=request.getContextPath()%>/webpage/biz/sc/oretype/doAddSave.action" method="post" class="form-horizontal">
		<input type="hidden" id="oretypeid" name="oretypeid" value="${bean.oretypeid}"/>
		
		
	</form>
</div>
</body>
<%@include file="/webpage/pub/biz/footerinfo.jsp" %>
<script type="text/javascript" src="<%=request.getContextPath()%>/webpage/biz/sc/oretype/info.js"></script>
<script type="text/javascript">
		var oretypeid = '<%=request.getAttribute("bean.oretypeid")%>';
	var actiontype = '<%=request.getAttribute("actiontype")%>';
	function onSave() {
		if(actiontype=='addsave')
			addSave("t_sc_oretype");
		else if(actiontype=='modifysave')
			modifySave("t_sc_oretype");
		else if(actiontype=='deletesave')
			deleteSave("t_sc_oretype");
	}
	initvalid("t_sc_oretype");
	$(function(){
		if(actiontype=='view')
			parent.setDialogButtons("t_sc_oretype",{"取消": function() {parent.closewin("t_sc_oretype");}});
		else 
			parent.setDialogButtons("t_sc_oretype",{"取消": function() {parent.closewin("t_sc_oretype");},"确定": function() {parent.window.frames["t_sc_oretype_frame"].onSave();}});
	});
</script>
</html>