//$("#biz_info").on('domi.modifysave.before' , function() {alert("测试修改保存之前事件")});
//$("#biz_info").on('domi.modifysave.after' , function() {alert("测试修改保存之后事件")});
//删除修改进入表单时传递参数
var addsaveurl = rootpath+"/manager/sys/user/doAddSave.action";
var modifysaveurl = rootpath+"/manager/sys/user/doModifySave.action";
var deletesaveurl = rootpath+"/manager/sys/user/delete.action";

//删除修改进入表单时传递参数
function getIDVal(){
    return {"bean.userid" : userid};
}

function getGridParams(_bizname){
	var selectedRowId = $("#"+_bizname+"_grid").jqGrid("getGridParam", "selrow");  
    var str = $("#"+_bizname+"_grid").jqGrid("getRowData", selectedRowId); // 根据行ID，获取选中行的数据
	return {  
        "bean.userid" : str.userid  
    };  
}

//新增修改删除保存是获取表单参数
function getInfoData(_bizname){
    var consoleDlg = $("#"+_bizname+"_info");  
	var userid = $.trim(consoleDlg.find("#userid").val());  
    var loginname = $.trim(consoleDlg.find("#loginname").val());  
    var username = $.trim(consoleDlg.find("#username").val());  
    var deptid = $.trim(consoleDlg.find("#deptid").val());  
    var pwd = $.trim(consoleDlg.find("#pwd").val());  
    var memo = $.trim(consoleDlg.find("#memo").val()); 
    return {  
        "bean.userid" : userid,  
        "bean.loginname" : loginname,  
        "bean.username" : username,  
        "bean.deptid" : deptid,
        "bean.loginpwd" : pwd,
        "bean.memo" : memo
    };      
}
//根据后台数据，拼装grid行数据
function initGridRowData(_bizname,data){
	var dataJson = eval(data);
    var consoleDlg = $("#"+_bizname+"_info");
    var loginname = $.trim(consoleDlg.find("#loginname").val());
    var username = $.trim(consoleDlg.find("#username").val());
	var dataRow = {
		_rowid : dataJson.userid,   // 从Server端得到系统分配的id
		userid : dataJson.userid,   // 从Server端得到系统分配的id  
		loginname : loginname,  
		username : username,  
		deptname : dataJson.deptname,  
		deptCode : dataJson.deptCode,  
		deptid : dataJson.deptid
    };
	return dataRow;
}
