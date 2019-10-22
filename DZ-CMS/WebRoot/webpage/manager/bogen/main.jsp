<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String sessionID = session.getId();
%>
<!DOCTYPE html>
<html>
	<head>
		<title>框架代码自动生成系统</title>
		<STYLE TYPE="text/CSS">
			.scenter {
				width: 100%;
				display: block;
				margin-left: 2px;
			}
			
			.fcenter {
				width: 100%;
				display: block;
			}
			
			.mtop {
				_margin-top: -5px;
			}
			
			.cheiht {
				margin-top: 0;
				margin-top: 0px\0;
			}
			
			#table {
				border: solid 1px #B9D3EA;
				border-collapse: collapse;
				width: 100%;
				margin: 0;
				padding: 0;
			}
			
			#table td {
				font-size: 12px;
				padding: 5px;
				border: solid 1px #B9D3EA;
			}
			
			#cb1 {
				width: 293px;
			}
			
		</STYLE>


		<!-- ace settings handler -->
		<script src="<%=request.getContextPath()%>/plugins/assets/js/ace-extra.min.js"></script>

		<!-- HTML5 shim and Respond.min.js IE8 support of HTML5 elements and media queries -->

		<!--[if lte IE 8]>
		<script src="<%=request.getContextPath()%>/plugins/components/html5shiv/dist/html5shiv.min.js"></script>
		<script src="<%=request.getContextPath()%>/plugins/components/respond/dest/respond.min.js"></script>
		<![endif]-->

		<script type="text/javascript" src="<%=request.getContextPath()%>/plugins/combobox/babu.common.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/plugins/combobox/babu.combobox.js"></script>
		<script type="text/javascript">
			var rootpath = "<%=path%>";
			var sessionID = "<%=sessionID%>";
			
			//监听回车事件,兼容火狐与IE
			document.onkeydown=keyListener;    
			function keyListener(e){    
				e = e ? e : event;   
				if(e.keyCode == 13){    
					connectDB(); 
				}    
			}
			//DB类型改变SID值方法
			function changeDBType(){
				var DBType = $("#DBType").val();
				if(DBType == 'ORACLE'){
					$("#sid").val('localhost:1521:orcl');
					$("#username").val('dimine');
					$("#password").val('dimine');
				}else if(DBType == 'SQLSERVER'){
					$("#sid").val('localhost:1433;DatabaseName=wfland');
					$("#username").val('sa');
					$("#password").val('dimine');
				}else if(DBType == 'MYSQL'){
					$("#sid").val('localhost:3306/dmframe3');
					$("#username").val('root');
					$("#password").val('root');
				}
			}
		</script>
	</head>

	<body>
		<table>
			<tr>
				<td width="300px">
					<table id="table">
						<tr>
							<td align="right">
								<nobr>
									<font
										style="font-size: 12px; font-weight: bold; color: #0C3F78;">DB类型：</font>
								</nobr>
							</td>
							<td>
								<select id="DBType" onchange="changeDBType()" class="chosen-select form-control" data-placeholder="Choose a State...">
									<option value="SQLSERVER">
										SQLSERVER
									</option>
									<option value="ORACLE">
										ORACLE
									</option>
									<option value="MYSQL">
										MYSQL
									</option>
								</select>
							</td>
						</tr>
						<tr>
							<td align="right">
								<nobr>
									<font
										style="font-size: 12px; font-weight: bold; color: #0C3F78;">DB-SID：</font>
								</nobr>
							</td>
							<td>
								<input maxlength="200" size="40" class="text"
									value="localhost:1433;DatabaseName=wfland" name="sid" id="sid"
									valid="required" errmsg="数据库SID为必填项" />
							</td>
						</tr>
						<tr>
							<td align="right">
								<nobr>
									<font
										style="font-size: 12px; font-weight: bold; color: #0C3F78;">用户名：</font>
								</nobr>
							</td>
							<td>
								<input maxlength="20" size="40" class="text" name="username"
									id="username" value="sa" valid="required"
									errmsg="用户名为必填项" />
							</td>
						</tr>
						<tr>
							<td align="right">
								<nobr>
									<font
										style="font-size: 12px; font-weight: bold; color: #0C3F78;">密&nbsp;
										&nbsp; 码：</font>
								</nobr>
							</td>
							<td>
								<input maxlength="20" size="40" class="text" name="password"
									id="password" value="dimine" valid="required" errmsg="密码为必填项" />
							</td>
						</tr>
						<tr>
							<td colspan="2" align="center">
								<input type="button" id="connect" name="connect" value="连接数据库"
									onClick="connectDB();" />
							</td>
						</tr>
					</table>
				</td>
				<td width="600px">
					<table id="table">
						<tr>
							<td>
								<nobr style="color:#0C3F78;">
									表&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名:
								</nobr>
								<input id="cb1" name="cb1" />
							</td>
							<td>
								<nobr style=" color:#0C3F78;">
									页面是否显示主键
								</nobr>
								<input type="checkbox" name="isShowPK" id="isShowPK" />
							</td>
						</tr>
						<tr>
							<td>
								<nobr style=" color:#0C3F78;">
									类文件名:
								</nobr>
								<input name="className" id="className" maxlength="30" size="44" class="text" readonly="readonly"/>
							</td>
							<td>
								<nobr style=" color:#0C3F78;">
									页面标题:
								</nobr>
								<input name="titleName" id="titleName" maxlength="30" size="20" class="text" />
								<span style="color: red; font-weight: bold;"> *</span>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<nobr style=" color:#0C3F78;">
									配置模块:
								</nobr>
								<input name="srcRoot" id="srcRoot" maxlength="30" size="44"
									class="text" value="" />
								<span style="color: red; font-weight: bold;">
									*(不以&nbsp;/&nbsp;开头,&nbsp;多级用&nbsp;/&nbsp;隔开)</span>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<nobr style=" color:#0C3F78;">
									页面路径:
								</nobr>
								<input name="webRoot" id="webRoot" maxlength="30" size="44"
									class="text" />
								<span style="color: red; font-weight: bold;">
									*(不以&nbsp;/&nbsp;开头,&nbsp;多级用&nbsp;/&nbsp;隔开)</span>
							</td>
						</tr>
					</table>
				</td>
				<td>
					<div style="width: 100%; margin-left: 5px;">
						<textarea style="width:140px;height:112px" id="tablesTextarea">已生成代码的表:</textarea>
					</div>
				</td>
			</tr>
		</table>

		<div class="scenter">
			<iframe id="listIframe" name="listIframe" width="100%" height="300"
				frameborder="0" style="border: #98C3E5 1px solid;" src="list.jsp"></iframe>
		</div>

		<div class="fcenter">
			<table cellpadding="2" cellspacing="0" border="0" align="center"
				width="100%">
				<tr align="right">
					<td class="lable">
						<hidden name="previewPath" id="previewPath"></hidden>
					</td>
					<td class="lable">
						<hidden name="tablenameHidden" id="tablenameHidden"></hidden>
					</td>
					<td class="lable">
						<input type="button" id="ok" name="value" value="操作说明"
							class="ext_button" onClick="showHelp();" />
						<%--
						<input type="button" id="ok" name="value" value="生成该表到WORD文件"
							class="ext_button" onClick="createWord('myTable_thisweek');" />

						<input type="button" id="ok" name="value" value="生成所有表到WORD文件"
							class="ext_button" onClick="createWordAll();" />
						 --%>
						<input type="button" id="ok" name="value" value="生成源代码"
							class="ext_button" onClick="getSelectValue('myTable_thisweek');" />
						<%--
						<input type="button" id="ok" name="value" value="预览页面"
							class="ext_button" onClick="showPage();" />
						--%>
						<input type="button" name="button2" value="下载源代码"
							class="ext_button" onClick="downFile();" />
					</td>
				</tr>
			</table>
		</div>


		<!--[if !IE]> -->
		<script src="<%=request.getContextPath()%>/plugins/components/jquery/dist/jquery.js"></script>
		<!-- <![endif]-->
	
		<!--[if IE]>
		<script src="<%=request.getContextPath()%>/plugins/components/jquery.1x/dist/jquery.js"></script>
		<![endif]-->
	
		<script type="text/javascript">
			if('ontouchstart' in document.documentElement) document.write("<script src='<%=request.getContextPath()%>/plugins/components/_mod/jquery.mobile.custom/jquery.mobile.custom.js'>"+"<"+"/script>");
		</script>
		<script src="<%=request.getContextPath()%>/plugins/components/bootstrap/dist/js/bootstrap.min.js"></script>
		<script src="<%=request.getContextPath()%>/plugins/components/typeahead.js/dist/typeahead.jquery.min.js"></script>
		<script src="<%=request.getContextPath()%>/plugins/components/jquery-ui/jquery-ui.min.js"></script>
		<!-- ace scripts -->
		<script src="<%=request.getContextPath()%>/plugins/assets/js/ace.min.js"></script>
		<script src="<%=request.getContextPath()%>/plugins/assets/js/ace-elements.min.js"></script>
		<script src="main.js"></script>

	</body>
</html>