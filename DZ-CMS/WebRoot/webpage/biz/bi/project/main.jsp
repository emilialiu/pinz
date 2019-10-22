<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/webpage/pub/biz/head.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/style/jquery/botree.css" type="text/css" />
<title>工程信息维护</title>

<%@include file="/webpage/pub/biz/footer.jsp" %>
<div class="row">
	<div class="col-xs-12">
		<div class="col-xs-12 col-sm-2">
			<div class="widget-box widget-color-blue">
				<div class="widget-header">
					<h5 class="widget-title"><i class="ace-icon fa fa-sitemap"></i><s:text name="dmmes.bi.projectinfo.projectunittree" /></h5>
				</div>
				<div>
					<ul id="project_tree" class="ztree" style="height: 860px;overflow:auto;"></ul>
				</div>
			</div>
		</div>
		<div class="col-xs-12 col-sm-10">
			<div class="widget box">
				<div class="widget-content">
					<div class="tabbable box-tabs">
						<ul class="nav nav-tabs">
							<li class="active">
								<a href="#box_tab1" data-toggle="tab"> <s:text name="dmmes.bi.projectinfo.roadwayinfo" /> </a>
							</li>
							<li>
								<a href="#box_tab2" data-toggle="tab"> <s:text name="dmmes.bi.projectinfo.stopeinfo" /> </a>
							</li>
							<li>
								<a href="#box_tab3" data-toggle="tab"> <s:text name="dmmes.bi.projectinfo.otherprojectinfo" /> </a>
							</li>
						</ul>
						<div class="tab-content">
							<div class="tab-pane active" id="box_tab1">
							<%@ include file="/webpage/biz/bi/roadway/main.jsp"%>
							</div>
							<div class="tab-pane" id="box_tab2">
							<%@ include file="/webpage/biz/bi/stope/main.jsp"%>
							</div>
							<div class="tab-pane" id="box_tab3">
							<script type="text/javascript">
								var hideItemName = true;
							</script>
							<%@ include file="/webpage/biz/bi/other/main.jsp"%>
							</div>				
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript" src="<%=request.getContextPath()%>/plugins/jquery/botree.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/webpage/biz/bi/projectinfo/tree.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/plugins/common/calculate.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/webpage/biz/bi/project/pubbiz.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/plugins/jquery/jquery-json.js"></script>
<script>
var tabindex = 0;
//tab点击事件
$(".nav-tabs li a").click(function() {
	tabindex = $(".nav-tabs li a").index(this);
	if(tabindex==0){
		$("#project_tree").height(850);
		setTimeout(function() {
			$("#roadway_grid").jqGrid('setGridWidth', $("#roadway_grid_box").width());
			$("#projectsupport_grid").jqGrid('setGridWidth', $("#projectsupport_grid_box").width());
			$("#roadway_installinfo_grid").jqGrid('setGridWidth', $("#roadway_installinfo_grid_box").width());
		}, 0);
	}else if(tabindex==1){
		$("#project_tree").height(850);
		setTimeout(function() {
			$("#stope_grid").jqGrid('setGridWidth', $("#stope_grid_box").width());
			$("#stope_projectsupport_grid").jqGrid('setGridWidth', $("#stope_projectsupport_grid_box").width());
			$("#projectfill_grid").jqGrid('setGridWidth', $("#projectfill_grid_box").width());
			$("#stope_installinfo_grid").jqGrid('setGridWidth', $("#stope_installinfo_grid_box").width());
		}, 0);
	}else if(tabindex==2){
		$("#project_tree").height(431);
		setTimeout(function() {
			$("#other_grid").jqGrid('setGridWidth', $("#other_grid_box").width());
		}, 0);
	}
	var srcNode = BOTree1.getSelectedNodes();
	getNodeInfo(srcNode[0].id);
});

//
function getNodeInfo(projectid){
	if(tabindex == 0){
		$("#roadway_grid").setGridParam({page:1,postData:{"bean.projectid": projectid}}).trigger("reloadGrid");
		$("#projectsupport_grid").jqGrid('setGridParam',{postData:{'bean.projectid':''}}).trigger("reloadGrid");
		$("#roadway_installinfo_grid").jqGrid('setGridParam',{postData:{'bean.projectid':''}}).trigger("reloadGrid");
	}else if(tabindex == 1){
		$("#stope_grid").setGridParam({page:1,postData:{"bean.projectid": projectid}}).trigger("reloadGrid");
		$("#stope_projectsupport_grid").jqGrid('setGridParam',{postData:{'bean.projectid':''}}).trigger("reloadGrid");
		$("#projectfill_grid").jqGrid('setGridParam',{postData:{'bean.projectid':''}}).trigger("reloadGrid");
		$("#stope_installinfo_grid").jqGrid('setGridParam',{postData:{'bean.projectid':''}}).trigger("reloadGrid");
	}else if(tabindex == 2){
		$("#other_grid").setGridParam({page:1,postData:{"bean.projectid": projectid}}).trigger("reloadGrid");
	}
}
function roadwayReloadGrid(){
	$("#roadway_grid").trigger("reloadGrid");
}
function stopeReloadGrid(){
	$("#stope_grid").trigger("reloadGrid");
}
</script>
