$(function() {
	// grid参数
	$("#t_bi_mrbaseinfo_grid").data("gridOptions", {
		height : 250,
		url :  rootpath + '/webpage/biz/bi/mrbaseinfo/list.action',
		colNames : [ 
					'操作',
					'矿业权ID',
					'矿山ID',
					'矿权类别',
					'项目名称',
					'项目性质',
					'矿业权状态',
					'拥有方式',
					'经济类型',
					'注册资金',
					'有效期开始时间',
					'有效期结束时间',
					'公司名称',
					'公司地址',
					'公司邮编',
					'法定代表人',
					'帐号',
					'开户银行',
					'矿业权资格证号',
					'东经起',
					'东经止',
					'北纬起',
					'北纬止',
					'地理位置',
					'矿权面积(km²)',
					'责任人'
					],//列显示名称
		colModel : [ //	align:left,center,right,默认left;classes:列的css;datefmt:默认Y-m-d;defval:查询字段的默认值;editable:单元格是否可编辑,默认false;
					 //fixed:列宽是否固定不可变,默认false;search:在搜索模式下，定义此列是否可以作搜索列
					{name : 'processbtn',index:'', width:80, fixed:true, sortable:false, resize:false,search : false,formatter:btnformatters},
					{name : 'rightid',index : 'rightid',fixed : true,width : 100,hidden:true,sorttype : 'varchar(32)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
					{name : 'mineid',index : 'mineid',fixed : true,width : 100,hidden:true,sorttype : 'varchar(32)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
					{name : 'mrcategoryname',index : 'mrcategoryname',fixed : true,width : 80,sorttype : 'varchar(10)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
					{name : 'projectname',index : 'projectname',fixed : true,width : 100,sorttype : 'varchar(100)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
					{name : 'projectqualityname',index : 'projectqualityname',fixed : true,width : 80,sorttype : 'varchar(10)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
					{name : 'mrstatename',index : 'mrstatename',fixed : true,width : 80,sorttype : 'varchar(10)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
					{name : 'ownwayname',index : 'ownwayname',fixed : true,width : 80,sorttype : 'varchar(10)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
					{name : 'economictypename',index : 'economictypename',fixed : true,width : 80,sorttype : 'varchar(10)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
					{name : 'registeredfund',index : 'registeredfund',fixed : true,width : 100,sorttype : 'decimal(16,4)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
					{name : 'expstartdate',index : 'expstartdate',fixed : true,width : 110,sorttype : 'datetime',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
					{name : 'expenddate',index : 'expenddate',fixed : true,width : 110,sorttype : 'datetime',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
					{name : 'companyname',index : 'companyname',fixed : true,width : 100,sorttype : 'varchar(100)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
					{name : 'companyaddr',index : 'companyaddr',fixed : true,width : 100,sorttype : 'varchar(100)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
					{name : 'companypost',index : 'companypost',fixed : true,width : 100,sorttype : 'varchar(20)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
					{name : 'legalrepresentative',index : 'legalrepresentative',fixed : true,width : 100,sorttype : 'varchar(100)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
					{name : 'accountnum',index : 'accountnum',fixed : true,width : 100,sorttype : 'varchar(100)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
					{name : 'bank',index : 'bank',fixed : true,width : 100,sorttype : 'varchar(100)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
					{name : 'mrcardid',index : 'mrcardid',fixed : true,width : 100,sorttype : 'varchar(100)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
					{name : 'eaststart',index : 'eaststart',fixed : true,width : 100,sorttype : 'decimal(16,4)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
					{name : 'eastend',index : 'eastend',fixed : true,width : 100,sorttype : 'decimal(16,4)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
					{name : 'northstart',index : 'northstart',fixed : true,width : 100,sorttype : 'decimal(16,4)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
					{name : 'northend',index : 'northend',fixed : true,width : 100,sorttype : 'decimal(16,4)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
					{name : 'geoposition',index : 'geoposition',fixed : true,width : 100,sorttype : 'varchar(100)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
					{name : 'mrarea',index : 'mrarea',fixed : true,width : 100,sorttype : 'decimal(16,4)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
					{name : 'dutyman',index : 'dutyman',fixed : true,width : 100,sorttype : 'varchar(32)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}}
				],
				onSelectRow: function(rowid, status){
				    var str = $("#t_bi_mrbaseinfo_grid").jqGrid("getRowData", rowid);
					$("#t_bi_mrbaseinfofee_grid").setGridParam({page:1,postData:{"bean.rightid":str.rightid}}).trigger("reloadGrid");
				},
		processbtncol:2,//操作按钮放在第几个列
		isedit:false,//是否可编辑列表
		advsearch:true,
		multiSort:true,
		formw:900,
		formh:600,
		viewurl : "/webpage/biz/bi/mrbaseinfo/doModify.action",
		addurl : "/webpage/biz/bi/mrbaseinfo/doAdd.action",
		modifyurl : "/webpage/biz/bi/mrbaseinfo/doModifySave.action",
		//deleteurl : "/webpage/biz/bi/mrbaseinfo/delete.action"
	});
	
	// 隐藏分页栏修改、删除按钮
	$("#t_bi_mrbaseinfo_grid_pager_left td").slice(0, 1).hide();
	
	$("#btn_add_tkq").click(function(){
		var _bizname = "t_bi_mrbaseinfo";
		var url = $("#"+_bizname+"_grid").data("gridOptions").addurl+"?bean.mrcategory=KQLB001";
		parent.createwindow(_bizname,url,"新增",_formw,_formh,true);
	});
	
	$("#t_bi_mrbaseinfofee_grid").data("gridOptions", {
		height : 250,
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
	
	initgrid("t_bi_mrbaseinfo");
});

function btnformatters(cellvalue, options, rowobj){
	update = "<div class='ui-pg-div ui-inline-edit' style='float: left; margin-left: 8px;' title='修改' onclick=_doopenmodify('t_bi_mrbaseinfo','"+ options.rowId
	+ "')><span class='ui-icon ui-icon-pencil'/></div>";
	view = "<div class='ui-pg-div' style='float: left; margin-left: 8px;' title='查看' onclick=doview('t_bi_mrbaseinfo','"+ options.rowId
		+ "')><span class='ui-icon fa-search-plus'/></div>";
	return  update+view;
}

function doview(_bizname,selectedRowId){
	setTimeout(function(){
		_openDialog4Reading(_bizname);
	},0);
}

var _openDialog4Adding = function(_bizname) {
	if(_bizname=='t_bi_mrbaseinfofee'){
		var selectedRowId = $("#t_bi_mrbaseinfo_grid").jqGrid("getGridParam", "selrow");
		if(selectedRowId==''||selectedRowId==null){
			alertMsg("请先选择信息!");
			return;
		}
		var rowData = $("#t_bi_mrbaseinfo_grid").jqGrid('getRowData',selectedRowId);
		var url = $("#"+_bizname+"_grid").data("gridOptions").addurl+"?bean.rightid="+rowData.rightid;
		parent.createwindow(_bizname,url,"新增",_formw,_formh,true);
	}else if(_bizname=='t_bi_mrbaseinfo'){
		var url = $("#"+_bizname+"_grid").data("gridOptions").addurl;
		parent.createwindow(_bizname,url,"新增",_formw,_formh,true);
	}
};

//删除修改进入表单时传递参数
function getGridParams(_bizname){
	var selectedRowId = $("#"+_bizname+"_grid").jqGrid("getGridParam", "selrow");  
    var str = $("#"+_bizname+"_grid").jqGrid("getRowData", selectedRowId); // 根据行ID，获取选中行的数据
    if(_bizname=='t_bi_mrbaseinfo'){
    	return {"bean.rightid" : str.rightid};
    }else if(_bizname=='t_bi_mrbaseinfofee'){
    	return {"bean.feeid" : str.feeid};
    }
		
}

function getParams(_bizname){
	var selectedRowId = $("#"+_bizname+"_grid").jqGrid("getGridParam", "selrow");  
    var str = $("#"+_bizname+"_grid").jqGrid("getRowData", selectedRowId); // 根据行ID，获取选中行的数据
    if(_bizname=='t_bi_mrbaseinfo'){
		return "?bean.rightid="+str.rightid;
    }else if(_bizname=='t_bi_mrbaseinfofee'){
    	return "?bean.feeid="+str.feeid;
    }
}

//取编辑grid列表数据
function getEditGridParams(_bizname,selectedRowId){
	var datalist = $("#"+_bizname+"_grid").jqGrid('getRowData', selectedRowId);
	return {  
			"bean.rightid" : datalist.rightid,
			"bean.mineid" : datalist.mineid,
			"bean.mrcategory" : datalist.mrcategory,
			"bean.projectname" : datalist.projectname,
			"bean.projectquality" : datalist.projectquality,
			"bean.mrstate" : datalist.mrstate,
			"bean.ownway" : datalist.ownway,
			"bean.economictype" : datalist.economictype,
			"bean.companyname" : datalist.companyname,
			"bean.companyaddr" : datalist.companyaddr,
			"bean.companypost" : datalist.companypost,
			"bean.registeredfund" : datalist.registeredfund,
			"bean.legalrepresentative" : datalist.legalrepresentative,
			"bean.accountnum" : datalist.accountnum,
			"bean.bank" : datalist.bank,
			"bean.mrcardid" : datalist.mrcardid,
			"bean.eaststart" : datalist.eaststart,
			"bean.eastend" : datalist.eastend,
			"bean.northstart" : datalist.northstart,
			"bean.northend" : datalist.northend,
			"bean.geoposition" : datalist.geoposition,
			"bean.mrarea" : datalist.mrarea,
			"bean.expstartdate" : datalist.expstartdate,
			"bean.expenddate" : datalist.expenddate,
			"bean.dutyman" : datalist.dutyman,
			"bean.createdate" : datalist.createdate,
			"bean.createid" : datalist.createid,
			"bean.modifyid" : datalist.modifyid,
			"bean.modifydate" : datalist.modifydate,
			"bean.remark" : datalist.remark
	}; 
}
function initEditGridRowData(_bizname,data,selectedRowId){
	var datalist = getEditGridParams(_bizname,selectedRowId);
	var dataJson = eval(data);
	return dataRow = {  
				rightid : dataJson.id,
				mineid : datalist.mineid,
				mrcategory : datalist.mrcategory,
				projectname : datalist.projectname,
				projectquality : datalist.projectquality,
				mrstate : datalist.mrstate,
				ownway : datalist.ownway,
				economictype : datalist.economictype,
				companyname : datalist.companyname,
				companyaddr : datalist.companyaddr,
				companypost : datalist.companypost,
				registeredfund : datalist.registeredfund,
				legalrepresentative : datalist.legalrepresentative,
				accountnum : datalist.accountnum,
				bank : datalist.bank,
				mrcardid : datalist.mrcardid,
				eaststart : datalist.eaststart,
				eastend : datalist.eastend,
				northstart : datalist.northstart,
				northend : datalist.northend,
				geoposition : datalist.geoposition,
				mrarea : datalist.mrarea,
				expstartdate : datalist.expstartdate,
				expenddate : datalist.expenddate,
				dutyman : datalist.dutyman,
				createdate : datalist.createdate,
				createid : datalist.createid,
				modifyid : datalist.modifyid,
				modifydate : datalist.modifydate,
				remark : datalist.remark
	}; 
}
