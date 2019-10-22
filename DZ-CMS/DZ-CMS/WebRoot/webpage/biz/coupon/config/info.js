var addsaveurl = rootpath+"/webpage/biz/coupon/config/doAddSave.action";
var modifysaveurl = rootpath+"/webpage/biz/coupon/config/doModifySave.action";
var deletesaveurl = rootpath+"/webpage/biz/coupon/config/delete.action";

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
	consoleDlg.find("#type_id").val(rowData.type_id);
	consoleDlg.find("#expired_time").val(rowData.expired_time);
	consoleDlg.find("#expired_num").val(rowData.expired_num);
	consoleDlg.find("#remarks").val(rowData.remarks);
	consoleDlg.find("#voucher_amount").val(rowData.voucher_amount);
	consoleDlg.find("#commodity_id").val(rowData.commodity_id);
	consoleDlg.find("#member_level").val(rowData.member_level);
	consoleDlg.find("#commodity_type_id").val(rowData.commodity_type_id);
	consoleDlg.find("#create_time").val(rowData.create_time);
	consoleDlg.find("#create_by").val(rowData.create_by);
	consoleDlg.find("#update_time").val(rowData.update_time);
	consoleDlg.find("#update_by").val(rowData.update_by);
	consoleDlg.find("#state").val(rowData.state);
}

//新增修改删除保存是获取表单参数
function getInfoData(_bizname){
    var consoleDlg = $("#"+_bizname+"_info");  
	var id = $.trim(consoleDlg.find("#id").val());
	var type_id = $.trim(consoleDlg.find("#type_id option:selected").val());
	var expired_time = $.trim(consoleDlg.find("#expired_time").val());
	var expired_num = $.trim(consoleDlg.find("#expired_num").val());
	var remarks = $.trim(consoleDlg.find("#remarks").val());
	var voucher_amount = $.trim(consoleDlg.find("#voucher_amount").val());
	var commodity_id = $.trim(consoleDlg.find("#commodity_id").val());
	var member_level = $.trim(consoleDlg.find("#member_level").val());
	var commodity_type_id = $.trim(consoleDlg.find("#commodity_type_id").val());
	var create_time = $.trim(consoleDlg.find("#create_time").val());
	var create_by = $.trim(consoleDlg.find("#create_by").val());
	var update_time = $.trim(consoleDlg.find("#update_time").val());
	var update_by = $.trim(consoleDlg.find("#update_by").val());
	var state = $.trim(consoleDlg.find("#state option:selected").val());
      
    return {   
        "bean.id" : id,
        "bean.type_id" : type_id,
        "bean.expired_time" : expired_time,
        "bean.expired_num" : expired_num,
        "bean.remarks" : remarks,
        "bean.voucher_amount" : voucher_amount,
        "bean.commodity_id" : commodity_id,
        "bean.member_level" : member_level,
        "bean.commodity_type_id" : commodity_type_id,
        "bean.create_time" : create_time,
        "bean.create_by" : create_by,
        "bean.update_time" : update_time,
        "bean.update_by" : update_by,
        "bean.state" : state
    };      
}
//根据后台数据，拼装grid行数据
function initGridRowData(_bizname,data){
	var dataJson = eval(data);
    var consoleDlg = $("#"+_bizname+"_info");  
	var type_id = $.trim(consoleDlg.find("#type_id").val());	
	var expired_time = $.trim(consoleDlg.find("#expired_time").val());	
	var expired_num = $.trim(consoleDlg.find("#expired_num").val());	
	var remarks = $.trim(consoleDlg.find("#remarks").val());	
	var voucher_amount = $.trim(consoleDlg.find("#voucher_amount").val());	
	var commodityid = $.trim(consoleDlg.find("#commodityid").val());	
	var memberlevel = $.trim(consoleDlg.find("#member_level option:selected").text());
	var commoditytypeid = $.trim(consoleDlg.find("#commoditytypeid").val());	
	var create_time = $.trim(consoleDlg.find("#create_time").val());	
	var create_by = $.trim(consoleDlg.find("#create_by").val());	
	var update_time = $.trim(consoleDlg.find("#update_time").val());	
	var update_by = $.trim(consoleDlg.find("#update_by").val());	
	var statename = $.trim(consoleDlg.find("#state option:selected").text());
	var dataRow = { 			
	    _rowid : dataJson.id,// 从Server端得到系统分配的id
		id : dataJson.id,// 从Server端得到系统分配的id
		type_id : type_id,
		expired_time : expired_time,
		expired_num : expired_num,
		remarks : remarks,
		voucher_amount : voucher_amount,
		commodityid : commodityid,
		memberlevel : memberlevel,
		commoditytypeid : commoditytypeid,
		create_time : create_time,
		create_by : create_by,
		update_time : update_time,
		update_by : update_by,
		statename : statename
	};
	return dataRow;
}
