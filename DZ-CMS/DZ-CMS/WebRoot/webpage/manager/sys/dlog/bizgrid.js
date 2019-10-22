$(function() {
	// grid参数
	$("#sys_dlog_grid").data("gridOptions", {
		height : 400,
		url :  rootpath + '/manager/sys/dlog/list.action',
		colNames : [  '调度日志ID', '调度名称', '调度时间','调度模块', '调度内容' ],//列显示名称
		colModel : [ {name : 'logid',index : 'logid',width : 80,sorttype : "int",editable : true,hidden : true}, 
					 {name : 'name',index : 'name',fixed : true,width : 200,sorttype : 'string',searchoptions : {sopt : [ 'cn', 'eq', 'ne' ]}}, 
					 {name : 'usetime',index : 'use_time',fixed : true,width : 200,sorttype : 'string',searchoptions : {sopt : [ 'cn', 'eq', 'ne' ]}},
					 {name : 'usemodule',index : 'use_module',fixed : true,width : 200,sorttype : 'string',searchoptions : {sopt : [ 'cn', 'eq', 'ne' ]}},
					 {name : 'content',index : 'content',fixed : false,width : 150,sorttype : 'string',searchoptions : {sopt : [ 'cn', 'eq', 'ne' ]}}
					],
		processbtncol:2,//第几个列
		isedit:true,//是否可编辑列表
		advsearch:true
		//
	});
	function  myformatter(cellvalue,options,rowobj){
		return cellvalue;
	}
	initgrid("sys_dlog");		
});

//取编辑grid列表数据
function getEditGridParams(_bizname,selectedRowId){
	var datalist = $("#"+_bizname+"_grid").jqGrid('getRowData', selectedRowId);
	return {  
	        "bean.logid" : datalist.logid,  
	        "bean.name" : datalist.name,  
	        "bean.usetime" : datalist.usetime,  
	        "bean.usemodule" : datalist.usemodule,  
	        "bean.content" : datalist.content
	    }; 
}
function initEditGridRowData(_bizname,data,selectedRowId){
	var datalist = getEditGridParams(_bizname,selectedRowId);
	var dataJson = eval(data);
	return dataRow = {  
			logid : dataJson.id,  
			name : datalist.name,  
			usetime : datalist.usetime,  
			usemodule : datalist.usemodule,  
			content : datalist.content
            
        }; 
}