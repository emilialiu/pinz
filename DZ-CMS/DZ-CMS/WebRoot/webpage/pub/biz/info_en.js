
$(function(){
	if(location.href.indexOf("load=detail")!=-1){
		$(":input").attr("disabled","true");
	}
});

//新增保存
function addSave(_bizname) {
	//检查不成功，跳出
	if(!$("#"+_bizname+"_form").Validform().checkForm()) return;
	//禁用操作按钮
	$("#"+_bizname, window.parent.document).parent().find("button").attr("disabled", true);
    var consoleDlg = $("#"+_bizname+"_info");
	//新增保存开始
    consoleDlg.trigger('domi.addsave.before');
    //提取表单参数
    var params = getInfoData(_bizname);
    $.ajax( {
        url : addsaveurl,
        type:'POST',
        data : params,
        cache : false,
        success : function(data) {
        	var dataJson = eval(data);
            if(dataJson.success) {
                gridrowdata = initGridRowData(_bizname,data);
                parent.alertMsg("The information created successfully!");
                parent.closewin(_bizname,"_doGridAddSave('"+_bizname+"',"+JSON.stringify(gridrowdata)+")");
            	//新增保存完成
                consoleDlg.trigger('domi.addsave.after');
            } else {
            	parent.alertErrorMsg("Add failure :"+dataJson.errorMessage);
                consoleDlg.trigger('domi.addsave.failue');
            }
        	$("#"+_bizname, window.parent.document).parent().find("button").attr("disabled", false);
        },
        error : function(data) {
        	parent.alertErrorMsg("Ajax interactive error");
        	$("#"+_bizname, window.parent.document).parent().find("button").attr("disabled", false);
        }
    });
}

function modifySave(_bizname) {
	//检查不成功，跳出
	if(!$("#"+_bizname+"_form").Validform().checkForm()) return;
	//禁用操作按钮
	$("#"+_bizname, window.parent.document).parent().find("button").attr("disabled", true);
    var consoleDlg = $("#"+_bizname+"_info");
	//修改保存开始
    consoleDlg.trigger('domi.modifysave.before');
    //提取表单参数
    var params = getInfoData(_bizname);

    $.ajax( {
        url : modifysaveurl,
        type:'POST',
        data : params,
        cache : false,
        error : function(data) {
        	parent.alertErrorMsg("Ajax interactive error ");
        	$("#"+_bizname, window.parent.document).parent().find("button").attr("disabled", false);
        },
        success : function(data) {
        	var dataJson = eval(data);
            if(dataJson.success) {
                gridrowdata = initGridRowData(_bizname,data);
                parent.alertMsg("Modify the information successfully!");
                parent.closewin(_bizname,"_doGridModifySave('"+_bizname+"',"+JSON.stringify(gridrowdata)+")");
            	//修改保存完成
                consoleDlg.trigger('domi.modifysave.after');
            } else {
            	parent.alertErrorMsg(":"+dataJson.errorMessage);
                consoleDlg.trigger('domi.modifysave.failue');
            }
        	$("#"+_bizname, window.parent.document).parent().find("button").attr("disabled", false);
        }
    });
}

function deleteSave(_bizname) {
	//禁用操作按钮
	$("#"+_bizname, window.parent.document).parent().find("button").attr("disabled", true);
    var consoleDlg = $("#"+_bizname+"_info");
	//删除保存开始
    consoleDlg.trigger('domi.deletesave.before');
    //提取表单参数
    var params = getIDVal();

    $.ajax({
        url : deletesaveurl,
        type:'POST',
        data : params,
        cache : false,
        error : function(data) {
        	parent.alertErrorMsg("Ajax interactive error");
        	$("#"+_bizname, window.parent.document).parent().find("button").attr("disabled", false);
        },
        success : function(data) {
        	var dataJson = eval(data);
            if(dataJson.success) {
            	parent.closewin(_bizname,"_doGridDeleteSave('"+_bizname+"')");
            	parent.alertMsg("Delete the information successfully!");
            	//删除保存完成
                consoleDlg.trigger('domi.deletesave.after');
            } else {
            	parent.alertErrorMsg("Delete failure:"+dataJson.errorMessage);
                consoleDlg.trigger('domi.deletesave.failue');
            }
        	$("#"+_bizname, window.parent.document).parent().find("button").attr("disabled", false);
        }
    });
}

function loadSelectedRowData(_bizname){
	//提取表单参数
    var params = getIDVal();
	$.ajax({
        url : loadinfourl,
        type:'POST',
        data : params,
        cache : false,
        error : function(data) {
        	parent.alertErrorMsg("Ajax interactive error");
        },
        success : function(data) {
        	setInfoData(_bizname,data);
        }
    });
}