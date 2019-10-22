var addsaveurl = rootpath+"/webpage/biz/home/page/doAddSave.action";
var modifysaveurl = rootpath+"/webpage/biz/home/page/doModifySave.action";
var deletesaveurl = rootpath+"/webpage/biz/home/page/delete.action";

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
	consoleDlg.find("#img_url").val(rowData.img_url);
	consoleDlg.find("#is_start").val(rowData.is_start);
	consoleDlg.find("#sort").val(rowData.sort);
	consoleDlg.find("#create_time").val(rowData.create_time);
	consoleDlg.find("#create_by").val(rowData.create_by);
	consoleDlg.find("#update_time").val(rowData.update_time);
	consoleDlg.find("#update_by").val(rowData.update_by);
	consoleDlg.find("#remarks").val(rowData.remarks);
}

//新增修改删除保存是获取表单参数
function getInfoData(_bizname){
    var consoleDlg = $("#"+_bizname+"_info");  
	var id = $.trim(consoleDlg.find("#id").val());
	var img_url = $.trim(consoleDlg.find("#img_url").val());
	var is_start = $.trim(consoleDlg.find("#is_start option:selected").val());
	var sort = $.trim(consoleDlg.find("#sort").val());
	var create_time = $.trim(consoleDlg.find("#create_time").val());
	var create_by = $.trim(consoleDlg.find("#create_by").val());
	var update_time = $.trim(consoleDlg.find("#update_time").val());
	var update_by = $.trim(consoleDlg.find("#update_by").val());
	var remarks = $.trim(consoleDlg.find("#remarks").val());
      
    return {   
        "bean.id" : id,
        "bean.img_url" : img_url,
        "bean.is_start" : is_start,
        "bean.sort" : sort,
        "bean.create_time" : create_time,
        "bean.create_by" : create_by,
        "bean.update_time" : update_time,
        "bean.update_by" : update_by,
        "bean.remarks" : remarks
    };      
}
//根据后台数据，拼装grid行数据
function initGridRowData(_bizname,data){
	var dataJson = eval(data);
    var consoleDlg = $("#"+_bizname+"_info");  
	var img_url = $.trim(consoleDlg.find("#img_url").val());	
	var isstart = $.trim(consoleDlg.find("#is_start option:selected").text());	
	var sort = $.trim(consoleDlg.find("#sort").val());	
	var create_time = $.trim(consoleDlg.find("#create_time").val());	
	var create_by = $.trim(consoleDlg.find("#create_by").val());	
	var update_time = $.trim(consoleDlg.find("#update_time").val());	
	var update_by = $.trim(consoleDlg.find("#update_by").val());	
	var remarks = $.trim(consoleDlg.find("#remarks").val());	
	var dataRow = { 			
	    _rowid : dataJson.id,// 从Server端得到系统分配的id
		id : dataJson.id,// 从Server端得到系统分配的id
		img_url : img_url,
		isstart : isstart,
		sort : sort,
		create_time : create_time,
		create_by : create_by,
		update_time : update_time,
		update_by : update_by,
		remarks : remarks
	};
	return dataRow;
}
