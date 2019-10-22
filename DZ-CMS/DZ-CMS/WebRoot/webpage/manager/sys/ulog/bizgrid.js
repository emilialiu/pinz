$(function() {
	// grid参数
	$("#sys_ulog_grid").data("gridOptions", {
		height : 400,
		url :  rootpath + '/manager/sys/ulog/list.action',
		colNames : [  '角色ID', '机构信息', '用户名', 'IP地址', '操作功能','操作时间','操作信息'],//列显示名称
		colModel : [ //	align:left,center,right,默认left;classes:列的css;datefmt:默认Y-m-d;defval:查询字段的默认值;editable:单元格是否可编辑,默认false;
					 //fixed:列宽是否固定不可变,默认false;search:在搜索模式下，定义此列是否可以作搜索列
					 // {name : 'processbtn',index:'', width:80, fixed:true, sortable:false, resize:false,search : false,formatter:myformatter},
					 {name : 'logid',index : 'logid',width : 80,sorttype : "int",editable : true,hidden : true}, 
					 {name : 'deptname',index : 'deptname',fixed : true,width : 150,sorttype : 'string',searchoptions : {sopt : [ 'cn', 'eq', 'ne' ]}}, 
					 {name : 'username',index : 'username',fixed : true,width : 150,sorttype : 'string',searchoptions : {sopt : [ 'cn', 'eq', 'ne' ]}}, 
					 {name : 'useip',index : 'use_ip',fixed : true,width : 150,sorttype : 'string',searchoptions : {sopt : [ 'cn', 'eq', 'ne' ]}}, 
					 {name : 'usemodule',index : 'use_module',fixed : true,width : 150,sorttype : 'string',searchoptions : {sopt : [ 'cn', 'eq', 'ne' ]}},
					 {name : 'usetime',index : 'use_time',fixed : true,width : 150,sorttype : 'string',searchoptions : {sopt : [ 'cn', 'eq', 'ne' ]}},
					 {name : 'useoperation',index : 'use_operation',fixed : true,width : 200,sorttype : 'string',searchoptions : {sopt : [ 'cn', 'eq', 'ne' ]}}
					],
		processbtncol:2,//第几个列
		isedit:true,//是否可编辑列表
		advsearch:true,
		viewurl : rootpath+"/manager/sys/ulog/doModify.action?actiontype=modify",
		deleteurl : rootpath+"/manager/sys/ulog/delete.action?actiontype=delete"
	});
	function  myformatter(cellvalue,options,rowobj){
		return cellvalue;
	}
	initgrid("sys_ulog");		
});

function delAll(){
	setTimeout(function(){
		parent.bootbox.confirm("确定删除全部系统日志吗?", function(result) {
			if(result) {
			    $.ajax({
			        url :rootpath+"/manager/sys/ulog/deleteall.action",
			        data : {},  
			        cache : false,
			        error : function(data) {
			        	parent.alertMsg("系统ajax交互错误");  
			        },  
			        success : function(data) {  
			        	var dataJson = eval(data);
			            if(dataJson.success) { 
			            	parent.alertMsg("信息删除成功!");  
			            	//删除保存完成
			                jQuery("#sys_ulog_grid").setGridParam({url:rootpath + '/manager/sys/ulog/list.action'}).trigger("reloadGrid"); 
			            } else {  
			            	parent.alertMsg("删除操作失败!");
			            }  
			        }  
			    });  
			}
		});
	},0);
}