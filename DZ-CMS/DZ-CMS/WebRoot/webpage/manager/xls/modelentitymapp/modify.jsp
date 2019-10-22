<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>新增实体映射信息</title>
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
		
	</head>
	<body class="boui-layout" style="overflow-y: hidden" scroll="no">
		<div region="center" border="false">
			<s:form action="modify" method="post" theme="simple">
				<table width="100%" border="0">
					<s:hidden name="actiontype" id="actiontype"></s:hidden>
					<tr>
						<td align="right">
							数据库表名:
						</td>
						<td>
							<s:select list="#session.tablenamelist" name="bean.tablename"
								id="bean.tablename" listKey="codekey" listValue="codevalue"
								headerKey="" headerValue="-请选择-" disabled="true"></s:select>
						</td>
					</tr>
					<tr>
						<td align="right">
							是否指定自动生成列值:
						</td>
						<td>
							<s:radio name="bean.isauto" id="bean.isauto"
								list="#{'1':'是','0':'否'}" cssClass="text"
								onclick="hiddencodetext();"></s:radio>
						</td>
					</tr>
					<tr>
						<td align="right">
							对应自动键值列(多个请用,号隔开):
						</td>

						<td>
							<s:textfield maxlength="100" cssClass="text" name="bean.dbkey"
								id="bean.dbkey" cssStyle="width:280px;"></s:textfield>
							&nbsp;
							<font color="red">*</font>
						</td>
					</tr>
					<tr>
						<td align="right">
							实体类(包)完整引用:
						</td>
						<td>
							<s:textfield cssClass="text" name="bean.entityclass"
								id="bean.entityclass" cssStyle="width:280px"></s:textfield>
							&nbsp;
							<font color="red">*</font>
						</td>
					</tr>
				</table>
			</s:form>
		</div>
		<div region="south" border="false" class="info-button">
			<a href="javascript:onSave()" class="boui-linkbutton" iconCls="icon-save">保存</a>
			<a href="javascript:parent.closewin('modelentitymapp')" class="boui-linkbutton" iconCls="icon-cancel">取消</a>
		</div>
	</body>
</html>
<script type="text/javascript">
		  	var errorFlag = '<%=request.getAttribute("errorFlag") %>';
			var message = '<%=request.getAttribute("errorMessage") %>';
			if(errorFlag == 'true'){
				alert(message);
			  	parent.closewin("modelentitymapp");
			}
			if(errorFlag == 'modifysuccess'){
			  	 parent.alertInfo("实体映射信息修改成功!");
			  	 parent.closewin("modelentitymapp","doreload()");
			}
		  	function validform(){
		   		var tablenamev = document.getElementById("bean.tablename").value;
				if(tablenamev == ''){
					alert('数据库表名不能为空!');
					return false;
				}
				if(document.getElementById("bean.isauto1").checked){
					var dbkeyv = document.getElementById("bean.dbkey").value;
					if(dbkeyv == ''){
						alert('对应表主键不能为空!');
						return false;
					}
				}else{
					return true;
				}
				var entityclassv = document.getElementById("bean.entityclass").value;
				if(entityclassv == ''){
					alert('实体类(包)完整引用不能为空!');
					return false;
				}
				return true;
		 	}		 	
			function onSave(){
				if (validform()){
					document.getElementById("bean.tablename").disabled=false;
					document.forms[0].submit();
				}
			}
			//根据是否指定自动生成列值控制对应自动键值列(多个请用,号隔开)可填与否,（bean.isauto0或者bean.isauto1是控件自动加上0和1）
			if( document.getElementById("bean.isauto0").checked){
 				document.getElementById("bean.dbkey").disabled=true;
			}else{
 				document.getElementById("bean.dbkey").disabled=false;
			}
			function hiddencodetext(){
				if( document.getElementById("bean.isauto0").checked){
 					document.getElementById("bean.dbkey").disabled=true;
 				}
				if( document.getElementById("bean.isauto1").checked){
 					document.getElementById("bean.dbkey").disabled=false;
 				}
			}
</script>