<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/pub/biz/headlist.jsp" %>
<title>数据库监控</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/jquery-ui.min.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/datepicker.min.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/ui.jqgrid.min.css" />
<script type="text/javascript">
	var scripts = [ null, null ];
	$('.page-content-area').ace_ajax('loadScripts', scripts, function() {
	});
</script>
<div class="row">

	<div class="col-xs-12">
		<div id="biz_grid_box" class="widget-box widget-color-blue">
			<div class="widget-header">
				<h5 class="widget-title">
					<i class="ace-icon fa fa-list"></i>数据库监控信息
				</h5>
				<div class="widget-toolbar">
					<a href="#" data-action="fullscreen" class="white"> <i
						class="ace-icon fa fa-expand"></i> </a>
					<a href="#" id="biz_grid_box_filter" data-action="filter"> <i
						class="ace-icon fa fa-filter bigger white"></i> </a>
					<a href="#" data-action="collapse" class="white"> <i
						class="ace-icon fa fa-chevron-up"></i> </a>
				</div>

			</div>

			<div class="widget-body">
				<div class="widget-main no-padding">
					<iframe src="<%=request.getContextPath()%>/druid/sql.html"
						frameborder="0"
						style="overflow: no; padding: 0px; margin: 0px; valign: top; height: 500px; width: 100%"
						id="iframe_000000" name="rightframe" scolling="no"
						noresize="noresize"></iframe>

				</div>
			</div>
		</div>
	</div>
	<!-- /.col -->



	<!-- PAGE CONTENT ENDS -->
</div>
<!-- /.row -->
<%@include file="/pub/biz/footerlist.jsp" %>