var addsaveurl = rootpath+"/webpage/biz/dz/order/doAddSave.action";
var modifysaveurl = rootpath+"/webpage/biz/dz/order/doModifySave.action";
var deletesaveurl = rootpath+"/webpage/biz/dz/order/delete.action";

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
	consoleDlg.find("#customer_id").val(rowData.customer_id);
	consoleDlg.find("#total_money").val(rowData.total_money);
	consoleDlg.find("#deposit_paid").val(rowData.deposit_paid);
	consoleDlg.find("#deposit_paid_time").val(rowData.deposit_paid_time);
	consoleDlg.find("#final_payment").val(rowData.final_payment);
	consoleDlg.find("#freight").val(rowData.freight);
	consoleDlg.find("#state").val(rowData.state);
	consoleDlg.find("#is_cancel").val(rowData.is_cancel);
	consoleDlg.find("#create_time").val(rowData.create_time);
	consoleDlg.find("#create_by").val(rowData.create_by);
	consoleDlg.find("#update_time").val(rowData.update_time);
	consoleDlg.find("#update_by").val(rowData.update_by);
	consoleDlg.find("#payment_time").val(rowData.payment_time);
	consoleDlg.find("#payment_money").val(rowData.payment_money);
	consoleDlg.find("#logistics_number").val(rowData.logistics_number);
}

//新增修改删除保存是获取表单参数
function getInfoData(_bizname){
    var consoleDlg = $("#"+_bizname+"_info");  
	var id = $.trim(consoleDlg.find("#id").val());
	var order_code = $.trim(consoleDlg.find("#order_code").val());
	var customer_id = $.trim(consoleDlg.find("#customer_id").val());
	var total_money = $.trim(consoleDlg.find("#total_money").val());
	var deposit_paid = $.trim(consoleDlg.find("#deposit_paid").val());
	var deposit_paid_time = $.trim(consoleDlg.find("#deposit_paid_time").val());
	var final_payment = $.trim(consoleDlg.find("#final_payment").val());
	var freight = $.trim(consoleDlg.find("#freight").val());
	var state = $.trim(consoleDlg.find("#state").val());
	var is_cancel = $.trim(consoleDlg.find("#is_cancel option:selected").val());
	var create_time = $.trim(consoleDlg.find("#create_time").val());
	var create_by = $.trim(consoleDlg.find("#create_by").val());
	var update_time = $.trim(consoleDlg.find("#update_time").val());
	var update_by = $.trim(consoleDlg.find("#update_by").val());
	var payment_time = $.trim(consoleDlg.find("#payment_time").val());
	var payment_money = $.trim(consoleDlg.find("#payment_money").val());
	var logistics_number = $.trim(consoleDlg.find("#logistics_number").val());
      
    return {   
        "bean.id" : id,
        "bean.order_code" : order_code,
        "bean.customer_id" : customer_id,
        "bean.total_money" : total_money,
        "bean.deposit_paid" : deposit_paid,
        "bean.deposit_paid_time" : deposit_paid_time,
        "bean.final_payment" : final_payment,
        "bean.freight" : freight,
        "bean.state" : state,
        "bean.is_cancel" : is_cancel,
        "bean.create_time" : create_time,
        "bean.create_by" : create_by,
        "bean.update_time" : update_time,
        "bean.update_by" : update_by,
        "bean.payment_time" : payment_time,
        "bean.payment_money" : payment_money,
        "bean.logistics_number" : logistics_number
    };      
}
//根据后台数据，拼装grid行数据
function initGridRowData(_bizname,data){
	var dataJson = eval(data);
    var consoleDlg = $("#"+_bizname+"_info");  
	var order_code = $.trim(consoleDlg.find("#order_code").val());	
	var customer_id = $.trim(consoleDlg.find("#customer_id").val());	
	var total_money = $.trim(consoleDlg.find("#total_money").val());	
	var deposit_paid = $.trim(consoleDlg.find("#deposit_paid").val());	
	var deposit_paid_time = $.trim(consoleDlg.find("#deposit_paid_time").val());	
	var final_payment = $.trim(consoleDlg.find("#final_payment").val());	
	var freight = $.trim(consoleDlg.find("#freight").val());	
	var state = $.trim(consoleDlg.find("#state").val());	
	var iscancelname = $.trim(consoleDlg.find("#is_cancel option:selected").text());	
	var create_time = $.trim(consoleDlg.find("#create_time").val());	
	var create_by = $.trim(consoleDlg.find("#create_by").val());	
	var update_time = $.trim(consoleDlg.find("#update_time").val());	
	var update_by = $.trim(consoleDlg.find("#update_by").val());	
	var payment_time = $.trim(consoleDlg.find("#payment_time").val());	
	var payment_money = $.trim(consoleDlg.find("#payment_money").val());	
	var logistics_number = $.trim(consoleDlg.find("#logistics_number").val());	
	var dataRow = { 			
	    _rowid : dataJson.id,// 从Server端得到系统分配的id
		id : dataJson.id,// 从Server端得到系统分配的id
		order_code : order_code,
		customer_id : customer_id,
		total_money : total_money,
		deposit_paid : deposit_paid,
		deposit_paid_time : deposit_paid_time,
		final_payment : final_payment,
		freight : freight,
		state : state,
		iscancelname : iscancelname,
		create_time : create_time,
		create_by : create_by,
		update_time : update_time,
		update_by : update_by,
		payment_time : payment_time,
		payment_money : payment_money,
		logistics_number : logistics_number
	};
	return dataRow;
}
