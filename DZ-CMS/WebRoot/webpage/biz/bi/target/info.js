var addsaveurl = rootpath+"/webpage/biz/bi/target/doAddSave.action";
var modifysaveurl = rootpath+"/webpage/biz/bi/target/doModifySave.action";
var deletesaveurl = rootpath+"/webpage/biz/bi/target/delete.action";

//删除修改进入表单时传递参数
function getIDVal(){
		return {"bean.targetid" : targetid};
}

//给表单赋值
function setInfoData(_bizname,data){  
	var dataJson = eval(data);
	var rowData = dataJson[0];
    var consoleDlg = $("#"+_bizname+"_info");
	consoleDlg.find("#targetid").val(rowData.targetid);
	consoleDlg.find("#procid").val(rowData.procid);
	consoleDlg.find("#targetname").val(rowData.targetname);
	consoleDlg.find("#shortname").val(rowData.shortname);
	consoleDlg.find("#targetunit").val(rowData.targetunit);
	consoleDlg.find("#isused").val(rowData.isused);
	consoleDlg.find("#isdel").val(rowData.isdel);
	consoleDlg.find("#isedit").val(rowData.isedit);
	consoleDlg.find("#issum").val(rowData.issum);
	consoleDlg.find("#ismust").val(rowData.ismust);
	consoleDlg.find("#datatype").val(rowData.datatype);
	consoleDlg.find("#isyplan").val(rowData.isyplan);
	consoleDlg.find("#ismplan").val(rowData.ismplan);
	consoleDlg.find("#isproduce").val(rowData.isproduce);
	consoleDlg.find("#isaccept").val(rowData.isaccept);
	consoleDlg.find("#memo").val(rowData.memo);
	consoleDlg.find("#createid").val(rowData.createid);
	consoleDlg.find("#createdate").val(rowData.createdate);
	consoleDlg.find("#modifyid").val(rowData.modifyid);
	consoleDlg.find("#modifydate").val(rowData.modifydate);
}

//新增修改删除保存是获取表单参数
function getInfoData(_bizname){
    var consoleDlg = $("#"+_bizname+"_info");  
	var targetid = $.trim(consoleDlg.find("#targetid").val());
	var procid = $.trim(consoleDlg.find("#procid").val());
	var targetname = $.trim(consoleDlg.find("#targetname").val());
	var shortname = $.trim(consoleDlg.find("#shortname").val());
	var targetunit = $.trim(consoleDlg.find("#targetunit").val());
	var isused = $.trim(consoleDlg.find("#isused").val());
	var isdel = $.trim(consoleDlg.find("#isdel").val());
	var isedit = $.trim(consoleDlg.find("#isedit").val());
	var issum = $.trim(consoleDlg.find("#issum").val());
	var ismust = $.trim(consoleDlg.find("#ismust").val());
	var datatype = $.trim(consoleDlg.find("#datatype").val());
	var isyplan = $.trim(consoleDlg.find("#isyplan").val());
	var ismplan = $.trim(consoleDlg.find("#ismplan").val());
	var isproduce = $.trim(consoleDlg.find("#isproduce").val());
	var isaccept = $.trim(consoleDlg.find("#isaccept").val());
	var memo = $.trim(consoleDlg.find("#memo").val());
	var createid = $.trim(consoleDlg.find("#createid").val());
	var createdate = $.trim(consoleDlg.find("#createdate").val());
	var modifyid = $.trim(consoleDlg.find("#modifyid").val());
	var modifydate = $.trim(consoleDlg.find("#modifydate").val());
      
    return {   
        "bean.targetid" : targetid,
        "bean.procid" : procid,
        "bean.targetname" : targetname,
        "bean.shortname" : shortname,
        "bean.targetunit" : targetunit,
        "bean.isused" : isused,
        "bean.isdel" : isdel,
        "bean.isedit" : isedit,
        "bean.issum" : issum,
        "bean.ismust" : ismust,
        "bean.datatype" : datatype,
        "bean.isyplan" : isyplan,
        "bean.ismplan" : ismplan,
        "bean.isproduce" : isproduce,
        "bean.isaccept" : isaccept,
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
	var procid = $.trim(consoleDlg.find("#procid").val());	
	var targetname = $.trim(consoleDlg.find("#targetname").val());	
	var shortname = $.trim(consoleDlg.find("#shortname").val());	
	var targetunit = $.trim(consoleDlg.find("#targetunit").val());	
	var isused = $.trim(consoleDlg.find("#isused").val());	
	var isdel = $.trim(consoleDlg.find("#isdel").val());	
	var isedit = $.trim(consoleDlg.find("#isedit").val());	
	var issum = $.trim(consoleDlg.find("#issum").val());	
	var ismust = $.trim(consoleDlg.find("#ismust").val());	
	var datatype = $.trim(consoleDlg.find("#datatype").val());	
	var isyplan = $.trim(consoleDlg.find("#isyplan").val());	
	var ismplan = $.trim(consoleDlg.find("#ismplan").val());	
	var isproduce = $.trim(consoleDlg.find("#isproduce").val());	
	var isaccept = $.trim(consoleDlg.find("#isaccept").val());	
	var memo = $.trim(consoleDlg.find("#memo").val());	
	var createid = $.trim(consoleDlg.find("#createid").val());	
	var createdate = $.trim(consoleDlg.find("#createdate").val());	
	var modifyid = $.trim(consoleDlg.find("#modifyid").val());	
	var modifydate = $.trim(consoleDlg.find("#modifydate").val());	
	var dataRow = { 			
	    _rowid : dataJson.targetid,// 从Server端得到系统分配的id
		targetid : dataJson.targetid,// 从Server端得到系统分配的id
		procid : procid,
		targetname : targetname,
		shortname : shortname,
		targetunit : targetunit,
		isused : isused,
		isdel : isdel,
		isedit : isedit,
		issum : issum,
		ismust : ismust,
		datatype : datatype,
		isyplan : isyplan,
		ismplan : ismplan,
		isproduce : isproduce,
		isaccept : isaccept,
		memo : memo,
		createid : createid,
		createdate : createdate,
		modifyid : modifyid,
		modifydate : modifydate
	};
	return dataRow;
}
