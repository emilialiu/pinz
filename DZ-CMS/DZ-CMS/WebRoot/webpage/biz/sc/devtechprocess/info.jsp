<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/webpage/pub/biz/headinfo.jsp" %>
<body>
<div id="t_sc_devtechprocess_info" >
	<form id="t_sc_devtechprocess_form" action="<%=request.getContextPath()%>/webpage/biz/sc/devtechprocess/doAddSave.action" method="post" class="form-horizontal">
		<input type="hidden" id="procid" name="procid" value="${bean.procid}"/>
		<input type="hidden" id="devid" name="devid" value="${bean.devid}"/>
		
		
	</form>
</div>
</body>
<%@include file="/webpage/pub/biz/footerinfo.jsp" %>
<script type="text/javascript" src="<%=request.getContextPath()%>/webpage/biz/sc/devtechprocess/info.js"></script>
<script type="text/javascript">
		var procid = '<%=request.getAttribute("bean.procid")%>';
		var devid = '<%=request.getAttribute("bean.devid")%>';
	var actiontype = '<%=request.getAttribute("actiontype")%>';
	function onSave() {
		if(actiontype=='addsave')
			addSave("t_sc_devtechprocess");
		else if(actiontype=='modifysave')
			modifySave("t_sc_devtechprocess");
		else if(actiontype=='deletesave')
			deleteSave("t_sc_devtechprocess");
	}
	initvalid("t_sc_devtechprocess");
	$(function(){
		if(actiontype=='view')
			parent.setDialogButtons("t_sc_devtechprocess",{"取消": function() {parent.closewin("t_sc_devtechprocess");}});
		else 
			parent.setDialogButtons("t_sc_devtechprocess",{"取消": function() {parent.closewin("t_sc_devtechprocess");},"确定": function() {parent.window.frames["t_sc_devtechprocess_frame"].onSave();}});
	});
</script>
</html>