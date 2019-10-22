$(function() {
	function sfformatter(cellvalue,options,rowobj){
		if(cellvalue==1){
			return '是';
		}else{
			return '否';
		}
	}
$("#sys_dict_grid").data("gridOptions", {
		height : 272,
		url :  rootpath + '/manager/sys/dict/dictlist.action',
		colNames : [ 
		            sys_dict_resourceNumber, sys_dict_resourceType, sys_dict_resourceName, sys_dict_description, sys_dict_whether,'序号','父ID' 
					],//列显示名称
		colModel : [ //	align:left,center,right,默认left;classes:列的css;datefmt:默认Y-m-d;defval:查询字段的默认值;editable:单元格是否可编辑,默认false;
					 //fixed:列宽是否固定不可变,默认false;search:在搜索模式下，定义此列是否可以作搜索列
					 {name : 'code',index : 'dictid',fixed : false,sorttype : "int",editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne' ]}}, 
					 {name : 'typeid',index : 'dicttypeid',fixed : false,sorttype : "string",editable : false,search:false,hidden:true},
					 {name : 'paramname',index : 'paramname',fixed : false,sorttype : 'string',editable : true,searchoptions : {sopt : [ 'cn', 'eq', 'ne' ]}}, 
					 {name:'paramremark',index:'paramremark',fixed : false,sorttype : 'string',editable : true,search:false},
					 {name:'canmodify',index:'canmodify',fixed : false,sorttype : 'int',editable : true,search:false,edittype:"select",formatter:sfformatter,editoptions:{value:"1:是;0:否"}},
					 {name : 'orderno',index : 'orderno',fixed : false,sorttype : "int",editable : false,search:false,hidden:true},
					 {name : 'parentid',index : 'parentid',fixed : false,sorttype : "string",editable : false,search:false,hidden:true}
					],
		processbtncol:2,//操作按钮放在第几个列
		isedit:false,//是否可编辑列表
		advsearch:true,
		ondblClickRow:function(data){
			onSave();
		},
		multiselect:multiselect=="null"?false:multiselect
	});
		initgrid("sys_dict");
//		if(multiselect){
//			$("button:contains('清空选择')", window.parent.document).hide();
//		}else{
//			$("button:contains('清空选择')", window.parent.document).show();
//		}
});

function onSave(){
	var data = [];
	var rowdata;
	if(multiselect == 'true'){//多选
		var selectedRowIds = $("#sys_dict_grid").jqGrid("getGridParam", "selarrrow");
		if (!selectedRowIds) {
			alertMsg("请至少选择一个节点!");
			return false;
		}
		for(var i=0;i<selectedRowIds.length;i++){
			rowdata = $("#sys_dict_grid").jqGrid("getRowData", selectedRowIds[i]);
			data.push(rowdata);
		}
	}else{//单选
		var selectedRowId = $("#sys_dict_grid").jqGrid("getGridParam", "selrow");
	    if (!selectedRowId) {
	    	alertMsg("请选择一个节点!");
	        return false;
	    }
	    rowdata = $("#sys_dict_grid").jqGrid("getRowData", selectedRowId);
		data.push(rowdata);
	}
	try{
		parent.PageObject.itemMap['selectsys_dict'].callback(data);
	}catch(e){
		parent.closewin('selectsys_dict');
	}
	parent.closewin('selectsys_dict');
}
function uncheck() {
	parent.PageObject.itemMap['selectsys_dict'].callback([{"paramname":"","code":""}]);
	parent.closewin('selectsys_dict');
}

