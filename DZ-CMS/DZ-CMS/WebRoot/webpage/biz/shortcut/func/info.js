var addsaveurl = rootpath+"/webpage/biz/shortcut/func/doAddSave.action";
var modifysaveurl = rootpath+"/webpage/biz/shortcut/func/doModifySave.action";
var deletesaveurl = rootpath+"/webpage/biz/shortcut/func/delete.action";

//删除修改进入表单时传递参数
function getIDVal(){
		return {"bean.userid" : userid};
		return {"bean.funccode" : funccode};
}

//给表单赋值
function setInfoData(_bizname,data){  
	var dataJson = eval(data);
	var rowData = dataJson[0];
    var consoleDlg = $("#"+_bizname+"_info");
	consoleDlg.find("#userid").val(rowData.userid);
	consoleDlg.find("#funccode").val(rowData.funccode);
}

//新增修改删除保存是获取表单参数
function getInfoData(_bizname){
    var consoleDlg = $("#"+_bizname+"_info");  
	var userid = $.trim(consoleDlg.find("#userid").val());
	var funccode = $.trim(consoleDlg.find("#funccode").val());
      
    return {   
        "bean.userid" : userid,
        "bean.funccode" : funccode
    };      
}
//根据后台数据，拼装grid行数据
function initGridRowData(_bizname,data){
	var dataJson = eval(data);
    var consoleDlg = $("#"+_bizname+"_info");  
	var dataRow = { 			
	    _rowid : dataJson.userid,// 从Server端得到系统分配的id
		userid : dataJson.userid,// 从Server端得到系统分配的id
	    _rowid : dataJson.funccode,// 从Server端得到系统分配的id
		funccode : dataJson.funccode,// 从Server端得到系统分配的id
	};
	return dataRow;
}
