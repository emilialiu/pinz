$(function() {
	// grid参数
	$("#dz_coupon_user_record_grid").data("gridOptions", {
		height : parent.getClientHeights(),
		url :  rootpath + '/webpage/biz/coupon/user/list.action',
		colNames : [ 
					'操作',
					'id',
					'订单id',
					'客户id',
					'使用时间',
					'状态',
					'过期时间',
					'优惠券金额'
					],//列显示名称
		colModel : [ //	align:left,center,right,默认left;classes:列的css;datefmt:默认Y-m-d;defval:查询字段的默认值;editable:单元格是否可编辑,默认false;
					 //fixed:列宽是否固定不可变,默认false;search:在搜索模式下，定义此列是否可以作搜索列
					{name : 'processbtn',index:'', width:80, fixed:true, sortable:false, resize:false,search : false,formatter:btnformatter},
						{name : 'id',index : 'id',width : 60,sorttype : "bigint(16)",editable : false,hidden : true},
						{name : 'order_id',index : 'order_id',width : 60,sorttype : "varchar(64)",editable : false,hidden : true},
						{name : 'customer_id',index : 'customer_id',width : 60,sorttype : "varchar(64)",editable : false,hidden : true},
						{name : 'create_time',index : 'create_time',fixed : true,width : 100,sorttype : 'datetime',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'state',index : 'state',fixed : true,width : 100,sorttype : 'int(11)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'expire_time',index : 'expire_time',fixed : true,width : 100,sorttype : 'datetime',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'coupon_amount',index : 'coupon_amount',fixed : true,width : 100,sorttype : 'int(11)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}}
					],
		processbtncol:2,//操作按钮放在第几个列
		isedit:false,//是否可编辑列表
		advsearch:true,
		multiSort:true,
		viewurl : "/webpage/biz/coupon/user/doModify.action",
		addurl : "/webpage/biz/coupon/user/doAdd.action",
		modifyurl : "/webpage/biz/coupon/user/doModifySave.action",
		deleteurl : "/webpage/biz/coupon/user/delete.action"
		
	});
	
	initgrid("dz_coupon_user_record");
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
			"bean.order_id" : datalist.order_id,
			"bean.customer_id" : datalist.customer_id,
			"bean.create_time" : datalist.create_time,
			"bean.state" : datalist.state,
			"bean.expire_time" : datalist.expire_time,
			"bean.coupon_amount" : datalist.coupon_amount
	}; 
}
function initEditGridRowData(_bizname,data,selectedRowId){
	var datalist = getEditGridParams(_bizname,selectedRowId);
	var dataJson = eval(data);
	return dataRow = {  
				id : dataJson.id,
				order_id : datalist.order_id,
				customer_id : datalist.customer_id,
				create_time : datalist.create_time,
				state : datalist.state,
				expire_time : datalist.expire_time,
				coupon_amount : datalist.coupon_amount
	}; 
}
