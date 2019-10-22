$(function() {
	// grid参数
	$("#t_bi_mine_grid").data("gridOptions", {
		height : parent.getClientHeights(),
		url :  rootpath + '/webpage/biz/bi/mine/list.action',
		colNames : [ 
					'操作',
					'MineID',
					'组织机构',
					'组织机构',
					'矿山编码',
					'矿山名称',
					'矿石工业类型',
					'建矿日期',
					'投产日期',
					'生产能力(万吨)',
					'选矿能力(万吨)',
					'开拓运输方案',
					'开采方式',
					'国家',
					'省份',
					'城市',
					'地址',
					'面积',
					'宽度（Km）',
					'长度（Km）',
					'标高（m）',
					'东经起',
					'东经止',
					'北纬起',
					'北纬止',
					'矿山X坐标',
					'矿山Y坐标',
					'生产状态',
					'服务年限',
					'剥离比',
					'探采比',
					'从业人员',
					'技术人员'
					],//列显示名称
		colModel : [ //	align:left,center,right,默认left;classes:列的css;datefmt:默认Y-m-d;defval:查询字段的默认值;editable:单元格是否可编辑,默认false;
					 //fixed:列宽是否固定不可变,默认false;search:在搜索模式下，定义此列是否可以作搜索列
					{name : 'processbtn',index:'', width:80, fixed:true, sortable:false, resize:false,search : false,formatter:btnformatter},
					{name : 'mineid',index : 'mineid',fixed : true,width : 100,hidden:true,sorttype : 'varchar(32)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
					{name : 'orgid',index : 'orgid',fixed : true,width : 100,hidden:true,sorttype : 'varchar(32)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
					{name : 'orgname',index : 'orgname',fixed : true,width : 100,sorttype : 'varchar(32)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
					{name : 'mineno',index : 'mineno',fixed : true,width : 100,sorttype : 'varchar(50)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
					{name : 'minename',index : 'minename',fixed : true,width : 100,sorttype : 'varchar(255)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
					{name : 'oremanutypename',index : 'oremanutypename',fixed : true,width : 100,sorttype : 'varchar(32)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
					{name : 'builddate',index : 'builddate',fixed : true,width : 80,sorttype : 'datetime',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
					{name : 'startingdate',index : 'startingdate',fixed : true,width : 80,sorttype : 'datetime',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
					{name : 'productivepower',index : 'productivepower',fixed : true,width : 100,sorttype : 'decimal(16,4)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
					{name : 'siftpower',index : 'siftpower',fixed : true,width : 100,hidden:true,sorttype : 'decimal(16,4)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
					{name : 'transportation',index : 'transportation',fixed : true,width : 100,sorttype : 'varchar(100)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
					{name : 'miningmethodname',index : 'miningmethodname',fixed : true,width : 100,sorttype : 'varchar(10)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
					{name : 'countryname',index : 'countryname',fixed : true,width : 60,sorttype : 'varchar(32)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
					{name : 'provincename',index : 'provincename',fixed : true,width : 60,sorttype : 'varchar(32)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
					{name : 'cityname',index : 'cityname',fixed : true,width : 90,sorttype : 'varchar(32)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
					{name : 'address',index : 'address',fixed : true,width : 100,sorttype : 'varchar(500)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
					{name : 'area',index : 'area',fixed : true,width : 100,sorttype : 'decimal(16,4)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
					{name : 'minewidth',index : 'minewidth',fixed : true,width : 100,sorttype : 'decimal(10,4)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
					{name : 'minelength',index : 'minelength',fixed : true,width : 100,sorttype : 'decimal(10,4)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
					{name : 'mineheight',index : 'mineheight',fixed : true,width : 100,sorttype : 'decimal(10,4)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
					{name : 'eastlognstart',index : 'eastlognstart',fixed : true,width : 100,sorttype : 'varchar(20)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
					{name : 'eastlognend',index : 'eastlognend',fixed : true,width : 100,sorttype : 'varchar(20)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
					{name : 'northlatstart',index : 'northlatstart',fixed : true,width : 100,sorttype : 'varchar(20)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
					{name : 'northlatend',index : 'northlatend',fixed : true,width : 100,sorttype : 'varchar(20)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
					{name : 'coordinatex',index : 'coordinatex',fixed : true,width : 100,sorttype : 'varchar(20)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
					{name : 'coordinatey',index : 'coordinatey',fixed : true,width : 100,sorttype : 'varchar(20)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
					{name : 'productivestatusname',index : 'productivestatusname',fixed : true,width : 100,sorttype : 'varchar(32)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
					{name : 'serviceyear',index : 'serviceyear',fixed : true,width : 100,sorttype : 'int(11)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
					{name : 'strippingratio',index : 'strippingratio',fixed : true,width : 100,sorttype : 'decimal(16,4)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
					{name : 'explorationratio',index : 'explorationratio',fixed : true,width : 100,sorttype : 'decimal(16,4)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
					{name : 'workusercount',index : 'workusercount',fixed : true,width : 100,sorttype : 'int(11)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
					{name : 'techusercount',index : 'techusercount',fixed : true,width : 100,sorttype : 'int(11)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}}
				],
		processbtncol:2,//操作按钮放在第几个列
		isedit:false,//是否可编辑列表
		advsearch:true,
		multiSort:true,
		formw:800,
		formh:500,
		viewurl : "/webpage/biz/bi/mine/doModify.action",
		addurl : "/webpage/biz/bi/mine/doAdd.action",
		modifyurl : "/webpage/biz/bi/mine/doModifySave.action",
		deleteurl : "/webpage/biz/bi/mine/delete.action"
		
	});
	
	initgrid("t_bi_mine");
});

//删除修改进入表单时传递参数
function getGridParams(_bizname){
	var selectedRowId = $("#"+_bizname+"_grid").jqGrid("getGridParam", "selrow");  
    var str = $("#"+_bizname+"_grid").jqGrid("getRowData", selectedRowId); // 根据行ID，获取选中行的数据
		return {"bean.mineid" : str.mineid};
}

function getParams(_bizname){
	var selectedRowId = $("#"+_bizname+"_grid").jqGrid("getGridParam", "selrow");  
    var str = $("#"+_bizname+"_grid").jqGrid("getRowData", selectedRowId); // 根据行ID，获取选中行的数据
		return "?bean.mineid="+str.mineid;
}

//取编辑grid列表数据
function getEditGridParams(_bizname,selectedRowId){
	var datalist = $("#"+_bizname+"_grid").jqGrid('getRowData', selectedRowId);
	return {  
			"bean.mineid" : datalist.mineid,
			"bean.orgid" : datalist.orgid,
			"bean.mineno" : datalist.mineno,
			"bean.minename" : datalist.minename,
			"bean.oremanutype" : datalist.oremanutype,
			"bean.builddate" : datalist.builddate,
			"bean.startingdate" : datalist.startingdate,
			"bean.productivepower" : datalist.productivepower,
			"bean.siftpower" : datalist.siftpower,
			"bean.transportation" : datalist.transportation,
			"bean.miningmethod" : datalist.miningmethod,
			"bean.country" : datalist.country,
			"bean.province" : datalist.province,
			"bean.city" : datalist.city,
			"bean.address" : datalist.address,
			"bean.area" : datalist.area,
			"bean.minewidth" : datalist.minewidth,
			"bean.minelength" : datalist.minelength,
			"bean.mineheight" : datalist.mineheight,
			"bean.eastlognstart" : datalist.eastlognstart,
			"bean.eastlognend" : datalist.eastlognend,
			"bean.northlatstart" : datalist.northlatstart,
			"bean.northlatend" : datalist.northlatend,
			"bean.coordinatex" : datalist.coordinatex,
			"bean.coordinatey" : datalist.coordinatey,
			"bean.productivestatus" : datalist.productivestatus,
			"bean.serviceyear" : datalist.serviceyear,
			"bean.strippingratio" : datalist.strippingratio,
			"bean.explorationratio" : datalist.explorationratio,
			"bean.workusercount" : datalist.workusercount,
			"bean.techusercount" : datalist.techusercount,
			"bean.remark" : datalist.remark,
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
				mineid : dataJson.id,
				orgid : datalist.orgid,
				mineno : datalist.mineno,
				minename : datalist.minename,
				oremanutype : datalist.oremanutype,
				builddate : datalist.builddate,
				startingdate : datalist.startingdate,
				productivepower : datalist.productivepower,
				siftpower : datalist.siftpower,
				transportation : datalist.transportation,
				miningmethod : datalist.miningmethod,
				country : datalist.country,
				province : datalist.province,
				city : datalist.city,
				address : datalist.address,
				area : datalist.area,
				minewidth : datalist.minewidth,
				minelength : datalist.minelength,
				mineheight : datalist.mineheight,
				eastlognstart : datalist.eastlognstart,
				eastlognend : datalist.eastlognend,
				northlatstart : datalist.northlatstart,
				northlatend : datalist.northlatend,
				coordinatex : datalist.coordinatex,
				coordinatey : datalist.coordinatey,
				productivestatus : datalist.productivestatus,
				serviceyear : datalist.serviceyear,
				strippingratio : datalist.strippingratio,
				explorationratio : datalist.explorationratio,
				workusercount : datalist.workusercount,
				techusercount : datalist.techusercount,
				remark : datalist.remark,
				createid : datalist.createid,
				createdate : datalist.createdate,
				modifyid : datalist.modifyid,
				modifydate : datalist.modifydate
	}; 
}
