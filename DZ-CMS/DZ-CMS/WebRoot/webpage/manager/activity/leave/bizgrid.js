var scripts = [ null, null ];

$('.page-content-area')
		.ace_ajax(
				'loadScripts',
				scripts,
				function() {
					$(function() {
						
						// grid参数
						$("#act_leave_grid")
								.data(
										"gridOptions",
										{
											height : 400,
											url : rootpath
													+ '/manager/leave/list.action',
											colNames : [ '操作','申请人id','suspended','assignee','taskid','id',
													'假种',
													'申请人', '申请时间',
													'开始时间', '结束时间', '当前节点'
													, '创建时间','流程状态'
													 ],// 列显示名称
											colModel : [ // align:left,center,right,默认left;classes:列的css;datefmt:默认Y-m-d;defval:查询字段的默认值;editable:单元格是否可编辑,默认false;
											// fixed:列宽是否固定不可变,默认false;search:在搜索模式下，定义此列是否可以作搜索列
											{
												name : 'processbtn',
												index : '',
												width : 120,
												fixed : true,
												sortable : false,
												resize : false,
												search : false,
												formatter : btnformat
											}, {
												name : 'userId',
												index : 'userId',
												width : 100,
												sorttype : "string",
												editable : true,
												fixed : true,
												hidden:true
											},{
												name : 'suspended',
												index : 'suspended',
												width : 100,
												sorttype : "string",
												editable : true,
												fixed : true,
												hidden:true
											},{
												name : 'assignee',
												index : 'assignee',
												width : 100,
												sorttype : "string",
												editable : true,
												fixed : true,
												hidden:true
											},{
												name : 'taskid',
												index : 'taskid',
												width : 100,
												sorttype : "string",
												editable : true,
												fixed : true,
												hidden:true
											},{
												name : 'id',
												index : 'id',
												width : 100,
												sorttype : "string",
												editable : true,
												fixed : true,
												hidden:true
											}, {
												name : 'leaveType',
												index : 'leaveType',
												width : 100,
												sorttype : "int",
												editable : true,
												fixed : true
											}, {
												name : 'username',
												index : 'username',
												width : 80,
												sorttype : "string",
												editable : true,
												fixed : true
											},

											{
												name : 'applyTime',
												index : 'applyTime',
												fixed : true,
												width : 150,
												sorttype : 'string',
												editable : true,
												searchoptions : {
													sopt : [ 'cn', 'eq', 'ne' ]
												}
											}, {
												name : 'startTime',
												index : 'startTime',
												fixed : true,
												width : 150,
												sorttype : 'string',
												editable : true,
												searchoptions : {
													sopt : [ 'cn', 'eq', 'ne' ]
												}
											}, {
												name : 'endTime',
												index : 'endTime',
												fixed : true,
												width : 160,
												sortable : false,
												editable : true,
												searchoptions : {
													sopt : [ 'cn' ]
												},
												edittype : "textarea",
												editoptions : {
													rows : "2",
													cols : "10"
												}
											}, {
												name : 'name',
												index : 'name',
												width : 170,
												editable : true,
												fixed : true
											}
											, {
												name : 'createTime',
												index : 'createTime',
												width : 170,
												editable : true,
												fixed : true
											}, {
												name : 'version',
												index : 'version',
												width : 170,
												editable : true,
												fixed : true,
												formatter : versionformat
											}
											],
											// processbtncol:2,//操作按钮放在第几个列
											advsearch : true,
											multiSort : true,
											viewurl : rootpath
													+ "/manager/activity/doModify.action",
											addurl : rootpath
													+ "/manager/leave/doAddSave.action",
											modifyurl : rootpath
													+ "/manager/sys/role/doModifySave.action?actiontype=modifysave",
											deleteurl : rootpath+"/manager/model/delete.action?actiontype=delete"

										});

						initgrid("act_leave");
						initvalid("act_leave");

						// 弹出表单
						$("#act_leave_info").dialog({
							autoOpen : false,
							modal : true,
							resizable : true,
							width : 800,
							buttons : { // 为对话框添加按钮
								"取消" : function() {
									$("#act_leave_info").dialog("close");
								},
								"创建" : function() {
									_doAddSave('act_leave');
								}
							}
						});
						
						// 弹出表单
						$("#act_leave_view_info").dialog({
							autoOpen : false,
							modal : true,
							resizable : true,
							width : 800,
							buttons : { // 为对话框添加按钮
								"取消" : function() {
									$("#act_leave_view_info").dialog("close");
								},
								"通过" : function() {
									 pass();
								},
								"办理" : function() {
									 doapplyhandle();
								},
								"驳回" : function() {
									_doAddSave('act_leave_view');
								}
							}
						});
					});
				});

function btnformat(cellvalue, options, rowobj) {
	if(rowobj.assignee=="null"){
		edit= "<div class='ui-pg-div' style='float: left; margin-left: 23px;' onclick=claim('"+rowobj.taskid+"')  title='签收' ><span class='ui-icon fa-edit'/></div>";
	}else{
		if(rowobj.name=="销假"){
			edit= "<div class='ui-pg-div' style='float: left; margin-left: 23px;' onclick=applyhandle('"+rowobj.id+"','"+rowobj.taskid+"')  title='办理' ><span class='ui-icon fa-edit'/></div>";
		}else{
			edit= "<div class='ui-pg-div' style='float: left; margin-left: 23px;' onclick=handle('"+rowobj.id+"','"+rowobj.taskid+"')  title='办理' ><span class='ui-icon fa-edit'/></div>";
		}
	}
	return edit;
}
function versionformat(cellvalue, options, rowobj) {
	var edit="";
	if(rowobj.suspended=="false"){
		edit+="正常;";
	}else{
		edit+="已挂起;";
	}
	edit+="v"+cellvalue;
	return edit;
}

