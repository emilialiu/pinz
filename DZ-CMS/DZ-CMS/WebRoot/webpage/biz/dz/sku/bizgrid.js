$(function() {
	// grid参数
	$("#dz_sku_grid").data("gridOptions", {
		height : parent.getClientHeights()/2,
		url :  rootpath + '/webpage/biz/dz/sku/list.action',
		colNames : [ 
					'操作',
					'id',
					'分类id',
					'规格名称',
					'规格值',
					'父级id',
					'排序',
					'商品分类',
					'创建人',
					'创建时间',
					'修改人',
					'修改时间',
					'规格code',
					'规格限制id'
					],//列显示名称
		colModel : [ //	align:left,center,right,默认left;classes:列的css;datefmt:默认Y-m-d;defval:查询字段的默认值;editable:单元格是否可编辑,默认false;
					 //fixed:列宽是否固定不可变,默认false;search:在搜索模式下，定义此列是否可以作搜索列
					{name : 'processbtn',index:'', width:80, fixed:true, sortable:false, resize:false,search : false,formatter:btnformatter},
						{name : 'id',index : 'id',width : 60,sorttype : "bigint(16)",editable : false,hidden : true},
						{name : 'product_category_id',index : 'product_category_id',width : 60,sorttype : "bigint(16)",editable : false,hidden : true},
						{name : 'sku_name',index : 'sku_name',fixed : true,width : 200,sorttype : 'varchar(20)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'sku_value',index : 'sku_value',fixed : true,width : 200,sorttype : 'varchar(256)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'parent_id',index : 'parent_id',width : 60,sorttype : "varchar(64)",editable : false,hidden : true},
						{name : 'sort',index : 'sort',fixed : true,width : 100,sorttype : 'int(11)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'categoryname',index : 'categoryname',fixed : true,width : 200,sorttype : 'varchar(64)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'create_by',index : 'create_by',width : 60,sorttype : "varchar(64)",editable : false,hidden : true},
						{name : 'create_time',index : 'create_time',width : 60,sorttype : "datetime",editable : false,hidden : true},
						{name : 'update_by',index : 'update_by',width : 60,sorttype : "varchar(64)",editable : false,hidden : true},
						{name : 'update_time',index : 'update_time',width : 60,sorttype : "datetime",editable : false,hidden : true},
						{name : 'sku_code',index : 'sku_code',fixed : true,width : 200,sorttype : 'varchar(64)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'limit_ids',index : 'limit_ids',fixed : true,width : 200,sorttype : 'varchar(255)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}}
					],onSelectRow: function(rowid, status){
	                   	 var rowData = $("#dz_sku_grid").jqGrid('getRowData',rowid);
	                	 $("#dz_skusum_grid").setGridParam({url:rootpath + '/webpage/biz/dz/sku/list2.action?bean.parent_id='+rowData.id}).trigger("reloadGrid");
	                 },
		processbtncol:2,//操作按钮放在第几个列
		isedit:false,//是否可编辑列表
		advsearch:true,
		multiSort:true,
		viewurl : "/webpage/biz/dz/sku/doModify.action",
		addurl : "/webpage/biz/dz/sku/doAdd.action",
		modifyurl : "/webpage/biz/dz/sku/doModifySave.action",
		deleteurl : "/webpage/biz/dz/sku/delete.action"
		
	});
	
	initgrid("dz_sku");
	// grid参数
	$("#dz_skusum_grid").data("gridOptions", {
		height : parent.getClientHeights()/2,
		url :  rootpath + '/webpage/biz/dz/sku/list2.action',
		colNames : [ 
					'操作',
					'id',
					'规格名称',
					'规格值',
					'父级id',
					'排序',
					'商品分类ID',
					'创建人',
					'创建时间',
					'修改人',
					'修改时间',
					'规格code',
					'规格限制id'
					],//列显示名称
		colModel : [ //	align:left,center,right,默认left;classes:列的css;datefmt:默认Y-m-d;defval:查询字段的默认值;editable:单元格是否可编辑,默认false;
					 //fixed:列宽是否固定不可变,默认false;search:在搜索模式下，定义此列是否可以作搜索列
					{name : 'processbtn',index:'', width:80, fixed:true, sortable:false, resize:false,search : false,formatter:btnformatter},
						{name : 'id',index : 'id',width : 60,sorttype : "bigint(16)",editable : false,hidden : true},
						{name : 'sku_name',index : 'sku_name',fixed : true,width : 200,sorttype : 'varchar(20)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'sku_value',index : 'sku_value',fixed : true,width : 200,sorttype : 'varchar(256)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'parent_id',index : 'parent_id',width : 60,sorttype : "varchar(64)",editable : false,hidden : true},
						{name : 'sort',index : 'sort',fixed : true,width : 200,sorttype : 'int(11)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'product_category_id',hidden:true,index : 'product_category_id',fixed : true,width : 100,sorttype : 'varchar(64)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'create_by',index : 'create_by',width : 60,sorttype : "varchar(64)",editable : false,hidden : true},
						{name : 'create_time',index : 'create_time',width : 60,sorttype : "datetime",editable : false,hidden : true},
						{name : 'update_by',index : 'update_by',width : 60,sorttype : "varchar(64)",editable : false,hidden : true},
						{name : 'update_time',index : 'update_time',width : 60,sorttype : "datetime",editable : false,hidden : true},
						{name : 'sku_code',index : 'sku_code',fixed : true,width : 200,sorttype : 'varchar(64)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'limit_ids',index : 'limit_ids',fixed : true,width : 200,sorttype : 'varchar(255)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}}
					],
		processbtncol:2,//操作按钮放在第几个列
		isedit:false,//是否可编辑列表
		advsearch:true,
		multiSort:true,
		viewurl : "/webpage/biz/dz/sku/doModify.action",
		addurl : "/webpage/biz/dz/sku/doAdd2.action",
		modifyurl : "/webpage/biz/dz/sku/doModifySave.action",
		deleteurl : "/webpage/biz/dz/sku/delete.action"
		
	});
	
	initgrid("dz_skusum");
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
			"bean.sku_name" : datalist.sku_name,
			"bean.sku_value" : datalist.sku_value,
			"bean.parent_id" : datalist.parent_id,
			"bean.sort" : datalist.sort,
			"bean.product_category_id" : datalist.product_category_id,
			"bean.create_by" : datalist.create_by,
			"bean.create_time" : datalist.create_time,
			"bean.update_by" : datalist.update_by,
			"bean.update_time" : datalist.update_time,
			"bean.sku_code" : datalist.sku_code,
			"bean.limit_ids" : datalist.limit_ids
	}; 
}
function initEditGridRowData(_bizname,data,selectedRowId){
	var datalist = getEditGridParams(_bizname,selectedRowId);
	var dataJson = eval(data);
	return dataRow = {  
				id : dataJson.id,
				sku_name : datalist.sku_name,
				sku_value : datalist.sku_value,
				parent_id : datalist.parent_id,
				sort : datalist.sort,
				product_category_id : datalist.product_category_id,
				create_by : datalist.create_by,
				create_time : datalist.create_time,
				update_by : datalist.update_by,
				update_time : datalist.update_time,
				sku_code : datalist.sku_code,
				limit_ids : datalist.limit_ids
	}; 
}
var _openDialog4Adding = function(_bizname) {
	var selectedRowId = $("#"+_bizname+"_grid").jqGrid("getGridParam", "selrow");
	var rowData = $("#"+_bizname+"_grid").jqGrid('getRowData', selectedRowId);
	var url = $("#"+_bizname+"_grid").data("gridOptions").addurl;
	if("dz_sku" == _bizname){
		parent.createwindow(_bizname,url,"新增",_formw,_formh,true);
	}else{
		var selectedRowId2 = $("#dz_sku_grid").jqGrid("getGridParam", "selrow");
		var rowData2 = $("#dz_sku_grid").jqGrid('getRowData', selectedRowId2);
		if(selectedRowId2==''||selectedRowId2==null||selectedRowId2==undefined){
			alertMsg("请先选择一条规格父类信息!");
			return;
		}
		url += "?bean.parent_id="+rowData2.id+"&bean.product_category_id="+rowData2.product_category_id
		parent.createwindow(_bizname,url,"新增",_formw,_formh,true);
	}
};