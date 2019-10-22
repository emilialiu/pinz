<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dt">
<html>
	<head>
		<title>通知</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link rel="stylesheet" type="text/css"
			href="<%=request.getContextPath()%>/style/style.css" />
		<script type="text/javascript">
			function doloadevent(){
				document.getElementById("maindiv").style.height = parent.document.body.scrollHeight-80;
				document.getElementById("contentdiv").style.height = parent.document.body.scrollHeight-85-24;
			}
		</script>
	</head>
	<body onload="doloadevent()">
	<div id="maindiv" style="background-color: #ffffff;padding:2px;">
		<div class="box" style="height:100%">
			<div class="title">
				<div style="float: left;">
					&nbsp;我的桌面
				</div>
			</div>
			<div class="content" id="contentdiv">
				<center>
					<table>
						<s:iterator value="list">
							<s:if test='noticeTotal!="0"'>
								<tr>
									<td style="text-align: center;">
										<s:property value="noticeType" />
									</td>

									<td style="text-align: center;">
										<s:property value="noticeTotal" />
									</td>
									<td>
										<s:if test='actionType=="flow"'>
											<a
												href='<%=request.getContextPath()%>/manager/bp/receive/main.jsp'>处理审批流程</a>
										</s:if>
										<s:else>
											<a
												href='<%=request.getContextPath()%>/manager/dm/fileupload/main.jsp'>查看文档内容</a>
										</s:else>
									</td>
								</tr>
							</s:if>
						</s:iterator>
					</table>
				</center>
			</div>
		</div>
		</div>
	</body>
	
</html>

