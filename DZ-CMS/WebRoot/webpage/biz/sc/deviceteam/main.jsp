<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/webpage/pub/biz/head.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/style/jquery/botree.css" type="text/css" />
<%@include file="/webpage/pub/biz/footer.jsp" %>
<body>
		<title>班组</title>
		<div class="row">
			<div class="col-xs-12">
				<div class="col-xs-12 col-sm-2 ui-widget-content resizable" style="padding-left: 0px;">
					<div id="dept_tree_grid_box" class="widget-box widget-color-blue">
						<div class="widget-header">
							<h5 class="widget-title"><i class="ace-icon fa fa-sitemap"></i><s:text name="dmmes.dm.deviceteam.depttree" /></h5>
						</div>
						<div>
							<ul id="dept_tree" class="ztree" style="height: 612px;overflow:auto;"></ul>
						</div>
					</div>
				</div>
				<div class="col-xs-12 col-sm-10">
					<div id="team_grid_box" class="widget-box widget-color-blue">
						<div class="widget-header">
							<h5 class="widget-title"><i class="ace-icon fa fa-list"></i><s:text name="dmmes.dm.deviceteam.teamtitle" /></h5>
							<div class="widget-toolbar">
								<a href="#" data-action="fullscreen" class="orange2">
									<i class="ace-icon fa fa-expand"></i>
								</a>
								<a href="#" id="team_grid_box_filter" data-action="filter">
									<i class="ace-icon fa fa-filter bigger red"></i>
								</a>
								<a href="#" data-action="collapse">
									<i class="ace-icon fa fa-chevron-up"></i>
								</a>
							</div>
						</div>
	
						<div class="widget-body">
							<div class="widget-main no-padding">
								<table id="team_grid"></table>
						
								<div id="team_grid_pager"></div>
							</div>
						</div>
					</div>

					<div id="deviceteam_grid_box" class="widget-box widget-color-blue">
						<div class="widget-header">
							<h5 class="widget-title"><i class="ace-icon fa fa-list"></i><s:text name="dmmes.dm.deviceteam.title" /></h5>
							<div class="widget-toolbar">
								<a href="#" data-action="fullscreen" class="orange2">
									<i class="ace-icon fa fa-expand"></i>
								</a>
								<a href="#" id="deviceteam_grid_box_filter" data-action="filter">
									<i class="ace-icon fa fa-filter bigger red"></i>
								</a>
								<a href="#" data-action="collapse">
									<i class="ace-icon fa fa-chevron-up"></i>
								</a>
							</div>
							<div id="deviceteam_grid_btn_add" class="widget-toolbar no-border">
								<button class="btn btn-xs btn-success bigger no-border">
									<i class="ace-icon fa fa-plus"></i>
									<s:text name="button.add" />
								</button>
							</div>
						</div>
	
						<div class="widget-body">
							<div class="widget-main no-padding">
								<table id="deviceteam_grid"></table>
						
								<div id="deviceteam_grid_pager"></div>
							</div>
						</div>
					</div>
				</div><!-- /.col -->
			
			</div><!-- /.col -->	
			<!-- PAGE CONTENT ENDS -->
		</div><!-- /.row -->
<script type="text/javascript">
 	 var dm_deviceteam_deptname = '<s:text name="dmmes.dm.deviceteam.deptname" />';
	 var dm_deviceteam_teamcode = '<s:text name="dmmes.dm.deviceteam.teamcode" />';
	 var dm_deviceteam_teamtypename = '<s:text name="dmmes.dm.deviceteam.teamtypename" />';
	 var dm_deviceteam_teamname = '<s:text name="dmmes.dm.deviceteam.teamname" />';
	 var dm_deviceteam_peoplenum = '<s:text name="dmmes.dm.deviceteam.peoplenum" />';
	 var dm_deviceteam_dutymanname = '<s:text name="dmmes.dm.deviceteam.dutymanname" />';
	 var dm_deviceteam_phone = '<s:text name="dmmes.dm.deviceteam.phone" />';
	 var dm_deviceteam_teamtitle = '<s:text name="dmmes.dm.deviceteam.teamtitle" />';
	 var dm_deviceteam_depttree = '<s:text name="dmmes.dm.deviceteam.depttree" />';
	 var dm_deviceteam_title = '<s:text name="dmmes.dm.deviceteam.title" />';
	 var dm_deviceteam_devcode = '<s:text name="dmmes.dm.deviceteam.devcode" />';
	 var dm_deviceteam_devccode = '<s:text name="dmmes.dm.deviceteam.devccode" />';
	 var dm_deviceteam_devnamename = '<s:text name="dmmes.dm.deviceteam.devnamename" />';
	 var dm_deviceteam_devclassname = '<s:text name="dmmes.dm.deviceteam.devclassname" />';
	 var dm_deviceteam_devtype = '<s:text name="dmmes.dm.deviceteam.devtype" />';
	 var dm_deviceteam_devmodel = '<s:text name="dmmes.dm.deviceteam.devmodel" />';
	 var dm_deviceteam_devusename = '<s:text name="dmmes.dm.deviceteam.devusename" />';
	 var dm_deviceteam_deviceselection = '<s:text name="dmmes.dm.deviceteam.deviceselection" />';
	 var pub_operation='<s:text name="pub.operation" />';
</script>
<script type="text/javascript" src="<%=request.getContextPath()%>/plugins/jquery/botree.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/webpage/biz/sc/deviceteam/tree.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/webpage/biz/sc/deviceteam/bizgrid.js"></script>
</body>
</html>