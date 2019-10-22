$(function() {
	// grid参数
	$("#sys_dicttype_grid").data("gridOptions", {
		height : parent.getClientHeights(),
		url :  rootpath + '/manager/sys/dicttype/typelist.action',
		colNames : [ pub_operation, sys_dict_number, sys_dict_name, qt_assaysample_memo, sys_dict_whether ],//列显示名称
		colModel : [ //	align:left,center,right,默认left;classes:列的css;datefmt:默认Y-m-d;defval:查询字段的默认值;editable:单元格是否可编辑,默认false;
					 //fixed:列宽是否固定不可变,默认false;search:在搜索模式下，定义此列是否可以作搜索列
					 {name : 'processbtn',index:'', width:80, fixed:false, sortable:false, resize:false,search : false},
					 {name : 'code',index :'dicttypeid',width : 100,fixed : false,sorttype : "string",editable : true,searchoptions : {sopt : [ 'cn', 'eq', 'ne' ]}}, 
					 {name : 'name',index : 'name',fixed : false,width : 120,sorttype : 'string',editable : true,searchoptions : {sopt : [ 'cn', 'eq', 'ne' ]}}, 
					 {name : 'remark',index : 'remark',fixed : false,width : 150,sorttype : 'string',editable : true,searchoptions : {sopt : [ 'cn', 'eq', 'ne' ]}}, 
					 {name:'canmodify',index:'canmodify', width:70,fixed:false, editable: true,edittype:"checkbox",search : false,editoptions: {value:"1:0"},unformat: aceSwitch,formatter:sfformatter}
					 ],
		onSelectRow: function(rowid, status){
			var rowData = $("#sys_dicttype_grid").jqGrid('getRowData',rowid);
			reloadByType(rowData.code);
		},			 
		processbtncol:2,//第几个列
		isedit:false,//是否可编辑列表
		advsearch:true,
		exportexcellocal:false,
		infowidth:800,
		viewurl : "/manager/sys/dicttype/doModify.action",
		addurl : "/manager/sys/dicttype/doAdd.action",
		modifyurl : "/manager/sys/dicttype/doModify.action",
		deleteurl : "/manager/sys/dicttype/deletetype.action"
	});
	initgrid("sys_dicttype");		
	function aceSwitch( cellvalue, options, cell ) {
		setTimeout(function(){
			$(cell) .find('input[type=checkbox]')
				.addClass('ace ace-switch ace-switch-lt')
				.after('<span class="lbl"></span>');
		}, 0);
	}
	function sfformatter(cellvalue,options,rowobj){
		if(cellvalue==1){
			return '是';
		}else{
			return '否';
		}
	}
	// grid参数
	$("#sys_dict_grid").data("gridOptions", {
		height : parent.getClientHeights(),
		url :  rootpath + '/manager/sys/dict/dictlist.action',
		colNames : [ pub_operation, sys_dict_resourceNumber, sys_dict_resourceType,  sys_dict_resourceName, sys_dict_description,sys_dict_whether,'序号','父ID' ],//列显示名称
		colModel : [ //	align:left,center,right,默认left;classes:列的css;datefmt:默认Y-m-d;defval:查询字段的默认值;editable:单元格是否可编辑,默认false;
					 //fixed:列宽是否固定不可变,默认false;search:在搜索模式下，定义此列是否可以作搜索列
					 {name : 'processbtn',index:'',  fixed:false, sortable:false, resize:false,search : false},
					 {name : 'code',index : 'dictid',fixed : false,sorttype : "int",editable : false,search:false}, 
					 {name : 'typeid',index : 'dicttypeid',fixed : false,sorttype : "string",editable : false,search:false,hidden:true},
					 {name : 'paramname',index : 'paramname',fixed : false,sorttype : 'string',editable : true,search:false}, 
					 {name:'paramremark',index:'paramremark',fixed : false,sorttype : 'string',editable : true,search:false},
					 {name:'canmodify',index:'canmodify',fixed : false,sorttype : 'int',editable : true,search:false,edittype:"select",formatter:sfformatter, editoptions:{value:"1:是;0:否"}},
					 {name : 'orderno',index : 'orderno',fixed : false,sorttype : "int",editable : false,search:false,hidden:true},
					 {name : 'parentid',index : 'parentid',fixed : false,sorttype : "string",editable : false,search:false,hidden:true}
					],
		processbtncol:2,//第几个列
		isedit:false,//是否可编辑列表
		advsearch:false,
		exportexcellocal:false,
		viewurl : "/manager/sys/dict/doModify.action",
		addurl : "/manager/sys/dict/doAdd.action",
		modifyurl : "/manager/sys/dict/doModify.action",
		deleteurl : "/manager/sys/dict/deletedict.action"
	});
	initgrid("sys_dict");
	function reloadByType(code){
		jQuery("#sys_dict_grid").setGridParam({url:rootpath + '/manager/sys/dict/dictlist.action?bean.dicttypeid='+code}).trigger("reloadGrid");
	}
});

