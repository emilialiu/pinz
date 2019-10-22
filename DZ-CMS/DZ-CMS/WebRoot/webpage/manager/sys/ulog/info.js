//删除修改进入表单时传递参数
function getGridParams(_bizname){
	var selectedRowId = $("#"+_bizname+"_grid").jqGrid("getGridParam", "selrow");  
    var str = $("#"+_bizname+"_grid").jqGrid("getRowData", selectedRowId); // 根据行ID，获取选中行的数据
	return {  
        "bean.logid" : str.logid  
    };  
}
//给表单赋值
function setInfoData(_bizname,data){
	var dataJson = eval(data);
	var rowData = dataJson[0];
	console.info(rowData.useip);
    var consoleDlg = $("#"+_bizname+"_info");   
    consoleDlg.find("#deptname").val(rowData.deptname);  
    consoleDlg.find("#username").val(rowData.username);  
    consoleDlg.find("#useip").val(rowData.useip);  
    consoleDlg.find("#usemodule").val(rowData.usemodule);  
    consoleDlg.find("#usetime").val(rowData.usetime);
    consoleDlg.find("#useoperation").val(rowData.useoperation); 
}

//新增修改删除保存是获取表单参数
function getInfoData(_bizname){
    var consoleDlg = $("#"+_bizname+"_info");  
	var logid = $.trim(consoleDlg.find("#logid").val());  
    var deptname = $.trim(consoleDlg.find("#deptname").val());  
    var username = $.trim(consoleDlg.find("#username").val());  
    var useip = $.trim(consoleDlg.find("#useip").val());  
    var usemodule = $.trim(consoleDlg.find("#usemodule").val());
    var usetime = $.trim(consoleDlg.find("#usetime").val());
    var useoperation = $.trim(consoleDlg.find("#useoperation").val());
      
    return {  
        "bean.logid" : logid,  
        "bean.deptname" : deptname,  
        "bean.username" : username,  
        "bean.useip" : useip,  
        "bean.usemodule" : usemodule,
        "bean.usetime" : usetime,
        "bean.useoperation" : useoperation
    };      
}
//根据后台数据，拼装grid行数据
function initGridRowData(_bizname,data){
	var dataJson = eval(data);
    var consoleDlg = $("#"+_bizname+"_info");  
    var deptname = $.trim(consoleDlg.find("#deptname").val());  
    var username = $.trim(consoleDlg.find("#username").val());  
    var useip = $.trim(consoleDlg.find("#useip").val());  
    var usemodule = $.trim(consoleDlg.find("#usemodule").val()); 
    var usetime = $.trim(consoleDlg.find("#usetime").val()); 
    var useoperation = $.trim(consoleDlg.find("#useoperation").val()); 
	var dataRow = {  
			logid : dataJson.id,   // 从Server端得到系统分配的id  
			deptname : deptname,  
			username : username,  
			useip : useip,  
			usemodule : usemodule,
			usetime : usetime,
			useoperation : useoperation
        }; 
	return dataRow;
}