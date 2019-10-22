var addsaveurl = rootpath+"/webpage/biz/customer/size/doAddSave.action";
var modifysaveurl = rootpath+"/webpage/biz/customer/size/doModifySave.action";
var deletesaveurl = rootpath+"/webpage/biz/customer/size/delete.action";

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
	consoleDlg.find("#customer_id").val(rowData.customer_id);
	consoleDlg.find("#name").val(rowData.name);
	consoleDlg.find("#sex").val(rowData.sex);
	consoleDlg.find("#year").val(rowData.year);
	consoleDlg.find("#height").val(rowData.height);
	consoleDlg.find("#weight").val(rowData.weight);
	consoleDlg.find("#waist").val(rowData.waist);
	consoleDlg.find("#bust").val(rowData.bust);
	consoleDlg.find("#clothes_length").val(rowData.clothes_length);
	consoleDlg.find("#arm_length").val(rowData.arm_length);
	consoleDlg.find("#arm_circum").val(rowData.arm_circum);
	consoleDlg.find("#shoulder_width").val(rowData.shoulder_width);
	consoleDlg.find("#content").val(rowData.content);
	consoleDlg.find("#headlight_url").val(rowData.headlight_url);
	consoleDlg.find("#side_shot_url").val(rowData.side_shot_url);
	consoleDlg.find("#back_photo_url").val(rowData.back_photo_url);
	consoleDlg.find("#imgs_url").val(rowData.imgs_url);
	consoleDlg.find("#is_default").val(rowData.is_default);
	consoleDlg.find("#remarks").val(rowData.remarks);
}

//新增修改删除保存是获取表单参数
function getInfoData(_bizname){
    var consoleDlg = $("#"+_bizname+"_info");  
	var id = $.trim(consoleDlg.find("#id").val());
	var customer_id = $.trim(consoleDlg.find("#customer_id").val());
	var name = $.trim(consoleDlg.find("#name").val());
	var sex = $.trim(consoleDlg.find("#sex").val());
	var year = $.trim(consoleDlg.find("#year").val());
	var height = $.trim(consoleDlg.find("#height").val());
	var weight = $.trim(consoleDlg.find("#weight").val());
	var waist = $.trim(consoleDlg.find("#waist").val());
	var bust = $.trim(consoleDlg.find("#bust").val());
	var clothes_length = $.trim(consoleDlg.find("#clothes_length").val());
	var arm_length = $.trim(consoleDlg.find("#arm_length").val());
	var arm_circum = $.trim(consoleDlg.find("#arm_circum").val());
	var shoulder_width = $.trim(consoleDlg.find("#shoulder_width").val());
	var content = $.trim(consoleDlg.find("#content").val());
	var headlight_url = $.trim(consoleDlg.find("#headlight_url").val());
	var side_shot_url = $.trim(consoleDlg.find("#side_shot_url").val());
	var back_photo_url = $.trim(consoleDlg.find("#back_photo_url").val());
	var imgs_url = $.trim(consoleDlg.find("#imgs_url").val());
	var is_default = $.trim(consoleDlg.find("#is_default").val());
	var remarks = $.trim(consoleDlg.find("#remarks").val());
      
    return {   
        "bean.id" : id,
        "bean.customer_id" : customer_id,
        "bean.name" : name,
        "bean.sex" : sex,
        "bean.year" : year,
        "bean.height" : height,
        "bean.weight" : weight,
        "bean.waist" : waist,
        "bean.bust" : bust,
        "bean.clothes_length" : clothes_length,
        "bean.arm_length" : arm_length,
        "bean.arm_circum" : arm_circum,
        "bean.shoulder_width" : shoulder_width,
        "bean.content" : content,
        "bean.headlight_url" : headlight_url,
        "bean.side_shot_url" : side_shot_url,
        "bean.back_photo_url" : back_photo_url,
        "bean.imgs_url" : imgs_url,
        "bean.is_default" : is_default,
        "bean.remarks" : remarks
    };      
}
//根据后台数据，拼装grid行数据
function initGridRowData(_bizname,data){
	var dataJson = eval(data);
    var consoleDlg = $("#"+_bizname+"_info");  
	var customer_id = $.trim(consoleDlg.find("#customer_id").val());	
	var name = $.trim(consoleDlg.find("#name").val());	
	var sex = $.trim(consoleDlg.find("#sex").val());	
	var year = $.trim(consoleDlg.find("#year").val());	
	var height = $.trim(consoleDlg.find("#height").val());	
	var weight = $.trim(consoleDlg.find("#weight").val());	
	var waist = $.trim(consoleDlg.find("#waist").val());	
	var bust = $.trim(consoleDlg.find("#bust").val());	
	var clothes_length = $.trim(consoleDlg.find("#clothes_length").val());	
	var arm_length = $.trim(consoleDlg.find("#arm_length").val());	
	var arm_circum = $.trim(consoleDlg.find("#arm_circum").val());	
	var shoulder_width = $.trim(consoleDlg.find("#shoulder_width").val());	
	var content = $.trim(consoleDlg.find("#content").val());	
	var headlight_url = $.trim(consoleDlg.find("#headlight_url").val());	
	var side_shot_url = $.trim(consoleDlg.find("#side_shot_url").val());	
	var back_photo_url = $.trim(consoleDlg.find("#back_photo_url").val());	
	var imgs_url = $.trim(consoleDlg.find("#imgs_url").val());	
	var is_default = $.trim(consoleDlg.find("#is_default").val());	
	var remarks = $.trim(consoleDlg.find("#remarks").val());	
	var dataRow = { 			
	    _rowid : dataJson.id,// 从Server端得到系统分配的id
		id : dataJson.id,// 从Server端得到系统分配的id
		customer_id : customer_id,
		name : name,
		sex : sex,
		year : year,
		height : height,
		weight : weight,
		waist : waist,
		bust : bust,
		clothes_length : clothes_length,
		arm_length : arm_length,
		arm_circum : arm_circum,
		shoulder_width : shoulder_width,
		content : content,
		headlight_url : headlight_url,
		side_shot_url : side_shot_url,
		back_photo_url : back_photo_url,
		imgs_url : imgs_url,
		is_default : is_default,
		remarks : remarks
	};
	return dataRow;
}
