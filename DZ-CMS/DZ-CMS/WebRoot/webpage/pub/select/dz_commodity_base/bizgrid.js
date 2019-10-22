$(function() {
	$("#dz_commodity_base_grid").data("gridOptions", {
		height : 400,//parent.getClientHeights(),
		url :  rootpath + '/webpage/biz/commodity/base/list.action',
		colNames : [ 
				'操作',
				'id',
				'商品名称',
				'商品分类',
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
						{name : 'processbtn',index:'', width:80, fixed:true, hidden:true,sortable:false, resize:false,search : false,formatter:btnformatter},
						{name : 'id',index : 'id',width : 60,sorttype : "int(11)",editable : false,hidden : true},
						{name : 'title',index : 'title',fixed : true,width : 100,sorttype : 'varchar(50)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'product_category_id',hidden:true,index : 'product_category_id',fixed : true,width : 100,sorttype : 'varchar(64)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'productcategoryid',index : 'productcategoryid',fixed : true,width : 100,sorttype : 'varchar(64)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'code',index : 'code',fixed : true,width : 100,sorttype : 'varchar(50)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'fabric_zhishu',index : 'fabric_zhishu',fixed : true,width : 100,sorttype : 'varchar(50)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'fabric_thickness',index : 'fabric_thickness',fixed : true,width : 100,sorttype : 'varchar(50)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'fabric_weight',index : 'fabric_weight',fixed : true,width : 100,sorttype : 'varchar(50)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'fabric_elasticity',index : 'fabric_elasticity',fixed : true,width : 100,sorttype : 'varchar(50)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'work_season',index : 'work_season',fixed : true,width : 100,sorttype : 'varchar(50)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'fabric_ingredient',index : 'fabric_ingredient',fixed : true,width : 100,sorttype : 'varchar(255)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'banner_img',hidden:true,index : 'banner_img',fixed : true,width : 100,sorttype : 'varchar(255)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'context_img',hidden:true,index : 'context_img',fixed : true,width : 100,sorttype : 'varchar(255)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'original_price',index : 'original_price',fixed : true,width : 100,sorttype : 'int(11)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'earnest_money',index : 'earnest_money',fixed : true,width : 100,sorttype : 'int(11)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'seckill_price',index : 'seckill_price',fixed : true,width : 100,sorttype : 'int(11)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'ishotmoney',index : 'ishotmoney',fixed : true,width : 100,sorttype : 'int(11)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'isnewproduct',index : 'isnewproduct',fixed : true,width : 100,sorttype : 'int(11)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'isrecommend',index : 'isrecommend',fixed : true,width : 100,sorttype : 'int(11)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'sales',index : 'sales',fixed : true,width : 100,sorttype : 'int(11)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'stock',index : 'stock',fixed : true,width : 100,sorttype : 'int(11)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}}
					],
		processbtncol:2,//操作按钮放在第几个列
		isedit:false,//是否可编辑列表
		advsearch:true,
		multiSort:true,
		multiselect:multiselect=="null"?false:multiselect
	});
		initgrid("dz_commodity_base");
		if(multiselect){
			$("button:contains('清空选择')", window.parent.document).hide();
		}else{
			$("button:contains('清空选择')", window.parent.document).show();
		}
});

function onSave(){
	var data = [];
	var rowdata;
	if(multiselect){//多选
		var selectedRowIds = $("#dz_commodity_base_grid").jqGrid("getGridParam", "selarrrow");
		if (!selectedRowIds) {
			alertMsg("请至少选择一个节点!");
			return false;
		}
		for(var i=0;i<selectedRowIds.length;i++){
			rowdata = $("#dz_commodity_base_grid").jqGrid("getRowData", selectedRowIds[i]);
			data.push(rowdata);
		}
	}else{//单选
		var selectedRowId = $("#dz_commodity_base_grid").jqGrid("getGridParam", "selrow");
	    if (!selectedRowId) {
	    	alertMsg("请选择一个节点!");
	        return false;
	    }
	    rowdata = $("#dz_commodity_base_grid").jqGrid("getRowData", selectedRowId);
		data.push(rowdata);
	}
	try{
		parent.PageObject.itemMap['dz_commodity_base_select'].callback(data);
	}catch(e){
		parent.closewin('dz_commodity_base_select');
	}
	parent.closewin('dz_commodity_base_select');
}
function uncheck() {
	parent.PageObject.itemMap['dz_commodity_base_select'].callback();
	parent.closewin('dz_commodity_base_select');
}

