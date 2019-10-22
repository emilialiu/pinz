var addsaveurl = rootpath+"/webpage/biz/bi/expright/doAddSave.action";
var modifysaveurl = rootpath+"/webpage/biz/bi/expright/doModifySave.action";
var deletesaveurl = rootpath+"/webpage/biz/bi/expright/delete.action";

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
	consoleDlg.find("#mineralstage").val(rowData.mineralstage);
	consoleDlg.find("#mineraltype").val(rowData.mineraltype);
	consoleDlg.find("#rightgetway").val(rowData.rightgetway);
	consoleDlg.find("#totalamount").val(rowData.totalamount);
	consoleDlg.find("#year1amount").val(rowData.year1amount);
	consoleDlg.find("#year2amount").val(rowData.year2amount);
	consoleDlg.find("#year3amount").val(rowData.year3amount);
	consoleDlg.find("#stateamount").val(rowData.stateamount);
	consoleDlg.find("#localamount").val(rowData.localamount);
	consoleDlg.find("#enterpriseamount").val(rowData.enterpriseamount);
	consoleDlg.find("#foreignamount").val(rowData.foreignamount);
	consoleDlg.find("#personalamount").val(rowData.personalamount);
	consoleDlg.find("#otheramount").val(rowData.otheramount);
	consoleDlg.find("#result").val(rowData.result);
	consoleDlg.find("#remark").val(rowData.remark);
}

//新增修改删除保存是获取表单参数
function getInfoData(_bizname){
    var consoleDlg = $("#"+_bizname+"_info");  
	var rightid = $.trim(consoleDlg.find("#rightid").val());
	var cooperateent = $.trim(consoleDlg.find("#cooperateent").val());
	var mineralstage = $.trim(consoleDlg.find("#mineralstage").val());
	var mineraltype = $.trim(consoleDlg.find("#mineraltype").val());
	var rightgetway = $.trim(consoleDlg.find("#rightgetway").val());
	var totalamount = $.trim(consoleDlg.find("#totalamount").val());
	var year1amount = $.trim(consoleDlg.find("#year1amount").val());
	var year2amount = $.trim(consoleDlg.find("#year2amount").val());
	var year3amount = $.trim(consoleDlg.find("#year3amount").val());
	var stateamount = $.trim(consoleDlg.find("#stateamount").val());
	var localamount = $.trim(consoleDlg.find("#localamount").val());
	var enterpriseamount = $.trim(consoleDlg.find("#enterpriseamount").val());
	var foreignamount = $.trim(consoleDlg.find("#foreignamount").val());
	var personalamount = $.trim(consoleDlg.find("#personalamount").val());
	var otheramount = $.trim(consoleDlg.find("#otheramount").val());
	var result = $.trim(consoleDlg.find("#result").val());
	var remark = $.trim(consoleDlg.find("#remark").val());
      
    return {   
        "bean.rightid" : rightid,
        "bean.cooperateent" : cooperateent,
        "bean.mineralstage" : mineralstage,
        "bean.mineraltype" : mineraltype,
        "bean.rightgetway" : rightgetway,
        "bean.totalamount" : totalamount,
        "bean.year1amount" : year1amount,
        "bean.year2amount" : year2amount,
        "bean.year3amount" : year3amount,
        "bean.stateamount" : stateamount,
        "bean.localamount" : localamount,
        "bean.enterpriseamount" : enterpriseamount,
        "bean.foreignamount" : foreignamount,
        "bean.personalamount" : personalamount,
        "bean.otheramount" : otheramount,
        "bean.result" : result,
        "bean.remark" : remark
    };      
}
//根据后台数据，拼装grid行数据
function initGridRowData(_bizname,data){
	var dataJson = eval(data);
    var consoleDlg = $("#"+_bizname+"_info");  
	var cooperateent = $.trim(consoleDlg.find("#cooperateent").val());	
	var mineralstage = $.trim(consoleDlg.find("#mineralstage").val());	
	var mineraltype = $.trim(consoleDlg.find("#mineraltype").val());	
	var rightgetway = $.trim(consoleDlg.find("#rightgetway").val());	
	var totalamount = $.trim(consoleDlg.find("#totalamount").val());	
	var year1amount = $.trim(consoleDlg.find("#year1amount").val());	
	var year2amount = $.trim(consoleDlg.find("#year2amount").val());	
	var year3amount = $.trim(consoleDlg.find("#year3amount").val());	
	var stateamount = $.trim(consoleDlg.find("#stateamount").val());	
	var localamount = $.trim(consoleDlg.find("#localamount").val());	
	var enterpriseamount = $.trim(consoleDlg.find("#enterpriseamount").val());	
	var foreignamount = $.trim(consoleDlg.find("#foreignamount").val());	
	var personalamount = $.trim(consoleDlg.find("#personalamount").val());	
	var otheramount = $.trim(consoleDlg.find("#otheramount").val());	
	var result = $.trim(consoleDlg.find("#result").val());	
	var remark = $.trim(consoleDlg.find("#remark").val());	
	var dataRow = { 			
	    _rowid : dataJson.rightid,// 从Server端得到系统分配的id
		rightid : dataJson.rightid,// 从Server端得到系统分配的id
		cooperateent : cooperateent,
		mineralstage : mineralstage,
		mineraltype : mineraltype,
		rightgetway : rightgetway,
		totalamount : totalamount,
		year1amount : year1amount,
		year2amount : year2amount,
		year3amount : year3amount,
		stateamount : stateamount,
		localamount : localamount,
		enterpriseamount : enterpriseamount,
		foreignamount : foreignamount,
		personalamount : personalamount,
		otheramount : otheramount,
		result : result,
		remark : remark
	};
	return dataRow;
}
