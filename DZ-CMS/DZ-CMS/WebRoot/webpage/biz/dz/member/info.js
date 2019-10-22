var addsaveurl = rootpath+"/webpage/biz/dz/member/doAddSave.action";
var modifysaveurl = rootpath+"/webpage/biz/dz/member/doModifySave.action";
var deletesaveurl = rootpath+"/webpage/biz/dz/member/delete.action";

//删除修改进入表单时传递参数
function getIDVal(){
		return {"bean.id" : id};
}

//给表单赋值
function setInfoData(_bizname,data){  
	var dataJson = eval(data);
	var rowData = dataJson[0];
    var consoleDlg = $("#"+_bizname+"_info");
	consoleDlg.find("#id").val(rowData.id);
	consoleDlg.find("#lever_name").val(rowData.lever_name);
	consoleDlg.find("#maximum").val(rowData.maximum);
	consoleDlg.find("#minimum").val(rowData.minimum);
	consoleDlg.find("#remarks").val(rowData.remarks);
}

//新增修改删除保存是获取表单参数
function getInfoData(_bizname){
    var consoleDlg = $("#"+_bizname+"_info");  
	var id = $.trim(consoleDlg.find("#id").val());
	var lever_name = $.trim(consoleDlg.find("#lever_name").val());
	var maximum = $.trim(consoleDlg.find("#maximum").val());
	var minimum = $.trim(consoleDlg.find("#minimum").val());
	var remarks = $.trim(consoleDlg.find("#remarks").val());
      
    return {   
        "bean.id" : id,
        "bean.lever_name" : lever_name,
        "bean.maximum" : maximum,
        "bean.minimum" : minimum,
        "bean.remarks" : remarks
    };      
}
//根据后台数据，拼装grid行数据
function initGridRowData(_bizname,data){
	var dataJson = eval(data);
    var consoleDlg = $("#"+_bizname+"_info");  
	var lever_name = $.trim(consoleDlg.find("#lever_name").val());	
	var maximum = $.trim(consoleDlg.find("#maximum").val());	
	var minimum = $.trim(consoleDlg.find("#minimum").val());	
	var remarks = $.trim(consoleDlg.find("#remarks").val());	
	var dataRow = { 			
	    _rowid : dataJson.id,// 从Server端得到系统分配的id
		id : dataJson.id,// 从Server端得到系统分配的id
		lever_name : lever_name,
		maximum : maximum,
		minimum : minimum,
		remarks : remarks
	};
	return dataRow;
}
