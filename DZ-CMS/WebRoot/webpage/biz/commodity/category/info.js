var addsaveurl = rootpath+"/webpage/biz/commodity/category/doAddSave.action";
var modifysaveurl = rootpath+"/webpage/biz/commodity/category/doModifySave.action";
var deletesaveurl = rootpath+"/webpage/biz/commodity/category/delete.action";

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
	consoleDlg.find("#name").val(rowData.name);
	consoleDlg.find("#sex").val(rowData.sex);
	consoleDlg.find("#remarks").val(rowData.remarks);
	consoleDlg.find("#sort").val(rowData.sort);
	consoleDlg.find("#create_time").val(rowData.create_time);
	consoleDlg.find("#create_by").val(rowData.create_by);
	consoleDlg.find("#update_time").val(rowData.update_time);
	consoleDlg.find("#update_by").val(rowData.update_by);
}

//新增修改删除保存是获取表单参数
function getInfoData(_bizname){
    var consoleDlg = $("#"+_bizname+"_info");  
	var id = $.trim(consoleDlg.find("#id").val());
	var name = $.trim(consoleDlg.find("#name").val());
	var sex = $.trim(consoleDlg.find("#sex").val());
	var remarks = $.trim(consoleDlg.find("#remarks").val());
	var sort = $.trim(consoleDlg.find("#sort").val());
	var create_time = $.trim(consoleDlg.find("#create_time").val());
	var create_by = $.trim(consoleDlg.find("#create_by").val());
	var update_time = $.trim(consoleDlg.find("#update_time").val());
	var update_by = $.trim(consoleDlg.find("#update_by").val());
      
    return {   
        "bean.id" : id,
        "bean.name" : name,
        "bean.sex" : sex,
        "bean.remarks" : remarks,
        "bean.sort" : sort,
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
	var name = $.trim(consoleDlg.find("#name").val());	
	var sex = $.trim(consoleDlg.find("#sex").val());	
	var remarks = $.trim(consoleDlg.find("#remarks").val());	
	var sort = $.trim(consoleDlg.find("#sort").val());	
	var create_time = $.trim(consoleDlg.find("#create_time").val());	
	var create_by = $.trim(consoleDlg.find("#create_by").val());	
	var update_time = $.trim(consoleDlg.find("#update_time").val());	
	var update_by = $.trim(consoleDlg.find("#update_by").val());	
	var dataRow = { 			
	    _rowid : dataJson.id,// 从Server端得到系统分配的id
		id : dataJson.id,// 从Server端得到系统分配的id
		name : name,
		sex : sex,
		remarks : remarks,
		sort : sort,
		create_time : create_time,
		create_by : create_by,
		update_time : update_time,
		update_by : update_by
	};
	return dataRow;
}
