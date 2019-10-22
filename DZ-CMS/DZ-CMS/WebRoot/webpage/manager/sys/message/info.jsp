<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/webpage/pub/biz/headinfo.jsp" %>
<div id="sys_message_info" >
	<form id="sys_message_form" method="post" class="form-horizontal" enctype="multipart/form-data">
		<input type="hidden" id="messageid" name="messageid" value="${bean.messageid}"/> 
 		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right"
				for="rolename">
				<s:text name="dmmes.sys.message.title" /> :
			</label>
			<div class="col-xs-12 col-sm-9">
				<div class="col-sm-6""><!-- class="clearfix" -->
					<input type="text" name="messagetitle" datatype="*" nullmsg="标题必填！" id="messagetitle" value="${bean.messagetitle}" class="col-xs-12" />
				</div>
				<div class="col-sm-3">
					<div class="info"><span class="Validform_checktip"></span><span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s></span></div>
				</div>
			</div>
		</div>
		<div class="space-2"></div>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right"
				for="acginame">
				<s:text name="dmmes.sys.message.shortTitle" /> :
			</label>
			<div class="col-xs-12 col-sm-9">
				<div class="col-sm-6"><!-- class="clearfix" -->
					<input type="text" name="shorttile" datatype="*" nullmsg="短标题必填！" id="shorttile" value="${bean.shorttile}" class="col-xs-12" />
				</div>
				<div class="col-sm-3">
					<div class="info"><span class="Validform_checktip"></span><span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s></span></div>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right"
				for="acginame">
				<s:text name="dmmes.sys.message.messageType" /> :
			</label>
			<div class="col-xs-12 col-sm-9">
				<div class="col-sm-6"><!-- class="clearfix" -->
					<input type="text" name="mestype" datatype="*" nullmsg="信息类型必填！" id="mestype" value="${bean.mestype}" class="col-xs-12" />
				</div>
				<div class="col-sm-3">
					<div class="info"><span class="Validform_checktip"></span><span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s></span></div>
				</div>
			</div>
		</div>
	<!-- <div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right"
				for="acginame">
				文件:
			</label>
			<div class="col-xs-12 col-sm-9">
				<div class="col-sm-6"> 
					<input type="file" name="mesfile"   id="mesfile" class="col-xs-12" />
				</div>
				<div class="col-sm-3">
					<div class="info"><span class="Validform_checktip"></span><span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s></span></div>
				</div>
			</div>
		</div> -->	
		<div class="space-2"></div>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right"
				for="memo">
				<s:text name="dmmes.sys.message.content" /> :
			</label>
			<div class="col-xs-12 col-sm-9">
				<div class="col-sm-9"><!-- class="clearfix" -->
					<textarea name="mescontent" id="mescontent" rows="5" class="col-xs-12">${bean.mescontent}</textarea>	 
				</div>
			</div>
		</div>
	</form>
</div>
<%@include file="/webpage/pub/biz/footerinfo.jsp" %>
<script type="text/javascript" src="<%=request.getContextPath()%>/webpage/manager/sys/message/info.js"></script>
<script type="text/javascript">
	var messageid = '<%=request.getAttribute("bean.messageid")%>';
	var actiontype = '<%=request.getAttribute("actiontype")%>';
	initvalid("sys_message");
	$(function(){
		if(actiontype=='view')
			parent.setDialogButtons("sys_message",{"取消": function() {parent.closewin("sys_message");}});
		else 
			parent.setDialogButtons("sys_message",{"取消": function() {parent.closewin("sys_message");},"确定": function() {parent.window.frames["sys_message_frame"].onSave();}});
	});
	function onSave() {
		if(actiontype=='addsave')
			addSave("sys_message");
		else if(actiontype=='modifysave')
			modifySave("sys_message");
		else if(actiontype=='deletesave')
			deleteSave("sys_message");
	}
</script>