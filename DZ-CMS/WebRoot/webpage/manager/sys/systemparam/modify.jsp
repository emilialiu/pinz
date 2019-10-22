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
			function submitMe(){
				if ($('#form').form('validate')){
					$('.boui-linkbutton').attr("disabled",true);
					document.forms[0].submit();
				}
			}
			  <s:set name="errorFlag" value="errorFlag" />
			  <s:if test='#errorFlag=="modifysuccess"'>
			  	parent.alertInfo("系统配置参数信息修改成功!");
			  	parent.tabCloseWin();
			  </s:if>
			  <s:if test='#errorFlag=="true"'>
			  	 parent.alertInfo("<s:property value="errorMessage"/>");
			  	 parent.tabCloseWin();
			  </s:if>
		</script>
	</head>

	<body class="boui-layout" style="overflow-y: hidden" scroll="no">
		<div region="center" border="false">
			<s:form action="modify" method="post" theme="simple" id="form">
			<s:hidden name="actiontype" value="modifysave"></s:hidden>
				<table border="0" align="center">
					<tr>
						<td align="right">
							参数编号:
						</td>
						<td>
							<s:textfield name="bean.paramcode" id="paramcode" maxlength="100"
								cssClass="boui-validatebox" data-options="required:true,validType:'length[1,100]'"
								cssStyle="width:176px"></s:textfield>
						</td>
					</tr>
					<tr>
						<td align="right">
							<nobr>
							参数名:
							</nobr>
						</td>
						<td>
							<s:textfield maxlength="100" 
								cssClass="boui-validatebox" data-options="required:true,validType:'length[1,100]'" name="bean.paramname"
								cssStyle="width:176px"></s:textfield>
						</td>
					</tr>
					<tr>
						<td align="right">
							参数值:
						</td>
						<td>
							<s:textfield maxlength="100" cssClass="boui-validatebox"
								 data-options="required:true,validType:'length[1,100]'" name="bean.paramvalue"
								cssStyle="width:176px"></s:textfield>
						</td>
					</tr>
					<tr>
						<td align="right" valign="top">
							备注:
						</td>
						<td>
							<s:textarea cssClass="x-form-text" name="bean.memo"
								cssStyle="width:176px"></s:textarea>
						</td>
					</tr>
				</table>
			</s:form>
		</div>
		<div region="south" border="false" class="info-button">
			<a href="javascript:submitMe()" class="boui-linkbutton"
				iconCls="icon-save">保存</a>
			<a href="javascript:parent.tabCloseWin()"
				class="boui-linkbutton" iconCls="icon-cancel">关闭</a>
		</div>
	</body>
</html>
