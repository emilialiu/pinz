$(function() {
	// grid参数
	$("#t_dm_device_grid").data("gridOptions", {
		height : 272,
		url :  rootpath + '/webpage/biz/dm/devicetz/list.action',
		colNames : [ 
					'设备ID',
					'所属矿山',
					'设备编号',
					'设备名称',
					'规格型号',
					'投用时间',
					'创建人',
					'创建日期',
					'修改人',
					'修改日期',
					'备注',
					'所属工段',
					'发动机型号',
					'车架号',
					'发动机号',
					'产量',
					'运行小时数',
					'柴油消耗',
					'备件消耗',
					'故障小时数',
					'设备来源'
					],//列显示名称
		colModel : [ //	align:left,center,right,默认left;classes:列的css;datefmt:默认Y-m-d;defval:查询字段的默认值;editable:单元格是否可编辑,默认false;
					 //fixed:列宽是否固定不可变,默认false;search:在搜索模式下，定义此列是否可以作搜索列
						{name : 'devid',index : 'devid',width : 60,sorttype : "varchar(40)",editable : false,hidden : true},
						{name : 'deptid',index : 'deptid',width : 60,sorttype : "varchar(40)",editable : false,hidden : true},
						{name : 'devcode',index : 'devcode',fixed : true,width : 100,sorttype : 'varchar(40)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'devname',index : 'devname',fixed : true,width : 100,sorttype : 'varchar(100)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'devmodel',index : 'devmodel',fixed : true,width : 100,sorttype : 'varchar(100)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'userdate',index : 'userdate',fixed : true,width : 100,sorttype : 'date',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'createid',index : 'createid',width : 60,sorttype : "varchar(40)",editable : false,hidden : true},
						{name : 'createdate',index : 'createdate',width : 60,sorttype : "datetime",editable : false,hidden : true},
						{name : 'modifyid',index : 'modifyid',width : 60,sorttype : "varchar(40)",editable : false,hidden : true},
						{name : 'modifydate',index : 'modifydate',width : 60,sorttype : "datetime",editable : false,hidden : true},
						{name : 'memo',index : 'memo',fixed : true,width : 100,sorttype : 'varchar(1000)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'belongname',index : 'belongname',fixed : true,width : 100,sorttype : 'varchar(100)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'engineno',index : 'engineno',fixed : true,width : 100,sorttype : 'varchar(100)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'frameno',index : 'frameno',fixed : true,width : 100,sorttype : 'varchar(100)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'enginehao',index : 'enginehao',fixed : true,width : 100,sorttype : 'varchar(100)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'production',hidden : true,index : 'production',fixed : true,width : 100,sorttype : 'decimal(16,2)',editable : false},
						{name : 'workhour',hidden : true,index : 'workhour',fixed : true,width : 100,sorttype : 'decimal(16,2)',editable : false},
						{name : 'fuelconsumption',hidden : true,index : 'fuelconsumption',fixed : true,width : 100,sorttype : 'decimal(16,2)',editable : false},
						{name : 'partsconsumption',hidden : true,index : 'partsconsumption',fixed : true,width : 100,sorttype : 'varchar(500)',editable : false},
						{name : 'bughour',hidden : true,index : 'bughour',fixed : true,width : 100,sorttype : 'decimal(16,2)',editable : false},
						{name : 'source',index : 'source',fixed : true,width : 100,sorttype : 'varchar(40)',editable : false}
					],
		processbtncol:2,//操作按钮放在第几个列
		isedit:false,//是否可编辑列表
		advsearch:true,
		multiSort:true,
		multiselect:multiselect=="null"?false:multiselect,
		viewurl : "/webpage/biz/dm/devicetz/doModify.action",
		addurl : "/webpage/biz/dm/devicetz/doAdd.action",
		modifyurl : "/webpage/biz/dm/devicetz/doModifySave.action",
		deleteurl : "/webpage/biz/dm/devicetz/delete.action"
		
	});
	
	initgrid("t_dm_device");
	
	$("#t_dm_device_grid_pager_left td").slice(0,5).hide();
	
	if(multiselect){
		$("button:contains('清空选择')", window.parent.document).hide();
		$("button:contains('Empty choice')", window.parent.document).hide();
	}else{
		$("button:contains('清空选择')", window.parent.document).show();
		$("button:contains('Empty choice')", window.parent.document).show();
	}
});

//删除修改进入表单时传递参数
function getGridParams(_bizname){
	var selectedRowId = $("#"+_bizname+"_grid").jqGrid("getGridParam", "selrow");  
    var str = $("#"+_bizname+"_grid").jqGrid("getRowData", selectedRowId); // 根据行ID，获取选中行的数据
		return {"bean.devid" : str.devid};
}

function getParams(_bizname){
	var selectedRowId = $("#"+_bizname+"_grid").jqGrid("getGridParam", "selrow");  
    var str = $("#"+_bizname+"_grid").jqGrid("getRowData", selectedRowId); // 根据行ID，获取选中行的数据
		return "?bean.devid="+str.devid;
}

//取编辑grid列表数据
function getEditGridParams(_bizname,selectedRowId){
	var datalist = $("#"+_bizname+"_grid").jqGrid('getRowData', selectedRowId);
	return {  
			"bean.devid" : datalist.devid,
			"bean.deptid" : datalist.deptid,
			"bean.devcode" : datalist.devcode,
			"bean.devname" : datalist.devname,
			"bean.devmodel" : datalist.devmodel,
			"bean.userdate" : datalist.userdate,
			"bean.createid" : datalist.createid,
			"bean.createdate" : datalist.createdate,
			"bean.modifyid" : datalist.modifyid,
			"bean.modifydate" : datalist.modifydate,
			"bean.memo" : datalist.memo,
			"bean.belongunit" : datalist.belongunit,
			"bean.engineno" : datalist.engineno,
			"bean.frameno" : datalist.frameno,
			"bean.enginehao" : datalist.enginehao,
			"bean.production" : datalist.production,
			"bean.workhour" : datalist.workhour,
			"bean.fuelconsumption" : datalist.fuelconsumption,
			"bean.partsconsumption" : datalist.partsconsumption,
			"bean.bughour" : datalist.bughour,
			"bean.source" : datalist.source
	}; 
}
function initEditGridRowData(_bizname,data,selectedRowId){
	var datalist = getEditGridParams(_bizname,selectedRowId);
	var dataJson = eval(data);
	return dataRow = {  
				devid : dataJson.id,
				deptid : datalist.deptid,
				devcode : datalist.devcode,
				devname : datalist.devname,
				devmodel : datalist.devmodel,
				userdate : datalist.userdate,
				createid : datalist.createid,
				createdate : datalist.createdate,
				modifyid : datalist.modifyid,
				modifydate : datalist.modifydate,
				memo : datalist.memo,
				belongunit : datalist.belongunit,
				engineno : datalist.engineno,
				frameno : datalist.frameno,
				enginehao : datalist.enginehao,
				production : datalist.production,
				workhour : datalist.workhour,
				fuelconsumption : datalist.fuelconsumption,
				partsconsumption : datalist.partsconsumption,
				bughour : datalist.bughour,
				source : datalist.source
	}; 
}


function onSave(){
	var data = [];
	var rowdata;
	if(multiselect == 'true'){//多选
		var selectedRowIds = $("#t_dm_device_grid").jqGrid("getGridParam", "selarrrow");
		if (!selectedRowIds) {
			alertMsg(pub_one);
			return false;
		}
		for(var i=0;i<selectedRowIds.length;i++){
			rowdata = $("#t_dm_device_grid").jqGrid("getRowData", selectedRowIds[i]);
			data.push(rowdata);
		}
	}else{//单选
		var selectedRowId = $("#t_dm_device_grid").jqGrid("getGridParam", "selrow");
	    if (!selectedRowId) {
	    	alertMsg(pub_one);
	        return false;
	    }
	    rowdata = $("#t_dm_device_grid").jqGrid("getRowData", selectedRowId);
		data.push(rowdata);
	}
	try{
		parent.PageObject.itemMap['devicetz_selectdevice'].callback(data);
	}catch(e){
		parent.closewin('devicetz_selectdevice');
	}
	parent.closewin('devicetz_selectdevice');
}

function uncheck() {
	parent.PageObject.itemMap['devicetz_selectdevice'].callback([{"paramname":"","code":""}]);
	parent.closewin('devicetz_selectdevice');
}