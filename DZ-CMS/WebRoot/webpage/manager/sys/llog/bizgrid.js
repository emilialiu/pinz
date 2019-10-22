$(function() {
	// grid参数
	$("#sys_llog_grid").data("gridOptions", {
		height : 400,
		url :  rootpath + '/manager/sys/llog/list.action',
		colNames : [  '角色ID', '机构信息', '用户名', '登录时间', 'IP地址','日志信息' ],//列显示名称
		colModel : [ //	align:left,center,right,默认left;classes:列的css;datefmt:默认Y-m-d;defval:查询字段的默认值;editable:单元格是否可编辑,默认false;
					 //fixed:列宽是否固定不可变,默认false;search:在搜索模式下，定义此列是否可以作搜索列
					// {name : 'processbtn',index:'', width:80, fixed:true, sortable:false, resize:false,search : false,formatter:myformatter},
					 {name : 'logid',index : 'logid',width : 80,sorttype : "int",editable : true,hidden : true}, 
					 {name : 'deptname',index : 'deptname',fixed : true,width : 150,sorttype : 'string',searchoptions : {sopt : [ 'cn', 'eq', 'ne' ]}}, 
					 {name : 'loginname',index : 'login_name',fixed : true,width : 150,sorttype : 'string',searchoptions : {sopt : [ 'cn', 'eq', 'ne' ]}}, 
					 {name : 'logintime',index : 'login_time',fixed : true,width : 150,sorttype : 'string',searchoptions : {sopt : [ 'cn', 'eq', 'ne' ]}}, 
					 {name : 'loginip',index : 'login_ip',fixed : true,width : 150,sorttype : 'string',searchoptions : {sopt : [ 'cn', 'eq', 'ne' ]}},
					 {name : 'loginmessage',index : 'login_message',fixed : false,width : 200,sorttype : 'string',searchoptions : {sopt : [ 'cn', 'eq', 'ne' ]}}
					],
		processbtncol:2,//第几个列
		isedit:true,//是否可编辑列表
		advsearch:true
		//
	});
	function  myformatter(cellvalue,options,rowobj){
		return cellvalue;
	}
	initgrid("sys_llog");		
});

//取编辑grid列表数据
function getEditGridParams(_bizname,selectedRowId){
	var datalist = $("#"+_bizname+"_grid").jqGrid('getRowData', selectedRowId);
	return {  
	        "bean.logid" : datalist.logid,  
	        "bean.deptname" : datalist.deptname,  
	        "bean.loginname" : datalist.loginname,  
	        "bean.logintime" : datalist.logintime,  
	        "bean.loginip" : datalist.loginip,
	        "bean.loginmessage" : datalist.loginmessage
	    }; 
}
function initEditGridRowData(_bizname,data,selectedRowId){
	var datalist = getEditGridParams(_bizname,selectedRowId);
	var dataJson = eval(data);
	return dataRow = {  
			logid : dataJson.id,  
        	deptname : datalist.deptname,  
        	loginname : datalist.loginname,  
        	logintime : datalist.logintime,  
        	loginip : datalist.loginip,
        	loginmessage : datalist.loginmessage
            
        }; 
}