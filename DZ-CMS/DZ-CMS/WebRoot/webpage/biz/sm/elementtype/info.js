//$("#elementtype_info").on('domi.modifysave.before' , function() {alert("测试修改保存之前事件")});
//$("#elementtype_info").on('domi.modifysave.after' , function() {alert("测试修改保存之后事件")});
//删除修改进入表单时传递参数
var addsaveurl = rootpath+"/biz/sm/elementtype/doAddSave.action";
var modifysaveurl = rootpath+"/biz/sm/elementtype/doModifySave.action";
var deletesaveurl = rootpath+"/biz/sm/elementtype/delete.action";

//删除修改进入表单时传递参数
function getIDVal(){
    return {"bean.eletypeid" : eletypeid};
}

//给表单赋值
function setInfoData(_bizname,data){  
	var dataJson = eval(data);
	var rowData = dataJson[0];
    var consoleDlg = $("#"+_bizname+"_info");
	consoleDlg.find("#eletypeid").val(rowData.eletypeid);
	consoleDlg.find("#eletypekind").val(rowData.eletypekind);
	consoleDlg.find("#eletypename").val(rowData.eletypename);
	consoleDlg.find("#deptid").val(rowData.deptid);
	consoleDlg.find("#createid").val(rowData.createid);
	consoleDlg.find("#createdate").val(rowData.createdate);
	consoleDlg.find("#modifyid").val(rowData.modifyid);
	consoleDlg.find("#modifydate").val(rowData.modifydate);
	consoleDlg.find("#memo").val(rowData.memo);
}
//新增修改删除保存是获取表单参数
function getInfoData(_bizname){	
	var consoleDlg = $("#"+_bizname+"_info");  
	var eletypeid = $.trim(consoleDlg.find("#eletypeid").val());
	var eletypekind = $.trim(consoleDlg.find("#eletypekind").val());
	var eletypename = $.trim(consoleDlg.find("#eletypename").val());
	var deptid = $.trim(consoleDlg.find("#deptid").val());
	var createid = $.trim(consoleDlg.find("#createid").val());
	var createdate = $.trim(consoleDlg.find("#createdate").val());
	var modifyid = $.trim(consoleDlg.find("#modifyid").val());
	var modifydate = $.trim(consoleDlg.find("#modifydate").val());
	var memo = $.trim(consoleDlg.find("#memo").val());
    return {
        "bean.eletypeid" : eletypeid,
        "bean.eletypekind" : eletypekind,
        "bean.eletypename" : eletypename,
        "bean.deptid" : deptid,
        "bean.createid" : createid,
        "bean.createdate" : createdate,
        "bean.modifyid" : modifyid,
        "bean.modifydate" : modifydate,
        "bean.memo" : memo
    };
}
//根据后台数据，拼装grid行数据
function initGridRowData(_bizname,data){
	var dataJson = eval(data);
    var consoleDlg = $("#"+_bizname+"_info");  
	var eletypekind = $.trim($("#eletypekind option:selected").text());	
	var eletypename = $.trim($("#eletypekind option:selected").text());	
	var deptid = $.trim(consoleDlg.find("#deptid").val());	
	var createid = $.trim(consoleDlg.find("#createid").val());	
	var createdate = $.trim(consoleDlg.find("#createdate").val());	
	var modifyid = $.trim(consoleDlg.find("#modifyid").val());	
	var modifydate = $.trim(consoleDlg.find("#modifydate").val());	
	var memo = $.trim(consoleDlg.find("#memo").val());	
	var dataRow = { 			
		eletypeid : dataJson.id,// 从Server端得到系统分配的id
		eletypekind : eletypekind,
		eletypename : eletypename,
		deptid : deptid,
		createid : createid,
		createdate : createdate,
		modifyid : modifyid,
		modifydate : modifydate,
		memo : memo
	}; 
	return dataRow;
}
