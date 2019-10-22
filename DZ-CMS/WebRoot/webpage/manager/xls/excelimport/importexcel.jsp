<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>EXCEL文件导入</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<link rel="stylesheet" type="text/css"
			href="<%=request.getContextPath()%>/style/jquery/boui.css" />
		<link rel="stylesheet" type="text/css"
			href="<%=request.getContextPath()%>/style/jquery/icon.css" />
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/boui.js"></script>

		<script type="text/javascript">	
			<s:set name="errorFlag" value="errorFlag" />	
			<s:if test='#errorFlag=="false"'>
				alert("<s:text name='Exicutionsuccessful'/>!");		
			</s:if>
			<s:if test='#errorFlag=="true"'>
			  	alert("<s:property value="errorMessage"/>");
			</s:if>	
			var validflag="false";
			function valid(){
				if(document.getElementById("modelid").value==""){
					validflag="true";
					alert("模板名称不能为空！");
				}else  if(document.getElementById("filen").value==""){
					validflag="true";
					alert("Excel文件不能为空！");
				}
			}
			function onSave(){
			    valid();
				var objname=document.getElementById("filen").value
				document.getElementById("localfilenname").value=objname;
				if(validflag=="false"){
					document.forms[0].submit();
				}
			}
		</script>
	</head>

	<body class="boui-layout" style="overflow-y: hidden" scroll="no">
		<div region="center" border="false">
			<div id="p" class="boui-panel" fit="true" title="EXCEL文件导入"
				style="height: 60px;">
			<s:form action="excelimport" method="post"
				enctype="multipart/form-data" theme="simple">
				<table>
					<s:hidden name="actiontype"></s:hidden>
					<tr>
						<td>
							模板名称:
						</td>
						<td>
							<s:select list="#session.modelcodelist" name="modelid"
								id="modelid" listKey="codekey" listValue="codevalue"
								headerKey="" headerValue="-请选择-"></s:select>
						</td>
					</tr>
					<tr>
						<td>
							Excel文件:
						</td>
						<td>
							<s:file name="filen" id="filen" accept="text/*"></s:file>
							<s:hidden name="localfilenname" id="localfilenname"></s:hidden>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<div style="margin-left:55px;">
								<a href="javascript:onSave()" class="boui-linkbutton"
									iconCls="icon-save">导入</a>
							</div>							
						</td>
					</tr>
				</table>
			</s:form>
			</div>
		</div>
	</body>
</html>