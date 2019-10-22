<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/webpage/pub/biz/head.jsp" %>
<%@page import="com.dimine.sys.util.PublicUtil"%>
<title>组织机构信息管理</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/style/jquery/botree.css" type="text/css" />	
	<div class="row">
		<div class="col-xs-12">
			<div class="col-sm-3">
				<div class="widget-box widget-color-blue">
					<div class="widget-header">
						<h4 class="widget-title lighter smaller"><i class="ace-icon fa fa-sitemap"></i>组织机构树</h4>
					</div>
					<div class="widget-body">
						<div class="widget-main padding-8" id="leftid">
							<ul id="dept_tree" class="ztree" style="height: 528px;overflow:auto;"></ul>
						</div>
					</div>
				</div>
			</div>
			<div class="col-sm-9">
				<div class="widget-box widget-color-blue">
					<div class="widget-header">
						<h4 class="widget-title lighter smaller"><i class="ace-icon fa fa-list"></i>组织机构信息</h4>
					</div>
					<div class="widget-body" style="height: 543px;overflow:auto;">
						<div class="widget-main padding-8" id="rightid">
							<%@include file="info.jsp"%>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
<%@include file="/webpage/pub/biz/footer.jsp" %>
<script type="text/javascript" src="<%=request.getContextPath()%>/plugins/jquery/botree.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/webpage/manager/sys/dept/tree.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/webpage/manager/sys/dept/submit.js"></script>
<script src="<%=request.getContextPath()%>/plugins/components/jquery-validation/dist/jquery.validate.min.js"></script>
<script src="<%=request.getContextPath()%>/plugins/components/jquery.maskedinput/dist/jquery.maskedinput.min.js"></script>
<script type="text/javascript">
	function replaceSpace(str){
        return str.replace(/\s/g,'');
	}
	$.mask.definitions['~']='[+-]';
	$('#fax').mask('9999-99999999');
	function submitme(){
	  	var parentid = document.getElementById("parentdeptid").value;
		var deptid = document.getElementById("deptid").value;
		if (parentid == "" && deptid!='<%=PublicUtil.DEPT_ROOT_NUMBER%>'){
			alertMsg('请选择上级机构');
		}else{
			//if (validator(document.forms[0])){
			submit();
		}
	}
</script>