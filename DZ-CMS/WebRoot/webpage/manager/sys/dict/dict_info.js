var addsaveurl = rootpath + "/manager/sys/dict/adddict.action";
var modifysaveurl = rootpath + "/manager/sys/dict/modifydict.action";
var deletesaveurl = rootpath + "/manager/sys/dict/deletedict.action";

// 删除修改进入表单时传递参数
function getIDVal() {
	return {
		"bean.code" : code
	};
}
// 给表单赋值
function setInfoData(_bizname, data) {
	var dataJson = eval(data);
	alert(dataJson);
	var rowData = dataJson[0];
	var consoleDlg = $("#" + _bizname + "_info");
	consoleDlg.find("#code").val(rowData.code);
	consoleDlg.find("#typeid").val(rowData.typeid);
	consoleDlg.find("#paramname").val(rowData.paramname);
	consoleDlg.find("#paramremark").val(rowData.paramremark);
	consoleDlg.find("#parentid").val(rowData.parentid);
	 consoleDlg.find("#parentname").val(rowData.parentname);
	consoleDlg.find("#orderno").val(rowData.orderno);
	if (rowData.canmodify == '1') {
		consoleDlg.find("#canmodify").attr("checked", true);
	} else {
		consoleDlg.find("#canmodify").attr("checked", false);
	}
}

// 新增修改删除保存是获取表单参数
function getInfoData(_bizname) {
	var consoleDlg = $("#" + _bizname + "_info");
	var code = $.trim(consoleDlg.find("#code").val());
	var typeid = $.trim(consoleDlg.find("#typeid").val());
	var paramname = $.trim(consoleDlg.find("#paramname").val());
	var paramremark = $.trim(consoleDlg.find("#paramremark").val());
	var parentid = $.trim(consoleDlg.find("#parentid").val());
	var orderno = $.trim(consoleDlg.find("#orderno").val());
	var canmodify = '1';
	if (consoleDlg.find("#canmodify").is(':checked')) {
		canmodify = '1';
	} else {
		canmodify = '0';
	}
	return {
		"bean.code" : code,
		"bean.typeid" : typeid,
		"bean.paramname" : paramname,
		"bean.canmodify" : canmodify,
		"bean.paramremark" : paramremark,
		"bean.parentid" : parentid,
		"bean.orderno" : orderno
	};
}
// 根据后台数据，拼装grid行数据
function initGridRowData(_bizname, data) {
	var dataJson = eval(data);
	var consoleDlg = $("#" + _bizname + "_info");
	var typeid = $.trim(consoleDlg.find("#typeid").val());
	var paramname = $.trim(consoleDlg.find("#paramname").val());
	var paramremark = $.trim(consoleDlg.find("#paramremark").val());
	var orderno = $.trim(consoleDlg.find("#orderno").val());
	var parentid = $.trim(consoleDlg.find("#parentid").val());
	var canmodify = '1';
	if (consoleDlg.find("#canmodify").is(':checked')) {
		canmodify = '1';
	} else {
		canmodify = '0';
	}
	return dataRow = {
		_rowid : dataJson.code, // 从Server端得到系统分配的id
		code : dataJson.code,
		typeid : typeid,
		paramname : paramname,
		paramremark : paramremark,
		canmodify : canmodify,
		parentid : parentid,
		orderno : orderno
	}
}