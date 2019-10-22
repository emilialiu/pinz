$(function() {
	// grid参数
	$("#act_workflow_grid").data("gridOptions",
	{
		height : parent.getClientHeights(),
		url : rootpath
				+ '/manager/activity/list.action',
		colNames : [ pub_operation,
				'ProcessDefinitionID',
				'DeploymentId', sys_workflow_name,
				'key', sys_workflow_version, 'xml', sys_workflow_diagramResourceName,
				sys_workflow_deploymentTime,sys_workflow_suspended ],// 列显示名称
		colModel : [ // align:left,center,right,默认left;classes:列的css;datefmt:默认Y-m-d;defval:查询字段的默认值;editable:单元格是否可编辑,默认false;
		// fixed:列宽是否固定不可变,默认false;search:在搜索模式下，定义此列是否可以作搜索列
		{name : 'processbtn',index : '',width : 120,fixed : true,sortable : false,resize : false,search : false,formatter : btnformat}
		, {name : 'id',index : 'id',width : 180,sorttype : "int",editable : false,fixed : true}
		, {name : 'deploymentId',index : 'deploymentId',width : 80,sorttype : "int",editable : false,fixed : true}
		, {name : 'name',index : 'name',fixed : true,width : 150,sorttype : 'string',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne' ]}}
		, {name : 'key',index : 'key',fixed : true,width : 150,sorttype : 'string',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne' ]}}
		, {name : 'version',index : 'version',fixed : true,width : 60,sortable : false,editable : false,searchoptions : {sopt : [ 'ne' ]}}
		, {name : 'resourceName',index : 'resourceName',width : 170,editable : false,fixed : true,formatter : XMLformat}
		, {name : 'diagramResourceName',index : 'diagramResourceName',width : 170,editable : false,fixed : true,formatter:PICformat}
		, {name : 'deploymentTime',index : 'deploymentTime',width : 120,editable : false,fixed : true,search : false	}
		, {name : 'suspended',index : 'suspended',width : 120,editable : false,	fixed : true,formatter:OPformat,search : false}
		
		],
		// processbtncol:2,//操作按钮放在第几个列
		isedit : false,// 是否可编辑列表
		advsearch : true,
		multiSort : true,
		deleteurl : rootpath
				+ "/manager/activity/delete.action?actiontype=delete"

	});

	initgrid("act_workflow");
	initvalid("act_workflow");
});
function btnformat(cellvalue, options, rowobj) {
	edit = "<div class='ui-pg-div' style='float: left; margin-left: 23px;' title='"+sys_workflow_viewhis+"' onclick=seehis('"
			+  rowobj.key + "')><span class='ui-icon fa-eye'/></div>";
	/*edit += "<div class='ui-pg-div' style='float: left; margin-left: 23px;' title='启动流程' onclick=startprocess('"
		+  rowobj.id +"','"+rowobj.name+"')><span class='ui-icon fa-edit'/></div>"*/;
	return edit;
}
/**
 * 查看XML信息
 * @param cellvalue
 * @param options
 * @param rowobj
 * @returns
 */
function XMLformat(cellvalue, options, rowobj) {
	edit = "<a  target=\"_blank\" href=\""+rootpath+"/manager/activity/view.action?bean.id="+rowobj.id+"&resourceType=xml\" class='ui-pg-div' style='float: left; margin-left: 23px;' title='"+sys_workflow_viewxml+"'>"+cellvalue+"</a>";
	return edit;
}
/**
 * 查看图片信息
 * @param cellvalue
 * @param options
 * @param rowobj
 * @returns
 */
function PICformat(cellvalue, options, rowobj) {
	edit = "<a  target=\"_blank\" href=\""+rootpath+"/manager/activity/view.action?bean.id="+rowobj.id+"&resourceType=image\" class='ui-pg-div' style='float: left; margin-left: 23px;' title='"+sys_workflow_viewpic+"'>"+cellvalue+"</a>";
	return edit;
}
/**
 * 流程的操作是否挂起
 * @param cellvalue
 * @param options
 * @param rowobj
 * @returns
 */
function OPformat(cellvalue, options, rowobj) {
	if(rowobj.suspended=="false"){
		edit = "<input type=\"checkbox\" ProcessDefinitionID=\""+rowobj.id+"\"  suspended=\""+rowobj.suspended+"\"   class=\"FormElement ace ace-switch ace-switch-lt\" onchange=\"PRoption(this)\"><span class=\"lbl\"></span> ";
	}else{
		edit = "<input type=\"checkbox\"  checked=\"true\"  ProcessDefinitionID=\""+rowobj.id+"\" suspended=\""+rowobj.suspended+"\"  class=\"FormElement ace ace-switch ace-switch-lt\" onchange=\"PRoption(this)\"><span class=\"lbl\"></span> ";
	}
	return edit;
}
/**
 * 将流程转化为model
 * @param id
 */
function ConvertModel(id){
	 $.ajax({  
	        url : rootpath+'/manager/activity/convertToModel.action',
	        data : {"bean.id":id},  
	        cache : false,  
	        error : function(data) {  
	        	alertMsg("系统ajax交互错误");  
	        },  
	        success : function(data) {  
	        	var dataJson = eval(data);
	            if(dataJson.success) {
	            	alertMsg(dataJson.id);  
	                //$("#act_workflow_grid").trigger("reloadGrid");
	            } else {  
	            	alertMsg("操作失败"+dataJson.errormessage); 
	            	$("#act_workflow_grid").trigger("reloadGrid");
	            }  
	        }  
	    });  
}
// 弹出表单
$("#act_workflow_view_info").dialog({
	autoOpen : false,
	modal : true,
	resizable : true,
	width : 800,
	buttons : { // 为对话框添加按钮
		"取消" : function() {
			$("#" + _bizname + "_info").dialog("close");
		},
		"创建" : function() {
			_doAddSave(_bizname);
		},
		"保存" : function() {
			_doModifySave(_bizname);
		},
		"删除" : function() {
			_doDeleteSave("act_workflow");
		}
	}
});



var _openDialog4Adding = function(_bizname) {
	var consoleDlg = $("#act_workflow_info");
	var dialogButtonPanel = consoleDlg.siblings(".ui-dialog-buttonpane");

	$("#act_workflow_form").Validform().resetForm();
	consoleDlg.find(".info").css('display', 'none');

	consoleDlg.find("input").removeAttr("disabled").val("");
	consoleDlg.find("textarea").removeAttr("disabled").val("");

	consoleDlg.trigger('domi.add.before');

	dialogButtonPanel.find("button:not(:contains('取消'))").hide();
	dialogButtonPanel.find("button:contains('部署')").show();
	dialogButtonPanel.find("button").addClass('btn btn-xs btn-primary');

	consoleDlg.dialog("option", "title", "流程部署").dialog("open");
	consoleDlg.trigger('domi.add.after');
};

// 新增保存
var doAddSave = function(_bizname) {
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
		fileElementId : 'file', // 文件上传域的ID
		dataType : 'json', // 返回值类型 一般设置为json
		success : function(data) // 服务器成功响应处理函数
		{
			var dataJson = eval(data);
			if (dataJson.success) {
				alertMsg("流程部署成功!");
				consoleDlg.dialog("close");
				$("#act_workflow_grid").trigger("reloadGrid");
			} else {
				alertMsg("流程部署失败" + dataJson.errormessage);
				consoleDlg.trigger('domi.addsave.failue');
			}
		},
		error : function(data)// 服务器响应失败处理函数
		{
			alertMsg("系统ajax交互错误");
		}
	});

};
var _openDialog4Reading = function(_bizname) {  
    var consoleDlg = $("#act_workflow_view_info");   
    var dialogButtonPanel = consoleDlg.siblings(".ui-dialog-buttonpane");  
    $("#act_workflow_view_form").Validform().resetForm();
    consoleDlg.find(".info").css('display','none');
    consoleDlg.find("input").attr("disabled", true);  
    consoleDlg.find("textarea").attr("disabled", true); 
    consoleDlg.trigger('domi.viewinfo');
    dialogButtonPanel.find("button:not(:contains('取消'))").hide();  
    dialogButtonPanel.find("button:contains('删除')").hide();  
    dialogButtonPanel.find("button").addClass('btn btn-xs btn-primary');
    consoleDlg.dialog("option", "title", "查看信息");  
    loadSelectedRowData("act_workflow_view");  
};
var loadSelectedRowData = function(_bizname) {    
    var selectedRowId = $("#act_workflow_grid").jqGrid("getGridParam", "selrow");  
    var consoleDlg = $("#act_workflow_view_info");  
    if (!selectedRowId) {  
    	alertMsg("请先选择需要操作的行!");  
        return false;  
    } else {  
    	//删除修改进入表单时传递参数,业务功能实现
        var params = getGridParams("act_workflow");  
        // 从Server读取对应ID的JSON数据  
        $.ajax( {  
            url : $("#act_workflow_grid").data("gridOptions").viewurl, 
            data : params,  
            cache : false,  
            error : function(data) {  
            	alertMsg("系统ajax交互错误");  
            },  
            success : function(data) {  
                // 设置表单数据 
            	setInfoData("act_workflow_view",data);
                // 根据新载入的数据将表格中的对应数据行一并更新一下  
                gridrowdata = initGridRowData("act_workflow_view",data);
                  
                $("#act_workflow_grid").jqGrid("setRowData", selectedRowId, gridrowdata); 
                
                      
                // 打开对话框  
                consoleDlg.dialog("open");  
            }  
        });  
          
    }  
};  
/**
 * 重写删除的方法
 * @param _bizname
 * @returns
 */
