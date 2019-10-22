<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/webpage/pub/biz/headinfo.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/style/activity/style.css" type="text/css" />
<!--[if IE]>
<link rel="stylesheet" href="<%=request.getContextPath()%>/style/activity/style-ie.css" type="text/css" media="screen"/>
<![endif]-->
<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/activity/qtip/jquery.qtip.css" type="text/css" />
<div id="act_processinstance_info">
	<div class="form-group">
	</div>
</div>
<%@include file="/webpage/pub/biz/footerinfo.jsp"%>
<script>
	var pid='<%=request.getParameter("pid")%>';
	var pdid='<%=request.getParameter("pdid")%>';
</script>
<script type="text/javascript" src="<%=request.getContextPath()%>/plugins/activity/jui/jquery-ui-1.9.2.min.js" type="text/javascript"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/plugins/activity/qtip/jquery.qtip.pack.js" type="text/javascript"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/plugins/activity/jquery.outerhtml.js" type="text/javascript"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/webpage/manager/activity/processinstance/workflow.js"></script>
