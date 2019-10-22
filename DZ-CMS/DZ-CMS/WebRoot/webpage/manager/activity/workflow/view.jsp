<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/webpage/pub/biz/headinfo.jsp" %>
<div id="act_workflow_view_info" >
	<form id="act_workflow_view_form" method="post" class="form-horizontal">
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right"
				for="ProcessDefintionID">
				ProcessDefintionID:
			</label>
			<div class="col-xs-12 col-sm-9">
				<div class="col-sm-6"><!-- class="clearfix" -->
					<input type="text" name="ProcessDefintionID" datatype="*" nullmsg="角色名称必填！" id="ProcessDefintionID" class="col-xs-12" />
				</div>
				<div class="col-sm-3">
					<div class="info"><span class="Validform_checktip"></span><span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s></span></div>
				</div>
			</div>
		</div>
		
		<div class="space-2"></div>
		
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right"
				for="deploymentId">
				Deploymentid:
			</label>
			<div class="col-xs-12 col-sm-9">
				<div class="col-sm-6"><!-- class="clearfix" -->
					<input type="text" name="deploymentId" datatype="*" nullmsg="角色编码必填！" id="deploymentId" class="col-xs-12" />
				</div>
				<div class="col-sm-3">
					<div class="info"><span class="Validform_checktip"></span><span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s></span></div>
				</div>
			</div>
		</div>
		
		<div class="space-2"></div>
		
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right"
				for="name">
				名称:
			</label>
			<div class="col-xs-12 col-sm-9">
				<div class="col-sm-6"><!-- class="clearfix" -->
					<input type="text" name="name" datatype="*" nullmsg="角色编码必填！" id="name" class="col-xs-12" />
				</div>
			</div>
		</div>
		<div class="space-2"></div>
		
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right"
				for="key">
				key:
			</label>
			<div class="col-xs-12 col-sm-9">
				<div class="col-sm-6"><!-- class="clearfix" -->
					<input type="text" name="key" datatype="*" nullmsg="角色编码必填！" id="key" class="col-xs-12" />
				</div>
			</div>
		</div>
		
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right"
				for="version">
				版本号:
			</label>
			<div class="col-xs-12 col-sm-9">
				<div class="col-sm-6"><!-- class="clearfix" -->
					<input type="text" name="version" datatype="*" nullmsg="角色编码必填！" id="version" class="col-xs-12" />
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right"
				for="resourceName">
				xml:
			</label>
			<div class="col-xs-12 col-sm-9">
				<div class="col-sm-6"><!-- class="clearfix" -->
					<input type="text" name="resourceName" datatype="*" nullmsg="角色编码必填！" id="resourceName" class="col-xs-12" />
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right"
				for="diagramResourceName">
				图片:
			</label>
			<div class="col-xs-12 col-sm-9">
				<div class="col-sm-6"><!-- class="clearfix" -->
					<input type="text" name="diagramResourceName" datatype="*" nullmsg="角色编码必填！" id="diagramResourceName" class="col-xs-12" />
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right"
				for="deploymentTime">
				部署时间:
			</label>
			<div class="col-xs-12 col-sm-9">
				<div class="col-sm-6"><!-- class="clearfix" -->
					<input type="text" name="deploymentTime" datatype="*" nullmsg="角色编码必填！" id="deploymentTime" class="col-xs-12" />
				</div>
			</div>
		</div>
	</form>
</div>
<%@include file="/webpage/pub/biz/footerinfo.jsp" %>