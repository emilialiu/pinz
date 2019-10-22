<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/webpage/pub/biz/head.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/style/jquery/botree.css" type="text/css" />
<title>工程对应到工区</title>
<%@include file="/webpage/pub/biz/footer.jsp" %>
<div class="row">
	<div class="col-md-12">
		<div class="col-xs-12 col-sm-2" style="padding-right:4px;">
			<div class="widget-box widget-color-blue">
				<div class="widget-header">
					<h5 class="widget-title"><i class="ace-icon fa fa-sitemap"></i><s:text name="dmmes.bi.projectinfo.projectunittree" /></h5>
				</div>
				<div>
					<ul id="project_tree" class="ztree" style="height: 861px;overflow:auto;"></ul>
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
							<%@ include file="/webpage/biz/bi/roadway/mainsea.jsp"%>
							</div>
							<div class="tab-pane" id="box_tab2">
							<%@ include file="/webpage/biz/bi/stope/mainsea.jsp"%>
							</div>
							<div class="tab-pane" id="box_tab3">
							<script type="text/javascript">
								var hideItemName = true;
							</script>
							<%@ include file="/webpage/biz/bi/other/mainsea.jsp"%>
							</div>				
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript" src="<%=request.getContextPath()%>/plugins/jquery/botree.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/webpage/biz/bi/project/tree.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/webpage/biz/bi/project/pubbiz.js"></script>
<script>
var choseteam='<s:text name="dmmes.bi.choseteam" />';
var projecttoworkarea='<s:text name="dmmes.bi.projecttoworkarea" />';
var deleterecord='<s:text name="dmmes.bi.deleterecord" />';
var error_ajax='<s:text name="error.ajax" />';
var deletesuccess='<s:text name="deletesuccess" />';
var deletefailure='<s:text name="deletefailure" />';
//页面脚本
$(".addproject").click(function(){
	var srcNode = BOTree.getSelectedNodes();
	if(srcNode == null||srcNode==''||srcNode[0].orgtype != 'ZZJGLX003'){
		alertMsg(choseteam);
		return ;
	}
	parent.openwin_nobutton("project","/webpage/pub/select/project_team/project.jsp?qdid="+srcNode[0].id,projecttoworkarea,850,600);
})

var tabindex = 0;
//tab点击事件
$(".nav-tabs li a").click(function() {
	tabindex = $(".nav-tabs li a").index(this);
	if(tabindex == 0){
		$("#project_tree").height(851);
		setTimeout(function() {
			$("#roadway_grid").jqGrid('setGridWidth', $("#roadway_grid_box").width());
			$("#projectsupport_grid").jqGrid('setGridWidth', $("#projectsupport_grid_box").width());
			$("#roadway_installinfo_grid").jqGrid('setGridWidth', $("#roadway_installinfo_grid_box").width());
		}, 0);
	}else if(tabindex == 1){
		$("#project_tree").height(851);
		setTimeout(function() {
			$("#stope_grid").jqGrid('setGridWidth', $("#stope_grid_box").width());
			$("#stope_projectsupport_grid").jqGrid('setGridWidth', $("#stope_projectsupport_grid_box").width());
			$("#projectfill_grid").jqGrid('setGridWidth', $("#projectfill_grid_box").width());
			$("#stope_installinfo_grid").jqGrid('setGridWidth', $("#stope_installinfo_grid_box").width());
		}, 0);
	}else if(tabindex == 2){
		$("#project_tree").height(431);
		setTimeout(function() {
			$("#other_grid").jqGrid('setGridWidth', $("#other_grid_box").width());
		}, 0);
	}
	var srcNode = BOTree.getSelectedNodes();
	getNodeInfo(srcNode[0].id);
});

//
function getNodeInfo(qdid){
	if(tabindex == 0){
		$("#roadway_grid").setGridParam({page:1,postData:{"bean.qdid": qdid}}).trigger("reloadGrid");
		$("#projectsupport_grid").jqGrid('setGridParam',{postData:{'bean.projectid':''}}).trigger("reloadGrid");
		$("#roadway_installinfo_grid").jqGrid('setGridWidth', $("#roadway_installinfo_grid_box").width());
	}else if(tabindex == 1){
		$("#stope_grid").setGridParam({page:1,postData:{"bean.qdid": qdid}}).trigger("reloadGrid");
		$("#stope_projectsupport_grid").jqGrid('setGridParam',{postData:{'bean.projectid':''}}).trigger("reloadGrid");
		$("#projectfill_grid").jqGrid('setGridParam',{postData:{'bean.projectid':''}}).trigger("reloadGrid");
		$("#stope_installinfo_grid").jqGrid('setGridWidth', $("#roadway_installinfo_grid_box").width());
	}else if(tabindex == 2){
		$("#other_grid").setGridParam({page:1,postData:{"bean.qdid": qdid}}).trigger("reloadGrid");
	}
}
//打开删除窗口
function doopendelete_(_bizname,selectedRowId) {
	setTimeout(function(){
		bootbox.confirm(deleterecord, function(result) {
			if(result) {
				var str = $("#"+_bizname+"_grid").jqGrid("getRowData", selectedRowId);
				$.ajax({
			        url : rootpath+'/biz/bi/areaproject/delete.action',
			        type:'POST',
			        data : {'areaProject.projectid':str.projectid,'areaProject.deptid':BOTree.getSelectedNodes()[0].id},
			        cache : false,
			        error : function(data) {
			        	alertErrorMsg(error_ajax);
			        },
			        success : function(data) {
			        	var dataJson = eval(data);
			            if(dataJson.success) {
			                $("#"+_bizname+"_grid").jqGrid("delRowData", selectedRowId);
			                alertMsg(deletesuccess);
			            } else {
			            	alertErrorMsg(deletefailure+":"+dataJson.errorMessage);
			            }
			            if(_bizname=="roadway"){//巷道
			            	$("#projectsupport_grid").setGridParam({page:1,postData:{'bean.projectid':'-'}}).trigger("reloadGrid");
			            	$("#roadway_installinfo_grid").setGridParam({page:1,postData:{'bean.projectid':'-'}}).trigger("reloadGrid");
			            }
			            if(_bizname=="stope"){//采场
			            	$("#stope_projectsupport_grid").setGridParam({page:1,postData:{'bean.projectid':'-'}}).trigger("reloadGrid");
			            	$("#projectfill_grid").setGridParam({page:1,postData:{'bean.projectid':'-'}}).trigger("reloadGrid");
			            	$("#roadway_installinfo_grid").setGridParam({page:1,postData:{'bean.projectid':'-'}}).trigger("reloadGrid");
			            }
			        }  
			    });
			}
		});
	},0);
}
</script>
