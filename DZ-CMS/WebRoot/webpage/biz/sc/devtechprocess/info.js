var addsaveurl = rootpath+"/webpage/biz/sc/devtechprocess/doAddSave.action";
var modifysaveurl = rootpath+"/webpage/biz/sc/devtechprocess/doModifySave.action";
var deletesaveurl = rootpath+"/webpage/biz/sc/devtechprocess/delete.action";

//删除修改进入表单时传递参数
function getIDVal(){
		return {"bean.procid" : procid};
		return {"bean.devid" : devid};
}

//给表单赋值
function setInfoData(_bizname,data){  
	var dataJson = eval(data);
	var rowData = dataJson[0];
    var consoleDlg = $("#"+_bizname+"_info");
	consoleDlg.find("#procid").val(rowData.procid);
	consoleDlg.find("#devid").val(rowData.devid);
	consoleDlg.find("#tdeptid").val(rowData.tdeptid);
}

//新增修改删除保存是获取表单参数
function getInfoData(_bizname){
    var consoleDlg = $("#"+_bizname+"_info");  
	var procid = $.trim(consoleDlg.find("#procid").val());
	var devid = $.trim(consoleDlg.find("#devid").val());
	var tdeptid = $.trim(consoleDlg.find("#tdeptid").val());
      
    return {   
        "bean.procid" : procid,
        "bean.devid" : devid,
        "bean.tdeptid" : tdeptid
    };      
}
//根据后台数据，拼装grid行数据
function initGridRowData(_bizname,data){
	var dataJson = eval(data);
    var consoleDlg = $("#"+_bizname+"_info");  
	var tdeptid = $.trim(consoleDlg.find("#tdeptid").val());	
	var dataRow = { 			
	    _rowid : dataJson.procid,// 从Server端得到系统分配的id
		procid : dataJson.procid,// 从Server端得到系统分配的id
	    _rowid : dataJson.devid,// 从Server端得到系统分配的id
		devid : dataJson.devid,// 从Server端得到系统分配的id
		tdeptid : tdeptid
	};
	return dataRow;
}
