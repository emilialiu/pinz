$(function() {
	// grid参数
	$("#act_starttask_grid").data("gridOptions", {
		height : parent.getClientHeights(),
		url :  rootpath + '/manager/task/startTaskList.action',
		colNames : [  '流程名称','任务名称','流程开始时间', '流程结束时间', '时长(分钟)', '流程是否结束' ],//列显示名称
		colModel : [ //	align:left,center,right,默认left;classes:列的css;datefmt:默认Y-m-d;defval:查询字段的默认值;editable:单元格是否可编辑,默认false;
					 //fixed:列宽是否固定不可变,默认false;search:在搜索模式下，定义此列是否可以作搜索列
		             {name : 'processname',index : 'processname',fixed : true,width : 180,sorttype : 'string',editable : false,searchoptions : {sopt : [ 'cn','eq' ]}},
					 {name : 'ywname',index : 'ywname',fixed : true,width : 180,sortable : false,editable : false,searchoptions : {sopt : [ 'cn' ]},search:false},
		             {name : 'starttime',index : 'starttime',fixed : true,width :150,sorttype : "string",editable : false,search:false},  
					 {name : 'endtime',index : 'endtime',fixed : true,width :150,sorttype : 'string',editable : false,search:false}, 
					 {name : 'duration',index : 'duration',fixed : true,width : 100,sortable : false,editable : false,search:false}, 
					 {name : 'isend',index : 'isend',fixed : true,width : 100,editable : false,search:false} 
					],
//		processbtncol:2,//操作按钮放在第几个列
		isedit:false,//是否可编辑列表
		advsearch:true,
		multiSort:true
		
	});
	
	initgrid("act_starttask");
});

