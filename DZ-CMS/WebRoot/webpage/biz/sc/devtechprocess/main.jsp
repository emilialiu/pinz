<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/webpage/pub/biz/head.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/style/jquery/botree.css" type="text/css" />
		<title>工序对应设备</title>
	<body>
		<div class="row">
	<div class="col-sm-2">
				<div class="widget-box widget-color-blue">
					<div class="widget-header">
						<h4 class="widget-title lighter smaller"><i class="ace-icon fa fa-sitemap"></i><s:text name="dmmes.sc.devtechprocess.process" /></h4>
					</div>
					<div class="widget-body">
						<div class="widget-main padding-8" id="leftid">
							<ul id="process_tree" class="ztree" style="height:603px;overflow:auto;"></ul>
						</div>
					</div>
				</div>
			</div>
			<div class="col-xs-18">
				<!-- PAGE CONTENT BEGINS -->				
				<div class="input-group col-sm-8">
					<span class="input-group-addon">
						<i class="ace-icon fa fa-search"></i>
					</span>					
					<input type="text" id="t_sc_devtechprocess_text_search" class="form-control search-query" placeholder="设备名称" />
					<span class="input-group-btn">
						<button id="t_sc_devtechprocess_btn_search" type="button" class="btn btn-purple btn-sm">
							<span class="ace-icon fa fa-search icon-on-right bigger-110"></span>&nbsp;&nbsp;
							<s:text name="button.query" />
						</button>
						<button id="t_sc_devtechprocess_btn_advsearch" style="margin-left:10px;" class="btn btn-pink btn-sm">
							<span class="ace-icon fa fa-filter icon-on-right bigger-110"></span>&nbsp;&nbsp;
							<s:text name="button.Advancedquery" />
						</button>
					</span>
				</div>
			</div><!-- /.col -->
			<div class="col-xs-10">				
				<div id="t_sc_devtechprocess_grid_box" class="widget-box widget-color-blue">
					<div class="widget-header">
						<h5 class="widget-title"><i class="ace-icon fa fa-list"></i><s:text name="dmmes.sc.devtechprocess.title" /></h5>
						<div class="widget-toolbar">
							<a href="#" data-action="fullscreen" class="white">
								<i class="ace-icon fa fa-expand"></i>
							</a>
							<a href="#" id="t_sc_devtechprocess_grid_box_filter" class="white" data-action="filter">
								<i class="ace-icon fa fa-filter bigger"></i>
							</a>
							<a href="#" data-action="collapse" class="white">
								<i class="ace-icon fa fa-chevron-up"></i>
							</a>
						</div>
						<div id="t_sc_devtechprocess_grid_btn_add" class="widget-toolbar no-border">
							<button class="btn btn-xs btn-primary bigger no-border">
								<i class="ace-icon fa fa-plus"></i>
								<s:text name="button.add" />
							</button>
						</div>
					</div>

					<div class="widget-body">
						<div class="widget-main no-padding">
							<table id="t_sc_devtechprocess_grid"></table>
					
							<div id="t_sc_devtechprocess_grid_pager"></div>
						</div>
					</div>
				</div>
			</div><!-- /.col -->
				
			<!-- PAGE CONTENT ENDS -->
		</div><!-- /.row -->
	</body>
<%@include file="/webpage/pub/biz/footer.jsp" %>
<script type="text/javascript">
	 var dm_deviceteam_devcode = '<s:text name="dmmes.dm.deviceteam.devcode" />';
	 var dm_deviceteam_devccode = '<s:text name="dmmes.dm.deviceteam.devccode" />';
	 var dm_deviceteam_devnamename = '<s:text name="dmmes.dm.deviceteam.devnamename" />';
	 var dm_deviceteam_devclassname = '<s:text name="dmmes.dm.deviceteam.devclassname" />';
	 var dm_deviceteam_devtype = '<s:text name="dmmes.dm.deviceteam.devtype" />';
	 var dm_deviceteam_devmodel = '<s:text name="dmmes.dm.deviceteam.devmodel" />';
	 var dm_deviceteam_devusename = '<s:text name="dmmes.dm.deviceteam.devusename" />';
	 var pub_operation='<s:text name="pub.operation" />';
	 var button_add='<s:text name="button.add" />';
	 var button_add='<s:text name="button.add" />';
	 var pub_selectLastProcess='<s:text name="dmmes.pub.selectLastProcess" />';
</script>
<script type="text/javascript" src="<%=request.getContextPath()%>/webpage/biz/sc/devtechprocess/bizgrid.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/plugins/jquery/botree.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/webpage/biz/sc/process/tree.js"></script>
<script type="text/javascript">
	//获取树节点的信息
	//var srcNode = BOTree1.getSelectedNodes();
	
	function getNodeInfo(id){
		$("#t_sc_devtechprocess_grid").setGridParam({page:1,postData:{"bean.procid": id}}).trigger("reloadGrid");
	}
	
</script>