$(function() {
$("#device_grid").data("gridOptions", {
		height : 272,
		url :  rootpath + '/webpage/biz/sc/devtechprocess/devicelist.action?bean.procid='+procid,
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
		multiselect:multiselect=="null"?false:multiselect
	});
		initgrid("device");
		if(multiselect){
			$("button:contains('清空选择')", window.parent.document).hide();
			$("button:contains('Empty choice')", window.parent.document).hide();
		}else{
			$("button:contains('清空选择')", window.parent.document).show();
			$("button:contains('Empty choice')", window.parent.document).show();
		}
});

function onSave(){
	var data = [];
	var rowdata;
	if(multiselect == 'true'){//多选
		var selectedRowIds = $("#device_grid").jqGrid("getGridParam", "selarrrow");
		if (!selectedRowIds) {
			alertMsg(pub_one);
			return false;
		}
		for(var i=0;i<selectedRowIds.length;i++){
			rowdata = $("#device_grid").jqGrid("getRowData", selectedRowIds[i]);
			data.push(rowdata);
		}
	}else{//单选
		var selectedRowId = $("#device_grid").jqGrid("getGridParam", "selrow");
	    if (!selectedRowId) {
	    	alertMsg(pub_one);
	        return false;
	    }
	    rowdata = $("#device_grid").jqGrid("getRowData", selectedRowId);
		data.push(rowdata);
	}
	try{
		parent.PageObject.itemMap['deviceprocess_selectdevice'].callback(data);
	}catch(e){
		parent.closewin('deviceprocess_selectdevice');
	}
	parent.closewin('deviceprocess_selectdevice');
}
function uncheck() {
	parent.PageObject.itemMap['deviceprocess_selectdevice'].callback([{"paramname":"","code":""}]);
	parent.closewin('deviceprocess_selectdevice');
}

