<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>角色功能信息</title>
		<link rel="stylesheet" type="text/css"
			href="<%=request.getContextPath()%>/style/jquery/boui.css" />
		<link rel="stylesheet" type="text/css"
			href="<%=request.getContextPath()%>/style/jquery/icon.css" />
		<link rel="stylesheet"
			href="<%=request.getContextPath()%>/style/jquery/botree.css"
			type="text/css" />
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/jquery/jquery.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/jquery/botree.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/jquery/boui.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/boui-lang-<s:text name="boui.lang" />.js"></script>
		<script type="text/javascript" src="tree1.js"></script>
		<script type="text/javascript" src="tree2.js"></script>
		<script type="text/javascript">
		<!--
		//撤销权限的功能(主要操作右边的那个表)
			
	//授予功能
	 function onGetNode() {
	 	var checkedNodes = BOTree_weishouyu.getCheckedNodes(true);
	 	var length = checkedNodes.length;
	 	var funcids = '';
	 	for(var i = 0; i < length; i++){
	 		if(!checkedNodes[i].isParent)
	 			funcids += checkedNodes[i].id + ',';
	 	}
	 	funcids = funcids.substring(0, funcids.length-1);
	 	if(funcids == ''){
	 		parent.alertInfo('请选择需要授予给角色的功能');
	 		return;
	 	}
	 	$.ajax({
				url: rootpath+'/manager/sys/rolepopedom/add.action',
				data: { 'funccodes' : funcids, 'bean.roleid':$("#roleid").val() },
				cache: false,
				success: function(data){
					var dataJson = eval(data);
					if(dataJson.success){
						parent.alertInfo('已成功授予角色功能');
						reloadTree();
					}else{
						parent.alertError(dataJson.errormessage);
					}
				},
				error: function(data) {
					parent.alertError('授予失败'); 
				}
			});
	}
			//撤销功能
	function revokeNode() {
	  	var checkedNodes = BOTree_yishouyu.getCheckedNodes(true);
	 	var length = checkedNodes.length;
	 	var funcids = '';
	 	for(var i = 0; i < length; i++){
	 		if(!checkedNodes[i].isParent)
	 			funcids += checkedNodes[i].id + ',';
	 	}
	 	funcids = funcids.substring(0, funcids.length-1);
	 	if(funcids == ''){
	 		parent.alertInfo('请选择需要撤销的角色功能');
	 		return;
	 	}
	 	$.ajax({
				url: rootpath+'/manager/sys/rolepopedom/delete.action',
				data: { 'funccodes' : funcids, 'bean.roleid':$("#roleid").val() },
				cache: false,
				success: function(data){
					var dataJson = eval(data);
					if(dataJson.success){
						parent.alertInfo('已成功撤销角色功能');
						reloadTree();
					}else{
						parent.alertError(dataJson.errormessage);
					}
				},
				error: function(data) {
					parent.alertError('撤销失败'); 
				}
			});
	}
	
	function reloadTree(){
		//初始化未授予功能权限的树
		reoladTree_weishouyu();
		//初始化已授予功能权限的树
		reoladTree_yishouyu();
	}
	function doclose(){
		parent.closewin('rolefunc');
	}
		//-->
		</script>
	</head>


	<body class="boui-layout">
		<div region="west"
			style="width: 340px; padding: 2px; overflow: hidden;" border="false">
			<div id="p" class="boui-panel" fit="true" title="未授予功能"
				style="height: 60px;">
				<div>
					<ul id="unfunc_tree" class="ztree"></ul>
				</div>
			</div>
		</div>
		<div region="center" style="padding: 2px; overflow: hidden;100px"
			border="false">
			<input type="hidden" id="deptid"
				value="<%=request.getParameter("bean.deptid")%>" />
			<input type="hidden" id="roleid"
				value="<%=request.getParameter("bean.roleid")%>" />
			<table border="0" height="100%" width="100px">
				<tr>
					<td>
						<div style="text-align: center; width:100%; height: 100%;vertical-align:middle;" border="false"
							class="info-button-nobg">
							<a href="javascript:onGetNode()" class="boui-linkbutton"
								iconCls="icon-add">授予</a><p>
							<a href="javascript:revokeNode()" class="boui-linkbutton"
								iconCls="icon-remove">撤销</a><p><br/><br/><br/><br/>
							<a href="javascript:doclose()" class="boui-linkbutton"
								iconCls="icon-cancel">关闭</a>
						</div>
					</td>
				</tr>
			</table>
		</div>
		<div region="east"
			style="width: 340px; padding: 2px; overflow: hidden;" border="false">
			<div id="p" class="boui-panel" fit="true" title="已授予功能"
				style="height: 60px;">
				<div>
					<ul id="func_tree" class="ztree"></ul>
				</div>
			</div>
		</div>
	</body>
</html>