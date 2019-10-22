<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/webpage/pub/biz/headinfo.jsp" %>
<body>
<div id="dz_commodity_category_info" >
	<form id="dz_commodity_category_form" action="<%=request.getContextPath()%>/webpage/biz/commodity/category/doAddSave.action" method="post" class="form-horizontal">
		<input type="hidden" id="id" name="id" value="${bean.id}"/>

			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-3 no-padding-right"
					for="name">分类名称</label>
				<div class="col-xs-12 col-sm-9">
					<div class="col-sm-6">
						<!-- class="clearfix" -->
						<input type="text" name="name" id="name" value="${bean.name}"
							class="col-xs-12" />
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-3 no-padding-right"
					for="sex">性别</label>
				<div class="col-xs-12 col-sm-9">
					<div class="col-sm-6">
						<!-- class="clearfix" -->
						<select id="sex" class="col-xs-12" >
							<option value="0">男</option>
							<option value="1">女</option>
						</select>
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-3 no-padding-right"
					for="remarks">备注</label>
				<div class="col-xs-12 col-sm-9">
					<div class="col-sm-6">
						<!-- class="clearfix" -->
						<textarea name="remarks" id="remarks" class="col-xs-12">${bean.remarks}</textarea>
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-3 no-padding-right"
					for="sort">排序</label>
				<div class="col-xs-12 col-sm-9">
					<div class="col-sm-6">
						<!-- class="clearfix" -->
						<input type="text" name="sort" id="sort" value="${bean.sort}"
							class="col-xs-12" />
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>

		</form>
</div>
</body>
<%@include file="/webpage/pub/biz/footerinfo.jsp" %>
<script type="text/javascript" src="<%=request.getContextPath()%>/webpage/biz/commodity/category/info.js"></script>
<script type="text/javascript">
	var id = '<%=request.getAttribute("bean.id")%>';
	var actiontype = '<%=request.getAttribute("actiontype")%>';
	function onSave() {
		if(actiontype=='addsave')
			addSave("dz_commodity_category");
		else if(actiontype=='modifysave')
			modifySave("dz_commodity_category");
		else if(actiontype=='deletesave')
			deleteSave("dz_commodity_category");
	}
	initvalid("dz_commodity_category");
	var sex = '<%=request.getAttribute("bean.sex")%>';
	$(function(){
	$("#sex  option[value='"+sex+"']").attr("selected","selected");
		if(actiontype=='view')
			parent.setDialogButtons("dz_commodity_category",{"取消": function() {parent.closewin("dz_commodity_category");}});
		else 
			parent.setDialogButtons("dz_commodity_category",{"取消": function() {parent.closewin("dz_commodity_category");},"确定": function() {parent.window.frames["dz_commodity_category_frame"].onSave();}});
	});
</script>
</html>