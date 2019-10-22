$(function() {
	// grid参数
	$("#t_bi_expright_grid").data("gridOptions", {
		height : parent.getClientHeights(),
		url :  rootpath + '/webpage/biz/bi/expright/list.action',
		colNames : [ 
					'操作',
					'矿业权ID',
					'合作单位',
					'勘查阶段',
					'勘查矿种',
					'探矿权获取方式',
					'获取投资总金额',
					'第一勘查年投资金额',
					'第二勘查年投资金额',
					'第三勘查年投资金额',
					'国家投资金额',
					'地方投资金额',
					'企业投资金额',
					'外商投资金额',
					'个人投资金额',
					'其他投资金额',
					'勘查工作程度或成果',
					'备注'
					],//列显示名称
		colModel : [ //	align:left,center,right,默认left;classes:列的css;datefmt:默认Y-m-d;defval:查询字段的默认值;editable:单元格是否可编辑,默认false;
					 //fixed:列宽是否固定不可变,默认false;search:在搜索模式下，定义此列是否可以作搜索列
					{name : 'processbtn',index:'', width:80, fixed:true, sortable:false, resize:false,search : false,formatter:btnformatter},
						{name : 'rightid',index : 'rightid',fixed : true,width : 100,sorttype : 'varchar(32)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'cooperateent',index : 'cooperateent',fixed : true,width : 100,sorttype : 'varchar(256)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'mineralstage',index : 'mineralstage',fixed : true,width : 100,sorttype : 'varchar(10)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'mineraltype',index : 'mineraltype',fixed : true,width : 100,sorttype : 'varchar(10)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'rightgetway',index : 'rightgetway',fixed : true,width : 100,sorttype : 'varchar(10)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'totalamount',index : 'totalamount',fixed : true,width : 100,sorttype : 'decimal(16,4)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'year1amount',index : 'year1amount',fixed : true,width : 100,sorttype : 'decimal(16,4)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'year2amount',index : 'year2amount',fixed : true,width : 100,sorttype : 'decimal(16,4)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'year3amount',index : 'year3amount',fixed : true,width : 100,sorttype : 'decimal(16,4)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'stateamount',index : 'stateamount',fixed : true,width : 100,sorttype : 'decimal(16,4)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'localamount',index : 'localamount',fixed : true,width : 100,sorttype : 'decimal(16,4)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'enterpriseamount',index : 'enterpriseamount',fixed : true,width : 100,sorttype : 'decimal(16,4)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'foreignamount',index : 'foreignamount',fixed : true,width : 100,sorttype : 'decimal(16,4)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'personalamount',index : 'personalamount',fixed : true,width : 100,sorttype : 'decimal(16,4)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'otheramount',index : 'otheramount',fixed : true,width : 100,sorttype : 'decimal(16,4)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'result',index : 'result',fixed : true,width : 100,sorttype : 'varchar(512)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'remark',index : 'remark',fixed : true,width : 100,sorttype : 'varchar(1000)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}}
					],
		processbtncol:2,//操作按钮放在第几个列
		isedit:false,//是否可编辑列表
		advsearch:true,
		multiSort:true,
		viewurl : "/webpage/biz/bi/expright/doModify.action",
		addurl : "/webpage/biz/bi/expright/doAdd.action",
		modifyurl : "/webpage/biz/bi/expright/doModifySave.action",
		deleteurl : "/webpage/biz/bi/expright/delete.action"
		
	});
	
	initgrid("t_bi_expright");
});

//删除修改进入表单时传递参数
function getGridParams(_bizname){
	var selectedRowId = $("#"+_bizname+"_grid").jqGrid("getGridParam", "selrow");  
    var str = $("#"+_bizname+"_grid").jqGrid("getRowData", selectedRowId); // 根据行ID，获取选中行的数据
		return {"bean.rightid" : str.rightid};
}

function getParams(_bizname){
	var selectedRowId = $("#"+_bizname+"_grid").jqGrid("getGridParam", "selrow");  
    var str = $("#"+_bizname+"_grid").jqGrid("getRowData", selectedRowId); // 根据行ID，获取选中行的数据
		return "?bean.rightid="+str.rightid;
}

//取编辑grid列表数据
function getEditGridParams(_bizname,selectedRowId){
	var datalist = $("#"+_bizname+"_grid").jqGrid('getRowData', selectedRowId);
	return {  
			"bean.rightid" : datalist.rightid,
			"bean.cooperateent" : datalist.cooperateent,
			"bean.mineralstage" : datalist.mineralstage,
			"bean.mineraltype" : datalist.mineraltype,
			"bean.rightgetway" : datalist.rightgetway,
			"bean.totalamount" : datalist.totalamount,
			"bean.year1amount" : datalist.year1amount,
			"bean.year2amount" : datalist.year2amount,
			"bean.year3amount" : datalist.year3amount,
			"bean.stateamount" : datalist.stateamount,
			"bean.localamount" : datalist.localamount,
			"bean.enterpriseamount" : datalist.enterpriseamount,
			"bean.foreignamount" : datalist.foreignamount,
			"bean.personalamount" : datalist.personalamount,
			"bean.otheramount" : datalist.otheramount,
			"bean.result" : datalist.result,
			"bean.remark" : datalist.remark
	}; 
}
function initEditGridRowData(_bizname,data,selectedRowId){
	var datalist = getEditGridParams(_bizname,selectedRowId);
	var dataJson = eval(data);
	return dataRow = {  
				rightid : dataJson.id,
				cooperateent : datalist.cooperateent,
				mineralstage : datalist.mineralstage,
				mineraltype : datalist.mineraltype,
				rightgetway : datalist.rightgetway,
				totalamount : datalist.totalamount,
				year1amount : datalist.year1amount,
				year2amount : datalist.year2amount,
				year3amount : datalist.year3amount,
				stateamount : datalist.stateamount,
				localamount : datalist.localamount,
				enterpriseamount : datalist.enterpriseamount,
				foreignamount : datalist.foreignamount,
				personalamount : datalist.personalamount,
				otheramount : datalist.otheramount,
				result : datalist.result,
				remark : datalist.remark
	}; 
}
