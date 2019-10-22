$(function() {
	// grid参数
	$("#sys_message_grid").data("gridOptions", {
		height : 525,//parent.getClientHeights(),
		url :  rootpath + '/manager/sys/message/list.action?bean.messageid='+messageid,
		colNames : [  pub_operation,'公告ID', sys_message_title, sys_message_time, sys_message_content ],//列显示名称
		colModel : [ //	align:left,center,right,默认left;classes:列的css;datefmt:默认Y-m-d;defval:查询字段的默认值;editable:单元格是否可编辑,默认false;
					 //fixed:列宽是否固定不可变,默认false;search:在搜索模式下，定义此列是否可以作搜索列
					 {name : 'processbtn',index:'', width:80, fixed:true, sortable:false, resize:false,search : false,formatter:btnformatter},
					 {name : 'messageid',index : 'messageid',width : 60,sorttype : "string",editable : true,hidden : true}, 
					 {name : 'messagetitle',index : 'messagetitle',fixed : true,width : 200,sorttype : 'string',editable : true,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}}, 
					// {name : 'shorttile',index : 'shorttile',fixed : true,width : 200,sorttype : 'string',editable : true,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}}, 
					 {name : 'mestime',index : 'mestime',fixed : true,width : 130,sorttype : 'string',editable : true,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}}, 
					 {name : 'mescontent',index : 'mescontent',fixed : false,width : 150,sortable : false,editable : true,searchoptions : {sopt : [ 'cn' ]},edittype : "textarea",editoptions : {rows : "2",cols : "10"}}
					],
		processbtncol:2,//操作按钮放在第几个列
		isedit:false,//是否可编辑列表
		advsearch:true,
		viewurl : "/manager/sys/message/doModify.action",
		addurl : "/manager/sys/message/doAdd.action",
		modifyurl : "/manager/sys/message/doModifySave.action",
		deleteurl : "/manager/sys/message/delete.action"
	});
	
	initgrid("sys_message");
});

//删除修改进入表单时传递参数
function getGridParams(_bizname){
	var selectedRowId = $("#"+_bizname+"_grid").jqGrid("getGridParam", "selrow");  
    var str = $("#"+_bizname+"_grid").jqGrid("getRowData", selectedRowId); // 根据行ID，获取选中行的数据
    return {"bean.messageid" : str.messageid};
}

function getParams(_bizname){
	var selectedRowId = $("#"+_bizname+"_grid").jqGrid("getGridParam", "selrow");  
    var str = $("#"+_bizname+"_grid").jqGrid("getRowData", selectedRowId); // 根据行ID，获取选中行的数据
    return "?bean.messageid="+str.messageid;  
}

//取编辑grid列表数据
function getEditGridParams(_bizname,selectedRowId){
	var datalist = $("#"+_bizname+"_grid").jqGrid('getRowData', selectedRowId);
	return {
        "bean.messageid" : datalist.messageid,
        "bean.messagetitle" : datalist.messagetitle,
        "bean.shorttile" : datalist.shorttile,
        //"bean.mestype" : datalist.mestype,
        "bean.mescontent" : datalist.mescontent
    }; 
}
function initEditGridRowData(_bizname,data,selectedRowId){
	var datalist = getEditGridParams(_bizname,selectedRowId);
	var dataJson = eval(data);
	return dataRow = {
    	messageid : dataJson.id,
        messagetitle : datalist.messagetitle,
        shorttile : datalist.shorttile,
        //mestype : datalist.mestype,
        mescontent : datalist.mescontent
    }; 
}