var addsaveurl = rootpath+"/webpage/biz/commodity/review/doAddSave.action";
var modifysaveurl = rootpath+"/webpage/biz/commodity/review/doModifySave.action";
var deletesaveurl = rootpath+"/webpage/biz/commodity/review/delete.action";

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
	consoleDlg.find("#order_id").val(rowData.order_id);
	consoleDlg.find("#commodity_id").val(rowData.commodity_id);
	consoleDlg.find("#customer_id").val(rowData.customer_id);
	consoleDlg.find("#satisfaction").val(rowData.satisfaction);
	consoleDlg.find("#comments").val(rowData.comments);
	consoleDlg.find("#create_time").val(rowData.create_time);
	consoleDlg.find("#is_delete").val(rowData.is_delete);
	consoleDlg.find("#img_url").val(rowData.img_url);
	consoleDlg.find("#reply_content").val(rowData.reply_content);
}

//新增修改删除保存是获取表单参数
function getInfoData(_bizname){
    var consoleDlg = $("#"+_bizname+"_info");  
	var id = $.trim(consoleDlg.find("#id").val());
	var order_id = $.trim(consoleDlg.find("#order_id").val());
	var commodity_id = $.trim(consoleDlg.find("#commodity_id").val());
	var customer_id = $.trim(consoleDlg.find("#customer_id").val());
	var satisfaction = $.trim(consoleDlg.find("#satisfaction").val());
	var comments = $.trim(consoleDlg.find("#comments").val());
	var create_time = $.trim(consoleDlg.find("#create_time").val());
	var is_delete = $.trim(consoleDlg.find("#is_delete").val());
	var img_url = $.trim(consoleDlg.find("#img_url").val());
	var reply_content = $.trim(consoleDlg.find("#reply_content").val());
      
    return {   
        "bean.id" : id,
        "bean.order_id" : order_id,
        "bean.commodity_id" : commodity_id,
        "bean.customer_id" : customer_id,
        "bean.satisfaction" : satisfaction,
        "bean.comments" : comments,
        "bean.create_time" : create_time,
        "bean.is_delete" : is_delete,
        "bean.img_url" : img_url,
        "bean.reply_content" : reply_content
    };      
}
//根据后台数据，拼装grid行数据
function initGridRowData(_bizname,data){
	var dataJson = eval(data);
    var consoleDlg = $("#"+_bizname+"_info");  
	var order_id = $.trim(consoleDlg.find("#order_id").val());	
	var commodity_id = $.trim(consoleDlg.find("#commodity_id").val());	
	var customer_id = $.trim(consoleDlg.find("#customer_id").val());	
	var satisfaction = $.trim(consoleDlg.find("#satisfaction").val());	
	var comments = $.trim(consoleDlg.find("#comments").val());	
	var create_time = $.trim(consoleDlg.find("#create_time").val());	
	var is_delete = $.trim(consoleDlg.find("#is_delete").val());	
	var img_url = $.trim(consoleDlg.find("#img_url").val());	
	var reply_content = $.trim(consoleDlg.find("#reply_content").val());	
	var dataRow = { 			
	    _rowid : dataJson.id,// 从Server端得到系统分配的id
		id : dataJson.id,// 从Server端得到系统分配的id
		order_id : order_id,
		commodity_id : commodity_id,
		customer_id : customer_id,
		satisfaction : satisfaction,
		comments : comments,
		create_time : create_time,
		is_delete : is_delete,
		img_url : img_url,
		reply_content : reply_content
	};
	return dataRow;
}
