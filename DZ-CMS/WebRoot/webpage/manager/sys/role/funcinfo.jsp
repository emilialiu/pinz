<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/webpage/pub/biz/head.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/style/jquery/botree.css" type="text/css" />
<div id="sys_role_func_info" >
	<input type="hidden" id="roleid" value="<%=request.getParameter("roleid") %>"/>
	<div class="col-xs-12">
		<div id="sys_role_func_grid_box" class="col-xs-12 col-sm-5  widget-box widget-color-blue">
			<div class="widget-header">
				<h5 class="widget-title"><i class="ace-icon fa fa-sitemap"></i>系统功能</h5>
			</div>
			<div>
				<ul id="unfunc_tree" class="ztree" style="height: 400px;overflow:auto;"></ul>
			</div>
		</div>
		<div class="col-xs-12 col-sm-2 center">
			<div style="margin-top: 150px;">
				<button id="sys_role_func_btn_save" class="btn btn-info btn-primary btn-sm">
					<span class="ace-icon fa fa-arrow-right smaller-30"></span>
					授予
				</button>
				<div class="space-2"></div>
				<button id="sys_role_func_btn_remove" class="btn btn-info btn-pink btn-sm">
					<span class="ace-icon fa fa-arrow-left smaller-30"></span>
					撤销
				</button>
			</div>
		</div>
		<div id="sys_role_func_grid_box" class="col-xs-12 col-sm-5 widget-box widget-color-blue">
			<div class="widget-header">
				<h5 class="widget-title"><i class="ace-icon fa fa-sitemap"></i>已授予功能</h5>
			</div>
			<div>
				<ul id="func_tree" class="ztree" style="height: 400px;overflow:auto;"></ul>
			</div>
		</div>
	</div>
</div>
<%@include file="/webpage/pub/biz/footer.jsp" %>
<script type="text/javascript" src="<%=request.getContextPath()%>/plugins/jquery/botree.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/webpage/manager/sys/role/tree1.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/webpage/manager/sys/role/tree2.js"></script>
<script type="text/javascript">
	$(function(){
		$("#sys_role_func_btn_save").click(function(){
			var checkedNodes = BOTree_weishouyu.getCheckedNodes(true);
		 	var length = checkedNodes.length;
		 	var funcids = '';
		 	for(var i = 0; i < length; i++){
		 		if(!checkedNodes[i].isParent)
		 			funcids += checkedNodes[i].id + ',';
		 	}
		 	funcids = funcids.substring(0, funcids.length-1);
		 	if(funcids == ''){
		 		parent.alertMsg('请选择需要授予给角色的功能');
		 		return;
		 	}
		 	$.ajax({
				url: rootpath+'/manager/sys/rolepopedom/add.action',
				data: { 'funccodes' : funcids, 'bean.roleid':$("#roleid").val() },
				cache: false,
				success: function(data){
					var dataJson = eval(data);
					if(dataJson.success){
						parent.alertMsg('已成功授予角色功能');
						reloadTree();
					}else{
						parent.alertErrorMsg(dataJson.errormessage);
					}
				},
				error: function(data) {
					parent.alertErrorMsg('授予失败'); 
				}
			});
		});

		$("#sys_role_func_btn_remove").click(function(){
			var checkedNodes = BOTree_yishouyu.getCheckedNodes(true);
		 	var length = checkedNodes.length;
		 	var funcids = '';
		 	for(var i = 0; i < length; i++){
		 		if(!checkedNodes[i].isParent)
		 			funcids += checkedNodes[i].id + ',';
		 	}
		 	funcids = funcids.substring(0, funcids.length-1);
		 	if(funcids == ''){
		 		parent.alertMsg('请选择需要撤销的角色功能');
		 		return;
		 	}
		 	$.ajax({
				url: rootpath+'/manager/sys/rolepopedom/delete.action',
				data: { 'funccodes' : funcids, 'bean.roleid':$("#roleid").val() },
				cache: false,
				success: function(data){
					var dataJson = eval(data);
					if(dataJson.success){
						parent.alertMsg('已成功撤销角色功能');
						reloadTree();
					}else{
						parent.alertErrorMsg(dataJson.errormessage);
					}
				},
				error: function(data) {
					parent.alertErrorMsg('撤销失败'); 
				}
			});
		});
	});
	function reloadTree(){
		//初始化未授予功能权限的树
		reoladTree_weishouyu();
		//初始化已授予功能权限的树
		reoladTree_yishouyu();
	}
</script>