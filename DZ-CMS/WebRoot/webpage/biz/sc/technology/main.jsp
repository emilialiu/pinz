<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/webpage/pub/biz/head.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/webpage/common/plugins/jui/themes/redmond/jquery-ui-1.9.2.custom.css" />
<style>
	.ui-jqgrid .tree-wrap-ltr{float:left !important;}
</style>
		<title>工艺信息</title>
		<div class="row">
			<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS -->				
				<!-- <div class="input-group col-sm-8">
					<span class="input-group-addon">
						<i class="ace-icon fa fa-search"></i>
					</span>					
					<input type="text" id="t_sc_technology_text_search" class="form-control search-query" placeholder="" />
					<span class="input-group-btn">
						<button id="t_sc_technology_btn_search" type="button" class="btn btn-purple btn-sm">
							<span class="ace-icon fa fa-search icon-on-right bigger-110"></span>&nbsp;&nbsp;
							查询
						</button>
						<button id="t_sc_technology_btn_advsearch" style="margin-left:10px;" class="btn btn-pink btn-sm">
							<span class="ace-icon fa fa-filter icon-on-right bigger-110"></span>&nbsp;&nbsp;
							高级查询
						</button>
					</span>
				</div> -->
			</div><!-- /.col -->
			<div class="col-xs-6">				
				<div id="t_sc_technology_grid_box" class="widget-box widget-color-blue">
					<div class="widget-header">
						<h5 class="widget-title"><i class="ace-icon fa fa-list"></i><s:text name="dmmes.sc.technology.title" /></h5>
						<div class="widget-toolbar">
							<a href="#" data-action="fullscreen" class="white">
								<i class="ace-icon fa fa-expand"></i>
							</a>
							<a href="#" id="t_sc_technology_grid_box_filter" class="white" data-action="filter">
								<i class="ace-icon fa fa-filter bigger"></i>
							</a>
							<a href="#" data-action="collapse" class="white">
								<i class="ace-icon fa fa-chevron-up"></i>
							</a>
						</div>
						<div class="widget-toolbar no-border">
							<button class="btn btn-xs btn-primary bigger no-border" onclick="saveAll()">
								<i class="ace-icon fa fa-plus"></i>
								<s:text name="dmmes.sc.technology.save" />
							</button>
						</div> 
					</div>

					<div class="widget-body">
						<div class="widget-main no-padding">
							<table id="t_sc_technology_grid"></table>
					
							<div id="t_sc_technology_grid_pager"></div>
						</div>
					</div>
				</div>
			</div><!-- /.col -->
			<div class="col-sm-6">
				<div class="widget-box widget-color-blue">
					<div class="widget-header">
						<h4 class="widget-title lighter smaller"><i class="ace-icon fa fa-list"></i><s:text name="dmmes.sc.technology.maintaintitle" /></h4>
					</div>
					<div class="widget-body" style="height: 600px;overflow:auto;">
					 <form id="Form1" name="Form1"  method="post">
						<div class="widget-main padding-8" id="rightid">
						<%@include file="/webpage/biz/sc/technology/infos.jsp"%>
						</div>
					</form>	
					</div>
				</div>
			</div>
			<!-- PAGE CONTENT ENDS -->
		</div><!-- /.row -->
<%@include file="/webpage/pub/biz/footer.jsp" %>
<script type="text/javascript">
 var sc_technology_techid = '<s:text name="dmmes.sc.technology.techid" />';
 var sc_technology_tdeptid = '<s:text name="dmmes.sc.technology.tdeptid" />';
 var sc_technology_techname = '<s:text name="dmmes.sc.technology.techname" />';
 var sc_technology_techcode = '<s:text name="dmmes.sc.technology.techcode" />';
 var sc_technology_technameen = '<s:text name="dmmes.sc.technology.technameen" />';
 var sc_technology_shortname = '<s:text name="dmmes.sc.technology.shortname" />';
 var sc_technology_parentname = '<s:text name="dmmes.sc.technology.parentname" />';
 var sc_technology_parentid = '<s:text name="dmmes.sc.technology.parentid" />';
 var sc_technology_isplan = '<s:text name="dmmes.sc.technology.isplan" />';
 var sc_technology_isaccept = '<s:text name="dmmes.sc.technology.isaccept" />';
 var sc_technology_isleaf = '<s:text name="dmmes.sc.technology.isleaf" />';
 var sc_technology_parentstr = '<s:text name="dmmes.sc.technology.parentstr" />';
 var sc_technology_serialno = '<s:text name="dmmes.sc.technology.serialno" />';
 var sc_technology_level = '<s:text name="dmmes.sc.technology.level" />';
 var sc_technology_memo = '<s:text name="dmmes.sc.technology.memo" />';
 
</script>
<script type="text/javascript" src="<%=request.getContextPath()%>/webpage/biz/sc/technology/bizgrid.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/webpage/biz/sc/technology/submit.js"></script> 
<script type="text/javascript" src="<%=request.getContextPath()%>/plugins/jquery/jQuery.Hz2Py-min.js"></script>
