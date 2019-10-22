//$("#sys_role_info").on('domi.modifysave.before' , function() {alert("测试修改保存之前事件")});
//$("#sys_role_info").on('domi.modifysave.after' , function() {alert("测试修改保存之后事件")});
//删除修改进入表单时传递参数
function getGridParams(_bizname){
	var selectedRowId = $("#"+_bizname+"_grid").jqGrid("getGridParam", "selrow");  
    var str = $("#"+_bizname+"_grid").jqGrid("getRowData", selectedRowId); // 根据行ID，获取选中行的数据
	return {  
        "bean.borrowid" : str.borrowid  
    };  
}
//给表单赋值
function setInfoData(_bizname,data){  
	var dataJson = eval(data);
	var rowData = dataJson[0];
    var consoleDlg = $("#"+_bizname+"_info");  
    if(rowData!=null){
	    if(consoleDlg.find("#borrowid").length>0){
	    	consoleDlg.find("#borrowid").val(rowData.borrowid);  
	    }
	    if(consoleDlg.find("#borrowamount").length>0){
	    	consoleDlg.find("#borrowamount").val(rowData.borrowamount);  
	    }
	    if(consoleDlg.find("#cause").length>0){
	    	consoleDlg.find("#cause").val(rowData.cause); 
	    }
	    if(consoleDlg.find("#username").length>0){
	    	consoleDlg.find("#username").val(rowData.username); 
	    }
	    if(consoleDlg.find("#borrowdate").length>0){
	    	consoleDlg.find("#borrowdate").val(rowData.borrowdate); 
	    }
    }
    
}

//新增修改删除保存是获取表单参数
function getInfoData(_bizname){
    var consoleDlg = $("#"+_bizname+"_info");  
	var borrowid = $.trim(consoleDlg.find("#borrowid").val());  
    var borrowamount = $.trim(consoleDlg.find("#borrowamount").val());  
    var cause = $.trim(consoleDlg.find("#cause").val());   
    return {  
        "bean.borrowid" : borrowid,  
        "bean.borrowamount" : borrowamount,  
        "bean.cause" : cause
    };      
}
//根据后台数据，拼装grid行数据
function initGridRowData1(_bizname,data){
	var dataJson = eval(data);
    var consoleDlg = $("#"+_bizname+"_info");    
	var dataRow = {  
			borrowid : dataJson.borrowid,   // 从Server端得到系统分配的id  
			username:dataJson.username,
			statename:dataJson.statename,
			state:dataJson.state,
			borrowdate:dataJson.borrowdate,
			borrowamount : dataJson.borrowamount,  
			cause : dataJson.cause
        }; 
	return dataRow;
}
//根据后台数据，拼装grid行数据
function initGridRowData(_bizname,data){
	var dataJson = eval(data);
	var str = dataJson.id;
    var consoleDlg = $("#"+_bizname+"_info");    
    var borrowamount = $.trim(consoleDlg.find("#borrowamount").val());  
    var cause = $.trim(consoleDlg.find("#cause").val());   
	var dataRow = {  
			borrowid : str,   // 从Server端得到系统分配的id  
			borrowamount : borrowamount,  
			cause : cause
        }; 
	return dataRow;
}

$("#sys_role_func_btn_save").click(function(){
	var checkedNodes = BOTree_weishouyu.getCheckedNodes(true);
 	var length = checkedNodes.length;
 	var funcids = '';
 	for(var i = 0; i < length; i++){
 		if(!checkedNodes[i].isParent)
 			funcids += checkedNodes[i].id + ',';
 	}
 	funcids = funcids.substring(0, funcids.length-1);
 	if(funcids == ''){
 		alertMsg('请选择需要授予给角色的功能');
 		return;
 	}
 	$.ajax({
		url: rootpath+'/manager/sys/rolepopedom/add.action',
		data: { 'funccodes' : funcids, 'bean.roleid':$("#roleid").val() },
		cache: false,
		success: function(data){
			var dataJson = eval(data);
			if(dataJson.success){
				alertMsg('已成功授予角色功能');
				reloadTree();
			}else{
				parent.alertError(dataJson.errormessage);
			}
		},
		error: function(data) {
			parent.alertError('授予失败'); 
		}
	});
});

$("#sys_role_func_btn_remove").click(function(){
	
	var checkedNodes = BOTree_yishouyu.getCheckedNodes(true);
 	var length = checkedNodes.length;
 	var funcids = '';
 	for(var i = 0; i < length; i++){
 		if(!checkedNodes[i].isParent)
 			funcids += checkedNodes[i].id + ',';
 	}
 	funcids = funcids.substring(0, funcids.length-1);
 	if(funcids == ''){
 		alertMsg('请选择需要撤销的角色功能');
 		return;
 	}
 	$.ajax({
		url: rootpath+'/manager/sys/rolepopedom/delete.action',
		data: { 'funccodes' : funcids, 'bean.roleid':$("#roleid").val() },
		cache: false,
		success: function(data){
			var dataJson = eval(data);
			if(dataJson.success){
				alertMsg('已成功撤销角色功能');
				reloadTree();
			}else{
				parent.alertError(dataJson.errormessage);
			}
		},
		error: function(data) {
			alertMsg('撤销失败'); 
		}
	});
});

function reloadTree(){
	//初始化未授予功能权限的树
	reoladTree_weishouyu();
	//初始化已授予功能权限的树
	reoladTree_yishouyu();
}