var _openDialog4Deleting = function(_bizname) {  
    var consoleDlg = $("#act_workflow_view_info"); 
    var dialogButtonPanel = consoleDlg.siblings(".ui-dialog-buttonpane");  

    $("#act_workflow_view_form").Validform().resetForm();
    consoleDlg.find(".info").css('display','none');

    consoleDlg.find("input").attr("disabled", true);  
    consoleDlg.find("textarea").attr("disabled", true); 
    consoleDlg.trigger('domi.delete.before');
    
    dialogButtonPanel.find("button:not(:contains('取消'))").hide();  
    dialogButtonPanel.find("button:contains('删除')").show(); 
    dialogButtonPanel.find("button").addClass('btn btn-xs btn-primary');
    consoleDlg.dialog("option", "title", "删除流程");  
      
    loadSelectedRowData("act_workflow_view");  
    consoleDlg.trigger('domi.delete.after');
};

//重写删除的按钮
var _doDeleteSave = function(_bizname) {  
	var selectedRowId = $("#"+_bizname+"_grid").jqGrid("getGridParam", "selrow"); 
    var consoleDlg = $("#act_workflow_view_info"); 
	//删除保存开始
    consoleDlg.trigger('domi.deletesave.before'); 
    //提取表单参数
    var params = getGridParams(_bizname);
    
    $.ajax({  
        url : $("#"+_bizname+"_grid").data("gridOptions").deleteurl,
        data : params,  
        cache : false,  
        error : function(data) {  
        	alertMsg("系统ajax交互错误");  
        },  
        success : function(data) {  
        	var dataJson = eval(data);
            if(dataJson.success) { 
                $("#"+_bizname+"_grid").jqGrid("delRowData", selectedRowId);  
                  
                consoleDlg.dialog("close");  
                alertMsg("信息删除成功!");  
            	//删除保存完成
                consoleDlg.trigger('domi.deletesave.after');
            } else {  
            	alertMsg("删除失败"+dataJson.errormessage); 
                consoleDlg.trigger('domi.deletesave.failue');
            }  
        }  
    });  
};  
/**
 * 列表中是否挂起的操作
 * @param obj
 */
