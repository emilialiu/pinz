//$("#sys_role_info").on('domi.modifysave.before' , function() {alert("测试修改保存之前事件")});
//$("#sys_role_info").on('domi.modifysave.after' , function() {alert("测试修改保存之后事件")});
var addsaveurl = rootpath+"/manager/sys/role/doAddSave.action";
var modifysaveurl = rootpath+"/manager/sys/role/doModifySave.action";
var deletesaveurl = rootpath+"/manager/sys/role/delete.action";

//删除修改进入表单时传递参数
function getIDVal(){
    return {"bean.roleid" : roleid};
}

//给表单赋值
function setInfoData(_bizname,data){  
	var dataJson = eval(data);
	var rowData = dataJson[0];
    var consoleDlg = $("#"+_bizname+"_info");   
    consoleDlg.find("#roleid").val(rowData.roleid);  
    consoleDlg.find("#rolename").val(rowData.rolename);  
    consoleDlg.find("#memo").val(rowData.memo);  
    consoleDlg.find("#deptid").val(rowData.deptid);  
    consoleDlg.find("#acginame").val(rowData.acginame); 
}

//新增修改删除保存是获取表单参数
function getInfoData(_bizname){
    var consoleDlg = $("#"+_bizname+"_info");  
	var roleid = $.trim(consoleDlg.find("#roleid").val());  
    var rolename = $.trim(consoleDlg.find("#rolename").val());  
    var memo = $.trim(consoleDlg.find("#memo").val());  
    var deptid = $.trim(consoleDlg.find("#deptid").val());  
    var acginame = $.trim(consoleDlg.find("#acginame").val());  
      
    return {  
        "bean.roleid" : roleid,  
        "bean.rolename" : rolename,  
        "bean.memo" : memo,  
        "bean.deptid" : deptid,  
        "bean.acginame" : acginame
    };      
}
//根据后台数据，拼装grid行数据
function initGridRowData(_bizname,data){
	var dataJson = eval(data);
    var consoleDlg = $("#"+_bizname+"_info");  
    var rolename = $.trim(consoleDlg.find("#rolename").val());  
    var memo = $.trim(consoleDlg.find("#memo").val());  
    var deptid = $.trim(consoleDlg.find("#deptid").val());  
    var acginame = $.trim(consoleDlg.find("#acginame").val()); 
	var dataRow = {
		_rowid : dataJson.roleid,   // 从Server端得到系统分配的id
        roleid : dataJson.roleid,   // 从Server端得到系统分配的id
        rolename : rolename,  
        memo : memo,  
        deptid : deptid,  
        acginame : acginame  
    };
	return dataRow;
}
