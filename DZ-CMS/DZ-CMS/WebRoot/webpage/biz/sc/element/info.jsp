<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/webpage/pub/biz/headinfo.jsp" %>
<body>
<div id="t_sc_element_info" >
	<form id="t_sc_element_form" action="<%=request.getContextPath()%>/webpage/biz/sc/element/doAddSave.action" method="post" class="form-horizontal">
		<input type="hidden" id="elementid" name="elementid" value="${bean.elementid}"/>
		
		
	</form>
</div>
</body>
<%@include file="/webpage/pub/biz/footerinfo.jsp" %>
<script type="text/javascript" src="<%=request.getContextPath()%>/webpage/biz/sc/element/info.js"></script>
<script type="text/javascript">
		var elementid = '<%=request.getAttribute("bean.elementid")%>';
	var actiontype = '<%=request.getAttribute("actiontype")%>';
	function onSave() {
		if(actiontype=='addsave')
			addSave("t_sc_element");
		else if(actiontype=='modifysave')
			modifySave("t_sc_element");
		else if(actiontype=='deletesave')
			deleteSave("t_sc_element");
	}
	initvalid("t_sc_element");
	$(function(){
		if(actiontype=='view')
			parent.setDialogButtons("t_sc_element",{"取消": function() {parent.closewin("t_sc_element");}});
		else 
			parent.setDialogButtons("t_sc_element",{"取消": function() {parent.closewin("t_sc_element");},"确定": function() {parent.window.frames["t_sc_element_frame"].onSave();}});
	});
</script>
</html>