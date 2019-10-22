<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/webpage/pub/biz/headinfo.jsp" %>
<body>
<div id="t_pub_tomatter_info" >
	<form id="t_pub_tomatter_form" action="<%=request.getContextPath()%>/webpage/biz/pub/tomatter/doAddSave.action" method="post" class="form-horizontal">
		<input type="hidden" id="matterid" name="matterid" value="${bean.matterid}"/>
		
		
	</form>
</div>
</body>
<%@include file="/webpage/pub/biz/footerinfo.jsp" %>
<script type="text/javascript" src="<%=request.getContextPath()%>/webpage/biz/pub/tomatter/info.js"></script>
<script type="text/javascript">
		var matterid = '<%=request.getAttribute("bean.matterid")%>';
	var actiontype = '<%=request.getAttribute("actiontype")%>';
	function onSave() {
		if(actiontype=='addsave')
			addSave("t_pub_tomatter");
		else if(actiontype=='modifysave')
			modifySave("t_pub_tomatter");
		else if(actiontype=='deletesave')
			deleteSave("t_pub_tomatter");
	}
	initvalid("t_pub_tomatter");
	$(function(){
		if(actiontype=='view')
			parent.setDialogButtons("t_pub_tomatter",{"取消": function() {parent.closewin("t_pub_tomatter");}});
		else 
			parent.setDialogButtons("t_pub_tomatter",{"取消": function() {parent.closewin("t_pub_tomatter");},"确定": function() {parent.window.frames["t_pub_tomatter_frame"].onSave();}});
	});
</script>
</html>