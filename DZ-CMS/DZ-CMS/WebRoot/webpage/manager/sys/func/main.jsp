<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/webpage/pub/biz/head.jsp" %>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/style/jquery/botree.css"
	type="text/css" />
<title>功能管理</title>	
	<div class="row">
		<div class="col-xs-12">
			<div class="col-sm-3">
				<div class="widget-box widget-color-blue">
					<div class="widget-header">
						<h4 class="widget-title lighter smaller"><i class="ace-icon fa fa-sitemap"></i>功能信息树</h4>
					</div>
					<div class="widget-body">
						<div class="widget-main padding-8" id="leftid">
							<ul id="func_tree" class="ztree" style="height: 528px;overflow:auto;"></ul>
						</div>
					</div>
				</div>
			</div>

			<div class="col-sm-9">
				<div class="widget-box widget-color-blue">
					<div class="widget-header">
						<h4 class="widget-title lighter smaller"><i class="ace-icon fa fa-list"></i>功能信息</h4>
					</div>
					<div class="widget-body" style="height: 543px;overflow:auto;">
					 <form id="Form1" name="Form1"  method="post">
						<div class="widget-main padding-8" id="rightid">
						<%@include file="/webpage/manager/sys/func/info.jsp"%>
						</div>
					</form>	
					</div>
				</div>
			</div>
		</div>
	</div>
<%@include file="/webpage/pub/biz/footer.jsp" %>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/plugins/jquery/botree.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/webpage/manager/sys/func/tree.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/webpage/manager/sys/func/submit.js"></script> 
<script type="text/javascript" src="<%=request.getContextPath()%>/plugins/jquery/jQuery.Hz2Py-min.js"></script>