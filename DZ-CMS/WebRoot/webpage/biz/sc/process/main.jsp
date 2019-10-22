<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/webpage/pub/biz/head.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/style/jquery/botree.css" type="text/css" />
		<title>工序信息</title>
		<div class="row">
			<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS -->				
				<div class="input-group col-sm-8">
					<span class="input-group-addon">
						<i class="ace-icon fa fa-search"></i>
					</span>					
					<input type="text" id="t_sc_process_text_search" class="form-control search-query" placeholder="<s:text name="dmmes.sc.process.proctname" />" />
					<span class="input-group-btn">
						<button id="t_sc_process_btn_search" type="button" class="btn btn-purple btn-sm">
							<span class="ace-icon fa fa-search icon-on-right bigger-110"></span>&nbsp;&nbsp;
							<s:text name="button.query" />
						</button>
						<button id="t_sc_process_btn_advsearch" style="margin-left:10px;" class="btn btn-pink btn-sm">
							<span class="ace-icon fa fa-filter icon-on-right bigger-110"></span>&nbsp;&nbsp;
							<s:text name="button.Advancedquery" />
						</button>
					</span>
				</div>
			</div><!-- /.col -->
			<div class="col-sm-2">
				<div class="widget-box widget-color-blue">
					<div class="widget-header">
						<h4 class="widget-title lighter smaller"><i class="ace-icon fa fa-sitemap"></i><s:text name="dmmes.sc.process.treename" /></h4>
					</div>
					<div class="widget-body">
						<div class="widget-main padding-8" id="leftid">
							<ul id="process_tree" class="ztree" style="height: 583px;overflow:auto;"></ul>
						</div>
					</div>
				</div>
			</div>
			<div class="col-xs-5">				
				<div id="t_sc_process_grid_box" class="widget-box widget-color-blue">
					<div class="widget-header">
						<h5 class="widget-title"><i class="ace-icon fa fa-list"></i><s:text name="dmmes.sc.process.title" /></h5>
						<div class="widget-toolbar">
							<a href="#" data-action="fullscreen" class="white">
								<i class="ace-icon fa fa-expand"></i>
							</a>
							<a href="#" id="t_sc_process_grid_box_filter" class="white" data-action="filter">
								<i class="ace-icon fa fa-filter bigger"></i>
							</a>
							<a href="#" data-action="collapse" class="white">
								<i class="ace-icon fa fa-chevron-up"></i>
							</a>
						</div>
						<div id="t_sc_process_grid_btn_add" class="widget-toolbar no-border">
							<button class="btn btn-xs btn-primary bigger no-border">
								<i class="ace-icon fa fa-plus"></i>
								<s:text name="button.add" />
							</button>
						</div>
					</div>

					<div class="widget-body">
						<div class="widget-main no-padding">
							<table id="t_sc_process_grid"></table>
					
							<div id="t_sc_process_grid_pager"></div>
						</div>
					</div>
				</div>
			</div><!-- /.col -->
			<div class="col-xs-5">				
				<div id="t_bi_target_grid_box" class="widget-box widget-color-blue">
					<div class="widget-header">
						<h5 class="widget-title"><i class="ace-icon fa fa-list"></i><s:text name="dmmes.bi.target.title" /></h5>
						<div class="widget-toolbar">
							<a href="#" data-action="fullscreen" class="white">
								<i class="ace-icon fa fa-expand"></i>
							</a>
							<a href="#" id="t_bi_target_grid_box_filter" class="white" data-action="filter">
								<i class="ace-icon fa fa-filter bigger"></i>
							</a>
							<a href="#" data-action="collapse" class="white">
								<i class="ace-icon fa fa-chevron-up"></i>
							</a>
						</div>
						<div id="t_bi_target_grid_btn_add" class="widget-toolbar no-border">
							<button class="btn btn-xs btn-primary bigger no-border">
								<i class="ace-icon fa fa-plus"></i>
								<s:text name="button.add" />
							</button>
						</div>
					</div>

					<div class="widget-body">
						<div class="widget-main no-padding">
							<table id="t_bi_target_grid"></table>
					
							<div id="t_bi_target_grid_pager"></div>
						</div>
					</div>
				</div>
			</div><!-- /.col -->
				
			<!-- PAGE CONTENT ENDS -->
		</div><!-- /.row -->