function PRoption(obj){
	 var suspended=$(obj).attr("suspended");
	 var ProcessDefinitionID=$(obj).attr("ProcessDefinitionID");
	 $.ajax({  
	        url : rootpath+'/manager/activity/doActity.action',
	        data : {"bean.id":ProcessDefinitionID,"suspended":suspended},  
	        cache : false,  
	        error : function(data) {  
	        	parent.alertMsg("系统ajax交互错误");  
	        },  
	        success : function(data) {  
	        	var dataJson = eval(data);
	            if(dataJson.success) {
	                parent.alertMsg(dataJson.id);  
	                $("#act_workflow_grid").trigger("reloadGrid");
	            } else {  
	            	parent.alertMsg("操作失败"+dataJson.errormessage); 
	            	$("#act_workflow_grid").trigger("reloadGrid");
	            }  
	        }  
	    });  
}
/**
 * 查询历史版本信息
 * @param id
 */
function seehis(key){
	
	parent.createwindow("act_workflow_his","/webpage/manager/activity/workflow/hismain.jsp?key="+key,sys_workflow_viewhis,900,500,true);
	/*var consoleDlg = $("#act_workflow_his_info");  
    var dialogButtonPanel = consoleDlg.siblings(".ui-dialog-buttonpane");  
    dialogButtonPanel.find("button").addClass('btn btn-xs btn-primary');
	//var datalist = $("#sys_user_grid").jqGrid('getRowData', rowid);
	//consoleDlg.find("#userid").val(datalist.userid);  
	consoleDlg.dialog("option", "title", "查看历史版本").dialog("open"); 
	//dosome(key);
    $("#act_workflow_his_grid").jqGrid('setGridWidth', $("#act_workflow_his_grid_box").width()-2);
    $("#act_workflow_his_grid").jqGrid('setGridParam',{
		postData:{'key':key}, 
        page:1 
    }).trigger("reloadGrid"); 
	*/
}
/**
 * 启动流程
 * @param id
 * @param name
 */
