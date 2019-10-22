//$("#teamstaff_info").on('domi.modifysave.before' , function() {alert("测试修改保存之前事件")});
//$("#teamstaff_info").on('domi.modifysave.after' , function() {alert("测试修改保存之后事件")});
//删除修改进入表单时传递参数
function getGridParams(_bizname){
	var selectedRowId = $("#"+_bizname+"_grid").jqGrid("getGridParam", "selrow");  
    var str = $("#"+_bizname+"_grid").jqGrid("getRowData", selectedRowId); // 根据行ID，获取选中行的数据
	return {
        "bean.teamid" : str.teamid,
        "bean.staffid" : str.staffid
    };  
}
//给表单赋值
function setInfoData(_bizname,data){  
	var dataJson = eval(data);
	var rowData = dataJson[0];
    var consoleDlg = $("#"+_bizname+"_info");
	consoleDlg.find("#teamid").val(rowData.teamid);
	consoleDlg.find("#staffid").val(rowData.staffid);
}

//新增修改删除保存是获取表单参数
function getInfoData(_bizname){
    var consoleDlg = $("#"+_bizname+"_info");  
	var teamid = $.trim(consoleDlg.find("#teamid").val());
	var staffid = $.trim(consoleDlg.find("#staffid").val());
      
    return {   
        "bean.teamid" : teamid,
        "bean.staffid" : staffid
    };      
}
//根据后台数据，拼装grid行数据
function initGridRowData(_bizname,data){
	var dataJson = eval(data);
    var consoleDlg = $("#"+_bizname+"_info");  
	var dataRow = { 			
		teamid : dataJson.teamid,
		staffid : dataJson.staffid,
	}; 
	return dataRow;
}
