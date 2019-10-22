$(function() {
	// grid参数
	$("#act_processinstance_grid").data("gridOptions",
					   {height : parent.getClientHeights(),
						url : rootpath+ '/manager/processInstance/list.action',
						colNames : [ pub_operation,
								'执行ID',
								'流程实例ID', '流程定义ID',
								sys_processinstance_processname,sys_processinstance_ywname,sys_processinstance_appusername,sys_processinstance_currentnode,sys_processinstance_createtime,sys_processinstance_suspensionState
								 ],// 列显示名称
						colModel : [ // align:left,center,right,默认left;classes:列的css;datefmt:默认Y-m-d;defval:查询字段的默认值;editable:单元格是否可编辑,默认false;
						// fixed:列宽是否固定不可变,默认false;search:在搜索模式下，定义此列是否可以作搜索列
						{name : 'processbtn',index : '',width : 120,fixed : true,sortable : false,resize : false,search : false,formatter : btnformat}
						, {name : 'id',index : 'id',width : 100,sorttype : "int",editable : false,fixed : true,hidden:true}
						, {name : 'processInstanceId',index : 'processInstanceId',width : 80,sorttype : "int",editable : false,fixed : true,hidden:true}
						, {name : 'processDefinitionId',index : 'processDefinitionId',fixed : true,width : 150,sorttype : 'int',editable : false,fixed:true,hidden:true}
						, {name : 'processname',index : 'processname',fixed : true,width : 150,sorttype : 'string',editable : false,searchoptions : {sopt : [ 'cn','eq' ]}}
						, {name : 'ywname',index : 'ywname',fixed : true,width : 150,sorttype : 'string',editable : false,searchoptions : {sopt : [ 'cn','eq' ]}}
						, {name : 'appusername',index : 'appusername',fixed : true,width : 100,sorttype : 'string',editable : false,searchoptions : {sopt : [ 'cn','eq' ]}} 
						,{name : 'taskname',index : 'taskname',fixed : true,width : 180,sorttype : 'string',editable : false,formatter : nameformat,searchoptions : {sopt : [ 'cn','eq' ]}}
						, {name : 'createtime',index : 'createtime',fixed : true,width : 160,sortable : false,editable : false,search : false}
						, {name : 'suspensionState',index : 'suspensionState',fixed : true,width : 160,sortable : false,editable : false,search : false,
							formatter : function(v,opt, rec) {
								if(v=="1"){
									return sys_processinstance_no;
								}else{
									return sys_processinstance_yes;
								}
							}
						}
						],
						// processbtncol:2,//操作按钮放在第几个列
						isedit : true,// 是否可编辑列表
						advsearch : true,
						multiSort : true,
						viewurl : rootpath
								+ "/manager/activity/doModify.action",
						addurl : rootpath
								+ "/manager/model/doAddSave.action",
						modifyurl : rootpath
								+ "/manager/sys/role/doModifySave.action?actiontype=modifysave",
						deleteurl : rootpath+"/manager/model/delete.action?actiontype=delete"

					});

	initgrid("act_processinstance");
	initvalid("act_processinstance");
	$("#act_processinstance_grid_pager_left td").slice(0, 4).hide();
});
function btnformat(cellvalue, options, rowobj) {
	if(rowobj.suspensionState=="2"){
		edit = "<a class='ui-pg-div' href='#' onClick=changState('active','"+rowobj.processInstanceId+"') style='float: left; margin-left: 23px;'   title='' >"+sys_processinstance_activate+"</a>";
	}else{
		edit = "<a class='ui-pg-div' href='#' onClick=changState('suspend','"+rowobj.processInstanceId+"') style='float: left; margin-left: 23px;'   title='' >"+sys_processinstance_btnHangup+"</a>";
	}
	return edit;
}
function nameformat(cellvalue, options, rowobj){
	edit = "<a  pid='"+rowobj.id+"' pdid='"+rowobj.processDefinitionId+"' title=\""+sys_processinstance_viewflowchart+"\" href='javascript:void(0);'  onclick='graphTrace(this)' >"+rowobj.taskname+"</a>";
	return edit;
}
/**
 * 列表中是否挂起的操作
 * @param obj
 */
function changState(state,processInstanceId){
	 $.ajax({  
	        url : rootpath+'/manager/processInstance/updateState.action',
	        data : {"processInstanceId":processInstanceId,"state":state},  
	        cache : false,  
	        error : function(data) {  
	        	alertMsg(pub_messageajaxerror);  
	        },  
	        success : function(data) {  
	        	var dataJson = eval(data);
	            if(dataJson.success) {
	                alertMsg(dataJson.id);  
	                $("#act_processinstance_grid").trigger("reloadGrid");
	            } else {  
	            	alertMsg(operationfaile+dataJson.errormessage); 
	            	$("#act_processinstance_grid").trigger("reloadGrid");
	            }  
	        }  
	    });  
}

function graphTrace(option){
	//'/activiti/diagram-viewer/index.html?processDefinitionId=' + opts.pdid + '&processInstanceId=' + opts.pid
	//parent.createwindow("act_processinstanceview",'/webpage/manager/activity/processinstance/info.jsp?pid='+$(option).attr("pid")+"&pdid="+$(option).attr("pdid"),'流程跟踪',900,700);
	parent.createwindow("act_processinstanceview",'/plugins/activity/diagram-viewer/index.html?processInstanceId='+$(option).attr("pid")+"&processDefinitionId="+$(option).attr("pdid"),sys_processinstance_processtracking,900,700);
}


