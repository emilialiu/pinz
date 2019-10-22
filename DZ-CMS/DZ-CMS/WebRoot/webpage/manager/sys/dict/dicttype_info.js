var addsaveurl = rootpath + "/manager/sys/dicttype/addtype.action";
var modifysaveurl = rootpath + "/manager/sys/dicttype/modifytype.action";
var deletesaveurl = rootpath + "/manager/sys/dicttype/deletetype.action";

// 删除修改进入表单时传递参数
function getIDVal() {
	return {
		"bean.code" : code
	};
}
// 给表单赋值
function setInfoData(_bizname, data) {
	var dataJson = eval(data);
	var rowData = dataJson[0];
	var consoleDlg = $("#" + _bizname + "_info");
	consoleDlg.find("#code").val(rowData.code);
	consoleDlg.find("#name").val(rowData.name);
	consoleDlg.find("#remark").val(rowData.remark);
	if (rowData.canmodify == '1') {
		consoleDlg.find("#canmodify").attr("checked", true);
	} else {
		consoleDlg.find("#canmodify").attr("checked", false);
	}
	consoleDlg.find("#canmodify").val(rowData.canmodify);
}

// 新增修改删除保存是获取表单参数
function getInfoData(_bizname) {
	var consoleDlg = $("#" + _bizname + "_info");
	var code = $.trim(consoleDlg.find("#code").val());
	var name = $.trim(consoleDlg.find("#name").val());
	var remark = $.trim(consoleDlg.find("#remark").val());
	var canmodify = '1';
	if (consoleDlg.find("#canmodify").is(':checked')) {
		canmodify = '1';
	} else {
		canmodify = '0';
	}
	return {
		"bean.code" : code,
		"bean.name" : name,
		"bean.remark" : remark,
		"bean.canmodify" : canmodify
	};
}
// 根据后台数据，拼装grid行数据
function initGridRowData(_bizname, data) {
	var dataJson = eval(data);
	var consoleDlg = $("#" + _bizname + "_info");
	var name = $.trim(consoleDlg.find("#name").val());
	var remark = $.trim(consoleDlg.find("#remark").val());
	var canmodify = '1';
	if (consoleDlg.find("#canmodify").is(':checked')) {
		canmodify = '1';
	} else {
		canmodify = '0';
	}
	var dataRow = {
		_rowid : dataJson.code, // 从Server端得到系统分配的id
		code : dataJson.code, // 从Server端得到系统分配的id
		name : name,
		remark : remark,
		canmodify : canmodify
	};
	return dataRow;
}