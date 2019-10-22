<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/webpage/pub/biz/head.jsp" %>
<title>资源管理</title>
<style>
<!--
input[type=checkbox].ace.ace-switch.ace-switch-lt+.lbl::before {
	content: "是\a0\a0\a0\a0\a0\a0\a0\a0\a0\a0\a0\否"
}
-->
</style>	
		<div class="row">
			<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS -->				
				<div class="input-group col-sm-8">
					<span class="input-group-addon">
						<i class="ace-icon fa fa-search"></i>
					</span>					
					<input type="text" id="sys_dicttype_text_search" class="form-control search-query" placeholder="<s:text name="dmmes.sys.dict.number" />、<s:text name="dmmes.sys.dict.name" />" />
					<span class="input-group-btn">
						<button id="sys_dicttype_btn_search" type="button" class="btn btn-primary btn-sm">
							<span class="ace-icon fa fa-search icon-on-right bigger-110"></span>&nbsp;&nbsp;
							<s:text name="button.query" />
						</button>
						<button id="sys_dicttype_btn_advsearch" style="margin-left:10px;" class="btn btn-primary btn-sm">
							<span class="ace-icon fa fa-filter icon-on-right bigger-110"></span>&nbsp;&nbsp;
							<s:text name="button.Advancedquery" />
						</button>
					</span>
				</div>
			</div><!-- /.col -->
			<div class="col-xs-6">				
				<div id="sys_dicttype_grid_box" class="widget-box widget-color-blue">
					<div class="widget-header">
						<h5 class="widget-title"><i class="ace-icon fa fa-list"></i><s:text name="dmmes.sys.dict.title1" /></h5>
						<div class="widget-toolbar">
							<a href="#" data-action="fullscreen" class="white">
								<i class="ace-icon fa fa-expand"></i>
							</a>
							<a href="#" id="sys_dicttype_grid_box_filter" class="white" data-action="filter">
								<i class="ace-icon fa fa-filter bigger"></i>
							</a>
							<a href="#" data-action="collapse" class="white">
								<i class="ace-icon fa fa-chevron-up"></i>
							</a>
						</div>
						<div id="sys_dicttype_grid_btn_add" class="widget-toolbar no-border">
							<button class="btn btn-xs btn-primary bigger no-border">
								<i class="ace-icon fa fa-plus"></i>
								<s:text name="button.add" />
							</button>
						</div>
					</div>

					<div class="widget-body">
						<div class="widget-main no-padding">
							<table id="sys_dicttype_grid"></table>
					
							<div id="sys_dicttype_grid_pager"></div>
						</div>
					</div>
				</div>
			</div><!-- /.col -->
			<div class="col-xs-6">				
				<div id="sys_dict_grid_box" class="widget-box widget-color-blue">
					<div class="widget-header">
						<h5 class="widget-title"><i class="ace-icon fa fa-list"></i><s:text name="dmmes.sys.dict.title2" /></h5>
						<div class="widget-toolbar">
							<a href="#" data-action="fullscreen" class="white" class="orange2">
								<i class="ace-icon fa fa-expand"></i>
							</a>
							<a href="#" data-action="collapse" class="white">
								<i class="ace-icon fa fa-chevron-up"></i>
							</a>
						</div>
						<div id="sys_dict_grid_btn_add" class="widget-toolbar no-border">
							<button class="btn btn-xs btn-primary bigger no-border">
								<i class="ace-icon fa fa-plus"></i>
								<s:text name="button.add" />
							</button>
						</div>
					</div>

					<div class="widget-body">
						<div class="widget-main no-padding">
							<table id="sys_dict_grid"></table>
					
							<div id="sys_dict_grid_pager"></div>
						</div>
					</div>
				</div>
			</div>	
		</div><!-- /.row -->
<%@include file="/webpage/pub/biz/footer.jsp" %>
<script type="text/javascript">
 var pub_operation='<s:text name="pub.operation" />';
 var dmmes_pub_operation='<s:text name="dmmes.pub.operation" />';
 var dmmes_pub_modify='<s:text name="dmmes.pub.modify" />';
 var button_add='<s:text name="button.add" />';
 var sys_dict_number ='<s:text name="dmmes.sys.dict.number" />';
 var sys_dict_select ='<s:text name="dmmes.sys.dict.select" />';
 var sys_dict_name ='<s:text name="dmmes.sys.dict.name" />';
 var sys_dict_description='<s:text name="dmmes.sys.dict.description" />';
 var sys_dict_whether ='<s:text name="dmmes.sys.dict.whether" />';
 var sys_dict_resourceNumber ='<s:text name="dmmes.sys.dict.resourceNumber" />';
 var sys_dict_resourceName ='<s:text name="dmmes.sys.dict.resourceName" />';
 var sys_dict_resourceType ='<s:text name="dmmes.sys.dict.resourceType" />';
 var qt_assaysample_memo='<s:text name="dmmes.qt.assaysample.memo" />';
</script>		
<script type="text/javascript" src="<%=request.getContextPath()%>/webpage/manager/sys/dict/bizgrid.js"></script>