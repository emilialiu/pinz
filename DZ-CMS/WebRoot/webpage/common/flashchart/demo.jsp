<%@ include file="../../pub/biz/headlist.jsp"%>
<script>
var scripts = [null, null];
$('.page-content-area').ace_ajax('loadScripts', scripts, function() {});
</script>
<title>3D图表</title>
<div class="row">
	<div class="col-md-12">
		<div class="widget box">
			<!-- <div class="widget-header">
				<h4>
					<i class="icon-reorder"> </i> 3D图表
				</h4>
			</div> -->
			<div class="widget-content">
				<div class="tabbable box-tabs">
					<ul class="nav nav-tabs">
						<li class="active">
							<a href="#box_tab1" data-toggle="tab"> 投票统计 </a>
						</li>
						<li>
							<a href="#box_tab2" data-toggle="tab"> 生产统计 </a>
						</li>
						<li>
							<a href="#box_tab3" data-toggle="tab"> 销售统计 </a>
						</li>
					</ul>
					<div class="tab-content">
						<div class="tab-pane active" id="box_tab1">
							<iframe src="<%=request.getContextPath()%>/common/flashchart/demo.html" frameborder="0" style="border:0;width:100%;height:500px;"></iframe>
						</div>
						<div class="tab-pane" id="box_tab2">
							<iframe src="<%=request.getContextPath()%>/common/fusioncharts/demo1.html" frameborder="0" style="border:0;width:100%;height:500px;"></iframe>
						</div>
						<div class="tab-pane" id="box_tab3">
							<iframe src="<%=request.getContextPath()%>/common/fusioncharts/demo2.jsp" frameborder="0" style="border:0;width:100%;height:500px;"></iframe>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
