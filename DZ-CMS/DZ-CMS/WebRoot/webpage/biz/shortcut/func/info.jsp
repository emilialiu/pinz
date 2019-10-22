<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/webpage/pub/biz/headinfo.jsp" %>
<body>
<div id="sys_shortcut_func_info" >
	<form id="sys_shortcut_func_form" action="<%=request.getContextPath()%>/webpage/biz/shortcut/func/doAddSave.action" method="post" class="form-horizontal">
		<input type="hidden" id="userid" name="userid" value="${bean.userid}"/>
		<input type="hidden" id="funccode" name="funccode" value="${bean.funccode}"/>
		
		
	</form>
</div>
</body>
<%@include file="/webpage/pub/biz/footerinfo.jsp" %>
<script type="text/javascript" src="<%=request.getContextPath()%>/webpage/biz/shortcut/func/info.js"></script>
<script type="text/javascript">
		var userid = '<%=request.getAttribute("bean.userid")%>';
		var funccode = '<%=request.getAttribute("bean.funccode")%>';
	var actiontype = '<%=request.getAttribute("actiontype")%>';
	function onSave() {
		if(actiontype=='addsave')
			addSave("sys_shortcut_func");
		else if(actiontype=='modifysave')
			modifySave("sys_shortcut_func");
		else if(actiontype=='deletesave')
			deleteSave("sys_shortcut_func");
	}
	initvalid("sys_shortcut_func");
	$(function(){
		if(actiontype=='view')
			parent.setDialogButtons("sys_shortcut_func",{"取消": function() {parent.closewin("sys_shortcut_func");}});
		else 
			parent.setDialogButtons("sys_shortcut_func",{"取消": function() {parent.closewin("sys_shortcut_func");},"确定": function() {parent.window.frames["sys_shortcut_func_frame"].onSave();}});
	});
</script>
</html>