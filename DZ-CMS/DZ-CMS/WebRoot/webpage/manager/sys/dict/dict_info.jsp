<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/webpage/pub/biz/headinfo.jsp"%>
<style type="text/css">
input[type=checkbox].ace.ace-switch.ace-switch-lt+.lbl::before {
	content: "是\a0\a0\a0\a0\a0\a0\a0\a0\a0\a0\a0\否"
}
</style>
<div id="sys_dict_info" >
	<form id="sys_dict_form" method="post" class="form-horizontal">
		<input type="hidden" name="typeid" id="typeid" value="${bean.typeid}" />
		<input type="hidden" name="parentid" id="parentid" value="${bean.parentid}" />
		<input type="hidden" name="code" id="code" value="${bean.code}" />
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right"
				for="paramname">
				<s:text name="dmmes.sys.dict.resourceName" />:<font color="red">*</font>
			</label>
			<div class="col-xs-12 col-sm-9">
				<div class="col-sm-6""><!-- class="clearfix" -->
					<input type="text" name="paramname" datatype="*" nullmsg="<s:text name="dmmes.pub.Mustfill" /> !" maxlength="50" id="paramname" value="${bean.paramname}" class="col-xs-12" />
				</div>
				<div class="col-sm-3">
					<div class="info"><span class="Validform_checktip"></span><span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s></span></div>
				</div>
			</div>
		</div>
		
		<div class="space-2"></div>
		
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right"
				for="parentid">
				<s:text name="dmmes.sys.dict.parent" />:
			</label>
			<div class="col-xs-12 col-sm-9">
				<div class="col-sm-6""><!-- class="clearfix" -->
					<input type="text"  id="parentname" class="col-xs-12" readonly="readonly" value="${bean.parentname}" onclick="getParentnode()"/>
				</div>
				<div class="col-sm-3">
					
				</div>
			</div>
		</div>
		
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right"
				for="canmodify">
				<s:text name="dmmes.sys.dict.whether" />:
			</label>
			<div class="col-xs-12 col-sm-9">
				<div class="col-sm-6"><!-- class="clearfix" -->
				<input type="checkbox" checked="" id="canmodify" name="canmodify" class="FormElement ace ace-switch ace-switch-lt"><span class="lbl"></span> 
				</div>
			</div>
		</div>
		<div class="space-2"></div>
		
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right"
				for="orderno">
				<s:text name="dmmes.sys.dict.sequence" />:
			</label>
			<div class="col-xs-12 col-sm-9">
				<div class="col-sm-6"><!-- class="clearfix" -->
					<input type="text" name="orderno" id="orderno" value="${bean.orderno}" class="col-sm-2" maxlength="11"
					onkeyup="this.value=this.value.replace(/\D/g,'')"
					 />
				</div>
				<div class="col-sm-3">
					<div class="info"><span class="Validform_checktip"></span><span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s></span></div>
				</div>
			</div>
		</div>
		
		<div class="space-2"></div>
		
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right"
				for="paramremark">
				<s:text name="dmmes.sys.dict.description" />:
			</label>
			<div class="col-xs-12 col-sm-9">
				<div class="col-sm-6"><!-- class="clearfix" -->
					<textarea name="paramremark" id="paramremark" class="col-sm-12" datatype="byterange" min="0"   max="400">${bean.paramremark}</textarea>
				</div>
				<div class="col-sm-3">
					<div class="info"><span class="Validform_checktip"></span><span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s></span></div>
				</div>
			</div>
		</div>
	</form>
</div>
<%@include file="/webpage/pub/biz/footerinfo.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/webpage/manager/sys/dict/dict_info.js"></script>
<script type="text/javascript">
    var code = '<%=request.getAttribute("bean.code")%>';
	var actiontype = '<%=request.getAttribute("actiontype")%>';
	initvalid("sys_dict");
	function onSave() {
		if (actiontype == 'addsave')
			addSave("sys_dict");
		else if (actiontype == 'modifysave')
			modifySave("sys_dict");
		else if (actiontype == 'deletesave')
			deleteSave("sys_dict");
	}
	$(function() {
		if (actiontype == 'view')
			parent.setDialogButtons("sys_dict", {
				<s:text name="button.cancel" /> : function() {
					parent.closewin("sys_dict");
				}
			});
		else
			parent.setDialogButtons("sys_dict", {
				<s:text name="button.cancel" /> : function() {
					parent.closewin("sys_dict");
				},
				<s:text name="button.confirm" /> : function() {
					parent.window.frames["sys_dict_frame"].onSave();
				}
			});
	});
	//显示父节点的信息
function getParentnode(){
	parent.openwin("selectsys_dict","/webpage/pub/select/dict_parentnode/main.jsp",'<s:text name="dmmes.sys.dict.parent" />',1100,600);
	parent.PageObject.itemMap['selectsys_dict'].callback = function(no){
		selectParentCallBack(no);
	};
}	
//选择节点的回调函数
function selectParentCallBack(arr){
	$("#parentname").val(arr[0].paramname);
	$("#parentid").val(arr[0].code);
}
</script>