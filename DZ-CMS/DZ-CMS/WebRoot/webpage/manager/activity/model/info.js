var addsaveurl = rootpath+"/manager/model/doAddSave.action";
var modifysaveurl = rootpath+"/manager/sys/role/doModifySave.action";
var deletesaveurl = rootpath+"/manager/model/delete.action";
function getGridParams(_bizname){
	var selectedRowId = $("#"+_bizname+"_grid").jqGrid("getGridParam", "selrow");  
    var str = $("#"+_bizname+"_grid").jqGrid("getRowData", selectedRowId); // 根据行ID，获取选中行的数据
	return {  
        "modelId" : str.id 
    };  
}
//新增修改删除保存是获取表单参数
function getInfoData(_bizname){
    var consoleDlg = $("#"+_bizname+"_info");  
	var name = $.trim(consoleDlg.find("#name").val());  
    var key = $.trim(consoleDlg.find("#key").val());  
    var description = $.trim(consoleDlg.find("#description").val());  
    return {  
        "name" : name,  
        "key" : key,  
        "description" : description
    };      
}
//给表单赋值
function setInfoData(_bizname,data){  
	var dataJson = eval(data);
	var rowData = dataJson[0];
    var consoleDlg = $("#"+_bizname+"_info");
	consoleDlg.find("#name").val(rowData.name);
	consoleDlg.find("#key").val(rowData.key);
	consoleDlg.find("#version").val(rowData.version);
}

//根据后台数据，拼装grid行数据
function initGridRowData(_bizname,data){
	var dataJson = eval(data);
    var consoleDlg = $("#"+_bizname+"_info");  
	var name = $.trim(consoleDlg.find("#name").val());	
	var key = $.trim(consoleDlg.find("#key").val());	
	var version = $.trim(consoleDlg.find("#version").val());	
	 var description = $.trim(consoleDlg.find("#description").val());  
	var dataRow = { 			
	    id : dataJson.id,// 从Server端得到系统分配的id
	    createTime:dataJson.createTime,
	    lastUpdateTime:dataJson.lastUpdateTime,
	    name:name,
	    key:key,
	    version:version,
	    description:description
	}; 
	return dataRow;
}
//新增保存
var doAddSave = function(_bizname) {
	//检查不成功，跳出
	if(!$("#"+_bizname+"_form").Validform().checkForm()) return;
	
    var consoleDlg = $("#"+_bizname+"_info");  
	//新增保存开始
    consoleDlg.trigger('domi.addsave.before');
    
    //提取表单参数
    var params = getInfoData(_bizname);
      
    $.ajax( {  
        url : $("#"+_bizname+"_grid").data("gridOptions").addurl,  
        data : params,  
        cache : false,  
        success : function(data) {  
        	var dataJson = eval(data);
            if(dataJson.success) {  
                gridrowdata = initGridRowData(_bizname,data);
                  
                var srcrowid = $("#"+_bizname+"_grid").jqGrid("getGridParam", "selrow");  
                
                if(srcrowid) {  
                    $("#"+_bizname+"_grid").jqGrid("addRowData", dataJson.id, gridrowdata, "before", srcrowid);  
                } else {
                    $("#"+_bizname+"_grid").jqGrid("addRowData", dataJson.id, gridrowdata, "first",1);  
                }  
                if($("#"+_bizname+"_grid").data("gridOptions").processbtncol!=undefined){
					if($(gridid).data("gridOptions").isedit!=undefined&&$(gridid).data("gridOptions").isedit){
						updateEditIcons(_bizname);
					}else{
						updateIcons(_bizname);
					}
				}
                
                consoleDlg.dialog("close");  

                alertMsg("信息新增成功!");  
            	//新增保存完成
                consoleDlg.trigger('domi.addsave.after');
                  
            } else {  
            	alertMsg("新增失败"+dataJson.errormessage);  
                consoleDlg.trigger('domi.addsave.failue');
            }  
        },
        error : function(data) {  
        	alertMsg("系统ajax交互错误" );  
        } 
    });  
};  


/**
 * 流程上传部署
 * @param _bizname
 * @returns
 */
//新增保存
var wrokAddSave = function() {
	// 检查不成功，跳出
	if (!$("#act_workflow_form").Validform().checkForm())
		return;
	var consoleDlg = $("#act_workflow_info");
	// 新增保存开始
	consoleDlg.trigger('domi.addsave.before');
	var url = rootpath + "/manager/activity/deploy.action";
	// xhr.open("post",url , true);
	// xhr.send(form);
	$.ajaxFileUpload({
		url : url, // 用于文件上传的服务器端请求地址
		secureuri : false, // 是否需要安全协议，一般设置为false
		fileElementId : ['file'], // 文件上传域的ID
		dataType : 'json', // 返回值类型 一般设置为json
		success : function(data) // 服务器成功响应处理函数
		{
			var dataJson = eval(data);
			if (dataJson.success) {
				parent.alertMsg("流程部署成功!");
				parent.closewin('act_workflow');
				$("#act_model_grid").trigger("reloadGrid");
			} else {
				parent.alertMsg("流程部署失败" + dataJson.errormessage);
				consoleDlg.trigger('domi.addsave.failue');
			}
		},
		error : function(data)// 服务器响应失败处理函数
		{
			alertMsg("系统ajax交互错误");
		}
	});

};

