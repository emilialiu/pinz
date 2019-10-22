$(function() {
	// grid参数
	$("#sys_tomatter_grid").data("gridOptions", {
		height : parent.getClientHeights(),
		url :  rootpath + '/pub/tomatter/list.action?bean.matterid='+matterid,
		colNames : [  '待办ID', '事项名称', '发布时间', '处理状态', '处理时间', '处理人员','跳转地址', '内容' ],//列显示名称
		colModel : [ //	align:left,center,right,默认left;classes:列的css;datefmt:默认Y-m-d;defval:查询字段的默认值;editable:单元格是否可编辑,默认false;
					 //fixed:列宽是否固定不可变,默认false;search:在搜索模式下，定义此列是否可以作搜索列
					// {name : 'processbtn',index:'', width:80, fixed:true, sortable:false, resize:false,search : false,formatter:myformatter},
					 {name : 'matterid',index : 'matterid',width : 80,sorttype : "int",editable : true,hidden : true}, 
					 {name : 'mattername',index : 'mattername',fixed : true,width : 150,sorttype : 'string',searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}}, 
					 {name : 'addtime',index : 'addtime',fixed : true,width : 130,sorttype : 'string',searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}}, 
					 {name : 'mattertype',index : 'mattertype',fixed : true,width : 100,sorttype : 'string',searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}}, 
					 {name : 'handletime',index : 'handletime',fixed : true,width : 130,sorttype : 'string',searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}}, 
					 {name : 'handlename',index : 'handlename',fixed : true,width : 100,sorttype : 'string',searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
					 {name : 'linkurl',index : 'linkurl',fixed : true,width : 250,sorttype : 'string',searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}}, 
					 {name : 'memo',index : 'memo',fixed : false,width : 150,sorttype : 'string',searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}} 

					],
		processbtncol:2,//第几个列
		isedit:true,//是否可编辑列表
		advsearch:true
	});
	function myformatter(cellvalue,options,rowobj){
		return cellvalue;
	}
	initgrid("sys_tomatter");		
});
