<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/webpage/pub/biz/headinfo.jsp" %>
<div id="sys_user_info" >
	<form id="sys_user_form" method="post" class="form-horizontal">
		<input type="hidden" id="deptid" name="deptid" value="${bean.deptid}"/>
		<input type="hidden" id="userid" name="userid" value="${bean.userid}"/>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right"
				for="username">
				用户姓名 :
			</label>
			<div class="col-xs-12 col-sm-9">
				<div class="col-sm-6"><!-- class="clearfix" -->
					<input type="text" name="username" datatype="*" nullmsg="用户姓名必填！" id="username" 
						value="${bean.username}" class="col-xs-12" />
				</div>
				<div class="col-sm-3">
					<div class="info"><span class="Validform_checktip"></span><span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s></span></div>
				</div>
			</div>
		</div>
		
		<div class="space-2"></div>

		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right"
				for="rolename">
				登录用户名: 
			</label>
			<div class="col-xs-12 col-sm-9">
				<div class="col-sm-6"><!-- class="clearfix" -->
					<input type="text" name="loginname" datatype="*" nullmsg="登录用户名必填！" id="loginname" value="${bean.loginname}" class="col-xs-12" />
				</div>
				<div class="col-sm-3">
					<div class="info"><span class="Validform_checktip"></span><span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s></span></div>
				</div>
			</div>
		</div>
		
		<div class="space-2"></div>
		
		<div id="password" class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right"
				for="username">
				登录密码 :
			</label>
			<div class="col-xs-12 col-sm-9">
				<div class="col-sm-6"><!-- class="clearfix" -->
					<input type="password" name="pwd" datatype="*" nullmsg="登录密码必填！" id="pwd" class="col-xs-12" />
				</div>
				<div class="col-sm-3">
					<div class="info"><span class="Validform_checktip"></span><span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s></span></div>
				</div>
			</div>
		</div>
		
		<div class="space-2"></div>
		
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right"
				for="tel">
				备     注:
			</label>
			<div class="col-xs-12 col-sm-9">
				<div class="col-sm-6""><!-- class="clearfix" -->
					<textarea name="memo" id="memo" class="col-xs-12">${bean.memo}</textarea>
				</div>
				<div class="col-sm-3">
					<div class="info"><span class="Validform_checktip"></span><span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s></span></div>
				</div>
			</div>
		</div>
		
	</form>
</div>
<%@include file="/webpage/pub/biz/footerinfo.jsp" %>
<script type="text/javascript" src="<%=request.getContextPath()%>/webpage/manager/sys/user/info.js"></script>
<script type="text/javascript">
	var userid = '<%=request.getAttribute("bean.userid")%>';
	var actiontype = '<%=request.getAttribute("actiontype")%>';
	function onSave() {
		if(actiontype=='addsave')
			addSave("sys_user");
		else if(actiontype=='modifysave')
			modifySave("sys_user");
		else if(actiontype=='deletesave')
			deleteSave("sys_user");
	}
	initvalid("sys_user");
	$(function(){
		if(actiontype=='view')
			parent.setDialogButtons("sys_user",{"取消": function() {parent.closewin("sys_user");}});
		else 
			parent.setDialogButtons("sys_user",{"取消": function() {parent.closewin("sys_user");},"确定": function() {parent.window.frames["sys_user_frame"].onSave();}});

		if(actiontype=='addsave'){
			$("#password").show();
		}else{
			$("#password").hide();
		}
	});
</script>