//删除修改进入表单时传递参数
function getGridParams(_bizname){
	if(_bizname=='sys_dict'){
		var selectedRowId = $("#"+_bizname+"_grid").jqGrid("getGridParam", "selrow");  
	    var str = $("#"+_bizname+"_grid").jqGrid("getRowData", selectedRowId); // 根据行ID，获取选中行的数据
	    return {"bean.code" : str.code};
	}else if(_bizname=='sys_dicttype'){
		var selectedRowId = $("#"+_bizname+"_grid").jqGrid("getGridParam", "selrow");  
	    var str = $("#"+_bizname+"_grid").jqGrid("getRowData", selectedRowId); // 根据行ID，获取选中行的数据
	    return {"bean.code" : str.code};
	}
}

function getParams(_bizname){
	if(_bizname=='sys_dict'){
		var selectedRowId = $("#"+_bizname+"_grid").jqGrid("getGridParam", "selrow");  
	    var str = $("#"+_bizname+"_grid").jqGrid("getRowData", selectedRowId); // 根据行ID，获取选中行的数据
	    return "?bean.code="+str.code;
	}else if(_bizname=='sys_dicttype'){
		var selectedRowId = $("#"+_bizname+"_grid").jqGrid("getGridParam", "selrow");  
	    var str = $("#"+_bizname+"_grid").jqGrid("getRowData", selectedRowId); // 根据行ID，获取选中行的数据
	    return "?bean.code="+str.code;
}
}
var _openDialog4Adding = function(_bizname) {
	if(_bizname=='sys_dict'){
		var selectedRowId = $("#sys_dicttype_grid").jqGrid("getGridParam", "selrow");
		if(selectedRowId==''||selectedRowId==null){
			alertMsg(sys_dict_select+" !");
			return;
		}
		var rowData = $("#sys_dicttype_grid").jqGrid('getRowData',selectedRowId);
		var url = $("#"+_bizname+"_grid").data("gridOptions").addurl+"?bean.typeid="+rowData.code;
		parent.createwindow(_bizname,url,button_add,_formw,_formh,true);
	}else{
		var url = $("#"+_bizname+"_grid").data("gridOptions").addurl;
		parent.createwindow(_bizname,url,button_add,_formw,_formh,true);
	}
};

function checkDatas(_bizname){
	var selectedRowId = $("#"+_bizname+"_grid").jqGrid("getGridParam", "selrow");  
	var rowData = $("#"+_bizname+"_grid").jqGrid('getRowData',selectedRowId);
	var canmodify = rowData.canmodify;
    if(canmodify=='否'){
    	alertMsg(dmmes_pub_modify+" !");
		return;
    }
    if (!selectedRowId) {  
    	alertMsg(dmmes_pub_operation+" !");  
        return false;  
    } else {
    	return true;
    }
}

var _openDialog4Modifying = function(_bizname) {  
	var flag = checkDatas(_bizname);
	if(!flag)return;
	var url = $("#"+_bizname+"_grid").data("gridOptions").viewurl;
	parent.createwindow(_bizname,url+getParams(_bizname)+"&actiontype=modify","修改",_formw,_formh,true);
};  

//取编辑grid列表数据
function getEditGridParams(_bizname,selectedRowId){
	if(_bizname=='sys_dicttype'){
		var datalist = $("#"+_bizname+"_grid").jqGrid('getRowData', selectedRowId);
		return {
	        "bean.code" : datalist.code,  
	        "bean.name" : datalist.name,  
	        "bean.remark" : datalist.remark,
	        "bean.canmodify" : datalist.canmodify=='否'?"0":"1"
	    };
	}else{
		var datalist = $("#"+_bizname+"_grid").jqGrid('getRowData', selectedRowId);
		return {
	        "bean.code" : datalist.code,  
	        "bean.paramname" : datalist.paramname,
	        "bean.paramremark" : datalist.paramremark,
	        "bean.canmodify" : datalist.canmodify=='否'?"0":"1",
	        "bean.parentid" : datalist.parentid,
	        "bean.orderno" : datalist.orderno,
	        "bean.typeid" : datalist.typeid
	    }; 
	}
}
function initEditGridRowData(_bizname,data,selectedRowId){
	if(_bizname=='sys_dicttype'){
		var datalist = getEditGridParams(_bizname,selectedRowId);
		var dataJson = eval(data);
		return dataRow = {
			code : datalist.code,
			name : datalist.name,  
            remark : datalist.remark,
            canmodify : datalist.canmodify
        }; 
	}else{
		var datalist = getEditGridParams(_bizname,selectedRowId);
		var dataJson = eval(data);
		return dataRow = {
			code : datalist.code,
			typeid : datalist.typeid,  
			paramname : datalist.paramname,
			paramremark : datalist.paramremark,
			orderno :datalist.orderno,
			canmodify : datalist.canmodify,
			parentid : datalist.parentid
		};
	}
}
