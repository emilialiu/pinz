$(function() {
	// grid参数
	$("#t_bi_mineright_grid").data("gridOptions", {
		height : parent.getClientHeights(),
		url :  rootpath + '/webpage/biz/bi/mineright/list.action',
		colNames : [ 
					'操作',
					'矿业权ID',
					'合作单位',
					'设计规模',
					'投资额',
					'矿区范围',
					'矿权面积(km2)',
					'矿种',
					'共伴生类型',
					'探明的地质储量',
					'开采方式',
					'采矿方法',
					'选矿方法',
					'采深上限',
					'采深下限',
					'采矿权获取方式',
					'备注'
					],//列显示名称
		colModel : [ //	align:left,center,right,默认left;classes:列的css;datefmt:默认Y-m-d;defval:查询字段的默认值;editable:单元格是否可编辑,默认false;
					 //fixed:列宽是否固定不可变,默认false;search:在搜索模式下，定义此列是否可以作搜索列
					{name : 'processbtn',index:'', width:80, fixed:true, sortable:false, resize:false,search : false,formatter:btnformatter},
						{name : 'rightid',index : 'rightid',fixed : true,width : 100,sorttype : 'varchar(32)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'cooperateent',index : 'cooperateent',fixed : true,width : 100,sorttype : 'varchar(256)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'planscale',index : 'planscale',fixed : true,width : 100,sorttype : 'varchar(100)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'involume',index : 'involume',fixed : true,width : 100,sorttype : 'decimal(16,4)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'arearange',index : 'arearange',fixed : true,width : 100,sorttype : 'varchar(100)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'mrarea',index : 'mrarea',fixed : true,width : 100,sorttype : 'decimal(16,4)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'mineraltype',index : 'mineraltype',fixed : true,width : 100,sorttype : 'varchar(10)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'catype',index : 'catype',fixed : true,width : 100,sorttype : 'varchar(10)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'verifyreserve',index : 'verifyreserve',fixed : true,width : 100,sorttype : 'decimal(16,4)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'mineway',index : 'mineway',fixed : true,width : 100,sorttype : 'varchar(10)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'miningmethod',index : 'miningmethod',fixed : true,width : 100,sorttype : 'varchar(10)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'benemethod',index : 'benemethod',fixed : true,width : 100,sorttype : 'varchar(10)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'depthupper',index : 'depthupper',fixed : true,width : 100,sorttype : 'decimal(16,4)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'depthlower',index : 'depthlower',fixed : true,width : 100,sorttype : 'decimal(16,4)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'minegetway',index : 'minegetway',fixed : true,width : 100,sorttype : 'varchar(10)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'remark',index : 'remark',fixed : true,width : 100,sorttype : 'varchar(1000)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}}
					],
		processbtncol:2,//操作按钮放在第几个列
		isedit:false,//是否可编辑列表
		advsearch:true,
		multiSort:true,
		viewurl : "/webpage/biz/bi/mineright/doModify.action",
		addurl : "/webpage/biz/bi/mineright/doAdd.action",
		modifyurl : "/webpage/biz/bi/mineright/doModifySave.action",
		deleteurl : "/webpage/biz/bi/mineright/delete.action"
		
	});
	
	initgrid("t_bi_mineright");
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
			"bean.planscale" : datalist.planscale,
			"bean.involume" : datalist.involume,
			"bean.arearange" : datalist.arearange,
			"bean.mrarea" : datalist.mrarea,
			"bean.mineraltype" : datalist.mineraltype,
			"bean.catype" : datalist.catype,
			"bean.verifyreserve" : datalist.verifyreserve,
			"bean.mineway" : datalist.mineway,
			"bean.miningmethod" : datalist.miningmethod,
			"bean.benemethod" : datalist.benemethod,
			"bean.depthupper" : datalist.depthupper,
			"bean.depthlower" : datalist.depthlower,
			"bean.minegetway" : datalist.minegetway,
			"bean.remark" : datalist.remark
	}; 
}
function initEditGridRowData(_bizname,data,selectedRowId){
	var datalist = getEditGridParams(_bizname,selectedRowId);
	var dataJson = eval(data);
	return dataRow = {  
				rightid : dataJson.id,
				cooperateent : datalist.cooperateent,
				planscale : datalist.planscale,
				involume : datalist.involume,
				arearange : datalist.arearange,
				mrarea : datalist.mrarea,
				mineraltype : datalist.mineraltype,
				catype : datalist.catype,
				verifyreserve : datalist.verifyreserve,
				mineway : datalist.mineway,
				miningmethod : datalist.miningmethod,
				benemethod : datalist.benemethod,
				depthupper : datalist.depthupper,
				depthlower : datalist.depthlower,
				minegetway : datalist.minegetway,
				remark : datalist.remark
	}; 
}
