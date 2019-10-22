function getGridParams(_bizname){
	var selectedRowId = $("#"+_bizname+"_grid").jqGrid("getGridParam", "selrow");  
    var str = $("#"+_bizname+"_grid").jqGrid("getRowData", selectedRowId); // 根据行ID，获取选中行的数据
	return {  
        "bean.id" : str.id , 
        "bean.deploymentId":str.deploymentId
    };  
}
//给表单赋值
function setInfoData(_bizname,data){  
	var dataJson = eval(data);
	var rowData = dataJson[0];
    var consoleDlg = $("#"+_bizname+"_info");
	consoleDlg.find("#ProcessDefintionID").val(rowData.id);
	consoleDlg.find("#deploymentId").val(rowData.deploymentId);
	consoleDlg.find("#name").val(rowData.name);
	consoleDlg.find("#key").val(rowData.key);
	consoleDlg.find("#version").val(rowData.version);
	consoleDlg.find("#resourceName").val(rowData.resourceName);
	consoleDlg.find("#diagramResourceName").val(rowData.diagramResourceName);
	consoleDlg.find("#deploymentTime").val(rowData.deploymentTime);
}

//根据后台数据，拼装grid行数据
function initGridRowData(_bizname,data){
	var dataJson = eval(data);
    var consoleDlg = $("#"+_bizname+"_info");  
	var ProcessDefintionID = $.trim(consoleDlg.find("#ProcessDefintionID").val());	
	var Deploymentid = $.trim(consoleDlg.find("#Deploymentid").val());	
	var name = $.trim(consoleDlg.find("#name").val());	
	var key = $.trim(consoleDlg.find("#key").val());	
	var version = $.trim(consoleDlg.find("#version").val());	
	var resourceName = $.trim(consoleDlg.find("#resourceName").val());	
	var diagramResourceName = $.trim(consoleDlg.find("#diagramResourceName").val());	
	var deploymentTime = $.trim(consoleDlg.find("#deploymentTime").val());	
	var dataRow = { 			
	    ProcessDefintionID : dataJson.id,// 从Server端得到系统分配的id
	    Deploymentid : Deploymentid,
	    name:name,
	    key:key,
	    version:version,
	    resourceName:resourceName,
	    diagramResourceName:diagramResourceName,
	    deploymentTime:deploymentTime
	}; 
	return dataRow;
}
