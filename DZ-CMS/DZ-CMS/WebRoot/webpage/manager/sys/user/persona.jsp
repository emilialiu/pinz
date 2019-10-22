<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/webpage/pub/biz/head.jsp" %>
<div id="sys_user_persona_info" >
	<input type="hidden" id="userid" value="<%=request.getParameter("userid") %>"/>
	<div class="col-xs-12">
		<div id="sys_user_unrole_grid_box" class="col-xs-12 col-sm-5  widget-box widget-color-blue">
			<div class="widget-header">
				<h5 class="widget-title"><i class="ace-icon fa fa-list"></i>可授予角色列表</h5>
				<div class="widget-toolbar">
					<a href="#" data-action="collapse">
						<i class="ace-icon fa fa-chevron-up"></i>
					</a>
				</div>
			</div>

			<div class="widget-body">
				<div class="widget-main no-padding">
					<table id="sys_user_unrole_grid"></table>
			
					<div id="sys_user_unrole_grid_pager"></div>
				</div>
			</div>
		</div>
		<div class="col-xs-12 col-sm-2 center">
			<div style="margin-top: 150px;">
				<button id="sys_user_btn_save" class="btn btn-info btn-purple btn-sm">
					<span class="ace-icon fa fa-arrow-right smaller-30"></span>
					授予
				</button>
				<div class="space-2"></div>
				<button id="sys_user_btn_remove" class="btn btn-info btn-pink btn-sm">
					<span class="ace-icon fa fa-arrow-left smaller-30"></span>
					撤销
				</button>
			</div>
		</div>
		<div id="sys_user_role_grid_box" class="col-xs-12 col-sm-5 widget-box widget-color-red">
			<div class="widget-header">
				<h5 class="widget-title"><i class="ace-icon fa fa-list"></i>已授予角色列表</h5>
				<div class="widget-toolbar">
					<a href="#" data-action="collapse">
						<i class="ace-icon fa fa-chevron-up"></i>
					</a>
				</div>
			</div>

			<div class="widget-body">
				<div class="widget-main no-padding">
					<table id="sys_user_role_grid"></table>
			
					<div id="sys_user_role_grid_pager"></div>
				</div>
			</div>
		</div>
	</div>
</div>
<%@include file="/webpage/pub/biz/footer.jsp" %>
<script type="text/javascript">
	$(function(){
		// grid参数
		$("#sys_user_unrole_grid").data("gridOptions", {
			height : 350,
			url :  rootpath + "/manager/sys/userrole/userroleunlist.action?bean.userid=<%=request.getParameter("userid") %>",
			colNames : ['','可授予的角色' ],//列显示名称
			colModel : [ //	align:left,center,right,默认left;classes:列的css;datefmt:默认Y-m-d;defval:查询字段的默认值;editable:单元格是否可编辑,默认false;
						 //fixed:列宽是否固定不可变,默认false;search:在搜索模式下，定义此列是否可以作搜索列
						 {name : 'roleid',index : 'roleid',width:220,orttype : "string",editable : true,hidden:true},
						 {name : 'rolename',index:'rolename',fixed : true,width : 250,sorttype : 'string',searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}}	
						],
			multiselect:true,
			isedit:false,//是否可编辑列表
			exportexcellocal:false,
			advsearch:false
		});
		initgrid("sys_user_unrole");
		
		// grid参数
		$("#sys_user_role_grid").data("gridOptions", {
			height : 350,
			url :  rootpath + "/manager/sys/userrole/userrolelist.action?bean.userid=<%=request.getParameter("userid") %>",
			colNames : ['','已授予的角色' ],//列显示名称
			colModel : [ //	align:left,center,right,默认left;classes:列的css;datefmt:默认Y-m-d;defval:查询字段的默认值;editable:单元格是否可编辑,默认false;
						 //fixed:列宽是否固定不可变,默认false;search:在搜索模式下，定义此列是否可以作搜索列
						 {name : 'roleid',index : 'roleid',width:220,orttype : "string",editable : true,hidden:true},
			             {name : 'rolename',index:'rolename',fixed : true,width : 250,sorttype : 'string',searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}}	
						],
			multiselect:true,
			isedit:false,//是否可编辑列表
			exportexcellocal:false,
			advsearch:false
		});
		initgrid("sys_user_role");
		
		//授予权限的功能(主要操作右边的那个表)
		$("#sys_user_btn_save").click(function addInfo(){
			var selectedRowId = $("#sys_user_unrole_grid").jqGrid("getGridParam", "selarrrow");
			if (selectedRowId=="") {  
				parent.alertMsg("请选择要授予的角色!");  
		        return;  
		    } 
			var roles='';
			for(var i = 0; i < selectedRowId.length; i++){
				var datalist = $("#sys_user_unrole_grid").jqGrid('getRowData', selectedRowId[i]);
				roles += datalist.roleid + ',';
			}
			//这里主要负责和服务器的交互
			$.ajax({
				url: rootpath+'/manager/sys/userrole/doAdd.action',//路径
				cache: false,
				data: { 'roles' : roles.substring(0, roles.length-1) ,'bean.userid':$("#userid").val()},
				success: function(data){
					var dataJson = eval(data);
					if(dataJson.success){
						$("#sys_user_unrole_grid").setGridParam({page:1}).trigger("reloadGrid");
						$("#sys_user_role_grid").setGridParam({page:1}).trigger("reloadGrid");
						parent.alertMsg('角色授予成功');
					}else{
						parent.alertErrorMsg(dataJson.errormessage);
					}
				},
				error: function(data) {
					parent.alertErrorMsg('系统ajax交互错误');
				}
			});
		});

		//撤销权限的功能(主要操作左边的那个表)
		$("#sys_user_btn_remove").click(function deleteInfo(){
			var selectedRowId = $("#sys_user_role_grid").jqGrid("getGridParam", "selarrrow");
			if (selectedRowId=="") {  
				parent.alertMsg("请选择要授予的角色!");  
		        return;  
		    } 
			var roles='';
			for(var i = 0; i < selectedRowId.length; i++){
				var datalist = $("#sys_user_role_grid").jqGrid('getRowData', selectedRowId[i]);
				roles += datalist.roleid + ',';
			}
			//这里主要负责和服务器的交互
			$.ajax({
				url: rootpath+'/manager/sys/userrole/userroledel.action',//路径
				cache: false,
				data: { 'roles' : roles.substring(0, roles.length-1) ,'bean.userid':$("#userid").val()},
				success: function(data){
					var dataJson = eval(data);
					if(dataJson.success){
						$("#sys_user_unrole_grid").setGridParam({page:1}).trigger("reloadGrid");
						$("#sys_user_role_grid").setGridParam({page:1}).trigger("reloadGrid");
						parent.alertMsg('角色撤销成功');
					}else{
						parent.alertErrorMsg(dataJson.errormessage);
					}
				},
				error: function(data) {
					parent.alertErrorMsg('系统ajax交互错误');
				}
			});
		});
	});
</script>