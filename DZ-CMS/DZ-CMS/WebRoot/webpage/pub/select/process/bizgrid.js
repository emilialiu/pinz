$(function() {
$("#process_grid").data("gridOptions", {
		height : 272,
		url :  rootpath + '/webpage/biz/sc/process/list.action?bean.techid='+techid,
		colNames : [ 
					'工序ID',
					sc_techprocess_processname,
					'上级工序id',
					sc_techprocess_parentprocess
					],//列显示名称
		colModel : [ //	align:left,center,right,默认left;classes:列的css;datefmt:默认Y-m-d;defval:查询字段的默认值;editable:单元格是否可编辑,默认false;
					 //fixed:列宽是否固定不可变,默认false;search:在搜索模式下，定义此列是否可以作搜索列
		            {name : 'procid',index : 'procid',width : 60,sorttype : "varchar(40)",editable : true,hidden : true},
					{name : 'proctname',index : 'proctname',fixed : true,width : 100,sorttype : 'varchar(100)',editable : true,searchoptions : {sopt : [ 'cn', 'eq', 'ne']}},
					{name : 'parentid',index : 'parentid',fixed : true,width : 100,sorttype : 'varchar(40)',hidden:true,editable : true},
					{name : 'parentname',index : 'parentname',fixed : true,width : 100,sorttype : 'varchar(40)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne' ]}},
					],
		processbtncol:2,//操作按钮放在第几个列
		isedit:false,//是否可编辑列表
		advsearch:true,
		multiselect:multiselect=="null"?false:multiselect
	});
		initgrid("process");
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
		var selectedRowIds = $("#process_grid").jqGrid("getGridParam", "selarrrow");
		if (!selectedRowIds) {
			alertMsg("请至少选择一个节点!");
			return false;
		}
		for(var i=0;i<selectedRowIds.length;i++){
			rowdata = $("#process_grid").jqGrid("getRowData", selectedRowIds[i]);
			data.push(rowdata);
		}
	}else{//单选
		var selectedRowId = $("#process_grid").jqGrid("getGridParam", "selrow");
	    if (!selectedRowId) {
	    	alertMsg("请选择一个节点!");
	        return false;
	    }
	    rowdata = $("#process_grid").jqGrid("getRowData", selectedRowId);
		data.push(rowdata);
	}
	try{
		parent.PageObject.itemMap['techprocess_selectgx'].callback(data);
	}catch(e){
		parent.closewin('techprocess_selectgx');
	}
	parent.closewin('techprocess_selectgx');
}
function uncheck() {
	parent.PageObject.itemMap['techprocess_selectgx'].callback([{"paramname":"","code":""}]);
	parent.closewin('techprocess_selectgx');
}

