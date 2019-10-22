$(function() {
	// grid参数
	$("#t_bi_mrbaseinfofee_grid").data("gridOptions", {
		height : parent.getClientHeights(),
		url :  rootpath + '/webpage/biz/bi/mrbaseinfofee/list.action',
		colNames : [ 
					'操作',
					'矿业权资金信息ID',
					'矿业权ID',
					'费用名称',
					'费用类型',
					'费用发生阶段',
					'费用（￥）',
					'费用产生日期',
					'备注',
					'创建人',
					'创建时间',
					'修改人',
					'修改时间'
					],//列显示名称
		colModel : [ //	align:left,center,right,默认left;classes:列的css;datefmt:默认Y-m-d;defval:查询字段的默认值;editable:单元格是否可编辑,默认false;
					 //fixed:列宽是否固定不可变,默认false;search:在搜索模式下，定义此列是否可以作搜索列
					{name : 'processbtn',index:'', width:80, fixed:true, sortable:false, resize:false,search : false,formatter:btnformatter},
						{name : 'feeid',index : 'feeid',width : 60,sorttype : "varchar(32)",editable : false,hidden : true},
						{name : 'rightid',index : 'rightid',width : 60,sorttype : "varchar(32)",editable : false,hidden : true},
						{name : 'feename',index : 'feename',fixed : true,width : 100,sorttype : 'varchar(40)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'feetype',index : 'feetype',fixed : true,width : 100,sorttype : 'varchar(10)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'feestage',index : 'feestage',fixed : true,width : 100,sorttype : 'varchar(10)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'feeamount',index : 'feeamount',fixed : true,width : 100,sorttype : 'decimal(16,4)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'feedate',index : 'feedate',fixed : true,width : 100,sorttype : 'datetime',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'memo',index : 'memo',fixed : true,width : 100,sorttype : 'varchar(1000)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'createid',index : 'createid',width : 60,sorttype : "varchar(32)",editable : false,hidden : true},
						{name : 'createdate',index : 'createdate',width : 60,sorttype : "datetime",editable : false,hidden : true},
						{name : 'modifyid',index : 'modifyid',width : 60,sorttype : "varchar(32)",editable : false,hidden : true},
						{name : 'modifydate',index : 'modifydate',width : 60,sorttype : "datetime",editable : false,hidden : true}
					],
		processbtncol:2,//操作按钮放在第几个列
		isedit:false,//是否可编辑列表
		advsearch:true,
		multiSort:true,
		viewurl : "/webpage/biz/bi/mrbaseinfofee/doModify.action",
		addurl : "/webpage/biz/bi/mrbaseinfofee/doAdd.action",
		modifyurl : "/webpage/biz/bi/mrbaseinfofee/doModifySave.action",
		deleteurl : "/webpage/biz/bi/mrbaseinfofee/delete.action"
		
	});
	
	initgrid("t_bi_mrbaseinfofee");
});

//删除修改进入表单时传递参数
function getGridParams(_bizname){
	var selectedRowId = $("#"+_bizname+"_grid").jqGrid("getGridParam", "selrow");  
    var str = $("#"+_bizname+"_grid").jqGrid("getRowData", selectedRowId); // 根据行ID，获取选中行的数据
		return {"bean.feeid" : str.feeid};
}

function getParams(_bizname){
	var selectedRowId = $("#"+_bizname+"_grid").jqGrid("getGridParam", "selrow");  
    var str = $("#"+_bizname+"_grid").jqGrid("getRowData", selectedRowId); // 根据行ID，获取选中行的数据
		return "?bean.feeid="+str.feeid;
}

//取编辑grid列表数据
function getEditGridParams(_bizname,selectedRowId){
	var datalist = $("#"+_bizname+"_grid").jqGrid('getRowData', selectedRowId);
	return {  
			"bean.feeid" : datalist.feeid,
			"bean.rightid" : datalist.rightid,
			"bean.feename" : datalist.feename,
			"bean.feetype" : datalist.feetype,
			"bean.feestage" : datalist.feestage,
			"bean.feeamount" : datalist.feeamount,
			"bean.feedate" : datalist.feedate,
			"bean.memo" : datalist.memo,
			"bean.createid" : datalist.createid,
			"bean.createdate" : datalist.createdate,
			"bean.modifyid" : datalist.modifyid,
			"bean.modifydate" : datalist.modifydate
	}; 
}
function initEditGridRowData(_bizname,data,selectedRowId){
	var datalist = getEditGridParams(_bizname,selectedRowId);
	var dataJson = eval(data);
	return dataRow = {  
				feeid : dataJson.id,
				rightid : datalist.rightid,
				feename : datalist.feename,
				feetype : datalist.feetype,
				feestage : datalist.feestage,
				feeamount : datalist.feeamount,
				feedate : datalist.feedate,
				memo : datalist.memo,
				createid : datalist.createid,
				createdate : datalist.createdate,
				modifyid : datalist.modifyid,
				modifydate : datalist.modifydate
	}; 
}
