var addsaveurl = rootpath+"/webpage/biz/commodity/collocation/doAddSave.action";
var modifysaveurl = rootpath+"/webpage/biz/commodity/collocation/doModifySave.action";
var deletesaveurl = rootpath+"/webpage/biz/commodity/collocation/delete.action";

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
	consoleDlg.find("#banner_url").val(rowData.banner_url);
	consoleDlg.find("#designer_said").val(rowData.designer_said);
	consoleDlg.find("#single_ids").val(rowData.single_ids);
	consoleDlg.find("#remarks").val(rowData.remarks);
	consoleDlg.find("#create_time").val(rowData.create_time);
	consoleDlg.find("#create_by").val(rowData.create_by);
	consoleDlg.find("#update_time").val(rowData.update_time);
	consoleDlg.find("#update_by").val(rowData.update_by);
	consoleDlg.find("#designer_name").val(rowData.designer_name);
	consoleDlg.find("#designer_head_url").val(rowData.designer_head_url);
}

//新增修改删除保存是获取表单参数
function getInfoData(_bizname){
    var consoleDlg = $("#"+_bizname+"_info");  
	var id = $.trim(consoleDlg.find("#id").val());
	var banner_url = $.trim(consoleDlg.find("#banner_url").val());
	var designer_said = $.trim(consoleDlg.find("#designer_said").val());
	var single_ids = $.trim(consoleDlg.find("#single_ids").val());
	var remarks = $.trim(consoleDlg.find("#remarks").val());
	var create_time = $.trim(consoleDlg.find("#create_time").val());
	var create_by = $.trim(consoleDlg.find("#create_by").val());
	var update_time = $.trim(consoleDlg.find("#update_time").val());
	var update_by = $.trim(consoleDlg.find("#update_by").val());
	var designer_name = $.trim(consoleDlg.find("#designer_name").val());
	var designer_head_url = $.trim(consoleDlg.find("#designer_head_url").val());
      
    return {   
        "bean.id" : id,
        "bean.banner_url" : banner_url,
        "bean.designer_said" : designer_said,
        "bean.single_ids" : single_ids,
        "bean.remarks" : remarks,
        "bean.create_time" : create_time,
        "bean.create_by" : create_by,
        "bean.update_time" : update_time,
        "bean.update_by" : update_by,
        "bean.designer_name" : designer_name,
        "bean.designer_head_url" : designer_head_url
    };      
}
//根据后台数据，拼装grid行数据
function initGridRowData(_bizname,data){
	var dataJson = eval(data);
    var consoleDlg = $("#"+_bizname+"_info");  
	var banner_url = $.trim(consoleDlg.find("#banner_url").val());	
	var designer_said = $.trim(consoleDlg.find("#designer_said").val());	
	var single_ids = $.trim(consoleDlg.find("#single_ids").val());	
	var remarks = $.trim(consoleDlg.find("#remarks").val());	
	var names = $.trim(consoleDlg.find("#names").val());	
	var create_time = $.trim(consoleDlg.find("#create_time").val());	
	var create_by = $.trim(consoleDlg.find("#create_by").val());	
	var update_time = $.trim(consoleDlg.find("#update_time").val());	
	var update_by = $.trim(consoleDlg.find("#update_by").val());	
	var designer_name = $.trim(consoleDlg.find("#designer_name").val());	
	var designer_head_url = $.trim(consoleDlg.find("#designer_head_url").val());	
	var dataRow = { 			
	    _rowid : dataJson.id,// 从Server端得到系统分配的id
		id : dataJson.id,// 从Server端得到系统分配的id
		banner_url : banner_url,
		designer_said : designer_said,
		single_ids : single_ids,
		remarks : remarks,
		names:names,
		create_time : create_time,
		create_by : create_by,
		update_time : update_time,
		update_by : update_by,
		designer_name : designer_name,
		designer_head_url : designer_head_url
	};
	return dataRow;
}
