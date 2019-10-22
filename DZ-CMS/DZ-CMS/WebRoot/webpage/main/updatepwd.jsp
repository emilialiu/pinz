<%@ page language="java" pageEncoding="utf-8"%>
<div id="update_pwd">
	<form id="update_pwd_form" method="post" class="form-horizontal">
	<input type="hidden" id="userid"/>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="rolename">
				原始密码:
			</label>
			<div class="col-xs-12 col-sm-9">
				<div class="col-sm-7"><!-- class="clearfix" -->
					<input type="password" name="oldpasswd" datatype="*" nullmsg="原始密码必填！" id="oldpasswd" class="col-xs-12" />
				</div>
				<div class="col-sm-2">
					<div class="info"><span class="Validform_checktip"></span><span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s></span></div>
				</div>
			</div>
		</div>
		<div class="space-2"></div>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="rolename">
				密码:
			</label>
			<div class="col-xs-12 col-sm-9">
				<div class="col-sm-7"><!-- class="clearfix" -->
					<input type="password" name="newpasswd" datatype="*" nullmsg="密码必填！" id="newpasswd" class="col-xs-12" />
				</div>
				<div class="col-sm-2">
					<div class="info"><span class="Validform_checktip"></span><span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s></span></div>
				</div>
			</div>
		</div>
		<div class="space-2"></div>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="username">
				密码确认:
			</label>
			<div class="col-xs-12 col-sm-9">
				<div class="col-sm-7"><!-- class="clearfix" -->
					<input type="password" datatype="*" nullmsg="密码确认必填！" errormsg="两次输入的密码不一致！" recheck="newpasswd" id="confirmpasswd" class="col-xs-12" />
				</div>
				<div class="col-sm-2">
					<div class="info"><span class="Validform_checktip"></span><span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s></span></div>
				</div>
			</div>
		</div>
	</form>
</div>
