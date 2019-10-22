<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/webpage/pub/biz/head.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/style/jquery/botree.css" type="text/css" />

	<body style="background-color: #fff">
		<div class="row" style="padding:0px 10px;">
			<div class="col-xs-12">
				<dmtag:sourcemanager sourcename="ZYDDLX" name="projecttype"
					cssStyle="width:100px;height:33px;" isnull="true" onchange="reoladTree_weifenpei();"></dmtag:sourcemanager>
				<input type="text" id="projectname" placeholder="作业地点" style="width:150px;height:33px;"/>
				<button id="pro_btn_search" type="button" class="btn btn-primary btn-sm" style="height:33px;">
					<span class="ace-icon fa fa-search icon-on-right bigger-110"></span>&nbsp;&nbsp;
					<s:text name="button.query" />
				</button>
			</div>
			<div class="col-xs-12">
				<div id="sys_role_func_grid_box" class="col-xs-12 col-sm-5 widget-box widget-color-blue">
					<div class="widget-header">
						<h5 class="widget-title"><i class="ace-icon fa fa-sitemap"></i><s:text name="dmmes.bi.projectinfo.joblocation" /></h5>
					</div>
					<div>
						<ul id="unproject_tree" class="ztree" style="height: 400px;overflow:auto;"></ul>
					</div>
				</div>
				<div class="col-xs-12 col-sm-2 center">
					<div style="margin-top: 150px;">
						<button id="project_btn_save" onclick="onGetNode()" class="btn btn-info btn-primary btn-sm">
							<span class="ace-icon fa fa-arrow-right smaller-30"></span>
							<s:text name="button.grant" />
						</button>
						<div class="space-2"></div>
						<button id="project_btn_remove" onclick="revokeNode()" class="btn btn-info btn-pink btn-sm">
							<span class="ace-icon fa fa-arrow-left smaller-30"></span>
							<s:text name="button.revoke" />
						</button>
					</div>
				</div>
				<div id="sys_role_func_grid_box" class="col-xs-12 col-sm-5 widget-box widget-color-blue">
					<div class="widget-header">
						<h5 class="widget-title"><i class="ace-icon fa fa-sitemap"></i><s:text name="dmmes.bi.hasgrantedarea" /></h5>
					</div>
					<div>
						<ul id="project_tree" class="ztree" style="height: 400px;overflow:auto;"></ul>
					</div>
				</div>
			</div>
		</div>
<%@include file="/webpage/pub/biz/footer.jsp" %>
<script type="text/javascript" src="<%=request.getContextPath()%>/plugins/jquery/botree.js"></script>
		<script type="text/javascript">
			var grantsuccess='<s:text name="button.grantsuccess" />';
			var grantfailure='<s:text name="button.grantfailure" />';
			var revokesuccess='<s:text name="revokesuccess" />';
			var revokefailure='<s:text name="revokefailure" />';
			var granting='<s:text name="granting" />';
			var revoking='<s:text name="revoking" />';
			var needtograntol='<s:text name="dmmes.bi.needtograntol" />';
			var needtorevokeol='<s:text name="dmmes.bi.needtorevokeol" />';
			var projectinfo='<s:text name="dmmes.bi.projectinfo" />';
			var techid = "<%=request.getParameter("techid")%>";
			$(function(){
				$("#projecttype option[value='ZYDDLX004']").remove();
			});
			//分配作业地点
			function onGetNode() {
				parent.fullShow(granting);
			 	var checkedNodes = BOTree_weifenpei.getCheckedNodes(true);
			 	var length = checkedNodes.length;
			 	var projectids = '';//作业地点id
			 	var pids = '';//没有选择子节点的中段或者分段id
			 	for(var i = 0; i < length; i++){
			 		if(checkedNodes[i].id != '0000'){
			 			if(checkedNodes[i].isleaf == '0' && checkedNodes[i].children==undefined){
			 				pids += checkedNodes[i].id + ',';
			 			}
			 			projectids += checkedNodes[i].id + ',';
			 		}
			 	}
			 	projectids = projectids.substring(0, projectids.length-1);
			 	pids = pids.substring(0, pids.length-1);
			 	if(projectids == ''){
			 		alertMsg(needtograntol);
			 		return;
			 	}
			 	$.ajax({
					url: rootpath+'/biz/bi/technologyProject/addTechnologyProject.action',
					data: { 'param' : projectids,'pids':pids, 'technologyProject.techid':techid },
					cache: false,
					success: function(data){
						parent.fullHide();
						var dataJson = eval(data);
						if(dataJson.success){
							alertMsg(grantsuccess);
							reloadTree();
						}else{
							alertErrorMsg(dataJson.errormessage);
						}
					},
					error: function(data) {
						parent.fullHide();
						alertErrorMsg(grantfailure); 
					}
				});
			}
			//撤销作业地点
			function revokeNode() {
				parent.fullShow(revoking);
			  	var checkedNodes = BOTree_yifenpei.getCheckedNodes(true);
			 	var length = checkedNodes.length;
			 	var projectids = '';
			 	for(var i = 0; i < length; i++){
			 		if(!checkedNodes[i].isParent)
			 			projectids += checkedNodes[i].id + ',';
			 	}
			 	projectids = projectids.substring(0, projectids.length-1);
			 	if(projectids == ''){
			 		alertMsg(needtorevokeol);
			 		return;
			 	}
			 	$.ajax({
					url: rootpath+'/biz/bi/technologyProject/delTechnologyProject.action',
					data: { 'param' : projectids, 'technologyProject.techid':techid },
					cache: false,
					success: function(data){
						parent.fullHide();
						var dataJson = eval(data);
						if(dataJson.success){
							alertMsg(revokesuccess);
							reloadTree();
						}else{
							alertErrorMsg(dataJson.errormessage);
						}
					},
					error: function(data) {
						parent.fullHide();
						alertErrorMsg(revokefailure); 
					}
				});
			}
			
			function reloadTree(){
				//初始化未分配的作业地点的树
				reoladTree_weifenpei();
				//初始化已分配的作业地点的树
				reoladTree_yifenpei();
				var srcNode = parent.window.frames[parent.getCurIframeID()].BOTree.getSelectedNodes();
				parent.window.frames[parent.getCurIframeID()].getNodeInfo(srcNode[0].id);
			}
		</script>
	   	<script src="./tree1.js"></script>
	   	<script src="./tree2.js"></script>
	</body>
</html>