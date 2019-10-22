$(function() {
		// grid参数
$("#act_hisfinish_grid")
		.data(
				"gridOptions",
				{
					height :parent.getClientHeights(),
					url : rootpath+ '/manager/activity/finished.action',
					colNames : [ sys_hisfinish_id,sys_hisfinish_processDefinitionId,sys_hisfinish_startTime,sys_hisfinish_endTime,sys_hisfinish_deleteReason
							 ],// 列显示名称
					colModel : [ // align:left,center,right,默认left;classes:列的css;datefmt:默认Y-m-d;defval:查询字段的默认值;editable:单元格是否可编辑,默认false;
					// fixed:列宽是否固定不可变,默认false;search:在搜索模式下，定义此列是否可以作搜索列
					 {name : 'id',index : 'id',width : 100,sorttype : "string",editable : true,fixed : true}
					 ,{name : 'processDefinitionId',index : 'processDefinitionId',width : 140,sorttype : "string",editable : true,fixed : true}
					 ,{name : 'startTime',index : 'startTime',width : 140,sorttype : "string",editable : true,fixed : true}
					 ,{name : 'endTime',index : 'endTime',width : 140,sorttype : "string",editable : true,fixed : true}
					 ,{name : 'deleteReason',index : 'deleteReason',width : 140,sorttype : "string",editable : true,fixed : true,search:false}
					],
					// processbtncol:2,//操作按钮放在第几个列
					advsearch : true,
					multiSort : true
				});

			initgrid("act_hisfinish");
			initvalid("act_hisfinish");

	
});

