var addsaveurl = rootpath+"/webpage/biz/commodity/buying/doAddSave.action";
var modifysaveurl = rootpath+"/webpage/biz/commodity/buying/doModifySave.action";
var deletesaveurl = rootpath+"/webpage/biz/commodity/buying/delete.action";

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
	consoleDlg.find("#activity_name").val(rowData.activity_name);
	consoleDlg.find("#start_time").val(rowData.start_time);
	consoleDlg.find("#end_time").val(rowData.end_time);
	consoleDlg.find("#is_start").val(rowData.is_start);
	consoleDlg.find("#create_time").val(rowData.create_time);
	consoleDlg.find("#create_by").val(rowData.create_by);
	consoleDlg.find("#update_time").val(rowData.update_time);
	consoleDlg.find("#update_by").val(rowData.update_by);
}

//新增修改删除保存是获取表单参数
function getInfoData(_bizname){
    var consoleDlg = $("#"+_bizname+"_info");  
	var id = $.trim(consoleDlg.find("#id").val());
	var activity_name = $.trim(consoleDlg.find("#activity_name").val());
	var start_time = $.trim(consoleDlg.find("#start_time").val());
	var end_time = $.trim(consoleDlg.find("#end_time").val());
	var is_start = $.trim(consoleDlg.find("#is_start option:selected").val());
	var create_time = $.trim(consoleDlg.find("#create_time").val());
	var create_by = $.trim(consoleDlg.find("#create_by").val());
	var update_time = $.trim(consoleDlg.find("#update_time").val());
	var update_by = $.trim(consoleDlg.find("#update_by").val());
      
    return {   
        "bean.id" : id,
        "bean.activity_name" : activity_name,
        "bean.start_time" : start_time,
        "bean.end_time" : end_time,
        "bean.is_start" : is_start,
        "bean.create_time" : create_time,
        "bean.create_by" : create_by,
        "bean.update_time" : update_time,
        "bean.update_by" : update_by
    };      
}
//根据后台数据，拼装grid行数据
function initGridRowData(_bizname,data){
	var dataJson = eval(data);
    var consoleDlg = $("#"+_bizname+"_info");  
	var activity_name = $.trim(consoleDlg.find("#activity_name").val());	
	var start_time = $.trim(consoleDlg.find("#start_time").val());	
	var end_time = $.trim(consoleDlg.find("#end_time").val());	
	var start = $.trim(consoleDlg.find("#start option:selected").text());	
	var create_time = $.trim(consoleDlg.find("#create_time").val());	
	var create_by = $.trim(consoleDlg.find("#create_by").val());	
	var update_time = $.trim(consoleDlg.find("#update_time").val());	
	var update_by = $.trim(consoleDlg.find("#update_by").val());	
	var dataRow = { 			
	    _rowid : dataJson.id,// 从Server端得到系统分配的id
		id : dataJson.id,// 从Server端得到系统分配的id
		activity_name : activity_name,
		start_time : start_time,
		end_time : end_time,
		start : start,
		create_time : create_time,
		create_by : create_by,
		update_time : update_time,
		update_by : update_by
	};
	return dataRow;
}
