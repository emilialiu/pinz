<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>新增Excel导入模板与列对应信息</title>
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
			var errorFlag = '<%=request.getAttribute("errorFlag")%>';
			var message = '<%=request.getAttribute("errorMessage")%>';
			if(errorFlag == 'true'){
				alert(message);
			  	parent.closewin("type");
			}
			if(errorFlag == 'modifysuccess'){
			  	 parent.alertInfo("模板信息修改成功!");
			  	 parent.closewin("info","doreload()");
			}			
		</script>
	</head>

	<body class="boui-layout" style="overflow-y: hidden" scroll="no">
		<div region="center" border="false">
			<s:form action="modelmappsmM" method="post" theme="simple">
				<table width="100%" border="0">
					<tr>
						<td align="right">
							Excel列名:
						</td>
						<td>
							<s:hidden name="bean.mid"></s:hidden>
							<s:hidden name="bean.modelid"></s:hidden>
							<s:hidden name="bean.ecolname" id="bean.ecolname"></s:hidden>
							<s:hidden name="actiontype" id="actiontype"></s:hidden>
							<s:select list="#session.excellist" name="bean.ecolindex"
								id="bean.ecolindex" listKey="codekey" listValue="codevalue"
								headerKey="" headerValue="-请选择-"></s:select>
						</td>
					</tr>
					<tr>
						<td align="right">
							对应表字段:
						</td>
						<td>
							<s:select list="#session.fieldslist1" name="bean.mfield"
								id="bean.mfield" listKey="codekey" listValue="codevalue"
								headerKey="" headerValue="-请选择-"></s:select>
						</td>
					</tr>

					<tr>
						<td align="right">
							是否允许为空:
						</td>
						<td>
							<s:radio name="bean.isnull" id="bean.isnull"
								list="#{'1':'是','0':'否'}" cssClass="text"></s:radio>
						</td>
					</tr>
					<tr>
						<td align="right">
							是否转码:
						</td>
						<td>
							<s:radio name="bean.isscode" id="bean.isscode"
								list="#{'1':'是','0':'否'}" cssClass="text"
								onclick="hiddencodetext();"></s:radio>
						</td>
					</tr>
					<tr>
						<td align="right">
							码表父类:
						</td>
						<td>
							<s:select list="#session.codefieldslist" name="bean.dicttypeid"
								id="bean.dicttypeid" listKey="codekey" listValue="codevalue"
								headerKey="" headerValue="-请选择-"></s:select>
						</td>
					</tr>
				</table>
			</s:form>
		</div>
		<div region="south" border="false" class="info-button">
			<a href="javascript:onSave()" class="boui-linkbutton"
				iconCls="icon-save">保存</a>
			<a href="javascript:parent.closewin('info')" class="boui-linkbutton"
				iconCls="icon-cancel">取消</a>
		</div>
	</body>
</html>
<script type="text/javascript">
			function setEcolIndex(vo){
				alert(document.getElementById("bean.ecolindex").options[vo].Text);
				$("#bean.ecolname").val(vo);
			}
			function submitMe(){
				var ecolnamev = document.getElementById("bean.ecolindex").value;
				if(ecolnamev == ''){
					alert('<s:text name="Excel列名不能为空！"/>');
					return false;
				}
				var mfieldv = document.getElementById("bean.mfield").value;
				if(mfieldv == ''){
					alert('<s:text name="对应表字段不能为空！"/>');
					return false;
				}
				if(document.getElementById("bean.isscode1").checked){
					var dicttypeidv = document.getElementById("bean.dicttypeid").value;
					if(dicttypeidv == ''){
						alert('<s:text name="码表父类不能为空！"/>');
						return false;
					}
				}
				return true;
			}
			function onSave(){
				//获取Excel列名称
				var seleobj=document.getElementById("bean.ecolindex");
				var index= seleobj.selectedIndex;
				var valueobj= seleobj.options[index].text;
				//alert(valueobj);
				var setobj=document.getElementById("bean.ecolname");
				setobj.value=valueobj;
				//为了将不可用状态的值用于Form表单上传
				document.getElementById("bean.ecolindex").disabled=false;
				document.getElementById("bean.mfield").disabled=false;
				if (submitMe()){
					document.forms[0].submit();
				}
			}
			//修改页面使其不可用
			if( document.getElementById("actiontype").value=='modifysave'){
 				document.getElementById("bean.ecolindex").disabled=true;
 				document.getElementById("bean.mfield").disabled=true;
 			}else{
 				document.getElementById("bean.ecolindex").disabled=false;
 				document.getElementById("bean.mfield").disabled=false;
			}
			//根据是否转码的值控制码表父类可选与否（bean.isscode0或者bean.isscode1是控件自动加上0和1）
			if( document.getElementById("bean.isscode0").checked){
 				document.getElementById("bean.dicttypeid").disabled=true;
			}else{
 				document.getElementById("bean.dicttypeid").disabled=false;
			}
			 
			function hiddencodetext(){
				if( document.getElementById("bean.isscode0").checked){
 					document.getElementById("bean.dicttypeid").disabled=true;
 				}
				if( document.getElementById("bean.isscode1").checked){
 					document.getElementById("bean.dicttypeid").disabled=false;
 				}
			}
</script>