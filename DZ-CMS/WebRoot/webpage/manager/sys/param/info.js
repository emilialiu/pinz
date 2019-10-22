//$("#sys_param_info").on('domi.modifysave.before' , function() {alert("测试修改保存之前事件")});
var addsaveurl = rootpath+"/manager/sys/param/doAddSave.action";
var modifysaveurl = rootpath+"/manager/sys/param/doModifySave.action";
var deletesaveurl = rootpath+"/manager/sys/param/delete.action";

//删除修改进入表单时传递参数
function getIDVal(){
    return {"bean.paramcode" : paramcode};
}

//给表单赋值
function setInfoData(_bizname,data){  
	var dataJson = eval(data);
	var rowData = dataJson[0];
    var consoleDlg = $("#"+_bizname+"_info");
	consoleDlg.find("#paramcode").val(rowData.paramcode);
	consoleDlg.find("#paramname").val(rowData.paramname);
	consoleDlg.find("#paramvalue").val(rowData.paramvalue);
	consoleDlg.find("#deptid").val(rowData.deptid);
	consoleDlg.find("#memo").val(rowData.memo);
}

//新增修改删除保存是获取表单参数
function getInfoData(_bizname){
    var consoleDlg = $("#"+_bizname+"_info");  
	var paramcode = $.trim(consoleDlg.find("#paramcode").val());
	var paramname = $.trim(consoleDlg.find("#paramname").val());
	var paramvalue = $.trim(consoleDlg.find("#paramvalue").val());
	var deptid = $.trim(consoleDlg.find("#deptid").val());
	var memo = $.trim(consoleDlg.find("#memo").val());
      
    return {   
        "bean.paramcode" : paramcode,
        "bean.paramname" : paramname,
        "bean.paramvalue" : paramvalue,
        "bean.deptid" : deptid,
        "bean.memo" : memo
    };      
}
//根据后台数据，拼装grid行数据
function initGridRowData(_bizname,data){
	var dataJson = eval(data);
    var consoleDlg = $("#"+_bizname+"_info");  
	var paramname = $.trim(consoleDlg.find("#paramname").val());	
	var paramvalue = $.trim(consoleDlg.find("#paramvalue").val());	
	var deptid = $.trim(consoleDlg.find("#deptid").val());	
	var memo = $.trim(consoleDlg.find("#memo").val());	
	var dataRow = { 
	    _rowid : dataJson.paramcode,   // 从Server端得到系统分配的id
		paramcode : dataJson.paramcode,// 从Server端得到系统分配的id
		paramname : paramname,
		paramvalue : paramvalue,
		deptid : deptid,
		memo : memo
	}; 
	return dataRow;
}
