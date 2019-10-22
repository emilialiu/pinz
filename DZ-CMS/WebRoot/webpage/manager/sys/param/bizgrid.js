$(function() {
		// grid参数
		$("#sys_param_grid").data("gridOptions", {
			height : parent.getClientHeights(),
			url :  rootpath + '/manager/sys/param/list.action',
			colNames : [ 
						'操作',
							'参数编号',
							'参数名称',
							'参数值',
							'部门id',
							'备注'
						],//列显示名称
			colModel : [ //	align:left,center,right,默认left;classes:列的css;datefmt:默认Y-m-d;defval:查询字段的默认值;editable:单元格是否可编辑,默认false;
						 //fixed:列宽是否固定不可变,默认false;search:在搜索模式下，定义此列是否可以作搜索列
						{name : 'processbtn',index:'', width:80, fixed:true, sortable:false, resize:false,search : false,formatter:btnformatter},
							{name : 'paramcode',index : 'paramcode',fixed : true,width : 200,sorttype : 'varchar2',editable : false,hidden : true},
							{name : 'paramname',index : 'paramname',fixed : true,width : 200,sorttype : 'varchar2',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne' ]}},
							{name : 'paramvalue',index : 'paramvalue',fixed : true,width : 200,sorttype : 'varchar2',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne' ]}},
							{name : 'deptid',index : 'deptid',fixed : true,width : 100,sorttype : 'varchar2',editable : false,hidden : true},
							{name : 'memo',index : 'memo',fixed : false,width : 100,sorttype : 'varchar2',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne' ]}}
						],
			processbtncol:2,//操作按钮放在第几个列
			isedit:false,//是否可编辑列表
			advsearch:true,
			viewurl : "/manager/sys/param/doModify.action",
			addurl : "/manager/sys/param/doAdd.action",
			modifyurl : "/manager/sys/param/doModifySave.action",
			deleteurl : "/manager/sys/param/delete.action"
		});
		
		initgrid("sys_param");
	});

//删除修改进入表单时传递参数
function getGridParams(_bizname){
	var selectedRowId = $("#"+_bizname+"_grid").jqGrid("getGridParam", "selrow");  
    var str = $("#"+_bizname+"_grid").jqGrid("getRowData", selectedRowId); // 根据行ID，获取选中行的数据
    return {"bean.paramcode" : str.paramcode};
}

function getParams(_bizname){
	var selectedRowId = $("#"+_bizname+"_grid").jqGrid("getGridParam", "selrow");  
    var str = $("#"+_bizname+"_grid").jqGrid("getRowData", selectedRowId); // 根据行ID，获取选中行的数据
    return "?bean.paramcode="+str.paramcode;  
}
//取编辑grid列表数据
function getEditGridParams(_bizname,selectedRowId){
	var datalist = $("#"+_bizname+"_grid").jqGrid('getRowData', selectedRowId);
	return {  
				"bean.paramcode" : datalist.paramcode,
				"bean.paramname" : datalist.paramname,
				"bean.paramvalue" : datalist.paramvalue,
				"bean.deptid" : datalist.deptid,
				"bean.memo" : datalist.memo
	    }; 
}
function initEditGridRowData(_bizname,data,selectedRowId){
	var datalist = getEditGridParams(_bizname,selectedRowId);
	var dataJson = eval(data);
	return dataRow = {  
			paramcode : dataJson.id,
			paramname : datalist.paramname,
			paramvalue : datalist.paramvalue,
			deptid : datalist.deptid,
			memo : datalist.memo
        }; 
}
