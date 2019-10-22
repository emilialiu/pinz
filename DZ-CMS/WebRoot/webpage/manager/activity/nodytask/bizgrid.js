$(function() {
	// grid参数
	$("#act_nodytask_grid").data("gridOptions",
					{
						height : 525,//parent.getClientHeights(),
						url :rootpath+ '/manager/task/list.action?bean.id='+id,
						colNames : [ pub_operation,
						             sys_task_id,
						             sys_task_taskDefinitionKey,sys_task_processname, sys_task_ywname,sys_task_appusername ,sys_task_nameWithoutCascade,'流程定义id',
								'流程实例id', '优先级'
								, sys_task_createTime, '任务逾期日期'
								, '任务所属人','assignee','buninesskey','uri','nodeaction'
								 ],// 列显示名称
						colModel : [ // align:left,center,right,默认left;classes:列的css;datefmt:默认Y-m-d;defval:查询字段的默认值;editable:单元格是否可编辑,默认false;
						// fixed:列宽是否固定不可变,默认false;search:在搜索模式下，定义此列是否可以作搜索列
						{name : 'processbtn',index : '',width : 100,fixed : true,sortable : false,resize : false,search : false,formatter : btnformat}
						, {name : 'id',index : 'id',width : 100,sorttype : "int",editable : false,fixed : true,hidden:true}
						, {name : 'taskDefinitionKey',index : 'taskDefinitionKey',width : 80,sorttype : "int",editable : false,fixed : true,hidden:true}
						,{name : 'processname',index : 'processname',fixed : true,width : 150,sorttype : 'string',editable : false,searchoptions : {sopt : [ 'cn','eq' ]}}
						,{name : 'ywname',index : 'ywname',fixed : true,width : 160,sortable : false,editable : false,searchoptions : {sopt : [ 'cn' ]}}
						,{name : 'appusername',index : 'appusername',fixed : true,width : 100,sorttype : 'string',editable : false,searchoptions : {sopt : [ 'cn','eq' ]}} 
						,{name : 'nameWithoutCascade',index : 'nameWithoutCascade',fixed : true,width : 180,sorttype : 'int',editable : false,fixed:true,searchoptions : {sopt : [ 'cn' ]}},
						 {name : 'processDefinitionId',index : 'processDefinitionId',fixed : true,width : 160,sorttype : 'string',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne' ]},hidden:true}
						 , {name : 'processInstanceId',index : 'processInstanceId',fixed : true,width : 100,sortable : false,editable : false,searchoptions : {sopt : [ 'cn' ]},hidden:true}
						, {name : 'priority',index : 'priority',fixed : true,width : 50,sortable : false,editable : false,searchoptions : {sopt : [ 'cn' ]},hidden:true}
						, {name : 'createTime',index : 'createTime',fixed : true,width : 160,sortable : false,editable : false,searchoptions : {sopt : [ 'cn' ]},search:false}
						, {name : 'dueDate',index : 'dueDate',fixed : true,width : 160,sortable : false,editable : false,searchoptions : {sopt : [ 'cn' ]},hidden:true}
						, {name : 'owner',index : 'owner',fixed : true,width : 160,sortable : false,editable : false,searchoptions : {sopt : [ 'cn' ]},hidden:true}
						,{name : 'assigneeWithoutCascade',index : 'assigneeWithoutCascade',width : 100,fixed : true,sortable : false,resize : false,search : false,hidden:true}
						,{name : 'bussinesskey',index : 'bussinesskey',fixed : true,width : 100,sorttype : 'int',editable : false,hidden:true,fixed:true}
						,{name : 'uri',index : 'uri',fixed : true,width : 100,sorttype : 'int',editable : false,hidden:true,fixed:true}
						,{name : 'nodeaction',index : 'nodeaction',fixed : true,width : 100,sorttype : 'int',editable : false,hidden:true,fixed:true
						}
						],
						// processbtncol:2,//操作按钮放在第几个列
						isedit : true,// 是否可编辑列表
						advsearch : true,
						multiSort : true
						
					});

	initgrid("act_nodytask");
	initvalid("act_nodytask");

	
	// 弹出表单
	$("#act_nodytask_info").dialog({
		autoOpen : false,
		modal : true,
		resizable : true,
		width : 700,
		
		buttons : { // 为对话框添加按钮
			button_cancel : function() {
				$("#act_nodytask_info").dialog("close");
			},
			button_submit : function() {
				subdata();
			}
		}
	});
});
function btnformat(cellvalue, options, rowobj) {
	if(rowobj.assigneeWithoutCascade==null || rowobj.assigneeWithoutCascade==""){
		edit = "<a class='ui-pg-div' href='#' onClick=claim('"+rowobj.id+"') style='float: left; margin-left: 23px;'   title='' >"+sys_task_claim+"</a>";
	}else{                                                                                                                                                                                
		edit = "<a class='ui-pg-div' href='#' onClick=handle('"+rowobj.taskDefinitionKey+"','"+rowobj.nameWithoutCascade+"','"+rowobj.id+"','"+rowobj.bussinesskey+"','"+rowobj.uri+"','"+rowobj.nodeaction+"','"+rowobj.processInstanceId+"','"+rowobj.processDefinitionId+"') style='float: left; margin-left: 23px;'   title='' >"+sys_task_handle+"</a>";
	}
	return edit;
}

/**
 * 签收任务
 * @param taskid
 */
function claim(taskid){
	 $.ajax({  
	        url : rootpath+'/manager/activity/claim.action',
	        data : {"taskId":taskid},  
	        cache : false,  
	        error : function(data) {  
	        	alertMsg(pub_messageajaxerror);  
	        },  
	        success : function(data) {  
	        	var dataJson = eval(data);
	            if(dataJson.success) {
	                $("#act_nodytask_grid").trigger("reloadGrid");
	            } else {  
	            	alertMsg("操作失败"+dataJson.errormessage); 
	            	$("#act_nodytask_grid").trigger("reloadGrid");
	            }  
	        }  
	    });  
}
/**
 * 任务处理
 * @param taskDefinitionKey
 * @param name
 * @param id
 */
function handle(taskDefinitionKey,name,id,bussinesskey,uri,nodeaction,processInstanceId,processDefinitionId){
   var url=rootpath+uri+"?bussinesskey="+bussinesskey;
   //var consoleDlg = $("#act_nodytask_info");
   //历史记录的url
  // var hisurl = rootpath+"/manager/activity/nodytask/hismain.jsp?processDefinitionId="+processDefinitionId+"&bussinesskey="+bussinesskey;
   //consoleDlg.find("#buniessinfo").attr("src",url);
   //consoleDlg.find("#hismain").attr("src",hisurl);
  // consoleDlg.find("#taskDefinitionKey").attr("value",taskDefinitionKey);
   //consoleDlg.find("#taskId").attr("value",id);
   //consoleDlg.find("#nodeaction").attr("value",nodeaction);
  // consoleDlg.find("#bussinesskey").attr("value",bussinesskey);
   //consoleDlg.find("#processInstanceId").attr("value",processInstanceId);
   //consoleDlg.find("#processDefinitionId").attr("value", processDefinitionId);
   //window.location.href=rootpath+'/manager/activity/nodytask/dyform.jsp';
   //consoleDlg.find("#buniessinfo").contents().find("textarea").attr("disabled","disabled"); 
   //$("#act_nodytask_info").dialog("option", "title",name+"处理").dialog("open");
   parent.createwindow("act_nodytask",'/webpage/manager/activity/nodytask/dyform.jsp?processDefinitionId='+processDefinitionId+'&processInstanceId='+processInstanceId+"&bussinesskey="+bussinesskey+"&taskDefinitionKey="+taskDefinitionKey+"&nodeaction="+nodeaction+'&url='+url+'&taskId='+id+'&nodeaction='+nodeaction,name+sys_task_handle,900,600);
}

/**
 * 刷新待办列表
 */
function refresh(){
	$("#act_nodytask_grid").setGridParam({page:1}).trigger("reloadGrid");
}

