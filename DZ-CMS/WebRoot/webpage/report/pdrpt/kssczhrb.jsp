<%@ page import="com.dimine.sys.util.DateUtil"%>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../pub/biz/head.jsp"%>
<body>
<title>全椒海螺矿山生产综合日报表</title>
<div class="row">
	<div class="col-xs-12">
		<form id="reportforms_sbscyx" action="<%=request.getContextPath()%>/pub/report/doReport.action" target="reportframe" >
			<input type="hidden" name="reportclass" value="sczhrbb"/>
 			<input type="hidden" name="reportformat" value="html"/>
			<input type="hidden" name="reporttitle" value="全椒海螺矿山生产综合日报表"/>
			<input type="hidden" name="SUBREPORT_DIR" value="<%=request.getRealPath("webpage/report/jasper/")%>\"/>	
 			
			<table>
				<tr>
					<td>
						日期&nbsp;
					</td>
					<td style="display: table">
						<input type="text" name="searchdate" id="searchdate" class="col-xs-12" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"
							value="<%=DateUtil.addDay(DateUtil.getDate(), -1) %>" readonly="readonly"/>
					</td>
					<td>
					<div class="toolbar">
						<button id="btn_search" type="button" class="btn btn-primary btn-sm" onclick="doSearch()">
							<span class="ace-icon fa fa-search icon-on-right bigger-110"></span>&nbsp;&nbsp;
							<s:text name="button.generatingReports" />
						</button>
					</div>
					</td>
				</tr>
			</table>		
		</form>
		<iframe name="reportframe" id="reportframe" src="" frameborder="0" style="border:0;width:100%;height:600px;"></iframe>
	</div>
</div>
<%@ include file="../../pub/biz/footer.jsp"%>
<script type="text/javascript">
$(function(){
	doSearch();
});
function doSearch(){
	$("#reportforms_sbscyx")[0].submit();
}	
</script>
</body>
</html>