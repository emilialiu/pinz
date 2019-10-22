var addsaveurl = rootpath+"/webpage/biz/sc/weightparameter/doAddSave.action";
var modifysaveurl = rootpath+"/webpage/biz/sc/weightparameter/doModifySave.action";
var deletesaveurl = rootpath+"/webpage/biz/sc/weightparameter/delete.action";

//删除修改进入表单时传递参数
function getIDVal(){
		return {"bean.paraid" : paraid};
}

//给表单赋值
function setInfoData(_bizname,data){  
	var dataJson = eval(data);
	var rowData = dataJson[0];
    var consoleDlg = $("#"+_bizname+"_info");
	consoleDlg.find("#paraid").val(rowData.paraid);
	consoleDlg.find("#tdeptid").val(rowData.tdeptid);
	consoleDlg.find("#paracode").val(rowData.paracode);
	consoleDlg.find("#paraname").val(rowData.paraname);
	consoleDlg.find("#unit").val(rowData.unit);
	consoleDlg.find("#paravalue").val(rowData.paravalue);
	consoleDlg.find("#isdel").val(rowData.isdel);
	consoleDlg.find("#createid").val(rowData.createid);
	consoleDlg.find("#createdate").val(rowData.createdate);
	consoleDlg.find("#modifyid").val(rowData.modifyid);
	consoleDlg.find("#modifydate").val(rowData.modifydate);
	consoleDlg.find("#demo").val(rowData.demo);
}

//新增修改删除保存是获取表单参数
function getInfoData(_bizname){
    var consoleDlg = $("#"+_bizname+"_info");  
	var paraid = $.trim(consoleDlg.find("#paraid").val());
	var tdeptid = $.trim(consoleDlg.find("#tdeptid").val());
	var paracode = $.trim(consoleDlg.find("#paracode").val());
	var paraname = $.trim(consoleDlg.find("#paraname").val());
	var unit = $.trim(consoleDlg.find("#unit").val());
	var paravalue = $.trim(consoleDlg.find("#paravalue").val());
	var isdel = $.trim(consoleDlg.find("#isdel").val());
	var createid = $.trim(consoleDlg.find("#createid").val());
	var createdate = $.trim(consoleDlg.find("#createdate").val());
	var modifyid = $.trim(consoleDlg.find("#modifyid").val());
	var modifydate = $.trim(consoleDlg.find("#modifydate").val());
	var demo = $.trim(consoleDlg.find("#demo").val());
      
    return {   
        "bean.paraid" : paraid,
        "bean.tdeptid" : tdeptid,
        "bean.paracode" : paracode,
        "bean.paraname" : paraname,
        "bean.unit" : unit,
        "bean.paravalue" : paravalue,
        "bean.isdel" : isdel,
        "bean.createid" : createid,
        "bean.createdate" : createdate,
        "bean.modifyid" : modifyid,
        "bean.modifydate" : modifydate,
        "bean.demo" : demo
    };      
}
//根据后台数据，拼装grid行数据
function initGridRowData(_bizname,data){
	var dataJson = eval(data);
    var consoleDlg = $("#"+_bizname+"_info");  
	var tdeptid = $.trim(consoleDlg.find("#tdeptid").val());	
	var paracode = $.trim(consoleDlg.find("#paracode").val());	
	var paraname = $.trim(consoleDlg.find("#paraname").val());	
	var unit = $.trim(consoleDlg.find("#unit").val());	
	var paravalue = $.trim(consoleDlg.find("#paravalue").val());	
	var isdel = $.trim(consoleDlg.find("#isdel").val());	
	var createid = $.trim(consoleDlg.find("#createid").val());	
	var createdate = $.trim(consoleDlg.find("#createdate").val());	
	var modifyid = $.trim(consoleDlg.find("#modifyid").val());	
	var modifydate = $.trim(consoleDlg.find("#modifydate").val());	
	var demo = $.trim(consoleDlg.find("#demo").val());	
	var dataRow = { 			
	    _rowid : dataJson.paraid,// 从Server端得到系统分配的id
		paraid : dataJson.paraid,// 从Server端得到系统分配的id
		tdeptid : tdeptid,
		paracode : paracode,
		paraname : paraname,
		unit : unit,
		paravalue : paravalue,
		isdel : isdel,
		createid : createid,
		createdate : createdate,
		modifyid : modifyid,
		modifydate : modifydate,
		demo : demo
	};
	return dataRow;
}
