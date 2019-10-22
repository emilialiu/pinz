var addsaveurl = rootpath+"/webpage/biz/customer/adress/doAddSave.action";
var modifysaveurl = rootpath+"/webpage/biz/customer/adress/doModifySave.action";
var deletesaveurl = rootpath+"/webpage/biz/customer/adress/delete.action";

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
	consoleDlg.find("#pro_id").val(rowData.pro_id);
	consoleDlg.find("#pro_name").val(rowData.pro_name);
	consoleDlg.find("#city_id").val(rowData.city_id);
	consoleDlg.find("#city_name").val(rowData.city_name);
	consoleDlg.find("#district_id").val(rowData.district_id);
	consoleDlg.find("#district_name").val(rowData.district_name);
	consoleDlg.find("#complet_address").val(rowData.complet_address);
	consoleDlg.find("#consignee_name").val(rowData.consignee_name);
	consoleDlg.find("#phone").val(rowData.phone);
	consoleDlg.find("#is_default").val(rowData.is_default);
}

//新增修改删除保存是获取表单参数
function getInfoData(_bizname){
    var consoleDlg = $("#"+_bizname+"_info");  
	var id = $.trim(consoleDlg.find("#id").val());
	var name = $.trim(consoleDlg.find("#name").val());
	var pro_id = $.trim(consoleDlg.find("#pro_id").val());
	var pro_name = $.trim(consoleDlg.find("#pro_name").val());
	var city_id = $.trim(consoleDlg.find("#city_id").val());
	var city_name = $.trim(consoleDlg.find("#city_name").val());
	var district_id = $.trim(consoleDlg.find("#district_id").val());
	var district_name = $.trim(consoleDlg.find("#district_name").val());
	var complet_address = $.trim(consoleDlg.find("#complet_address").val());
	var consignee_name = $.trim(consoleDlg.find("#consignee_name").val());
	var phone = $.trim(consoleDlg.find("#phone").val());
	var is_default = $.trim(consoleDlg.find("#is_default").val());
      
    return {   
        "bean.id" : id,
        "bean.name" : name,
        "bean.pro_id" : pro_id,
        "bean.pro_name" : pro_name,
        "bean.city_id" : city_id,
        "bean.city_name" : city_name,
        "bean.district_id" : district_id,
        "bean.district_name" : district_name,
        "bean.complet_address" : complet_address,
        "bean.consignee_name" : consignee_name,
        "bean.phone" : phone,
        "bean.is_default" : is_default
    };      
}
//根据后台数据，拼装grid行数据
function initGridRowData(_bizname,data){
	var dataJson = eval(data);
    var consoleDlg = $("#"+_bizname+"_info");  
	var name = $.trim(consoleDlg.find("#name").val());	
	var pro_id = $.trim(consoleDlg.find("#pro_id").val());	
	var pro_name = $.trim(consoleDlg.find("#pro_name").val());	
	var city_id = $.trim(consoleDlg.find("#city_id").val());	
	var city_name = $.trim(consoleDlg.find("#city_name").val());	
	var district_id = $.trim(consoleDlg.find("#district_id").val());	
	var district_name = $.trim(consoleDlg.find("#district_name").val());	
	var complet_address = $.trim(consoleDlg.find("#complet_address").val());	
	var consignee_name = $.trim(consoleDlg.find("#consignee_name").val());	
	var phone = $.trim(consoleDlg.find("#phone").val());	
	var is_default = $.trim(consoleDlg.find("#is_default").val());	
	var dataRow = { 			
	    _rowid : dataJson.id,// 从Server端得到系统分配的id
		id : dataJson.id,// 从Server端得到系统分配的id
		name : name,
		pro_id : pro_id,
		pro_name : pro_name,
		city_id : city_id,
		city_name : city_name,
		district_id : district_id,
		district_name : district_name,
		complet_address : complet_address,
		consignee_name : consignee_name,
		phone : phone,
		is_default : is_default
	};
	return dataRow;
}
