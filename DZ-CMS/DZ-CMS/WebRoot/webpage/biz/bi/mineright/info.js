var addsaveurl = rootpath+"/webpage/biz/bi/mineright/doAddSave.action";
var modifysaveurl = rootpath+"/webpage/biz/bi/mineright/doModifySave.action";
var deletesaveurl = rootpath+"/webpage/biz/bi/mineright/delete.action";

//删除修改进入表单时传递参数
function getIDVal(){
		return {"bean.rightid" : rightid};
}

//给表单赋值
function setInfoData(_bizname,data){  
	var dataJson = eval(data);
	var rowData = dataJson[0];
    var consoleDlg = $("#"+_bizname+"_info");
	consoleDlg.find("#rightid").val(rowData.rightid);
	consoleDlg.find("#cooperateent").val(rowData.cooperateent);
	consoleDlg.find("#planscale").val(rowData.planscale);
	consoleDlg.find("#involume").val(rowData.involume);
	consoleDlg.find("#arearange").val(rowData.arearange);
	consoleDlg.find("#mrarea").val(rowData.mrarea);
	consoleDlg.find("#mineraltype").val(rowData.mineraltype);
	consoleDlg.find("#catype").val(rowData.catype);
	consoleDlg.find("#verifyreserve").val(rowData.verifyreserve);
	consoleDlg.find("#mineway").val(rowData.mineway);
	consoleDlg.find("#miningmethod").val(rowData.miningmethod);
	consoleDlg.find("#benemethod").val(rowData.benemethod);
	consoleDlg.find("#depthupper").val(rowData.depthupper);
	consoleDlg.find("#depthlower").val(rowData.depthlower);
	consoleDlg.find("#minegetway").val(rowData.minegetway);
	consoleDlg.find("#remark").val(rowData.remark);
}

//新增修改删除保存是获取表单参数
function getInfoData(_bizname){
    var consoleDlg = $("#"+_bizname+"_info");  
	var rightid = $.trim(consoleDlg.find("#rightid").val());
	var cooperateent = $.trim(consoleDlg.find("#cooperateent").val());
	var planscale = $.trim(consoleDlg.find("#planscale").val());
	var involume = $.trim(consoleDlg.find("#involume").val());
	var arearange = $.trim(consoleDlg.find("#arearange").val());
	var mrarea = $.trim(consoleDlg.find("#mrarea").val());
	var mineraltype = $.trim(consoleDlg.find("#mineraltype").val());
	var catype = $.trim(consoleDlg.find("#catype").val());
	var verifyreserve = $.trim(consoleDlg.find("#verifyreserve").val());
	var mineway = $.trim(consoleDlg.find("#mineway").val());
	var miningmethod = $.trim(consoleDlg.find("#miningmethod").val());
	var benemethod = $.trim(consoleDlg.find("#benemethod").val());
	var depthupper = $.trim(consoleDlg.find("#depthupper").val());
	var depthlower = $.trim(consoleDlg.find("#depthlower").val());
	var minegetway = $.trim(consoleDlg.find("#minegetway").val());
	var remark = $.trim(consoleDlg.find("#remark").val());
      
    return {   
        "bean.rightid" : rightid,
        "bean.cooperateent" : cooperateent,
        "bean.planscale" : planscale,
        "bean.involume" : involume,
        "bean.arearange" : arearange,
        "bean.mrarea" : mrarea,
        "bean.mineraltype" : mineraltype,
        "bean.catype" : catype,
        "bean.verifyreserve" : verifyreserve,
        "bean.mineway" : mineway,
        "bean.miningmethod" : miningmethod,
        "bean.benemethod" : benemethod,
        "bean.depthupper" : depthupper,
        "bean.depthlower" : depthlower,
        "bean.minegetway" : minegetway,
        "bean.remark" : remark
    };      
}
//根据后台数据，拼装grid行数据
function initGridRowData(_bizname,data){
	var dataJson = eval(data);
    var consoleDlg = $("#"+_bizname+"_info");  
	var cooperateent = $.trim(consoleDlg.find("#cooperateent").val());	
	var planscale = $.trim(consoleDlg.find("#planscale").val());	
	var involume = $.trim(consoleDlg.find("#involume").val());	
	var arearange = $.trim(consoleDlg.find("#arearange").val());	
	var mrarea = $.trim(consoleDlg.find("#mrarea").val());	
	var mineraltype = $.trim(consoleDlg.find("#mineraltype").val());	
	var catype = $.trim(consoleDlg.find("#catype").val());	
	var verifyreserve = $.trim(consoleDlg.find("#verifyreserve").val());	
	var mineway = $.trim(consoleDlg.find("#mineway").val());	
	var miningmethod = $.trim(consoleDlg.find("#miningmethod").val());	
	var benemethod = $.trim(consoleDlg.find("#benemethod").val());	
	var depthupper = $.trim(consoleDlg.find("#depthupper").val());	
	var depthlower = $.trim(consoleDlg.find("#depthlower").val());	
	var minegetway = $.trim(consoleDlg.find("#minegetway").val());	
	var remark = $.trim(consoleDlg.find("#remark").val());	
	var dataRow = { 			
	    _rowid : dataJson.rightid,// 从Server端得到系统分配的id
		rightid : dataJson.rightid,// 从Server端得到系统分配的id
		cooperateent : cooperateent,
		planscale : planscale,
		involume : involume,
		arearange : arearange,
		mrarea : mrarea,
		mineraltype : mineraltype,
		catype : catype,
		verifyreserve : verifyreserve,
		mineway : mineway,
		miningmethod : miningmethod,
		benemethod : benemethod,
		depthupper : depthupper,
		depthlower : depthlower,
		minegetway : minegetway,
		remark : remark
	};
	return dataRow;
}
