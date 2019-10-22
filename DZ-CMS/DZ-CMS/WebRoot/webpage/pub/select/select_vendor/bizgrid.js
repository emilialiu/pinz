$(function() {
	$("#t_dm_vendor_grid").data("gridOptions", {
		height : 272,//parent.getClientHeights(),
		url :  rootpath + '/webpage/biz/dm/vendor/list.action',
		colNames : [ 
					'供应商ID',
					'供应商名称',
					'合作起始时间',
					'运营状况',
					'联系人',
					'联系电话',
					'地址'
					],//列显示名称
		colModel : [ //	align:left,center,right,默认left;classes:列的css;datefmt:默认Y-m-d;defval:查询字段的默认值;editable:单元格是否可编辑,默认false;
					 //fixed:列宽是否固定不可变,默认false;search:在搜索模式下，定义此列是否可以作搜索列
						{name : 'vendorid',index : 'vendorid',fixed : true,width : 100,sorttype : 'varchar(40)',editable : false,hidden:true},
						{name : 'vendorname',index : 'vendorname',fixed : true,width : 100,sorttype : 'varchar(100)',editable : true,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'startdate',index : 'startdate',fixed : true,width : 100,sorttype : 'date',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'operatestatus',index : 'operatestatus',fixed : true,width : 100,sorttype : 'varchar(500)',editable : true,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'contact',index : 'contact',fixed : true,width : 100,sorttype : 'varchar(40)',editable : true,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'contactnum',index : 'contactnum',fixed : true,width : 100,sorttype : 'varchar(40)',editable : true,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'address',index : 'address',fixed : true,width : 100,sorttype : 'varchar(200)',editable : true,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}}
					],
		processbtncol:2,//操作按钮放在第几个列
		isedit:false,//是否可编辑列表
		advsearch:true,
		multiselect:multiselect=="null"?false:multiselect
		
	});
		initgrid("t_dm_vendor");
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
		var selectedRowIds = $("#t_dm_vendor_grid").jqGrid("getGridParam", "selarrrow");
		if (!selectedRowIds) {
			alertMsg("请至少选择一个节点!");
			return false;
		}
		for(var i=0;i<selectedRowIds.length;i++){
			rowdata = $("#t_dm_vendor_grid").jqGrid("getRowData", selectedRowIds[i]);
			data.push(rowdata);
		}
	}else{//单选
		var selectedRowId = $("#t_dm_vendor_grid").jqGrid("getGridParam", "selrow");
	    if (!selectedRowId) {
	    	alertMsg("请选择一个节点!");
	        return false;
	    }
	    rowdata = $("#t_dm_vendor_grid").jqGrid("getRowData", selectedRowId);
		data.push(rowdata);
	}
	try{
		parent.PageObject.itemMap['t_dm_vendor_select'].callback(data);
	}catch(e){
		parent.closewin('t_dm_vendor_select');
	}
	parent.closewin('t_dm_vendor_select');
}
function uncheck() {
	parent.PageObject.itemMap['t_dm_vendor_select'].callback();
	parent.closewin('t_dm_vendor_select');
}