function claim(taskid){
	 $.ajax({  
	        url : rootpath+'/manager/leave/claim.action',
	        data : {"taskId":taskid},  
	        cache : false,  
	        error : function(data) {  
	        	alertMsg("系统ajax交互错误");  
	        },  
	        success : function(data) {  
	        	var dataJson = eval(data);
	            if(dataJson.success) {
	                alertMsg(dataJson.id);  
	                $("#act_leave_grid").trigger("reloadGrid");
	            } else {  
	            	alertMsg("操作失败"+dataJson.errormessage); 
	            	$("#act_leave_grid").trigger("reloadGrid");
	            }  
	        }  
	    });  
}
function handle(id,taskId){
	openDialog4Reading(id,taskId);
}
var openDialog4Reading = function(id,taskId) {  
    var consoleDlg = $("#act_leave_view_info");   
    var dialogButtonPanel = consoleDlg.siblings(".ui-dialog-buttonpane");  
    $("#act_leave_view_form").Validform().resetForm();
    consoleDlg.find(".info").css('display','none');
    consoleDlg.find("select").attr("disabled", true);
    consoleDlg.find("input").attr("disabled", true);  
    //consoleDlg.find("textarea").attr("disabled", true); 
    consoleDlg.trigger('domi.viewinfo');
    dialogButtonPanel.find("button").addClass('btn btn-xs btn-primary');
    consoleDlg.dialog("option", "title", "审批").dialog("open");
    dialogButtonPanel.find("button:contains('办理')").hide();
    loadSelectedRowData(id,taskId);
      
};

var loadSelectedRowData = function(id,taskId) { 
	 var consoleDlg = $("#act_leave_view_info"); 
        // 从Server读取对应ID的JSON数据  
        $.ajax( {  
            url : rootpath+'/manager/leave/view.action', 
            data : {'bean.id':id},  
            cache : false,  
            error : function(data) {  
            	alertMsg("系统ajax交互错误");  
            },  
            success : function(data) {  
                // 设置表单数据 
            	setInfoData('act_leave_view',data,taskId);  
                // 打开对话框  
                consoleDlg.dialog("open");  
            }  
        });   
}; 
/**
 * 审批通过
 */
function pass(){
	setTimeout(function(){
		bootbox.confirm("审批通过?", function(result) {
			if(result) {dopass();}
		});
	},0);
}
function dopass(){
	 var consoleDlg = $("#act_leave_view_info"); 
	 var taskId = consoleDlg.find("#taskId").val();  
     // 从Server读取对应ID的JSON数据  
     $.ajax( {  
         url : rootpath+'/manager/leave/complete.action', 
         data : {'taskId':taskId,'ispass':'pass'},  
         cache : false,  
         error : function(data) {  
         	alertMsg("系统ajax交互错误");
         	consoleDlg.dialog("close"); 
         },  
         success : function(data) {  
        	 alertMsg("审批成功"); 
        	 // 关闭对话框  
             consoleDlg.dialog("close");
             //刷新界面
             $("#act_leave_grid").trigger("reloadGrid");
         }  
     });   
}
/**
 * 办理人销假
 * @param id
 * @param taskId
 */
function applyhandle(id,taskId){
	  var consoleDlg = $("#act_leave_view_info");   
	    var dialogButtonPanel = consoleDlg.siblings(".ui-dialog-buttonpane");  
	    $("#act_leave_view_form").Validform().resetForm();
	    consoleDlg.find(".info").css('display','none');
	    //consoleDlg.find("select").attr("disabled", true);
	    //consoleDlg.find("input").attr("disabled", true);  
	    //consoleDlg.find("textarea").attr("disabled", true); 
	    consoleDlg.trigger('domi.viewinfo');
	    dialogButtonPanel.find("button").addClass('btn btn-xs btn-primary');
	    consoleDlg.dialog("option", "title", "办理").dialog("open"); 
	    dialogButtonPanel.find("button:contains('驳回')").hide();
	    dialogButtonPanel.find("button:contains('通过')").hide();
	    //让实际开始的时间开始显示
	    consoleDlg.find("#realityStartTimediv").css("display","");
	    consoleDlg.find("#realityEndTimediv").css("display","");
	    loadSelectedRowData(id,taskId);
}
/**
 * 办理
 */
function  doapplyhandle(){
	var consoleDlg = $("#act_leave_view_info");  
	 var taskId = consoleDlg.find("#taskId").val();  
	//检查不成功，跳出
	if(!$("#act_leave_view_form").Validform().checkForm()) return;
	  //提取表单参数
    var params = getInfoData("act_leave_view");
	//检查通过ajax提交
	  // 从Server读取对应ID的JSON数据  
    $.ajax( {  
        url : rootpath+'/manager/leave/complete.action?taskId='+taskId, 
        data : params,  
        cache : false,  
        error : function(data) {  
        	alertMsg("系统ajax交互错误");
        	consoleDlg.dialog("close"); 
        },  
        success : function(data) {  
       	 alertMsg("办理成功"); 
       	 // 关闭对话框  
            consoleDlg.dialog("close");
            //刷新界面
            $("#act_leave_grid").trigger("reloadGrid");
        }  
    });   
}