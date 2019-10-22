$(function() {
	// grid参数
	$("#dz_commodity_base_grid").data("gridOptions", {
		height : parent.getClientHeights(),
		url :  rootpath + '/webpage/biz/commodity/base/list.action',
		colNames : [ 
					'操作',
					'id',
					'商品名称',
					'商品分类',
					'产品款号',
					'面料支数',
					'面料厚度',
					'面料克重',
					'面料弹力',
					'适用季节',
					'面料成分',
					'banner图',
					'产品描述图片',
					'原价',
					'定金',
					'秒杀价',
					'是否热品',
					'是否新品',
					'是否推荐',
					'销量',
					'库存'
					],//列显示名称
		colModel : [ //	align:left,center,right,默认left;classes:列的css;datefmt:默认Y-m-d;defval:查询字段的默认值;editable:单元格是否可编辑,默认false;
					 //fixed:列宽是否固定不可变,默认false;search:在搜索模式下，定义此列是否可以作搜索列
					{name : 'processbtn',index:'', width:80, fixed:true, sortable:false, resize:false,search : false,formatter:btnformatter},
						{name : 'id',index : 'id',width : 60,sorttype : "int(11)",editable : false,hidden : true},
						{name : 'title',index : 'title',fixed : true,width : 100,sorttype : 'varchar(50)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'product_category_id',index : 'product_category_id',fixed : true,width : 100,sorttype : 'varchar(64)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'code',index : 'code',fixed : true,width : 100,sorttype : 'varchar(50)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'fabric_zhishu',index : 'fabric_zhishu',fixed : true,width : 100,sorttype : 'varchar(50)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'fabric_thickness',index : 'fabric_thickness',fixed : true,width : 100,sorttype : 'varchar(50)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'fabric_weight',index : 'fabric_weight',fixed : true,width : 100,sorttype : 'varchar(50)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'fabric_elasticity',index : 'fabric_elasticity',fixed : true,width : 100,sorttype : 'varchar(50)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'work_season',index : 'work_season',fixed : true,width : 100,sorttype : 'varchar(50)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'fabric_ingredient',index : 'fabric_ingredient',fixed : true,width : 100,sorttype : 'varchar(255)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'banner_img',index : 'banner_img',fixed : true,width : 100,sorttype : 'varchar(255)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'context_img',index : 'context_img',fixed : true,width : 100,sorttype : 'varchar(255)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'original_price',index : 'original_price',fixed : true,width : 100,sorttype : 'int(11)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'earnest_money',index : 'earnest_money',fixed : true,width : 100,sorttype : 'int(11)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'seckill_price',index : 'seckill_price',fixed : true,width : 100,sorttype : 'int(11)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'is_hot_money',index : 'is_hot_money',fixed : true,width : 100,sorttype : 'int(11)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'is_new_product',index : 'is_new_product',fixed : true,width : 100,sorttype : 'int(11)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'is_recommend',index : 'is_recommend',fixed : true,width : 100,sorttype : 'int(11)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'sales',index : 'sales',fixed : true,width : 100,sorttype : 'int(11)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'stock',index : 'stock',fixed : true,width : 100,sorttype : 'int(11)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}}
					],
		processbtncol:2,//操作按钮放在第几个列
		isedit:false,//是否可编辑列表
		advsearch:true,
		multiSort:true,
		viewurl : "/webpage/biz/commodity/base/doModify.action",
		addurl : "/webpage/biz/commodity/base/doAdd.action",
		modifyurl : "/webpage/biz/commodity/base/doModifySave.action",
		deleteurl : "/webpage/biz/commodity/base/delete.action"
		
	});
	
	initgrid("dz_commodity_base");
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
			"bean.title" : datalist.title,
			"bean.product_category_id" : datalist.product_category_id,
			"bean.code" : datalist.code,
			"bean.fabric_zhishu" : datalist.fabric_zhishu,
			"bean.fabric_thickness" : datalist.fabric_thickness,
			"bean.fabric_weight" : datalist.fabric_weight,
			"bean.fabric_elasticity" : datalist.fabric_elasticity,
			"bean.work_season" : datalist.work_season,
			"bean.fabric_ingredient" : datalist.fabric_ingredient,
			"bean.banner_img" : datalist.banner_img,
			"bean.context_img" : datalist.context_img,
			"bean.original_price" : datalist.original_price,
			"bean.earnest_money" : datalist.earnest_money,
			"bean.seckill_price" : datalist.seckill_price,
			"bean.is_hot_money" : datalist.is_hot_money,
			"bean.is_new_product" : datalist.is_new_product,
			"bean.is_recommend" : datalist.is_recommend,
			"bean.sales" : datalist.sales,
			"bean.stock" : datalist.stock
	}; 
}
function initEditGridRowData(_bizname,data,selectedRowId){
	var datalist = getEditGridParams(_bizname,selectedRowId);
	var dataJson = eval(data);
	return dataRow = {  
				id : dataJson.id,
				title : datalist.title,
				product_category_id : datalist.product_category_id,
				code : datalist.code,
				fabric_zhishu : datalist.fabric_zhishu,
				fabric_thickness : datalist.fabric_thickness,
				fabric_weight : datalist.fabric_weight,
				fabric_elasticity : datalist.fabric_elasticity,
				work_season : datalist.work_season,
				fabric_ingredient : datalist.fabric_ingredient,
				banner_img : datalist.banner_img,
				context_img : datalist.context_img,
				original_price : datalist.original_price,
				earnest_money : datalist.earnest_money,
				seckill_price : datalist.seckill_price,
				is_hot_money : datalist.is_hot_money,
				is_new_product : datalist.is_new_product,
				is_recommend : datalist.is_recommend,
				sales : datalist.sales,
				stock : datalist.stock
	}; 
}
