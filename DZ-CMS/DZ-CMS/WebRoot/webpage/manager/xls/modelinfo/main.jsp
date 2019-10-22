<%@ page contentType="text/html;charset=utf-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>模版信息管理</title>
		<link rel="stylesheet" type="text/css"
			href="<%=request.getContextPath()%>/style/jquery/boui.css" />
		<link rel="stylesheet" type="text/css"
			href="<%=request.getContextPath()%>/style/jquery/icon.css" />
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/jquery/jquery.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/jquery/boui.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/jquery/boui-lang-zh_CN.js"></script>
		<script type="text/javascript" src="bizgrid.js"></script>
		<script type="text/javascript" src="typebizgrid.js"></script>
	</head>

	<body class="boui-layout">
		<div region="north"
			style="padding: 2px; overflow: hidden;" border="false">
			<div id="p" class="boui-panel" fit="true" title="查询条件"
				style="height: 60px;">
				<table>
					<tr>
						<td>
							模板名称:
						</td>
						<td>
							<input type="text" name="name" id="name" class="text" />
						</td>
						<td>
							<div class="toolbar">
								<a href="javascript:dotypesearch()" class="boui-linkbutton"
									iconCls="icon-search">查询</a>
							</div>
						</td>
					</tr>
				</table>
			</div>
		</div>
		<div region="west"
			style="width: 395px; padding: 2px; overflow: hidden;" border="false">
			<table id="typegrid"></table>
		</div>
		<div region="center" style="padding: 2px; overflow: hidden;"
			border="false">
			<table id="grid"></table>
		</div>
	</body>
</html>
