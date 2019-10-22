<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/webpage/pub/biz/headinfo.jsp" %>
<div id="sys_role_info" >
	<form id="sys_role_form" method="post" class="form-horizontal">
		<input type="hidden" id="roleid" name="roleid" value="${bean.roleid}"/>
		<input type="hidden" id="deptid" name="deptid" value="${bean.deptid}"/>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right"
				for="rolename">
				角色名称:
			</label>
			<div class="col-xs-12 col-sm-9">
				<div class="col-sm-6"><!-- class="clearfix" -->
					<input type="text" name="rolename" datatype="byterange"
						min="1" max="50" nullmsg="角色名称必填！" id="rolename" 
						value="${bean.rolename}" class="col-xs-12" />
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
				角色编码:
			</label>
			<div class="col-xs-12 col-sm-9">
				<div class="col-sm-6"><!-- class="clearfix" -->
					<input type="text" name="acginame" datatype="byterange"
						min="1" max="50" nullmsg="角色编码必填！" id="acginame" 
						value="${bean.acginame}" class="col-xs-12" />
				</div>
				<div class="col-sm-3">
					<div class="info"><span class="Validform_checktip"></span><span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s></span></div>
				</div>
			</div>
		</div>
		
		<div class="space-2"></div>
		
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="memo">
				备注:
			</label>
			<div class="col-xs-12 col-sm-9">
				<div class="col-sm-6"><!-- class="clearfix" -->
					<textarea name="memo" id="memo" datatype="byterange"
						min="0" max="1000" class="col-xs-12">${bean.memo}</textarea>
				</div>
				<div class="col-sm-3">
					<div class="info">
						<span class="Validform_checktip"></span><span class="dec"><s
							class="dec1">&#9670;</s><s class="dec2">&#9670;</s>
						</span>
					</div>
				</div>
			</div>
		</div>
	</form>
</div>
<%@include file="/webpage/pub/biz/footerinfo.jsp" %>
<script type="text/javascript" src="<%=request.getContextPath()%>/webpage/manager/sys/role/info.js"></script>
<script type="text/javascript">
	var roleid = '<%=request.getAttribute("bean.roleid")%>';
	var actiontype = '<%=request.getAttribute("actiontype")%>';
	function onSave() {
		if(actiontype=='addsave')
			addSave("sys_role");
		else if(actiontype=='modifysave')
			modifySave("sys_role");
		else if(actiontype=='deletesave')
			deleteSave("sys_role");
	}
	initvalid("sys_role");
	$(function(){
		if(actiontype=='view')
			parent.setDialogButtons("sys_role",{"取消": function() {parent.closewin("sys_role");}});
		else 
			parent.setDialogButtons("sys_role",{"取消": function() {parent.closewin("sys_role");},"确定": function() {parent.window.frames["sys_role_frame"].onSave();}});
	});
</script>