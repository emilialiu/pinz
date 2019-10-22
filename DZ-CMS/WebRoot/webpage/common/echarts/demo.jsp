<%@ include file="../../pub/biz/headlist.jsp"%>
<script>
var scripts = [null, null];
$('.page-content-area').ace_ajax('loadScripts', scripts, function() {});
</script>
<title>Echarts</title>
<div class="row">
	<div class="col-md-12">
		<div class="widget box">
			<!--  <div class="widget-header">
				<h4>
					<i class="icon-reorder"> </i> Echarts
				</h4>
			</div>-->
			<div class="widget-content">
				<div class="tabbable box-tabs">
					<ul class="nav nav-tabs">
						<li class="active">
							<a href="#box_tab1" data-toggle="tab"> 标准折线图 </a>
						</li>
						<li>
							<a href="#box_tab2" data-toggle="tab"> 标准面积图 </a>
						</li>
						<li>
							<a href="#box_tab3" data-toggle="tab"> 标准饼图 </a>
						</li>
						<li>
							<a href="#box_tab4" data-toggle="tab"> 标准环形图 </a>
						</li>
						<li>
							<a href="#box_tab5" data-toggle="tab"> 标准柱状图 </a>
						</li>
						<!-- <li>
							<a href="#box_tab6" data-toggle="tab"> 堆积柱状图 </a>
						</li> -->
						<li>
							<a href="#box_tab6" data-toggle="tab"> K线图 </a>
						</li>
						<li>
							<a href="#box_tab7" data-toggle="tab"> 折柱混搭--双轴 </a>
						</li>
					</ul>
					<div class="tab-content">
						<div class="tab-pane active" id="box_tab1">
							<iframe id="iframechart" src="<%=request.getContextPath()%>/common/echarts/line1.html" frameborder="0" style="border:0;width:100%;height:500px;"></iframe>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script>
$(function() {
	$(".nav-tabs li a").click(function() {
		var index = $('.nav-tabs li a').index(this);
		if(index==0){
			$("#iframechart")[0].src = "<%=request.getContextPath()%>/common/echarts/line1.html";
		} else if(index==1){
			$("#iframechart")[0].src = "<%=request.getContextPath()%>/common/echarts/line2.html";
		} else if(index==2){
			$("#iframechart")[0].src = "<%=request.getContextPath()%>/common/echarts/pie1.html";
		} else if(index==3){
			$("#iframechart")[0].src = "<%=request.getContextPath()%>/common/echarts/pie2.html";
		} else if(index==4){
			$("#iframechart")[0].src = "<%=request.getContextPath()%>/common/echarts/bar1.html";
		//} else if(index==5){
		//	$("#iframechart")[0].src = "<%=request.getContextPath()%>/common/echarts/bar2.html";
		} else if(index==5){
			$("#iframechart")[0].src = "<%=request.getContextPath()%>/common/echarts/k1.html";
		} else if(index==6){
			$("#iframechart")[0].src = "<%=request.getContextPath()%>/common/echarts/mix1.html";
		}
	});
});	
</script>
