<%@ page import="com.dimine.sys.util.DateUtil"%>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../pub/biz/head.jsp"%>
<body>
<title>设备台账信息</title>
<div class="row">
	<div class="col-xs-12">
		<form id="reportforms_equipmentLedger" action="<%=request.getContextPath()%>/pub/report/doReport.action" target="reportframe" >
			<input type="hidden" name="reportclass" value="equipment_ledger"/>
 			<input type="hidden" name="reportformat" value="html"/>
			<input type="hidden" name="reporttitle" value="设备台账信息"/>
 			<input type="hidden" name="startDate" id="startDate"/> 		
 			<input type="hidden" name="endDate" id="endDate"/> 		
 			
			<table>
				<tr>
					<td>
						<s:text name="dmmes.am.acceptance.starttime" />&nbsp;
					</td>
					<td style="display: table">
						<input type="text" name="startDateValue" id="startDateValue" class="col-xs-12" 
						onfocus="WdatePicker({dateFmt:'yyyy-MM'})" />
					</td>
					<td>
						<s:text name="dmmes.am.acceptance.endtime" />&nbsp;
					</td>
					<td style="display: table">
						<input type="text" name="endDateValue" id="endDateValue" class="col-xs-12" 
						onfocus="WdatePicker({dateFmt:'yyyy-MM'})" />
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
function doSearch(){
	var startDate=$.trim($("#startDateValue").val());
	var endDate=$.trim($("#endDateValue").val());
	$("#reportforms_equipmentLedger").find("#startDate").val(startDate);
	$("#reportforms_equipmentLedger").find("#endDate").val(endDate);
	$("#reportforms_equipmentLedger")[0].submit();
}	
</script>
</body>
</html>