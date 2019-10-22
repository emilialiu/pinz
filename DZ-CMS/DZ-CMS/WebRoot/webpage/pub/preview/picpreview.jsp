<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/webpage/pub/biz/headinfo.jsp"%>
<body style="background-color: #fff;overflow:hidden;">
	<div class="row" style="padding:0px;padding-left:10px;">

		<div class="col-xs-12" style="padding-bottom: 10px; ">
			<img
				src="<%=(request.getContextPath()) + "/upfile"
					+ (request.getAttribute("fileurl"))%>"
				border="0" border="0" height="100%" width="100%" align="center" />
		</div>
		<!-- /.col -->
		<!-- PAGE CONTENT ENDS -->
	</div>
	<!-- /.row -->
</body>
<%@include file="/webpage/pub/biz/footerinfo.jsp"%>