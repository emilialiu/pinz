$(function() {
	// grid参数
	$("#sys_role_grid").data("gridOptions", {
		height : parent.getClientHeights(),
		url :  rootpath + '/manager/sys/role/list.action',
		colNames : [ '操作', '角色ID', '角色名称', '角色编码', '备注', '部门' ],//列显示名称
		colModel : [ //	align:left,center,right,默认left;classes:列的css;datefmt:默认Y-m-d;defval:查询字段的默认值;editable:单元格是否可编辑,默认false;
					 //fixed:列宽是否固定不可变,默认false;search:在搜索模式下，定义此列是否可以作搜索列
					 {name : 'processbtn',index:'', width:80, fixed:true, sortable:false, resize:false,search : false,formatter:btnformat},
					 {name : 'roleid',index : 'roleid',width : 255,fixed:true,sorttype : "int",editable : false,hidden : false}, 
					 {name : 'rolename',index : 'rolename',fixed : true,width : 150,sorttype : 'string',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne']}}, 
					 {name : 'acginame',index : 'acginame',fixed : true,width : 150,sorttype : 'string',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne']}}, 
					 {name : 'memo',index : 'memo',fixed : true,width : 250,sortable : false,editable : false,searchoptions : {sopt : [ 'cn' ]},edittype : "textarea",editoptions : {rows : "2",cols : "10"}}, 
					 {name : 'deptid',index : 'deptid',width : 60,editable : false,hidden : true} 
					],
		processbtncol:2,//操作按钮放在第几个列
		isedit:false,//是否可编辑列表
		advsearch:true,
		multiSort:true,
		viewurl : "/manager/sys/role/doModify.action",
		addurl : "/manager/sys/role/doAdd.action",
		modifyurl : "/manager/sys/role/doModifySave.action",
		deleteurl : "/manager/sys/role/delete.action"
	});
	
	initgrid("sys_role");
});

function btnformat(cellvalue,options,rowobj){
	edit = "<div class='ui-pg-div' style='float: left; margin-left: 23px;' title='功能' onclick=Editfunc('"+options.rowId+"')><span class='ui-icon fa-edit'/></div>";
	return edit;
}
//删除修改进入表单时传递参数
function getGridParams(_bizname){
	var selectedRowId = $("#"+_bizname+"_grid").jqGrid("getGridParam", "selrow");  
    var str = $("#"+_bizname+"_grid").jqGrid("getRowData", selectedRowId); // 根据行ID，获取选中行的数据
    return {"bean.roleid" : str.roleid};
}

function getParams(_bizname){
	var selectedRowId = $("#"+_bizname+"_grid").jqGrid("getGridParam", "selrow");  
    var str = $("#"+_bizname+"_grid").jqGrid("getRowData", selectedRowId); // 根据行ID，获取选中行的数据
    return "?bean.roleid="+str.roleid;  
}

//取编辑grid列表数据
function getEditGridParams(_bizname,selectedRowId){
	var datalist = $("#"+_bizname+"_grid").jqGrid('getRowData', selectedRowId);
	return {
        "bean.roleid" : datalist.roleid,
        "bean.rolename" : datalist.rolename,
        "bean.memo" : datalist.memo,
        "bean.deptid" : datalist.deptid,
        "bean.acginame" : datalist.acginame
    }; 
}
function initEditGridRowData(_bizname,data,selectedRowId){
	var datalist = getEditGridParams(_bizname,selectedRowId);
	var dataJson = eval(data);
	return dataRow = {
    	roleid : dataJson.id,
        rolename : datalist.rolename,
        memo : datalist.memo,
        deptid : datalist.deptid,
        acginame : datalist.acginame
    }; 
}

//功能
function Editfunc(rowid){
	var datalist = $("#sys_role_grid").jqGrid('getRowData', rowid);
	var url = "/webpage/manager/sys/role/funcinfo.jsp?roleid="+datalist.roleid;
	parent.createwindow("sys_role_func",url,"功能",1000,540,true);
}