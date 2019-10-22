var addsaveurl = rootpath+"/webpage/biz/sc/oretype/doAddSave.action";
var modifysaveurl = rootpath+"/webpage/biz/sc/oretype/doModifySave.action";
var deletesaveurl = rootpath+"/webpage/biz/sc/oretype/delete.action";

//删除修改进入表单时传递参数
function getIDVal(){
		return {"bean.oretypeid" : oretypeid};
}

//给表单赋值
function setInfoData(_bizname,data){  
	var dataJson = eval(data);
	var rowData = dataJson[0];
    var consoleDlg = $("#"+_bizname+"_info");
	consoleDlg.find("#oretypeid").val(rowData.oretypeid);
	consoleDlg.find("#oretype").val(rowData.oretype);
	consoleDlg.find("#deptid").val(rowData.deptid);
	consoleDlg.find("#unit").val(rowData.unit);
	consoleDlg.find("#indexno").val(rowData.indexno);
	consoleDlg.find("#createid").val(rowData.createid);
	consoleDlg.find("#createdate").val(rowData.createdate);
	consoleDlg.find("#modifyid").val(rowData.modifyid);
	consoleDlg.find("#modifydate").val(rowData.modifydate);
	consoleDlg.find("#demo").val(rowData.demo);
}

//新增修改删除保存是获取表单参数
function getInfoData(_bizname){
    var consoleDlg = $("#"+_bizname+"_info");  
	var oretypeid = $.trim(consoleDlg.find("#oretypeid").val());
	var oretype = $.trim(consoleDlg.find("#oretype").val());
	var deptid = $.trim(consoleDlg.find("#deptid").val());
	var unit = $.trim(consoleDlg.find("#unit").val());
	var indexno = $.trim(consoleDlg.find("#indexno").val());
	var createid = $.trim(consoleDlg.find("#createid").val());
	var createdate = $.trim(consoleDlg.find("#createdate").val());
	var modifyid = $.trim(consoleDlg.find("#modifyid").val());
	var modifydate = $.trim(consoleDlg.find("#modifydate").val());
	var demo = $.trim(consoleDlg.find("#demo").val());
      
    return {   
        "bean.oretypeid" : oretypeid,
        "bean.oretype" : oretype,
        "bean.deptid" : deptid,
        "bean.unit" : unit,
        "bean.indexno" : indexno,
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
	var oretype = $.trim(consoleDlg.find("#oretype").val());	
	var deptid = $.trim(consoleDlg.find("#deptid").val());	
	var unit = $.trim(consoleDlg.find("#unit").val());	
	var indexno = $.trim(consoleDlg.find("#indexno").val());	
	var createid = $.trim(consoleDlg.find("#createid").val());	
	var createdate = $.trim(consoleDlg.find("#createdate").val());	
	var modifyid = $.trim(consoleDlg.find("#modifyid").val());	
	var modifydate = $.trim(consoleDlg.find("#modifydate").val());	
	var demo = $.trim(consoleDlg.find("#demo").val());	
	var dataRow = { 			
	    _rowid : dataJson.oretypeid,// 从Server端得到系统分配的id
		oretypeid : dataJson.oretypeid,// 从Server端得到系统分配的id
		oretype : oretype,
		deptid : deptid,
		unit : unit,
		indexno : indexno,
		createid : createid,
		createdate : createdate,
		modifyid : modifyid,
		modifydate : modifydate,
		demo : demo
	};
	return dataRow;
}
