$(function() {
	// grid参数
	$("#sys_elog_grid").data("gridOptions", {
		height : 400,
		url :  rootpath + '/manager/sys/elog/list.action',
		colNames : [  '异常ID', '机构信息', 'IP地址', '异常功能名称', '异常时间','异常信息' ],//列显示名称
		colModel : [ {name : 'exceptionid',index : 'exceptionid',width : 80,sorttype : "int",editable : true,hidden : true}, 
					 {name : 'deptname',index : 'deptname',fixed : true,width : 150,sorttype : 'string',searchoptions : {sopt : [ 'cn', 'eq', 'ne' ]}}, 
					 {name : 'useip',index : 'useip',fixed : true,width : 150,sorttype : 'string',searchoptions : {sopt : [ 'cn', 'eq', 'ne' ]}},
					 {name : 'exceptionmodule',index : 'exception_module',fixed : true,width : 150,sorttype : 'string',searchoptions : {sopt : [ 'cn', 'eq', 'ne' ]}}, 
					 {name : 'exceptiontime',index : 'exception_time',fixed : true,width : 150,sorttype:"string",searchoptions : {sopt : [ 'cn', 'eq', 'ne' ]}}, 
					 {name : 'exceptioncontent',index : 'exception_content',fixed : false,width : 200,sorttype : 'string',searchoptions : {sopt : [ 'cn', 'eq', 'ne' ]}}
					],
		processbtncol:2,//第几个列
		isedit:false,//是否可编辑列表
		advsearch:true
		//
	});
	
	initgrid("sys_elog");		
});

//取编辑grid列表数据
function getEditGridParams(_bizname,selectedRowId){
	var datalist = $("#"+_bizname+"_grid").jqGrid('getRowData', selectedRowId);
	return {  
	        "bean.exceptionid" : datalist.exceptionid,  
	        "bean.deptname" : datalist.deptname,  
	        "bean.exceptionmodule" : datalist.exceptionmodule,  
	        "bean.exceptiontime" : datalist.exceptiontime,  
	        "bean.useip" : datalist.useip,
	        "bean.exceptioncontent" : datalist.exceptioncontent
	    }; 
}
function initEditGridRowData(_bizname,data,selectedRowId){
	var datalist = getEditGridParams(_bizname,selectedRowId);
	var dataJson = eval(data);
	return dataRow = {  
			exceptionid : dataJson.id,  
        	deptname : datalist.deptname,  
        	exceptionmodule : datalist.exceptionmodule,  
        	exceptiontime : datalist.exceptiontime,  
        	useip : datalist.useip,
        	exceptioncontent : datalist.exceptioncontent
            
        }; 
}