function startprocess(id,name){
	var consoleDlg = $("#act_workflow_dyform_info");   
    var dialogButtonPanel = consoleDlg.siblings(".ui-dialog-buttonpane");  
    $("#act_workflow_dyform_form").Validform().resetForm();
    consoleDlg.find(".info").css('display','none');
    //consoleDlg.find("textarea").attr("disabled", true); 
    consoleDlg.trigger('domi.viewinfo');
    dialogButtonPanel.find("button").addClass('btn btn-xs btn-primary');
    
    // dialogButtonPanel.find("button:contains('办理')").hide();
    //loadSelectedRowData(id,taskId);
    genform(id,name);
    
}
/***
 * 生成动态的表单
 * @param id
 */
function genform(id,name){
	// 从Server读取对应ID的JSON数据，生成动态表单
	var trs="<input type=\"hidden\" name=\"processDefinitionId\" id=\"processDefinitionId\" value=\""+id+"\">";
    $.ajax( {  
        url : rootpath+'/manager/activity/startform.action', 
        data : {processDefinitionId:id},  
        cache : false, 
        async:false,
        error : function(data) {  
        	alertMsg("系统ajax交互错误");  
        },  
        success : function(data) {  
            var dataJson = eval(data);
            var rowData = dataJson[0]; 
            $.each(rowData.form.formProperties, function() {
   			var className = this.required === true ? "required" : "";
   			trs +="<div class=\"space-2\"></div> <div class=\"form-group\">";
   			trs += createFieldHtml(data, this, className);
   			trs +="</div>";
   		  });
           
        }  
    });  
    $('#act_workflow_dyform_form').html(trs);
    $("#act_workflow_dyform_info").dialog("option", "title",name).dialog("open");
  
}
/**
 * 生成一个field的html代码
 */

function createFieldHtml(formData, prop, className) {
	return formFieldCreator[prop.type.name](formData, prop, className);
}

