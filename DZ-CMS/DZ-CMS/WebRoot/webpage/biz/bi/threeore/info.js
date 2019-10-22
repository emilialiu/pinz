var addsaveurl = rootpath+"/webpage/biz/bi/threeore/doAddSave.action";
var modifysaveurl = rootpath+"/webpage/biz/bi/threeore/doModifySave.action";
var deletesaveurl = rootpath+"/webpage/biz/bi/threeore/delete.action";

//删除修改进入表单时传递参数
function getIDVal(){
		return {"bean.threeoreid" : threeoreid};
}

//给表单赋值
function setInfoData(_bizname,data){  
	var dataJson = eval(data);
	var rowData = dataJson[0];
    var consoleDlg = $("#"+_bizname+"_info");
	consoleDlg.find("#threeoreid").val(rowData.threeoreid);
	consoleDlg.find("#mineid").val(rowData.mineid);
	consoleDlg.find("#mineunitid").val(rowData.mineunitid);
	consoleDlg.find("#yearvalue").val(rowData.yearvalue);
	consoleDlg.find("#element").val(rowData.element);
	consoleDlg.find("#reclamount").val(rowData.reclamount);
	consoleDlg.find("#expamount").val(rowData.expamount);
	consoleDlg.find("#extamount").val(rowData.extamount);
	consoleDlg.find("#byqx").val(rowData.byqx);
	consoleDlg.find("#memo").val(rowData.memo);
	consoleDlg.find("#createid").val(rowData.createid);
	consoleDlg.find("#createdate").val(rowData.createdate);
	consoleDlg.find("#modifyid").val(rowData.modifyid);
	consoleDlg.find("#modifydate").val(rowData.modifydate);
}

//新增修改删除保存是获取表单参数
function getInfoData(_bizname){
    var consoleDlg = $("#"+_bizname+"_info");  
	var threeoreid = $.trim(consoleDlg.find("#threeoreid").val());
	var mineid = $.trim(consoleDlg.find("#mineid").val());
	var mineunitid = $.trim(consoleDlg.find("#mineunitid").val());
	var yearvalue = $.trim(consoleDlg.find("#yearvalue").val());
	var element = $.trim(consoleDlg.find("#element").val());
	var reclamount = $.trim(consoleDlg.find("#reclamount").val());
	var expamount = $.trim(consoleDlg.find("#expamount").val());
	var extamount = $.trim(consoleDlg.find("#extamount").val());
	var byqx = $.trim(consoleDlg.find("#byqx").val());
	var memo = $.trim(consoleDlg.find("#memo").val());
	var createid = $.trim(consoleDlg.find("#createid").val());
	var createdate = $.trim(consoleDlg.find("#createdate").val());
	var modifyid = $.trim(consoleDlg.find("#modifyid").val());
	var modifydate = $.trim(consoleDlg.find("#modifydate").val());
      
    return {   
        "bean.threeoreid" : threeoreid,
        "bean.mineid" : mineid,
        "bean.mineunitid" : mineunitid,
        "bean.yearvalue" : yearvalue,
        "bean.element" : element,
        "bean.reclamount" : reclamount,
        "bean.expamount" : expamount,
        "bean.extamount" : extamount,
        "bean.byqx" : reclamount/500000,
        "bean.memo" : memo,
        "bean.createid" : createid,
        "bean.createdate" : createdate,
        "bean.modifyid" : modifyid,
        "bean.modifydate" : modifydate
    };      
}
//根据后台数据，拼装grid行数据
function initGridRowData(_bizname,data){
	var dataJson = eval(data);
    var consoleDlg = $("#"+_bizname+"_info");  
	var mineid = $.trim(consoleDlg.find("#mineid").val());	
	var mineunitid = $.trim(consoleDlg.find("#mineunitid").val());	
	var yearvalue = $.trim(consoleDlg.find("#yearvalue").val());	
	var element = $.trim(consoleDlg.find("#element").val());	
	var reclamount = $.trim(consoleDlg.find("#reclamount").val());	
	var expamount = $.trim(consoleDlg.find("#expamount").val());	
	var extamount = $.trim(consoleDlg.find("#extamount").val());	
	var byqx = $.trim(consoleDlg.find("#byqx").val());	
	var memo = $.trim(consoleDlg.find("#memo").val());	
	var createid = $.trim(consoleDlg.find("#createid").val());	
	var createdate = $.trim(consoleDlg.find("#createdate").val());	
	var modifyid = $.trim(consoleDlg.find("#modifyid").val());	
	var modifydate = $.trim(consoleDlg.find("#modifydate").val());	
	var dataRow = { 			
	    _rowid : dataJson.threeoreid,// 从Server端得到系统分配的id
		threeoreid : dataJson.threeoreid,// 从Server端得到系统分配的id
		mineid : mineid,
		mineunitid : mineunitid,
		yearvalue : yearvalue,
		element : element,
		reclamount : reclamount,
		expamount : expamount,
		extamount : extamount,
		byqx : byqx,
		memo : memo,
		createid : createid,
		createdate : createdate,
		modifyid : modifyid,
		modifydate : modifydate
	};
	return dataRow;
}
