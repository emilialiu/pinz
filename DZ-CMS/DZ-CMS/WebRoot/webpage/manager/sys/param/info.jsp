<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/webpage/pub/biz/headinfo.jsp"%>
<div id="sys_param_info">
	<form id="sys_param_form" method="post" class="form-horizontal">
		<input type="hidden" id="paramcode" name="paramcode"  value="${bean.paramcode}"/>

		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right"
				for="paramname">参数名称</label>
			<div class="col-xs-12 col-sm-9">
				<div class="col-sm-6">
					<!-- class="clearfix" -->
					<input type="text" name="paramname" id="paramname" datatype="*"  value="${bean.paramname}"
						nullmsg="参数名称信息必填!" class="col-xs-12" />
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
				for="paramvalue">参数值</label>
			<div class="col-xs-12 col-sm-9">
				<div class="col-sm-6">
					<!-- class="clearfix" -->
					<input type="text" name="paramvalue" id="paramvalue" datatype="*" value="${bean.paramvalue}"
						nullmsg="参数值信息必填!" class="col-xs-12" />
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
				for="memo">备注</label>
			<div class="col-xs-12 col-sm-9">
				<div class="col-sm-6">
					<!-- class="clearfix" -->
					<input type="text" name="memo" id="memo" value="${bean.memo}" class="col-xs-12" />
				</div>
				<div class="col-sm-3"></div>
			</div>
		</div>

	</form>
</div>
<%@include file="/webpage/pub/biz/footerinfo.jsp"%>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/webpage/manager/sys/param/info.js"></script>
<script type="text/javascript">
    var paramcode = '<%=request.getAttribute("bean.paramcode")%>';
	var actiontype = '<%=request.getAttribute("actiontype")%>';
	function onSave() {
		if (actiontype == 'addsave')
			addSave("sys_param");
		else if (actiontype == 'modifysave')
			modifySave("sys_param");
		else if (actiontype == 'deletesave')
			deleteSave("sys_param");
	}
	initvalid("sys_param");
	$(function() {
		if (actiontype == 'view')
			parent.setDialogButtons("sys_param", {
				"取消" : function() {
					parent.closewin("sys_param");
				}
			});
		else
			parent.setDialogButtons("sys_param", {
				"取消" : function() {
					parent.closewin("sys_param");
				},
				"确定" : function() {
					parent.window.frames["sys_param_frame"].onSave();
				}
			});
	});
</script>