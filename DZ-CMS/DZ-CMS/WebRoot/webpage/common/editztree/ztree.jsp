<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/taglibs.jsp"%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style/jquery/botree.css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery/botree.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/common/editztree/edittree.js"></script>

<script type="text/javascript">
	var scripts = [null, null];

	$('.page-content-area').ace_ajax('loadScripts', scripts, function(){});
</script>

<div class="row">
	<div class="col-xs-12 col-sm-6 widget-container-col">
		<!-- #section:custom/widget-box -->
		<div class="widget-box widget-color-blue">
			<div class="widget-header">
				<h5 class="widget-title">editztree使用方法</h5>
				<!-- #section:custom/widget-box.toolbar -->
				<div class="widget-toolbar">
					<a href="#" data-action="collapse">
						<i class="ace-icon fa fa-chevron-up"></i>
					</a>
				</div>
				<!-- /section:custom/widget-box.toolbar -->
			</div>

			<div class="widget-body">
				<div class="widget-main">
					<div class="alert alert-info">
						<dmtag:ztreemanager name="mine_tree" table="T_ztree" ztreevalue="id"
							parentid="parentid" ztreetext="name"></dmtag:ztreemanager>					
					</div>
					<p class="alert alert-success">
						1、界面引入
						<br/>
						"/style/jquery/botree.css"和"/js/jquery/botree.js"、"/common/editztree/edittree.js"
						<br/>
						2、代码部分:
						<br/>
						&lt;dmtag:ztreemanager name="mine_tree" table="T_ztree" ztreevalue="id"
							parentid="parentid" ztreetext="name"&gt;&lt;/dmtag:ztreemanager&gt;
					</p>
				</div>
			</div>
		</div>
	</div>
</div>
