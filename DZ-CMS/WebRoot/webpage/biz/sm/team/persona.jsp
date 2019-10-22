<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/webpage/pub/biz/head.jsp" %>
<body>
<div id="persona_info" >
	<div class="col-xs-12">
		<div id="teamstaff_unadd_grid_box" class="col-xs-12 col-sm-5  widget-box widget-color-blue">
			<div class="widget-header">
				<h5 class="widget-title"><i class="ace-icon fa fa-list"></i>可添加人员列表</h5>
				<div class="widget-toolbar">
					<a href="#" data-action="collapse">
						<i class="ace-icon fa fa-chevron-up"></i>
					</a>
				</div>
			</div>

			<div class="widget-body">
				<div class="widget-main no-padding">
					<table id="teamstaff_unadd_grid"></table>
			
					<div id="teamstaff_unadd_grid_pager"></div>
				</div>
			</div>
		</div>
		<div class="col-xs-12 col-sm-2 center">
			<div style="margin-top: 150px;">
				<button id="teamstaff_btn_save" class="btn btn-info btn-purple btn-sm">
					<span class="ace-icon fa fa-arrow-right smaller-30"></span>
					添加
				</button>
				<div class="space-2"></div>
				<button id="teamstaff_btn_remove" class="btn btn-info btn-pink btn-sm">
					<span class="ace-icon fa fa-arrow-left smaller-30"></span>
					撤销
				</button>
			</div>
		</div>
		<div id="teamstaff_add_grid_box" class="col-xs-12 col-sm-5 widget-box widget-color-red">
			<div class="widget-header">
				<h5 class="widget-title"><i class="ace-icon fa fa-list"></i>已添加人员列表</h5>
				<div class="widget-toolbar">
					<a href="#" data-action="collapse">
						<i class="ace-icon fa fa-chevron-up"></i>
					</a>
				</div>
			</div>

			<div class="widget-body">
				<div class="widget-main no-padding">
					<table id="teamstaff_add_grid"></table>
			
					<div id="teamstaff_add_grid_pager"></div>
				</div>
			</div>
		</div>
	</div>
</div>
<%@include file="/webpage/pub/biz/footer.jsp" %>
<script type="text/javascript">
var teamid="<%=request.getParameter("teamid")%>";
var deptid="<%=request.getParameter("deptid")%>";
$(function() {
	//grid参数
	$("#teamstaff_unadd_grid").data("gridOptions", {
		height : 350,
		url :  rootpath + '/biz/sm/teamstaff/doUnList.action?bean.teamid='+teamid+'&bean.deptid='+deptid,
		colNames : ['','可添加的人员' ],//列显示名称
		colModel : [ //	align:left,center,right,默认left;classes:列的css;datefmt:默认Y-m-d;defval:查询字段的默认值;editable:单元格是否可编辑,默认false;
					 //fixed:列宽是否固定不可变,默认false;search:在搜索模式下，定义此列是否可以作搜索列
					 {name : 'staffid',index : 'staffid',width:220,orttype : "string",editable : true,hidden:true},
					 {name : 'staffname',index:'staffname',fixed : true,width : 250,sorttype : 'string',searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}}	
				],
		multiselect:true,
		isedit:false,//是否可编辑列表
		exportexcellocal:false,
		advsearch:false
	});
	
	initgrid("teamstaff_unadd");
	
	// grid参数
	$("#teamstaff_add_grid").data("gridOptions", {
		height : 350,
		url :  rootpath + '/biz/sm/teamstaff/doList.action?bean.teamid='+teamid,
		colNames : ['','已添加的人员' ],//列显示名称
		colModel : [ //	align:left,center,right,默认left;classes:列的css;datefmt:默认Y-m-d;defval:查询字段的默认值;editable:单元格是否可编辑,默认false;
					 //fixed:列宽是否固定不可变,默认false;search:在搜索模式下，定义此列是否可以作搜索列
					 {name : 'staffid',index : 'staffid',width:220,orttype : "string",editable : true,hidden:true},
		             {name : 'staffname',index:'staffname',fixed : true,width : 250,sorttype : 'string',searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}}	
					],
		multiselect:true,
		isedit:false,//是否可编辑列表
		exportexcellocal:false,
		advsearch:false
	});
	
	initgrid("teamstaff_add");
});

//添加人员的功能(主要操作右边的那个表)
$("#teamstaff_btn_save").click(function addInfo(){
	var selectedRowId = $("#teamstaff_unadd_grid").jqGrid("getGridParam", "selarrrow");
	if (selectedRowId=="") {  
		alertMsg("请选择要添加的人员!");  
		return;  
	} 
	var staffids='';
	for(var i = 0; i < selectedRowId.length; i++){
		var datalist = $("#teamstaff_unadd_grid").jqGrid('getRowData', selectedRowId[i]);
		staffids += datalist.staffid + ',';
	}
	//这里主要负责和服务器的交互
	$.ajax({
		url: rootpath+'/biz/sm/teamstaff/doAdd.action',//路径
		cache: false,
		data: { 'staffids' : staffids.substring(0, staffids.length-1) ,'bean.teamid':teamid},
		success: function(data){
			var dataJson = eval(data);
			if(dataJson.success){
				$("#teamstaff_unadd_grid").trigger("reloadGrid");
				$("#teamstaff_add_grid").trigger("reloadGrid");
				parent.window.frames[parent.getCurIframeID()].teamstaffReloadGrid();
				parent.alertMsg('人员添加成功');
			}else{
				parent.alertError(dataJson.errormessage);
			}
		},
		error: function(data) {
			parent.alertMsg('系统ajax交互错误');
		}
	});
});

//撤销人员的功能(主要操作左边的那个表)
$("#teamstaff_btn_remove").click(function deleteInfo(){
	var selectedRowId = $("#teamstaff_add_grid").jqGrid("getGridParam", "selarrrow");
	if (selectedRowId=="") {  
		parent.alertMsg("请选择要撤销的人员!");  
		return;  
	} 
	var staffids='';
	for(var i = 0; i < selectedRowId.length; i++){
		var datalist = $("#teamstaff_add_grid").jqGrid('getRowData', selectedRowId[i]);
		staffids += datalist.staffid + ',';
	}
	//这里主要负责和服务器的交互
	$.ajax({
		url: rootpath+'/biz/sm/teamstaff/doDel.action',//路径
		cache: false,
		data: { 'staffids' : staffids.substring(0, staffids.length-1) ,'bean.teamid':teamid},
		success: function(data){
			var dataJson = eval(data);
			if(dataJson.success){
				$("#teamstaff_unadd_grid").trigger("reloadGrid");
				$("#teamstaff_add_grid").trigger("reloadGrid");
				parent.window.frames[parent.getCurIframeID()].teamstaffReloadGrid();
				parent.alertMsg('人员撤销成功');
			}else{
				parent.alertError(dataJson.errormessage);
			}
		},
		error: function(data) {
			parent.alertMsg('系统ajax交互错误');
		}
	});
});
</script>
</body>
</html>