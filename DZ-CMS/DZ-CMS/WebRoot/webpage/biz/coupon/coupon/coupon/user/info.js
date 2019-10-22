var addsaveurl = rootpath+"/webpage/biz/coupon/user/doAddSave.action";
var modifysaveurl = rootpath+"/webpage/biz/coupon/user/doModifySave.action";
var deletesaveurl = rootpath+"/webpage/biz/coupon/user/delete.action";

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
	consoleDlg.find("#customer_id").val(rowData.customer_id);
	consoleDlg.find("#create_time").val(rowData.create_time);
	consoleDlg.find("#state").val(rowData.state);
	consoleDlg.find("#expire_time").val(rowData.expire_time);
	consoleDlg.find("#coupon_amount").val(rowData.coupon_amount);
}

//新增修改删除保存是获取表单参数
function getInfoData(_bizname){
    var consoleDlg = $("#"+_bizname+"_info");  
	var id = $.trim(consoleDlg.find("#id").val());
	var order_id = $.trim(consoleDlg.find("#order_id").val());
	var customer_id = $.trim(consoleDlg.find("#customer_id").val());
	var create_time = $.trim(consoleDlg.find("#create_time").val());
	var state = $.trim(consoleDlg.find("#state").val());
	var expire_time = $.trim(consoleDlg.find("#expire_time").val());
	var coupon_amount = $.trim(consoleDlg.find("#coupon_amount").val());
      
    return {   
        "bean.id" : id,
        "bean.order_id" : order_id,
        "bean.customer_id" : customer_id,
        "bean.create_time" : create_time,
        "bean.state" : state,
        "bean.expire_time" : expire_time,
        "bean.coupon_amount" : coupon_amount
    };      
}
//根据后台数据，拼装grid行数据
function initGridRowData(_bizname,data){
	var dataJson = eval(data);
    var consoleDlg = $("#"+_bizname+"_info");  
	var order_id = $.trim(consoleDlg.find("#order_id").val());	
	var customer_id = $.trim(consoleDlg.find("#customer_id").val());	
	var create_time = $.trim(consoleDlg.find("#create_time").val());	
	var state = $.trim(consoleDlg.find("#state").val());	
	var expire_time = $.trim(consoleDlg.find("#expire_time").val());	
	var coupon_amount = $.trim(consoleDlg.find("#coupon_amount").val());	
	var dataRow = { 			
	    _rowid : dataJson.id,// 从Server端得到系统分配的id
		id : dataJson.id,// 从Server端得到系统分配的id
		order_id : order_id,
		customer_id : customer_id,
		create_time : create_time,
		state : state,
		expire_time : expire_time,
		coupon_amount : coupon_amount
	};
	return dataRow;
}
