var addsaveurl = rootpath+"/webpage/biz/commodity/base/doAddSave.action";
var modifysaveurl = rootpath+"/webpage/biz/commodity/base/doModifySave.action";
var deletesaveurl = rootpath+"/webpage/biz/commodity/base/delete.action";

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
	consoleDlg.find("#title").val(rowData.title);
	consoleDlg.find("#product_category_id").val(rowData.product_category_id);
	consoleDlg.find("#code").val(rowData.code);
	consoleDlg.find("#fabric_zhishu").val(rowData.fabric_zhishu);
	consoleDlg.find("#fabric_thickness").val(rowData.fabric_thickness);
	consoleDlg.find("#fabric_weight").val(rowData.fabric_weight);
	consoleDlg.find("#fabric_elasticity").val(rowData.fabric_elasticity);
	consoleDlg.find("#work_season").val(rowData.work_season);
	consoleDlg.find("#fabric_ingredient").val(rowData.fabric_ingredient);
	consoleDlg.find("#banner_img").val(rowData.banner_img);
	consoleDlg.find("#context_img").val(rowData.context_img);
	consoleDlg.find("#original_price").val(rowData.original_price);
	consoleDlg.find("#earnest_money").val(rowData.earnest_money);
	consoleDlg.find("#seckill_price").val(rowData.seckill_price);
	consoleDlg.find("#is_hot_money").val(rowData.is_hot_money);
	consoleDlg.find("#is_new_product").val(rowData.is_new_product);
	consoleDlg.find("#is_recommend").val(rowData.is_recommend);
	consoleDlg.find("#sales").val(rowData.sales);
	consoleDlg.find("#stock").val(rowData.stock);
}

//新增修改删除保存是获取表单参数
function getInfoData(_bizname){
    var consoleDlg = $("#"+_bizname+"_info");  
	var id = $.trim(consoleDlg.find("#id").val());
	var title = $.trim(consoleDlg.find("#title").val());
	var product_category_id = $.trim(consoleDlg.find("#product_category_id").val());
	var code = $.trim(consoleDlg.find("#code").val());
	var fabric_zhishu = $.trim(consoleDlg.find("#fabric_zhishu").val());
	var fabric_thickness = $.trim(consoleDlg.find("#fabric_thickness").val());
	var fabric_weight = $.trim(consoleDlg.find("#fabric_weight").val());
	var fabric_elasticity = $.trim(consoleDlg.find("#fabric_elasticity").val());
	var work_season = $.trim(consoleDlg.find("#work_season").val());
	var fabric_ingredient = $.trim(consoleDlg.find("#fabric_ingredient").val());
	var banner_img = $.trim(consoleDlg.find("#banner_img").val());
	var context_img = $.trim(consoleDlg.find("#context_img").val());
	var original_price = $.trim(consoleDlg.find("#original_price").val());
	var earnest_money = $.trim(consoleDlg.find("#earnest_money").val());
	var seckill_price = $.trim(consoleDlg.find("#seckill_price").val());
	var is_hot_money = $.trim(consoleDlg.find("#is_hot_money option:selected").val());
	var is_new_product = $.trim(consoleDlg.find("#is_new_product option:selected").val());
	var is_recommend = $.trim(consoleDlg.find("#is_recommend option:selected").val());
	var sales = $.trim(consoleDlg.find("#sales").val());
	var stock = $.trim(consoleDlg.find("#stock").val());
      
    return {   
        "bean.id" : id,
        "bean.title" : title,
        "bean.product_category_id" : product_category_id,
        "bean.code" : code,
        "bean.fabric_zhishu" : fabric_zhishu,
        "bean.fabric_thickness" : fabric_thickness,
        "bean.fabric_weight" : fabric_weight,
        "bean.fabric_elasticity" : fabric_elasticity,
        "bean.work_season" : work_season,
        "bean.fabric_ingredient" : fabric_ingredient,
        "bean.banner_img" : banner_img,
        "bean.context_img" : context_img,
        "bean.original_price" : original_price,
        "bean.earnest_money" : earnest_money,
        "bean.seckill_price" : seckill_price,
        "bean.is_hot_money" : is_hot_money,
        "bean.is_new_product" : is_new_product,
        "bean.is_recommend" : is_recommend,
        "bean.sales" : sales,
        "bean.stock" : stock
    };      
}
//根据后台数据，拼装grid行数据
function initGridRowData(_bizname,data){
	var dataJson = eval(data);
    var consoleDlg = $("#"+_bizname+"_info");  
	var title = $.trim(consoleDlg.find("#title").val());	
	var product_category_id = $.trim(consoleDlg.find("#product_category_id").val());	
	var productcategoryid = $.trim(consoleDlg.find("#product_category_id option:selected").text());	
	var code = $.trim(consoleDlg.find("#code").val());	
	var fabric_zhishu = $.trim(consoleDlg.find("#fabric_zhishu").val());	
	var fabric_thickness = $.trim(consoleDlg.find("#fabric_thickness").val());	
	var fabric_weight = $.trim(consoleDlg.find("#fabric_weight").val());	
	var fabric_elasticity = $.trim(consoleDlg.find("#fabric_elasticity").val());	
	var work_season = $.trim(consoleDlg.find("#work_season").val());	
	var fabric_ingredient = $.trim(consoleDlg.find("#fabric_ingredient").val());	
	var banner_img = $.trim(consoleDlg.find("#banner_img").val());	
	var context_img = $.trim(consoleDlg.find("#context_img").val());	
	var original_price = $.trim(consoleDlg.find("#original_price").val());	
	var earnest_money = $.trim(consoleDlg.find("#earnest_money").val());	
	var seckill_price = $.trim(consoleDlg.find("#seckill_price").val());	
	var ishotmoney = $.trim(consoleDlg.find("#is_hot_money option:selected").text());;	
	var isnewproduct = $.trim(consoleDlg.find("#is_new_product option:selected").text());	
	var isrecommend = $.trim(consoleDlg.find("#is_recommend option:selected").text());
	var sales = $.trim(consoleDlg.find("#sales").val());	
	var stock = $.trim(consoleDlg.find("#stock").val());	
	var dataRow = { 			
	    _rowid : dataJson.id,// 从Server端得到系统分配的id
		id : dataJson.id,// 从Server端得到系统分配的id
		title : title,
		product_category_id : product_category_id,
		productcategoryid:productcategoryid,
		code : code,
		fabric_zhishu : fabric_zhishu,
		fabric_thickness : fabric_thickness,
		fabric_weight : fabric_weight,
		fabric_elasticity : fabric_elasticity,
		work_season : work_season,
		fabric_ingredient : fabric_ingredient,
		banner_img : banner_img,
		context_img : context_img,
		original_price : original_price,
		earnest_money : earnest_money,
		seckill_price : seckill_price,
		ishotmoney : ishotmoney,
		isnewproduct : isnewproduct,
		isrecommend : isrecommend,
		sales : sales,
		stock : stock
	};
	return dataRow;
}
