<%@page import="com.dimine.security.entity.LoginUserEntity"%>
<%@page import="com.dimine.base.util.WebUtil"%>
<%@ page import="com.dimine.sys.util.DateUtil"%>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../pub/biz/head.jsp"%>

<%
	LoginUserEntity loginuser = WebUtil.getLoginUser(session);
	String deptid = "";
	if (loginuser != null) {
		deptid = loginuser.getOrgid();
		if (deptid != null && "1001".equals(deptid)) {
			deptid = "";
		}
	}
%>
<body>
	<title>周报</title>
	<div class="row">
		<div class="col-xs-12">
			<form id="reportforms_week" action="<%=request.getContextPath()%>/pub/report/doReport.action" target="reportframe">
				<input type="hidden" name="reportclass" value="weekReport" /> 
				<input
					type="hidden" name="reportformat" value="html" /> 
					<input type="hidden" name="reporttitle" value="周报" /> 
					<input type="hidden" name="techid" id="techid" /> 
					<input type="hidden" name="yearmonth" id="yearmonth" /> 
					<input type="hidden" name="count" id="count" /> 
					<input type="hidden" name="deptid" value="<%=deptid%>" />
				<div class="col-sm-9">
				 <span style="">工艺名称</span> 
					<select style="width:200px;height:33px" name="techids" id="techids">
					</select> 
				 <span style="">年月</span> 
				    <input type="text" name="datavaluess" id="datavaluess" style="width:200px;height:33px" onfocus="WdatePicker({dateFmt:'yyyy-MM'})" /> 
				 <span style="">周次</span>
					<select style="width:200px;height:33px" name="weekcounts" id="weekcounts">
						<option value="0" selected="selected">--请选择--</option>
						<option value="1">第一周</option>
						<option value="2">第二周</option>
						<option value="3">第三周</option>
						<option value="4">第四周</option>
						<option value="5">第五周</option>
						<option value="6">第六周</option>
					</select>
					<button id="btn_search" type="button"
						class="btn btn-primary btn-sm" onclick="doSearch()">
						<span class="ace-icon fa fa-search icon-on-right bigger-110"></span>&nbsp;&nbsp;
						查询
					</button>
				</div>
			</form>
			<iframe name="reportframe" id="reportframe" src="" frameborder="0"
				style="border:0;width:100%;height:650px;"></iframe>
		</div>
	</div>
	<%@ include file="../../pub/biz/footer.jsp"%>
	<script type="text/javascript">
		var dates = {};
		$(function() {
			$.ajax({
				url : rootpath
						+ '/webpage/biz/sc/technology/selectByReport.action',
				data : {},
				cache : false,
				async : false,
				error : function(data) {
					alertMsg("系统ajax交互错误");
				},
				success : function(data) {
					dates = eval('({' + data + '})');
					for ( var i in dates) {
						var objOption = new Option(dates[i], i);
						$("#techids")[0].add(objOption);
					}
				}
			});
		});

		function doSearch() {
			var counts = $.trim($("#weekcounts").val());
			var yearmonth = $.trim($("#datavaluess").val());
			var techids = $.trim($("#techids").val());
			$("#reportforms_week").find("#count").val(counts);
			$("#reportforms_week").find("#yearmonth").val(yearmonth);
			$("#reportforms_week").find("#techid").val(techids);
			$("#reportforms_week")[0].submit();
		}
	</script>
</body>
</html>