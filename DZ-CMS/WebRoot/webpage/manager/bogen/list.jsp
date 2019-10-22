<%@ page language="java" import="java.util.*,com.dimine.bogen.model.ColumnsEntity" pageEncoding="utf-8"%>
<%
	List<ColumnsEntity> columnsList = (List<ColumnsEntity>) request.getAttribute("columnsList");
	String tablename = (String) request.getAttribute("tablename");
	String tablecomments = (String) request.getAttribute("tablecomments");
	String[] pk = (String[]) request.getAttribute("pk");
%>
<html>
	<head>
		<title>list</title>
		<link href="<%=request.getContextPath()%>/style/grid/public.css" type=text/css rel=stylesheet>
		<script src="<%=request.getContextPath()%>/plugins/grid/beyond_grid.js"></script>
		<!-- ace settings handler -->
		<script src="<%=request.getContextPath()%>/plugins/assets/js/ace-extra.min.js"></script>

		<!-- HTML5 shim and Respond.min.js IE8 support of HTML5 elements and media queries -->

		<!--[if lte IE 8]>
		<script src="<%=request.getContextPath()%>/plugins/components/html5shiv/dist/html5shiv.min.js"></script>
		<script src="<%=request.getContextPath()%>/plugins/components/respond/dest/respond.min.js"></script>
		<![endif]-->
	</head>
	
		

		<div id="selectState" style="display: none;">
			<select size="1">
				<option value="choose" style="color: #CCCCCC">
					-请选择HTML控件类型-
				</option>	
				<option value="text" selected="selected">
					文本框
				</option>	
				<option value="select">
					下拉选择
				</option>
				<option value="date">
					时间控件
				</option>
				<option value="textarea">
					多行文本
				</option>
				<option value="file">
					文件上传
				</option>
				<option value="checkbox">
					复选框
				</option>
				<option value="radio">
					单选
				</option>
				<option value="label">
					文本
				</option>
				<option value="hidden">
					隐藏
				</option>
			</select>	
		</div>
	
		<div id="select" style="display: none;">	
			<select size="1">
				<option value="choose" style="color: #CCCCCC">
					-请选择校验规则-
				</option>	
				<option value="required">
					必填
				</option>
				<option value="*6-16">
					6到16位任意字符
				</option>
				<option value="isNumber">
					请填写数字
				</option>
				<option value="n6-16">
					6到16位数字
				</option>
				<option value="isInvalid">
					不能输入特殊字符
				</option>
				<option value="s6-16">
					6到18位字符
				</option>
				<option value="isEmail">
					必须是Email格式
				</option>
				<option value="isMobile">
					是否为手机号码
				</option>
				<option value="isZip">
					是否为邮政编码
				</option>
				<option value="isUrl">
					是否为url
				</option>
			</select>	
		</div>
	
		<table cellpadding="2" cellspacing="0" border="0" align="center" width="100%">
			<tr>
				<td style="font-weight: 600; background-color: #98C3E5";">
					<font color="white">表字段信息</font>
				</td>
			</tr>
		</table>
	
		<div style="width: 100%; background-color: #FFFFFF; text-align: left;">
			<table id="myTable_thisweek" cellpadding="2" cellspacing="1"
				border="0" align="left" width="100%">
				<thead>
					<tr>
						<th width="1%">
							<input id="thisweek" type="checkbox" title="全选"
								onClick="TE_thisweek.selectAll(this)"></input>
						</th>
						<th width="15%">
							列名
						</th>
						<th width="16%">
							列描述
						</th>
						<th width="10%">
							是否表单显示
						</th>
						<th width="10%">
							是否列表显示
						</th>
						<th width="10%">
							是否主键
						</th>
						<th width="10%">
							是否查询条件
						</th>
						<th width="8%">
							字段类型
						</th>
						<th width="10%">
							HTML控件类型
						</th>
						<th width="10%">
							校验规则
						</th>
					</tr>
				</thead>
				<tbody>
					<%
						if (columnsList != null && columnsList.size() != 0) {
							int i = 0;
							for (ColumnsEntity entity : columnsList) {
								i++;
					%>
					<tr>
						<td><%=i%></td>
						<td><%=entity.getColumn_name()%></td>
						<%if(entity.getComments() != null){ %>
							<td><%=entity.getComments()%></td>
						<% } else {%>
							<td></td>
						<% } %>
						<td align="center">1</td>
						<td align="center">1</td>
						<%								
							int count = 0;
								for(int j=0;j<pk.length;j++){
									if(pk[j].equals(entity.getColumn_name())){
										count += 1;
									}
								}
						%>						
						<%if(count>0){ %>
						<td align="center">1</td>
						<%}else{ %>
						<td align="center">0</td>
						<%} %>
						<td align="center"></td>
						<td><%=entity.getData_type()%></td>
						<td>choose</td>
						<td>choose</td>
					</tr>
					<%
						}
					} 
					%>
				</tbody>
			</table>
		</div>
		<br>
		
		<script type="text/javascript">
			var tablename = "<%=tablename%>";
			var tablecomments = "<%=tablecomments%>";			
			var thisweek="";
			var rpt_taskidArr;
			
			var TE_thisweek = new beyond_grid();
			TE_thisweek.setMaxLength("1,2,3,4,5,6","10,20,50,10,10,10");
			TE_thisweek.init("myTable_thisweek","id,code,edit,checkBox,checkBox,checkBox,checkBox,code,comBoxselectState,comBoxselect");				
			
			//list初始化时全选记录
		    window.onload = thisweekSelectAll;					    
			function thisweekSelectAll(){
				document.getElementById("thisweek").checked=true;
			    TE_thisweek.selectAll(document.getElementById("thisweek"));
			}
			
			//生成源码获取列表选择值
			function getSelectValue(tableId,isShowPK,className,titleName,srcRoot,webRoot){//获取grid中选了多少条记录
				var	TAB = document.getElementById(tableId);
				var TBD = TAB.getElementsByTagName("tbody")[0];
				var	ROW = TBD.getElementsByTagName("tr");
				rpt_taskidArr=[];
				var flg=0;//记录选中的记录数
				for(var i = 0;i<ROW.length;i++){
					//如果是选中行，则提取数据
					if(ROW[i].getElementsByTagName("input")[0].checked){
					rpt_taskidArr.push(ROW[i].getElementsByTagName("input")[0].id);
					flg++;
					}		
				}	
				if(flg<=0){
					alert("请选择要生成代码的表字段列！");
					return false;
				}else{
					//获取grid所选的值
					getGridValue();
					//提交参数值生成源代码
					var url = "<%=request.getContextPath()%>/webpage/manager/bogen/freemarker/createCode.action";
					$.ajax({
						url: url,
						cache: false,
						async: false,
						type : "POST", 
						data : {'rpt_taskidArr':rpt_taskidArr,'thisweek':thisweek,'tablename':tablename,
								'tablecomments':tablecomments,'isShowPK':isShowPK,'classFileName':className,
								'titleName':titleName,'srcRoot':srcRoot,'webRoot':webRoot},
						success: function(data){
							var dataJson = eval(data);
							var tablename = dataJson.cells[0].tablename;
							var previewpath = dataJson.cells[0].previewpath;
							alert("源代码生成成功！");
							
							//设置已生成表信息
							var temp = parent.document.getElementById("tablesTextarea").innerHTML;
							//判断该表是否已经包含在信息中
							var j = temp.indexOf(tablename);
							if(j>=0){//包含
								parent.document.getElementById("tablesTextarea").innerHTML = temp;
							}else{//不包含
								var contents = temp+'\n'+ tablename;
								parent.document.getElementById("tablesTextarea").innerHTML = contents;
							}		
							//设置预览路径到main页面
							parent.document.getElementById("previewPath").value = previewpath;
						},
						error: function(data) {
							alert("生成源代码失败，请与管理员联系！");
						}
					});
				}
			}
			//获取grid值 
			function getGridValue(){
			    //获取本周工作描述
			    thisweek = TE_thisweek.getSelectedRowValue();
			}
			
			//生成WORD模版文件
			function createWord(obj,tablenameE,tablenameC){
				var	TAB = document.getElementById(obj);
				var TBD = TAB.getElementsByTagName("tbody")[0];
				var	ROW = TBD.getElementsByTagName("tr");
				rpt_taskidArr=[];
				var flg=0;//记录选中的记录数
				for(var i = 0;i<ROW.length;i++){
					//如果是选中行，则提取数据
					if(ROW[i].getElementsByTagName("input")[0].checked){
					rpt_taskidArr.push(ROW[i].getElementsByTagName("input")[0].id);
					flg++;
					}
				}	
				if(flg<=0){
					alert("请选择要生成word的表字段列！");
					return false;
				}else{
					//获取grid所选的值
					getGridValue();
					//提交参数值生成word
					var url = "<%=request.getContextPath()%>/webpage/manager/bogen/toWord.action";
					$.ajax({
						url: url,
						cache: false,
						async: false,
						type : "POST", 
						data : {'tablenameE':tablenameE,'tablenameC':tablenameC,'rpt_taskidArr':rpt_taskidArr,'thisweek':thisweek},
						success: function(data){
							downWordFile();					
						},
						error: function(data) {
							alert("生成该表到WORD文件失败，请与管理员联系！");
						}
					});
				}
			}
			
			//生成所有表到WORD文件
			function createWordAll(){
				//提交参数值生成word
				var url = "<%=request.getContextPath()%>/webpage/manager/bogen/toWordAll.action";
				$.ajax({
					url: url,
					cache: false,
					async: false,
					type : "POST",
					success: function(data){
						downWordFile();					
					},
					error: function(data) {
						alert("生成所有表到WORD文件失败，请与管理员联系！");
					}
				});
			}
			
			//word生成完成后下载到本地保存
			function downWordFile(){
				window.document.location = "<%=request.getContextPath()%>/upfile/word/" +"<%=session.getId()%>"+ ".doc";
			}
		</script>
		
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
</html>