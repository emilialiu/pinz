$(function() {
	// grid参数
	$("#dz_customer_adress_grid").data("gridOptions", {
		height : parent.getClientHeights(),
		url :  rootpath + '/webpage/biz/customer/adress/list.action',
		colNames : [ 
					'操作',
					'id',
					'客户姓名',
					'省级id',
					'省级名称',
					'市级id',
					'市级名称',
					'区级id',
					'区级名称',
					'详细地址',
					'收货人姓名',
					'电话号码',
					'是否设置为默认地址'
					],//列显示名称
		colModel : [ //	align:left,center,right,默认left;classes:列的css;datefmt:默认Y-m-d;defval:查询字段的默认值;editable:单元格是否可编辑,默认false;
					 //fixed:列宽是否固定不可变,默认false;search:在搜索模式下，定义此列是否可以作搜索列
					{name : 'processbtn',index:'', width:80, fixed:true, sortable:false, resize:false,search : false,formatter:btnformatter},
						{name : 'id',index : 'id',width : 60,sorttype : "int(11)",editable : false,hidden : true},
						{name : 'name',index : 'name',fixed : true,width : 100,sorttype : 'varchar(64)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'pro_id',index : 'pro_id',width : 60,sorttype : "varchar(64)",editable : false,hidden : true},
						{name : 'pro_name',index : 'pro_name',width : 60,sorttype : "varchar(64)",editable : false,hidden : true},
						{name : 'city_id',index : 'city_id',width : 60,sorttype : "varchar(64)",editable : false,hidden : true},
						{name : 'city_name',index : 'city_name',fixed : true,width : 100,sorttype : 'varchar(64)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'district_id',index : 'district_id',width : 60,sorttype : "varchar(64)",editable : false,hidden : true},
						{name : 'district_name',index : 'district_name',fixed : true,width : 100,sorttype : 'varchar(64)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'complet_address',index : 'complet_address',fixed : true,width : 100,sorttype : 'varchar(200)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'consignee_name',index : 'consignee_name',fixed : true,width : 100,sorttype : 'varchar(64)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'phone',index : 'phone',fixed : true,width : 100,sorttype : 'varchar(64)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'is_default',index : 'is_default',fixed : true,width : 100,sorttype : 'int(11)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}}
					],
		processbtncol:2,//操作按钮放在第几个列
		isedit:false,//是否可编辑列表
		advsearch:true,
		multiSort:true,
		viewurl : "/webpage/biz/customer/adress/doModify.action",
		addurl : "/webpage/biz/customer/adress/doAdd.action",
		modifyurl : "/webpage/biz/customer/adress/doModifySave.action",
		deleteurl : "/webpage/biz/customer/adress/delete.action"
		
	});
	
	initgrid("dz_customer_adress");
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
			"bean.customer_id" : datalist.customer_id,
			"bean.pro_id" : datalist.pro_id,
			"bean.pro_name" : datalist.pro_name,
			"bean.city_id" : datalist.city_id,
			"bean.city_name" : datalist.city_name,
			"bean.district_id" : datalist.district_id,
			"bean.district_name" : datalist.district_name,
			"bean.complet_address" : datalist.complet_address,
			"bean.consignee_name" : datalist.consignee_name,
			"bean.phone" : datalist.phone,
			"bean.is_default" : datalist.is_default
	}; 
}
function initEditGridRowData(_bizname,data,selectedRowId){
	var datalist = getEditGridParams(_bizname,selectedRowId);
	var dataJson = eval(data);
	return dataRow = {  
				id : dataJson.id,
				customer_id : datalist.customer_id,
				pro_id : datalist.pro_id,
				pro_name : datalist.pro_name,
				city_id : datalist.city_id,
				city_name : datalist.city_name,
				district_id : datalist.district_id,
				district_name : datalist.district_name,
				complet_address : datalist.complet_address,
				consignee_name : datalist.consignee_name,
				phone : datalist.phone,
				is_default : datalist.is_default
	}; 
}
