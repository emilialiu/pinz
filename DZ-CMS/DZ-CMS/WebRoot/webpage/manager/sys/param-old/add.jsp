<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/taglibs.jsp"%>
<html>
	<head>

		<title>系统参数</title>
		<link rel="stylesheet" type="text/css"
			href="<%=request.getContextPath()%>/style/jquery/boui.css" />
		<link rel="stylesheet" type="text/css"
			href="<%=request.getContextPath()%>/style/jquery/icon.css" />
		<!--[if lt IE 9]>
			<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery-1.9.1.min.js"></script>
		<![endif]-->
		<!--[if gte IE 9]><!-->
			<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery-2.1.0.min.js"></script>
		<!--<![endif]--> 
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/jquery/boui.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/boui-lang-<s:text name="boui.lang" />.js"></script>
		<script type="text/javascript">
			function onSave(){
				if ($('#form').form('validate')){
					$('#addtype').val("");
					$('.boui-linkbutton').attr("disabled",true);
					document.forms[0].submit();
				}
			}
			/**
			保存新增
			*/
			function Savenext(){
				if ($('#form').form('validate')){
					$('#addtype').val("addmore");
					$('.boui-linkbutton').attr("disabled",true);
					document.forms[0].submit();
				}
			}
		  var addtype = '${addtype}';
		  <s:set name="errorFlag" value="errorFlag" />
		  <s:if test='#errorFlag=="addsuccess"'>
		  	 parent.alertInfo("参数信息添加成功!");
		  	if(addtype==''){
		  		parent.tabCloseWin();
		  	}else{
		  		$('.boui-linkbutton').attr("disabled",false);
		  		parent.tabReload();
		  	}
		  </s:if>
		  <s:if test='#errorFlag=="true"'>
		  	 parent.alertError("<s:property value="errorMessage"/>");
		  	if(addtype==''){
			  	 	parent.tabCloseWin();
		  	 }else{
		  	 	$('.boui-linkbutton').attr("disabled",false);
		  	 	parent.tabReload();
		  	 }
		  </s:if>
		</script>
	</head>

	<body class="boui-layout" style="overflow-y: hidden" scroll="no">
		<div region="center" border="false">
			<s:form action="add" method="post" theme="simple" id="form">
			<input type="hidden" name="addtype" id="addtype"/>
			<input type="hidden" name="parameterid" id="parameterid"/>
				<table border="0" align="center">
					<tr>
						<td align="right">
							登陆页logo:
						</td>
						<td>
							<s:file name="bean.loginlogo" id="loginlogo" maxlength="200"
								cssClass="boui-validatebox" data-options="required:true,validType:'length[1,200]'"
								cssStyle="width:276px"></s:file>
						</td>
					</tr>
					<tr>
						<td align="right">
							<nobr>
							登录页名字:
							</nobr>
						</td>
						<td>
							<s:textfield name="bean.loginname" id="loginname" maxlength="200"
								cssClass="boui-validatebox" data-options="required:true,validType:'length[1,200]'"
								cssStyle="width:276px"></s:textfield>
						</td>
					</tr>
					<tr>
						<td align="right">
							首页logo:
						</td>
						<td>
							<s:file name="bean.firstlogo" id="firstlogo" maxlength="200"
								cssClass="boui-validatebox" data-options="required:true,validType:'length[1,200]'"
								cssStyle="width:276px"></s:file>
						</td>
					</tr>
					<tr>
						<td align="right" valign="top">
							首页名字:
						</td>
						<td>
							<s:textfield name="bean.firstname" id="firstname" maxlength="200"
								cssClass="boui-validatebox" data-options="required:true,validType:'length[1,200]'"
								cssStyle="width:276px"></s:textfield>
						</td>
					</tr>
					<tr>
						<td align="right" valign="top">
							是否开启验证码:
						</td>
						<td>
							<s:select name="bean.iscaptcha" list="#{'0':'关闭','1':'开启'}"></s:select> 
						</td>
					</tr>
				</table>
			</s:form>
		</div>
		<div region="south" border="false" class="info-button">
			<a href="javascript:onSave()" class="boui-linkbutton"
				iconCls="icon-save" id="save">保存</a>
			<a href="javascript:Savenext()" class="boui-linkbutton"
				iconCls="icon-save" id="addsave">保存&新增</a>
			<a href="javascript:parent.tabCloseWin()" class="boui-linkbutton"
				iconCls="icon-cancel">关闭</a>
		</div>
	</body>
</html>
