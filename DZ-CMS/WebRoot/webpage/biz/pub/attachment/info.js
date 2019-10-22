var addsaveurl = rootpath+"/webpage/biz/pub/attachment/doAddSave.action";
var modifysaveurl = rootpath+"/webpage/biz/pub/attachment/doModifySave.action";
var deletesaveurl = rootpath+"/webpage/biz/pub/attachment/delete.action";

//删除修改进入表单时传递参数
function getIDVal(){
		return {"bean.attachmentid" : attachmentid};
}

//给表单赋值
function setInfoData(_bizname,data){  
	var dataJson = eval(data);
	var rowData = dataJson[0];
    var consoleDlg = $("#"+_bizname+"_info");
	consoleDlg.find("#attachmentid").val(rowData.attachmentid);
	consoleDlg.find("#deptid").val(rowData.deptid);
	consoleDlg.find("#bzstype").val(rowData.bzstype);
	consoleDlg.find("#businessid").val(rowData.businessid);
	consoleDlg.find("#attachmenttype").val(rowData.attachmenttype);
	consoleDlg.find("#attachmentname").val(rowData.attachmentname);
	consoleDlg.find("#attachmenturl").val(rowData.attachmenturl);
	consoleDlg.find("#yearvalue").val(rowData.yearvalue);
	consoleDlg.find("#createid").val(rowData.createid);
	consoleDlg.find("#createdate").val(rowData.createdate);
	consoleDlg.find("#modifyid").val(rowData.modifyid);
	consoleDlg.find("#modifydate").val(rowData.modifydate);
	consoleDlg.find("#memo").val(rowData.memo);
	consoleDlg.find("#infoid").val(rowData.infoid);
	consoleDlg.find("#filepath").val(rowData.filepath);
	consoleDlg.find("#filename").val(rowData.filename);
}

//新增修改删除保存是获取表单参数
function getInfoData(_bizname){
    var consoleDlg = $("#"+_bizname+"_info");  
	var attachmentid = $.trim(consoleDlg.find("#attachmentid").val());
	var deptid = $.trim(consoleDlg.find("#deptid").val());
	var bzstype = $.trim(consoleDlg.find("#bzstype").val());
	var businessid = $.trim(consoleDlg.find("#businessid").val());
	var attachmenttype = $.trim(consoleDlg.find("#attachmenttype").val());
	var attachmentname = $.trim(consoleDlg.find("#attachmentname").val());
	var attachmenturl = $.trim(consoleDlg.find("#attachmenturl").val());
	var yearvalue = $.trim(consoleDlg.find("#yearvalue").val());
	var createid = $.trim(consoleDlg.find("#createid").val());
	var createdate = $.trim(consoleDlg.find("#createdate").val());
	var modifyid = $.trim(consoleDlg.find("#modifyid").val());
	var modifydate = $.trim(consoleDlg.find("#modifydate").val());
	var memo = $.trim(consoleDlg.find("#memo").val());
	var infoid = $.trim(consoleDlg.find("#infoid").val());  
	var filepath = $.trim(consoleDlg.find("#filepath").val());  
	var filename = $.trim(consoleDlg.find("#filename").val());  
    return {   
        "bean.attachmentid" : attachmentid,
        "bean.deptid" : deptid,
        "bean.bzstype" : bzstype,
        "bean.businessid" : businessid,
        "bean.attachmenttype" : attachmenttype,
        "bean.attachmentname" : attachmentname,
        "bean.attachmenturl" : attachmenturl,
        "bean.yearvalue" : yearvalue,
        "bean.createid" : createid,
        "bean.createdate" : createdate,
        "bean.modifyid" : modifyid,
        "bean.modifydate" : modifydate,
        "bean.memo" : memo,
        "bean.infoid":infoid,
        "bean.filepath":filepath,
        "bean.filename":filename
        
    };      
}
//根据后台数据，拼装grid行数据
function initGridRowData(_bizname,data){
	var dataJson = eval(data);
    var consoleDlg = $("#"+_bizname+"_info");  
	var deptid = $.trim(consoleDlg.find("#deptid").val());	
	var bzstype = $.trim(consoleDlg.find("#bzstype").val());	
	var businessid = $.trim(consoleDlg.find("#businessid").val());	
	var attachmenttype = $.trim(consoleDlg.find("#attachmenttype").val());	
	var attachmentname = $.trim(consoleDlg.find("#attachmentname").val());	
	var attachmenturl = $.trim(consoleDlg.find("#attachmenturl").val());	
	var yearvalue = $.trim(consoleDlg.find("#yearvalue").val());	
	var createid = $.trim(consoleDlg.find("#createid").val());	
	var createdate = $.trim(consoleDlg.find("#createdate").val());	
	var modifyid = $.trim(consoleDlg.find("#modifyid").val());	
	var modifydate = $.trim(consoleDlg.find("#modifydate").val());	
	var memo = $.trim(consoleDlg.find("#memo").val());
	var infoid = $.trim(consoleDlg.find("#infoid").val());
	var filepath = $.trim(consoleDlg.find("#filepath").val());
	var filename = $.trim(consoleDlg.find("#filename").val());
	var dataRow = { 			
	    _rowid : dataJson.attachmentid,// 从Server端得到系统分配的id
		attachmentid : dataJson.attachmentid,// 从Server端得到系统分配的id
		deptid : deptid,
		bzstype : bzstype,
		businessid : businessid,
		attachmenttype : attachmenttype,
		attachmentname : dataJson.attachmentname,
		attachmenturl : dataJson.attachmenturl,
		yearvalue : yearvalue,
		createid : createid,
		createdate : dataJson.createdate,
		modifyid : modifyid,
		modifydate : modifydate,
		memo : memo,
		infoid : infoid,
		filepath : filepath,
		filename : filename
	};
	return dataRow;
}
