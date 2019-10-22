$(function() {
	// grid参数
	$("#dz_coupon_config_grid").data("gridOptions", {
		height : parent.getClientHeights(),
		url :  rootpath + '/webpage/biz/coupon/config/list.action',
		colNames : [ 
					'操作',
					'id',
					'分类id',
					"分类",
					'过期时间',
					'过期时间,天数',
					'说明',
					'优惠券金额',
					'商品名称',
					'会员等级',
					'商品分类',
					'创建时间',
					'创建人',
					'修改时间',
					'修改人',
					'状态'
					],//列显示名称
		colModel : [ //	align:left,center,right,默认left;classes:列的css;datefmt:默认Y-m-d;defval:查询字段的默认值;editable:单元格是否可编辑,默认false;
					 //fixed:列宽是否固定不可变,默认false;search:在搜索模式下，定义此列是否可以作搜索列
					{name : 'processbtn',index:'', width:80, fixed:true, sortable:false, resize:false,search : false,formatter:btnformatter},
						{name : 'id',index : 'id',width : 60,sorttype : "int(11)",editable : false,hidden : true},
						{name : 'type_id',index : 'type_id',width : 60,sorttype : "varchar(64)",editable : false,hidden : true},
						{name : 'typename',index : 'typename',fixed : true,width : 100,sorttype : 'varchar(64)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'expired_time',index : 'expired_time',fixed : true,width : 100,sorttype : 'datetime',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'expired_num',index : 'expired_num',fixed : true,width : 100,sorttype : 'int(11)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'remarks',index : 'remarks',fixed : true,width : 100,sorttype : 'varchar(50)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'voucher_amount',index : 'voucher_amount',fixed : true,width : 100,sorttype : 'int(11)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'commodityid',index : 'commodityid',fixed : true,width : 100,sorttype : 'varchar(64)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'memberlevel',index : 'memberlevel',fixed : true,width : 100,sorttype : 'varchar(64)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'commoditytypeid',index : 'commoditytypeid',fixed : true,width : 100,sorttype : 'varchar(64)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'create_time',index : 'create_time',width : 60,sorttype : "datetime",editable : false,hidden : true},
						{name : 'create_by',index : 'create_by',width : 60,sorttype : "varchar(64)",editable : false,hidden : true},
						{name : 'update_time',index : 'update_time',width : 60,sorttype : "datetime",editable : false,hidden : true},
						{name : 'update_by',index : 'update_by',width : 60,sorttype : "varchar(64)",editable : false,hidden : true},
						{name : 'statename',index : 'statename',fixed : true,width : 100,sorttype : 'int(11)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}}
					],
		processbtncol:2,//操作按钮放在第几个列
		isedit:false,//是否可编辑列表
		advsearch:true,
		multiSort:true,
		viewurl : "/webpage/biz/coupon/config/doModify.action",
		addurl : "/webpage/biz/coupon/config/doAdd.action",
		modifyurl : "/webpage/biz/coupon/config/doModifySave.action",
		deleteurl : "/webpage/biz/coupon/config/delete.action"
		
	});
	
	initgrid("dz_coupon_config");
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
			"bean.type_id" : datalist.type_id,
			"bean.expired_time" : datalist.expired_time,
			"bean.expired_num" : datalist.expired_num,
			"bean.remarks" : datalist.remarks,
			"bean.voucher_amount" : datalist.voucher_amount,
			"bean.commodity_id" : datalist.commodity_id,
			"bean.member_level" : datalist.member_level,
			"bean.commodity_type_id" : datalist.commodity_type_id,
			"bean.create_time" : datalist.create_time,
			"bean.create_by" : datalist.create_by,
			"bean.update_time" : datalist.update_time,
			"bean.update_by" : datalist.update_by,
			"bean.state" : datalist.state
	}; 
}
function initEditGridRowData(_bizname,data,selectedRowId){
	var datalist = getEditGridParams(_bizname,selectedRowId);
	var dataJson = eval(data);
	return dataRow = {  
				id : dataJson.id,
				type_id : datalist.type_id,
				expired_time : datalist.expired_time,
				expired_num : datalist.expired_num,
				remarks : datalist.remarks,
				voucher_amount : datalist.voucher_amount,
				commodity_id : datalist.commodity_id,
				member_level : datalist.member_level,
				commodity_type_id : datalist.commodity_type_id,
				create_time : datalist.create_time,
				create_by : datalist.create_by,
				update_time : datalist.update_time,
				update_by : datalist.update_by,
				state : datalist.state
	}; 
}
