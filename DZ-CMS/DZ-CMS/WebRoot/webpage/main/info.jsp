<%@ page language="java" pageEncoding="utf-8"%>
<%@page import="com.dimine.sys.util.DateUtil"%>
<%@include file="/webpage/pub/biz/headinfo.jsp" %>
<script type="text/javascript">
var techid = "<%=request.getParameter("techid")%>";
var procid = "<%=request.getParameter("procid")%>";
var targetid = "<%=request.getParameter("targetid")%>";
var pdate = "<%=request.getParameter("pdate")%>";
var bizname = "<%=request.getParameter("bizname")%>";
var proctname="<%=request.getParameter("proctname")%>";
var targetname="<%=request.getParameter("targetname")%>";
var unitname="<%=request.getParameter("unitname")%>";
var pd_drugbill_unit='<s:text name="dmmes.pd.drugbill.unit" />';
var home_planned='<s:text name="dmmes.home.planned" />';
var home_completion='<s:text name="dmmes.home.completion" />';
var home_completionRate='<s:text name="dmmes.home.completionRate" />';
</script>
		<div>
			<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
			<div id="productdayrpt_gcrb_picture" class="col-xs-12" style="width: 1150px;height: 420px"></div>
			<!-- ECharts单文件引入 -->
		</div>
<%@include file="/webpage/pub/biz/footerinfo.jsp" %>
<script type="text/javascript" src="<%=request.getContextPath()%>/plugins/jquery/owin.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/webpage/common/echarts/echarts-all.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/webpage/main/charts.js"></script>
<script type="text/javascript">
	 //页面加载时执行
	//$('.page-content-area').ace_ajax('loadScripts', scripts, function() {
		$(function() {
			$("button:contains('清空选择')", window.parent.document).hide();
			$("button:contains('Empty choice')", window.parent.document).hide();
			$("button:contains('Confirm')", window.parent.document).hide();
			$("button:contains('确定')", window.parent.document).hide();
	    	productScrbLoadCharts(techid,procid,targetid,pdate,bizname);
		});
	//}); 
</script>