<script type="text/javascript">
	//获取树节点的信息
	//var srcNode = BOTree1.getSelectedNodes();
	
	function getNodeInfo(id){
		$("#t_sc_process_grid").setGridParam({page:1,postData:{"bean.procid": id}}).trigger("reloadGrid");
	}
	
	 var sc_process_procid = '<s:text name="dmmes.sc.process.procid" />';
	 var sc_process_tdeptid = '<s:text name="dmmes.sc.process.tdeptid" />';
	 var sc_process_proctname = '<s:text name="dmmes.sc.process.proctname" />';
	 var sc_process_proctnameen = '<s:text name="dmmes.sc.process.proctnameen" />';
	 var sc_process_proccode = '<s:text name="dmmes.sc.process.proccode" />';
	 var sc_process_shortname = '<s:text name="dmmes.sc.process.shortname" />';
	 var sc_process_parentid = '<s:text name="dmmes.sc.process.parentid" />';
	 var sc_process_parentname = '<s:text name="dmmes.sc.process.parentname" />';
	 var sc_process_isleaf = '<s:text name="dmmes.sc.process.isleaf" />';
	 var sc_process_parentstr = '<s:text name="dmmes.sc.process.parentstr" />';
	 var sc_process_serialno = '<s:text name="dmmes.sc.process.serialno" />';
	 var sc_process_memo = '<s:text name="dmmes.sc.process.memo" />';
	
	 var pub_operation='<s:text name="pub.operation" />';
	 
	 var bi_target_targetid = '<s:text name="dmmes.bi.target.targetid" />';
	 var bi_target_procid = '<s:text name="dmmes.bi.target.procid" />';
	 var bi_target_targetname = '<s:text name="dmmes.bi.target.targetname" />';
	 var bi_target_targetnameen = '<s:text name="dmmes.bi.target.targetnameen" />';
	 var bi_target_shortname = '<s:text name="dmmes.bi.target.shortname" />';
	 var bi_target_targetunit = '<s:text name="dmmes.bi.target.targetunit" />';
	 var bi_target_serialno = '<s:text name="dmmes.bi.target.serialno" />';
	 var bi_target_hqdatatype = '<s:text name="dmmes.bi.target.hqdatatype" />';
	 var bi_target_talgorithm = '<s:text name="dmmes.bi.target.talgorithm" />';
	 var bi_target_tal_desc = '<s:text name="dmmes.bi.target.tal_desc" />';
	 var bi_target_isevent = '<s:text name="dmmes.bi.target.isevent" />';
	 var bi_target_isedit = '<s:text name="dmmes.bi.target.isedit" />';
	 var bi_target_ismust = '<s:text name="dmmes.bi.target.ismust" />';
	 var bi_target_issum = '<s:text name="dmmes.bi.target.issum" />';
	 var bi_target_isdisplay = '<s:text name="dmmes.bi.target.isdisplay" />';
	 var bi_target_ismaintarget = '<s:text name="dmmes.bi.target.ismaintarget" />';
	 var bi_target_datatype = '<s:text name="dmmes.bi.target.datatype" />';
	 var bi_target_isyplan = '<s:text name="dmmes.bi.target.isyplan" />';
	 var bi_target_ismplan = '<s:text name="dmmes.bi.target.ismplan" />';
	 var bi_target_isproduce = '<s:text name="dmmes.bi.target.isproduce" />';
	 var bi_target_isaccept = '<s:text name="dmmes.bi.target.isaccept" />';
	 var bi_target_isused = '<s:text name="dmmes.bi.target.isused" />';
	 var bi_target_memo = '<s:text name="dmmes.bi.target.memo" />';
</script>
<%@include file="/webpage/pub/biz/footer.jsp" %>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/plugins/jquery/botree.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/webpage/biz/sc/process/jQuery.Hz2Py-min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/webpage/biz/sc/process/bizgrid.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/webpage/biz/sc/process/tree.js"></script>
