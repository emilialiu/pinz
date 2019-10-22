<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/webpage/pub/biz/head.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/style/jquery/botree.css" type="text/css" />
	<body>
	<div class="row">
		<div class="col-xs-12">
			<div class="col-sm-3">
				<div class="widget-box widget-color-blue">
					<div class="widget-header">
						<h4 class="widget-title lighter smaller"><i class="ace-icon fa fa-sitemap"></i>工程单元树</h4>
					</div>
					<div class="widget-body">
						<div class="widget-main padding-8" id="leftid">
							<ul id="project_tree" class="ztree" style="height: 527px;overflow:auto;"></ul>
						</div>
					</div>
				</div>
			</div>
			<div class="col-sm-9">
				<div class="widget-box widget-color-blue">
					<div class="widget-header">
						<h4 class="widget-title lighter smaller"><i class="ace-icon fa fa-list"></i>工程单元信息</h4>
					</div>
					<div class="widget-body" style="height: 543px;overflow:auto;">
						<div class="widget-main padding-8" id="rightid">
							<%@include file="/webpage/biz/bi/projectinfo/info.jsp"%>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<%@include file="/webpage/pub/biz/footer.jsp" %>
<script type="text/javascript" src="<%=request.getContextPath()%>/plugins/jquery/botree.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/webpage/biz/bi/projectinfo/tree.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/webpage/biz/bi/projectinfo/submit.js"></script>
<script src="<%=request.getContextPath()%>/plugins/components/jquery-validation/dist/jquery.validate.min.js"></script>
<script type="text/javascript">
	function replaceSpace(str){
        return str.replace(/\s/g,'');
	}

	function submitme(){
	  	var parentid = document.getElementById("parentitemid").value;
		var projectid = document.getElementById("projectid").value;
		if (parentid == "" && projectid!='1000'){
			alertMsg('请选择上级工程');
		}else{
			submit();
		}
	}
</script>
</html>