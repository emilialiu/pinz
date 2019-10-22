//$("#biz_info").on('domi.modifysave.before' , function() {alert("测试修改保存之前事件")});
//$("#biz_info").on('domi.modifysave.after' , function() {alert("测试修改保存之后事件")});
//删除修改进入表单时传递参数
function getGridParams(_bizname){
	var selectedRowId = $("#"+_bizname+"_grid").jqGrid("getGridParam", "selrow");  
    var str = $("#"+_bizname+"_grid").jqGrid("getRowData", selectedRowId); // 根据行ID，获取选中行的数据
	return {  
        "bean.logid" : str.logid  
    };  
}
//给表单赋值
function setInfoData(_bizname,data){  
	var dataJson = eval(data);
	var rowData = dataJson[0];
    var consoleDlg = $("#"+_bizname+"_info");   
    consoleDlg.find("#logid").val(rowData.logid);  
    consoleDlg.find("#deptname").val(rowData.deptname);  
    consoleDlg.find("#loginname").val(rowData.loginname);  
    consoleDlg.find("#logintime").val(rowData.logintime);  
    consoleDlg.find("#loginip").val(rowData.loginip);
    consoleDlg.find("#loginmessage").val(rowData.loginmessage); 
}

//新增修改删除保存是获取表单参数
function getInfoData(_bizname){
    var consoleDlg = $("#"+_bizname+"_info");  
	var logid = $.trim(consoleDlg.find("#logid").val());  
    var deptname = $.trim(consoleDlg.find("#deptname").val());  
    var loginname = $.trim(consoleDlg.find("#loginname").val());  
    var logintime = $.trim(consoleDlg.find("#logintime").val());  
    var loginip = $.trim(consoleDlg.find("#loginip").val());  
    var loginmessage = $.trim(consoleDlg.find("#loginmessage").val());    
    return {  
        "bean.logid" : logid,  
        "bean.deptname" : deptname,  
        "bean.loginname" : loginname,  
        "bean.logintime" : logintime,  
        "bean.loginip" : loginip,
        "bean.loginmessage" : loginmessage
    };      
}
//根据后台数据，拼装grid行数据
function initGridRowData(_bizname,data){
	var dataJson = eval(data);
    var consoleDlg = $("#"+_bizname+"_info");  
    var rolename = $.trim(consoleDlg.find("#rolename").val());  
    var memo = $.trim(consoleDlg.find("#memo").val());  
    var deptid = $.trim(consoleDlg.find("#deptid").val());  
    var acginame = $.trim(consoleDlg.find("#acginame").val()); 
	var dataRow = {  
            logid : dataJson.id,   // 从Server端得到系统分配的id  
            deptname : deptname,  
            loginname : loginname,  
            logintime : logintime,  
            loginip : loginip  ,
            loginmessage : loginmessage 
        }; 
	return dataRow;
}
//验证
$('#biz_form').validate({
	errorElement: 'div',
	errorClass: 'help-block',
	focusInvalid: false,
	ignore: "",
	rules: {
		rolename: {
			required: true
		},
		acginame: {
			required: true
		}
	},

	messages: {
		rolename: "角色名称信息必填",
		acginame: "角色编码信息必填"
	},

	highlight: function (e) {
		$(e).closest('.form-group').removeClass('has-info').addClass('has-error');
	},

	success: function (e) {
		$(e).closest('.form-group').removeClass('has-error');//.addClass('has-info');
		$(e).remove();
	},

	errorPlacement: function (error, element) {
		if(element.is('input[type=checkbox]') || element.is('input[type=radio]')) {
			var controls = element.closest('div[class*="col-"]');
			if(controls.find(':checkbox,:radio').length > 1) controls.append(error);
			else error.insertAfter(element.nextAll('.lbl:eq(0)').eq(0));
		}
		else if(element.is('.select2')) {
			error.insertAfter(element.siblings('[class*="select2-container"]:eq(0)'));
		}
		else if(element.is('.chosen-select')) {
			error.insertAfter(element.siblings('[class*="chosen-container"]:eq(0)'));
		}
		else error.insertAfter(element.parent());
	},

	submitHandler: function (form) {
	},
	invalidHandler: function (form) {
	}
});