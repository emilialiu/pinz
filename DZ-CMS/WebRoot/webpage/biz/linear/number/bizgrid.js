$(function() {
	// grid参数
	$("#dz_linear_number_grid").data("gridOptions", {
		height : parent.getClientHeights(),
		url :  rootpath + '/webpage/biz/linear/number/list.action',
		colNames : [ 
					'操作',
					'id',
					'条数名称',
					'系数',
					'备注',
					'排序',
					'创建时间',
					'创建人',
					'修改时间',
					'修改人'
					],//列显示名称
		colModel : [ //	align:left,center,right,默认left;classes:列的css;datefmt:默认Y-m-d;defval:查询字段的默认值;editable:单元格是否可编辑,默认false;
					 //fixed:列宽是否固定不可变,默认false;search:在搜索模式下，定义此列是否可以作搜索列
					{name : 'processbtn',index:'', width:80, fixed:true, sortable:false, resize:false,search : false,formatter:btnformatter},
						{name : 'id',index : 'id',width : 60,sorttype : "bigint(16)",editable : false,hidden : true},
						{name : 'name',index : 'name',fixed : true,width : 100,sorttype : 'varchar(64)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'coefficient',index : 'coefficient',fixed : true,width : 100,sorttype : 'float',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'remarks',index : 'remarks',fixed : true,width : 100,sorttype : 'varchar(512)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'sort',index : 'sort',fixed : true,width : 100,sorttype : 'int(11)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'create_time',index : 'create_time',width : 60,sorttype : "datetime",editable : false,hidden : true},
						{name : 'create_by',index : 'create_by',width : 60,sorttype : "varchar(64)",editable : false,hidden : true},
						{name : 'update_time',index : 'update_time',width : 60,sorttype : "datetime",editable : false,hidden : true},
						{name : 'update_by',index : 'update_by',width : 60,sorttype : "varchar(64)",editable : false,hidden : true}
					],
		processbtncol:2,//操作按钮放在第几个列
		isedit:false,//是否可编辑列表
		advsearch:true,
		multiSort:true,
		viewurl : "/webpage/biz/linear/number/doModify.action",
		addurl : "/webpage/biz/linear/number/doAdd.action",
		modifyurl : "/webpage/biz/linear/number/doModifySave.action",
		deleteurl : "/webpage/biz/linear/number/delete.action"
		
	});
	
	initgrid("dz_linear_number");
});

//删除修改进入表单时传递参数
function getGridParams(_bizname){
	var selectedRowId = $("#"+_bizname+"_grid").jqGrid("getGridParam", "selrow");  
    var str = $("#"+_bizname+"_grid").jqGrid("getRowData", selectedRowId); // 根据行ID，获取选中行的数据
		return {"bean.id" : str.id};
}

function getParams(_bizname){
	var selectedRowId = $("#"+_bizname+"_grid").jqGrid("getGridParam", "selrow");  
    var str = $("#"+_bizname+"_grid").jqGrid("getRowData", selectedRowId); // 根据行ID，获取选中行的数据
		return "?bean.id="+str.id;
}

//取编辑grid列表数据
function getEditGridParams(_bizname,selectedRowId){
	var datalist = $("#"+_bizname+"_grid").jqGrid('getRowData', selectedRowId);
	return {  
			"bean.id" : datalist.id,
			"bean.name" : datalist.name,
			"bean.coefficient" : datalist.coefficient,
			"bean.remarks" : datalist.remarks,
			"bean.sort" : datalist.sort,
			"bean.create_time" : datalist.create_time,
			"bean.create_by" : datalist.create_by,
			"bean.update_time" : datalist.update_time,
			"bean.update_by" : datalist.update_by
	}; 
}
function initEditGridRowData(_bizname,data,selectedRowId){
	var datalist = getEditGridParams(_bizname,selectedRowId);
	var dataJson = eval(data);
	return dataRow = {  
				id : dataJson.id,
				name : datalist.name,
				coefficient : datalist.coefficient,
				remarks : datalist.remarks,
				sort : datalist.sort,
				create_time : datalist.create_time,
				create_by : datalist.create_by,
				update_time : datalist.update_time,
				update_by : datalist.update_by
	}; 
}
