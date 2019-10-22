<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/webpage/pub/biz/headinfo.jsp" %>
<body>
<div id="dz_customer_member_record_info" >
	<form id="dz_customer_member_record_form" action="<%=request.getContextPath()%>/webpage/biz/customer/member/doAddSave.action" method="post" class="form-horizontal">
		<input type="hidden" id="id" name="id" value="${bean.id}"/>
		
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="customer_id">客户</label><div class="col-xs-12 col-sm-9"><div class="col-sm-6"><!-- class="clearfix" --><input type="text" name="customer_id" id="customer_id" value="${bean.customer_id}" class="col-xs-12" /></div><div class="col-sm-3"></div></div>
		</div>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="member_id">会员等级</label><div class="col-xs-12 col-sm-9"><div class="col-sm-6"><!-- class="clearfix" --><input type="text" name="member_id" id="member_id" value="${bean.member_id}" class="col-xs-12" /></div><div class="col-sm-3"></div></div>
		</div>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="create_time">升级时间</label><div class="col-xs-12 col-sm-9"><div class="col-sm-6"><!-- class="clearfix" --><input type="text" name="create_time" id="create_time" value="${bean.create_time}" class="col-xs-12" /></div><div class="col-sm-3"></div></div>
		</div>
		
	</form>
</div>
</body>
<%@include file="/webpage/pub/biz/footerinfo.jsp" %>
<script type="text/javascript" src="<%=request.getContextPath()%>/webpage/biz/customer/member/info.js"></script>
<script type="text/javascript">
		var id = '<%=request.getAttribute("bean.id")%>';
	var actiontype = '<%=request.getAttribute("actiontype")%>';
	function onSave() {
		if(actiontype=='addsave')
			addSave("dz_customer_member_record");
		else if(actiontype=='modifysave')
			modifySave("dz_customer_member_record");
		else if(actiontype=='deletesave')
			deleteSave("dz_customer_member_record");
	}
	initvalid("dz_customer_member_record");
	$(function(){
		if(actiontype=='view')
			parent.setDialogButtons("dz_customer_member_record",{"取消": function() {parent.closewin("dz_customer_member_record");}});
		else 
			parent.setDialogButtons("dz_customer_member_record",{"取消": function() {parent.closewin("dz_customer_member_record");},"确定": function() {parent.window.frames["dz_customer_member_record_frame"].onSave();}});
	});
</script>
</html>