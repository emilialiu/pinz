$(function() {
	// grid参数
	$("#dz_custom_enterprise_grid").data("gridOptions", {
		height : parent.getClientHeights(),
		url :  rootpath + '/webpage/biz/custom/enterprise/list.action',
		colNames : [ 
					'操作',
					'id',
					'姓名',
					'手机号码',
					'团购意向：什么类型，多少预算',
					'说明',
					'需求数量,数量由数据字典提供区间值',
					'定制时间',
					'定制客户id',
					'处理状态'
					],//列显示名称
		colModel : [ //	align:left,center,right,默认left;classes:列的css;datefmt:默认Y-m-d;defval:查询字段的默认值;editable:单元格是否可编辑,默认false;
					 //fixed:列宽是否固定不可变,默认false;search:在搜索模式下，定义此列是否可以作搜索列
					{name : 'processbtn',index:'', width:80, fixed:true, sortable:false, resize:false,search : false,formatter:btnformatter},
						{name : 'id',index : 'id',width : 60,sorttype : "bigint(16)",editable : false,hidden : true},
						{name : 'name',index : 'name',fixed : true,width : 100,sorttype : 'varchar(20)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'phone',index : 'phone',fixed : true,width : 100,sorttype : 'varchar(50)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'description',index : 'description',fixed : true,width : 100,sorttype : 'varchar(500)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'remarks',index : 'remarks',fixed : true,width : 100,sorttype : 'varchar(500)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'demand_count',index : 'demand_count',fixed : true,width : 100,sorttype : 'int(11)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'create_time',index : 'create_time',fixed : true,width : 100,sorttype : 'datetime',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'customer_id',index : 'customer_id',fixed : true,width : 100,sorttype : 'varchar(64)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'state',index : 'state',fixed : true,width : 100,sorttype : 'int(11)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}}
					],
		processbtncol:2,//操作按钮放在第几个列
		isedit:false,//是否可编辑列表
		advsearch:true,
		multiSort:true,
		viewurl : "/webpage/biz/custom/enterprise/doModify.action",
		addurl : "/webpage/biz/custom/enterprise/doAdd.action",
		modifyurl : "/webpage/biz/custom/enterprise/doModifySave.action",
		deleteurl : "/webpage/biz/custom/enterprise/delete.action"
		
	});
	
	initgrid("dz_custom_enterprise");
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
			"bean.phone" : datalist.phone,
			"bean.description" : datalist.description,
			"bean.remarks" : datalist.remarks,
			"bean.demand_count" : datalist.demand_count,
			"bean.create_time" : datalist.create_time,
			"bean.customer_id" : datalist.customer_id,
			"bean.state" : datalist.state
	}; 
}
function initEditGridRowData(_bizname,data,selectedRowId){
	var datalist = getEditGridParams(_bizname,selectedRowId);
	var dataJson = eval(data);
	return dataRow = {  
				id : dataJson.id,
				name : datalist.name,
				phone : datalist.phone,
				description : datalist.description,
				remarks : datalist.remarks,
				demand_count : datalist.demand_count,
				create_time : datalist.create_time,
				customer_id : datalist.customer_id,
				state : datalist.state
	}; 
}
