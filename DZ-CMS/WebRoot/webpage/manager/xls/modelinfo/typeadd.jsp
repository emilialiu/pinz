<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>

		<title>新增Excel导入模板信息</title>
		<link rel="stylesheet" type="text/css"
			href="<%=request.getContextPath()%>/style/jquery/boui.css" />
		<link rel="stylesheet" type="text/css"
			href="<%=request.getContextPath()%>/style/jquery/icon.css" />
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/jquery/jquery.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/jquery/boui.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/valid/FormValid.js"></script>
		<script type="text/javascript">		
			var errorFlag = '<%=request.getAttribute("errorFlag") %>';
			var message = '<%=request.getAttribute("errorMessage") %>';
			if(errorFlag == 'true'){
				alert(message);
			  	parent.closewin("type");
			}
			if(errorFlag == 'addsuccess'){
			  	 parent.alertInfo("模板信息添加成功!");
			  	 parent.closewin("type","dotypereload()");
			}
			var validflag="false";
			function formvalid(){
				if(document.getElementById("bean.modelname").value==""){
					validflag="true";
					alert("模板名称不能为空！");
				}else  if(document.getElementById("bean.mtable").value==""){
					validflag="true";
					alert("对应表名不能为空！");
				}else  if(document.getElementById("modelfielname").value==""){
					validflag="true";
					alert("Excel模板文件不能为空！");
				}
			}
			function onSave(){
				var objname=document.getElementById("modelfielname").value
				document.getElementById("localfilename").value=objname;
				formvalid();
				if(validflag=="false"){
					document.forms[0].submit();
				}
			}
		</script>
	</head>

	<body class="boui-layout" style="overflow-y: hidden" scroll="no">
		<div region="center" border="false">
		<s:form action="modelinfosmA" method="post" theme="simple" enctype="multipart/form-data">
			<table width="100%" border="0">
				<s:hidden name="bean.modelid"></s:hidden>
				<s:hidden name="actiontype"></s:hidden>
				<s:hidden name="localfilename" id="localfilename"></s:hidden>
				<tr>
					<td align="right">
						模板名称:
					</td>
					<td>					
						<s:textfield maxlength="10" cssClass="text" id="bean.modelname" name="bean.modelname" cssStyle="width:280px" ></s:textfield>&nbsp;<font color="red">*</font>
					</td>
				</tr>
				<tr>
					<td align="right">
						对应表名:
					</td>
					<td>
						<s:select list="#session.tableslist" id="bean.mtable" name="bean.mtable" listKey="codekey" listValue="codevalue" headerKey="" headerValue="-请选择-" ></s:select>
					</td>
				</tr>
				
				<tr>
					<td align="right">
						Excel模板文件:
					</td>
					<td>
						<s:file name="modelfielname" id="modelfielname" accept="text/*" ></s:file>
					</td>
				</tr>
			</table>
		</s:form>
		</div>
		<div region="south" border="false" class="info-button">
			<a href="javascript:onSave()" class="boui-linkbutton" iconCls="icon-save">保存</a>
			<a href="javascript:parent.closewin('type')" class="boui-linkbutton" iconCls="icon-cancel">取消</a>
		</div>
	</body>
</html>
