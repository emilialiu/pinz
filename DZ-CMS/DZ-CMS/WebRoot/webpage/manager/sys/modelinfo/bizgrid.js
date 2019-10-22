var scripts = [null, null];
$('.page-content-area').ace_ajax('loadScripts', scripts, function() {
	$(function() {
		// sys_modelinfo_grid参数
		$("#sys_modelinfo_grid").data("gridOptions", {
			height : 400,
			url :  rootpath + '/manager/sys/modelinfo/list.action',
			colNames : [ 
						'操作',
							'模版ID',
							'模版名称',
							'对应数据库表',
							'本地模板文件名',
							'在服务器上保存的名称'
						],//列显示名称
			colModel : [ //	align:left,center,right,默认left;classes:列的css;datefmt:默认Y-m-d;defval:查询字段的默认值;editable:单元格是否可编辑,默认false;
						 //fixed:列宽是否固定不可变,默认false;search:在搜索模式下，定义此列是否可以作搜索列
						{name : 'processbtn',index:'', width:80, fixed:true, sortable:false, resize:false,search : false,formatter:btnformatter},
							{name : 'modelid',index : 'modelid',fixed : true,width : 100,sorttype : 'varchar2',editable : false,hidden:true},
							{name : 'modelname',index : 'modelname',fixed : true,width : 160,sorttype : 'varchar2',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
							{name : 'mtable',index : 'mtable',fixed : true,width : 140,sorttype : 'varchar2',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
							{name : 'exceltb',index : 'exceltb',fixed : true,width : 300,sorttype : 'varchar2',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
							{name : 'targetname',index : 'targetname',fixed : true,width : 100,sorttype : 'varchar2',editable : false,hidden:true}
						],
						onSelectRow: function(rowid, status){
							var rowData = $("#sys_modelinfo_grid").jqGrid('getRowData',rowid);
							reloadByModel(rowData.modelid);
						},
			processbtncol:2,//操作按钮放在第几个列
			isedit:false,//是否可编辑列表
			advsearch:true,
			pagerpos:"right",
			viewrecords:false,
			viewurl : rootpath+"/manager/sys/modelinfo/doModify.action",
			addurl : rootpath+"/manager/sys/modelinfo/doAddSave.action",
			modifyurl : rootpath+"/manager/sys/modelinfo/doModifySave.action?actiontype=modifysave",
			deleteurl : rootpath+"/manager/sys/modelinfo/delete.action?actiontype=delete"
			
		});		
		initgrid("sys_modelinfo");
		initvalid("sys_modelinfo");
		
		//sys_modelmapp_grid参数
		$("#sys_modelmapp_grid").data("gridOptions", {
			height : 400,
			url :  rootpath + '/manager/sys/modelmapp/list.action',
			colNames : [ 
						'操作',
							'映射关系ID',
							'模版ID',
							'excel列名称',
							'excel列序号',
							'映射字段',
							'是否为空',
							'是否转码',
							'转码列的父类',
							'数据类型'
						],//列显示名称
			colModel : [ //	align:left,center,right,默认left;classes:列的css;datefmt:默认Y-m-d;defval:查询字段的默认值;editable:单元格是否可编辑,默认false;
						 //fixed:列宽是否固定不可变,默认false;search:在搜索模式下，定义此列是否可以作搜索列
						{name : 'processbtn',index:'', width:80, fixed:true, sortable:false, resize:false,search : false,formatter:btnformatter},
							{name : 'mid',index : 'mid',fixed : true,width : 100,sorttype : 'varchar2',editable : false,hidden:true},
							{name : 'modelid',index : 'modelid',fixed : true,width : 100,sorttype : 'varchar2',editable : false,hidden:true},
							{name : 'ecolname',index : 'ecolname',fixed : true,width : 100,sorttype : 'varchar2',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
							{name : 'ecolindex',index : 'ecolindex',fixed : true,width : 100,sorttype : 'varchar2',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
							{name : 'mfield',index : 'mfield',fixed : true,width : 100,sorttype : 'varchar2',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
							{name : 'isnull',index : 'isnull',fixed : true,width : 100,sorttype : 'char',editable : false,formatter:sfformatter,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
							{name : 'isscode',index : 'isscode',fixed : true,width : 100,sorttype : 'char',editable : false,formatter:sfformatter,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
							{name : 'dicttypeid',index : 'dicttypeid',fixed : true,width : 100,sorttype : 'varchar2',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
							{name : 'dtype',index : 'dtype',fixed : true,width : 100,sorttype : 'varchar2',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}}
						],
			processbtncol:2,//操作按钮放在第几个列
			isedit:false,//是否可编辑列表
			advsearch:true,
			pagerpos:"right",
			viewrecords:false,
			viewurl : rootpath+"/manager/sys/modelmapp/doModify.action",
			addurl : rootpath+"/manager/sys/modelmapp/doAddSave.action",
			modifyurl : rootpath+"/manager/sys/modelmapp/doModifySave.action?actiontype=modifysave",
			deleteurl : rootpath+"/manager/sys/modelmapp/delete.action?actiontype=delete"
			
		});		

		function sfformatter(cellvalue,options,rowobj){
			if(cellvalue==1){
				return '是';
			}else{
				return '否';
			}
		}
		
		initgrid("sys_modelmapp");
		initvalid("sys_modelmapp");
		
		function reloadByModel(modelid){
			jQuery("#sys_modelmapp_grid").setGridParam({url:rootpath + '/manager/sys/modelmapp/list.action?modelid='+modelid}).trigger("reloadGrid");
		}
	});
});

//弹出新增界面前事件方法
var _openDialog4Adding = function(_bizname) {
	if(_bizname=='sys_modelmapp'){
	    var consoleDlg = $("#sys_modelmapp_info");  
	    var dialogButtonPanel = consoleDlg.siblings(".ui-dialog-buttonpane"); 
	    $("#sys_modelmapp_info").Validform().resetForm();
	    
	    consoleDlg.find(".info").css('display','none');	    
	    consoleDlg.find("input").removeAttr("disabled").val("");  
	    consoleDlg.find("textarea").removeAttr("disabled").val("");	    
	    consoleDlg.trigger('domi.add.before');
	    
	    dialogButtonPanel.find("button:not(:contains('取消'))").hide();  
	    dialogButtonPanel.find("button:contains('创建')").show();  
	    dialogButtonPanel.find("button").addClass('btn btn-xs btn-primary');
	    
	    var selectedRowId = $("#sys_modelinfo_grid").jqGrid("getGridParam", "selrow");
		if(selectedRowId==''||selectedRowId==null){
				alertMsg("请先选择模版信息!");
				return;
		}
		var rowData = $("#sys_modelinfo_grid").jqGrid('getRowData',selectedRowId);
		var modelid = rowData.modelid;
		
	    consoleDlg.dialog("option", "title", "创建信息").dialog("open");
	    $("#sys_modelmapp_info").find("#modelid").val(modelid);
	    consoleDlg.trigger('domi.add.after');
	}else{
		    var consoleDlg = $("#"+_bizname+"_info");  
		    var dialogButtonPanel = consoleDlg.siblings(".ui-dialog-buttonpane"); 
		    
		    $("#"+_bizname+"_form").Validform().resetForm();
		    consoleDlg.find(".info").css('display','none');

		    
		    consoleDlg.find("input").removeAttr("disabled").val("");  
		    consoleDlg.find("textarea").removeAttr("disabled").val("");
		    
		    consoleDlg.trigger('domi.add.before');
		    
		    dialogButtonPanel.find("button:not(:contains('取消'))").hide();  
		    dialogButtonPanel.find("button:contains('创建')").show();  
		    dialogButtonPanel.find("button").addClass('btn btn-xs btn-primary');
		    consoleDlg.dialog("option", "title", "创建信息").dialog("open");  
		    consoleDlg.trigger('domi.add.after');
	}
};
