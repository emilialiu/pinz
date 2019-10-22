var addsaveurl = rootpath+"/webpage/biz/dz/customer/doAddSave.action";
var modifysaveurl = rootpath+"/webpage/biz/dz/customer/doModifySave.action";
var deletesaveurl = rootpath+"/webpage/biz/dz/customer/delete.action";

//删除修改进入表单时传递参数
function getIDVal(){
		return {"bean.uid" : uid};
}

//给表单赋值
function setInfoData(_bizname,data){  
	var dataJson = eval(data);
	var rowData = dataJson[0];
    var consoleDlg = $("#"+_bizname+"_info");
	consoleDlg.find("#uid").val(rowData.uid);
	consoleDlg.find("#openid").val(rowData.openid);
	consoleDlg.find("#vip_level").val(rowData.vip_level);
	consoleDlg.find("#name").val(rowData.name);
	consoleDlg.find("#nick_name").val(rowData.nick_name);
	consoleDlg.find("#head_url").val(rowData.head_url);
	consoleDlg.find("#integral_num").val(rowData.integral_num);
	consoleDlg.find("#sex").val(rowData.sex);
	consoleDlg.find("#union_id").val(rowData.union_id);
	consoleDlg.find("#city").val(rowData.city);
	consoleDlg.find("#province").val(rowData.province);
	consoleDlg.find("#country").val(rowData.country);
	consoleDlg.find("#create_time").val(rowData.create_time);
	consoleDlg.find("#last_logon_time").val(rowData.last_logon_time);
	consoleDlg.find("#belong_agent").val(rowData.belong_agent);
}

//新增修改删除保存是获取表单参数
function getInfoData(_bizname){
    var consoleDlg = $("#"+_bizname+"_info");  
	var uid = $.trim(consoleDlg.find("#uid").val());
	var openid = $.trim(consoleDlg.find("#openid").val());
	var vip_level = $.trim(consoleDlg.find("#vip_level option:selected").val());
	var name = $.trim(consoleDlg.find("#name").val());
	var nick_name = $.trim(consoleDlg.find("#nick_name").val());
	var head_url = $.trim(consoleDlg.find("#head_url").val());
	var integral_num = $.trim(consoleDlg.find("#integral_num").val());
	var sex = $.trim(consoleDlg.find("#sex option:selected").val());
	var union_id = $.trim(consoleDlg.find("#union_id").val());
	var city = $.trim(consoleDlg.find("#city").val());
	var province = $.trim(consoleDlg.find("#province").val());
	var country = $.trim(consoleDlg.find("#country").val());
	var create_time = $.trim(consoleDlg.find("#create_time").val());
	var last_logon_time = $.trim(consoleDlg.find("#last_logon_time").val());
	var belong_agent = $.trim(consoleDlg.find("#belong_agent").val());
      
    return {   
        "bean.uid" : uid,
        "bean.openid" : openid,
        "bean.vip_level" : vip_level,
        "bean.name" : name,
        "bean.nick_name" : nick_name,
        "bean.head_url" : head_url,
        "bean.integral_num" : integral_num,
        "bean.sex" : sex,
        "bean.union_id" : union_id,
        "bean.city" : city,
        "bean.province" : province,
        "bean.country" : country,
        "bean.create_time" : create_time,
        "bean.last_logon_time" : last_logon_time,
        "bean.belong_agent" : belong_agent
    };      
}
//根据后台数据，拼装grid行数据
function initGridRowData(_bizname,data){
	var dataJson = eval(data);
    var consoleDlg = $("#"+_bizname+"_info");  
	var openid = $.trim(consoleDlg.find("#openid").val());	
	var vip_level = $.trim(consoleDlg.find("#vip_level").val());	
	var vip_levelname =   $.trim(consoleDlg.find("#vip_level option:selected").text());
	var name = $.trim(consoleDlg.find("#name").val());	
	var nick_name = $.trim(consoleDlg.find("#nick_name").val());	
	var head_url = $.trim(consoleDlg.find("#head_url").val());	
	var integral_num = $.trim(consoleDlg.find("#integral_num").val());	
	var sex = $.trim(consoleDlg.find("#sex").val());	
	var sexname = $.trim(consoleDlg.find("#sex option:selected").text());
	var union_id = $.trim(consoleDlg.find("#union_id").val());	
	var city = $.trim(consoleDlg.find("#city").val());	
	var province = $.trim(consoleDlg.find("#province").val());	
	var country = $.trim(consoleDlg.find("#country").val());	
	var create_time = $.trim(consoleDlg.find("#create_time").val());	
	var last_logon_time = $.trim(consoleDlg.find("#last_logon_time").val());	
	var belong_agent = $.trim(consoleDlg.find("#belong_agent").val());	
	var dataRow = { 			
	    _rowid : dataJson.uid,// 从Server端得到系统分配的id
		uid : dataJson.uid,// 从Server端得到系统分配的id
		openid : openid,
		vip_level : vip_level,
		vip_levelname:vip_levelname,
		name : name,
		nick_name : nick_name,
		head_url : head_url,
		integral_num : integral_num,
		sex : sex,
		sexname:sexname,
		union_id : union_id,
		city : city,
		province : province,
		country : country,
		create_time : create_time,
		last_logon_time : last_logon_time,
		belong_agent : belong_agent
	};
	return dataRow;
}
