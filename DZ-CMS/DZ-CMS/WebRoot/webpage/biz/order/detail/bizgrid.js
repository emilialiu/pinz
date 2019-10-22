$(function() {
	// grid参数
	$("#dz_order_detail_grid").data("gridOptions", {
		height : parent.getClientHeights(),
		url :  rootpath + '/webpage/biz/order/detail/list.action',
		colNames : [ 
					'操作',
					'id',
					'订单编号',
					'商品id',
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
					{name : 'processbtn',index:'', width:80, fixed:true, sortable:false, resize:false,search : false,formatter:btnformatter},
						{name : 'id',index : 'id',width : 60,sorttype : "bigint(16)",editable : false,hidden : true},
						{name : 'order_code',index : 'order_code',fixed : true,width : 100,sorttype : 'varchar(64)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'commodity_id',index : 'commodity_id',fixed : true,width : 100,sorttype : 'varchar(64)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
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
			"bean.commodity_id" : datalist.commodity_id,
			"bean.sku_content" : datalist.sku_content,
			"bean.style_content" : datalist.style_content,
			"bean.custome_size_content" : datalist.custome_size_content,
			"bean.commodity_title" : datalist.commodity_title,
			"bean.buy_count" : datalist.buy_count,
			"bean.product_price" : datalist.product_price,
			"bean.ear_money" : datalist.ear_money,
			"bean.commodity_img" : datalist.commodity_img,
			"bean.tail_money" : datalist.tail_money,
			"bean.linear_number" : datalist.linear_number,
			"bean.linear_material_name" : datalist.linear_material_name
	}; 
}
function initEditGridRowData(_bizname,data,selectedRowId){
	var datalist = getEditGridParams(_bizname,selectedRowId);
	var dataJson = eval(data);
	return dataRow = {  
				id : dataJson.id,
				order_code : datalist.order_code,
				commodity_id : datalist.commodity_id,
				sku_content : datalist.sku_content,
				style_content : datalist.style_content,
				custome_size_content : datalist.custome_size_content,
				commodity_title : datalist.commodity_title,
				buy_count : datalist.buy_count,
				product_price : datalist.product_price,
				ear_money : datalist.ear_money,
				commodity_img : datalist.commodity_img,
				tail_money : datalist.tail_money,
				linear_number : datalist.linear_number,
				linear_material_name : datalist.linear_material_name
	}; 
}
