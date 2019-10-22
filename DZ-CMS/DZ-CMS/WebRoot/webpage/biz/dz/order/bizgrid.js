$(function() {
	// grid参数
	$("#dz_order_grid").data("gridOptions", {
		height : parent.getClientHeights()/2,
		url :  rootpath + '/webpage/biz/dz/order/list.action',
		colNames : [ 
					'操作',
					'id',
					'订单编号',
					'客户昵称',
					'总价金额',
					'已付定金金额',
					'付定金时间',
					'剩余尾款金额',
					'运费',
					'订单状态',
					'是否取消订单',
					'创建订单时间',
					'创建客户id',
					'管理员修改订单状态时间',
					'修改人',
					'付尾款时间',
					'已付尾款金额',
					'物流单号'
					],//列显示名称
		colModel : [ //	align:left,center,right,默认left;classes:列的css;datefmt:默认Y-m-d;defval:查询字段的默认值;editable:单元格是否可编辑,默认false;
					 //fixed:列宽是否固定不可变,默认false;search:在搜索模式下，定义此列是否可以作搜索列
					{name : 'processbtn',index:'', width:80, fixed:true, sortable:false, resize:false,search : false,formatter:btnformatter},
						{name : 'id',index : 'id',width : 60,sorttype : "bigint(16)",editable : false,hidden : true},
						{name : 'order_code',index : 'order_code',fixed : true,width : 100,sorttype : 'varchar(64)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'nickname',index : 'nickname',fixed : true,width : 100,sorttype : 'varchar(64)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'total_money',index : 'total_money',fixed : true,width : 100,sorttype : 'int(11)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'deposit_paid',index : 'deposit_paid',fixed : true,width : 100,sorttype : 'int(11)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'deposit_paid_time',index : 'deposit_paid_time',fixed : true,width : 100,sorttype : 'datetime',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'final_payment',index : 'final_payment',fixed : true,width : 100,sorttype : 'int(11)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'freight',index : 'freight',fixed : true,width : 100,sorttype : 'int(11)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'state',index : 'state',fixed : true,width : 100,sorttype : 'int(11)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'iscancelname',index : 'iscancelname',fixed : true,width : 100,sorttype : 'int(11)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'create_time',index : 'create_time',fixed : true,width : 100,sorttype : 'datetime',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'create_by',index : 'create_by',width : 60,sorttype : "varchar(64)",editable : false,hidden : true},
						{name : 'update_time',index : 'update_time',fixed : true,width : 100,sorttype : 'datetime',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'update_by',index : 'update_by',fixed : true,width : 100,sorttype : 'varchar(64)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'payment_time',index : 'payment_time',fixed : true,width : 100,sorttype : 'datetime',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'payment_money',index : 'payment_money',fixed : true,width : 100,sorttype : 'int(11)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'logistics_number',index : 'logistics_number',fixed : true,width : 100,sorttype : 'varchar(64)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}}
					],onSelectRow: function(rowid, status){
                   	 var rowData = $("#dz_order_grid").jqGrid('getRowData',rowid);
                	 $("#dz_order_detail_grid").setGridParam({url:rootpath + '/webpage/biz/order/detail/list.action?bean.order_code='+rowData.id}).trigger("reloadGrid");
                 },
		processbtncol:2,//操作按钮放在第几个列
		isedit:false,//是否可编辑列表
		advsearch:true,
		multiSort:true,
		viewurl : "/webpage/biz/dz/order/doModify.action",
		addurl : "/webpage/biz/dz/order/doAdd.action",
		modifyurl : "/webpage/biz/dz/order/doModifySave.action",
		deleteurl : "/webpage/biz/dz/order/delete.action"
		
	});
	
	initgrid("dz_order");
	// grid参数
	$("#dz_order_detail_grid").data("gridOptions", {
		height : parent.getClientHeights()/2,
		url :  rootpath + '/webpage/biz/order/detail/list.action',
		colNames : [ 
					'操作',
					'id',
					'订单编号',
					'商品名称',
					'商品规格尺寸',
					'客户定制明细',
					'客户身体尺寸',
					'商品标题',
					'购买数量',
					'商品价格',
					'定金',
					'商品图片',
					'尾款',
					'线性条数',
					'线性材质'
					],//列显示名称
		colModel : [ //	align:left,center,right,默认left;classes:列的css;datefmt:默认Y-m-d;defval:查询字段的默认值;editable:单元格是否可编辑,默认false;
					 //fixed:列宽是否固定不可变,默认false;search:在搜索模式下，定义此列是否可以作搜索列
					{name : 'processbtn',index:'', width:100, fixed:true, sortable:false, resize:false,search : false,formatter:practice_btnformatter},
						{name : 'id',index : 'id',width : 60,sorttype : "bigint(16)",editable : false,hidden : true},
						{name : 'order_code',index : 'order_code',fixed : true,width : 100,sorttype : 'varchar(64)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'title',index : 'title',fixed : true,width : 100,sorttype : 'varchar(64)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'sku_content',index : 'sku_content',fixed : true,width : 100,sorttype : 'varchar(128)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'style_content',index : 'style_content',fixed : true,width : 100,sorttype : 'varchar(1024)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'custome_size_content',index : 'custome_size_content',fixed : true,width : 100,sorttype : 'varchar(1024)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'commodity_title',index : 'commodity_title',fixed : true,width : 100,sorttype : 'varchar(128)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'buy_count',index : 'buy_count',fixed : true,width : 100,sorttype : 'int(11)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'product_price',index : 'product_price',fixed : true,width : 100,sorttype : 'int(11)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'ear_money',index : 'ear_money',fixed : true,width : 100,sorttype : 'int(11)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'commodity_img',index : 'commodity_img',fixed : true,width : 100,sorttype : 'varchar(128)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'tail_money',index : 'tail_money',fixed : true,width : 100,sorttype : 'int(11)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'linear_number',index : 'linear_number',fixed : true,width : 100,sorttype : 'int(11)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'linear_material_name',index : 'linear_material_name',fixed : true,width : 100,sorttype : 'varchar(50)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}}
					],
		processbtncol:2,//操作按钮放在第几个列
		isedit:false,//是否可编辑列表
		advsearch:true,
		multiSort:true,
		viewurl : "/webpage/biz/order/detail/doModify.action",
		addurl : "/webpage/biz/order/detail/doAdd.action",
		modifyurl : "/webpage/biz/order/detail/doModifySave.action",
		deleteurl : "/webpage/biz/order/detail/delete.action"
		
	});
	
	initgrid("dz_order_detail");
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
			"bean.order_code" : datalist.order_code,
			"bean.customer_id" : datalist.customer_id,
			"bean.total_money" : datalist.total_money,
			"bean.deposit_paid" : datalist.deposit_paid,
			"bean.deposit_paid_time" : datalist.deposit_paid_time,
			"bean.final_payment" : datalist.final_payment,
			"bean.freight" : datalist.freight,
			"bean.state" : datalist.state,
			"bean.is_cancel" : datalist.is_cancel,
			"bean.create_time" : datalist.create_time,
			"bean.create_by" : datalist.create_by,
			"bean.update_time" : datalist.update_time,
			"bean.update_by" : datalist.update_by,
			"bean.payment_time" : datalist.payment_time,
			"bean.payment_money" : datalist.payment_money,
			"bean.logistics_number" : datalist.logistics_number
	}; 
}
function initEditGridRowData(_bizname,data,selectedRowId){
	var datalist = getEditGridParams(_bizname,selectedRowId);
	var dataJson = eval(data);
	return dataRow = {  
				id : dataJson.id,
				order_code : datalist.order_code,
				customer_id : datalist.customer_id,
				total_money : datalist.total_money,
				deposit_paid : datalist.deposit_paid,
				deposit_paid_time : datalist.deposit_paid_time,
				final_payment : datalist.final_payment,
				freight : datalist.freight,
				state : datalist.state,
				is_cancel : datalist.is_cancel,
				create_time : datalist.create_time,
				create_by : datalist.create_by,
				update_time : datalist.update_time,
				update_by : datalist.update_by,
				payment_time : datalist.payment_time,
				payment_money : datalist.payment_money,
				logistics_number : datalist.logistics_number
	}; 
}

var _openDialog4Adding = function(_bizname) {
	var selectedRowId = $("#"+_bizname+"_grid").jqGrid("getGridParam", "selrow");
	var rowData = $("#"+_bizname+"_grid").jqGrid('getRowData', selectedRowId);
	var url = $("#"+_bizname+"_grid").data("gridOptions").addurl;
	if("dz_order" == _bizname){
		parent.createwindow(_bizname,url,"新增",_formw,_formh,true);
	}else{
		var selectedRowId2 = $("#dz_order_grid").jqGrid("getGridParam", "selrow");
		var rowData2 = $("#dz_order_grid").jqGrid('getRowData', selectedRowId2);
		if(selectedRowId2==''||selectedRowId2==null||selectedRowId2==undefined){
			alertMsg("请先选择一条订单信息!");
			return;
		}
		url += "?bean.order_code="+rowData2.id
		parent.createwindow(_bizname,url,"新增",_formw,_formh,true);
	}
};
function practice_btnformatter(cellvalue,options,rowobj){
	update = "<div class='ui-pg-div' style='float: left; margin-left: 5px;' title='修改' onclick=_doopenmodify('dz_order_detail','"+options.rowId+"')><span class='ui-icon ui-icon-pencil'/></div>";
	delet = "<div class='ui-pg-div' style='float: left; margin-left: 5px;' title='删除' onclick=_doopendelete('dz_order_detail','"+options.rowId+"')><span class='ui-icon ui-icon-trash'/></div>";
	detail = "<div class='ui-pg-div' style='float: left; margin-left: 5px;' title='查看商品图片' onclick=openfile('dz_order_detail','"+options.rowId+"')><span class='ui-icon fa-eye'/></div>";
	return update+delet+detail;
}
function openfile(_bizname,rowid){
	var selectedRowId = $("#"+_bizname+"_grid").jqGrid("getGridParam", "selrow"); 
	var rowData =$("#"+_bizname+"_grid").jqGrid('getRowData', selectedRowId);
	window.open(rootpath + "/webpage/biz/showimg.jsp?path="+rowData.commodity_img);
}