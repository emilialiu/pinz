<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/webpage/pub/biz/headinfo.jsp" %>
<div id="sys_user_reset_info" >
	<form id="sys_user_reset_form" method="post" class="form-horizontal">
	<input type="hidden" id="userid" value="<%=request.getParameter("userid") %>"/>
	<div class="form-group">
		<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="rolename">
				密码:
			</label>
			<div class="col-xs-12 col-sm-9">
				<div class="col-sm-7"><!-- class="clearfix" -->
					<input type="password" name="loginpwd" datatype="*" nullmsg="密码必填！" id="loginpwd" class="col-xs-12" />
				</div>
				<div class="col-sm-2">
					<div class="info"><span class="Validform_checktip"></span><span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s></span></div>
				</div>
			</div>
		</div>
		
		<div class="space-2"></div>
		
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right"
				for="username">
				密码确认:
			</label>
			<div class="col-xs-12 col-sm-9">
				<div class="col-sm-7"><!-- class="clearfix" -->
					<input type="password" datatype="*" nullmsg="密码确认必填！" errormsg="两次输入的密码不一致！" recheck="loginpwd" id="reloginpwd" class="col-xs-12" />
				</div>
				<div class="col-sm-2">
					<div class="info"><span class="Validform_checktip"></span><span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s></span></div>
				</div>
			</div>
		</div>
	</form>
</div>
<%@include file="/webpage/pub/biz/footerinfo.jsp" %>
<script type="text/javascript">
	initvalid("sys_user_reset");
	$(function(){
		parent.setDialogButtons("sys_user_reset",{"取消": function() {parent.closewin("sys_user_reset");},"确定": function() {parent.window.frames["sys_user_reset_frame"].onSave();}});
	});
	function onSave() {
		//检查不成功，跳出
		if(!$("#sys_user_reset_form").Validform().checkForm()) return;
	    var consoleDlg = $("#sys_user_reset_info"); 
	    var userid = $.trim(consoleDlg.find("#userid").val());
	    var loginpwd = $.trim(consoleDlg.find("#loginpwd").val());
	    $.ajax( {  
	        url : rootpath+"/manager/sys/user/modifypwd.action",  
	        data : {'bean.userid':userid,'bean.loginpwd':loginpwd},  
	        cache : false,  
	        success : function(data) {  
	        	var dataJson = eval(data);
	            if(dataJson.success) {  
	            	parent.closewin("sys_user_reset");  
	            	parent.alertMsg("密码重置成功!");  
	            } else {  
	            	parent.alertMsg("密码重置失败"+dataJson.errormessage);  
	            }  
	        },
	        error : function(data) {  
	        	parent.alertMsg("系统ajax交互错误" );  
	        }
	    });
	}
</script>