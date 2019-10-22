<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/webpage/pub/biz/headinfo.jsp" %>
<body>
<div id="orgcfg_info">
	<form id="orgcfg_form" method="post" class="form-horizontal">
		<input type="hidden" id="orgcfgid" name="orgcfgid" value="${bean.orgcfgid}"/>

		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right"
				for="orgtype">组织机构类型</label>
			<div class="col-xs-12 col-sm-9">
				<div class="col-sm-6">
					<!-- class="clearfix" -->
					<dmtag:sourcemanager sourcename="ZZJGLX" name="orgtype"
						cssClass="form-control" isnull="false" fixValue="${bean.orgtype}"></dmtag:sourcemanager>
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
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right"
				for="isorg">是否是组织</label>
			<div class="col-xs-12 col-sm-9">
				<div class="col-sm-6">
					<!-- class="clearfix" -->
					<select id="isorg" name="isorg" style="width:60px">
						<option value="0" selected="selected">否</option>
						<option value="1">是</option>
					</select>
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
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right"
				for="demo">备注</label>
			<div class="col-xs-12 col-sm-9">
				<div class="col-sm-6">
					<!-- class="clearfix" -->
					<textarea name="demo" id="demo" datatype="byterange"
						min="0" max="1000" class="col-xs-12">${bean.demo}</textarea>
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
</body>
<%@include file="/webpage/pub/biz/footerinfo.jsp" %>
<script type="text/javascript" src="<%=request.getContextPath()%>/webpage/biz/sm/orgcfg/info.js"></script>
<script type="text/javascript">
	var orgcfgid = '<%=request.getAttribute("bean.orgcfgid")%>';
	var isorg = '<%=request.getAttribute("bean.isorg")%>';
	var actiontype = '<%=request.getAttribute("actiontype")%>';
	function onSave() {
		if(actiontype=='addsave')
			addSave("orgcfg");
		else if(actiontype=='modifysave')
			modifySave("orgcfg");
		else if(actiontype=='deletesave')
			deleteSave("orgcfg");
	}
	initvalid("orgcfg");
	$(function(){
		$("#isorg").val(isorg);
		if(actiontype=='view')
			parent.setDialogButtons("orgcfg",{"取消": function() {parent.closewin("orgcfg");}});
		else 
			parent.setDialogButtons("orgcfg",{"取消": function() {parent.closewin("orgcfg");},"确定": function() {parent.window.frames["orgcfg_frame"].onSave();}});
	});
</script>
</html>