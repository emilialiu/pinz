var addsaveurl = rootpath+"/webpage/biz/sc/techprocess/doAddSave.action";
var modifysaveurl = rootpath+"/webpage/biz/sc/techprocess/doModifySave.action";
var deletesaveurl = rootpath+"/webpage/biz/sc/techprocess/delete.action";

//删除修改进入表单时传递参数
function getIDVal(){
		return {"bean.techid" : techid};
		return {"bean.procid" : procid};
}

//给表单赋值
function setInfoData(_bizname,data){  
	var dataJson = eval(data);
	var rowData = dataJson[0];
    var consoleDlg = $("#"+_bizname+"_info");
	consoleDlg.find("#techid").val(rowData.techid);
	consoleDlg.find("#procid").val(rowData.procid);
	consoleDlg.find("#tdeptid").val(rowData.tdeptid);
	consoleDlg.find("#serialno").val(rowData.serialno);
	consoleDlg.find("#isplan").val(rowData.isplan);
	consoleDlg.find("#isaccept").val(rowData.isaccept);
	consoleDlg.find("#createid").val(rowData.createid);
	consoleDlg.find("#createdate").val(rowData.createdate);
	consoleDlg.find("#modifyid").val(rowData.modifyid);
	consoleDlg.find("#modifydate").val(rowData.modifydate);
	consoleDlg.find("#memo").val(rowData.memo);
}

//新增修改删除保存是获取表单参数
function getInfoData(_bizname){
    var consoleDlg = $("#"+_bizname+"_info");  
	var techid = $.trim(consoleDlg.find("#techid").val());
	var procid = $.trim(consoleDlg.find("#procid").val());
	var tdeptid = $.trim(consoleDlg.find("#tdeptid").val());
	var serialno = $.trim(consoleDlg.find("#serialno").val());
	var isplan = $.trim(consoleDlg.find("#isplan").val());
	var isaccept = $.trim(consoleDlg.find("#isaccept").val());
	var createid = $.trim(consoleDlg.find("#createid").val());
	var createdate = $.trim(consoleDlg.find("#createdate").val());
	var modifyid = $.trim(consoleDlg.find("#modifyid").val());
	var modifydate = $.trim(consoleDlg.find("#modifydate").val());
	var memo = $.trim(consoleDlg.find("#memo").val());
      
    return {   
        "bean.techid" : techid,
        "bean.procid" : procid,
        "bean.tdeptid" : tdeptid,
        "bean.serialno" : serialno,
        "bean.isplan" : isplan,
        "bean.isaccept" : isaccept,
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
	var tdeptid = $.trim(consoleDlg.find("#tdeptid").val());	
	var serialno = $.trim(consoleDlg.find("#serialno").val());	
	var isplan = $.trim(consoleDlg.find("#isplan").val());	
	var isaccept = $.trim(consoleDlg.find("#isaccept").val());	
	var createid = $.trim(consoleDlg.find("#createid").val());	
	var createdate = $.trim(consoleDlg.find("#createdate").val());	
	var modifyid = $.trim(consoleDlg.find("#modifyid").val());	
	var modifydate = $.trim(consoleDlg.find("#modifydate").val());	
	var memo = $.trim(consoleDlg.find("#memo").val());	
	var dataRow = { 			
	    _rowid : dataJson.techid,// 从Server端得到系统分配的id
		techid : dataJson.techid,// 从Server端得到系统分配的id
	    _rowid : dataJson.procid,// 从Server端得到系统分配的id
		procid : dataJson.procid,// 从Server端得到系统分配的id
		tdeptid : tdeptid,
		serialno : serialno,
		isplan : isplan,
		isaccept : isaccept,
		createid : createid,
		createdate : createdate,
		modifyid : modifyid,
		modifydate : modifydate,
		memo : memo
	};
	return dataRow;
}
