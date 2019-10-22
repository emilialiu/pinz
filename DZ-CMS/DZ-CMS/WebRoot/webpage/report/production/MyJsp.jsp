<%@ page import="com.dimine.sys.util.DateUtil"%>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../pub/biz/head.jsp"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/style/jquery/botree.css" type="text/css" />
<body>
<title>物料成本分析</title>
<div class="main-content" >
	<div class="page-content">
		<div class="row">
			<div class="col-xs-12" style="width: 99%;height:100%;padding-left:2px;padding-right: 2px;" id="tabs">
                  <ul class="nav nav-tabs" role="tablist">
                      <li class="active" id="tab_tab_0"><a href="#Index" role="tab" data-toggle="tab">物料总成本分析</a></li>
                      <li  id="tab_tab_0"><a href="#Index1" role="tab" data-toggle="tab">工矿物资成本分析</a></li>
                      <li  id="tab_tab_0"><a href="#Index2" role="tab" data-toggle="tab">机械物资成本分析</a></li>
                  </ul>
                  <div class="tab-content">
                      <div role="tabpanel" class="tab-pane active" id="Index" style="height:680px">
                      	<iframe id="content_tab_0" name="content_tab_0" src="<%=request.getContextPath()%>/webpage/report/production/materialCost.jsp" width="100%" height="100%" frameborder="no" 
                      		border="0" marginwidth="0" marginheight="0" scrolling="yes" allowtransparency="yes"></iframe>
                      </div>
                      <div role="tabpanel" class="tab-pane" id="Index1" style="height:680px">
                      	<iframe id="content_tab_0" name="content_tab_0" src="<%=request.getContextPath()%>/webpage/report/production/materialCost1.jsp" width="100%" height="100%" frameborder="no" 
                      		border="0" marginwidth="0" marginheight="0" scrolling="yes" allowtransparency="yes"></iframe>
                      </div>
                      <div role="tabpanel" class="tab-pane" id="Index2" style="height:680px">
                      	<iframe id="content_tab_0" name="content_tab_0" src="<%=request.getContextPath()%>/webpage/report/production/materialCost2.jsp" width="100%" height="100%" frameborder="no" 
                      		border="0" marginwidth="0" marginheight="0" scrolling="yes" allowtransparency="yes"></iframe>
                      </div>
                  </div>
              </div>
		</div>
	</div>
</div>
<%@ include file="../../pub/biz/footer.jsp"%>
<script type="text/javascript">
function doSearch(){
	var startDate=$.trim($("#startDateValue").val());
	var endDate=$.trim($("#endDateValue").val());
	$("#reportforms_materialCost").find("#startDate").val(startDate);
	$("#reportforms_materialCost").find("#endDate").val(endDate);
	$("#reportforms_materialCost")[0].submit();
}

function test(){
	
}	
</script>
</body>
</html>
