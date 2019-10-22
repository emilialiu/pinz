var addsaveurl = rootpath+"/webpage/biz/dz/sku/doAddSave.action";
var modifysaveurl = rootpath+"/webpage/biz/dz/sku/doModifySave.action";
var deletesaveurl = rootpath+"/webpage/biz/dz/sku/delete.action";

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
	consoleDlg.find("#sku_name").val(rowData.sku_name);
	consoleDlg.find("#sku_value").val(rowData.sku_value);
	consoleDlg.find("#parent_id").val(rowData.parent_id);
	consoleDlg.find("#sort").val(rowData.sort);
	consoleDlg.find("#product_category_id").val(rowData.product_category_id);
	consoleDlg.find("#create_by").val(rowData.create_by);
	consoleDlg.find("#create_time").val(rowData.create_time);
	consoleDlg.find("#update_by").val(rowData.update_by);
	consoleDlg.find("#update_time").val(rowData.update_time);
	consoleDlg.find("#sku_code").val(rowData.sku_code);
	consoleDlg.find("#limit_ids").val(rowData.limit_ids);
}

//新增修改删除保存是获取表单参数
function getInfoData(_bizname){
    var consoleDlg = $("#"+_bizname+"_info");  
	var id = $.trim(consoleDlg.find("#id").val());
	var sku_name = $.trim(consoleDlg.find("#sku_name").val());
	var sku_value = $.trim(consoleDlg.find("#sku_value").val());
	var parent_id = $.trim(consoleDlg.find("#parent_id").val());
	var sort = $.trim(consoleDlg.find("#sort").val());
	var product_category_id = $.trim(consoleDlg.find("#product_category_id").val());
	var create_by = $.trim(consoleDlg.find("#create_by").val());
	var create_time = $.trim(consoleDlg.find("#create_time").val());
	var update_by = $.trim(consoleDlg.find("#update_by").val());
	var update_time = $.trim(consoleDlg.find("#update_time").val());
	var sku_code = $.trim(consoleDlg.find("#sku_code").val());
	var limit_ids = $.trim(consoleDlg.find("#limit_ids").val());
      
    return {   
        "bean.id" : id,
        "bean.sku_name" : sku_name,
        "bean.sku_value" : sku_value,
        "bean.parent_id" : parent_id,
        "bean.sort" : sort,
        "bean.product_category_id" : product_category_id,
        "bean.create_by" : create_by,
        "bean.create_time" : create_time,
        "bean.update_by" : update_by,
        "bean.update_time" : update_time,
        "bean.sku_code" : sku_code,
        "bean.limit_ids" : limit_ids
    };      
}
//根据后台数据，拼装grid行数据
function initGridRowData(_bizname,data){
	var dataJson = eval(data);
    var consoleDlg = $("#"+_bizname+"_info");  
	var sku_name = $.trim(consoleDlg.find("#sku_name").val());	
	var sku_value = $.trim(consoleDlg.find("#sku_value").val());	
	var parent_id = $.trim(consoleDlg.find("#parent_id").val());	
	var sort = $.trim(consoleDlg.find("#sort").val());	
	var product_category_id = $.trim(consoleDlg.find("#product_category_id").val());
	var create_by = $.trim(consoleDlg.find("#create_by").val());	
	var create_time = $.trim(consoleDlg.find("#create_time").val());	
	var update_by = $.trim(consoleDlg.find("#update_by").val());	
	var update_time = $.trim(consoleDlg.find("#update_time").val());	
	var sku_code = $.trim(consoleDlg.find("#sku_code").val());	
	var limit_ids = $.trim(consoleDlg.find("#limit_ids").val());	
	var dataRow = { 			
	    _rowid : dataJson.id,// 从Server端得到系统分配的id
		id : dataJson.id,// 从Server端得到系统分配的id
		sku_name : sku_name,
		sku_value : sku_value,
		parent_id : parent_id,
		sort : sort,
		product_category_id : product_category_id,
		create_by : create_by,
		create_time : create_time,
		update_by : update_by,
		update_time : update_time,
		sku_code : sku_code,
		limit_ids : limit_ids
	};
	return dataRow;
}
