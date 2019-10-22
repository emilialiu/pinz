var addsaveurl = rootpath+"/webpage/biz/bi/mrbaseinfofee/doAddSave.action";
var modifysaveurl = rootpath+"/webpage/biz/bi/mrbaseinfofee/doModifySave.action";
var deletesaveurl = rootpath+"/webpage/biz/bi/mrbaseinfofee/delete.action";

//删除修改进入表单时传递参数
function getIDVal(){
		return {"bean.feeid" : feeid};
}

//给表单赋值
function setInfoData(_bizname,data){  
	var dataJson = eval(data);
	var rowData = dataJson[0];
    var consoleDlg = $("#"+_bizname+"_info");
	consoleDlg.find("#feeid").val(rowData.feeid);
	consoleDlg.find("#rightid").val(rowData.rightid);
	consoleDlg.find("#feename").val(rowData.feename);
	consoleDlg.find("#feetype").val(rowData.feetype);
	consoleDlg.find("#feestage").val(rowData.feestage);
	consoleDlg.find("#feeamount").val(rowData.feeamount);
	consoleDlg.find("#feedate").val(rowData.feedate);
	consoleDlg.find("#memo").val(rowData.memo);
	consoleDlg.find("#createid").val(rowData.createid);
	consoleDlg.find("#createdate").val(rowData.createdate);
	consoleDlg.find("#modifyid").val(rowData.modifyid);
	consoleDlg.find("#modifydate").val(rowData.modifydate);
}

//新增修改删除保存是获取表单参数
function getInfoData(_bizname){
    var consoleDlg = $("#"+_bizname+"_info");  
	var feeid = $.trim(consoleDlg.find("#feeid").val());
	var rightid = $.trim(consoleDlg.find("#rightid").val());
	var feename = $.trim(consoleDlg.find("#feename").val());
	var feetype = $.trim(consoleDlg.find("#feetype").val());
	var feestage = $.trim(consoleDlg.find("#feestage").val());
	var feeamount = $.trim(consoleDlg.find("#feeamount").val());
	var feedate = $.trim(consoleDlg.find("#feedate").val());
	var memo = $.trim(consoleDlg.find("#memo").val());
	var createid = $.trim(consoleDlg.find("#createid").val());
	var createdate = $.trim(consoleDlg.find("#createdate").val());
	var modifyid = $.trim(consoleDlg.find("#modifyid").val());
	var modifydate = $.trim(consoleDlg.find("#modifydate").val());
      
    return {   
        "bean.feeid" : feeid,
        "bean.rightid" : rightid,
        "bean.feename" : feename,
        "bean.feetype" : feetype,
        "bean.feestage" : feestage,
        "bean.feeamount" : feeamount,
        "bean.feedate" : feedate,
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
	var rightid = $.trim(consoleDlg.find("#rightid").val());	
	var feename = $.trim(consoleDlg.find("#feename").val());	
	var feetype = $.trim(consoleDlg.find("#feetype option:selected").text());	
	var feestage = $.trim(consoleDlg.find("#feestage option:selected").text());		
	var feeamount = $.trim(consoleDlg.find("#feeamount").val());	
	var feedate = $.trim(consoleDlg.find("#feedate").val());	
	var memo = $.trim(consoleDlg.find("#memo").val());	
	var createid = $.trim(consoleDlg.find("#createid").val());	
	var createdate = $.trim(consoleDlg.find("#createdate").val());	
	var modifyid = $.trim(consoleDlg.find("#modifyid").val());	
	var modifydate = $.trim(consoleDlg.find("#modifydate").val());	
	var dataRow = { 			
	    _rowid : dataJson.feeid,// 从Server端得到系统分配的id
		feeid : dataJson.feeid,// 从Server端得到系统分配的id
		rightid : rightid,
		feename : feename,
		feetype : feetype,
		feestage : feestage,
		feeamount : feeamount,
		feedate : feedate,
		memo : memo,
		createid : createid,
		createdate : createdate,
		modifyid : modifyid,
		modifydate : modifydate
	};
	return dataRow;
}
