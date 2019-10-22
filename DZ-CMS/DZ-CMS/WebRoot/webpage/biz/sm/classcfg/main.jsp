<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/webpage/pub/biz/head.jsp" %>
		<title>班次配置</title>
		<div class="row">
			<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS -->				
				<div class="input-group col-sm-8">
					<span class="input-group-addon">
						<i class="ace-icon fa fa-search"></i>
					</span>					
					<input type="text" id="classcfg_text_search" class="form-control search-query" placeholder="<s:text name="dmmes.sm.classcfg.dept" />" />
					<span class="input-group-btn">
						<button id="classcfg_btn_search" type="button" class="btn btn-primary btn-sm">
							<span class="ace-icon fa fa-search icon-on-right bigger-110"></span>&nbsp;&nbsp;
							<s:text name="button.query" />
						</button>
						<button id="classcfg_btn_advsearch" style="margin-left:10px;" class="btn btn-primary btn-sm">
							<span class="ace-icon fa fa-filter icon-on-right bigger-110"></span>&nbsp;&nbsp;
							<s:text name="button.Advancedquery" />
						</button>
					</span>
				</div>
			</div><!-- /.col -->
			<div class="col-xs-12">				
				<div id="classcfg_grid_box" class="widget-box widget-color-blue">
					<div class="widget-header">
						<h5 class="widget-title"><i class="ace-icon fa fa-list"></i>班次配置表</h5>
						<div class="widget-toolbar">
							<a href="#" data-action="fullscreen" class="orange2">
								<i class="ace-icon fa fa-expand"></i>
							</a>
							<a href="#" id="classcfg_grid_box_filter" data-action="filter">
								<i class="ace-icon fa fa-filter bigger red"></i>
							</a>
							<a href="#" data-action="collapse">
								<i class="ace-icon fa fa-chevron-up"></i>
							</a>
						</div>
						<div id="classcfg_grid_btn_add" class="widget-toolbar no-border">
							<button class="btn btn-xs btn-success bigger no-border">
								<i class="ace-icon fa fa-plus"></i>
								<s:text name="button.add" />
							</button>
						</div>
					</div>

					<div class="widget-body">
						<div class="widget-main no-padding">
							<table id="classcfg_grid"></table>
					
							<div id="classcfg_grid_pager"></div>
						</div>
					</div>
				</div>
			</div><!-- /.col -->
	
				
				<!-- PAGE CONTENT ENDS -->
		</div><!-- /.row -->

<%@include file="/webpage/pub/biz/footer.jsp" %>
<script type="text/javascript">
 var pub_operation='<s:text name="pub.operation" />';
 var qt_samplebook_classes='<s:text name="dmmes.qt.samplebook.classes" />';
 var sm_classcfg_dept='<s:text name="dmmes.sm.classcfg.dept" />';
 var sm_classcfg__start='<s:text name="dmmes.sm.classcfg.start" />';
 var sm_classcfg__end='<s:text name="dmmes.sm.classcfg.end" />';
 var button_add='<s:text name="button.add" />';
</script>
<script type="text/javascript" src="<%=request.getContextPath()%>/webpage/biz/sm/classcfg/bizgrid.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/plugins/jquery/owin.js"></script>
