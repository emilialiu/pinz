$(function() {
	// grid参数
	$("#sys_shortcut_func_grid").data("gridOptions", {
		height : 529,//parent.getClientHeights(),
		url :  rootpath + '/webpage/biz/shortcut/func/list.action',
		colNames : [ 
					pub_operation,
					'用户ID',
					'功能id',
					shortcut_func_function,
					'orderno'
					],//列显示名称
		colModel : [ //	align:left,center,right,默认left;classes:列的css;datefmt:默认Y-m-d;defval:查询字段的默认值;editable:单元格是否可编辑,默认false;
					 //fixed:列宽是否固定不可变,默认false;search:在搜索模式下，定义此列是否可以作搜索列
					{name : 'processbtn',index:'', width:100, fixed:true, sortable:false, resize:false,search : false,formatter:btnformat},
						{name : 'userid',index : 'userid',fixed : false,width : 100,sorttype : 'varchar(40)',editable : false,hidden:true,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'funccode',index : 'funccode',fixed : false,width : 100,sorttype : 'varchar(40)',editable : false,hidden:true,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'funcname',index : 'funcname',fixed : true,width : 220,sorttype : 'varchar(40)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'orderno',index : 'orderno',fixed : false,width : 100,sorttype : 'int(11)',editable : false,hidden:true}
						],
		processbtncol:2,//操作按钮放在第几个列
		isedit:false,//是否可编辑列表
		advsearch:true,
		multiSort:true,
		viewurl : "/webpage/biz/shortcut/func/doModify.action",
		addurl : "/webpage/biz/shortcut/func/doAdd.action",
		modifyurl : "/webpage/biz/shortcut/func/doModifySave.action",
		deleteurl : "/webpage/biz/shortcut/func/delete.action"
		
	});
	initgrid("sys_shortcut_func");
	$("#sys_shortcut_func_grid_pager_left td").slice(0, 5).hide();
});

//删除修改进入表单时传递参数
function getGridParams(_bizname){
	var selectedRowId = $("#"+_bizname+"_grid").jqGrid("getGridParam", "selrow");  
    var str = $("#"+_bizname+"_grid").jqGrid("getRowData", selectedRowId); // 根据行ID，获取选中行的数据
		return {
			"bean.userid" : str.userid,
			"bean.funccode" : str.funccode
		};
}

function getParams(_bizname){
	var selectedRowId = $("#"+_bizname+"_grid").jqGrid("getGridParam", "selrow");  
    var str = $("#"+_bizname+"_grid").jqGrid("getRowData", selectedRowId); // 根据行ID，获取选中行的数据
	return "?bean.userid="+str.userid+"&bean.funccode="+str.funccode;
}

//取编辑grid列表数据
function getEditGridParams(_bizname,selectedRowId){
	var datalist = $("#"+_bizname+"_grid").jqGrid('getRowData', selectedRowId);
	return {  
			"bean.userid" : datalist.userid,
			"bean.funccode" : datalist.funccode
	}; 
}
function initEditGridRowData(_bizname,data,selectedRowId){
	var datalist = getEditGridParams(_bizname,selectedRowId);
	var dataJson = eval(data);
	return dataRow = {  
				userid : dataJson.id,
				funccode : dataJson.id,
	}; 
}

function btnformat(cellvalue,options,rowobj){
	del = "<div class='ui-pg-div ui-inline-del' style='float: left; cursor: pointer;display:block;' onclick=_doeditdelete('sys_shortcut_func','"+ options.rowId
	+ "')><span class='ui-icon ui-icon-trash'/></div>";
	up = "<div  style='float:left;cursor: pointer;margin-left: 5px;'  onclick=upOrderno('sys_shortcut_func','"+ options.rowId
	+ "')><span style='height:20px;width:20px;background-size: 20px;background-image:url(./up.png);background-repeat: no-repeat;display:block;margin-top: 3px;'/></div>";
	down = "<div  style='float:left;cursor: pointer;margin-left: 5px;' onclick=downOrderno('sys_shortcut_func','"+ options.rowId
	+ "')><span style='height:20px;width:20px;background-size: 20px;background-image:url(./down.png);background-repeat: no-repeat;display:block;margin-top: 3px;'/></div>";
	return del+up+down;
}

function upOrderno(bizname,rowid){
	if(rowid==1){
		return;
	}
	rowdata1 = $("#sys_shortcut_func_grid").jqGrid("getRowData", rowid);
	rowdata2 = $("#sys_shortcut_func_grid").jqGrid("getRowData", eval(rowid)-1);
	var params = { 
			"bean.funccode":rowdata1.funccode+','+rowdata2.funccode,
			"bean.orderno":rowdata2.orderno+','+rowdata1.orderno,
		};
	$.ajax({
		url : rootpath + '/webpage/biz/shortcut/func/changOrderno.action',
		cache : false,
		data : params,
		async: false,
		success : function(data) {
			  $("#sys_shortcut_func_grid").trigger("reloadGrid");
			  //selectone(eval(rowid)-1);
		},
		error : function(data) {  
			alertMsg("系统ajax交互错误 ");  
        }
	}); 
}

/*function selectone(rowid){
	$("#sys_shortcut_func_grid").setSelection(rowid,true);
}
*/
function downOrderno(bizname,rowid){
	var total=$("#sys_shortcut_func_grid").jqGrid('getGridParam', 'records'); 
	if(rowid==total){
		return;
	}
	rowdata1 = $("#sys_shortcut_func_grid").jqGrid("getRowData", rowid);
	rowdata2 = $("#sys_shortcut_func_grid").jqGrid("getRowData", eval(rowid)+1);
	var params = { 
			"bean.funccode":rowdata1.funccode+','+rowdata2.funccode,
			"bean.orderno":rowdata2.orderno+','+rowdata1.orderno,
		};
	$.ajax({
		url : rootpath + '/webpage/biz/shortcut/func/changOrderno.action',
		cache : false,
		data : params,
		async: false,
		success : function(data) {
			  $("#sys_shortcut_func_grid").trigger("reloadGrid");
			 // selectone(eval(rowid)+1);
		},
		error : function(data) {  
			alertMsg("系统ajax交互错误 ");  
        }
	}); 
}

function Editfunc(){
	var url = "/webpage/biz/shortcut/func/funcinfo.jsp";
	parent.openwin("sys_role_func",url,shortcut_func_commonFunction,450,540);
	parent.PageObject.itemMap['sys_role_func'].callback = function(no){
		addFuncCallBack(no);
	};
}

function addFuncCallBack(arr){
	var funccode='';
	for(var i=0;i<arr.length;i++){
		if(!arr[i].isParent){
			funccode+=(arr[i].id+',');
		}
	}
	funccode=funccode.substring(0,funccode.length-1);
	var params = { 
		"bean.funccode":funccode,
	};
	//ajax产生消息
	$.ajax({
		url : rootpath + '/webpage/biz/shortcut/func/addFunc.action',
		cache : false,
		data : params,
		async: false,
		success : function(data) {
			var info=$.trim(data);
            if(info=='n') { 
                //alertMsg("您没有配置作业地点!");  
            }else if(info=='y'){
            	 alertMsg(pub_success+" !");  
	             $("#sys_shortcut_func_grid").trigger("reloadGrid");
            }else {  
            	alertErrorMsg(dataJson.errormessage); 
            }  
		},
		error : function(data) {  
			alertMsg("系统ajax交互错误 ");  
        }
	}); 
}