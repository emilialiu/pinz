<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>用户角色信息管理</title>
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
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/jquery/boui-lang-zh_CN.js"></script>
		<script type="text/javascript" src="bizgrid1.js"></script>
		<script type="text/javascript" src="bizgrid2.js"></script>
		<script type="text/javascript">
		<!--
		//撤销权限的功能(主要操作右边的那个表)
			function addInfo(){
				var results = $('#grid1').datagrid('getSelections');
				if(results == ''){
					parent.alertInfo('请选择要授予的角色');
					return ;
				}
				var roles='';
				for(var i = 0; i < results.length; i++){
					roles += results[i].roleid + ',';
				}
				//这里主要负责和服务器的交互
				$.ajax({
					url: rootpath+'/manager/sys/userrole/userroleadd.action',//路径
					cache: false,
					data: { 'roles' : roles.substring(0, roles.length-1) ,'bean.userid':$("#userid").val()},
					success: function(data){
						var dataJson = eval(data);
						if(dataJson.success){
							parent.alertInfo('角色授予成功');
							doreload();
						}else{
							parent.alertError(dataJson.errormessage);
						}
					},
					error: function(data) {
						parent.alertError('<%=request.getAttribute("errorMessage")%>');
					}
				});
			}
			
			//授予权限的功能(主要操作左边的那个表)
			function deleteInfo(){
				var results = $('#grid2').datagrid('getSelections');
				if(results == ''){
					parent.alertInfo('请选择要撤销的角色!');
					return ;
				}
				var roles='';
				for(var i = 0; i < results.length; i++){
					roles += results[i].roleid + ',';
				}
				//这里主要负责和服务器的交互
				$.ajax({
					url: rootpath+'/manager/sys/userrole/userroledel.action',
					cache: false,
					data: { 'roles' : roles.substring(0, roles.length-1) ,'bean.userid': $("#userid").val()},
					success: function(data){
						var dataJson = eval(data);
						if(dataJson.success){
							parent.alertInfo('撤销角色成功！');
							doreload();
						}else{
							parent.alertError(dataJson.errormessage);
						}
					},
					error: function(data) {
						parent.alertError('<%=request.getAttribute("errorMessage")%>');
					}
				});
			}
		//-->
		</script>
	</head>

	<body class="boui-layout">
		<input type="hidden" id="userid"
			value="<%=request.getParameter("bean.userid")%>">
		<div region="west"
			style="width: 420px; padding: 2px; overflow: hidden;" border="false">
			<table id="grid1"></table>
		</div>
		<div region="center" style="padding: 2px; overflow: hidden;"
			border="false">
			<table border="0" height="100%">
				<tr>
					<td>
						<div style="text-align: center; heoght:30px" border="false" class="info-button-nobg">
							<a href="javascript:addInfo()" class="boui-linkbutton"
								iconCls="icon-save">授予</a>
							<a href="javascript:deleteInfo()" class="boui-linkbutton"
								iconCls="icon-cancel">撤销</a>
						</div>
					</td>
				</tr>
			</table>
		</div>
		<div region="east"
			style="width: 420px; padding: 2px; overflow: hidden;" border="false">
			<table id="grid2"></table>
		</div>
	</body>
</html>