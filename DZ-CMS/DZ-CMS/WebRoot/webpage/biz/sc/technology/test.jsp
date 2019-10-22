<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/webpage/pub/biz/head.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/webpage/common/plugins/jui/themes/redmond/jquery-ui-1.9.2.custom.css" />
<style>
	.ui-jqgrid .tree-wrap-ltr{float:left;}
</style>
<body>
		<title><br>角色管理</title>
		<div class="row">
			<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS -->				
				<div class="input-group col-sm-8">
					<span class="input-group-addon">
						<i class="ace-icon fa fa-search"></i>
					</span>					
					<input type="text" id="sys_role_text_search" class="form-control search-query" placeholder="角色名称、角色编码" />
					<span class="input-group-btn">
						<button id="sys_role_btn_search" type="button" class="btn btn-primary btn-sm">
							<span class="ace-icon fa fa-search icon-on-right bigger-110"></span>&nbsp;&nbsp;
							查询
						</button>
						<button id="sys_role_btn_advsearch" style="margin-left:10px;" class="btn btn-primary btn-sm">
							<span class="ace-icon fa fa-filter icon-on-right bigger-110"></span>&nbsp;&nbsp;
							高级查询
						</button>
					</span>
				</div>
			</div><!-- /.col -->
			<div class="col-xs-12">				
				<div id="sys_role_grid_box" class="widget-box widget-color-blue">
					<div class="widget-header">
						<h5 class="widget-title"><i class="ace-icon fa fa-list"></i>用户角色管理</h5>
						<div class="widget-toolbar">
							<a href="#" data-action="fullscreen" class="white">
								<i class="ace-icon fa fa-expand"></i>
							</a>
							<a href="#" id="sys_role_grid_box_filter" class="white" data-action="filter">
								<i class="ace-icon fa fa-filter bigger"></i>
							</a>
							<a href="#" data-action="collapse" class="white">
								<i class="ace-icon fa fa-chevron-up"></i>
							</a>
						</div>
						<div id="sys_role_grid_btn_add" class="widget-toolbar no-border">
							<button class="btn btn-xs btn-primary bigger no-border">
								<i class="ace-icon fa fa-plus"></i>
								新增
							</button>
						</div>
					</div>

					<div class="widget-body">
						<div class="widget-main no-padding">
							<table id="sys_role_grid"></table>
						</div>
					</div>
				</div>
			</div><!-- /.col -->

			<!-- PAGE CONTENT ENDS -->
		</div><!-- /.row -->
</body>

<%@include file="/webpage/pub/biz/footer.jsp" %>
	<script type="text/javascript">
	$(document).ready(function(){
		var topicjson={
		   "response": [
		          {
		              "id": "1",
		              "elementName": "Grouping",
		              level:"0", parent:"", isLeaf:false, expanded:false, loaded:true
		          },
		          {
		              "id": "1_1",
		              "elementName": "Simple Grouping",
		              level:"1", parent:"1", isLeaf:true, expanded:false, loaded:true
		          },
		          {
		              "id": "1_2",
		              "elementName": "May be some other grouping",
		              level:"1", parent:"1", isLeaf:true, expanded:false, loaded:true
		          },
		           {
		              "id": "2_1",
		              "elementName": "Image Formatter",
		              level:"1", parent:"2", isLeaf:true, expanded:false, loaded:true
		          },
		          {
		              "id": "2",
		              "elementName": "CustomFormater",
		              level:"0", parent:"", isLeaf:false, expanded:true, loaded:true
		          },
		          {
		              "id": "2_2",
		              "elementName": "Anchor Formatter",
		              level:"1", parent:"2", isLeaf:true, expanded:false, loaded:true
		          }
		      ]
		   };

		var lastsel;
		var grid = $("#sys_role_grid");
		grid.jqGrid({
		   datastr: topicjson,
		   datatype: "jsonstring",
		   height: "400",
		   loadui: "disable",
		   colNames: ["id","Items","url"],
		   colModel: [
		       {name: "id",width:200, hidden:false, key:true},
		       {name: "elementName", editable:true,width:250, resizable: false},
		       {name: "url",width:1, editable:true,hidden:true}
		   ],
		   gridview: true,
		   treeGrid: true,
		   treeGridModel: "adjacency",
		   ExpandColumn: "elementName",
		   //autowidth: true,
		   rowNum: 10000,
		   ExpandColClick: true,
		   jsonReader: {
		       repeatitems: false,
		       root: "response"//"dataRows"
		   },
		   treeReader : {  
		      level_field: "level",  
		      parent_id_field: "parent",   
		      leaf_field: "isLeaf",
		      expanded_field: "expanded"  
		   },
		   onSelectRow: function(id){ 
		    	if(id && id!==lastsel){
				    grid.jqGrid('restoreRow',lastsel); 
				    //grid.jqGrid('editRow',id,true); 
				    lastsel=id; 
		    	} 
		   }
		});
	});
	</script>
</html>
