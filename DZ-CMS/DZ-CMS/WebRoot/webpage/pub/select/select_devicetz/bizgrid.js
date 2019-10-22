$(function() {
$("#device_grid").data("gridOptions", {
		height : 272,
		url :  rootpath + '/webpage/biz/dm/devicetz/list.action?bean.devstatus='+devstatus+'&bean.devkind='+devkind+"&bean.devtype="+devtype,
		colNames : [ 
					'设备id',
					'设备名称',
					'设备类型',
					'设备类型',
					//'设备类型',
					'规格型号',
					'设备编号',
					'所属工段'/*,
					'设备种类',
					'设备种类',
					//'设备种类ID',
					'安装地点',
					'使用部门',
					'使用部门'*/
					],//列显示名称
		colModel : [ //	align:left,center,right,默认left;classes:列的css;datefmt:默认Y-m-d;defval:查询字段的默认值;editable:单元格是否可编辑,默认false;
					 //fixed:列宽是否固定不可变,默认false;search:在搜索模式下，定义此列是否可以作搜索列
		             {name : 'devid',index : 'devid',fixed : true,width : 100,sorttype : 'varchar(40)',hidden:true,editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne' ]}},
		             {name : 'devname',index : 'devname',fixed : true,width : 100,sorttype : 'varchar(40)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne' ]}},
					 {name : 'dtid',index : 'dtid',fixed : true,width : 100,hidden:true,sorttype : 'varchar(40)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne' ]}},
					 {name : 'dtname',index : 'dtname',fixed : true,width : 100,sorttype : 'varchar(40)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne' ]}},
					 {name : 'devmodel',index : 'devmodel',fixed : true,width : 100,sorttype : 'varchar(40)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne' ]}},
					 {name : 'devcode',index : 'devcode',fixed : true,width : 100,sorttype : 'varchar(100)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne']}},
					 {name : 'belongname',index : 'belongname',fixed : true,width : 100,sorttype : 'varchar(100)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne']}}/*,
					 {name : 'devkind',index : 'devkind',fixed : true,width : 100,hidden:true,sorttype : 'varchar(100)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne' ]}},
					 {name : 'devkindname',index : 'devkindname',fixed : true,width : 100,sorttype : 'varchar(100)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne' ]}},
					 {name : 'installaddr',index : 'installaddr',fixed : true,width : 100,sorttype : 'varchar(100)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne' ]}},
					 {name : 'usedept',index : 'usedept',fixed : true,width : 100,hidden:true,sorttype : 'varchar(100)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne' ]}},
					 {name : 'usedeptname',index : 'usedeptname',fixed : true,width : 100,sorttype : 'varchar(100)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne' ]}}*/
					],
					
		processbtncol:2,//操作按钮放在第几个列
		isedit:false,//是否可编辑列表
		advsearch:true,
		multiselect:multiselect=="null"?false:multiselect
	});
		initgrid("device");
		if(multiselect){
			$("button:contains('清空选择')", window.parent.document).hide();
		}else{
			$("button:contains('清空选择')", window.parent.document).show();
		}
});

function onSave(){
	var data = [];
	var rowdata;
	if(multiselect == 'true'){//多选
		var selectedRowIds = $("#device_grid").jqGrid("getGridParam", "selarrrow");
		if (!selectedRowIds) {
			alertMsg("请至少选择一个节点!");
			return false;
		}
		for(var i=0;i<selectedRowIds.length;i++){
			rowdata = $("#device_grid").jqGrid("getRowData", selectedRowIds[i]);
			data.push(rowdata);
		}
	}else{//单选
		var selectedRowId = $("#device_grid").jqGrid("getGridParam", "selrow");
	    if (!selectedRowId) {
	    	alertMsg("请选择一个节点!");
	        return false;
	    }
	    rowdata = $("#device_grid").jqGrid("getRowData", selectedRowId);
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

