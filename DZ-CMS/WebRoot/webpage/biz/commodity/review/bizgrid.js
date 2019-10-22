$(function() {
	// grid参数
	$("#dz_commodity_review_grid").data("gridOptions", {
		height : parent.getClientHeights(),
		url :  rootpath + '/webpage/biz/commodity/review/list.action',
		colNames : [ 
					'操作',
					'id',
					'订单id',
					'商品id',
					'客户id',
					'满意系数：星星表示',
					'评论内容',
					'评论时间',
					'是否删除',
					'图片地址',
					'回复内容'
					],//列显示名称
		colModel : [ //	align:left,center,right,默认left;classes:列的css;datefmt:默认Y-m-d;defval:查询字段的默认值;editable:单元格是否可编辑,默认false;
					 //fixed:列宽是否固定不可变,默认false;search:在搜索模式下，定义此列是否可以作搜索列
					{name : 'processbtn',index:'', width:80, fixed:true, sortable:false, resize:false,search : false,formatter:btnformatter},
						{name : 'id',index : 'id',width : 60,sorttype : "int(11)",editable : false,hidden : true},
						{name : 'order_id',index : 'order_id',width : 60,sorttype : "varchar(64)",editable : false,hidden : true},
						{name : 'commodity_id',index : 'commodity_id',width : 60,sorttype : "varchar(64)",editable : false,hidden : true},
						{name : 'customer_id',index : 'customer_id',width : 60,sorttype : "varchar(64)",editable : false,hidden : true},
						{name : 'satisfaction',index : 'satisfaction',fixed : true,width : 100,sorttype : 'int(11)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'comments',index : 'comments',fixed : true,width : 100,sorttype : 'varchar(200)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'create_time',index : 'create_time',fixed : true,width : 100,sorttype : 'datetime',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'is_delete',index : 'is_delete',fixed : true,width : 100,sorttype : 'int(11)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'img_url',index : 'img_url',fixed : true,width : 100,sorttype : 'varchar(500)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'reply_content',index : 'reply_content',fixed : true,width : 100,sorttype : 'varchar(500)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}}
					],
		processbtncol:2,//操作按钮放在第几个列
		isedit:false,//是否可编辑列表
		advsearch:true,
		multiSort:true,
		viewurl : "/webpage/biz/commodity/review/doModify.action",
		addurl : "/webpage/biz/commodity/review/doAdd.action",
		modifyurl : "/webpage/biz/commodity/review/doModifySave.action",
		deleteurl : "/webpage/biz/commodity/review/delete.action"
		
	});
	
	initgrid("dz_commodity_review");
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
			"bean.commodity_id" : datalist.commodity_id,
			"bean.customer_id" : datalist.customer_id,
			"bean.satisfaction" : datalist.satisfaction,
			"bean.comments" : datalist.comments,
			"bean.create_time" : datalist.create_time,
			"bean.is_delete" : datalist.is_delete,
			"bean.img_url" : datalist.img_url,
			"bean.reply_content" : datalist.reply_content
	}; 
}
function initEditGridRowData(_bizname,data,selectedRowId){
	var datalist = getEditGridParams(_bizname,selectedRowId);
	var dataJson = eval(data);
	return dataRow = {  
				id : dataJson.id,
				order_id : datalist.order_id,
				commodity_id : datalist.commodity_id,
				customer_id : datalist.customer_id,
				satisfaction : datalist.satisfaction,
				comments : datalist.comments,
				create_time : datalist.create_time,
				is_delete : datalist.is_delete,
				img_url : datalist.img_url,
				reply_content : datalist.reply_content
	}; 
}
