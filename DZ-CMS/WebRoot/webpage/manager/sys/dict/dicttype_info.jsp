<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/webpage/pub/biz/headinfo.jsp"%>
<style type="text/css">
input[type=checkbox].ace.ace-switch.ace-switch-lt+.lbl::before {
	content: "是\a0\a0\a0\a0\a0\a0\a0\a0\a0\a0\a0\否"
}
</style>
<div id="sys_dicttype_info">
	<form id="sys_dicttype_form" method="post" class="form-horizontal">
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right"
				for="rolename">
				<s:text name="dmmes.sys.dict.number" />:<font color="red">*</font>
			</label>
			<div class="col-xs-12 col-sm-9">
				<div class="col-sm-6""><!-- class="clearfix" -->
					<input type="text" name="code" datatype="*" nullmsg="类型编号必填!" readonly="readonly" maxlength="50" id="code" value="${bean.code}" class="col-xs-12" />
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
				<s:text name="dmmes.sys.dict.name" />:<font color="red">*</font>
			</label>
			<div class="col-xs-12 col-sm-9">
				<div class="col-sm-6""><!-- class="clearfix" -->
					<input type="text" name="name" datatype="*" nullmsg="类型名称必填!" maxlength="50" id="name" value="${bean.name}" class="col-xs-12" />
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
				<s:text name="dmmes.sys.dict.whether" />:
			</label>
			<div class="col-xs-12 col-sm-9">
				<div class="col-sm-6"><!-- class="clearfix" -->
				<input type="checkbox" checked="" id="canmodify" name="canmodify" class="FormElement ace ace-switch ace-switch-lt"><span class="lbl"></span> 
				<!-- <select name="bean.canmodify" class="FormElement ui-widget-content ui-corner-all" id="canmodify"
								role="select" size="1"><option role="option" selected="selected"  value="1">是</option>
									<option role="option" value="0">否</option>
							</select>-->
				</div>
			</div>
		</div>
		<div class="space-2"></div>
		
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right"
				for="remark">
				<s:text name="dmmes.qt.assaysample.memo" />:
			</label>
			<div class="col-xs-12 col-sm-9">
				<div class="col-sm-6"><!-- class="clearfix" -->
					<textarea name="remark" id="remark" class="col-sm-12" datatype="byterange" min="0"   max="400">${bean.remark}</textarea>
				</div>
				<div class="col-sm-3">
					<div class="info"><span class="Validform_checktip"></span><span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s></span></div>
				</div>
			</div>
		</div>
	</form>
</div>
<%@include file="/webpage/pub/biz/footerinfo.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/webpage/manager/sys/dict/dicttype_info.js"></script>
<script type="text/javascript">
    var code = '<%=request.getAttribute("bean.code")%>';
	var actiontype = '<%=request.getAttribute("actiontype")%>';
	function onSave() {
		if (actiontype == 'addsave')
			addSave("sys_dicttype");
		else if (actiontype == 'modifysave')
			modifySave("sys_dicttype");
		else if (actiontype == 'deletesave')
			deleteSave("sys_dicttype");
	}
	initvalid("sys_dicttype");
	$(function() {
		if (actiontype == 'view')
			parent.setDialogButtons("sys_dicttype", {
				<s:text name="button.cancel" /> : function() {
					parent.closewin("sys_dicttype");
				}
			});
		else
			parent.setDialogButtons("sys_dicttype", {
				<s:text name="button.cancel" /> : function() {
					parent.closewin("sys_dicttype");
				},
				<s:text name="button.confirm" />  : function() {
					parent.window.frames["sys_dicttype_frame"].onSave();
				}
			});
	});
	$(function(){
	var code=$("#code").val();
	if(code==null||code==""){
    document.getElementById("code").readOnly=false;
	}else{
	document.getElementById("code").readOnly=true;
	};
	});
</script>