<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/webpage/pub/biz/head.jsp" %>
	<body>
		<title>人员快捷功能</title>
		<div class="row">
			<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS -->				
				<div class="input-group col-sm-8">
					<span class="input-group-addon">
						<i class="ace-icon fa fa-search"></i>
					</span>					
					<input type="text" id="sys_shortcut_func_text_search" class="form-control search-query" placeholder="<s:text name="dmmes.shortcut.func.function" />" />
					<span class="input-group-btn">
						<button id="sys_shortcut_func_btn_search" type="button" class="btn btn-purple btn-sm">
							<span class="ace-icon fa fa-search icon-on-right bigger-110"></span>&nbsp;&nbsp;
							<s:text name="button.query" />
						</button>
						<button id="sys_shortcut_func_btn_advsearch" style="margin-left:10px;" class="btn btn-pink btn-sm">
							<span class="ace-icon fa fa-filter icon-on-right bigger-110"></span>&nbsp;&nbsp;
							<s:text name="button.Advancedquery" />
						</button>
					</span>
				</div>
			</div><!-- /.col -->
			<div class="col-xs-12">				
				<div id="sys_shortcut_func_grid_box" class="widget-box widget-color-blue">
					<div class="widget-header">
						<h5 class="widget-title"><i class="ace-icon fa fa-list"></i><s:text name="dmmes.shortcut.func.title" /></h5>
						<div class="widget-toolbar">
							<a href="#" data-action="fullscreen" class="white">
								<i class="ace-icon fa fa-expand"></i>
							</a>
							<a href="#" id="sys_shortcut_func_grid_box_filter" class="white" data-action="filter">
								<i class="ace-icon fa fa-filter bigger"></i>
							</a>
							<a href="#" data-action="collapse" class="white">
								<i class="ace-icon fa fa-chevron-up"></i>
							</a>
						</div>
						<div id="sys_shortcut_func_grid_btnadd" class="widget-toolbar no-border" onclick="Editfunc()">
							<button class="btn btn-xs btn-primary bigger no-border">
								<i class="ace-icon fa fa-plus"></i>
								<s:text name="button.add" />
							</button>
						</div>
					</div>

					<div class="widget-body">
						<div class="widget-main no-padding">
							<table id="sys_shortcut_func_grid"></table>
					
							<div id="sys_shortcut_func_grid_pager"></div>
						</div>
					</div>
				</div>
			</div><!-- /.col -->
				
			<!-- PAGE CONTENT ENDS -->
		</div><!-- /.row -->
	</body>
<%@include file="/webpage/pub/biz/footer.jsp" %>
<script type="text/javascript">
 var pub_operation='<s:text name="pub.operation" />';
 var pub_success='<s:text name="dmmes.pub.success" />';
 var shortcut_func_function ='<s:text name="dmmes.shortcut.func.function" />';
 var shortcut_func_commonFunction ='<s:text name="dmmes.shortcut.func.commonFunction" />';
</script>
<script type="text/javascript" src="<%=request.getContextPath()%>/webpage/biz/shortcut/func/bizgrid.js"></script>
</html>