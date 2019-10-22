var realurl="";
$(function() {
	getDbInfo();

	$("#message_grid").data("gridOptions", {
		height :179,// parent.getClientHeights()-350,
		url :  rootpath + '/manager/sys/message/list.action',
		colNames : [ 
		            '公告ID', '标题','发布时间'
					],//列显示名称
		colModel : [ //	align:left,center,right,默认left;classes:列的css;datefmt:默认Y-m-d;defval:查询字段的默认值;editable:单元格是否可编辑,默认false;
					 //fixed:列宽是否固定不可变,默认false;search:在搜索模式下，定义此列是否可以作搜索列
		             {name : 'messageid',index : 'messageid',width : 60,sorttype : "string",editable : true,hidden : true}, 
					 {name : 'messagetitle',index : 'messagetitle',fixed : false,width : 100,sorttype : 'string',editable : true,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}}, 
					 {name : 'mestime',index : 'mestime',fixed : false,width : 100,sorttype : 'string',editable : true,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}}
		             ],
        onSelectRow : function(rowid,status) {
        	     _openDialog4Reading('message');
 		},
 		viewurl : "/manager/sys/message/doModify.action",
		isedit:false,//是否可编辑列表
		advsearch:true,
		multiSort:true,
		showrownumbers:false
	});
	
	$("#shssc_grid").data("gridOptions", {
		height : 179,
		url :  rootpath + '/webpage/biz/bb/daily/shsscList.action?bean.produceDate='+$("#pdate").val(),
		colNames : [ 
					'序号',
					'台阶',
					'爆落量(t)',
					'高钙(t)',
					'高硅(t)',
					'高镁(t)',
					'泥夹石(t)',
					'存矿量(t)',
					'出矿量(t)',
					'转场量(t)'
					],//列显示名称
		colModel : [ //	align:left,center,right,默认left;classes:列的css;datefmt:默认Y-m-d;defval:查询字段的默认值;editable:单元格是否可编辑,默认false;
					 //fixed:列宽是否固定不可变,默认false;search:在搜索模式下，定义此列是否可以作搜索列
					{name : 'xh',index : 'xh',fixed : true,hidden:true,width : 100,sorttype : 'varchar(40)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
					{name : 'projectName',index : 'projectName',fixed : true,width : 100,sorttype : 'varchar(100)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
					{name : 'rpll',index : 'rpll',fixed : true,width : 100,sorttype : 'decimal(16,2)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
					{name : 'rggksl',index : 'rggksl',fixed : true,width : 100,sorttype : 'decimal(16,2)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
					{name : 'rggiksl',index : 'rggiksl',fixed : true,width : 100,sorttype : 'decimal(16,2)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
					{name : 'rgmksl',index : 'rgmksl',fixed : true,width : 100,sorttype : 'decimal(16,2)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
					{name : 'rnjs',index : 'rnjs',fixed : true,width : 100,sorttype : 'decimal(16,2)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
					{name : 'rcnkl',index : 'rcnkl',fixed : true,width : 100,sorttype : 'decimal(16,2)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
					{name : 'rckl',index : 'rckl',fixed : true,width : 100,sorttype : 'decimal(16,2)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
					{name : 'rzcl',index : 'rzcl',fixed : true,width : 100,sorttype : 'decimal(16,2)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}}
				],
		isedit:false,//是否可编辑列表
		advsearch:true,
		footerrow:true,// 合计标识
		multiSort:true,
		gridComplete:function(){ 
			var rowNum=parseInt($(this).getGridParam("records"),10); 	
			if(rowNum>0){ 
				var produceDate = $("#pdate").val();
				createSumShsscData(produceDate);
				$(this).footerData('set',shsscdata);  
				$("#shssc_grid_box").find(".ui-jqgrid-sdiv").show(); 
			}else{
				$("#shssc_grid_box").find(".ui-jqgrid-sdiv").hide(); 
			}
		}
	});

	$("#alarm_grid").data("gridOptions", {
		height :179,//parent.getClientHeights()-280,
		url :  rootpath + '/manager/sys/alarm/list.action',
		colNames : [ 
		            '告警ID', '告警名称', '告警类型','告警类型','告警等级','发布时间','备注'
					],//列显示名称
		colModel : [ //	align:left,center,right,默认left;classes:列的css;datefmt:默认Y-m-d;defval:查询字段的默认值;editable:单元格是否可编辑,默认false;
					 //fixed:列宽是否固定不可变,默认false;search:在搜索模式下，定义此列是否可以作搜索列
		             {name : 'alarmid',index : 'alarmid',width : 80,sorttype : "int",editable : true,hidden : true}, 
					 {name : 'alarmtitle',index : 'alarmtitle',fixed : true,width : 300,sorttype : 'string',searchoptions : {sopt : [ 'cn', 'eq', 'ne' ]}},
					 {name : 'alarmtype',index : 'alarmtype',fixed : true,width : 65,sorttype : 'string',search:false,hidden:true},
					 {name : 'alarmtypename',index : 'alarmtypename',fixed : true,width : 65,sorttype : 'string',search:false}, 
					 {name : 'alarmlevel',index : 'alarmlevel',fixed : true,width : 65,sorttype : 'string',search:false
						 ,formatter:function(cellvalue, options, rowobj){
								if(rowobj.alarmtype=="GJLB002" && cellvalue!=""){
									return "<div style='background:red;width:100%;height:100%'></div>";
								}else{
									return "<div style='background:yellow;width:100%;height:100%'></div>";
								}
								
							}}, 
					 {name : 'addtime',index : 'addtime',fixed : true,width : 140,sorttype : 'string',searchoptions : {sopt : [ 'cn', 'eq', 'ne' ]}}, 	
					 {name : 'memo',index : 'memo',fixed : true,width : 100,sorttype : 'string',searchoptions : {sopt : [ 'cn', 'eq', 'ne' ]}}
				],
		isedit:false,//是否可编辑列表
		advsearch:true,
		multiSort:true,
		showrownumbers:false//隐藏序号列
	});
	
	initgrid("message");
	$("#message_grid_pager_left td").slice(0, 2).hide();
	$("#message_grid_pager_left td").slice(3, 4).hide();
	initgrid("shssc");
	initgrid("alarm");
	$("#alarm_grid_pager_left td").slice(1, 2).hide();
	//$('.ui-jqgrid-hdiv').hide();//隐藏表头
	$("#act_nodytask_info").dialog({
		autoOpen : false,
		modal : true,
		resizable : true,
		width : 700,
		buttons : { // 为对话框添加按钮
			"取消" : function() {
				$("#act_nodytask_info").dialog("close");
			},
			"提交" : function() {
				subdata();
			}
		}
	});
});

function getParams(_bizname){
	var selectedRowId = $("#"+_bizname+"_grid").jqGrid("getGridParam", "selrow");  
    var str = $("#"+_bizname+"_grid").jqGrid("getRowData", selectedRowId); // 根据行ID，获取选中行的数据
    return "?bean.messageid="+str.messageid;  
}

function btnformat(cellvalue, options, rowobj) {
	if(rowobj.assigneeWithoutCascade==null || rowobj.assigneeWithoutCascade==""){
		edit = "<a class='ui-pg-div' href='#' onClick=claim('"+rowobj.id+"') style='color:#307ECC;'   title='"+sys_task_claim+"' >"+sys_task_claim+"</a>";
	}else{                                                                                                                                                                                
		edit = "<a class='ui-pg-div' href='#' onClick=handle('"+rowobj.taskDefinitionKey+"','"+rowobj.nameWithoutCascade+"','"+rowobj.id+"','"+rowobj.bussinesskey+"','"+rowobj.uri+"','"+rowobj.nodeaction+"','"+rowobj.processInstanceId+"','"+rowobj.processDefinitionId+"') style='color:#307ECC;'  title='"+sys_task_handle+"' >"+sys_task_handle+"</a>";
	}
	return edit;
}

function selectmore(_bizname) {
	var obj=$("#"+_bizname+"_btn_more");
	var funccode=$(obj).attr("funccode");
	var url=$(obj).attr("url");
	var funcname=$(obj).attr("funcname");
	parent.addTabs({id:funccode,title:funcname,close:true,url:url});
	
};

function todo(obj) {
	var funccode=$(obj).attr("funccode");
	var funcname=$(obj).attr("funcname");
	var url=$(obj).attr("url");
	parent.addTabs({id:funccode,title:funcname,close:true,url:url});
};

function selectInfoByDate(){
	$("#shssc_grid").jqGrid('setGridParam', {
		url :  rootpath + "/webpage/biz/bb/daily/shsscList.action?&bean.produceDate="+$("#pdate").val(),
		page : 1
	}).trigger("reloadGrid");
}

//获得石灰石生产总数据
function createSumShsscData(produceDate){
	$.ajax( {  
		url : rootpath + '/webpage/biz/bb/daily/shsscListSum.action',
		type:'POST',
		data : {'bean.produceDate':produceDate},  
		cache : false,
		async : false,
		error : function(data) {  
			alertErrorMsg("系统ajax交互错误");   
		},  
		success : function(data) {
			shsscdata = eval('('+data+')');	
		}  
	});
}
/**
 * 获取待办事项
 */
function getDbInfo(){
	$.ajax( {  
		url : rootpath + '/main/daiban/getDbInfo.action',
		type:'POST',
		data : {},  
		cache : false,
		async : false,
		error : function(data) {  
			alertErrorMsg("系统ajax交互错误");   
		},  
		success : function(data) {
			$("#tomatter").html(data);	
		}  
	});
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
	        	alertMsg("系统ajax交互错误");  
	        },  
	        success : function(data) {  
	        	var dataJson = eval(data);
	            if(dataJson.success) {
	                $("#tomatter_grid").trigger("reloadGrid");
	            } else {  
	            	alertMsg("操作失败"+dataJson.errormessage); 
	            	$("#tomatter_grid").trigger("reloadGrid");
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
   parent.createwindow("act_nodytask",'/webpage/main/dyform.jsp?processDefinitionId='+processDefinitionId+'&processInstanceId='+processInstanceId+"&bussinesskey="+bussinesskey+"&taskDefinitionKey="+taskDefinitionKey+"&nodeaction="+nodeaction+'&url='+url+'&taskId='+id+'&nodeaction='+nodeaction,name+" "+sys_task_handle,900,600);
}

function refresh(){
	$("#tomatter_grid").setGridParam({page:1}).trigger("reloadGrid");
}