/**
 * form对应的string/date/long/enum/boolean类型表单组件生成器
 * fp_的意思是form paremeter
 */
var formFieldCreator = {
	string: function(formData, prop, className) {
		var result = "<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\" for=\""+prop.id+"\">"+prop.name+":</label>";
		result+="<div class=\"col-xs-12 col-sm-9\"> <div class=\"col-sm-6\"> <input type=\"text\" name=\""+prop.id+"\" datatype=\"*\" ";
		if(className!=""){
			result+= "nullmsg=\""+prop.name+"必填！\" "
		}
		result+="id=\"fp_"+prop.id+"\" class=\"col-xs-12\" /> </div>";	
		result+="<div class=\"col-sm-3\"> <div class=\"info\"><span class=\"Validform_checktip\"></span><span class=\"dec\"><s class=\"dec1\">&#9670;</s><s class=\"dec2\">&#9670;</s></span></div> </div></div>";	
		return result;
	},
	date: function(formData, prop, className) {
		var result = "<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\" for=\""+prop.id+"\">"+prop.name+":</label>";
		result+="<div class=\"col-xs-12 col-sm-9\"> <div class=\"col-sm-6\"> <input type=\"text\" name=\""+prop.id+"\" datatype=\"*\" ";
		if(className!=""){
			result+= "nullmsg=\""+prop.name+"必填！\" "
		}
		result+="id=\"fp_"+prop.id+"\" class=\"col-xs-12\" /> </div>";	
		result+="<div class=\"col-sm-3\"> <div class=\"info\"><span class=\"Validform_checktip\"></span><span class=\"dec\"><s class=\"dec1\">&#9670;</s><s class=\"dec2\">&#9670;</s></span></div> </div></div>";	
		return result;
	},
	'enum': function(formData, prop, className) {
		console.log(prop);
		var result = "<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\" for=\""+prop.id+"\">"+prop.name+":</label>";
		result+="<div class=\"col-xs-12 col-sm-9\"> <div class=\"col-sm-6\">  ";
		if(prop.writable === true) {
			result += "<select id='fp_" + prop.id + "' name='" + prop.id + "'>";
			//result += "<option>" + datas + "</option>";
			
			$.each(formData['enum_' + prop.id], function(k, v) {
				result += "<option value='" + k + "'>" + v + "</option>";
			});
			 
			result += "</select>";
		} else {
			result += "" + prop.value;
		}
		result+="<div class=\"col-sm-3\"> <div class=\"info\"><span class=\"Validform_checktip\"></span><span class=\"dec\"><s class=\"dec1\">&#9670;</s><s class=\"dec2\">&#9670;</s></span></div> </div></div>";	
		return result;
	},
	'users': function(formData, prop, className) {
		var result = "<td width='120'>" + prop.name + "：</td><td><input type='text' id='" + prop.id + "' name='" + prop.id + "' class='" + className + "' />";
		return result;
	}
}
/**
 * 获取数据
 */
function getprocessdata(){
	var consng=$('#act_workflow_dyform_info');
	var obj=consng.find("[id*='fp_']");
	var processDefinitionId=consng.find("#processDefinitionId").val();
	var param="";
	for(var i=0;i<obj.length;i++){
		param+=obj[i].name+"@#$%^"+obj[i].value;
		if(i<obj.length-1){
			param+="^%$#^";
		}
	}
	
	$.ajax({  
        url : rootpath+'/manager/activity/submitStartForm.action?processDefinitionId='+processDefinitionId,
        data : {"formdata":param},  
        cache : false,  
        error : function(data) {  
        	alertMsg("系统ajax交互错误");  
        },  
        success : function(data) {  
        	var dataJson = eval(data);
            if(dataJson.success) {
                alertMsg(dataJson.id);
                $("#act_workflow_dyform_info").dialog("close");
            } else {  
            	alertMsg("操作失败"+dataJson.errormessage);
            }  
        }  
    });  
}
