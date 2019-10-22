$(function() {
	// grid参数
	$("#act_nodytaskhis_grid").data("gridOptions", {
		height : 250,
		url :  rootpath + '/manager/activity/approvalhis.action?processDefinitionId='+processDefinitionId+'&bussinesskey='+bussinesskey,
		colNames : [  sys_task_approvallink, sys_task_approvalman, sys_task_approvaltime,sys_task_examinationresult,sys_task_approvalcomments ],//列显示名称
		colModel : [ //	align:left,center,right,默认left;classes:列的css;datefmt:默认Y-m-d;defval:查询字段的默认值;editable:单元格是否可编辑,默认false;
					 //fixed:列宽是否固定不可变,默认false;search:在搜索模式下，定义此列是否可以作搜索列
					 {name : 'name',index : 'name',width : 60,sorttype : "string",editable : false,search:false}, 
					 {name : 'username',index : 'username',fixed : true,width : 60,sorttype : 'string',editable : false,search:false}, 
					 {name : 'endtime',index : 'endtime',fixed : true,width :130,sorttype : 'string',editable : false,search:false}, 
					 {name : 'message1',index : 'message1',fixed : true,width : 60,sortable : false,editable : false,search:false}, 
					 {name : 'message2',index : 'message2',width : 100,editable : false,search:false} 
					],
//		processbtncol:2,//操作按钮放在第几个列
		isedit:false,//是否可编辑列表
		advsearch:true,
		multiSort:true
		
	});
	
	initgrid("act_nodytaskhis");
});

function changwidth(){
	$("#act_nodytaskhis_grid").jqGrid('setGridWidth', $("#act_nodytaskhis_grid_box").width());
}
