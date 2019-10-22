$(function() {
	// grid参数
	$("#t_bi_threeore_grid").data("gridOptions", {
		height : parent.getClientHeights(),
		url :  rootpath + '/webpage/biz/bi/threeore/list.action',
		colNames : [ 
					'操作',
					'三级矿量信息ID',
					'矿山ID',
					'所属单元ID',
					'年月',
					'开拓量（t）',
					'采准量（t）',
					'可采量（t）',
					'保有期限（月）',
					'备注'
					],//列显示名称
		colModel : [ //	align:left,center,right,默认left;classes:列的css;datefmt:默认Y-m-d;defval:查询字段的默认值;editable:单元格是否可编辑,默认false;
					 //fixed:列宽是否固定不可变,默认false;search:在搜索模式下，定义此列是否可以作搜索列
					{name : 'processbtn',index:'', width:80, fixed:true, sortable:false, resize:false,search : false,formatter:btnformatter},
						{name : 'threeoreid',index : 'threeoreid',hidden:true,fixed : true,width : 100,sorttype : 'varchar(32)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'mineid',index : 'mineid',hidden:true,fixed : true,width : 100,sorttype : 'varchar(32)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'mineunitid',index : 'mineunitid',hidden:true,fixed : true,width : 100,sorttype : 'varchar(32)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'yearvalue',index : 'yearvalue',fixed : true,width : 100,sorttype : 'varchar(4)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'reclamount',index : 'reclamount',fixed : true,width : 100,sorttype : 'decimal(16,4)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'expamount',index : 'expamount',fixed : true,width : 100,sorttype : 'decimal(16,4)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'extamount',index : 'extamount',fixed : true,width : 100,sorttype : 'decimal(16,4)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'byqx',index : 'byqx',fixed : true,width : 100,sorttype : 'decimal(8,1)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'memo',index : 'memo',fixed : true,width : 100,sorttype : 'varchar(1000)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}}
					],
		processbtncol:2,//操作按钮放在第几个列
		isedit:false,//是否可编辑列表
		advsearch:true,
		multiSort:true,
		viewurl : "/webpage/biz/bi/threeore/doModify.action",
		addurl : "/webpage/biz/bi/threeore/doAdd.action",
		modifyurl : "/webpage/biz/bi/threeore/doModifySave.action",
		deleteurl : "/webpage/biz/bi/threeore/delete.action"
		
	});
	
	initgrid("t_bi_threeore");
});

//删除修改进入表单时传递参数
function getGridParams(_bizname){
	var selectedRowId = $("#"+_bizname+"_grid").jqGrid("getGridParam", "selrow");  
    var str = $("#"+_bizname+"_grid").jqGrid("getRowData", selectedRowId); // 根据行ID，获取选中行的数据
		return {"bean.threeoreid" : str.threeoreid};
}

function getParams(_bizname){
	var selectedRowId = $("#"+_bizname+"_grid").jqGrid("getGridParam", "selrow");  
    var str = $("#"+_bizname+"_grid").jqGrid("getRowData", selectedRowId); // 根据行ID，获取选中行的数据
		return "?bean.threeoreid="+str.threeoreid;
}

//取编辑grid列表数据
function getEditGridParams(_bizname,selectedRowId){
	var datalist = $("#"+_bizname+"_grid").jqGrid('getRowData', selectedRowId);
	return {  
			"bean.threeoreid" : datalist.threeoreid,
			"bean.mineid" : datalist.mineid,
			"bean.mineunitid" : datalist.mineunitid,
			"bean.yearvalue" : datalist.yearvalue,
			"bean.element" : datalist.element,
			"bean.reclamount" : datalist.reclamount,
			"bean.expamount" : datalist.expamount,
			"bean.extamount" : datalist.extamount,
			"bean.byqx" : datalist.byqx,
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
				threeoreid : dataJson.id,
				mineid : datalist.mineid,
				mineunitid : datalist.mineunitid,
				yearvalue : datalist.yearvalue,
				element : datalist.element,
				reclamount : datalist.reclamount,
				expamount : datalist.expamount,
				extamount : datalist.extamount,
				byqx:datalist.byqx,
				memo : datalist.memo,
				createid : datalist.createid,
				createdate : datalist.createdate,
				modifyid : datalist.modifyid,
				modifydate : datalist.modifydate
	}; 
}
