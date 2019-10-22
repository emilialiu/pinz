<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/pub/biz/headinfo.jsp" %>
<div id="sys_ulog_info" >
	<form id="sys_ulog_form" method="post" class="form-horizontal">
		<input type="hidden" id="logid" name="logid"/> 
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right"
				for="rolename">
				机构信息:
			</label>
			<div class="col-xs-12 col-sm-9">
				<div class="col-sm-6""><!-- class="clearfix" -->
					<input type="text" name="deptname"  id="deptname" class="col-xs-12" />
				</div>
			</div>
		</div>
		
		<div class="space-2"></div>
		
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right"
				for="acginame">
				用户名:
			</label>
			<div class="col-xs-12 col-sm-9">
				<div class="col-sm-6"><!-- class="clearfix" -->
					<input type="text" name="username"  id="username" class="col-xs-12" />
				</div>
			</div>
		</div>
		
		<div class="space-2"></div>
		
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right"
				for="memo">
				IP地址:
			</label>
			<div class="col-xs-12 col-sm-9">
				<div class="col-sm-6"><!-- class="clearfix" -->
					<input type="text" name="useip"  id="useip" class="col-xs-12" />
				</div>
			</div>
		</div>
		<div class="space-2"></div>
		
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right"
				for="memo">
				操作功能:
			</label>
			<div class="col-xs-12 col-sm-9">
				<div class="col-sm-6"><!-- class="clearfix" -->
				<input type="text" name="usemodule"  id="usemodule" class="col-xs-12" />
				</div>
			</div>
		</div>
		<div class="space-2"></div>
		
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right"
				for="memo">
				操作时间:
			</label>
			<div class="col-xs-12 col-sm-9">
				<div class="col-sm-6"><!-- class="clearfix" -->
				<input type="text" name="usetime"  id="usetime" class="col-xs-12" />
				</div>
			</div>
		</div>
		<div class="space-2"></div>
		
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right"
				for="memo">
				操作信息:
			</label>
			<div class="col-xs-12 col-sm-9">
				<div class="col-sm-6"><!-- class="clearfix" -->
				<input type="text" name="useoperation"  id="useoperation" class="col-xs-12" />
				</div>
			</div>
		</div>
	</form>
</div>
<%@include file="/pub/biz/footerinfo.jsp" %>