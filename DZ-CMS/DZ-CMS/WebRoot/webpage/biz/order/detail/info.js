var addsaveurl = rootpath+"/webpage/biz/order/detail/doAddSave.action";
var modifysaveurl = rootpath+"/webpage/biz/order/detail/doModifySave.action";
var deletesaveurl = rootpath+"/webpage/biz/order/detail/delete.action";

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
	consoleDlg.find("#order_code").val(rowData.order_code);
	consoleDlg.find("#commodity_id").val(rowData.commodity_id);
	consoleDlg.find("#sku_content").val(rowData.sku_content);
	consoleDlg.find("#style_content").val(rowData.style_content);
	consoleDlg.find("#custome_size_content").val(rowData.custome_size_content);
	consoleDlg.find("#commodity_title").val(rowData.commodity_title);
	consoleDlg.find("#buy_count").val(rowData.buy_count);
	consoleDlg.find("#product_price").val(rowData.product_price);
	consoleDlg.find("#ear_money").val(rowData.ear_money);
	consoleDlg.find("#commodity_img").val(rowData.commodity_img);
	consoleDlg.find("#tail_money").val(rowData.tail_money);
	consoleDlg.find("#linear_number").val(rowData.linear_number);
	consoleDlg.find("#linear_material_name").val(rowData.linear_material_name);
}

//新增修改删除保存是获取表单参数
function getInfoData(_bizname){
    var consoleDlg = $("#"+_bizname+"_info");  
	var id = $.trim(consoleDlg.find("#id").val());
	var order_code = $.trim(consoleDlg.find("#order_code").val());
	var commodity_id = $.trim(consoleDlg.find("#commodity_id").val());
	var sku_content = $.trim(consoleDlg.find("#sku_content").val());
	var style_content = $.trim(consoleDlg.find("#style_content").val());
	var custome_size_content = $.trim(consoleDlg.find("#custome_size_content").val());
	var commodity_title = $.trim(consoleDlg.find("#commodity_title").val());
	var buy_count = $.trim(consoleDlg.find("#buy_count").val());
	var product_price = $.trim(consoleDlg.find("#product_price").val());
	var ear_money = $.trim(consoleDlg.find("#ear_money").val());
	var commodity_img = $.trim(consoleDlg.find("#commodity_img").val());
	var tail_money = $.trim(consoleDlg.find("#tail_money").val());
	var linear_number = $.trim(consoleDlg.find("#linear_number").val());
	var linear_material_name = $.trim(consoleDlg.find("#linear_material_name").val());
      
    return {   
        "bean.id" : id,
        "bean.order_code" : order_code,
        "bean.commodity_id" : commodity_id,
        "bean.sku_content" : sku_content,
        "bean.style_content" : style_content,
        "bean.custome_size_content" : custome_size_content,
        "bean.commodity_title" : commodity_title,
        "bean.buy_count" : buy_count,
        "bean.product_price" : product_price,
        "bean.ear_money" : ear_money,
        "bean.commodity_img" : commodity_img,
        "bean.tail_money" : tail_money,
        "bean.linear_number" : linear_number,
        "bean.linear_material_name" : linear_material_name
    };      
}
//根据后台数据，拼装grid行数据
function initGridRowData(_bizname,data){
	var dataJson = eval(data);
    var consoleDlg = $("#"+_bizname+"_info");  
	var order_code = $.trim(consoleDlg.find("#order_code").val());	
	var commodity_id = $.trim(consoleDlg.find("#commodity_id").val());	
	var sku_content = $.trim(consoleDlg.find("#sku_content").val());	
	var style_content = $.trim(consoleDlg.find("#style_content").val());	
	var custome_size_content = $.trim(consoleDlg.find("#custome_size_content").val());	
	var commodity_title = $.trim(consoleDlg.find("#commodity_title").val());	
	var buy_count = $.trim(consoleDlg.find("#buy_count").val());	
	var product_price = $.trim(consoleDlg.find("#product_price").val());	
	var ear_money = $.trim(consoleDlg.find("#ear_money").val());	
	var commodity_img = $.trim(consoleDlg.find("#commodity_img").val());	
	var tail_money = $.trim(consoleDlg.find("#tail_money").val());	
	var linear_number = $.trim(consoleDlg.find("#linear_number").val());	
	var linear_material_name = $.trim(consoleDlg.find("#linear_material_name").val());	
	var dataRow = { 			
	    _rowid : dataJson.id,// 从Server端得到系统分配的id
		id : dataJson.id,// 从Server端得到系统分配的id
		order_code : order_code,
		commodity_id : commodity_id,
		sku_content : sku_content,
		style_content : style_content,
		custome_size_content : custome_size_content,
		commodity_title : commodity_title,
		buy_count : buy_count,
		product_price : product_price,
		ear_money : ear_money,
		commodity_img : commodity_img,
		tail_money : tail_money,
		linear_number : linear_number,
		linear_material_name : linear_material_name
	};
	return dataRow;
}
