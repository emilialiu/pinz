<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/webpage/pub/biz/head.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/style/jquery/botree.css" type="text/css" />
	<body>
		<title>工艺对应工序</title>
		<div class="row">
			<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS -->				
				<div class="input-group col-sm-8">
					<span class="input-group-addon">
						<i class="ace-icon fa fa-search"></i>
					</span>					
					<input type="text" id="t_sc_techprocess_text_search" class="form-control search-query" placeholder="<s:text name="dmmes.sc.techprocess.processname" />" />
					<span class="input-group-btn">
						<button id="t_sc_techprocess_btn_search" type="button" class="btn btn-purple btn-sm">
							<span class="ace-icon fa fa-search icon-on-right bigger-110"></span>&nbsp;&nbsp;
							<s:text name="button.query" />
						</button>
						<button id="t_sc_techprocess_btn_advsearch" style="margin-left:10px;" class="btn btn-pink btn-sm">
							<span class="ace-icon fa fa-filter icon-on-right bigger-110"></span>&nbsp;&nbsp;
							<s:text name="button.Advancedquery" />
						</button>
					</span>
				</div>
			</div><!-- /.col -->
			<div class="col-sm-2">
				<div class="widget-box widget-color-blue">
					<div class="widget-header">
						<h4 class="widget-title lighter smaller"><i class="ace-icon fa fa-sitemap"></i><s:text name="dmmes.sc.techprocess.techtree" /></h4>
					</div>
					<div class="widget-body">
						<div class="widget-main padding-8" id="leftid">
							<ul id="technology_tree" class="ztree" style="height: 583px;overflow:auto;"></ul>
						</div>
					</div>
				</div>
			</div>
			<div class="col-xs-10">				
				<div id="t_sc_techprocess_grid_box" class="widget-box widget-color-blue">
					<div class="widget-header">
						<h5 class="widget-title"><i class="ace-icon fa fa-list"></i><s:text name="dmmes.sc.techprocess.title" /></h5>
						<div class="widget-toolbar">
							<a href="#" data-action="fullscreen" class="white">
								<i class="ace-icon fa fa-expand"></i>
							</a>
							<a href="#" id="t_sc_techprocess_grid_box_filter" class="white" data-action="filter">
								<i class="ace-icon fa fa-filter bigger"></i>
							</a>
							<a href="#" data-action="collapse" class="white">
								<i class="ace-icon fa fa-chevron-up"></i>
							</a>
						</div>
						<div class="widget-toolbar no-border">
							<button class="btn btn-xs btn-primary bigger no-border" onclick="selectGx()">
								<i class="ace-icon fa fa-plus"></i>
								<s:text name="button.add" />
							</button>
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
							<table id="t_sc_techprocess_grid"></table>
					
							<div id="t_sc_techprocess_grid_pager"></div>
						</div>
					</div>
				</div>
			</div><!-- /.col -->
				
			<!-- PAGE CONTENT ENDS -->
		</div><!-- /.row -->
	</body>
<%@include file="/webpage/pub/biz/footer.jsp" %>
<script type="text/javascript">
	 var sc_techprocess_processname = '<s:text name="dmmes.sc.techprocess.processname" />';
	 var sc_techprocess_parentprocess = '<s:text name="dmmes.sc.techprocess.parentprocess" />';
	 var sc_techprocess_isplan = '<s:text name="dmmes.sc.techprocess.isplan" />';
	 var sc_techprocess_isaccept = '<s:text name="dmmes.sc.techprocess.isaccept" />';
	 var sc_techprocess_serialno = '<s:text name="dmmes.sc.techprocess.serialno" />';
	 var pub_operation='<s:text name="pub.operation" />';
	 var sc_techprocess_processselection='<s:text name="dmmes.sc.process.processselection" />';
</script>
<script type="text/javascript" src="<%=request.getContextPath()%>/webpage/biz/sc/techprocess/bizgrid.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/plugins/jquery/botree.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/webpage/biz/sc/technology/tree.js"></script>

