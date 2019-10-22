$(function(){
	// grid参数
	$("#act_workflow_his_grid").data("gridOptions",
	{
		height : 300,
		url : rootpath+ '/manager/activity/hislist.action?key='+key,
		colNames : ['id',sys_workflow_name,'key', sys_workflow_version, 'xml', sys_workflow_diagramResourceName,
		            sys_workflow_deploymentTime,sys_workflow_suspended],// 列显示名称
		colModel : [ // align:left,center,right,默认left;classes:列的css;datefmt:默认Y-m-d;defval:查询字段的默认值;editable:单元格是否可编辑,默认false;
		// fixed:列宽是否固定不可变,默认false;search:在搜索模式下，定义此列是否可以作搜索列
		{name : 'id',index : 'id',width : 180,sorttype : "int",editable : false,fixed : true,hidden:true}
		,{name : 'name',index : 'name',fixed : true,width : 150,sorttype : 'string',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne' ]}}
		, {name : 'key',index : 'key',fixed : true,width : 150,sorttype : 'string',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne' ]}}
		, {name : 'version',index : 'version',fixed : true,width : 60,sortable : false,editable : false,searchoptions : {sopt : [ 'cn' ]}}
		, {name : 'resourceName',index : 'resourceName',width : 170,editable : false,fixed : true,formatter : XMLformat}
		, {name : 'diagramResourceName',index : 'diagramResourceName',width : 170,editable : false,fixed : true,formatter:PICformat}
		, {name : 'deploymentTime',index : 'deploymentTime',width : 120,editable : false,fixed : true}
		, {name : 'suspended',index : 'suspended',width : 90,editable : false,fixed : true,formatter:OPformat}
		],
		// processbtncol:2,//操作按钮放在第几个列
		isedit : false,// 是否可编辑列表
		advsearch : true,
		multiSort : true
		
	});
	initgrid("act_workflow_his");
});
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
	                $("#act_workflow_his_grid").trigger("reloadGrid");
	            } else {  
	            	parent.alertMsg("操作失败"+dataJson.errormessage); 
	            	$("#act_workflow_his_grid").trigger("reloadGrid");
	            }  
	        }  
	    });  
}