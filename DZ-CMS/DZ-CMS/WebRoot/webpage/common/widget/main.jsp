<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/pub/biz/headlist.jsp" %>
<%@ include file="/taglibs.jsp"%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets/css/chosen.min.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/chosen.jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/widget/chosen.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/widget/checktree.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/widget/radiotree.js"></script>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style/jquery/jquery.autocomplete.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style/jquery/botree.css" />

<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery.autocomplete.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/widget/auto.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/botree.js"></script>


<style type="text/css">
	ul.ztree {
		margin-top: 10px;
		border: 1px solid #617775;
		background: #f0f6e4;
		width:220px;
		height:360px;
		overflow-y:scroll;
		overflow-x:auto;
	}
</style>

<script type="text/javascript">
	var scripts = [null, null]
	$('.page-content-area').ace_ajax('loadScripts', scripts, function() {});
</script>
<div class="form-group">

	<div class="row">
		<div class="col-xs-12 col-sm-6 widget-container-col">
			<!-- #section:custom/widget-box -->
			<div class="widget-box widget-color-blue">
				<div class="widget-header">
					<h5 class="widget-title">组合框使用方法</h5>
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
						<p class="alert alert-info">
							<dmtag:combomanager table="T_combo" cssStyle="width:364px" cssClass="chosen-select form-control" name="chosenwidget" combovalue="id" combotext="text" parameter="text like '123%'"/>							
						</p>
						<p class="alert alert-success">
							1、界面引入
							<br/>
							"/assets/css/chosen.min.css"和"/assets/js/chosen.jquery.min.js"、
								"/js/widget/chosen.js"
							<br/>
							2、代码部分:
							<br/>
							&lt;dmtag:combomanager table="T_combo" cssStyle="width:364px" cssClass="chosen-select form-control" 
								name="chosenwidget" combovalue="id" combotext="text" parameter="text like '123%'"&gt;&lt;/dmtag:combomanager&gt;
						</p>
					</div>
				</div>
			</div>
		</div>

		<div class="col-xs-12 col-sm-6 widget-container-col">
			<!-- #section:custom/widget-box -->
			<div class="widget-box widget-color-blue">
				<div class="widget-header">
					<h5 class="widget-title">自动完成使用方法</h5>
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
						<p class="alert alert-info">
							<dmtag:automanager name="autowidget" table="T_auto" cssClass="text"
								cssStyle="width:350px" maxsize="10" cssWidth="350" parameter=""
								autotext="name"></dmtag:automanager>							
						</p>
						<p class="alert alert-success">
							1、界面引入
							<br/>
							"/style/jquery/jquery.autocomplete.css"和"/js/jquery/jquery.autocomplete.min.js"、
							<br/>
							"/js/widget/auto.js"
							<br/>
							2、代码部分:
							<br/>
							&lt;dmtag:automanager name="autowidget" table="T_auto" cssClass="text"
								cssStyle="width:350px" maxsize="10" cssWidth="350" parameter=""
								autotext="name"&gt;&lt;/dmtag:automanager&gt;
						</p>
					</div>
				</div>
			</div>
		</div>
	 
		<div class="col-xs-12 col-sm-6 widget-container-col">
			<!-- #section:custom/widget-box -->
			<div class="widget-box widget-color-blue">
				<div class="widget-header">
					<h5 class="widget-title">单选框使用方法</h5>
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
						<p class="alert alert-info">
							<dmtag:radiomanager table="T_combo" name="radiotest" radiovalue="id" radiotext="text" parameter="text like '123%'"/>							
						</p>
						<p class="alert alert-success">
							1、代码部分:
							<br/>
							&lt;dmtag:radiomanager table="T_combo" name="radiotest" radiovalue="id" 
								radiotext="text" parameter="text like '123%'"&gt;&lt;/dmtag:radiomanager&gt;
						</p>
					</div>
				</div>
			</div>
		</div>
		
		<div class="col-xs-12 col-sm-6 widget-container-col">
			<!-- #section:custom/widget-box -->
			<div class="widget-box widget-color-blue">
				<div class="widget-header">
					<h5 class="widget-title">多选框使用方法</h5>
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
						<p class="alert alert-info">
							<dmtag:checkboxmanager table="T_combo" name="checktest" checkboxvalue="id" checkboxtext="text" parameter="text like '123%'"/>							
						</p>
						<p class="alert alert-success">
							1、代码部分:
							<br/>
							&lt;dmtag:checkboxmanager table="T_combo" name="checktest" checkboxvalue="id" 
								checkboxtext="text" parameter="text like '123%'"&gt;&lt;/dmtag:checkboxmanager&gt;
						</p>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-xs-12 col-sm-6 widget-container-col">
			<!-- #section:custom/widget-box -->
			<div class="widget-box widget-color-blue">
				<div class="widget-header">
					<h5 class="widget-title">下拉单选树使用方法</h5>
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
							<dmtag:selectmemumanager table="T_checkbox" cssStyle="width:350px" checkdiv="radiotreediv" checktree="radiotreedemo"
								name="radiotree" checkboxvalue="id" checkboxtext="text" onclick="showRadioMenu();" treewidth="350px" treeheight="300px"
								parentid="parentid" parameter="text like '123%'"></dmtag:selectmemumanager>						
						</div>
						<p class="alert alert-success">
							1、界面引入
							<br/>
							"/js/jquery/botree.js"、"/js/widget/radiotree.js"
							<br/>
							2、代码部分:
							<br/>
							&lt;dmtag:selectmemumanager table="T_checkbox" cssStyle="width:350px" checkdiv="radiotreediv" checktree="radiotreedemo"
								name="radiotree" checkboxvalue="id" checkboxtext="text" onclick="showRadioMenu();" treewidth="350px" treeheight="300px"
								parentid="parentid" parameter="text like '123%'"&gt;&lt;/dmtag:selectmemumanager&gt;
						</p>
					</div>
				</div>
			</div>
		</div>

		<div class="col-xs-12 col-sm-6 widget-container-col">
			<!-- #section:custom/widget-box -->
			<div class="widget-box widget-color-blue">
				<div class="widget-header">
					<h5 class="widget-title">下拉多选树使用方法</h5>
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
							<dmtag:selectmemumanager table="T_checkbox" cssStyle="width:350px" checkdiv="checktreediv" checktree="checktreedemo"
								name="checktree" checkboxvalue="id" checkboxtext="text" onclick="showCheckMenu();" treewidth="350px" treeheight="300px"
								parentid="parentid"></dmtag:selectmemumanager>								
						</div>
						<p class="alert alert-success">
							1、界面引入
							<br/>
							"/js/jquery/botree.js"、"/js/widget/checktree.js"
							<br/>
							2、代码部分:
							<br/>
							&lt;dmtag:selectmemumanager table="T_checkbox" cssStyle="width:350px" checkdiv="checktreediv" checktree="checktreedemo"
								name="checktree" checkboxvalue="id" checkboxtext="text" onclick="showCheckMenu();" treewidth="350px" treeheight="300px"
								parentid="parentid"&gt;&lt;/dmtag:selectmemumanager&gt;
						</p>
					</div>
				</div>
			</div>
		</div>
	</div>
	
</div>
<script type="text/javascript">
	$(document).ready(function(){
		$.fn.zTree.init($("#checktreedemo"), checksetting, zNodes);
	});
	
	$(document).ready(function(){
		$.fn.zTree.init($("#radiotreedemo"), radiosetting, zNodes);
	});
	
	
</script>
<%@include file="/pub/biz/footerlist.jsp" %>
