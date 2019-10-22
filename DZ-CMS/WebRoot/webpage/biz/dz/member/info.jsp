<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/webpage/pub/biz/headinfo.jsp" %>
<body>
<div id="dz_member_info" >
	<form id="dz_member_form" action="<%=request.getContextPath()%>/webpage/biz/dz/member/doAddSave.action" method="post" class="form-horizontal">
		<input type="hidden" id="id" name="id" value="${bean.id}"/>
		
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="lever_name">等级名称</label><div class="col-xs-12 col-sm-9"><div class="col-sm-6"><!-- class="clearfix" --><input type="text" name="lever_name" id="lever_name" value="${bean.lever_name}" datatype="*" nullmsg="等级名称信息必填!" class="col-xs-12" /></div><div class="col-sm-3"><div class="info"><span class="Validform_checktip"></span><span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s></span></div></div></div>
		</div>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="maximum">积分值,最大值</label><div class="col-xs-12 col-sm-9"><div class="col-sm-6"><!-- class="clearfix" --><input type="text" name="maximum" id="maximum" value="${bean.maximum}" datatype="n" nullmsg="积分值,最大值必须是数字!" class="col-xs-12" /></div><div class="col-sm-3"><div class="info"><span class="Validform_checktip"></span><span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s></span></div></div></div>
		</div>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="minimum">积分值,最小值</label><div class="col-xs-12 col-sm-9"><div class="col-sm-6"><!-- class="clearfix" --><input type="text" name="minimum" id="minimum" value="${bean.minimum}" datatype="n" nullmsg="积分值,最小值必须是数字!" class="col-xs-12" /></div><div class="col-sm-3"><div class="info"><span class="Validform_checktip"></span><span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s></span></div></div></div>
		</div>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="remarks">等级备注</label><div class="col-xs-12 col-sm-9"><div class="col-sm-6"><!-- class="clearfix" --><textarea name="remarks" id="remarks" class="col-xs-12" ></textarea></div><div class="col-sm-3"></div></div>
		</div>
		
	</form>
</div>
</body>
<%@include file="/webpage/pub/biz/footerinfo.jsp" %>
<script type="text/javascript" src="<%=request.getContextPath()%>/webpage/biz/dz/member/info.js"></script>
<script type="text/javascript">
		var id = '<%=request.getAttribute("bean.id")%>';
	var actiontype = '<%=request.getAttribute("actiontype")%>';
	function onSave() {
		if(actiontype=='addsave')
			addSave("dz_member");
		else if(actiontype=='modifysave')
			modifySave("dz_member");
		else if(actiontype=='deletesave')
			deleteSave("dz_member");
	}
	initvalid("dz_member");
	$(function(){
		if(actiontype=='view')
			parent.setDialogButtons("dz_member",{"取消": function() {parent.closewin("dz_member");}});
		else 
			parent.setDialogButtons("dz_member",{"取消": function() {parent.closewin("dz_member");},"确定": function() {parent.window.frames["dz_member_frame"].onSave();}});
	});
</script>
</html>