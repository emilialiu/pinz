var scripts = [null, null];

$(function() {
	// grid参数
	$("#processhis_grid").data("gridOptions", {
		height : 300,
		url :  rootpath + '/manager/activity/approvalhis.action?prokey='+prokey+'&bussinesskey='+bussinesskey,
		colNames : [  '审批环节', '审批人', '审批时间', '审批结果', '审批意见' ],//列显示名称
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
	
	initgrid("processhis");
});

