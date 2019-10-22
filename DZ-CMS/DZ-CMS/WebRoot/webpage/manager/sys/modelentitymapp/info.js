//$("#sys_modelentitymapp_info").on('domi.modifysave.before' , function() {alert("测试修改保存之前事件")});
//$("#sys_modelentitymapp_info").on('domi.modifysave.after' , function() {alert("测试修改保存之后事件")});
//删除修改进入表单时传递参数
function getGridParams(_bizname){
	var selectedRowId = $("#"+_bizname+"_grid").jqGrid("getGridParam", "selrow");  
    var str = $("#"+_bizname+"_grid").jqGrid("getRowData", selectedRowId); // 根据行ID，获取选中行的数据
	return {
        "bean.tablename" : str.tablename
    };  
}
//给表单赋值
function setInfoData(_bizname,data){  
	var dataJson = eval(data);
	var rowData = dataJson[0];
    var consoleDlg = $("#"+_bizname+"_info");
	consoleDlg.find("#tablename").val(rowData.tablename);
	consoleDlg.find("#entityclass").val(rowData.entityclass);
	consoleDlg.find("#dbkey").val(rowData.dbkey);
	if(rowData.isauto == 1){
		$("#dbkey").attr("disabled",false);
	}else{
		$("#dbkey").attr("disabled",true);
	}
	consoleDlg.find("#isauto").val(rowData.isauto);
}

//新增修改删除保存是获取表单参数
function getInfoData(_bizname){
    var consoleDlg = $("#"+_bizname+"_info");  
	var tablename = $.trim(consoleDlg.find("#tablename").val());
	var entityclass = $.trim(consoleDlg.find("#entityclass").val());
	var dbkey = $.trim(consoleDlg.find("#dbkey").val());
	var isauto = $.trim(consoleDlg.find("#isauto").val());
    return {
        "bean.tablename" : tablename,
        "bean.entityclass" : entityclass,
        "bean.dbkey" : dbkey,
        "bean.isauto" : isauto
    };      
}
//根据后台数据，拼装grid行数据
function initGridRowData(_bizname,data){
	var dataJson = eval(data);
    var consoleDlg = $("#"+_bizname+"_info");  
	var entityclass = $.trim(consoleDlg.find("#entityclass").val());	
	var isauto = $.trim(consoleDlg.find("#isauto").val());	
	if(isauto == 1){
		var dbkey = $.trim(consoleDlg.find("#dbkey").val());
	}else{
		var dbkey = "";
	}	
	var dataRow = { 			
		tablename : dataJson.id,// 从Server端得到系统分配的id
		entityclass : entityclass,
		dbkey : dbkey,
		isauto : isauto
	}; 
	return dataRow;
}

//是否自动生成列值选择控制自动键列是否显示
function changeDbkey(value){
	if(value == 1){
		$("#dbkey").attr("disabled",false);
	}else{
		$("#dbkey").attr("disabled",true);
	}
}
