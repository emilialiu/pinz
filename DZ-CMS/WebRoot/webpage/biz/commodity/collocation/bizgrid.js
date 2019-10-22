$(function() {
	// grid参数
	$("#dz_commodity_collocation_grid").data("gridOptions", {
		height : parent.getClientHeights(),
		url :  rootpath + '/webpage/biz/commodity/collocation/list.action',
		colNames : [ 
					'操作',
					'id',
					'banner图路径',
					'设计师说明内容',
					'单品组成ids，用逗号分隔',
					'备注',
					'创建时间',
					'创建人',
					'修改时间',
					'修改人',
					'设计师名称',
					'设计师头像'
					],//列显示名称
		colModel : [ //	align:left,center,right,默认left;classes:列的css;datefmt:默认Y-m-d;defval:查询字段的默认值;editable:单元格是否可编辑,默认false;
					 //fixed:列宽是否固定不可变,默认false;search:在搜索模式下，定义此列是否可以作搜索列
					{name : 'processbtn',index:'', width:80, fixed:true, sortable:false, resize:false,search : false,formatter:btnformatter},
						{name : 'id',index : 'id',width : 60,sorttype : "int(11)",editable : false,hidden : true},
						{name : 'banner_url',index : 'banner_url',fixed : true,width : 100,sorttype : 'varchar(512)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'designer_said',index : 'designer_said',fixed : true,width : 100,sorttype : 'varchar(200)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'single_ids',index : 'single_ids',fixed : true,width : 100,sorttype : 'varchar(100)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'remarks',index : 'remarks',fixed : true,width : 100,sorttype : 'varchar(500)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'create_time',index : 'create_time',fixed : true,width : 100,sorttype : 'datetime',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'create_by',index : 'create_by',fixed : true,width : 100,sorttype : 'varchar(64)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'update_time',index : 'update_time',fixed : true,width : 100,sorttype : 'datetime',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'update_by',index : 'update_by',fixed : true,width : 100,sorttype : 'varchar(64)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'designer_name',index : 'designer_name',fixed : true,width : 100,sorttype : 'varchar(50)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'designer_head_url',index : 'designer_head_url',fixed : true,width : 100,sorttype : 'varchar(200)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}}
					],
		processbtncol:2,//操作按钮放在第几个列
		isedit:false,//是否可编辑列表
		advsearch:true,
		multiSort:true,
		viewurl : "/webpage/biz/commodity/collocation/doModify.action",
		addurl : "/webpage/biz/commodity/collocation/doAdd.action",
		modifyurl : "/webpage/biz/commodity/collocation/doModifySave.action",
		deleteurl : "/webpage/biz/commodity/collocation/delete.action"
		
	});
	
	initgrid("dz_commodity_collocation");
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
			"bean.banner_url" : datalist.banner_url,
			"bean.designer_said" : datalist.designer_said,
			"bean.single_ids" : datalist.single_ids,
			"bean.remarks" : datalist.remarks,
			"bean.create_time" : datalist.create_time,
			"bean.create_by" : datalist.create_by,
			"bean.update_time" : datalist.update_time,
			"bean.update_by" : datalist.update_by,
			"bean.designer_name" : datalist.designer_name,
			"bean.designer_head_url" : datalist.designer_head_url
	}; 
}
function initEditGridRowData(_bizname,data,selectedRowId){
	var datalist = getEditGridParams(_bizname,selectedRowId);
	var dataJson = eval(data);
	return dataRow = {  
				id : dataJson.id,
				banner_url : datalist.banner_url,
				designer_said : datalist.designer_said,
				single_ids : datalist.single_ids,
				remarks : datalist.remarks,
				create_time : datalist.create_time,
				create_by : datalist.create_by,
				update_time : datalist.update_time,
				update_by : datalist.update_by,
				designer_name : datalist.designer_name,
				designer_head_url : datalist.designer_head_url
	}; 
}
