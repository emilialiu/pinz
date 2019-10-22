<%@ page import="com.dimine.sys.util.DateUtil"%>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../pub/biz/head.jsp"%>
<body>
<title>月验收汇总查看信息表</title>
<div class="row">
	<div class="col-xs-12">
		<form id="reportforms_totalAdetail" action="<%=request.getContextPath()%>/pub/report/doReport.action" target="reportframe" >
			<input type="hidden" name="reportclass" value="totalAdetail"/>
 			<input type="hidden" name="reportformat" value="html"/>
			<input type="hidden" name="reporttitle" value="月验收汇总查看信息表"/>
 			<input type="hidden" name="monthvalue" id="monthvalue"/> 			
 			
			<table>
				<tr>
					<td>
						<s:text name="dmmes.am.acceptance.ymvalue" />&nbsp;
					</td>
					<td style="display: table">
						<input type="text" name="datavalues" id="datavalues" class="col-xs-12" 
						onfocus="WdatePicker({dateFmt:'yyyy-MM'})" />
					</td>
					<td>
					<div class="toolbar">
						<button id="btn_search" type="button" class="btn btn-primary btn-sm" onclick="doSearch()">
							<span class="ace-icon fa fa-search icon-on-right bigger-110"></span>&nbsp;&nbsp;
							<s:text name="button.query" />
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
function doSearch(){
	var monthvalue=$.trim($("#datavalues").val());
	$("#reportforms_totalAdetail").find("#monthvalue").val(monthvalue);
	$("#reportforms_totalAdetail")[0].submit();
}	
</script>
</body>
</html>