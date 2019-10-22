<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/webpage/pub/biz/headinfo.jsp" %>
<body>
<div id="${actionName ?lower_case}_info" >
	<form id="${actionName ?lower_case}_form" action="<%=request.getContextPath()%>/${filePath ?lower_case}/doAddSave.action" method="post" class="form-horizontal">
		<#list primaryKey as primaryKey>
		<input type="hidden" id="${primaryKey ?lower_case}" name="${primaryKey ?lower_case}" value="${'$'}${'{'}bean.${primaryKey ?lower_case}${'}'}"/>
		</#list>
		
		<#list code as code>
		<div class="form-group">
			${code}
		</div>
		</#list>
		
	</form>
</div>
</body>
<%@include file="/webpage/pub/biz/footerinfo.jsp" %>
<script type="text/javascript" src="<%=request.getContextPath()%>/${filePath ?lower_case}/info.js"></script>
<script type="text/javascript">
	<#list primaryKey as primaryKey>
		var ${primaryKey ?lower_case} = '<%=request.getAttribute("bean.${primaryKey ?lower_case}")%>';
	</#list>
	var actiontype = '<%=request.getAttribute("actiontype")%>';
	function onSave() {
		if(actiontype=='addsave')
			addSave("${actionName ?lower_case}");
		else if(actiontype=='modifysave')
			modifySave("${actionName ?lower_case}");
		else if(actiontype=='deletesave')
			deleteSave("${actionName ?lower_case}");
	}
	initvalid("${actionName ?lower_case}");
	$(function(){
		if(actiontype=='view')
			parent.setDialogButtons("${actionName ?lower_case}",{"取消": function() {parent.closewin("${actionName ?lower_case}");}});
		else 
			parent.setDialogButtons("${actionName ?lower_case}",{"取消": function() {parent.closewin("${actionName ?lower_case}");},"确定": function() {parent.window.frames["${actionName ?lower_case}_frame"].onSave();}});
	});
</script>
</html>