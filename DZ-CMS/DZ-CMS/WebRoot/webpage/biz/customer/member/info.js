var addsaveurl = rootpath+"/webpage/biz/customer/member/doAddSave.action";
var modifysaveurl = rootpath+"/webpage/biz/customer/member/doModifySave.action";
var deletesaveurl = rootpath+"/webpage/biz/customer/member/delete.action";

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
	consoleDlg.find("#customer_id").val(rowData.customer_id);
	consoleDlg.find("#member_id").val(rowData.member_id);
	consoleDlg.find("#create_time").val(rowData.create_time);
}

//新增修改删除保存是获取表单参数
function getInfoData(_bizname){
    var consoleDlg = $("#"+_bizname+"_info");  
	var id = $.trim(consoleDlg.find("#id").val());
	var customer_id = $.trim(consoleDlg.find("#customer_id").val());
	var member_id = $.trim(consoleDlg.find("#member_id").val());
	var create_time = $.trim(consoleDlg.find("#create_time").val());
      
    return {   
        "bean.id" : id,
        "bean.customer_id" : customer_id,
        "bean.member_id" : member_id,
        "bean.create_time" : create_time
    };      
}
//根据后台数据，拼装grid行数据
function initGridRowData(_bizname,data){
	var dataJson = eval(data);
    var consoleDlg = $("#"+_bizname+"_info");  
	var customer_id = $.trim(consoleDlg.find("#customer_id").val());	
	var member_id = $.trim(consoleDlg.find("#member_id").val());	
	var create_time = $.trim(consoleDlg.find("#create_time").val());	
	var dataRow = { 			
	    _rowid : dataJson.id,// 从Server端得到系统分配的id
		id : dataJson.id,// 从Server端得到系统分配的id
		customer_id : customer_id,
		member_id : member_id,
		create_time : create_time
	};
	return dataRow;
}
