var addsaveurl = rootpath+"/webpage/biz/custom/enterprise/doAddSave.action";
var modifysaveurl = rootpath+"/webpage/biz/custom/enterprise/doModifySave.action";
var deletesaveurl = rootpath+"/webpage/biz/custom/enterprise/delete.action";

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
	consoleDlg.find("#phone").val(rowData.phone);
	consoleDlg.find("#description").val(rowData.description);
	consoleDlg.find("#remarks").val(rowData.remarks);
	consoleDlg.find("#demand_count").val(rowData.demand_count);
	consoleDlg.find("#create_time").val(rowData.create_time);
	consoleDlg.find("#customer_id").val(rowData.customer_id);
	consoleDlg.find("#state").val(rowData.state);
}

//新增修改删除保存是获取表单参数
function getInfoData(_bizname){
    var consoleDlg = $("#"+_bizname+"_info");  
	var id = $.trim(consoleDlg.find("#id").val());
	var name = $.trim(consoleDlg.find("#name").val());
	var phone = $.trim(consoleDlg.find("#phone").val());
	var description = $.trim(consoleDlg.find("#description").val());
	var remarks = $.trim(consoleDlg.find("#remarks").val());
	var demand_count = $.trim(consoleDlg.find("#demand_count").val());
	var create_time = $.trim(consoleDlg.find("#create_time").val());
	var customer_id = $.trim(consoleDlg.find("#customer_id").val());
	var state = $.trim(consoleDlg.find("#state").val());
      
    return {   
        "bean.id" : id,
        "bean.name" : name,
        "bean.phone" : phone,
        "bean.description" : description,
        "bean.remarks" : remarks,
        "bean.demand_count" : demand_count,
        "bean.create_time" : create_time,
        "bean.customer_id" : customer_id,
        "bean.state" : state
    };      
}
//根据后台数据，拼装grid行数据
function initGridRowData(_bizname,data){
	var dataJson = eval(data);
    var consoleDlg = $("#"+_bizname+"_info");  
	var name = $.trim(consoleDlg.find("#name").val());	
	var phone = $.trim(consoleDlg.find("#phone").val());	
	var description = $.trim(consoleDlg.find("#description").val());	
	var remarks = $.trim(consoleDlg.find("#remarks").val());	
	var demandcount = $.trim(consoleDlg.find("#demandcount option:selected").text());	
	var create_time = $.trim(consoleDlg.find("#create_time").val());	
	var customer_id = $.trim(consoleDlg.find("#customer_id").val());	
	var statename = $.trim(consoleDlg.find("#state option:selected").text());	
	var dataRow = { 			
	    _rowid : dataJson.id,// 从Server端得到系统分配的id
		id : dataJson.id,// 从Server端得到系统分配的id
		name : name,
		phone : phone,
		description : description,
		remarks : remarks,
		demandcount : demandcount,
		create_time : create_time,
		customer_id : customer_id,
		statename : statename
	};
	return dataRow;
}
