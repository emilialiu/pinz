$(function() {
	// grid参数
	$("#dz_customer_size_config_grid").data("gridOptions", {
		height : parent.getClientHeights(),
		url :  rootpath + '/webpage/biz/customer/size/list.action',
		colNames : [ 
					'操作',
					'id',
					'登录客户的id',
					'姓名',
					'性别',
					'年龄',
					'身高（cm）',
					'体重（公斤）',
					'腰围（cm）',
					'胸围（cm）',
					'衣长（cm）',
					'臂长（cm）',
					'臂围（cm）',
					'肩宽（cm）',
					'尺寸类型配置内容',
					'图示-正面照',
					'图示-侧面照',
					'图示-背面照',
					'上传的图片',
					'是否设置为默认尺寸',
					'备注（特殊说明栏）'
					],//列显示名称
		colModel : [ //	align:left,center,right,默认left;classes:列的css;datefmt:默认Y-m-d;defval:查询字段的默认值;editable:单元格是否可编辑,默认false;
					 //fixed:列宽是否固定不可变,默认false;search:在搜索模式下，定义此列是否可以作搜索列
					{name : 'processbtn',index:'', width:80, fixed:true, sortable:false, resize:false,search : false,formatter:btnformatter},
						{name : 'id',index : 'id',width : 60,sorttype : "bigint(16)",editable : false,hidden : true},
						{name : 'customer_id',index : 'customer_id',width : 60,sorttype : "varchar(64)",editable : false,hidden : true},
						{name : 'name',index : 'name',fixed : true,width : 100,sorttype : 'varchar(50)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'sex',index : 'sex',fixed : true,width : 100,sorttype : 'int(11)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'year',index : 'year',fixed : true,width : 100,sorttype : 'int(11)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'height',index : 'height',fixed : true,width : 100,sorttype : 'int(11)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'weight',index : 'weight',fixed : true,width : 100,sorttype : 'int(11)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'waist',index : 'waist',fixed : true,width : 100,sorttype : 'int(11)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'bust',index : 'bust',fixed : true,width : 100,sorttype : 'int(11)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'clothes_length',index : 'clothes_length',fixed : true,width : 100,sorttype : 'int(11)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'arm_length',index : 'arm_length',fixed : true,width : 100,sorttype : 'int(11)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'arm_circum',index : 'arm_circum',fixed : true,width : 100,sorttype : 'int(11)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'shoulder_width',index : 'shoulder_width',fixed : true,width : 100,sorttype : 'int(11)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'content',index : 'content',fixed : true,width : 100,sorttype : 'varchar(200)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'headlight_url',index : 'headlight_url',fixed : true,width : 100,sorttype : 'varchar(200)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'side_shot_url',index : 'side_shot_url',fixed : true,width : 100,sorttype : 'varchar(200)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'back_photo_url',index : 'back_photo_url',fixed : true,width : 100,sorttype : 'varchar(200)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'imgs_url',index : 'imgs_url',fixed : true,width : 100,sorttype : 'varchar(1024)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'is_default',index : 'is_default',fixed : true,width : 100,sorttype : 'int(11)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'remarks',index : 'remarks',fixed : true,width : 100,sorttype : 'varchar(500)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}}
					],
		processbtncol:2,//操作按钮放在第几个列
		isedit:false,//是否可编辑列表
		advsearch:true,
		multiSort:true,
		viewurl : "/webpage/biz/customer/size/doModify.action",
		addurl : "/webpage/biz/customer/size/doAdd.action",
		modifyurl : "/webpage/biz/customer/size/doModifySave.action",
		deleteurl : "/webpage/biz/customer/size/delete.action"
		
	});
	
	initgrid("dz_customer_size_config");
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
			"bean.name" : datalist.name,
			"bean.sex" : datalist.sex,
			"bean.year" : datalist.year,
			"bean.height" : datalist.height,
			"bean.weight" : datalist.weight,
			"bean.waist" : datalist.waist,
			"bean.bust" : datalist.bust,
			"bean.clothes_length" : datalist.clothes_length,
			"bean.arm_length" : datalist.arm_length,
			"bean.arm_circum" : datalist.arm_circum,
			"bean.shoulder_width" : datalist.shoulder_width,
			"bean.content" : datalist.content,
			"bean.headlight_url" : datalist.headlight_url,
			"bean.side_shot_url" : datalist.side_shot_url,
			"bean.back_photo_url" : datalist.back_photo_url,
			"bean.imgs_url" : datalist.imgs_url,
			"bean.is_default" : datalist.is_default,
			"bean.remarks" : datalist.remarks
	}; 
}
function initEditGridRowData(_bizname,data,selectedRowId){
	var datalist = getEditGridParams(_bizname,selectedRowId);
	var dataJson = eval(data);
	return dataRow = {  
				id : dataJson.id,
				customer_id : datalist.customer_id,
				name : datalist.name,
				sex : datalist.sex,
				year : datalist.year,
				height : datalist.height,
				weight : datalist.weight,
				waist : datalist.waist,
				bust : datalist.bust,
				clothes_length : datalist.clothes_length,
				arm_length : datalist.arm_length,
				arm_circum : datalist.arm_circum,
				shoulder_width : datalist.shoulder_width,
				content : datalist.content,
				headlight_url : datalist.headlight_url,
				side_shot_url : datalist.side_shot_url,
				back_photo_url : datalist.back_photo_url,
				imgs_url : datalist.imgs_url,
				is_default : datalist.is_default,
				remarks : datalist.remarks
	}; 
}
