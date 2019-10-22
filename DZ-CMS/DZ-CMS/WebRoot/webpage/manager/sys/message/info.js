var addsaveurl = rootpath+"/manager/sys/message/doAddSave.action";
var modifysaveurl = rootpath+"/manager/sys/message/doModifySave.action";
var deletesaveurl = rootpath+"/manager/sys/message/delete.action";

//删除修改进入表单时传递参数
function getIDVal(){
    return {"bean.messageid" : messageid};
}

//给表单赋值
function setInfoData(_bizname,data){  
	var dataJson = eval(data);
	var rowData = dataJson[0];
    var consoleDlg = $("#"+_bizname+"_info");   
    consoleDlg.find("#messageid").val(rowData.messageid);  
    consoleDlg.find("#messagetitle").val(rowData.messagetitle);  
    consoleDlg.find("#shorttile").val(rowData.shorttile);  
    consoleDlg.find("#mestype").val(rowData.mestype);  
    consoleDlg.find("#mescontent").val(rowData.mescontent); 
}

//新增修改删除保存是获取表单参数
function getInfoData(_bizname){
    var consoleDlg = $("#"+_bizname+"_info");  
	var messageid = $.trim(consoleDlg.find("#messageid").val());  
    var messagetitle = $.trim(consoleDlg.find("#messagetitle").val());  
    var shorttile = $.trim(consoleDlg.find("#shorttile").val());  
    var mestype = $.trim(consoleDlg.find("#mestype").val());  
    var mescontent = $.trim(consoleDlg.find("#mescontent").val());  
    return {  
        "bean.messageid" : messageid,  
        "bean.messagetitle" : messagetitle,  
        "bean.shorttile" : shorttile,  
        "bean.mestype" : mestype,  
        "bean.mescontent" : mescontent
    };      
}
//根据后台数据，拼装grid行数据
function initGridRowData(_bizname,data){
	var dataJson = eval(data);
    var consoleDlg = $("#"+_bizname+"_info");  
    var messagetitle = $.trim(consoleDlg.find("#messagetitle").val());  
    var shorttile = $.trim(consoleDlg.find("#shorttile").val());  
    var mestype = $.trim(consoleDlg.find("#mestype").val());  
    var mescontent = $.trim(consoleDlg.find("#mescontent").val());
	var dataRow = {
		_rowid : dataJson.messageid,   // 从Server端得到系统分配的id
        messageid : dataJson.messageid,   // 从Server端得到系统分配的id  
        mestime : dataJson.mestime,
        messagetitle : messagetitle,
        shorttile : shorttile,
        mestype : mestype,
        mescontent : mescontent
    };
	return dataRow;
}
