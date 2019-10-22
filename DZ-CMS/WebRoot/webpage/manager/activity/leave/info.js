//给表单赋值
function setInfoData(_bizname,data,taskId){  
	var dataJson = eval(data);
	var rowData = dataJson[0];
    var consoleDlg = $("#"+_bizname+"_info");
	consoleDlg.find("#username").val(rowData.username);
	consoleDlg.find("#leaveType").val(rowData.leaveType);
	consoleDlg.find("#applyTime").val(rowData.applyTime);
	consoleDlg.find("#startTime").val(rowData.startTime);
	consoleDlg.find("#endTime").val(rowData.endTime);
	consoleDlg.find("#reason").val(rowData.reason);
	consoleDlg.find("#taskId").val(taskId);
}
//新增修改删除保存是获取表单参数
function getInfoData(_bizname){
    var consoleDlg = $("#"+_bizname+"_info");  
	var leaveType = $.trim(consoleDlg.find("#leaveType").val());  
    var startTime = $.trim(consoleDlg.find("#startTime").val());  
    var endTime = $.trim(consoleDlg.find("#endTime").val());  
    var reason = $.trim(consoleDlg.find("#reason").val());  
    var realityStartTime = $.trim(consoleDlg.find("#realityStartTime").val());  
    var realityEndTime = $.trim(consoleDlg.find("#realityEndTime").val());  
    return {  
        "bean.leaveType" : leaveType,  
        "bean.startTime" : startTime,  
        "bean.endTime" : endTime,  
        "bean.reason" : reason,
        "bean.realityStartTime":realityStartTime,
        "bean.realityEndTime":realityEndTime
    };      
}
function getGridParams(_bizname){
	var selectedRowId = $("#"+_bizname+"_grid").jqGrid("getGridParam", "selrow");  
    var str = $("#"+_bizname+"_grid").jqGrid("getRowData", selectedRowId); // 根据行ID，获取选中行的数据
	return {  
        "modelId" : str.id 
    };  
}
//根据后台数据，拼装grid行数据
function initGridRowData(_bizname,data){
	var dataJson = eval(data);
}