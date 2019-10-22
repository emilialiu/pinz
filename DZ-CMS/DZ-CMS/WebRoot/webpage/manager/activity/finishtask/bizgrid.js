$(function() {
	// grid参数
	$("#act_finishtask_grid").data("gridOptions", {
		height : parent.getClientHeights(),
		url :  rootpath + '/manager/task/selectHispartList.action?bean.id='+id,
		colNames : [  '流程名称','任务名称','任务发起人','审批环节', '审批人', '审批时间', '审批结果', '审批意见' ],//列显示名称
		colModel : [ //	align:left,center,right,默认left;classes:列的css;datefmt:默认Y-m-d;defval:查询字段的默认值;editable:单元格是否可编辑,默认false;
					 //fixed:列宽是否固定不可变,默认false;search:在搜索模式下，定义此列是否可以作搜索列
		             {name : 'processname',index : 'processname',fixed : true,width : 150,sorttype : 'string',editable : false,searchoptions : {sopt : [ 'cn','eq' ]}},
					 {name : 'ywname',index : 'ywname',fixed : true,width : 160,sortable : false,editable : false,searchoptions : {sopt : [ 'cn' ]},search:false},
					 {name : 'appusername',index : 'appusername',fixed : true,width : 100,sorttype : 'string',editable : false,searchoptions : {sopt : [ 'cn','eq' ]},search:false}, 
		             {name : 'nameWithoutCascade',index : 'nameWithoutCascade',width : 60,sorttype : "string",editable : false,search:false}, 
					 {name : 'username',index : 'username',fixed : true,width : 60,sorttype : 'string',editable : false,search:false}, 
					 {name : 'endtime',index : 'endtime',fixed : true,width :130,sorttype : 'string',editable : false,search:false}, 
					 {name : 'comment1',index : 'comment1',fixed : true,width : 60,sortable : false,editable : false,search:false}, 
					 {name : 'comment2',index : 'comment2',width : 100,editable : false,search:false} 
					],
//		processbtncol:2,//操作按钮放在第几个列
		isedit:false,//是否可编辑列表
		advsearch:true,
		multiSort:true
		
	});
	
	initgrid("act_finishtask");
});

