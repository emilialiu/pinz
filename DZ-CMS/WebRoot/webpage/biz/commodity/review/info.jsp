<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/webpage/pub/biz/headinfo.jsp" %>
<body>
<div id="dz_commodity_review_info" >
	<form id="dz_commodity_review_form" action="<%=request.getContextPath()%>/webpage/biz/commodity/review/doAddSave.action" method="post" class="form-horizontal">
		<input type="hidden" id="id" name="id" value="${bean.id}"/>
		
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="satisfaction">满意系数：星星表示</label><div class="col-xs-12 col-sm-9"><div class="col-sm-6"><!-- class="clearfix" --><input type="text" name="satisfaction" id="satisfaction" value="${bean.satisfaction}" class="col-xs-12" /></div><div class="col-sm-3"></div></div>
		</div>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="comments">评论内容</label><div class="col-xs-12 col-sm-9"><div class="col-sm-6"><!-- class="clearfix" --><input type="text" name="comments" id="comments" value="${bean.comments}" class="col-xs-12" /></div><div class="col-sm-3"></div></div>
		</div>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="create_time">评论时间</label><div class="col-xs-12 col-sm-9"><div class="col-sm-6"><!-- class="clearfix" --><div class="input-group"><input type="text" name="create_time" id="create_time" value="${bean.create_time}" class="form-control" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" /><span class="input-group-addon"><i class="ace-icon fa fa-calendar"></i> </span></div></div><div class="col-sm-3"></div></div>
		</div>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="is_delete">是否删除</label><div class="col-xs-12 col-sm-9"><div class="col-sm-6"><!-- class="clearfix" --><input type="text" name="is_delete" id="is_delete" value="${bean.is_delete}" class="col-xs-12" /></div><div class="col-sm-3"></div></div>
		</div>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="img_url">图片地址</label><div class="col-xs-12 col-sm-9"><div class="col-sm-6"><!-- class="clearfix" --><textarea name="img_url" id="img_url" class="col-xs-12" ></textarea></div><div class="col-sm-3"></div></div>
		</div>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="reply_content">回复内容</label><div class="col-xs-12 col-sm-9"><div class="col-sm-6"><!-- class="clearfix" --><textarea name="reply_content" id="reply_content" class="col-xs-12" ></textarea></div><div class="col-sm-3"></div></div>
		</div>
		
	</form>
</div>
</body>
<%@include file="/webpage/pub/biz/footerinfo.jsp" %>
<script type="text/javascript" src="<%=request.getContextPath()%>/webpage/biz/commodity/review/info.js"></script>
<script type="text/javascript">
		var id = '<%=request.getAttribute("bean.id")%>';
	var actiontype = '<%=request.getAttribute("actiontype")%>';
	function onSave() {
		if(actiontype=='addsave')
			addSave("dz_commodity_review");
		else if(actiontype=='modifysave')
			modifySave("dz_commodity_review");
		else if(actiontype=='deletesave')
			deleteSave("dz_commodity_review");
	}
	initvalid("dz_commodity_review");
	$(function(){
		if(actiontype=='view')
			parent.setDialogButtons("dz_commodity_review",{"取消": function() {parent.closewin("dz_commodity_review");}});
		else 
			parent.setDialogButtons("dz_commodity_review",{"取消": function() {parent.closewin("dz_commodity_review");},"确定": function() {parent.window.frames["dz_commodity_review_frame"].onSave();}});
	});
</script>
</html>